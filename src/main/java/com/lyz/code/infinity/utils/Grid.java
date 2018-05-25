package com.lyz.code.infinity.utils;
/**
 * Grid
 * 
 * @author Jerry Shen
 * @version v 1.0 Dec. 1st, 2004 
 * ---------------------------------------------------------------------------
 * @History
 */


import java.util.ArrayList;
import java.util.TreeMap;

public class Grid {
	
	private Row[] rows;

	private Modeler rowModeler;

	private int width = 0;	
	
	private TreeMap metaData;
	
	private Row metaRow;
	
	public Grid(){
		super();
		rowModeler = new DefaultModeler();
		metaData = new TreeMap();
		metaRow = null;
	}
	
	public Grid(Modeler m){
		this();
		setRowModeler(m);
	}
	
	/**
	 * @return Returns the rowModeler.
	 */
	public Modeler getRowModeler() {
		return rowModeler;
	}
	/**
	 * @param rowModeler The rowModeler to set.
	 */
	public void setRowModeler(Modeler rowModeler) {
		this.rowModeler = rowModeler;
	}
	/**
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width The width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	// Get the rows count of the grid
	public int getHeight(){
		if (rows==null)
			return 0;
		return rows.length;
	}
	
	// set up the grid table head row
	public void setMetaRow(Row r){
		if (getHeight()==0&& r != null && r.getFieldsNumber() > 0 ){
			metaRow = r;
			setWidth(r.getFieldsNumber());
		}
		else if (getWidth()> 0 && r.getFieldsNumber()==getWidth()){
			metaRow = r;
		}
	}
	
	// get the grid table head row
	public Row getMetaRow(){
		if (metaRow != null)
			return metaRow.getRow();
		else 
			return  Row.EMPTY_ROW;
	}
	
	// get the grid table head by col
	public String getRowHead(int col){
		Row r = getMetaRow();
		if (r!=null && !(r.getField(col)==null ||"".equals(r.getField(col))))
				return r.getField(col);
		else
			return "";
	}
	
	// add a row to the grid, the row will be validated
	public void addRow(Row r){
		if (rowModeler == null||Row.EMPTY_ROW.equals(r))
			return;
		if (!validate(r))
			return;
		if (rows == null) {
			if (getWidth()==0){
				rows = new Row[1];
				rows[0] = r;
				width = r.getFieldsNumber();
				metaRow = new Row(getWidth());
			}
			else if (getWidth() == r.getFieldsNumber()) {
				rows = new Row[1];
				rows[0] = r;
			}else {
				return;
			}
		}
		else if (getWidth() == r.getFieldsNumber()){
			Row[] rs = new Row[getHeight()+1];
			for (int i=0;i<getHeight();i++)
				rs[i] = rows[i];
			rs[getHeight()] = r;
			rows = rs;
		}
		else {
			return;
		}
	}
	
	// remove one row of the grid
	public void removeRow(int pos){
		if (pos < 0 || getHeight() <= pos || rows == null){
			return;
		}
		else if (getHeight() == 1 && pos == 0){
			rows = null;
			return;
		}
		else {
			Row[] rs = new Row[getHeight()-1];
			for (int i=0,j=0; i <getHeight()-1; i++,j++){
				if (j!=pos) {
					rs[i] = rows[j];
				} else {
					i--;
				}
			}
			rows = rs;
		}
	}
	
	// model one row to a Model object
	public Model doModel(int row){
		if (rowModeler == null)
			return null;
		if (row < getHeight() && row >= 0 && (! (rowModeler == null))){
			return rowModeler.doModel(rows[row]);			
		}
		else return null;
	}
	
	// set one field of the grid
	public void setField(int row, int col, String value){
		if (row >=0 && row < getHeight() && col >= 0 && col < getWidth())
			rows[row].setField(col,value);
	}
	
	// get one field of the grid
	public String getField(int row, int col){
		if (row >=0 && row < getHeight() && col >= 0 && col < getWidth() && rows != null)
			return rows[row].getField(col);
		else 
			return null;
	}
	
	// get the row of the grid
	public Row getRow(int row){
		if (row >= 0 && row < getHeight() )
			return rows[row].getRow();
		else 
			return null;
	}
	
	// set one row of the grid, the row will be validated
	public void setRow(int row,Row r){
		if (r.getFieldsNumber()== getWidth() && row >= 0 && row < getHeight() && validate(r))
			rows[row] = r;		
	}
	
	// get the row(use as an colum) of one colum
	public Row getColum(int col){
		if (col >= 0 && col < getWidth()) {
			Row r = new Row(getHeight());
			for (int i=0; i< r.getFieldsNumber();i++)
				r.setField(i,getField(i,col));
			return r;
		}
		else 
			return null;
			
	}	
	
	// set one colum of this grid
	public void setColum(int col,Row colum){
		if (getHeight()==colum.getFieldsNumber()&&col >=0 && col < getWidth()){
			for (int i=0; i < getHeight();i++)
				setField(i,col,colum.getField(i));
		}
	}
	
	// set the meta data carried by the grid
	// if the key is not existed, then create a new key
	public void setMetaData(String key,String value){
		try {
			metaData.put(key, value);
		}catch (Exception e){
		}		
	}
	
	// get the meta data carried with the grid
	// return "" if the meta data is not existed 
	public String getMetaData(String key){
		try {
			String str = (String)metaData.get(key);
			if (str == null || "".equals(str))
				return "";
			else 
				return str;
		}
		catch (Exception e){
			return "";
		}
	}
	
	// generate the model list.
	public ArrayList generateList(){
		ArrayList arr = new ArrayList();
		for (int i=0; i < getHeight();i++) {
			Model m = doModel(i);
			if (m==null)
				return null;
			else 
				arr.add(m);
		}		
		return  arr;		
	}
	
	// print out the grid on the console
	public void consoleShow(){
		for (int i=0; i < getHeight();i++){
			Row r = getRow(i);
			r.consoleShow();
		}
	}
	
	// see wether one row in the grid is validated
	public boolean validate(Row r){
		if (rowModeler == null)
			return false;
		else 
			return rowModeler.validate(r);
	}
	
	// see all the rows in the grid is validated
	public boolean validate(){
		if (rowModeler == null)
			return false;
		else if (getHeight()==0)
			return false;
		else {			
			for (int i=0; i<getHeight();i++){
				if (!validate(rows[i]))
					return false;
			}
			return true;
		}		
	}
	
	// add an model to the grid using the rowmodeler's deModel
	public void addRowByModel(Model m){
		try {
			Row r = rowModeler.deModel(m);
			addRow(r);				
		}catch (Exception e){
		}
	}
	
}
