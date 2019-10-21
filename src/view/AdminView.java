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
		System.out.println("欢迎登陆主窗体");
		System.out.println(u.getUid()+"您好：		您的权限是： 管理员");
		System.out.println("==================================");
		System.out.println("添加用户----------------------------1");
		System.out.println("删除用户----------------------------2");
		System.out.println("修改用户----------------------------3");
		System.out.println("查询用户----------------------------4");
		System.out.println("程序退出----------------------------5");
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
			//添加用户
			addUser(u);
			break;
		case 2:
			//删除用户
			deleteUser(u);
			break;
		case 3:
			//修改用户
			updateUser(u);
			break;
		case 4:
			//查询用户
			querySelect(u);
			break;
		case 5:
			//程序退出
			MainView.signOut();
			break;
		default:
			System.out.println("输入的选项不正确，请重新输入");	
			break;
		}

	}
	public void addUser(User u){
		boolean addStatus=false;
		//只有当输入的信息全部合法的时候，才允许添加用户信息
		while(addStatus==false){
			boolean status=false;
			Scanner sc=new Scanner(System.in);
			String uid=null;
			User user=new User();
			while(status==false){
				System.out.println("请输入用户名");
				uid=sc.nextLine();
				//判断是否已存在该用户名
				if(uc.query(uid).getUid()!=null){
					System.out.println("该用户名已存在，请重新输入用户名");
				}else{
					status=true;
				}		
			}
			System.out.println("请输入密码");
			String pwd=sc.nextLine();
			System.out.println("请输入邮箱");
			String email=sc.nextLine();
			System.out.println("请输入权限");
			int right=sc.nextInt();
			user.setUid(uid);
			user.setPwd(pwd);
			user.setMail(email);
			user.setRt(right);
			if(uc.addUser(user)!=0){
				addStatus=true;
				//判断语句中已经添加成功，不需要重复添加
				//uc.addUser(user);
				System.out.println("添加用户成功");
			}
			
		}
		showMenu(u);
		
	}

	public void deleteUser(User u){
		boolean status=false;
		String uid=null;
		while(status==false){
			System.out.println("请输入要删除的用户名");
			Scanner sc=new Scanner(System.in);
			String uuid=sc.nextLine();
			if(uc.query(uuid).getUid()==null){
				System.out.println("该用户不存在，请重新输入");
			}
			else if(uuid.equals(u.getUid())){
				System.out.println("不允许删除自己的数据，请重新输入");
			}
			else{
				status=true;
				uid=uuid;
			}
				
		}
		uc.deleteUser(uid);
		System.out.println("用户删除成功");
		showMenu(u);
	}
	
	public void updateUser(User u){
		boolean updateStatus=false;
		//只有update的信息全部合法的时候才能更新，否新循环当前页面
		while(updateStatus==false){
			boolean status=false;
			Scanner sc=new Scanner(System.in);
			String uid=null;
			//管理员不能修改自己的信息
			while(status==false){
				System.out.println("请输入要修改信息的用户名");
				uid=sc.nextLine();
				if(uid.equals(u.getUid())){
					System.out.println("不允许修改自己的信息，请重新输入");
				}
				else{
					status=true;
				}
			}
			//管理员不能修改用户id
			System.out.println("请输入要更改的密码");
			String pwd=sc.nextLine();
			System.out.println("请输入更改的邮箱");
			String email=sc.nextLine();
			System.out.println("请输入更改的权限");
			int rt=sc.nextInt();
			//注意：不能用短路运算符
			if(uc.updateUserPwd(uid, pwd)!=0&uc.updateUserMail(uid, email)!=0&uc.updateUserRight(uid, rt)!=0){
				updateStatus=true;
				//判断语句中已经更新成功，不需要重复更新
//				uc.updateUserPwd(uid, pwd);
//				uc.updateUserMail(uid, email);
//				uc.updateUserRight(uid, rt);

				System.out.println("用户修改成功");
			}
			
		}
		User uu=new User();
		uu=uc.query(u.getUid());
		showMenu(uu);
		
	}
	
	public void querySelect(User u){
		boolean status=false;
		//如果选择不是1或2，重新打印当前页面
		while(status==false){
			System.out.println("查询全部用户-------------1");
			System.out.println("根据用户名查询用户---------2");
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
		//显示全部用户信息
		uc.showAllUser();
		showMenu(u);
		
	}
	public void queryOneUser(User u){
		System.out.println("请输入要查询的用户名");
		Scanner sc=new Scanner(System.in);
		String uid=sc.nextLine();
		//查询用户过程
		User user=uc.query(uid);
		//如果用户不存在显示提示语句
		if(user.getUid()==null){
			System.out.println("该用户不存在");
		}
		//显示查询的用户信息
		else{
			user.showInfo();
		}
		showMenu(u);
		
	}
	

}
