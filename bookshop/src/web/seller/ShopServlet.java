package web.seller;

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

import dao.seller.ShopInfoDao;
import dao.seller.ShopListDao;
import dao.seller.ShopUpdateDao;
import model.Shop;

/**
 * 集中处理shop提交来的简单任务的servlet
 * 目前包含，1、获取给定商家id的所有店铺
 * 2、将店铺名和店铺id存到session的请求
 * @author KUIKUI
 *
 */
public class ShopServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");;
		
		HttpSession session=request.getSession();
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println(path);
		if("/getShopList".equals(path))
		{
			
			//从session中获取当前登陆的商家id
			String s_id=(String )session.getAttribute("s_id");
			
			/**如果s_id为null,则重定向到登陆页面，让商家重新登陆*/
			if(s_id==null)
			{
				response.sendRedirect("/bookshop/login/seller_login.jsp");
				return ;
			}
			
			ShopListDao sld=new ShopListDao();
			List<Shop> list=sld.getShopList(s_id);
			request.setAttribute("shopList", list);
			
			//转发到self_interface
			request.getRequestDispatcher("seller/self_interface.jsp").forward(request, response);
		}
		
		/**
		 * 当商家选择店铺时所做的操作
		 */
		if("/chooseShop".equals(path))
		{
			System.out.println("  之前 ----");
			String s_no=request.getParameter("s_no");
			String shopName=request.getParameter("shopName");
			System.out.println(s_no+"   ----"+shopName);
			//将获取的店铺id和店铺名称存到session里
			
			session.setAttribute("s_no", s_no);
			session.setAttribute("shopName", shopName);
			
			//存完之后转发到seller_interface
			
			response.sendRedirect("/bookshop/getShopInfo.shopAction");
		}
		
		/**
		 * 获取指定的店铺的信息
		 */
		if("/getShopInfo".equals(path))
		{
			String s_id=(String)session.getAttribute("s_id");
			String s_no=(String)session.getAttribute("s_no");
			
			ShopInfoDao sid=new ShopInfoDao();
			
			Shop shop=sid.getShopInfo(s_id, s_no);
			
			//将shop绑定到request对象里，然后转发给shop_info.jsp界面
			
			request.setAttribute("shop", shop);
			request.getRequestDispatcher("seller/shop_info.jsp").forward(request, response);
			
		}
		
		/**加载店铺信息*/
		
		if("/loadShopInfo".equals(path))
		{
			String s_id=(String)session.getAttribute("s_id");
			String s_no=(String)session.getAttribute("s_no");
			

			ShopInfoDao sid=new ShopInfoDao();
			
			Shop shop=sid.getShopInfo(s_id, s_no);
			
			//将shop绑定到request对象里，然后转发给shop_info.jsp界面
			
			request.setAttribute("shop", shop);
			
			request.getRequestDispatcher("seller/shop_update.jsp").forward(request,response);
			
		}
		
		/**更新店铺信息*/
		
		if("/updateShopInfo".equals(path))
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
					String shopNo=(String) request.getSession().getAttribute("s_no");
					
					
					//设置图片背景保存路径
					String imgPath="D:\\bookshop\\shop_img\\";
					
					String imgAddress=null;
						
					//1.创建一个文件解析工厂
					DiskFileItemFactory dfif=new DiskFileItemFactory();
					//2.创建一个文件解析器
					ServletFileUpload upload=new ServletFileUpload(dfif);
					upload.setFileSizeMax(1024*1024*5);//允许上传的单个文件最大为5Mb
					upload.setSizeMax(1024*1025*10);//允许上传的总共文件大小最大为10mb
					
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
								//System.out.print("文件输入框是否为空："+(item==null));
								
								if(! "".equals(item.getName()))
								{
									System.out.println("文件名："+item.getName());
									String fileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);
									System.out.println(fileName);
									InputStream in=item.getInputStream();
									imgAddress=imgPath+s_no+"\\";
									File file=new File(imgAddress);
									if(!file.exists())
									{
										file.mkdirs();
									}
									
									imgAddress+=fileName;
									
									file=new File(imgAddress);
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
								}else {
									//如果为空的话，则不重新上传图片
									ShopInfoDao sid=new ShopInfoDao();
									Shop s=sid.getShopInfo(s_id, shopNo);
									imgAddress=s.getImgAddress();
								}
								
								
							}
						}
					} catch (FileUploadException e) {
						e.printStackTrace();
					}catch(Exception e )
					{
						e.printStackTrace();
					}
					
					
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
					ShopUpdateDao sud=new ShopUpdateDao();
					
					//如果更新成功，则转发到shop_info.jsp页面，并提示更新成功，否则提示更新失败
					if(sud.updateShopInfo(shop))
					{
							request.setAttribute("updateMessage", "修改成功");
							//response.getWriter().println("修改成功");
							request.getRequestDispatcher("getShopInfo.shopAction").forward(request, response);
					}else {
						
						request.setAttribute("updateMessage", "修改失败");
						//response.getWriter().println("修改失败");
						request.getRequestDispatcher("getShopInfo.shopAction").forward(request, response);
					}
		}
	}
	

	

}
