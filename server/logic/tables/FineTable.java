package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import server.logic.model.Fine;
import server.logic.model.Loan;
import utilities.Config;

public class FineTable {
	List<Fine> fineList=new ArrayList<Fine>();
    private static class FineListHolder {
        private static final FineTable INSTANCE = new FineTable();
    }
    private FineTable(){
    	//set up the default list with some instances
    	Fine fine=new Fine(0,5);
    	fineList.add(fine);
    	Initialization();
    };
    public static final FineTable getInstance() {
        return FineListHolder.INSTANCE;
    }
    
	public boolean lookup(int j) {
		boolean result=true;
		int fee = 0;
		boolean user=FineTable.getInstance().checkuser(j);
		if(user){
			for(int i=0;i<fineList.size();i++){
				int userid=(fineList.get(i)).getUserid();
				if(userid==j){
					fee=fee+fineList.get(i).getFee();
				}
			}	
		}else{
			fee=0;
		}
		if(fee!=0){
			result=false;
		}
		return result;
	}
	
	private boolean checkuser(int j) {
		boolean result=true;
		int fee = 0;
		for(int i=0;i<fineList.size();i++){
			int userid=(fineList.get(i)).getUserid();
			if(userid==j){
				fee=fee+1;
			}else{
				fee=fee+0;
			}
		}	
		if(fee==0){
			result=false;
		}
		return result;
	}
    
	public void applyFine(int j, long time) {
		int flag=0;
		int index=0;
		for(int i = 0;i<fineList.size();i++){
			int userid=(fineList.get(i)).getUserid();
			if(userid==j){
				flag=flag+1;
				index=i;
			}
		}
		int a=(int) ((time/(Config.STIMULATED_DAY))-Config.OVERDUE);
		if(flag!=0){
			if(a>=0){
				fineList.get(index).setFee(a+fineList.get(index).getFee());
				fineList.get(index).setUserid(j);
			}else{
				fineList.get(index).setFee(fineList.get(index).getFee());
				fineList.get(index).setUserid(j);
			}
		}else{
			if(a>=0){
				Fine Fine=new Fine(j,a);
				fineList.add(Fine);
			}else{
				Fine Fine=new Fine(j,0);
				fineList.add(Fine);
			}
		}
	}
    
	public void Initialization(){
    	List<Loan> loanList=LoanTable.getInstance().getLoanTable();
    	for(int i=0;i<loanList.size();i++){
    		applyFine(loanList.get(i).getUserid(), new Date().getTime()-loanList.get(i).getDate().getTime());
    	}
	}

	public List<Fine> getFineTable() {
		return fineList;
	}
	
}