package a.wpf.dao;

import java.util.List;

import pojo.User;

public interface UserDao {
	//�����û��������ѯ��Ϣ
	
	User checkUserLoginDao(String uname,String pwd);

	int userChangePwdDao(String newPwd, int uid);

	List<User> userShowDao();

	int userRefDao(User u);
	
}
