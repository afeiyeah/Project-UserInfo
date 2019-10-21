package service;

import java.util.List;

import dao.UserDao;
import dao.UserDaoImp;
import entity.User;
import datatool.DataTool;
public class UserServiceImp implements UserService {
	//调用dao层方法
	private UserDao ud=new UserDaoImp();
	@Override
	public List<User> showAllUser() {
		// TODO Auto-generated method stub
		return ud.showAllUser();
	}

	@Override
	public int addUser(User u) {
		// TODO Auto-generated method stub
		//return ud.addUser(u);
		if(DataTool.checkPassword(u.getPwd())&&DataTool.checkMail(u.getMail())&&DataTool.checkRight(u.getRt())){
			return ud.addUser(u);
		}
		else{
		System.out.println("输入的注册信息格式不正确");
		return 0;
		}
	}

	@Override
	public User query(String uid) {
		// TODO Auto-generated method stub
		return ud.query(uid);
	}

	
//
//	@Override
//	public int updateUserId(String uid, String uuid) {
//		// TODO Auto-generated method stub
//		return ud.updateUserId(uid, uuid);
//	}

	@Override
	public int updateUserPwd(String uid, String upwd) {
		// TODO Auto-generated method stub
		if(DataTool.checkPassword(upwd)){
			return ud.updateUserPwd(uid, upwd);
		}
		else{
			System.out.println("密码格式不正确，密码无法修改");
			return 0;
		}
		
	}

	@Override
	public int updateUserMail(String uid, String mail) {
		// TODO Auto-generated method stub
		if(DataTool.checkMail(mail)){
			return ud.updateUserMail(uid, mail);
		}
		else{
			System.out.println("邮箱格式不正确，邮箱无法修改");
			return 0;
		}
	}

	@Override
	public int updateUserRight(String uid, int right) {
		// TODO Auto-generated method stub
		if(DataTool.checkRight(right)){
			return ud.updateUserRight(uid, right);
		}
		else{
			System.out.println("权限内容不正确，权限无法修改");
			return 0;
		}
		
		//return ud.updateUserRight(uid, right);
	}
	
	@Override
	public User login(String uid, String pwd) {
		// TODO Auto-generated method stub
		//只有密码正确时才允许登录，所以密码格式一定正确，不需要判断
		return ud.login(uid, pwd);
	}

	@Override
	public int deleteUser(String uid) {
		// TODO Auto-generated method stub
		return ud.deleteUser(uid);
	}

}
