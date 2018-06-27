package web.seller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import dao.seller.AddBookDao;
import dao.seller.BookInfoDao;
import dao.seller.BookListDao;
import dao.seller.BookSearchDao;
import dao.seller.BookUpdateDao;
import model.Book;
import model.BookInfo;
import model.Purchase;
import model.Sb;

/**
 * ����ͼ���һЩ����  ���д����serlvet
 * @author KUIKUI
 *
 */
public class BookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req,resp);	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session=request.getSession();
		PrintWriter pw=response.getWriter();
		
		//��ȡ��Ŀ�ĵ�ַ
		String imgPath=request.getSession().getServletContext().getRealPath("");
		String s_no=(String)session.getAttribute("s_no");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		//��ͼ���������
		if("/addBook".equals(path))
		{
			String b_no= null;
			String name = null;
			String type = null;
			String author = null;
			String publish = null;
			String publish_date = null;
			double price = 0;
			double sell_price = 0;
			int number = 0;
			double pur_price = 0;
			String book_info = null;
			
			String	projectPath=imgPath.substring(0,imgPath.lastIndexOf("\\"));
			projectPath=projectPath.substring(projectPath.lastIndexOf("\\"));
			imgPath=imgPath+"\\img\\book_img\\";
			projectPath+="\\img\\book_img\\";
			String imgAddress=null;
			
			String[] imgAddresses=new String[3];
			int j=0;
			
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
							case "b_no":
								b_no=item.getString("utf-8");
								break;
							case "name":
								name=item.getString("utf-8");
								break;
							case "type":
								type=item.getString("utf-8");
								break;
							case "author":
								author=item.getString("utf-8");
								break;
							case "publish":
								publish=item.getString("utf-8");
								break;
							case "publish_date":
								publish_date=item.getString("utf-8");
								break;
							case "price":
								price=Double.parseDouble(item.getString("utf-8"));
								break;
							case "sell_price":
								sell_price=Double.parseDouble(item.getString("utf-8"));
								break;
							case "number":
								number=Integer.parseInt(item.getString("utf-8"));
								break;
							case "pur_price":
								pur_price=Double.parseDouble(item.getString("utf-8"));
								break;
							case "book_info":
								book_info=item.getString("utf-8");
								break;
						}
					}
					else {
						//������ļ�����������������ļ����浽���ش���
						
						//1.��ȡ�ļ���
						//System.out.println(item.getName());
						
						if(item.getName()!="")
						{
							String fileName=item.getName().substring(item.getName().lastIndexOf("\\")+1);
							System.out.println(fileName);
							InputStream in=item.getInputStream();
							imgAddress=imgPath+s_no;
							projectPath+=s_no;
							File file=new File(imgAddress);
							if(!file.exists())
							{
								file.mkdirs();
							}
							
							imgAddress=imgAddress+"\\"+fileName;
							projectPath+="\\"+fileName;
							System.out.print(projectPath);
							file=new File(imgAddress);
							file.createNewFile();
							FileOutputStream out=new FileOutputStream(file);
							byte[] bytes=new byte[1024*10];//�����СΪ10k
							int i=-1;
							while((i=in.read(bytes))!=-1)
							{
								out.write(bytes, 0, i);
							}
							
							System.out.print("�ϴ����");
							
							imgAddresses[j]=projectPath;
							j++;
							pw.println("<center>�ϴ��ɹ�����</center>");
							in.close();
							out.close();
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}catch(Exception e )
			{
				e.printStackTrace();
			}
			
			
			boolean addBook=true;
			boolean addPurchase=true;
			boolean addSb=true;
			boolean usn=true;
			//���Ȳ�ѯ��ͼ�����Ƿ��Ѵ���
			BookInfoDao bid=new BookInfoDao();
			AddBookDao abd=new AddBookDao();
			//�������������� book�����������
			if(! bid.isExistInBook(b_no))
			{
			
				Book book=new Book();
				book.setB_no(b_no);
				book.setName(name);
				book.setType(type);
				book.setAuthor(author);
				book.setPrice(price);
				book.setPublish(publish);
				book.setPublish_date(publish_date);
				addBook=  abd.addBook(book);
			}
			//��ȡ��ǰϵͳʱ��
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			String today = sdf.format(d);  
			
			Purchase purchase=new Purchase();
			purchase.setS_no(s_no);
			purchase.setB_no(b_no);
			purchase.setPrice(pur_price);
			purchase.setNumber(number);
			purchase.setDate(today);
			addPurchase= abd.addPurchase(purchase);
			//����ͼ���Ƿ��Ѿ����ڣ����������ֱ���޸ĸõ��̵�ͼ����������
			//String s_no=(String)session.getAttribute("s_no");
			if(! bid.isExistInSb(b_no, s_no))
			{
				//���������������Ҫ��������ӵ���sb��
				Sb sb=new Sb();
				sb.setB_no(b_no);
				sb.setS_no(s_no);
				sb.setNumber(number);
				sb.setSell_price(sell_price);
				
				//Ĭ���Ƿ��ϼ�ʱ��
				sb.setIs_sell("��");
				sb.setBook_info(book_info);
				sb.setImg_address1(imgAddresses[0]);
				sb.setImg_address2(imgAddresses[1]);
				sb.setImg_address3(imgAddresses[2]);
				addSb=abd.addSb(sb);
				
			}else {
				//����Ѿ����ڣ���ֻ�޸�ָ�����̵�ͼ������
				usn=abd.updateSbNumber(s_no, b_no, number);
				
			}
			
			//��addBook��addPurchase��addSb��usn��Ϊtrueʱ��֤�������ɹ�������ʧ��
			System.out.println(addBook+"  "+addPurchase+"  "+addSb+"  "+usn);
			
			
			//��������ɹ�������ת��ͼ�����ҳ�棬����������ʾ��ӳɹ�
			if(addBook && addPurchase && addSb && usn)
			{
				request.setAttribute("addBookMessage", "���ɹ�");
				request.getRequestDispatcher("seller/book_storage.jsp").forward(request, response);
			}else {
				request.setAttribute("addBookMessage", "���ʧ��");
				request.getRequestDispatcher("seller/book_storage.jsp").forward(request, response);
				
			}
			
		}//if����
		
		//��ȡͼ�������Ϣ��·��
		if("/bookList".equals(path))
		{
			
			BookListDao bld=new BookListDao();
			List<BookInfo> unSellBookList=bld.unSellBookList(s_no);
			List<BookInfo> sellBookList=bld.sellBookList(s_no);
			
			request.setAttribute("unSellBookList", unSellBookList);
			request.setAttribute("sellBookList", sellBookList);
			
			//ת����book_manager.jspҳ��
			
			request.getRequestDispatcher("seller/book_manager.jsp").forward(request, response);
		}
		
		//��ͼ���ϼܻ��¼ܲ�������󣬷��ص� bookManagerҳ��
		if("/sellBook".equals(path))
		{
			//String s_no=request.getParameter("s_no");
			String b_no=request.getParameter("b_no");
			String isSell=request.getParameter("isSell");
			BookUpdateDao sud=new BookUpdateDao();
			if(sud.updateIsSell(s_no, b_no, isSell)) {
				//����ɹ����򷵻���ҳҳ��
				request.setAttribute("bookUpdateMessage", "�����ɹ�");
				request.getRequestDispatcher("bookList.bookAction").forward(request, response);
			}else {
				request.setAttribute("bookUpdateMessage", "����ʧ��");
				request.getRequestDispatcher("bookList.bookAction").forward(request, response);
			}
		}
		
		//��ѯ�鼮������һ���б�
		if("/searchBook".equals(path))
		{
			String searchContent=request.getParameter("searchContent");
			//System.out.print("��ѯ�鼮������,��ѯ������Ϊ��"+searchContent);
			
			BookSearchDao bsd=new BookSearchDao();
			List<BookInfo> bookSearchList=bsd.searchBook(s_no, searchContent);
			
			//��bookListת����book_search.jspҳ��
			request.setAttribute("bookSearchList", bookSearchList);
			
			request.getRequestDispatcher("seller/book_search.jsp").forward(request, response);
			
		}
		
		
		//�޸�ͼ����ۼ�
		if("/changeSellPrice".equals(path)) {
			String b_no=request.getParameter("b_no");
			double newSellPrice=Double.parseDouble(request.getParameter("newSellPrice"));
			System.out.println(b_no+" ���ۼۣ�"+newSellPrice);
			BookUpdateDao bud=new BookUpdateDao();
			if(bud.changeSellPrice(s_no, b_no, newSellPrice)) {
				//����޸ĳɹ�������ת����ǰ�����ҳ��
				response.sendRedirect("/bookshop/bookList.bookAction");
				
			} else {
				response.getWriter().println("�޸�ʧ��");
			}
			
			
		}
		
	}
	
}
