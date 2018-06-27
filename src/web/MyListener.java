package web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Exception.ClearPayingOrderFormException;
import dao.main.OrderFormDao;

public class MyListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
		System.out.println("session������");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		//session����ʱ����ζ�Ŵ�����Ķ��������ݿ���ɾ��
		//ɾ����ָ��c_id�Ĵ������
		
		String c_id = (String) hse.getSession().getAttribute("c_id");
		OrderFormDao ofd=new OrderFormDao();
		try{
			
			if(ofd.deleteOrderForm("������", c_id) == false) {
				throw new ClearPayingOrderFormException("session����ʱ,�����������ʧ��");
			}
		} catch(ClearPayingOrderFormException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		
	}
	

}
