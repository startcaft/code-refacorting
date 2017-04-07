package main;

import com.oodp.templatemethod.account.Account;
import com.oodp.templatemethod.account.CDAccount;
import com.oodp.templatemethod.account.MoneyMarketAccount;

public class Client {
	
	private static Account acct = null;
	
	public static void main(String[] args) {
		{
			acct = new MoneyMarketAccount();
			System.out.println("货币市场账号利息：" + acct.calculateInterest());
			
			acct = new CDAccount();
			System.out.println("定期存款账号利息：" + acct.calculateInterest());
		}
	}
}
