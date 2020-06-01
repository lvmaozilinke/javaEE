package service;

import java.util.List;

import pojo.User;

public interface UserService {
	//校验用户登录
//	uname
//	pwd
//	返回查询到的用户信息
//	
	User checkUserLoginService(String uname,String pwd);
	
	int userChangePwdService(String newPwd, int uid);

	List<User> userShowService();

	int userRegService(User u);


	

}
