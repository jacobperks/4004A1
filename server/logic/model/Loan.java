package server.logic.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan {
	int userid;
	String isbn;
	String copynumber;
	Date date;
	String renewstate;
	int loanid;
	
	public Loan(int userid,String isbn,String copynumber,Date date,String renewstate, int loanid){
		this.userid=userid;
		this.isbn=isbn;
		this.copynumber=copynumber;
		this.date=date;
		this.renewstate=renewstate;
		this.loanid=loanid;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public String toString(){
		return "["+this.userid+","+this.isbn+","+this.copynumber+","+format1.format(this.date)+","+this.renewstate+","+this.loanid+"]";
	}
	
	public int getUserid() {
		return userid;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public String getCopynumber() {
		return copynumber;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getRenewstate() {
		return renewstate;
	}
	
	public int getLoanid() {
		return loanid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setCopynumber(String copynumber) {
		this.copynumber = copynumber;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setRenewstate(String renewstate) {
		this.renewstate = renewstate;
	}
	
	public void setLoanid(int loanid) {
		this.loanid = loanid;
	}

}