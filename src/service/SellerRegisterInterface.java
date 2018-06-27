package service;

import model.Seller;

public interface SellerRegisterInterface {
	/**
	 * 商家信息的注测信息的提交业务层
	 * @param seller
	 * @return
	 */
	boolean doRegister(Seller seller);
	/**
	 * 检验id是否可用，即是否已经存在该id
	 * @param id
	 * @return
	 */
	boolean doCheckId(String id);
	/**
	 * 检验店铺名是否可用，即是否已经存在该店铺名；
	 * @param shopName
	 * @return
	 */
	boolean doCheckShopName(String shopName);
}
