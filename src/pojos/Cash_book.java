package pojos;

import java.util.Date;

public class Cash_book {
	int acid;
	String account;
	Date tran_date;
	double amount;
	int userid;
	String operation;
	public int getAcid() {
		return acid;
	}
	public void setAcid(int acid) {
		this.acid = acid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Cash_book(int acid, String account, Date tran_date, double amount, int userid, String operation) {
		super();
		this.acid = acid;
		this.account = account;
		this.tran_date = tran_date;
		this.amount = amount;
		this.userid = userid;
		this.operation = operation;
	}
	public Cash_book() {
		super();
	}
	
	
}
