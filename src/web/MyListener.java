package web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Exception.ClearPayingOrderFormException;
import dao.main.OrderFormDao;

public class MyListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
		System.out.println("session创建了");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		//session销毁时，意味着待付款的订单从数据库中删掉
		//删除掉指定c_id的待付款订单
		
		String c_id = (String) hse.getSession().getAttribute("c_id");
		OrderFormDao ofd=new OrderFormDao();
		try{
			
			if(ofd.deleteOrderForm("待付款", c_id) == false) {
				throw new ClearPayingOrderFormException("session销毁时,清除待付款项失败");
			}
		} catch(ClearPayingOrderFormException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		
	}
	

}
