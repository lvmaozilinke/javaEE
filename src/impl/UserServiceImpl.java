package impl;

import java.util.List;

import org.apache.log4j.Logger;

import a.wpf.dao.UserDao;
import a.wpf.dao.UserDaoImpl;

import pojo.User;
import service.UserService;

public class UserServiceImpl implements UserService  {
	//声明Dao层对象 
	UserDao ud=new UserDaoImpl();
	//声明日志对象
	Logger logger=Logger.getLogger(UserServiceImpl.class);
	
	

	public User checkUserLoginService(String uname, String pwd) {
//用户登录
		
		
		//打印日志
		logger.debug(uname+"发起登录请求");
		User u=ud.checkUserLoginDao(uname, pwd);
		if(u!=null){
			logger.debug(uname+"登录成功");
			
			
			
		}else{
			logger.debug(uname+"登录失败");
			
		}
		
		
		return u;
	}


	//修改用户密码
		public int userChangePwdService(String newPwd, int uid) {
			logger.debug(uid+":发起密码请求");
			int index=ud.userChangePwdDao(newPwd,uid);
			if(index>0){
				logger.debug(uid+":密码修改成功");
			}else{
				logger.debug(uid+":密码修改失败");
			}
			return index;
		}


		public List<User> userShowService() {
			List<User> lu=ud.userShowDao();
			logger.debug("显示所有用户信息："+lu);
			return lu;
		}

//用户注册：
		
		public int userRegService(User u) {

			return ud.userRefDao(u);
			
		}

	
}
