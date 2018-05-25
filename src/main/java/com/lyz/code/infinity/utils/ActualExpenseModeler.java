package com.lyz.code.infinity.utils;
/**
 * ActualExpenseModeler
 * 
 * @author Jerry Shen
 * @version v 1.0 Dec. 2nd, 2004
 * ---------------------------------------------------------------------------
 * @History
 */


public class ActualExpenseModeler implements Modeler{
	private Row row;
	
	public Model doModel(Row r){
		if (!validate(r)) {
			return null;
		}
		else {
			try {
				ActualExpense exp = new ActualExpense();
				String[] rValue = r.getFields();
				exp.setActualExpenseId(rValue[0]);
				exp.setExpenseHeadId(rValue[1]);
				exp.setBudgetId(rValue[2]);
				exp.setMonth(rValue[3]);
				exp.setAmount(Double.parseDouble(rValue[4]));
				exp.setCurrencyId(rValue[5]);
				exp.setExchangeRate(Double.parseDouble(rValue[6]));
				exp.setInputTime(rValue[7]);
				exp.setActualExpenseBatchId(rValue[8]);
				return exp;
			} catch (Exception e){
				return null;
			}
		}	
	}
	
	public boolean validate(Row r){
		if (r==null || r.getFieldsNumber()!= 9)
			return false;
		for (int i=0; i < r.getFieldsNumber();i++){
			if (r.getField(i)==null || "".equals(r.getField(i))){
				return false;
			}
		}
		try {
			Long.parseLong(r.getField(0));
			Long.parseLong(r.getField(1));
			Long.parseLong(r.getField(2));
			Float.parseFloat(r.getField(4));
			Float.parseFloat(r.getField(6));
			Long.parseLong(r.getField(8));
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	public Row deModel(Model actualModel){
		if (actualModel == null) {
			return Row.EMPTY_ROW;
		}
		else {
			try {
				ActualExpense actual=(ActualExpense)actualModel;
				Row r = new Row(9);
				r.setField(0,actual.getActualExpenseId());
				r.setField(1,actual.getExpenseHeadId());
				r.setField(2,actual.getBudgetId());
				r.setField(3,actual.getMonth());
				r.setField(4,""+actual.getAmount());
				r.setField(5,actual.getCurrencyId());
				r.setField(6,""+actual.getExchangeRate());
				r.setField(7,actual.getInputTime());
				r.setField(8,actual.getActualExpenseBatchId());
				return r;
			} catch (Exception e){
				return Row.EMPTY_ROW;
			}
		}	
	}
}


