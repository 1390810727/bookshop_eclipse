package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.seller.SellerRegisterDao;
import dao.seller.ShopRegisterDao;
import model.Seller;
import model.Shop;

public class RegisterServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//获取请求路径
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		String projectPath=request.getSession().getServletContext().getRealPath("");
		
		if("/registerSeller".equals(path))
		{
				
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			String tel = request.getParameter("tel");
			
	//		System.out.println(id+"  "+name+"  "+password+"  "+gender+"  "+tel+"  "
	//				+shopName+"  "+address+"  "+introduction+"  ");
			
			//接下来将这些结果传到一个封装类中
			Seller seller=new Seller();
			seller.setId(id);
			seller.setName(name);
			seller.setPassword(password);
			seller.setGender(gender);
			seller.setTel(tel);
			PrintWriter pw=response.getWriter();
			SellerRegisterDao srd=new SellerRegisterDao();
			HttpSession session=request.getSession();
			
			if(srd.doRegister(seller))
			{
				//代表注册成功,跳转到商家个人界面
				pw.println("注册成功");
				
				//将id和姓名保存到session里
				session.setAttribute("s_id", id);
				session.setAttribute("seller_name", name);
				request.setAttribute("message", "您还需要注册一个店铺，请注册");
				
				//转发到店铺的注册页面
				request.getRequestDispatcher("seller/shopRegister.jsp").forward(request, response);
				
			}
			else {
				pw.print("注册失败");
			}
			
			return;
		}
		
		if("/seller_ajax".equals(path))
		{
			String id=request.getParameter("id");
			SellerRegisterDao srd=new SellerRegisterDao();
			PrintWriter pw=response.getWriter();
			
			if(srd.checkId(id))
			{
				pw.println("账号可使用");
			}else{
				pw.println("账号已存在");
			}
			
			
			return;
		}
		
		if("/registerShop".equals(path))
		{
			//需要将shop图片上传到本地
			// TODO Auto-generated method stub
						request.setCharacterEncoding("utf-8");
						
					String 	s_no=null;
					String shopName=null;
					String province=null;
					String city=null;
					String country=null;
					String street=null;
					String postCode=null;
					String introduction=null;
					
//					System.out.println("从shop表单中获得的数据："+s_no+"|"+shopName+"|"+province+"|");
					//获得商家的id
					String s_id=(String)request.getSession().getAttribute("s_id");
					String imgPath=projectPath.substring(0,projectPath.lastIndexOf("\\"));
					imgPath=imgPath.substring(imgPath.lastIndexOf("\\"));
					//设置图片背景保存路径
					imgPath+="\\img\\shop_img\\";
					projectPath+="img\\shop_img\\";
					String imgAddress=null;
						
					//1.创建一个文件解析工厂
					DiskFileItemFactory dfif=new DiskFileItemFactory();
					//2.创建一个文件解析器
					ServletFileUpload upload=new ServletFileUpload(dfif);
					upload.setFileSizeMax(1024*1024*5);//允许上传的单个文件最大为5Mb
					upload.setSizeMax(1024*1024*10);//允许上传的总共文件大小最大为10mb
					
					//设置中文上传处理
					upload.setHeaderEncoding("UTF-8"); 
					try {
						//对request请求来的数据或文件进行分析
						//parseRequest方法返回一个FileItem类型的集合
						List<FileItem> list=upload.parseRequest(request);
						
						//接下来对获取的FileItem类型的集合进行分析,判断每个FileItem是否是文件，根据不同类型做不同的处理
						for(FileItem item:list)
						{
							//如果item是普通输入框（即不是文件输入框）
							if(item.isFormField())
							{
								//System.out.println(item.getFieldName()+"  :"+item.getString("utf-8"));
								switch(item.getFieldName())
								{
									case "s_no":	
										s_no=item.getString("utf-8");
										break;
									case "shopName":
										shopName=item.getString("utf-8");
										break;
									case "province":
										province=item.getString("utf-8");
										break;
									case "city":
										city=item.getString("utf-8");
										break;
									case "country":
										country=item.getString("utf-8");
										break;
									case "street":
										street=item.getString("utf-8");
										break;
									case "code":
										postCode=item.getString("utf-8");
										break;
									case "introduction":
										introduction=item.getString("utf-8");
										break;
								}
							}
							else {
								//如果是文件输入框，则将请求来的文件保存到本地磁盘
								
								//1.获取文件名
								System.out.println(item.getName());
								String fileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);
								System.out.println(fileName);
								InputStream in=item.getInputStream();
								imgPath=imgPath+s_no+"\\";
								projectPath=projectPath+s_no+"\\";
								File file=new File(projectPath);
								if(!file.exists())
								{
									file.mkdirs();
								}
								
								projectPath+=fileName;
								imgAddress=imgPath+fileName;
								
								file=new File(projectPath);
								file.createNewFile();
								FileOutputStream out=new FileOutputStream(file);
								byte[] bytes=new byte[1024*10];//缓冲大小为10k
								int i=-1;
								while((i=in.read(bytes))!=-1)
								{
									out.write(bytes, 0, i);
								}
								response.setCharacterEncoding("utf-8");
								System.out.print("上传完毕");
								PrintWriter pw=response.getWriter();
								//pw.println("<center>上传成功！！</center>");
								in.close();
								out.close();
								
								
							}
						}
					} catch (FileUploadException e) {
						e.printStackTrace();
					}catch(Exception e )
					{
						e.printStackTrace();
					}
					System.out.println(projectPath+"-----"+imgAddress);
					
					Shop shop=new Shop();
					shop.setS_no(s_no);
					shop.setShopName(shopName);
					shop.setProvince(province);
					shop.setCity(city);
					shop.setCountry(country);
					shop.setStreet(street);
					shop.setPostCode(postCode);
					shop.setIntroduction(introduction);
					shop.setImgAddress(imgAddress);
					shop.setS_id(s_id);
					ShopRegisterDao srd=new ShopRegisterDao();
					if(srd.doShopRegister(shop)) {
						
						response.sendRedirect("/bookshop/seller/registerOK.jsp");
					}else {
						response.getWriter().print("注册失败");
					}
				return;	
					
		}
	}//doPost()end
	

}
