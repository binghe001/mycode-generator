package com.lyz.code.infinity.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lyz.code.infinity.dao.MethodDao;
import com.lyz.code.infinity.database.DBConf;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Naming;
import com.lyz.code.infinity.domain.Signature;
import com.lyz.code.infinity.domain.Type;

public class MethodDaoImpl implements MethodDao {

	@Override
	public boolean validateMethodSignature(List<String> signature) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Method generateMethod(Naming naming, String standardName) throws Exception{
		Connection connection = DBConf.initDB();
        String query = "SELECT * FROM methods where naming_id = ? and method_standard_name=?";
        
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setLong(1,naming.getNamingid());
        ps.setString(2, standardName);
        String content = "";
        Method method = new Method();
        ResultSet result = ps.executeQuery();

        while(result.next()) {
            method.setContent(result.getString("method_content"));
        	method.setSerial(result.getInt("serial"));
        	method.setMethodComment(result.getString("method_comment"));
        	method.setMethodToken(result.getString("method_token"));
        	method.setStandardName(result.getString("method_standard_name"));        	
        }
        
        String query0 = "select * from method_signature where method_id = ? order by signature_position;";
        PreparedStatement ps0 = connection.prepareStatement(query0);
        
        ps0.setLong(1,method.getSerial());
        ResultSet result0 = ps0.executeQuery();
        while(result0.next()) {
            Signature sig = new Signature();
            sig.setSignatureId(result0.getLong("method_signature_id"));
            sig.setPosition(result0.getInt("signature_position"));
            sig.setType(new Type(result0.getString("signature_type")));
            sig.setName(result0.getString("signature_name"));
            method.addSignature(sig);
        }
        DBConf.closeDB(connection);
        return method;
	}
	
	@Override
	public Method generateMethod(Naming naming, String standardName, String returnVal, List<Signature> signatures) throws Exception{
		Connection connection = DBConf.initDB();
        String query = "SELECT * FROM methods where naming_id = ? and method_standard_name=? and return_value=?";
        
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setLong(1,naming.getNamingid());
        ps.setString(2, standardName);
        ps.setString(3, returnVal);
        String content = "";
        Method method = new Method();
        ResultSet result = ps.executeQuery();

        while(result.next()) {
            method.setContent(result.getString("method_content"));
        	method.setSerial(result.getInt("serial"));
        	method.setMethodComment(result.getString("method_comment"));
        	method.setMethodToken(result.getString("method_token"));
        	method.setStandardName(result.getString("method_standard_name"));        	

        
	        String query0 = "select * from method_signature where serial = ? order by signature_position;";
	        PreparedStatement ps0 = connection.prepareStatement(query0);

	        ps0.setInt(1,method.getSerial());
	        ResultSet result0 = ps0.executeQuery();
	        List<Signature> sigs = new ArrayList<Signature>();
	        while(result0.next()) {
	        	Signature sig = new Signature();
	            sig.setSignatureId(result0.getLong("method_signature_id"));
	            sig.setPosition(result0.getInt("signature_position"));
	            sig.setType(new Type(result0.getString("signature_type")));
	            sig.setName(result0.getString("signature_name"));
	            sigs.add(sig);
	        }
	        
	        if (assertSignatureEquals(sigs, signatures)){
	        	method.setSignatures(sigs);
	        	DBConf.closeDB(connection);
	        	return method;
	        }
        }        
        DBConf.closeDB(connection);
        return null;
	}
	
	public boolean assertSignatureEquals(List<Signature>sigs, List<Signature> signatures ){
		if (sigs.size() != signatures.size()){
			return false;
		} else {
			for (int i=0; i < sigs.size();i++){
				if (!sigs.get(i).equals(signatures.get(i))){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String generateMethodToString(Naming naming, String standardName) throws Exception{
		Method method = this.generateMethod(naming, standardName);
		String result = "/***" +method.getMethodToken() + "*/\n";
		result += "/**" + method.getMethodComment() + "*/\n";
		result += "public " + method.getStandardName() + "(";
		Iterator it = method.getSignatures().iterator();
		boolean hasParam = false;
        while (it.hasNext()) {	
        	hasParam = true;
	        Signature sig = (Signature)it.next();
	        Type type = sig.getType();
	        String name = sig.getName();
	        result +=  type + " " + name + ",";
        };
        if (hasParam){
        	result = result.substring(0,result.length()-1);
        }
	    result += ") {\n";
		result += method.getContent() + "}\n\n";
		
		return result;
	}

	@Override
	public Method generateMethod(Naming naming, String standardName,Map<String, String> vars) throws Exception{
		Connection connection = DBConf.initDB();
        String query = "SELECT * FROM methods where naming_id = ? and method_standard_name=?";
        
        PreparedStatement ps = connection.prepareStatement(query);
        
        ps.setLong(1,naming.getNamingid());
        ps.setString(2, standardName);
        String content = "";
        Method method = new Method();
        String methodContent = "";
        ResultSet result = ps.executeQuery();

        while(result.next()) {
            methodContent = result.getString("method_content");
        	method.setSerial(result.getInt("serial"));
        	method.setMethodComment(result.getString("method_comment"));
        	method.setMethodToken(result.getString("method_token"));
        	method.setStandardName(result.getString("method_standard_name"));        	
        }
        
        Iterator it = vars.entrySet().iterator();

        while (it.hasNext()) {	
	        Map.Entry pairs = (Map.Entry) it.next();
	        String token = pairs.getKey().toString();
	        String replacement = pairs.getValue().toString();
	        methodContent = methodContent.replaceAll(token, replacement);
        }
        method.setContent(methodContent);   
        
        query = "select * from method_signature where method_id = ? order by signature_position;";
        ps = connection.prepareStatement(query);
        
        ps.setInt(1,method.getSerial());
        result = ps.executeQuery();
        while(result.next()) {
            Signature sig = new Signature();
            sig.setSignatureId(result.getLong("signature_id"));
            sig.setPosition(result.getInt("signature_position"));
            sig.setType(new Type(result.getString("signature_type")));
            sig.setName(result.getString("signature_name"));
            method.addSignature(sig);
        }
        
        DBConf.closeDB(connection);
        return method;
	}
	
	@Override
	public String generateMethodSkeleton(Naming naming, String standardName) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateMethodContent(Naming naming, String standardName) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
}
