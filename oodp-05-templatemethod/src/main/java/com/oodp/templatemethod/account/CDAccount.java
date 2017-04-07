package com.oodp.templatemethod.account;

/**
 * 定期存款账号
 */
public class CDAccount extends Account {

	@Override
	protected String doCalculateAccountType() {
		return "Certificate of Deposite";
	}

	@Override
	protected double doCalculateInterestRate() {
		return 0.065D;
	}

}
