package view;

import java.util.Scanner;

import controller.UserControllerImp;
import entity.User;

public class UserView extends View {
	private UserControllerImp uc=new UserControllerImp();
	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("Invalid");

	}
	public void showMenu(User u){
		System.out.println("��ӭ��½������");
		System.out.println(u.getUid()+"���ã�		����Ȩ���ǣ���ͨ�û�");
		System.out.println("==================================");
		System.out.println("�޸��Լ�����Ϣ------------------------1");
		System.out.println("��ѯ�Լ�����Ϣ------------------------2");
		System.out.println("�����˳�----------------------------3");
		change(u);
	}

	@Override
	public void change(){
		
	}
	public void change(User u) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int select=sc.nextInt();
		
		switch(select){
		case 1:
			//�޸��Լ�����Ϣ
			userUpdate(u);
			break;
		case 2:
			//��ѯ�Լ�����Ϣ
			userQuery(u);
			break;
		case 3:
			MainView.signOut();
			break;
		default:
			System.out.println("�����ѡ���ȷ������������");	
			change(u);
			break;
		}

	}
	public void userUpdate(User u){
		boolean status=false;
		//���server���߼��жϽ��Ϊ0���ظ���ǰҳ��
		while(status==false){
			System.out.println("�����ڵ���Ϣ�ǣ�");
			//��ʾ�Լ����û���Ϣ
			u.showInfo();
			System.out.println("������Ҫ���ĵ�����");
			Scanner sc=new Scanner(System.in);
			String pwd=sc.nextLine();
			System.out.println("��������ĵ�����");
			String email=sc.nextLine();
			//��server�߼����ж�Ϊ��󣬲��ܴ洢�µ�����
			//��server�߼����ж�Ϊ��󣬲��ܴ洢�µ������ַ
			//ע�⣺�����ö�·�����
			if(uc.updateUserPwd(u.getUid(), pwd)!=0&uc.updateUserMail(u.getUid(), email)!=0){
				status=true;
				//�ж�������Ѿ����£������ظ�����
//				uc.updateUserPwd(u.getUid(), pwd);
//				uc.updateUserMail(u.getUid(), email);
				System.out.println("�޸ĳɹ�");
			}
			
		}
		User uu=new User();
		uu=uc.query(u.getUid());
		showMenu(uu);
		
	}
	public void userQuery(User u){
		//��ʾ�Լ����û���Ϣ
		//���ص���User����
		//uc.query(u.getUid()).showInfo();
		u.showInfo();
		showMenu(u);
	}
	
}
