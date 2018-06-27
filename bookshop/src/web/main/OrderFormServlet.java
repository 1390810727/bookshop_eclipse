package web.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.main.CustomerDao;
import dao.main.OrderFormDao;
import dao.main.ShopCardDao;
import model.Address;
import model.BookInfo;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import web.main.bean.Book;
import web.main.bean.Logistic;
import web.main.bean.OrderForm;
import web.main.bean.OrderFormInfo;
import web.main.bean.SameShopBook;

public class OrderFormServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		if("/submitOrderForm".equals(path)){
			
			//��session����ȡ��c_id
			String c_id=(String) request.getSession().getAttribute("c_id");
			String jsonStr=request.getParameter("content");
			System.out.println("��õ�json:"+jsonStr);
				
			//��json�ַ���ת����json����
			Book[] jsonArr=(Book[]) JSONArray.toArray(JSONArray.fromObject(jsonStr),Book.class);
			OrderForm orderForm=null;
			List<OrderForm> orderFormList=new ArrayList<>();
			Set<String> strSet=new HashSet<>();//��������
			for(Book b:jsonArr) {
				
				orderForm=new OrderForm();
				orderForm.setC_id(c_id);
				orderForm.setB_no(b.getB_no());
				orderForm.setS_no(b.getS_no());
				orderForm.setNumber(b.getNumber());
				orderForm.setPrice(b.getPrice());
				orderForm.setStatus("������");
				Date date=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String now=sdf.format(date);
				orderForm.setCreateDate(now);
				orderForm.setUpdateDate(now);
				
				orderFormList.add(orderForm);
				
				strSet.add(b.getS_no());
				System.out.println(b.getB_no()+"  "+b.getS_no()+"  "+b.getNumber()+"   "+b.getPrice());
			}
			OrderFormDao ofd=new OrderFormDao();
			ofd.createOrderForm(orderFormList);
			
			List<SameShopBook> ssbList=new ArrayList<>();
			List<OrderFormInfo> ofiList=null;
			SameShopBook sameShopBook=null;
			OrderFormInfo orderFormInfo=null;
			BookInfo bookInfo=null;
			//��������һ�������ٸ��������ʾ�ļ���
			Iterator<String> iter=strSet.iterator();
			while(iter.hasNext()) {
				String s_no=iter.next();
				String shopName=ofd.getShopNameByS_no(s_no);
				sameShopBook=new SameShopBook();
			
				ofiList=new ArrayList<>();
				
				int sumNumber=0;
				double sumPrice=0;
				for(OrderForm of:orderFormList) {
					if( of.getS_no()!=null  && of.getS_no().equals(s_no)) {
						sumNumber+=of.getNumber();
						sumPrice+=of.getPrice()*of.getNumber();
						
						orderFormInfo=new OrderFormInfo();
						bookInfo=ofd.getBookInfo(of.getS_no(), of.getB_no());
						orderFormInfo.setBookName(bookInfo.getName());
						orderFormInfo.setAuthor(bookInfo.getAuthor());
						orderFormInfo.setType(bookInfo.getType());
						orderFormInfo.setImgAddress(bookInfo.getImgAddress1());
						orderFormInfo.setOrderForm(of);
						
						ofiList.add(orderFormInfo);
					}
				}
				sameShopBook.setS_no(s_no);
				sameShopBook.setShopName(shopName);
				sameShopBook.setSumNumber(sumNumber);
				sameShopBook.setSumPrice(sumPrice);
				sameShopBook.setOrderFormInfoList(ofiList);
				ssbList.add(sameShopBook);
			}
			
			
			
			//��sameShopBook�浽session���У�Ȼ���ض��򵽴�����ҳ��
				
