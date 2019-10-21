package view;

import java.util.Scanner;

import controller.UserControllerImp;
import entity.User;

public class MainView extends View {
	private UserControllerImp uc=new UserControllerImp();

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("欢迎使用neusoft的用户管理系统");
		System.out.println("=============================");
		System.out.println("用户登录-----------------------1");
		System.out.println("用户注册-----------------------2");
		System.out.println("退出程序-----------------------3");
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
			System.out.println("输入的选项不正确，请重新输入");	
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
		//login,返回User类型
		//判断user是否存在
		while(status==false){
			System.out.println("用户登录界面");
			System.out.println("===============");
			System.out.println("请输入您的用户名");
			sc=new Scanner(System.in);
			uid=sc.nextLine();
			System.out.println("请输入您的密码");
			pwd=sc.nextLine();
			//判断传入的用户名和密码是否有匹配的值。当正确时，可登陆
			if(uc.query(uid).getUid()==null||!(uc.query(uid).getPwd().equals(pwd))){
				System.out.println("用户名不存在或密码输入错误，请重新输入");
				
			}
			else{
				status=true;
			}
		}
		ulogin=uc.login(uid, pwd);
		//检查用户是的权限
		if(ulogin.getRt()==1){
			//用户是管理员，进入AdminView
			AdminView av=new AdminView();
			av.showMenu(ulogin);
		}
		else if(ulogin.getRt()==0){
			//用户是普通用户，进入UserView
			UserView uv=new UserView();
			uv.showMenu(ulogin);
		}
		else{
			//数据库出错
			System.out.println("Invalid!权限数据出错!");
		}
	}
	//当addUser不成功时需要重新调用userRegister()
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
				//当注册名已存在时，需要重新输入
				System.out.println("用户注册界面");
				System.out.println("==============");
				System.out.println("请输入您的用户名");
				uid=sc.nextLine();
				if(uc.query(uid).getUid()!=null){
					//数据库存在相同的用户名
					System.out.println("用户名已存在，请重新输入");
				}
				else{
					status=true;
				}
			}
			System.out.println("请输入您的密码");
			pwd=sc.nextLine();
			System.out.println("请输入您的邮箱");
			email=sc.nextLine();
			//用户只能注册为普通用户
//			System.out.println("请输入用户的权限");
//			rt=sc.nextInt();
			u.setUid(uid);
			u.setPwd(pwd);
			u.setMail(email);
			u.setRt(rt);
			//如果UserServiceImp中逻辑判断结果为正确，将注册信息添加到数据库
			if(uc.addUser(u)!=0){
				addStatus=true;
				//if语句中已经添加，不能再重复添加
				//uc.addUser(u);
				System.out.println("注册成功！");
			}
		}
		showMenu();
	}

	public static void signOut(){
		System.out.println("用户退出登录");
		System.exit(0);
	}
	public static void main(String[] args){
		MainView mv=new MainView();
		mv.showMenu();
		
	}

}
