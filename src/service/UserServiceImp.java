package service;

import java.util.List;

import dao.UserDao;
import dao.UserDaoImp;
import entity.User;
import datatool.DataTool;
public class UserServiceImp implements UserService {
	//����dao�㷽��
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
		System.out.println("�����ע����Ϣ��ʽ����ȷ");
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
			System.out.println("�����ʽ����ȷ�������޷��޸�");
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
			System.out.println("�����ʽ����ȷ�������޷��޸�");
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
			System.out.println("Ȩ�����ݲ���ȷ��Ȩ���޷��޸�");
			return 0;
		}
		
		//return ud.updateUserRight(uid, right);
	}
	
	@Override
	public User login(String uid, String pwd) {
		// TODO Auto-generated method stub
		//ֻ��������ȷʱ�������¼�����������ʽһ����ȷ������Ҫ�ж�
		return ud.login(uid, pwd);
	}

	@Override
	public int deleteUser(String uid) {
		// TODO Auto-generated method stub
		return ud.deleteUser(uid);
	}

}
