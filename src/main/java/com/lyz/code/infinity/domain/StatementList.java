package com.lyz.code.infinity.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.lyz.code.infinity.core.Writeable;

public class StatementList implements Writeable,List<Statement>{
	protected long serial = 0;
	protected List<Statement> statements = new ArrayList<Statement>();

	@Override
	public long getSerial() {
		return serial;
	}
	
	public void setSerial(long serial){
		this.serial = serial;
	}

	@Override
	public String getContent() {
		Collections.sort(this.statements);
		StringBuilder sb = new StringBuilder();
		for (Statement s:this.statements){
			sb.append(s.getContent()).append("\n");
		}
		return sb.toString();
		
	}
	
	@Override
	public String getContentWithSerial() {
		Collections.sort(this.statements);
		StringBuilder sb = new StringBuilder();
		for (Statement s:this.statements){
			sb.append(s.getContentWithSerial()).append("\n");
		}
		return sb.toString();
		
	}

	@Override
	public String getStatement(long pos) {
		for (Statement s : this.statements){
			if (s.serial == pos) return s.statement;
		}
		return "";
	}
	
	public void addStatement(Statement statement){
		this.statements.add(statement);
	}

	@Override
	public int size() {
		return this.statements.size();
	}

	@Override
	public boolean isEmpty() {
		if (this.statements.size() ==0) return true;
		else return false;
	}

	@Override
	public boolean contains(Object o) {
		try {
			Statement s = (Statement) o;
			for (Statement str : this.statements){
				if (str.equals(s)) return true;
			}
			return false;
		} catch (Exception e){
			return false;
		}
	}

	@Override
	public Iterator<Statement> iterator() {
		return this.statements.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.statements.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.statements.toArray(a);
	}

	@Override
	public boolean add(Statement e) {
		return this.statements.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return this.statements.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.statements.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Statement> c) {
		return this.statements.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.statements.retainAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.statements.retainAll(c);
	}

	@Override
	public void clear() {
		this.statements.clear();
		this.serial = 0;		
	}

	@Override
	public int compareTo(Writeable o) {
		if (this.serial < o.getSerial()) return -1;
		else if (this.serial == o.getSerial()) return 0;
		else return 1;
	}
	
	public List<Statement> getStatementList(){
		return this.statements;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Statement> c) {
		return this.statements.addAll(c);
	}

	@Override
	public Statement get(int index) {
		return this.statements.get(index);
	}

	@Override
	public Statement set(int index, Statement element) {
		return this.statements.set(index, element);
	}

	@Override
	public void add(int index, Statement element) {
		this.statements.add(index, element);
		
	}

	@Override
	public Statement remove(int index) {
		return this.statements.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return this.statements.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.statements.lastIndexOf(o);
	}

	@Override
	public ListIterator<Statement> listIterator() {
		return this.statements.listIterator();
	}

	@Override
	public ListIterator<Statement> listIterator(int index) {
		return this.statements.listIterator(index);
	}

	@Override
	public List<Statement> subList(int fromIndex, int toIndex) {
		return this.statements.subList(fromIndex, toIndex);
	}
	
}
