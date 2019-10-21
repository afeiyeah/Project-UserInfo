package entity;

public class User {
	private String uid;
	private String pwd;
	private String mail;
	private int rt;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getRt() {
		return rt;
	}
	public void setRt(int rt) {
		this.rt = rt;
	}
	//当用户权限是普通用户是显示
	public void showInfo(){
		System.out.println(getUid()+"	"+getPwd()+"	"+getMail()+"	"+getRt());
	}


}
