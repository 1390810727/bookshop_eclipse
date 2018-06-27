package web.seller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.seller.SellerInfoDao;
import dao.seller.ShopDeleteDao;
import dao.seller.ShopListDao;
import model.Seller;
import model.Shop;

public class SellerServlet  extends HttpServlet{

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
		
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		
		
		//�����̼Ҹ��˽���������ַ
		if("/getSellerInfo".equals(path))
		{
			String s_id=(String)session.getAttribute("s_id");
			
			//��ȡ�̼Ҹ�����Ϣ
			SellerInfoDao sid=new SellerInfoDao();
			Seller seller=sid.getSellerInfo(s_id);
			
			//��ȡ�̼���ӵ�еĵ���
			ShopListDao sld=new ShopListDao();
			List<Shop> shopList= sld.getShopList(s_id);
			
			
			if(seller==null)
			{
				pw.println("��ȡ�̼���Ϣʧ��");
			}else {
				request.setAttribute("seller", seller);
				request.setAttribute("shopList", shopList);
				//ת����seller_info.jsp����
				request.getRequestDispatcher("seller/seller_info.jsp").forward(request, response);
			}
		}
		
		//ɾ��ָ���ĵ���
		if("/deleteShop".equals(path))
		{
			//��ȡҪɾ���ĵ��̵�s_no
			String s_no=request.getParameter("s_no");
			String s_id=(String)session.getAttribute("s_id");
			
			ShopDeleteDao sdd=new ShopDeleteDao();
			boolean deleteOK= sdd.deleteShop(s_no);
			
			
			ShopListDao sld=new ShopListDao();
			List<Shop> shopList= sld.getShopList(s_id);
			
			//���ɾ���ɹ����򵯳�ɾ���ɹ��ĵ���
			if(deleteOK)
			{
				request.setAttribute("deleteMessage", "ɾ���ɹ�");
				request.setAttribute("shopList", shopList);
				request.getRequestDispatcher("seller/seller_info.jsp").forward(request, response);
			}else {
				
				request.setAttribute("deleteMessage", "ɾ��ʧ��");
				request.setAttribute("shopList", shopList);
				request.getRequestDispatcher("seller/seller_info.jsp").forward(request, response);
			}
			
		}
		
		
	}
	
	

}
