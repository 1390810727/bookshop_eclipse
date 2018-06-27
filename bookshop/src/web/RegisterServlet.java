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
		//��ȡ����·��
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
			
			//����������Щ�������һ����װ����
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
				//����ע��ɹ�,��ת���̼Ҹ��˽���
				pw.println("ע��ɹ�");
				
				//��id���������浽session��
				session.setAttribute("s_id", id);
				session.setAttribute("seller_name", name);
				request.setAttribute("message", "������Ҫע��һ�����̣���ע��");
				
				//ת�������̵�ע��ҳ��
				request.getRequestDispatcher("seller/shopRegister.jsp").forward(request, response);
				
			}
			else {
				pw.print("ע��ʧ��");
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
				pw.println("�˺ſ�ʹ��");
			}else{
				pw.println("�˺��Ѵ���");
			}
			
			
			return;
		}
		
		if("/registerShop".equals(path))
		{
			//��Ҫ��shopͼƬ�ϴ�������
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
					
//					System.out.println("��shop���л�õ����ݣ�"+s_no+"|"+shopName+"|"+province+"|");
					//����̼ҵ�id
					String s_id=(String)request.getSession().getAttribute("s_id");
					String imgPath=projectPath.substring(0,projectPath.lastIndexOf("\\"));
					imgPath=imgPath.substring(imgPath.lastIndexOf("\\"));
					//����ͼƬ��������·��
					imgPath+="\\img\\shop_img\\";
					projectPath+="img\\shop_img\\";
					String imgAddress=null;
						
					//1.����һ���ļ���������
					DiskFileItemFactory dfif=new DiskFileItemFactory();
					//2.����һ���ļ�������
					ServletFileUpload upload=new ServletFileUpload(dfif);
					upload.setFileSizeMax(1024*1024*5);//�����ϴ��ĵ����ļ����Ϊ5Mb
					upload.setSizeMax(1024*1024*10);//�����ϴ����ܹ��ļ���С���Ϊ10mb
					
					//���������ϴ�����
					upload.setHeaderEncoding("UTF-8"); 
					try {
						//��request�����������ݻ��ļ����з���
						//parseRequest��������һ��FileItem���͵ļ���
						List<FileItem> list=upload.parseRequest(request);
						
						//�������Ի�ȡ��FileItem���͵ļ��Ͻ��з���,�ж�ÿ��FileItem�Ƿ����ļ������ݲ�ͬ��������ͬ�Ĵ���
						for(FileItem item:list)
						{
							//���item����ͨ����򣨼������ļ������
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
								//������ļ�����������������ļ����浽���ش���
								
								//1.��ȡ�ļ���
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
								byte[] bytes=new byte[1024*10];//�����СΪ10k
								int i=-1;
								while((i=in.read(bytes))!=-1)
								{
									out.write(bytes, 0, i);
								}
								response.setCharacterEncoding("utf-8");
								System.out.print("�ϴ����");
								PrintWriter pw=response.getWriter();
								//pw.println("<center>�ϴ��ɹ�����</center>");
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
						response.getWriter().print("ע��ʧ��");
					}
				return;	
					
		}
	}//doPost()end
	

}
