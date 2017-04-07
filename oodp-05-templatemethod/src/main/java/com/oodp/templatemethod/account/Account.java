package com.oodp.templatemethod.account;

public abstract class Account {
	
	protected String accountNumber;
	
	public Account() {
		accountNumber = null;
	}
	
	public Account(String accountNumber){
		this.accountNumber = accountNumber;
	}
	
	/**
	 * 模板方法，计算利息数额
	 */
	public final double calculateInterest(){
		double interestRate = doCalculateInterestRate();
		String accountType = doCalculateAccountType();
		double amount = calculateAmount(accountType,accountNumber);
		
		return amount * interestRate;
	}
	
	/**
	 * 基本方法留给具体子类实现
	 */
	protected abstract String doCalculateAccountType();
	
	/**
	 * 基本方法留给具体子类实现
	 */
	protected abstract double doCalculateInterestRate();

	public final double calculateAmount(String accountType,String accountNumber){
		//模拟从数据库中获取账号中的存款
		return 7243.00D;
	}
}
