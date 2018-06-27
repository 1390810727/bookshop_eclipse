package web.seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.seller.OrderFormDao;
import model.OrderFormInfo;
import model.SameShopBook;

public class OrderFormServlet extends HttpServlet{

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
		
		//��õ��̱��
		String s_no=(String)session.getAttribute("s_no");
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		
		if("/orderFormList".equals(path)) {
			String status=request.getParameter("status");
			//��ȡ�˿�idΪc_id�����ж���
			List<SameShopBook> sameShopBookList=null;
			
			//��status����nullʱ������ȫ�������Ĳ�ѯ
			
			OrderFormDao ofd=new OrderFormDao();
			sameShopBookList=ofd.getOrderFormList(s_no, status);
			System.out.println("��ȡ�ĳ���Ϊ��"+sameShopBookList.size());	
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/seller/order_form2.jsp").forward(request, response);
			return;
		}
		
		if("/deliverBook".equals(path)) {
			//������
			String c_id =request.getParameter("c_id");
			String createDate = request.getParameter("createDate");
			
			OrderFormDao ofd=new OrderFormDao();
			if(ofd.deliverBook(createDate, c_id, "������", "���ջ�")) {
				//�ɹ���
				
				response.getWriter().print("�����ɹ�");
			}else {
				System.out.println("����ʧ��.ʱ��Ϊ��"+createDate+"  c_id="+c_id);
				response.getWriter().print("����ʧ��");
			}
			
			
		}
		
		//��ȡ������Ķ�������ת������������ҳ��
		/**if("/orderFormList".equals(path))
		{
			String status=request.getParameter("status");
			System.out.println(status);
			OrderFormDao ofo=new OrderFormDao();
			
			List<OrderFormInfo> orderFormInfoList=ofo.orderFormInfoList(s_no, status);
			request.setAttribute("orderFormInfoList", orderFormInfoList);
			
			//ת������������ҳ��
			request.getRequestDispatcher("seller/order_form.jsp").forward(request, response);
		}
		
		//����������Ϣ
		if("/updateLog".equals(path))
		{
			String o_no=request.getParameter("o_no");
			String newAddress=request.getParameter("newAddress");
			OrderFormDao ofo=new OrderFormDao();
			if(ofo.updateLog(o_no, newAddress))
			{
				pw.println("���³ɹ�");
			}
			else {
				pw.println("����ʧ��");
			}
			
		}
		
		//���¼۸�
		if("/updatePrice".equals(path))
		{
			String o_no=request.getParameter("o_no");
			double price=Double.parseDouble(request.getParameter("price"));
			OrderFormDao ofo=new OrderFormDao();
			if(ofo.updatePrice(o_no, price))
			{
				pw.write("1");
			}else{
				pw.print("0");
			}
		}
		
		*/
		
	}

	
}
