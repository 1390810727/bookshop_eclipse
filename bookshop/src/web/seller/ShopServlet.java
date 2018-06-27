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
 * ���д���shop�ύ���ļ������servlet
 * Ŀǰ������1����ȡ�����̼�id�����е���
 * 2�����������͵���id�浽session������
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
			
			//��session�л�ȡ��ǰ��½���̼�id
			String s_id=(String )session.getAttribute("s_id");
			
			/**���s_idΪnull,���ض��򵽵�½ҳ�棬���̼����µ�½*/
			if(s_id==null)
			{
				response.sendRedirect("/bookshop/login/seller_login.jsp");
				return ;
			}
			
			ShopListDao sld=new ShopListDao();
			List<Shop> list=sld.getShopList(s_id);
			request.setAttribute("shopList", list);
			
			//ת����self_interface
			request.getRequestDispatcher("seller/self_interface.jsp").forward(request, response);
		}
		
		/**
		 * ���̼�ѡ�����ʱ�����Ĳ���
		 */
		if("/chooseShop".equals(path))
		{
			System.out.println("  ֮ǰ ----");
			String s_no=request.getParameter("s_no");
			String shopName=request.getParameter("shopName");
			System.out.println(s_no+"   ----"+shopName);
			//����ȡ�ĵ���id�͵������ƴ浽session��
			
			session.setAttribute("s_no", s_no);
			session.setAttribute("shopName", shopName);
			
			//����֮��ת����seller_interface
			
			response.sendRedirect("/bookshop/getShopInfo.shopAction");
		}
		
		/**
		 * ��ȡָ���ĵ��̵���Ϣ
		 */
		if("/getShopInfo".equals(path))
		{
			String s_id=(String)session.getAttribute("s_id");
			String s_no=(String)session.getAttribute("s_no");
			
			ShopInfoDao sid=new ShopInfoDao();
			
			Shop shop=sid.getShopInfo(s_id, s_no);
			
			//��shop�󶨵�request�����Ȼ��ת����shop_info.jsp����
			
			request.setAttribute("shop", shop);
			request.getRequestDispatcher("seller/shop_info.jsp").forward(request, response);
			
		}
		
		/**���ص�����Ϣ*/
		
		if("/loadShopInfo".equals(path))
		{
			String s_id=(String)session.getAttribute("s_id");
			String s_no=(String)session.getAttribute("s_no");
			

			ShopInfoDao sid=new ShopInfoDao();
			
			Shop shop=sid.getShopInfo(s_id, s_no);
			
			//��shop�󶨵�request�����Ȼ��ת����shop_info.jsp����
			
			request.setAttribute("shop", shop);
			
			request.getRequestDispatcher("seller/shop_update.jsp").forward(request,response);
			
		}
		
		/**���µ�����Ϣ*/
		
		if("/updateShopInfo".equals(path))
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
					String shopNo=(String) request.getSession().getAttribute("s_no");
					
					
					//����ͼƬ��������·��
					String imgPath="D:\\bookshop\\shop_img\\";
					
					String imgAddress=null;
						
					//1.����һ���ļ���������
					DiskFileItemFactory dfif=new DiskFileItemFactory();
					//2.����һ���ļ�������
					ServletFileUpload upload=new ServletFileUpload(dfif);
					upload.setFileSizeMax(1024*1024*5);//�����ϴ��ĵ����ļ����Ϊ5Mb
					upload.setSizeMax(1024*1025*10);//�����ϴ����ܹ��ļ���С���Ϊ10mb
					
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
								//System.out.print("�ļ�������Ƿ�Ϊ�գ�"+(item==null));
								
								if(! "".equals(item.getName()))
								{
									System.out.println("�ļ�����"+item.getName());
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
								}else {
									//���Ϊ�յĻ����������ϴ�ͼƬ
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
					
					//������³ɹ�����ת����shop_info.jspҳ�棬����ʾ���³ɹ���������ʾ����ʧ��
					if(sud.updateShopInfo(shop))
					{
							request.setAttribute("updateMessage", "�޸ĳɹ�");
							//response.getWriter().println("�޸ĳɹ�");
							request.getRequestDispatcher("getShopInfo.shopAction").forward(request, response);
					}else {
						
						request.setAttribute("updateMessage", "�޸�ʧ��");
						//response.getWriter().println("�޸�ʧ��");
						request.getRequestDispatcher("getShopInfo.shopAction").forward(request, response);
					}
		}
	}
	

	

}
