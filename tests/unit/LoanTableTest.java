package tests.unit;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.logic.tables.ItemTable;
import server.logic.tables.LoanTable;
import server.logic.tables.UserTable;
import server.logic.model.Item;
import server.logic.model.Loan;
import server.logic.model.User;

public class LoanTableTest {

	LoanTable loanTable;
	UserTable userTable;
	ItemTable itemTable;
	
	@Before
	public void setup() throws Exception {
		loanTable = LoanTable.getInstance();
		userTable = UserTable.getInstance();
		itemTable = ItemTable.getInstance();
	}
	
	@After
	public void teardown() throws Exception {
		List<Loan> loanList = loanTable.getLoanTable();
		while (loanList.size() > 2) {
			loanList.remove(loanList.size()-1);
		}
		if (loanList.size() == 2) {
			loanList.get(0).setUserid(0);
			loanList.get(0).setIsbn("9781442668584");
			loanList.get(0).setCopynumber("1");
			loanList.get(0).setRenewstate("0");
			loanList.get(0).setLoanid(0);
			
			loanList.get(1).setUserid(4);
			loanList.get(1).setIsbn("9781442616899");
			loanList.get(1).setCopynumber("1");
			loanList.get(1).setRenewstate("0");
			loanList.get(1).setLoanid(1);
		} else if (loanList.size() == 0) {
			Loan loan=new Loan(0,"9781442668584","1",new Date(),"0",0);
	    	loanList.add(loan);
	    	Loan loan2=new Loan(4,"9781442616899","1",new Date(),"0",1);
	    	loanList.add(loan2);
		}
	}

	@Test
	public void getLoanTableTest() {
		List<Loan> loanListActual = loanTable.getLoanTable();
		assertEquals(0,loanListActual.get(0).getUserid());
		assertEquals("9781442668584",loanListActual.get(0).getIsbn());
		assertEquals("1",loanListActual.get(0).getCopynumber());
		assertEquals("0",loanListActual.get(0).getRenewstate());
		assertEquals(0,loanListActual.get(0).getLoanid());
		
		assertEquals(4,loanListActual.get(1).getUserid());
		assertEquals("9781442616899",loanListActual.get(1).getIsbn());
		assertEquals("1",loanListActual.get(1).getCopynumber());
		assertEquals("0",loanListActual.get(1).getRenewstate());
		assertEquals(1,loanListActual.get(1).getLoanid());
	}
	
	@Test
	public void checkLoanTestFail() {
		List<Loan> loanListActual = loanTable.getLoanTable();
		for (int i =0; i <loanListActual.size();i++) {
			String isbn = loanTable.getLoanTable().get(i).getIsbn();
			String copynumber = loanTable.getLoanTable().get(i).getCopynumber();
			assertEquals(false, loanTable.checkLoan(isbn, copynumber));
		}
	}
	
	@Test
	public void checkLoanTestPass() {
		assertEquals(true, loanTable.checkLoan("9781442668584", "2"));
		assertEquals(true, loanTable.checkLoan("9781442616898", "1"));
	}
	
	@Test
	public void checkLoanOneParamTestFail() {
		List<Loan> loanListActual = loanTable.getLoanTable();
		for (int i =0; i <loanListActual.size();i++) {
			String isbn = loanTable.getLoanTable().get(i).getIsbn();
			assertEquals(false, loanTable.checkLoan(isbn));
		}
	}
	
	@Test
	public void checkLoanOneParamTestPass() {
		assertEquals(true, loanTable.checkLoan("9781442616898"));
	}
	
	@Test
	public void checkUserTestPass() {
		List<User> userListActual = userTable.getUserTable();
		for (int i = 0; i < userListActual.size(); i++){
			if (i != 0 && i != 4) {
				assertEquals(true, loanTable.checkUser(i));
			}
		}
		assertEquals(true, loanTable.checkUser(10));
	}
	
