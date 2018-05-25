package com.lyz.code.infinity.utils;

/**
 * ActualExpense
 * 
 * @author Jerry Shen
 * @version v 1.0 Nov. 29th, 2004
 * ---------------------------------------------------------------------------
 * @History
 */

public class ActualExpense extends Model {

	private String actualExpenseId;

	private String expenseHeadId;

	private String budgetId;

	private String month;

	private double amount;

	private String currencyId;

	private double exchangeRate;

	private String inputTime;

	private String actualExpenseBatchId;	

	//	 constructors
	public ActualExpense(){
		super();
	}	
	

	/**
	 * @return Returns the actualExpenseId.
	 */
	public String getActualExpenseId() {
		return actualExpenseId;
	}
	/**
	 * @param actualExpenseId The actualExpenseId to set.
	 */
	public void setActualExpenseId(String actualExpenseId) {
		this.actualExpenseId = actualExpenseId;
	}
	/**
	 * @return Returns the amount.
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return Returns the budgetId.
	 */
	public String getBudgetId() {
		return budgetId;
	}
	/**
	 * @param budgetId The budgetId to set.
	 */
	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}
	/**
	 * @return Returns the currencyExchangeRate.
	 */
	public double getExchangeRate() {
		return exchangeRate;
	}
	/**
	 * @param exchangeRate The currencyExchangeRate to set.
	 */
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	/**
	 * @return Returns the currencyId.
	 */
	public String getCurrencyId() {
		return currencyId;
	}
	/**
	 * @param currencyId The currencyId to set.
	 */
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	/**
	 * @return Returns the expenseHeadId.
	 */
	public String getExpenseHeadId() {
		return expenseHeadId;
	}
	/**
	 * @param expenseHeadId The expenseHeadId to set.
	 */
	public void setExpenseHeadId(String expenseHeadId) {
		this.expenseHeadId = expenseHeadId;
	}
	/**
	 * @return Returns the inputTime.
	 */
	public String getInputTime() {
		return inputTime;
	}
	/**
	 * @param inputTime The inputTime to set.
	 */
	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}
	/**
	 * @return Returns the month.
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return Returns the actualExpenseBatchId.
	 */
	public String getActualExpenseBatchId() {
		return actualExpenseBatchId;
	}
	/**
	 * @param actualExpenseBatchId The actualExpenseBatchId to set.
	 */
	public void setActualExpenseBatchId(String actualExpenseBatchId) {
		this.actualExpenseBatchId = actualExpenseBatchId;
	}
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this == o) {
			return true;
		} else if (o instanceof ActualExpense) {
			ActualExpense that = (ActualExpense) o;
			return that.getActualExpenseId().equals(this.getActualExpenseId());
		} else {
			return false;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("actualExpenseId:").append(getActualExpenseId()).append("\n")
		.append("amount:").append(getAmount()).append("\n")
		.append("budgetId:").append(getBudgetId()).append("\n")
		.append("exchangeRate:").append(getExchangeRate()).append("\n")
		.append("currencyId:").append(getCurrencyId()).append("\n")
		.append("expenseHeadId:").append(getExpenseHeadId()).append("\n")
		.append("inputTime:").append(getInputTime()).append("\n")
		.append("month:").append(getMonth()).append("\n")
		.append("actualExpenseBatchId").append(getActualExpenseBatchId()).append("\n");
		return sb.toString();
	}
}
