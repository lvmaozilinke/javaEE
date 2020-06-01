package impl;

import java.util.List;

import org.apache.log4j.Logger;

import a.wpf.dao.UserDao;
import a.wpf.dao.UserDaoImpl;

import pojo.User;
import service.UserService;

public class UserServiceImpl implements UserService  {
	//����Dao����� 
	UserDao ud=new UserDaoImpl();
	//������־����
	Logger logger=Logger.getLogger(UserServiceImpl.class);
	
	

	public User checkUserLoginService(String uname, String pwd) {
//�û���¼
		
		
		//��ӡ��־
		logger.debug(uname+"�����¼����");
		User u=ud.checkUserLoginDao(uname, pwd);
		if(u!=null){
			logger.debug(uname+"��¼�ɹ�");
			
			
			
		}else{
			logger.debug(uname+"��¼ʧ��");
			
		}
		
		
		return u;
	}


	//�޸��û�����
		public int userChangePwdService(String newPwd, int uid) {
			logger.debug(uid+":������������");
			int index=ud.userChangePwdDao(newPwd,uid);
			if(index>0){
				logger.debug(uid+":�����޸ĳɹ�");
			}else{
				logger.debug(uid+":�����޸�ʧ��");
			}
			return index;
		}


		public List<User> userShowService() {
			List<User> lu=ud.userShowDao();
			logger.debug("��ʾ�����û���Ϣ��"+lu);
			return lu;
		}

//�û�ע�᣺
		
		public int userRegService(User u) {

			return ud.userRefDao(u);
			
		}

	
}
