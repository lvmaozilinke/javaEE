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
		
		//������������ʽ
		req.setCharacterEncoding("utf-8");
		
		//������Ӧ�����ʽ
		resp.setContentType("text/html;charset=utf-8");
		//��ȡ������
		String oper=req.getParameter("oper");
		
		if("login".equals(oper)){
			//���õ�¼��������
			checkUserLogin(req,resp);
	
		}else if("out".equals(oper)){
			//�˳�����
			userOut(req,resp);
			
		}else if("pwd".equals(oper)){
			//�޸����빦��
			userChangePwd(req,resp);
			
			
		}else if("show".equals(oper)){
			//�鿴�����û���Ϣ
			userShow(req,resp);
			
			
		}else if("reg".equals(oper)){
			//ע�Ṧ��
			userReg(req,resp);
			
			
		}else{
			System.out.println("û���ҵ����Ӧ�Ĳ�����"+oper);
			
			
		}
	}
	
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//		ע�Ṧ��
		//��ȡ������Ϣ
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

		
//����ҵ��㴦��
		int index=us.userRegService(u);
//��Ӧ������
		if(index>0){
			//��ȡsession
			HttpSession hs=req.getSession();
			hs.setAttribute("reg", "true");
			//�ض���
			resp.sendRedirect("/mg/login.jsp");
			
			}
	
}

	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������
		//����service
		List<User> lu=us.userShowService();
		//�ж�
		if(lu!=null){
			//����ѯ���û����ݴ洢��request����
			req.setAttribute("lu",lu);
			//����ת��
			req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
			return;
		}
		
	}

	//�޸����빦��
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String newPwd=req.getParameter("newPwd");
		//��session��ȡ��Ϣ
		User u=(User) req.getSession().getAttribute("user");
		int uid=u.getUid();
		
		int index=us.userChangePwdService(newPwd,uid);
		if(index>0){
			//��ȡsession����
			HttpSession hs=req.getSession();
			hs.setAttribute("pwd","true");
			//�ض��򵽵�¼ҳ��
			resp.sendRedirect("/mg/login.jsp");
		}
		
		
		
		
	}

	private void userOut(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession hs=req.getSession();
		hs.invalidate();//����session
		try {
			resp.sendRedirect("/mg/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	//�����¼
	private void checkUserLogin(
			HttpServletRequest req, 
			HttpServletResponse resp) throws IOException, ServletException {
		
		//��ȡ������Ϣ
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");

		
		
		
		//����������Ϣ
			//��ȡservice�����
			//����
		User u=us.checkUserLoginService(uname, pwd);
		if(u!=null){
			
			//��ȡsession����
			HttpSession hs=req.getSession();
			hs.setAttribute("user", u);
			resp.sendRedirect("/mg/main/main.jsp");

		}else{
			//��ӱ�ʶ����request��
			req.setAttribute("flag",0);
			
			
			req.getRequestDispatcher("login.jsp").forward(req, resp);//	����ת��
			return;
			
			
			
		}
		
		
		//��Ӧ������
			//ֱ����Ӧ
			//����ת��
			//�ض���
		
	}

	
}
	
