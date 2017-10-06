package tests.unit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import server.logic.tables.LoanTable;
import server.logic.model.Loan;

public class LoanTableTest {

	LoanTable loanTable;
	
	@Before
	public void setup() throws Exception {
		loanTable = LoanTable.getInstance();
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

}
