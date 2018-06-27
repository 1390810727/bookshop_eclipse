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
		
		
		//处理商家个人界面的请求地址
		if("/getSellerInfo".equals(path))
		{
			String s_id=(String)session.getAttribute("s_id");
			
			//获取商家个人信息
			SellerInfoDao sid=new SellerInfoDao();
			Seller seller=sid.getSellerInfo(s_id);
			
			//获取商家所拥有的店铺
			ShopListDao sld=new ShopListDao();
			List<Shop> shopList= sld.getShopList(s_id);
			
			
			if(seller==null)
			{
				pw.println("获取商家信息失败");
			}else {
				request.setAttribute("seller", seller);
				request.setAttribute("shopList", shopList);
				//转发到seller_info.jsp界面
				request.getRequestDispatcher("seller/seller_info.jsp").forward(request, response);
			}
		}
		
		//删除指定的店铺
		if("/deleteShop".equals(path))
		{
			//获取要删除的店铺的s_no
			String s_no=request.getParameter("s_no");
			String s_id=(String)session.getAttribute("s_id");
			
			ShopDeleteDao sdd=new ShopDeleteDao();
			boolean deleteOK= sdd.deleteShop(s_no);
			
			
			ShopListDao sld=new ShopListDao();
			List<Shop> shopList= sld.getShopList(s_id);
			
			//如果删除成功，则弹出删除成功的弹框
			if(deleteOK)
			{
				request.setAttribute("deleteMessage", "删除成功");
				request.setAttribute("shopList", shopList);
				request.getRequestDispatcher("seller/seller_info.jsp").forward(request, response);
			}else {
				
				request.setAttribute("deleteMessage", "删除失败");
				request.setAttribute("shopList", shopList);
				request.getRequestDispatcher("seller/seller_info.jsp").forward(request, response);
			}
			
		}
		
		
	}
	
	

}
