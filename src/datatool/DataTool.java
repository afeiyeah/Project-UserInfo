package datatool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataTool {
	//��̬��:���Ժ;�̬���ԡ���̬����һ������Ϊ��ľ�̬��Ա
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("���������Ѽ���");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getCon(){
		//2.���������ַ���
		String url="jdbc:mysql://localhost:3306/mysqldb";
		String uid="root";
		String pwd="root";
		Connection con=null;
		//3.�������Ӷ���
		try {
			con = DriverManager.getConnection(url,uid,pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void close(ResultSet rs, PreparedStatement ps,Connection con){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NullPointerException e){
			e.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			finally{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(NullPointerException e){
					e.printStackTrace();
				}
			}
		}
	}
	public static void close(PreparedStatement ps,Connection con){
	
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}
	}
	
//���볤�ȼ�鷽��
	public static boolean checkPassword(String pwd){
		//���볤�ȴ��ڵ���8С�ڵ���12
		boolean flag=pwd.length()>=8&&pwd.length()<=12;
		boolean isDigit=false;
		boolean isLetter=false;
		//������ĸ������
		for(int i=0;i<pwd.length();i++){
			if(Character.isDigit(pwd.charAt(i))){
				isDigit=true;
			}
			if(Character.isLetter(pwd.charAt(i))){
				isLetter=true;
			}
		}
		return flag&&isDigit&&isLetter;
		
	}
	
//�ʼ���ʽ��鷽��
	public static boolean checkMail(String mail){
		boolean flag=false;
		int len=mail.length();
		int len2=mail.replaceAll("@","").length();
		int len3=mail.replaceAll("com","").length();
		
		if(mail.indexOf("@")>0&&mail.indexOf("@")<mail.length()-1){
			if(mail.indexOf("com")>0&&mail.indexOf("com")<mail.length()-1){
				if(len-len2==1&&len-len3==3){
					flag=true;
				}
			}
		}
		return flag;
	}
//Ȩ�޸�ʽ��鷽��
	public static boolean checkRight(int right){
		boolean flag=false;
		if(right==1||right==0){
			flag=true;
		}
		return flag;
	}
	
}
