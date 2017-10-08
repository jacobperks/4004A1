package server.logic.tables;

import java.util.ArrayList;
import java.util.List;


import server.logic.model.User;

public class UserTable {
	List<User> userList=new ArrayList<User>();
    private static class UserListHolder {
        private static final UserTable INSTANCE = new UserTable();
    }
    private UserTable(){
    	//set up the default list with some instances
    	String[] passwordList=new String[]{"Zhibo","Yu","Michelle","Kevin","Sun"};
    	String[] usernameList=new String[]{"Zhibo@carleton.ca","Yu@carleton.ca","Michelle@carleton.ca","Kevin@carleton.ca","Sun@carleton.ca"};
    	for(int i=0;i<usernameList.length;i++){
			User deuser=new User(i,usernameList[i],passwordList[i]);
			userList.add(deuser);
		}
    };
    public static final UserTable getInstance() {
        return UserListHolder.INSTANCE;
    }
    
	
	public List<User> getUserTable() {
		return userList;
	}
	
	public Object createuser(String string, String string2) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<userList.size();i++){
			String email=(userList.get(i)).getUsername();
			if(email.equalsIgnoreCase(string)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			User newuser=new User(userList.size(),string,string2);
			result=userList.add(newuser);
		}else{
			result=false;
		}
		return result;	
	}

	public int lookup(String string) {
		int userid=-1;
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equalsIgnoreCase(string)){
				userid=i;
			}
		}
		return userid;
	}
	
	public boolean lookup(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<userList.size();i++){
			int userid=(userList.get(i)).getUserid();
			if(userid==j){
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
	
	public Object delete(int i) {
		String result="";
		boolean loan=LoanTable.getInstance().checkUser(i);
		int flag=0;
		int index=0;
		for(int j=0;j<userList.size();j++){
			if(userList.get(j).getUserid()==i){
				index=j;
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		
		if(flag==0){
			result="The User Does Not Exist";
		}else{
			boolean fee=FineTable.getInstance().lookup(i);
			if(fee && loan){
				userList.get(index).setUserid(i);
				userList.get(index).setPassword("N/A");
				userList.get(index).setUsername("N/A");
				result="success";
			}else if(fee==false){
				result="Outstanding Fee Exists";
			}else if(loan==false){
				result="Active Loan Exists";
			}
		}
    
		return result;
	}
	
	public int checkUser(String string, String string2) {
		int result=0;
		int flag=0;
		int index=0;
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equalsIgnoreCase(string)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		boolean password=userList.get(index).getPassword().equalsIgnoreCase(string2);
		if(flag!=0 && password){
			result=0;
		}else if(flag==0){
			result=2;
		}else if(password==false){
			result=1;
		}
		return result;
	}
	
	public String toString() {
		String users = "[";
		for (int i = 0; i < userList.size(); i++) {
			if (i != userList.size()-1) {
				users += userList.get(i).getUsername() + ",";
			} else {
				users += userList.get(i).getUsername();
			}
		}
		users += "]";
		return users;
	}
}