package com.lyz.code.infinity.s2sh.core;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.domain.Controller;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.generator.JspTemplate;

public  class OnlyLoginIndexJspTemplate extends JspTemplate{
	protected List<Controller> controllers = new ArrayList<Controller>();

	@Override
	public String generateJspString() {
		StringBuilder sb = new StringBuilder();
		return null;
	}

	public List<Controller> getControllers() {
		return controllers;
	}

	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
	}
	
	public void addController(Controller controller){
		this.controllers.add(controller);
	}
	
	public void addControllers(List<Controller> controllers){
		this.controllers.addAll(controllers);
	}

	@Override
	public StatementList generateStatementList() {
		// TODO Auto-generated method stub
		return null;
	}

}
