package a.wpf.dao;

import java.util.List;

import pojo.User;

public interface UserDao {
	//根据用户名密码查询信息
	
	User checkUserLoginDao(String uname,String pwd);

	int userChangePwdDao(String newPwd, int uid);

	List<User> userShowDao();

	int userRefDao(User u);
	
}