	@Test
	public void checkUserTestFail() {
		assertEquals(false, loanTable.checkUser(0));
		assertEquals(false, loanTable.checkUser(4));
	}
	
	@Test
	public void checkLimitPass() {
		List<Loan> loanListActual = loanTable.getLoanTable();
		for (int i = 0; i < loanListActual.size(); i++) {
			assertEquals(true, loanTable.checkLimit(loanListActual.get(i).getUserid()));
		}
	}
	
	@Test
	public void checkLimitFail() {
		List<Loan> loanListActual = loanTable.getLoanTable();
    	Loan loan1=new Loan(0,"9781442668512","1",new Date(),"0",0);
    	loanListActual.add(loan1);
    	Loan loan2=new Loan(0,"9781442616236","1",new Date(),"0",1);
    	loanListActual.add(loan2);
    	Loan loan3=new Loan(0,"9781442616294","1",new Date(),"0",0);
    	loanListActual.add(loan3);

    	assertEquals(false, loanTable.checkLimit(0));
	}
	
	@Test
	public void createLoanPass() {
		assertEquals("success", loanTable.createloan(2, "9781611687910", "1", new Date()));
	}
	
	@Test
	public void createLoanFail() {
		assertEquals("User Invalid", loanTable.createloan(-1, "9781611687910", "1", new Date()));
		assertEquals("ISBN Invalid", loanTable.createloan(2, "9781317594100", "1", new Date()));
		assertEquals("Copynumber Invalid", loanTable.createloan(2, "9781611687910", "2", new Date()));
		assertEquals("Outstanding Fee Exists", loanTable.createloan(0, "9781611687910", "1", new Date()));
		assertEquals("The Item is Not Available", loanTable.createloan(2, "9781442668584", "1", new Date()));
		
		List<Loan> loanListActual = loanTable.getLoanTable();
    	Loan loan1=new Loan(4,"9781442668512","1",new Date(),"0",0);
    	loanListActual.add(loan1);
    	Loan loan2=new Loan(4,"9781442616236","1",new Date(),"0",1);
    	loanListActual.add(loan2);
    	Loan loan3=new Loan(4,"9781442616294","1",new Date(),"0",0);
    	loanListActual.add(loan3);
		assertEquals("The Maximun Number of Items is Reached", loanTable.createloan(4, "9781611687910", "1", new Date()));
	}
	
	@Test
	public void renewalPass() {
		assertEquals("success", loanTable.renewal(4, "9781442616899", "1", new Date()));
	}

	
	@Test
	public void renewalFail() {
		assertEquals("Outstanding Fee Exists", loanTable.renewal(0, "9781611687910", "1", new Date()));
		assertEquals("The loan does not exist", loanTable.renewal(2, "9781317594100", "1", new Date()));
		
		List<Loan> loanListActual = loanTable.getLoanTable();
		loanListActual.get(1).setRenewstate("2");
		assertEquals("Renewed Item More Than Once for the Same Loan", loanTable.renewal(4, "9781442616899", "1", new Date()));
	}
	
	@Test
	public void returnPass() {
		List<Loan> loanListActual = loanTable.getLoanTable();
		for (int i = 0; i < loanListActual.size(); i++) {
			int id = loanListActual.get(i).getUserid();
			String isbn = loanListActual.get(i).getIsbn();
			String copynumber = loanListActual.get(i).getCopynumber();
			assertEquals("success", loanTable.returnItem(id, isbn, copynumber, new Date()));
		}
	}
	
	@Test
	public void returnFail() {
		List<Item> itemListActual = itemTable.getItemTable();
		for (int i = 1; i < itemListActual.size()-1; i++) {
			String isbn = itemListActual.get(i).getISBN();
			String copynumber = itemListActual.get(i).getCopynumber();
			assertEquals("The Loan Does Not Exist", loanTable.returnItem(i, isbn, copynumber, new Date()));
		}
	}
}
