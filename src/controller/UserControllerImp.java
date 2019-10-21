package controller;

import java.util.List;

import entity.User;
import service.UserService;
import service.UserServiceImp;

public class UserControllerImp implements UserController {
	private UserService us=new UserServiceImp();
	@Override
	public List<User> showAllUser() {
		// TODO Auto-generated method stub
		return us.showAllUser();
	}

	@Override
	public int addUser(User u) {
		// TODO Auto-generated method stub
		return us.addUser(u);
	}

	@Override
	public User query(String uid) {
		// TODO Auto-generated method stub
		return us.query(uid);
	}


//
//	@Override
//	public int updateUserId(String uid, String uuid) {
//		// TODO Auto-generated method stub
//		return us.updateUserId(uid, uuid);
//	}

	@Override
	public int updateUserPwd(String uid, String upwd) {
		// TODO Auto-generated method stub
		return us.updateUserPwd(uid, upwd);
	}

	@Override
	public int updateUserMail(String uid, String mail) {
		// TODO Auto-generated method stub
		return us.updateUserMail(uid, mail);
	}

	@Override
	public int updateUserRight(String uid, int right) {
		// TODO Auto-generated method stub
		return us.updateUserRight(uid, right);
	}
	

	@Override
	public User login(String uid, String pwd) {
		// TODO Auto-generated method stub
		return us.login(uid, pwd);
	}

	@Override
	public int deleteUser(String uid) {
		// TODO Auto-generated method stub
		return us.deleteUser(uid);
	}

}
