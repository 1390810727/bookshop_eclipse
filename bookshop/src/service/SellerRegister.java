package service;

import dao.seller.SellerRegisterDao;
import model.Seller;

public class SellerRegister implements SellerRegisterInterface{

	@Override
	public boolean doRegister(Seller seller) {
		SellerRegisterDao srd=new SellerRegisterDao();
		if(srd.doRegister(seller))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean doCheckId(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doCheckShopName(String shopName) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
