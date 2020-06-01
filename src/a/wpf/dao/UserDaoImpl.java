package a.wpf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import pojo.User;

public class UserDaoImpl implements UserDao  {
	//��ѯ�û���Ϣ
public User checkUserLoginDao(String uname, String pwd) {

	//����jdbc����
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	
	User u=null;
	try {
		//��������
		Class.forName("com.mysql.jdbc.Driver");

		
		//��ȡ����
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1111");
		
		//����sq1����
		String sql="select * from t_user where uname=? and pwd=?";
		
		
		//����sq1�������
		ps=conn.prepareStatement(sql);
		
		
		//��ռλ����ֵ
		ps.setString(1, uname);
		ps.setString(2,pwd);
		
		//ִ��sq1
		rs=ps.executeQuery();
		
		//�������
		while(rs.next()){
			//��������ֵ
			u=new User();
			u.setUid(rs.getInt("uid"));
			u.setUname(rs.getString("uname"));
			u.setPwd(rs.getString("sex"));
			u.setAge(rs.getInt("age"));
			u.setBirth(rs.getString("birth"));
			
			
		}
		//�ر���Դ
		
		//���ؽ��
	} catch (Exception e) {
		e.printStackTrace();
		
	}finally{
		//�ر���Դ��
		try {
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
	}
	
	
		return u;
	}

public int userChangePwdDao(String newPwd, int uid) {
	//����jdbc����
			Connection conn=null;
			PreparedStatement ps=null;
			//��������
			int index=-1;
			try {
				//��������
				Class.forName("com.mysql.jdbc.Driver");
				//��ȡ����
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "1111");
				//����SQL����
				String sql="update t_user set pwd=? where uid=?";
				//����SQL�������
				ps=conn.prepareStatement(sql);
				//��ռλ����ֵ
				ps.setString(1, newPwd);
				ps.setInt(2, uid);
				//ִ��
				index=ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{//�ر���Դ
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//���ؽ��
			return index;
		
}

public List<User> userShowDao() {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	//��������
	List<User> lu=null;
	try {
		//��������
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ����
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "1111");
		//����sql����
		String sql="select * from t_user";
		//����sql�������
		ps=conn.prepareStatement(sql);
		//ִ��sql
		rs=ps.executeQuery();
		//�����ϸ�ֵ
		lu=new ArrayList<User>();
		//�������
			while(rs.next()){
				//��������ֵ
				User u=new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
				//������洢��������
				lu.add(u);
			}
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		//�ر���Դ
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//���ؽ��
	return lu;
}

public int userRefDao(User u) {
//�û�ע��
	//����jdbc����
	Connection conn=null;
	PreparedStatement ps=null;
	//��������
	int index=-1;
	try {
		//��������
		Class.forName("com.mysql.jdbc.Driver");
		//��ȡ����
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "1111");
		//����SQL����
		String sql="insert into t_user values(default,?,?,?,?,?)";
		//����SQL�������
		ps=conn.prepareStatement(sql);
		//��ռλ����ֵ
		ps.setString(1,u.getUname());
		ps.setString(2, u.getPwd());
		ps.setString(3, u.getSex());
		ps.setInt(4, u.getAge());
		ps.setString(5, u.getBirth());
		//ִ��
		index=ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}finally{//�ر���Դ
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//���ؽ��
	return index;
}

}
