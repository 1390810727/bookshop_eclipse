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
 * 关于图书的一些请求  集中处理的serlvet
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
		
		//获取项目的地址
		String imgPath=request.getSession().getServletContext().getRealPath("");
		String s_no=(String)session.getAttribute("s_no");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		//做图书的入库操作
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
						//如果是文件输入框，则将请求来的文件保存到本地磁盘
						
						//1.获取文件名
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
							byte[] bytes=new byte[1024*10];//缓冲大小为10k
							int i=-1;
							while((i=in.read(bytes))!=-1)
							{
								out.write(bytes, 0, i);
							}
							
							System.out.print("上传完毕");
							
							imgAddresses[j]=projectPath;
							j++;
							pw.println("<center>上传成功！！</center>");
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
			//首先查询改图书编号是否已存在
			BookInfoDao bid=new BookInfoDao();
			AddBookDao abd=new AddBookDao();
			//如果存在则不再向表 book里添加数据了
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
			//获取当前系统时间
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
			//检测该图书是否已经存在，如果存在则直接修改该店铺的图书数量即可
			//String s_no=(String)session.getAttribute("s_no");
			if(! bid.isExistInSb(b_no, s_no))
			{
				//如果不存在我们需要将数据添加到表sb里
				Sb sb=new Sb();
				sb.setB_no(b_no);
				sb.setS_no(s_no);
				sb.setNumber(number);
				sb.setSell_price(sell_price);
				
				//默认是否上架时否
				sb.setIs_sell("否");
				sb.setBook_info(book_info);
				sb.setImg_address1(imgAddresses[0]);
				sb.setImg_address2(imgAddresses[1]);
				sb.setImg_address3(imgAddresses[2]);
				addSb=abd.addSb(sb);
				
			}else {
				//如果已经存在，则只修改指定店铺的图书数量
				usn=abd.updateSbNumber(s_no, b_no, number);
				
			}
			
			//当addBook、addPurchase、addSb、usn都为true时，证明操作成功，否则失败
			System.out.println(addBook+"  "+addPurchase+"  "+addSb+"  "+usn);
			
			
			//如果操作成功，则跳转到图书入库页面，并弹出框提示添加成功
			if(addBook && addPurchase && addSb && usn)
			{
				request.setAttribute("addBookMessage", "入库成功");
				request.getRequestDispatcher("seller/book_storage.jsp").forward(request, response);
			}else {
				request.setAttribute("addBookMessage", "入库失败");
				request.getRequestDispatcher("seller/book_storage.jsp").forward(request, response);
				
			}
			
		}//if结束
		
		//获取图书管理信息的路径
		if("/bookList".equals(path))
		{
			
			BookListDao bld=new BookListDao();
			List<BookInfo> unSellBookList=bld.unSellBookList(s_no);
			List<BookInfo> sellBookList=bld.sellBookList(s_no);
			
			request.setAttribute("unSellBookList", unSellBookList);
			request.setAttribute("sellBookList", sellBookList);
			
			//转发到book_manager.jsp页面
			
			request.getRequestDispatcher("seller/book_manager.jsp").forward(request, response);
		}
		
		//将图书上架或下架操作，完后，返回到 bookManager页面
		if("/sellBook".equals(path))
		{
			//String s_no=request.getParameter("s_no");
			String b_no=request.getParameter("b_no");
			String isSell=request.getParameter("isSell");
			BookUpdateDao sud=new BookUpdateDao();
			if(sud.updateIsSell(s_no, b_no, isSell)) {
				//如果成功，则返回主页页面
				request.setAttribute("bookUpdateMessage", "操作成功");
				request.getRequestDispatcher("bookList.bookAction").forward(request, response);
			}else {
				request.setAttribute("bookUpdateMessage", "操作失败");
				request.getRequestDispatcher("bookList.bookAction").forward(request, response);
			}
		}
		
		//查询书籍，返回一个列表
		if("/searchBook".equals(path))
		{
			String searchContent=request.getParameter("searchContent");
			//System.out.print("查询书籍被调用,查询的内容为："+searchContent);
			
			BookSearchDao bsd=new BookSearchDao();
			List<BookInfo> bookSearchList=bsd.searchBook(s_no, searchContent);
			
			//将bookList转发到book_search.jsp页面
			request.setAttribute("bookSearchList", bookSearchList);
			
			request.getRequestDispatcher("seller/book_search.jsp").forward(request, response);
			
		}
		
		
		//修改图书的售价
		if("/changeSellPrice".equals(path)) {
			String b_no=request.getParameter("b_no");
			double newSellPrice=Double.parseDouble(request.getParameter("newSellPrice"));
			System.out.println(b_no+" 新售价："+newSellPrice);
			BookUpdateDao bud=new BookUpdateDao();
			if(bud.changeSellPrice(s_no, b_no, newSellPrice)) {
				//如果修改成功，则跳转到当前浏览器页面
				response.sendRedirect("/bookshop/bookList.bookAction");
				
			} else {
				response.getWriter().println("修改失败");
			}
			
			
		}
		
	}
	
}
