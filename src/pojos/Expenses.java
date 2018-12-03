package pojos;

import java.util.Date;

public class Expenses {
	int exp_id;
	String exp_ac;
	int userid;
	int exp_catid;
	double amount;
	Date tran_date;
	String payby;
	String remark;
	public Expenses() {
		super();
	}
	public Expenses(int exp_id, String exp_ac, int userid, int exp_catid, double amount, Date tran_date, String payby,
			String remark) {
		super();
		this.exp_id = exp_id;
		this.exp_ac = exp_ac;
		this.userid = userid;
		this.exp_catid = exp_catid;
		this.amount = amount;
		this.tran_date = tran_date;
		this.payby = payby;
		this.remark = remark;
	}
	public int getExp_id() {
		return exp_id;
	}
	public void setExp_id(int exp_id) {
		this.exp_id = exp_id;
	}
	public String getExp_ac() {
		return exp_ac;
	}
	public void setExp_ac(String exp_ac) {
		this.exp_ac = exp_ac;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getExp_catid() {
		return exp_catid;
	}
	public void setExp_catid(int exp_catid) {
		this.exp_catid = exp_catid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getPayby() {
		return payby;
	}
	public void setPayby(String payby) {
		this.payby = payby;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
