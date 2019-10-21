package view;

import java.util.Scanner;

import controller.UserControllerImp;
import entity.User;

public class MainView extends View {
	private UserControllerImp uc=new UserControllerImp();

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("��ӭʹ��neusoft���û�����ϵͳ");
		System.out.println("=============================");
		System.out.println("�û���¼-----------------------1");
		System.out.println("�û�ע��-----------------------2");
		System.out.println("�˳�����-----------------------3");
		change();
	}

	@Override
	public void change() {
		Scanner sc=new Scanner(System.in);
		int select=sc.nextInt();
		// TODO Auto-generated method stub
		
		switch(select){
		case 1:
			logIn();
			break;
		case 2:
			userRegister();
			break;
		case 3:
			signOut();
			break;
		default:
			System.out.println("�����ѡ���ȷ������������");	
			change();
			break;
		}
		
	}
	public void logIn(){
		boolean status=false;
		User ulogin=new User();
		Scanner sc=new Scanner(System.in);
		String uid=null;
		String pwd=null;
		//login,����User����
		//�ж�user�Ƿ����
		while(status==false){
			System.out.println("�û���¼����");
			System.out.println("===============");
			System.out.println("�����������û���");
			sc=new Scanner(System.in);
			uid=sc.nextLine();
			System.out.println("��������������");
			pwd=sc.nextLine();
			//�жϴ�����û����������Ƿ���ƥ���ֵ������ȷʱ���ɵ�½
			if(uc.query(uid).getUid()==null||!(uc.query(uid).getPwd().equals(pwd))){
				System.out.println("�û��������ڻ����������������������");
				
			}
			else{
				status=true;
			}
		}
		ulogin=uc.login(uid, pwd);
		//����û��ǵ�Ȩ��
		if(ulogin.getRt()==1){
			//�û��ǹ���Ա������AdminView
			AdminView av=new AdminView();
			av.showMenu(ulogin);
		}
		else if(ulogin.getRt()==0){
			//�û�����ͨ�û�������UserView
			UserView uv=new UserView();
			uv.showMenu(ulogin);
		}
		else{
			//���ݿ����
			System.out.println("Invalid!Ȩ�����ݳ���!");
		}
	}
	//��addUser���ɹ�ʱ��Ҫ���µ���userRegister()
	public void userRegister(){
		boolean addStatus=false;
		while(addStatus==false){
			boolean status=false;
			Scanner sc=new Scanner(System.in);
			User u=new User();
			String uid=null;
			String pwd=null;
			String email=null;
			int rt=0;
			while(status==false){
				//��ע�����Ѵ���ʱ����Ҫ��������
				System.out.println("�û�ע�����");
				System.out.println("==============");
				System.out.println("�����������û���");
				uid=sc.nextLine();
				if(uc.query(uid).getUid()!=null){
					//���ݿ������ͬ���û���
					System.out.println("�û����Ѵ��ڣ�����������");
				}
				else{
					status=true;
				}
			}
			System.out.println("��������������");
			pwd=sc.nextLine();
			System.out.println("��������������");
			email=sc.nextLine();
			//�û�ֻ��ע��Ϊ��ͨ�û�
//			System.out.println("�������û���Ȩ��");
//			rt=sc.nextInt();
			u.setUid(uid);
			u.setPwd(pwd);
			u.setMail(email);
			u.setRt(rt);
			//���UserServiceImp���߼��жϽ��Ϊ��ȷ����ע����Ϣ��ӵ����ݿ�
			if(uc.addUser(u)!=0){
				addStatus=true;
				//if������Ѿ���ӣ��������ظ����
				//uc.addUser(u);
				System.out.println("ע��ɹ���");
			}
		}
		showMenu();
	}

	public static void signOut(){
		System.out.println("�û��˳���¼");
		System.exit(0);
	}
	public static void main(String[] args){
		MainView mv=new MainView();
		mv.showMenu();
		
	}

}
