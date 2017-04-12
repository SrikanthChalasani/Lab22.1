
public class Bank {
private Account acct;

public Bank(Account acct)
{
	this.acct = acct;
}

public String getAccountHolderName()
{
	return acct.getName();
}
public double getAccountBalance(String currency)
{
	return acct.getBalance(currency);
}
public String closeAccount()
{
	acct.close();
	return "Account has been closed.";
}

public Account getAcct() {
	return acct;
}

public void setAcct(Account acct) {
	this.acct = acct;
}
}
