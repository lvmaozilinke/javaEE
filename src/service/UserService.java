package service;

import java.util.List;

import pojo.User;

public interface UserService {
	//У���û���¼
//	uname
//	pwd
//	���ز�ѯ�����û���Ϣ
//	
	User checkUserLoginService(String uname,String pwd);
	
	int userChangePwdService(String newPwd, int uid);

	List<User> userShowService();

	int userRegService(User u);


	

}
