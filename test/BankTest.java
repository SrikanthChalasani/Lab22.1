import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.easymock.IMocksControl;

public class BankTest {

	private Bank bank, bank1, bank2;
	private Account acct, acct1, acct2;
	IMocksControl mocks;

	@Before
	public void setUp() throws Exception {
		acct = EasyMock.createMock(Account.class);		
		bank = new Bank(acct);
		
		mocks = EasyMock.createControl();
		acct1 = mocks.createMock(Account.class);
		acct2 = mocks.createMock(Account.class);
		bank1 = new Bank(acct1);
		bank2 = new Bank(acct2);
		
		
	}

	@Test
	public void testGetAccountBalance() {
		EasyMock.expect(acct.getBalance("USD")).andReturn(100.00);
		EasyMock.replay(acct);
		assertEquals(100.0, bank.getAccountBalance("USD"), .001);
		EasyMock.verify(acct);
	}

	@Test
	public void testCloseAccountBalance() {
		acct.close();
		EasyMock.replay(acct);
		assertEquals("Account has been closed.", bank.closeAccount());
		EasyMock.verify(acct);
	}
	@Test
	public void testMultipleAccountBalance() {
		EasyMock.expect(acct1.getBalance("USD")).andReturn(100.00);
		EasyMock.expect(acct2.getBalance("IND")).andReturn(200.00);
		mocks.replay();
		assertEquals(100.0, bank1.getAccountBalance("USD"), .001);
		assertEquals(200.0, bank2.getAccountBalance("IND"), .001);
		mocks.verify();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testThrowException() {
		EasyMock.expect(acct.getBalance("XXX")).andThrow(
				new IllegalArgumentException("Invalid currency Symbol"));
		EasyMock.replay(acct);
		assertEquals(100.0, bank.getAccountBalance("XXX"), .001);
		EasyMock.verify(acct);
	}

	
}
