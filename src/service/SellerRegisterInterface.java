package service;

import model.Seller;

public interface SellerRegisterInterface {
	/**
	 * �̼���Ϣ��ע����Ϣ���ύҵ���
	 * @param seller
	 * @return
	 */
	boolean doRegister(Seller seller);
	/**
	 * ����id�Ƿ���ã����Ƿ��Ѿ����ڸ�id
	 * @param id
	 * @return
	 */
	boolean doCheckId(String id);
	/**
	 * ����������Ƿ���ã����Ƿ��Ѿ����ڸõ�������
	 * @param shopName
	 * @return
	 */
	boolean doCheckShopName(String shopName);
}
