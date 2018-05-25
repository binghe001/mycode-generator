package com.lyz.code.infinity.utils;
/**
 * ActualExpenseBatch 
 * 
 * @author Jerry Shen
 * @version v 1.0 Dec. 3rd, 2004
 * ---------------------------------------------------------------------------
 * @History
 */

public class ActualExpenseBatch extends Model {

	private String actualExpenseBatchId;

	private String budgetId;
	
	private String budgetName;

	private String month;

	private String comments;	

	//	 constructors
	public ActualExpenseBatch(){
		super();
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
	 * @return Returns the budgetName.
	 */
	public String getBudgetName() {
		return budgetName;
	}
	/**
	 * @param budgetName The budgetName to set.
	 */
	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}
	/**
	 * @return Returns the comment.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comment The comment to set.
	 */
	public void setComments(String comment) {
		this.comments = comment;
	}
	/**
	 * @return Returns the month.
	 */
	public String getMonth() {
		return month;
	}
	public String getMonthName() {
		if ("00".equals(month))
			return "Jan";
		else if ("01".equals(month))
			return "Feb";
		else if ("02".equals(month))
			return "Mar";
		else if("03".equals(month))
			return "Apr";
		else if ("04".equals(month))
			return "May";
		else if ("05".equals(month))
			return "Jun";
		else if ("06".equals(month))
			return "Jul";
		else if ("07".equals(month))
			return "Aug";
		else if("08".equals(month))
			return "Sep";
		else if ("09".equals(month))
			return "Oct";
		else if ("10".equals(month))
			return "Nov";
		else if ("11".equals(month))
			return "Dec";
		else 
			return "Jan";
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (this == o) {
			return true;
		} else if (o instanceof ActualExpense) {
			ActualExpense that = (ActualExpense) o;
			return that.getActualExpenseId().equals(this.getActualExpenseBatchId());
		} else {
			return false;
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("actualExpenseBatchId:").append(getActualExpenseBatchId()).append("\n")
		.append("budgetId:").append(getBudgetId()).append("\n")
		.append("comment:").append(getComments()).append("\n")
		.append("month:").append(getMonth()).append("\n");
		return sb.toString();
	}
}
