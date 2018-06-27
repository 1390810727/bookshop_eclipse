package dao;

import model.Seller;

public interface RegisterDaoInterface {
	/**
	 * 将对象存进数据库
	 * 并检查
	 * @param seller
	 * @return
	 */
	boolean doRegister(Seller seller);
	/**
	 * 检验商家id是否已经存在，使用ajax来操作，所以参数直接是个字符串
	 * @param id
	 * @return
	 */
	boolean checkId(String id);
	
	/**
	 * 检验店铺名shopName是否已经存在
	 * 存在返回false,不存在返回true;
	 * @param shopName
	 * @return
	 */
	boolean checkShopName(String shopName);
		

}
