package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datatool.DataTool;
import entity.User;

public class UserDaoImp implements UserDao {

	@Override
	public List<User> showAllUser() {
		// TODO Auto-generated method stub
		Connection con=DataTool.getCon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<User> users=new ArrayList<User>();
		String sql="select * from userinfo";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				User u=new User();
				String uuid=rs.getString("uid");
				String upwd=rs.getString("pwd");
				String umail=rs.getString("mail");
				int uright=rs.getInt("rt");
				u.setUid(uuid);
				u.setPwd(upwd);
				u.setMail(umail);
				u.setRt(uright);
				u.showInfo();
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataTool.close(rs, ps, con);
		}
		
		return users;
	}

	@Override
	public int addUser(User u) {
		// TODO Auto-generated method stub
		Connection con=DataTool.getCon();
		PreparedStatement ps=null;
		int row=0;
		
		String sql="insert into userinfo values(?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, u.getUid());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getMail());
			ps.setInt(4, u.getRt());
			row=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataTool.close(ps, con);
		}
		return row;
	}

	@Override
	public User query(String uid) {
		// TODO Auto-generated method stub
		Connection con=DataTool.getCon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from userinfo where uid=?";
		User u=new User();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			rs=ps.executeQuery();
			while(rs.next()){
				String uuid=rs.getString("uid");
				String upwd=rs.getString("pwd");
				String umail=rs.getString("mail");
				int uright=rs.getInt("rt");
				
				u.setUid(uuid);
				u.setPwd(upwd);
				u.setMail(umail);
				u.setRt(uright);

			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataTool.close(rs, ps, con);
		}
		return u;
	}
//
//	@Override
//	public int updateUserId(String uid,String uuid) {
//		// TODO Auto-generated method stub
//		Connection con=DataTool.getCon();
//		PreparedStatement ps=null;
//		String sql="update userinfo set uid=? where uid=?";
//		int row=0;
//		try {
//			ps=con.prepareStatement(sql);
//			ps.setString(1, uid);
//			ps.setString(2, uuid);
//			row=ps.executeUpdate();
//			System.out.println("更改了"+row+"行数据");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			DataTool.close(ps, con);
//		}
//		return row;
//	}
//
	

	@Override
	public int updateUserPwd(String uid, String upwd) {
		// TODO Auto-generated method stub
		Connection con=DataTool.getCon();
		PreparedStatement ps=null;
		String sql="update userinfo set pwd=? where uid=?";
		int row=0;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, upwd);
			ps.setString(2, uid);
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataTool.close(ps, con);
		}
		return row;
	}

	@Override
	public int updateUserMail(String uid, String mail) {
		// TODO Auto-generated method stub
		Connection con=DataTool.getCon();
		PreparedStatement ps=null;
		String sql="update userinfo set mail=? where uid=?";
		int row=0;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, mail);
			ps.setString(2, uid);
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataTool.close(ps, con);
		}
		return row;
	}

	@Override
	public int updateUserRight(String uid, int rt) {
		// TODO Auto-generated method stub
		Connection con=DataTool.getCon();
		PreparedStatement ps=null;
		String sql="update userinfo set rt=? where uid=?";
		int row=0;
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, rt);
			ps.setString(2, uid);
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataTool.close(ps, con);
		}
		return row;
	}
	
	@Override
	public User login(String uid, String pwd) {
		return query(uid);
	}

	@Override
	public int deleteUser(String uid) {
		// TODO Auto-generated method stub
		Connection con=DataTool.getCon();
		PreparedStatement ps=null;
		int row=0;
		String sql="delete from userinfo where uid=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataTool.close(ps, con);
		}
		return row;
	}

}
