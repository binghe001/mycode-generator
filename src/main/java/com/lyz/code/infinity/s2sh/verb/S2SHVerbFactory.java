package com.lyz.code.infinity.s2sh.verb;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.domain.Domain;


public class S2SHVerbFactory {
	public static Verb getInstance(String type){
		switch (type){
		case "listAll": 		return new ListAll();
		case "listActive":		return new ListActive();
		case "delete":			return new Delete();
		case "softDelete":		return new SoftDelete();
		case "update":			return new Update();
		case "updateAll":		return new UpdateAll();
		case "add":				return new Add();
		case "searchByName":	return new SearchByName();
		case "listAllByPage": 	return new ListAllByPage();
		case "findById": 		return new FindById();
		case "findByName":		return new FindByName();
		/*case "listMy":			return new ListMy();		
		case "deleteAll":		return new DeleteAll();		
		case "softDeleteAll":	return new SoftDeleteAll();
		case "addAll":			return new AddAll();		
		case "searchByDescription": return new SearchByDescription();
		case "searchByComment": return new SearchByDescription();
		case "searchByField": 	return new SearchByDescription();*/
		default:			return null;
		}
	}
	
	public static Verb getInstance(String type, Domain domain){
		switch (type){
		case "listAll": 		return new ListAll(domain);
		case "listActive":		return new ListActive(domain);
		case "delete":			return new Delete(domain);
		case "softDelete":		return new SoftDelete(domain);
		case "update":			return new Update(domain); 
		case "updateAll":		return new UpdateAll(domain);
		case "add":				return new Add(domain);
		case "searchByName":	return new SearchByName(domain);
		case "listAllByPage": 	return new ListAllByPage(domain);
		case "findById": 		return new FindById(domain);
		case "findByName":		return new FindByName(domain);		
		case "deleteAll":		return new DeleteAll(domain);		
		case "softDeleteAll":	return new SoftDeleteAll(domain);		
		//case "addAll":			return new AddAll(domain);	
		default:			return null;
		}
	}
	
	public static Verb getInstance(String type, Domain domain, String fieldName){
		switch (type){
		/*case "searchByDescription": return new SearchByDescription(domain,fieldName);
		case "searchByComment": return new SearchByComment(domain,fieldName); 
		case "searchByField": 	return new SearchByField(domain,fieldName);
		case "listMy": 	return new ListMy(domain,fieldName);*/
		default:			return null;
		}
	}
}
