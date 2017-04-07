package com.oodp.templatemethod.account;

/**
 * 货币市场账号 
 */
public class MoneyMarketAccount extends Account {

	@Override
	protected String doCalculateAccountType() {
		return "Money Market";
	}

	@Override
	protected double doCalculateInterestRate() {
		return  0.045D;
	}

}
