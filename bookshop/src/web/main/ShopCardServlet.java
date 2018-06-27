package web.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.main.ShopCardDao;
import model.ShopCard;
import model.ShopCardBook;
import net.sf.json.JSONArray;
import web.main.bean.Book;
import web.main.bean.OrderForm;

/**
 * 
 * ��������첽ͨ��
 * @author KUIKUI
 *
 */
public class ShopCardServlet extends HttpServlet {

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
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		
		//��ͼ����빺�ﳵ�ĵ�ַ
		if("/addBook".equals(path)) {
			
				//��session�л�ù˿�id
				String c_id=(String) request.getSession().getAttribute("c_id");
				if(c_id != null && !"".equals(c_id))
				{
					
					//��request���л�õ��̺ź�ͼ����
					String s_no=request.getParameter("s_no");
					String b_no=request.getParameter("b_no");
					int number=Integer.parseInt(request.getParameter("number"));
					
					ShopCard shopCard=new ShopCard();
					shopCard.setB_no(b_no);
					shopCard.setC_id(c_id);
					shopCard.setS_no(s_no);
					shopCard.setNumber(number);
					
					
					ShopCardDao scd=new ShopCardDao();
					
					if(scd.addBook(shopCard)) {
						response.getWriter().print("��ӳɹ�");
					}else {
						response.getWriter().print("���ʧ��");
					}
				}else {
					response.getWriter().print("���ȵ�½");
				}
		}
		
		/**
		 * ���ع��ﳵ�е�ͼ���б������첽��ʽ�������������������Ӧʹ��json�ַ�����
		 * ֻ���� �˿͵�id����Ի�ø��û��Ĺ��ﳵ  ��ͼ���б�
		 */
		if("/loadBook".equals(path)) {
			//��session�л�ù˿͵�id
			String c_id=(String)request.getSession().getAttribute("c_id");
			
			ShopCardDao scd=new ShopCardDao();
			List<ShopCardBook> scbList=new ArrayList<>();
			scbList=scd.loadBook(c_id);
			
			System.out.println(scbList);
			if(scbList != null) //������ͼ���б���ͼ���б�ת��δjson�ַ������͵��ͻ���
			{
				JSONArray jsonArray=JSONArray.fromObject(scbList);
				String jsonStr=jsonArray.toString();
				System.out.println(jsonArray.size()+"   "+jsonStr);
				response.getWriter().print(jsonStr);
			}else {
				response.getWriter().print("����ʧ��");
			}
		}
		
		//���������������number(������request��)
		if("/changeNumber".equals(path)) {
			String s_no=request.getParameter("s_no");
			String b_no=request.getParameter("b_no");
			int number=Integer.parseInt(request.getParameter("number"));
			
			//��session�л�ù˿�id
			String c_id=(String)request.getSession().getAttribute("c_id");
			
			System.out.println(s_no+"  "+b_no+" "+number+" "+c_id);
			ShopCardDao scd=new ShopCardDao();
			boolean isChange=scd.changeNumber(c_id, s_no, b_no, number);
			//����ɹ��򷵻سɹ���ʧ���򷵻�ʧ��
			if(isChange) {
				response.getWriter().print("�ɹ�");
			}else {
				response.getWriter().println("ʧ��");
			}
		}
		
		if("/deleteBook".equals(path)) {
			//��session����ȡ��c_id
			String c_id=(String) request.getSession().getAttribute("c_id");
			String jsonStr=request.getParameter("content");
			System.out.println(jsonStr);
			
			//��json�ַ���ת����json����
			Book[] jsonArr=(Book[]) JSONArray.toArray(JSONArray.fromObject(jsonStr),Book.class);
			
			ShopCardDao scd=new ShopCardDao();
			int n=0;
			for(Book b:jsonArr) {
				if(scd.deleteShopCard(c_id, b.getS_no(), b.getB_no())) {
					n++;
				}
			}
			//��ɾ�����������ظ������
			response.getWriter().print(n);
			
			
		}
		
		
	}
}
