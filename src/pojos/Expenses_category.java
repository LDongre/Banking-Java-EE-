package pojos;

public class Expenses_category {
	int exp_catid;
	String exp_catname;
	String exp_catdetails;
	int userid;
	public Expenses_category() {
		super();
	}
	public Expenses_category(int exp_catid, String exp_catname, String exp_catdetails, int userid) {
		super();
		this.exp_catid = exp_catid;
		this.exp_catname = exp_catname;
		this.exp_catdetails = exp_catdetails;
		this.userid = userid;
	}
	public int getExp_catid() {
		return exp_catid;
	}
	public void setExp_catid(int exp_catid) {
		this.exp_catid = exp_catid;
	}
	public String getExp_catname() {
		return exp_catname;
	}
	public void setExp_catname(String exp_catname) {
		this.exp_catname = exp_catname;
	}
	public String getExp_catdetails() {
		return exp_catdetails;
	}
	public void setExp_catdetails(String exp_catdetails) {
		this.exp_catdetails = exp_catdetails;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
