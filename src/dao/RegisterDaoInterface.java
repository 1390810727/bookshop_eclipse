package dao;

import model.Seller;

public interface RegisterDaoInterface {
	/**
	 * �����������ݿ�
	 * �����
	 * @param seller
	 * @return
	 */
	boolean doRegister(Seller seller);
	/**
	 * �����̼�id�Ƿ��Ѿ����ڣ�ʹ��ajax�����������Բ���ֱ���Ǹ��ַ���
	 * @param id
	 * @return
	 */
	boolean checkId(String id);
	
	/**
	 * ���������shopName�Ƿ��Ѿ�����
	 * ���ڷ���false,�����ڷ���true;
	 * @param shopName
	 * @return
	 */
	boolean checkShopName(String shopName);
		

}
