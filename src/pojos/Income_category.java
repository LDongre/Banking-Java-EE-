package pojos;

public class Income_category {
	int inc_catid;
	String inc_catname;
	String inc_catdetails;
	int userid;
	public Income_category() {
		super();
	}
	public Income_category(int inc_catid, String inc_catname, String inc_catdetails, int userid) {
		super();
		this.inc_catid = inc_catid;
		this.inc_catname = inc_catname;
		this.inc_catdetails = inc_catdetails;
		this.userid = userid;
	}
	public int getInc_catid() {
		return inc_catid;
	}
	public void setInc_catid(int inc_catid) {
		this.inc_catid = inc_catid;
	}
	public String getInc_catname() {
		return inc_catname;
	}
	public void setInc_catname(String inc_catname) {
		this.inc_catname = inc_catname;
	}
	public String getInc_catdetails() {
		return inc_catdetails;
	}
	public void setInc_catdetails(String inc_catdetails) {
		this.inc_catdetails = inc_catdetails;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
