package view;

import java.util.Scanner;

import controller.UserControllerImp;
import entity.User;

public class AdminView extends View {
	private UserControllerImp uc=new UserControllerImp();
	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("showMenu Invalid");

	}
	public void showMenu(User u){
		System.out.println("��ӭ��½������");
		System.out.println(u.getUid()+"���ã�		����Ȩ���ǣ� ����Ա");
		System.out.println("==================================");
		System.out.println("����û�----------------------------1");
		System.out.println("ɾ���û�----------------------------2");
		System.out.println("�޸��û�----------------------------3");
		System.out.println("��ѯ�û�----------------------------4");
		System.out.println("�����˳�----------------------------5");
		change(u);
	}

	@Override
	public void change(){
		System.out.println("change Invalid");
	}
	public void change(User u) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int select=sc.nextInt();
		
		switch(select){
		case 1:
			//����û�
			addUser(u);
			break;
		case 2:
			//ɾ���û�
			deleteUser(u);
			break;
		case 3:
			//�޸��û�
			updateUser(u);
			break;
		case 4:
			//��ѯ�û�
			querySelect(u);
			break;
		case 5:
			//�����˳�
			MainView.signOut();
			break;
		default:
			System.out.println("�����ѡ���ȷ������������");	
			break;
		}

	}
	public void addUser(User u){
		boolean addStatus=false;
		//ֻ�е��������Ϣȫ���Ϸ���ʱ�򣬲���������û���Ϣ
		while(addStatus==false){
			boolean status=false;
			Scanner sc=new Scanner(System.in);
			String uid=null;
			User user=new User();
			while(status==false){
				System.out.println("�������û���");
				uid=sc.nextLine();
				//�ж��Ƿ��Ѵ��ڸ��û���
				if(uc.query(uid).getUid()!=null){
					System.out.println("���û����Ѵ��ڣ������������û���");
				}else{
					status=true;
				}		
			}
			System.out.println("����������");
			String pwd=sc.nextLine();
			System.out.println("����������");
			String email=sc.nextLine();
			System.out.println("������Ȩ��");
			int right=sc.nextInt();
			user.setUid(uid);
			user.setPwd(pwd);
			user.setMail(email);
			user.setRt(right);
			if(uc.addUser(user)!=0){
				addStatus=true;
				//�ж�������Ѿ���ӳɹ�������Ҫ�ظ����
				//uc.addUser(user);
				System.out.println("����û��ɹ�");
			}
			
		}
		showMenu(u);
		
	}

	public void deleteUser(User u){
		boolean status=false;
		String uid=null;
		while(status==false){
			System.out.println("������Ҫɾ�����û���");
			Scanner sc=new Scanner(System.in);
			String uuid=sc.nextLine();
			if(uc.query(uuid).getUid()==null){
				System.out.println("���û������ڣ�����������");
			}
			else if(uuid.equals(u.getUid())){
				System.out.println("������ɾ���Լ������ݣ�����������");
			}
			else{
				status=true;
				uid=uuid;
			}
				
		}
		uc.deleteUser(uid);
		System.out.println("�û�ɾ���ɹ�");
		showMenu(u);
	}
	
	public void updateUser(User u){
		boolean updateStatus=false;
		//ֻ��update����Ϣȫ���Ϸ���ʱ����ܸ��£�����ѭ����ǰҳ��
		while(updateStatus==false){
			boolean status=false;
			Scanner sc=new Scanner(System.in);
			String uid=null;
			//����Ա�����޸��Լ�����Ϣ
			while(status==false){
				System.out.println("������Ҫ�޸���Ϣ���û���");
				uid=sc.nextLine();
				if(uid.equals(u.getUid())){
					System.out.println("�������޸��Լ�����Ϣ������������");
				}
				else{
					status=true;
				}
			}
			//����Ա�����޸��û�id
			System.out.println("������Ҫ���ĵ�����");
			String pwd=sc.nextLine();
			System.out.println("��������ĵ�����");
			String email=sc.nextLine();
			System.out.println("��������ĵ�Ȩ��");
			int rt=sc.nextInt();
			//ע�⣺�����ö�·�����
			if(uc.updateUserPwd(uid, pwd)!=0&uc.updateUserMail(uid, email)!=0&uc.updateUserRight(uid, rt)!=0){
				updateStatus=true;
				//�ж�������Ѿ����³ɹ�������Ҫ�ظ�����
//				uc.updateUserPwd(uid, pwd);
//				uc.updateUserMail(uid, email);
//				uc.updateUserRight(uid, rt);

				System.out.println("�û��޸ĳɹ�");
			}
			
		}
		User uu=new User();
		uu=uc.query(u.getUid());
		showMenu(uu);
		
	}
	
	public void querySelect(User u){
		boolean status=false;
		//���ѡ����1��2�����´�ӡ��ǰҳ��
		while(status==false){
			System.out.println("��ѯȫ���û�-------------1");
			System.out.println("�����û�����ѯ�û�---------2");
			Scanner sc=new Scanner(System.in);
			int select=sc.nextInt();
			if(select==1||select==2){
				status=true;
				if(select==1){
					queryAllUser(u);
				}else{
					queryOneUser(u);
				}
			}
		}
		
	}
	public void queryAllUser(User u){
		//��ʾȫ���û���Ϣ
		uc.showAllUser();
		showMenu(u);
		
	}
	public void queryOneUser(User u){
		System.out.println("������Ҫ��ѯ���û���");
		Scanner sc=new Scanner(System.in);
		String uid=sc.nextLine();
		//��ѯ�û�����
		User user=uc.query(uid);
		//����û���������ʾ��ʾ���
		if(user.getUid()==null){
			System.out.println("���û�������");
		}
		//��ʾ��ѯ���û���Ϣ
		else{
			user.showInfo();
		}
		showMenu(u);
		
	}
	

}
