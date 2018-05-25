package com.lyz.code.infinity.utils;
/**
 * Modeler
 * 
 * @author Jerry Shen
 * @version v 1.0 Dec. 1st, 2004 
 * ---------------------------------------------------------------------------
 * @History
 */
public interface Modeler{
	// Model a model from a row
	public Model doModel(Row r);	
	
	// validate to see if the row can model a model
	public boolean validate(Row r);
	
	// deModel a model to a row
	public Row deModel(Model m);
}
