package com.lyz.code.infinity.domain;

import com.lyz.code.infinity.core.Writeable;

public class DragonHideStatement extends Statement{
	protected boolean dragonHide = false;

	public DragonHideStatement(long serial, String statement,boolean dragonHide){
		super(serial,0,statement);
		this.dragonHide = dragonHide;
	}
	
	public DragonHideStatement(long serial, int indent, String statement,boolean dragonHide){
		super(serial,indent,statement);
		this.dragonHide = dragonHide;
	}
	public DragonHideStatement(){
		this(0,"",false);
	}	

	@Override
	public int compareTo(Writeable o) {
		if (this.getSerial() < o.getSerial()) return -1;
		else if (this.serial == o.getSerial()) return 0;
		else return 1;
	}
	
	@Override
	public String getContentWithSerial() {
		if (dragonHide){
			StringBuilder sb = new StringBuilder();		
			sb.append(this.serial).append("\t\t：");
			for (int i=0 ; i < this.indent; i++) sb.append("\t");
			sb.append(this.statement);
			return sb.toString();
		} else {
			return "";
		}
	}
	
	@Override
	public String getContent(){
		if (dragonHide){
			return super.getContent();
		}else{
			return "";
		}
	}
	
	public boolean isDragonHide() {
		return dragonHide;
	}

	public void setDragonHide(boolean dragonHide) {
		this.dragonHide = dragonHide;
	}

	public Statement getNormalStatement(){
		if (dragonHide){
			return new Statement(this.serial,this.indent,this.statement);
		}else{
			return new Statement(this.serial,this.indent,"");
		}
	}

}
