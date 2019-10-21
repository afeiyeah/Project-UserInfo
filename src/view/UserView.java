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
		System.out.println("欢迎登陆主窗体");
		System.out.println(u.getUid()+"您好：		您的权限是：普通用户");
		System.out.println("==================================");
		System.out.println("修改自己的信息------------------------1");
		System.out.println("查询自己的信息------------------------2");
		System.out.println("程序退出----------------------------3");
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
			//修改自己的信息
			userUpdate(u);
			break;
		case 2:
			//查询自己的信息
			userQuery(u);
			break;
		case 3:
			MainView.signOut();
			break;
		default:
			System.out.println("输入的选项不正确，请重新输入");	
			change(u);
			break;
		}

	}
	public void userUpdate(User u){
		boolean status=false;
		//如果server层逻辑判断结果为0，重复当前页面
		while(status==false){
			System.out.println("您现在的信息是：");
			//显示自己的用户信息
			u.showInfo();
			System.out.println("请输入要更改的密码");
			Scanner sc=new Scanner(System.in);
			String pwd=sc.nextLine();
			System.out.println("请输入更改的邮箱");
			String email=sc.nextLine();
			//当server逻辑层判断为真后，才能存储新的密码
			//当server逻辑层判断为真后，才能存储新的邮箱地址
			//注意：不可用短路运算符
			if(uc.updateUserPwd(u.getUid(), pwd)!=0&uc.updateUserMail(u.getUid(), email)!=0){
				status=true;
				//判断语句中已经更新，不用重复更新
//				uc.updateUserPwd(u.getUid(), pwd);
//				uc.updateUserMail(u.getUid(), email);
				System.out.println("修改成功");
			}
			
		}
		User uu=new User();
		uu=uc.query(u.getUid());
		showMenu(uu);
		
	}
	public void userQuery(User u){
		//显示自己的用户信息
		//返回的是User类型
		//uc.query(u.getUid()).showInfo();
		u.showInfo();
		showMenu(u);
	}
	
}
