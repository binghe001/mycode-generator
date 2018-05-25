package com.lyz.code.infinity.utils;
/**
 * Row
 * 
 * @author Jerry Shen
 * @version v 1.0 Dec. 1st, 2004 
 * ---------------------------------------------------------------------------
 * @History modified by Jerry Shen, Dec. 9th, 2004
 */

public class Row extends Model{
	private String[] fields;

	public static final Row EMPTY_ROW = new Row();
	
	public Row(int length){
		fields = new String[length];
	}
		
	public Row(){
		this(1);
		setField(0,"");
	}
	
	public Row(String[] row){
		this(row.length);
		setFields(row);
	}
	
	public int getFieldsNumber(){
		Row r= getRow();
		if (r.getField(0)==null ||"".equals(r.getField(0)))
			return 0;
		else 
			return fields.length;
	}
	
	public void setFields(String[] fields){
		this.fields = fields;
	}
	
	public String[] getFields(){
		return fields;
	}
	
	public String getField(int pos){
		if (this==Row.EMPTY_ROW ||fields == null || fields.length < pos + 1 || pos < 0)
			return "";
		else 
			return fields[pos];
	}
	
	public void setField(int pos,String value){
		if (this == Row.EMPTY_ROW ||fields == null || fields.length < pos + 1)
			return;
		else 
			fields[pos] = value;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		if (getFieldsNumber()>0){
			for (int i=0; i < getFieldsNumber();i++)
				sb.append(getField(i)).append(", ");
			sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1);
			return sb.toString();
		}
		else 
			return "";
	}
	
	public void consoleShow(){
		System.out.println(toString());	
	}
	
	public Row getRow(){
		if (fields==null)
			return EMPTY_ROW;
		else {
			for (int i= 0;i<fields.length;i++){
				if (fields[i]!= null)
					return this;
			}
			return EMPTY_ROW;				
		}
	}
	
	public boolean equals(Row r){
		Row thisRow = this.getRow();
		Row thatRow = r.getRow();
		if (thisRow.getFieldsNumber()!=thatRow.getFieldsNumber())
			return false;
		else {
			for (int i = 0; i < thisRow.getFieldsNumber();i++){
				if (!thisRow.getField(i).equals(thatRow.getField(i)))
						return false;
			}
			return true;
		}
	}
}

