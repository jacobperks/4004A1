package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utilities.Config;


import server.logic.model.Loan;

public class LoanTable {
	List<Loan> loanList=new ArrayList<Loan>();
    private static class LoanListHolder {
        private static final LoanTable INSTANCE = new LoanTable();
    }
    private LoanTable(){
    	//set up the default list with some instances
    	Loan loan=new Loan(0,"9781442668584","1",new Date(),"0",0);
    	loanList.add(loan);
    	Loan loan2=new Loan(4,"9781442616899","1",new Date(),"0",1);
    	loanList.add(loan2);
    };
    public static final LoanTable getInstance() {
        return LoanListHolder.INSTANCE;
    }
 
	public List<Loan> getLoanTable() {
		return loanList;
	}

	public boolean checkLimit(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag>=Config.MAX_BORROWED_ITEMS){
			result=false;
		}
		return result;
	}

	public boolean checkLoan(String string, String string2) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			String copynumber=(loanList.get(i)).getCopynumber();
			if(ISBN.equalsIgnoreCase(string) && copynumber.equalsIgnoreCase(string2)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
	
	public boolean checkLoan(String string) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBN=(loanList.get(i)).getIsbn();
			if(ISBN.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
	
	public boolean checkUser(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userid=(loanList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
	
	public Object createloan(int i, String string, String string2, Date date) {
		String result="";
		boolean user=UserTable.getInstance().lookup(i);
		boolean isbn=TitleTable.getInstance().lookup(string);
		boolean copynumber=ItemTable.getInstance().lookup(string,string2);
		boolean oloan=LoanTable.getInstance().checkLoan(string,string2);
		boolean limit=LoanTable.getInstance().checkLimit(i);
		boolean fee=FineTable.getInstance().lookup(i);
		if(user==false){
			result="User Invalid";
		}else if(isbn==false){
			result="ISBN Invalid";
		}else if(copynumber==false){
			result="Copynumber Invalid";
		}else{
			if(oloan){
				if(limit && fee){
				Loan loan=new Loan(i,string,string2,date,"0",LoanTable.getInstance().getLoanTable().size());
				loanList.add(loan);
				result="success";
				}else if(limit==false){
					result="The Maximun Number of Items is Reached";
				}else if(fee==false){
					result="Outstanding Fee Exists";
				}
			}else{
				result="The Item is Not Available";
			}
		}
    	return result;
	}
	
}