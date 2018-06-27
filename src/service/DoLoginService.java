package service;

import dao.login.DoLoginDao;
import model.User;

public class DoLoginService {
	public String doLoginAdmin(User user)
	{
		DoLoginDao dl=new DoLoginDao();
		return dl.doLoginAdmin(user);
	}
}
