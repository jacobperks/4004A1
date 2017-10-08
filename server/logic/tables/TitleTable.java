package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import server.logic.model.Title;

public class TitleTable {
	List<Title> titleList=new ArrayList<Title>();
    private static class TitleListHolder {
        private static final TitleTable INSTANCE = new TitleTable();
    }
    private TitleTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
    	String[] titlenameList=new String[]{"By the grace of God","Dante's lyric poetry ","Courtesy lost","Writing for justice","The act in context"};
    	for(int i=0;i<ISBNList.length;i++){
    		Title detitle=new Title(ISBNList[i],titlenameList[i]);
    		titleList.add(detitle);
		}
    };
    public static final TitleTable getInstance() {
        return TitleListHolder.INSTANCE;
    }
    
	public boolean createtitle(String string, String string2) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			Title newtitle=new Title(string,string2);
			result=titleList.add(newtitle);
		}else{
			result=false;
		}
		return result;	
	}

	public boolean lookup(String string) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	
	public String delete(String string) {
		String result="";
		int index=0;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			if(titleList.get(i).getISBN().equalsIgnoreCase(string)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
			boolean loan=LoanTable.getInstance().checkLoan(string);
			if(loan){
				ItemTable.getInstance().deleteAll(string);
				titleList.remove(index);
				result="success";
			}else{
				result="Active Loan Exists";
			}
		}else{
			result="The Title Does Not Exist";
		}
		return result;
	}
	
	
	public List<Title> getTitleTable() {
		return titleList;
	}
	
	public String toString() {
		String titles = "[";
		for (int i = 0; i < titleList.size(); i++) {
			if (i != titleList.size()-1) {
				titles += titleList.get(i).getBooktitle() + ",";
			} else {
				titles += titleList.get(i).getBooktitle();
			}
		}
		titles += "]";
		return titles;
	}

}