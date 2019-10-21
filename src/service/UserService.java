package service;

import java.util.List;

import entity.User;

public interface UserService {
	//调用dao层方法
	List<User> showAllUser();
	int addUser(User u);
	User query(String uid);
//	int updateUserId(String uid,String uuid);
	int updateUserPwd(String uid,String upwd);
	int updateUserMail(String uid,String mail);
	int updateUserRight(String uid,int right);
	int deleteUser(String uid);
	User login(String uid,String pwd);
}