				request.getSession().setAttribute("sameShopBookList", ssbList);
				response.sendRedirect("/bookshop/main/paying.jsp");
			 return;
		}
		
		
		//�����servlet��ַ
		if("/payOrderForm".equals(path)) {
			
			String c_id=(String)request.getSession().getAttribute("c_id");
			//��session���н����������Ϣȡ��
			
			List<SameShopBook> sameShopBookList=(List<SameShopBook>)request.getSession().getAttribute("sameShopBookList");
			//���request���еĵ��̺�
			String s_no=request.getParameter("s_no");
			SameShopBook sameShopBook=null;
			
			//�����ڸ��������Ϣ��ȡ����sameShopBook����
			for(SameShopBook ssb:sameShopBookList) {
				
				if( ssb.getS_no()!=null && ssb.getS_no().equals(s_no))
				{
					sameShopBook=ssb;
				}
			}
			
			//��ȡ�ù˿͵��ջ���ַ�ļ���
			OrderFormDao ofd=new OrderFormDao();
			
			List<Address> addressList=ofd.getAddressList(c_id);
			
			//��sameShopBook ��addressList�浽request��ת����orderForm.jsp
			request.setAttribute("sameShopBook", sameShopBook);
			request.setAttribute("addressList",addressList);
			request.getRequestDispatcher("main/orderForm.jsp").forward(request, response);
			return;
		}
		
		//�����������servlet��ַ
		if("/payAction".equals(path)) {
			
			//��request�л���������.�˴���ʱ�Ȼ���ջ���ַ�����ʽ���Ҳ�������
			int a_no=Integer.parseInt(request.getParameter("address"));
			String s_no=request.getParameter("s_no");
			String c_id =(String) request.getSession().getAttribute("c_id");
			
			System.out.println("�����ţ�"+a_no+"  ���̺ţ�"+s_no);
			//��session���л�ô�����Ķ���
			List<SameShopBook> sameShopBookList=(List<SameShopBook>)request.getSession().getAttribute("sameShopBookList");
			SameShopBook sameShopBook=null;
			
			//�����ڸ��������Ϣ��ȡ����sameShopBook����
			for(SameShopBook ssb:sameShopBookList) {
				
				if( ssb.getS_no()!=null && ssb.getS_no().equals(s_no))
				{
					sameShopBook=ssb;
				}
			}
			List<OrderFormInfo> orderFormInfoList=sameShopBook.getOrderFormInfoList();
			
			List<OrderForm> orderFormList=new ArrayList<>();
			OrderForm orderForm=null;
			for(OrderFormInfo ofi:orderFormInfoList) {
				
				orderForm=ofi.getOrderForm();
				orderForm.setStatus("������");
				orderForm.setA_no(a_no);
				orderFormList.add(orderForm);
				
			}
			
			OrderFormDao ofd=new OrderFormDao();
			if(orderFormList!=null &&  ofd.createOrderForm(orderFormList)) {
				//����ɹ������ض��򵽳ɹ�����
				ofd.deleteOrderForm("������", c_id);
				response.sendRedirect("/bookshop/main/success.jsp");
			}
			return;
		}
		
		
		if("/allOrderForm".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//��ȡ�˿�idΪc_id�����ж���
			List<SameShopBook> sameShopBookList=null;
			
			//��status����nullʱ������ȫ�������Ĳ�ѯ
			sameShopBookList=getOrderFormList(c_id, null);
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		if("/paying".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//��ȡ�˿�idΪc_id�����ж���
			List<SameShopBook> sameShopBookList=null;
			
			//��status����nullʱ������ȫ�������Ĳ�ѯ
			sameShopBookList=getOrderFormList(c_id, "������");
			
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
	
		
		if("/delivering".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//��ȡ�˿�idΪc_id�����ж���
			List<SameShopBook> sameShopBookList=null;
			
			//��status����nullʱ������ȫ�������Ĳ�ѯ
			sameShopBookList=getOrderFormList(c_id, "������");
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		
		if("/takingDelivery".equals(path)){
			String c_id=(String)request.getSession().getAttribute("c_id");
			//��ȡ�˿�idΪc_id�����ж���
			List<SameShopBook> sameShopBookList=null;
			
			//��status����nullʱ������ȫ�������Ĳ�ѯ
			sameShopBookList=getOrderFormList(c_id, "���ջ�");
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		
		if("/evaluating".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");
			//��ȡ�˿�idΪc_id�����ж���
			List<SameShopBook> sameShopBookList=null;
			
			//��status����nullʱ������ȫ�������Ĳ�ѯ
			sameShopBookList=getOrderFormList(c_id, "������");
				
			request.setAttribute("sameShopBookList", sameShopBookList);	
			request.getRequestDispatcher("/main/allOrderForm.jsp").forward(request, response);
			return;
		}
		
		if("/returnGoods".equals(path)){
			String s_no=request.getParameter("s_no");
			String createDate=request.getParameter("createDate");
			String status=request.getParameter("status");
			String c_id=(String)request.getSession().getAttribute("c_id");
			
			
			//�޸����ݿ⣬��ָ���Ķ���״̬��Ϊ���˻� 
			OrderFormDao ofd=new OrderFormDao();
			if(ofd.returnGoods(createDate, status, s_no,c_id)) {
				request.setAttribute("message", "�˻�����ɹ��������ĵȴ�");
				request.getRequestDispatcher("/main/order_success.jsp").forward(request, response);
			}else {
				
				request.setAttribute("message", "�˻�����ʧ�ܣ������³���");
				request.getRequestDispatcher("/main/order_success.jsp").forward(request, response);
			}
			
			
			return;
		}
		
		if("/showLogistic".equals(path)) {
			String c_id=(String)request.getSession().getAttribute("c_id");			
			
			String createDate=request.getParameter("createDate");
			OrderFormDao ofd=new OrderFormDao();
			List<Logistic> logisticList=ofd.getLogistic(c_id, createDate);
			
			request.setAttribute("logisticList", logisticList);
			request.getRequestDispatcher("/main/logistic.jsp");
			
		}
		
		if("/addAddress".equals(path)) {
			//��request����ȡ����
			String s_no=request.getParameter("s_no");
			String c_id=(String)request.getSession().getAttribute("c_id");
			Address address=new Address();
			
			address.setC_id(c_id);
			address.setName(request.getParameter("name"));
			address.setProvince(request.getParameter("province"));
			address.setCity(request.getParameter("city"));
			address.setCountry(request.getParameter("country"));
			address.setStreet(request.getParameter("street"));
			address.setPost_code(request.getParameter("post_code"));
			address.setTell_no(request.getParameter("tell_no"));
			
			new CustomerDao().addAddress(address);
			response.sendRedirect("/bookshop/payOrderForm.c_orderFormAction?s_no="+s_no);
			
			
		}
		
	}
	
	
	protected  List<SameShopBook> getOrderFormList(String c_id, String status) {
		List<SameShopBook> sameShopBookList=null;
		OrderFormDao ofd=new OrderFormDao();
		sameShopBookList=ofd.getOrderFormList(c_id, status);
		return sameShopBookList;
	}
	
}
