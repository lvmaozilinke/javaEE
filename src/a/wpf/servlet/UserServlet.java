package a.wpf.servlet;

import impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pojo.User;

import service.UserService;

public class UserServlet extends HttpServlet {
	Logger logger=Logger.getLogger(UserServlet.class);
	
	UserService us=new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取操作符
		String oper=req.getParameter("oper");
		
		if("login".equals(oper)){
			//调用登录处理方法：
			checkUserLogin(req,resp);
	
		}else if("out".equals(oper)){
			//退出功能
			userOut(req,resp);
			
		}else if("pwd".equals(oper)){
			//修改密码功能
			userChangePwd(req,resp);
			
			
		}else if("show".equals(oper)){
			//查看所有用户信息
			userShow(req,resp);
			
			
		}else if("reg".equals(oper)){
			//注册功能
			userReg(req,resp);
			
			
		}else{
			System.out.println("没有找到相对应的操作符"+oper);
			
			
		}
	}
	
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//		注册功能
		//获取请求信息
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		String sex=req.getParameter("sex");
		int age=req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
		String birth=req.getParameter("birth");
		String[] bs=null;
		
		if(birth!=""){
			bs=birth.split("/");
			birth=bs[2]+"-"+bs[0]+"-"+bs[1];
		}
		System.out.println(uname+":"+pwd+":"+sex+":"+age+":"+birth);
		User u=new User(0, uname, pwd, sex, age, birth);

		
//调用业务层处理
		int index=us.userRegService(u);
//响应处理结果
		if(index>0){
			//获取session
			HttpSession hs=req.getSession();
			hs.setAttribute("reg", "true");
			//重定向
			resp.sendRedirect("/mg/login.jsp");
			
			}
	
}

	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理请求
		//调用service
		List<User> lu=us.userShowService();
		//判断
		if(lu!=null){
			//将查询的用户数据存储到request对象
			req.setAttribute("lu",lu);
			//请求转发
			req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
			return;
		}
		
	}

	//修改密码功能
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String newPwd=req.getParameter("newPwd");
		//从session获取信息
		User u=(User) req.getSession().getAttribute("user");
		int uid=u.getUid();
		
		int index=us.userChangePwdService(newPwd,uid);
		if(index>0){
			//获取session对象
			HttpSession hs=req.getSession();
			hs.setAttribute("pwd","true");
			//重定向到登录页面
			resp.sendRedirect("/mg/login.jsp");
		}
		
		
		
		
	}

	private void userOut(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession hs=req.getSession();
		hs.invalidate();//销毁session
		try {
			resp.sendRedirect("/mg/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	//处理登录
	private void checkUserLogin(
			HttpServletRequest req, 
			HttpServletResponse resp) throws IOException, ServletException {
		
		//获取请求信息
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");

		
		
		
		//处理请求信息
			//获取service层对象：
			//检验
		User u=us.checkUserLoginService(uname, pwd);
		if(u!=null){
			
			//获取session对象
			HttpSession hs=req.getSession();
			hs.setAttribute("user", u);
			resp.sendRedirect("/mg/main/main.jsp");

		}else{
			//添加标识符到request中
			req.setAttribute("flag",0);
			
			
			req.getRequestDispatcher("login.jsp").forward(req, resp);//	请求转发
			return;
			
			
			
		}
		
		
		//响应处理结果
			//直接响应
			//请求转发
			//重定向
		
	}

	
}
	
