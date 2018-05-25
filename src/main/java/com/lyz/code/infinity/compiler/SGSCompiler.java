package com.lyz.code.infinity.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Field;
import com.lyz.code.infinity.domain.Prism;
import com.lyz.code.infinity.domain.Project;
import com.lyz.code.infinity.domain.ValidateInfo;
import com.lyz.code.infinity.exception.ValidateException;
import com.lyz.code.infinity.generator.DBDefinitionGenerator;
import com.lyz.code.infinity.generator.MysqlDBDefinitionGenerator;
import com.lyz.code.infinity.s2sh.core.Action;
import com.lyz.code.infinity.s2sh.core.ApplicationContextXml;
import com.lyz.code.infinity.s2sh.core.S2SHPrism;
import com.lyz.code.infinity.s2sh.core.Struts2Facade;

public class SGSCompiler {
	protected final static String [] forbiddenwords = {"abstract", "assert","boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum","extends", "final", "finally", "float", "for", "if",

	                                      "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super","switch",

	                                      "synchronized", "this", "throw", "throws","transient", "try", "void", "volatile", "while","byValue", "cast", "false", "future", "generic", "inner", "operator", "outer", "rest", "true", "var","goto","const","null"};
	
	protected static boolean isForbidden(String notion){
		for (String word: forbiddenwords){
			if (word.equalsIgnoreCase(notion)) return true;
		}
		return false;
	}
 
	public SGSCompiler() {}
	public static Project translate(String sgs,boolean ignoreWarning) throws ValidateException, Exception {
		try {
			if (containsRoundAndSquareBrackets(sgs)){
				throw new ValidateException("源码中存在圆括号或方括号。");
			}
			List<String> notions = parseNotions(sgs);
			Project project = notionsToProject(notions);
			ValidateInfo info = basicValidateProject(project);
			project.setSgsSource(sgs);
			if (ignoreWarning|| info.getCompileWarnings().size()==0){
				return project;
			} else {
				ValidateException em = new ValidateException(info);
				throw em;
			}
		} catch (ValidateException e){
			ValidateInfo info = e.getValidateInfo();
			for (String s: info.getCompileErrors()){
				System.out.println(s);
			}
			for (String s: info.getCompileWarnings()){
				System.out.println(s);
			}
			throw e;
		}
	}
	
	public static List<String> parseNotions(String sgs){
		return SGSTokenizer.generateTokens(sgs);
	}
	
	public static Project notionsToProject(List<String> notions) throws ValidateException, Exception {
		Project project;
		boolean headFinish = false;
		Stack<String> projectStack = new Stack<String>();
		DBDefinitionGenerator dbdg = new MysqlDBDefinitionGenerator();
		for (int i=0; i < notions.size();i++) {
			if ("project".equals(notions.get(i))){
				project = new Project();
				if (!isKeyword(notions.get(i+1))){
					project.setStandardName(notions.get(i+1));
					project.setDbName(project.getStandardName());
					project.setTechnicalstack("simplejee");
					dbdg.setDbName(notions.get(i+1));
					if ("{".equals(notions.get(i+2))){
						projectStack.push("{");
						for (int j=i+3; j < notions.size();j++) {

						if ("packagetoken".equals(notions.get(j))){
							if (":".equals(notions.get(j+1))&&!isKeyword(notions.get(j+2))) project.setPackageToken(notions.get(j+2));
							if (";".equals(notions.get(j+3))) j += 4;
						}
						
						if ("dbprefix".equals(notions.get(j))){
							if (":".equals(notions.get(j+1))&&!isKeyword(notions.get(j+2))) project.setDbPrefix(notions.get(j+2));
							if (";".equals(notions.get(j+3))) j += 4;
						}
						
						if ("dbusername".equals(notions.get(j))){
							if (":".equals(notions.get(j+1))&&!isKeyword(notions.get(j+2))) project.setDbUsername(notions.get(j+2));
							if (";".equals(notions.get(j+3))) j += 4;
						}
						
						if ("dbpassword".equals(notions.get(j))){
							if (":".equals(notions.get(j+1))&&!isKeyword(notions.get(j+2))) project.setDbPassword(notions.get(j+2));
							if (";".equals(notions.get(j+3))) j += 4;
						}
						

						if ("dbname".equals(notions.get(j))){
							if (":".equals(notions.get(j+1))&&!isKeyword(notions.get(j+2))) {
								project.setDbName(notions.get(j+2));
								dbdg.setDbName(notions.get(j+2));
							}
							if (";".equals(notions.get(j+3))) j += 4;
						}
						
						
						if ("emptypassword".equals(notions.get(j))){
							project.setEmptypassword(true);
							if (";".equals(notions.get(j+1))) j += 2;
						}
						
						if ("technicalstack".equals(notions.get(j))){
							if (":".equals(notions.get(j+1))&&!isKeyword(notions.get(j+2))) {
								String rawTech = notions.get(j+2);
								String formatedTech = "simplejee";
								if (rawTech == null || rawTech.equalsIgnoreCase("") || rawTech.equalsIgnoreCase("simplejee") || rawTech.equalsIgnoreCase("jsp") ||rawTech.equalsIgnoreCase("clocksimplejee")){
									formatedTech = "simplejee";
								}else if (rawTech.equalsIgnoreCase("s2sh")){
									formatedTech = "s2sh";
								}else if (rawTech.equalsIgnoreCase("s2shc")){
									formatedTech = "s2shc";
								}
								project.setTechnicalstack(formatedTech);
							}
							if (";".equals(notions.get(j+3))) j += 4;
						}

						
						if ("domain".equals(notions.get(j))|| "prism".equals(notions.get(j))){
							headFinish = true;
						}
						if (headFinish){	
							Stack<String> emptyStack = new Stack<>();
							List<String> domainNotions = fixDomainsNotions(notions.subList(j, notions.size()),projectStack);
							System.out.println("=================JerryDebug==================");
							for (String s: domainNotions){
								System.out.println(s);
							}
							List<Domain> domainList = parseDomains(domainNotions,emptyStack,project.getPackageToken());
							
							Project project2 = new Project(project.getStandardName(),project.getPackageToken(),project.getTechnicalstack(),project.getDbUsername(),project.getDbPassword(),project.isEmptypassword(),project.getDbName());
							project2.setDbPrefix(project.getDbPrefix());
							project2.setDbName(project.getDbName());
							project2.setDomains(domainList);
							dbdg.setDomains(domainList);
							project2.addDBDefinitionGenerator(dbdg);
							
							if (locateCallMagic(notions)){
								if (project2.getTechnicalstack().equalsIgnoreCase("simplejee")){									
									return callMagicClockSimpleJEE(project2,domainList,dbdg);
								} else if (project2.getTechnicalstack().equalsIgnoreCase("s2sh")){
									return callMagic(project2,domainList,dbdg);
								} else if (project2.getTechnicalstack().equalsIgnoreCase("s2shc")){
									return callMagicS2SHC(project2,domainList,dbdg);
								}
							}
							if (project2.getPackageToken() == null || "".equals(project2.getPackageToken())) {
								ValidateInfo info = new ValidateInfo();
								info.addCompileError("没有设置包名前缀。");
								throw new ValidateException(info);
							}
							if (project2.getTechnicalstack().equalsIgnoreCase("simplejee")){
								List<Prism> prismList = parsePrisms(notions.subList(j, notions.size()),projectStack,domainList,project.getPackageToken());
								project2.setPrisms(prismList);
								return project2;
							} else if (project2.getTechnicalstack().equalsIgnoreCase("s2sh")){
								List<S2SHPrism> s2shprismList = parseS2SHPrisms(notions.subList(j, notions.size()),projectStack,domainList,project.getPackageToken());
								for (S2SHPrism sp:s2shprismList){
									project2.addPrism(sp);
								}
								return project2;
							}else if (project2.getTechnicalstack().equalsIgnoreCase("s2shc")){
								List<S2SHPrism> s2shcprismList = parseS2SHCPrisms(notions.subList(j, notions.size()),projectStack,domainList,project.getPackageToken());
								for (S2SHPrism sp:s2shcprismList){
									project2.addPrism(sp);
								}
								return project2;
							}
						}
						}
					}			
				}			
			}
		}
		ValidateInfo info = new ValidateInfo();
		info.addCompileError("将片断缀合成项目错误。");
		throw new ValidateException(info);
	}
	
	public static List<Prism> parsePrisms(List<String> notions, Stack<String> projectStack, List<Domain> domainList, String packageToken) throws Exception{
		List<Prism> list = new ArrayList<Prism>();
		Prism prism = new Prism();
		boolean prismStackjOverflow = false;
		Stack<String> prismStack = new Stack<String>();
		boolean started = false;
		int totalPrismCounts = countPrisms(notions);
		
		for (int i=0; i < notions.size();i++) {
			if ("call".equals(notions.get(i))){
				if ("magic".equals(notions.get(i+1))&& ";".equals(notions.get(i+2))){
					return list;
				}
			}
			
			if  (!"prism".equals(notions.get(i))) {
				notions.remove(i);
				i = i - 1;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < notions.size(); i++){

			if ("prism".equals(notions.get(i))){
				prism = new Prism();
				if (!isKeyword(notions.get(i+1))){
					prism.setStandardName(notions.get(i+1));
				}
				if ("{".equals(notions.get(i+2))){
					prismStack.push("{");
					projectStack.push("{");
				}
				i= i +3;
			}
			
			if ("{".equals(notions.get(i))){
				if (!prismStackjOverflow){
					prismStack.push("{");
				}else{
					prismStackjOverflow = false;
				}
				projectStack.push("{");
			}
			
			if ("}".equals(notions.get(i))){
				if (!prismStack.empty()){
					prismStack.pop();
				} else {
					prismStackjOverflow = true;
				}
				projectStack.pop();
			}
					
			if (prismStack.empty()&&list.size() < totalPrismCounts){
				list.add(prism);
			}else if (prismStack.empty()&&list.size()== totalPrismCounts){
				return list;
			}
			
			if ("prismdomain".equals(notions.get(i))){
				if (":".equals(notions.get(i+1))){
					if (!isKeyword(notions.get(i+2))){
						Domain d = findDomainFromListByStandardName(domainList, notions.get(i+2));
						if (d!=null) prism.setDomain(d);
						prism.setPackageToken(packageToken);
						prism.generatePrismFromDomain();
						prism.expandPackageToken();
						i = i+2;
						started = true;
					}
				}
			 }	
		 }				
		ValidateInfo info = new ValidateInfo();
		info.addCompileError("解析棱柱错误。");
		throw new ValidateException(info);
	}
	
	public static List<S2SHPrism> parseS2SHPrisms(List<String> notions, Stack<String> projectStack, List<Domain> domainList, String packageToken) throws Exception{
		List<S2SHPrism> list = new ArrayList<S2SHPrism>();
		S2SHPrism prism = new S2SHPrism();
		boolean prismStackjOverflow = false;
		Stack<String> prismStack = new Stack<String>();
		boolean started = false;
		int totalPrismCounts = countPrisms(notions);
		
		for (int i=0; i < notions.size();i++) {
			if  (!"prism".equals(notions.get(i))) {
				notions.remove(i);
				i = i - 1;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < notions.size(); i++){

			if ("prism".equals(notions.get(i))){
				prism = new S2SHPrism();
				if (!isKeyword(notions.get(i+1))){
					prism.setStandardName(notions.get(i+1));
				}
				if ("{".equals(notions.get(i+2))){
					prismStack.push("{");
					projectStack.push("{");
				}
				i= i +3;
			}
			
			if ("{".equals(notions.get(i))){
				if (!prismStackjOverflow){
					prismStack.push("{");
				}else{
					prismStackjOverflow = false;
				}
				projectStack.push("{");
			}
			
			if ("}".equals(notions.get(i))){
				if (!prismStack.empty()){
					prismStack.pop();
				} else {
					prismStackjOverflow = true;
				}
				projectStack.pop();
			}
					
			if (prismStack.empty()&&list.size() < totalPrismCounts){
				list.add(prism);
			}else if (prismStack.empty()&&list.size()== totalPrismCounts){
				return list;
			}
			
			if ("prismdomain".equals(notions.get(i))){
				if (":".equals(notions.get(i+1))){
					if (!isKeyword(notions.get(i+2))){
						Domain d = findDomainFromListByStandardName(domainList, notions.get(i+2));
						if (d!=null) prism.setDomain(d);
						prism.setPackageToken(packageToken);
						prism.generatePrismFromDomain();
						prism.expandPackageToken();
						i = i+2;
						started = true;
					}
				}
			 }	
		 }				
		ValidateInfo info = new ValidateInfo();
		info.addCompileError("解析S2SH棱柱错误。");
		throw new ValidateException(info);
	}
	
	public static List<S2SHPrism> parseS2SHCPrisms(List<String> notions, Stack<String> projectStack, List<Domain> domainList, String packageToken) throws Exception{
		List<S2SHPrism> list = new ArrayList<S2SHPrism>();
		S2SHPrism prism = new S2SHPrism();
		boolean prismStackjOverflow = false;
		Stack<String> prismStack = new Stack<String>();
		boolean started = false;
		int totalPrismCounts = countPrisms(notions);
		
		for (int i=0; i < notions.size();i++) {
			if  (!"prism".equals(notions.get(i))) {
				notions.remove(i);
				i = i - 1;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < notions.size(); i++){

			if ("prism".equals(notions.get(i))){
				prism = new S2SHPrism();
				if (!isKeyword(notions.get(i+1))){
					prism.setStandardName(notions.get(i+1));
				}
				if ("{".equals(notions.get(i+2))){
					prismStack.push("{");
					projectStack.push("{");
				}
				i= i +3;
			}
			
			if ("{".equals(notions.get(i))){
				if (!prismStackjOverflow){
					prismStack.push("{");
				}else{
					prismStackjOverflow = false;
				}
				projectStack.push("{");
			}
			
			if ("}".equals(notions.get(i))){
				if (!prismStack.empty()){
					prismStack.pop();
				} else {
					prismStackjOverflow = true;
				}
				projectStack.pop();
			}
					
			if (prismStack.empty()&&list.size() < totalPrismCounts){
				list.add(prism);
			}else if (prismStack.empty()&&list.size()== totalPrismCounts){
				return list;
			}
			
			if ("prismdomain".equals(notions.get(i))){
				if (":".equals(notions.get(i+1))){
					if (!isKeyword(notions.get(i+2))){
						Domain d = findDomainFromListByStandardName(domainList, notions.get(i+2));
						if (d!=null) prism.setDomain(d);
						prism.setPackageToken(packageToken);
						prism.generateS2SHCPrismFromDomain();
						prism.expandPackageToken();
						i = i+2;
						started = true;
					}
				}
			 }	
		 }				
		ValidateInfo info = new ValidateInfo();
		info.addCompileError("解析S2SHC棱柱错误。");
		throw new ValidateException(info);
	}
	
	public static  Domain findDomainFromListByStandardName(List<Domain> domainList, String standardName) throws ValidateException{
		for (Domain d:domainList){
			if (d.getStandardName().equals(standardName)) return d;
		}
		ValidateInfo info = new ValidateInfo();
		info.addCompileError("在域对象列表找不到域对象"+standardName+"。");
		throw new ValidateException(info);
	}
	
	public static List<Domain> parseDomains(List<String> notions, Stack<String> projectStack, String packageToken) throws ValidateException{
		List<Domain> list = new ArrayList<Domain>();
		Domain domain = new Domain();
		boolean domainStackjOverflow = false;
		Stack<String> domainStack = new Stack<String>();
		int totalDomainCounts = countDomains(notions);		
		
		for (int i = 0; i < notions.size(); i++){
			if ("call".equals(notions.get(i))){
				if ("magic".equals(notions.get(i+1))&& ";".equals(notions.get(i+2))){
					return list;
				}
			}	
			
			if ("domain".equals(notions.get(i))){
				domain = new Domain();
				domain.setPackageToken(packageToken);
				if (isForbidden(notions.get(i+1))){
					ValidateInfo info = new ValidateInfo();
					info.addCompileError("使用了被禁止的单词:"+notions.get(i+1));
					throw new ValidateException(info);
				}
				if (!isKeyword(notions.get(i+1))){
					domain.setStandardName(notions.get(i+1));					
				}
				if ("{".equals(notions.get(i+2))){
					domainStack.push("{");
					projectStack.push("{");
				}
				i= i +3;
				//continue;
			}
			
			if ("{".equals(notions.get(i))){
				if (!domainStackjOverflow){
					domainStack.push("{");
				}else{
					domainStackjOverflow = false;
				}
				projectStack.push("{");
				//continue;
			}
			
			if ("}".equals(notions.get(i))){
				if (!domainStack.empty()){
					domainStack.pop();
				} else {
					domainStackjOverflow = true;
				}
				projectStack.pop();
				//continue;
				if (domainStack.empty() && domainStackjOverflow==false && list.size() < totalDomainCounts){
					list.add(domain);
				}
				if (domainStack.empty()&&list.size()== totalDomainCounts){					
					return list;
				}				
			}			
			
			if ("domainid".equals(notions.get(i))){
				if (notions.get(i+1).equals(":")&& !isKeyword(notions.get(i+2)) && !isKeyword(notions.get(i+3))){
					if (isForbidden(notions.get(i+2))){
						ValidateInfo info = new ValidateInfo();
						info.addCompileError("使用了被禁止的单词:"+notions.get(i+2));
						throw new ValidateException(info);
					}
					Field f = new Field();
					f.setFieldName(notions.get(i+2));
					if (notions.get(i+3).equalsIgnoreCase("long")){
						f.setFieldType("long");	
					} else if (notions.get(i+3).equals("int")|| notions.get(i+3).equals("Integer")){
						f.setFieldType("int");	
					}else {
						ValidateInfo info = new ValidateInfo();
						info.addCompileError("域对象"+domain.getCapFirstDomainName()+"的序号必须是long或者int");
						throw new ValidateException(info);
					}
					domain.setDomainId(f);
					i += 4;
					continue;
				}
		 }	
			
			if ("domainname".equals(notions.get(i))){
				if (notions.get(i+1).equals(":")&& !isKeyword(notions.get(i+2)) && !isKeyword(notions.get(i+3))){
					if (isForbidden(notions.get(i+2))){
						ValidateInfo info = new ValidateInfo();
						info.addCompileError("使用了被禁止的单词:"+notions.get(i+2));
						throw new ValidateException(info);
					}
					Field f = new Field();
					f.setFieldName(notions.get(i+2));
					f.setFieldType("String");					
					domain.setDomainName(f);
					i += 3;
					continue;
				}
		 }
		
			if ("activefield".equals(notions.get(i))){
				if (notions.get(i+1).equals(":")&& !isKeyword(notions.get(i+2)) && !isKeyword(notions.get(i+3))){
					if (isForbidden(notions.get(i+2))){
						ValidateInfo info = new ValidateInfo();
						info.addCompileError("使用了被禁止的单词:"+notions.get(i+2));
						throw new ValidateException(info);
					}
					Field f = new Field();
					f.setFieldName(notions.get(i+2));
					f.setFieldType("boolean");					
					domain.setActive(f);
					i += 3;
					continue;
				}
		 }
			
			if ("plural".equals(notions.get(i))){
				if (notions.get(i+1).equals(":")&& !isKeyword(notions.get(i+2)) && !isKeyword(notions.get(i+3))){
					if (isForbidden(notions.get(i+2))){
						ValidateInfo info = new ValidateInfo();
						info.addCompileError("使用了被禁止的单词:"+notions.get(i+2));
						throw new ValidateException(info);
					}
					String plural = notions.get(i+2);
					domain.setPlural(plural);
					i += 3;
					continue;
				}
			}
			
			if ("field".equals(notions.get(i))){
				if (notions.get(i+1).equals(":")&& !isKeyword(notions.get(i+2)) && !isKeyword(notions.get(i+3)) && ";".equals(notions.get(i+4))){
					if (isForbidden(notions.get(i+2))){
						ValidateInfo info = new ValidateInfo();
						info.addCompileError("Uses forbidden word:"+notions.get(i+2));
						throw new ValidateException(info);
					}
					Field f = new Field(notions.get(i+2),notions.get(i+3));
					domain.addField(f);	
					i += 3;
				}else if (!isKeyword(notions.get(i+4)) && !";".equals(notions.get(i+4))&&!"}".equals(notions.get(i+4))){
					Field f = new Field(notions.get(i+2),notions.get(i+3),notions.get(i+4));
					domain.addField(f);	
					i += 4;
				}						
				continue;
			}
			

			if (";".equals(notions.get(i))) continue;
		}				
		ValidateInfo info = new ValidateInfo();
		info.addCompileError("解析域对象列表错误！");
		throw new ValidateException(info);
	}
	
	public static boolean isKeyword(String notion){
		if ("project".equals(notion)||"prism".equals(notion)||"domain".equals(notion)||"field".equals(notion)
				||"packagetoken".equals(notion)||"plural".equals(notion)||"activefield".equals(notion)
				||"domainname".equals(notion)||"domainid".equals(notion)||"prismdomain".equals(notion)
				||"dbname".equals(notion)|| "emptypassword".equals(notion) ||"call".equals(notion) || "magic".equals(notion)) return true;
		else return false;
	}
	
	public static int countDomains(List<String> notions){
		int count = 0;
		for (String s:notions){
			if (s.equals("domain")) count ++;
		}
		return count;
	}
	
	public static int countPrisms(List<String> notions){
		int count = 0;
		for (String s:notions){
			if (s.equals("prism")) count ++;
		}
		return count;
	}
	
	public static boolean containsRoundAndSquareBrackets(String source){
		return source.contains("[") || source.contains("]")|| source.contains("(")|| source.contains(")");
	}
	
	public static ValidateInfo validateDomainsAndPrisms(List<Domain> domains,List<Prism> prisms){
		ValidateInfo validateInfo1 = validateDomains(domains);
		ValidateInfo validateInfo2 = validatePrisms(prisms);
		List<ValidateInfo> vList = new ArrayList<ValidateInfo>();
		vList.add(validateInfo1);
		vList.add(validateInfo2);
		ValidateInfo validateInfo = ValidateInfo.mergeValidateInfo(vList);
		return validateInfo;
	}
	
	public static ValidateInfo validateDomains(List<Domain> domains){
		ValidateInfo validateInfo = new ValidateInfo();
		List<Domain> targets = new ArrayList<Domain>();
		for (int i=0;i<domains.size();i++){
			for (int j=0;j<targets.size();j++){
				if (domains.get(i).getStandardName().equals(targets.get(j).getStandardName())) {
					validateInfo.addCompileWarning("域对象"+ domains.get(i).getStandardName()+"重复。");
				}else{
					targets.add(domains.get(i));
				}
			}
		}
		return validateInfo;
	}
	
	public static ValidateInfo validatePrisms(List<Prism> prisms){
		ValidateInfo validateInfo = new ValidateInfo();
		List<Prism> targets = new ArrayList<Prism>();
		for (int i=0;i<prisms.size();i++){
			for (int j=0;j<targets.size();j++){
				if (prisms.get(i).getStandardName().equals(targets.get(j).getStandardName())) {
					validateInfo.addCompileWarning("棱柱"+ prisms.get(i).getStandardName()+"重复。");
				}else{
					targets.add(prisms.get(i));
				}
			}
		}
		if (prisms != null){
			for (int i=0;i<prisms.size();i++){
				if (!prisms.get(i).getStandardName().equals(prisms.get(i).getDomain().getStandardName()+"Prism")){
					validateInfo.addCompileWarning("棱柱"+prisms.get(i).getStandardName() + "的域对象"+prisms.get(i).getDomain().getStandardName()+"没有正确设置。");
				}
			}
		}
		return validateInfo;
	}
	
	public static ValidateInfo basicValidateProject(Project project){
		List<Prism> prisms = project.getPrisms();
		List<Domain> domains = project.getDomains();
		return validateDomainsAndPrisms(domains, prisms);
	}
	
	public static Project callMagic(Project project,List<Domain> domainList,DBDefinitionGenerator dbdg) throws Exception{
		ApplicationContextXml axml = new ApplicationContextXml();
		axml.setDbname(project.getDbName());
		axml.setDbUsername(project.getDbUsername());
		axml.setDbPassword(project.getDbPassword());
		Project project2 = new Project(project.getStandardName(),project.getPackageToken(),project.getTechnicalstack(),
		project.getDbUsername(),project.getDbPassword(),project.isEmptypassword(),project.getDbName());

		if (project.getPackageToken() == null || "".equals(project.getPackageToken())) {		
			ValidateInfo info = new ValidateInfo();
			info.addCompileError("没有设置PackageToken！");
			throw new ValidateException(info);
		} else {			
			String dbPrefix = project.getDbPrefix();
			for (int m=0;m<domainList.size();m++){
				domainList.get(m).setDbPrefix(dbPrefix);
				domainList.get(m).setPackageToken(project.getPackageToken());
			}
		}
		List<S2SHPrism> prismList = generatePrismsByDomains(domainList,dbdg);
		for (S2SHPrism sp:prismList){
			project2.addPrism(sp);
		}
		
		TreeSet<Action> myActions = new TreeSet<>();
		for (S2SHPrism p:prismList){
			myActions.add(p.getAction());
		}
		
		TreeSet<Struts2Facade> myfacades = new TreeSet<>();
		for (S2SHPrism p:prismList){
			myfacades.add(p.getFacade());
		}

		project2.setDomains(domainList);
		dbdg.setDomains(domainList);
		
		axml.setDomainList(domainList);
		axml.setFacadeList(new ArrayList<>(myfacades));
		axml.setActionList(new ArrayList<>(myActions));
		
		List<String> packageToScanList = new ArrayList<String>();
		packageToScanList.add(project.getPackageToken()+".domain");
		axml.setPackagesToScanList(packageToScanList);
		axml.setPutInsideSrcAndClasses(true);
	
		project2.addDBDefinitionGenerator(dbdg);									
		project2.replaceConfigFile(axml);
		return project2;
	}
	
	public static Project callMagicS2SHC(Project project,List<Domain> domainList,DBDefinitionGenerator dbdg) throws Exception{
		ApplicationContextXml axml = new ApplicationContextXml();
		axml.setDbname(project.getDbName());
		axml.setDbUsername(project.getDbUsername());
		axml.setDbPassword(project.getDbPassword());
		Project project2 = new Project(project.getStandardName(),project.getPackageToken(),project.getTechnicalstack(),
		project.getDbUsername(),project.getDbPassword(),project.isEmptypassword(),project.getDbName());

		if (project.getPackageToken() == null || "".equals(project.getPackageToken())) {		
			ValidateInfo info = new ValidateInfo();
			info.addCompileError("没有设置PackageToken！");
			throw new ValidateException(info);
		} else {			
			String dbPrefix = project.getDbPrefix();
			for (int m=0;m<domainList.size();m++){
				domainList.get(m).setDbPrefix(dbPrefix);
				domainList.get(m).setPackageToken(project.getPackageToken());
			}
		}
		List<S2SHPrism> prismList = generateS2SHCPrismsByDomains(domainList,dbdg);
		for (S2SHPrism sp:prismList){
			project2.addPrism(sp);
		}
		
		TreeSet<Action> myActions = new TreeSet<>();
		for (S2SHPrism p:prismList){
			myActions.add(p.getAction());
		}
		
		TreeSet<Struts2Facade> myfacades = new TreeSet<>();
		for (S2SHPrism p:prismList){
			myfacades.add(p.getFacade());
		}

		project2.setDomains(domainList);
		dbdg.setDomains(domainList);
		
		axml.setDomainList(domainList);
		axml.setFacadeList(new ArrayList<>(myfacades));
		axml.setActionList(new ArrayList<>(myActions));
		
		List<String> packageToScanList = new ArrayList<String>();
		packageToScanList.add(project.getPackageToken()+".domain");
		axml.setPackagesToScanList(packageToScanList);
		axml.setPutInsideSrcAndClasses(true);
	
		project2.addDBDefinitionGenerator(dbdg);									
		project2.replaceConfigFile(axml);
		return project2;
	}
	
	public static Project callMagicClockSimpleJEE(Project project,List<Domain> domainList,DBDefinitionGenerator dbdg) throws Exception{
		List<Prism> prismList = generateClockSimpleJeePrismsByDomains(domainList,dbdg);
		project.setDomains(domainList);
		project.setPrisms(prismList);
		return project;
	}
	
	public static boolean locateCallMagic(List<String> notions){
		if (notions.contains("call")&&notions.contains("magic")) return true;
		else return false;
	}
	
	public static List<S2SHPrism> generatePrismsByDomains(List<Domain> domainList, DBDefinitionGenerator dbdg) throws Exception{
		List<S2SHPrism> prisms = new ArrayList<>();
		for (Domain d:domainList){
			S2SHPrism p = new S2SHPrism();
			p.setStandardName(d.getCapFirstDomainName()+"Prism");
			p.setPackageToken(d.getPackageToken());
			p.setDomain(d);			
			p.setDbDefinitionGenerator(dbdg);
			p.generatePrismFromDomain();			
			prisms.add(p);
		}
		return prisms;
	}
	
	public static List<S2SHPrism> generateS2SHCPrismsByDomains(List<Domain> domainList, DBDefinitionGenerator dbdg) throws Exception{
		List<S2SHPrism> prisms = new ArrayList<>();
		for (Domain d:domainList){
			S2SHPrism p = new S2SHPrism();
			p.setStandardName(d.getCapFirstDomainName()+"Prism");
			p.setPackageToken(d.getPackageToken());
			p.setDomain(d);			
			p.setDbDefinitionGenerator(dbdg);
			p.generateS2SHCPrismFromDomain();			
			prisms.add(p);
		}
		return prisms;
	}
	
	public static List<Prism> generateClockSimpleJeePrismsByDomains(List<Domain> domainList, DBDefinitionGenerator dbdg) throws Exception{
		List<Prism> prisms = new ArrayList<>();
		for (Domain d:domainList){
			Prism p = new Prism();			
			p.setStandardName(d.getCapFirstDomainName()+"Prism");
			p.setPackageToken(d.getPackageToken());
			p.setDomain(d);			
			p.setDbDefinitionGenerator(dbdg);
			p.generatePrismFromDomain();
			prisms.add(p);
		}
		return prisms;
	}
	
	public static List<String> fixDomainsNotions(List<String> notions, Stack<String> myStack){
		return removeNotions(notions,myStack.size());
	}
	
	public static List<String> removeNotions(List<String> notions, int count){
		if (notions == null || notions.size() < 1) return new ArrayList<String>();
		int endFlag = notions.size() -1;
		for (int i=notions.size()-1;i>=0;i--){
			if (!notions.get(i).equals("}") && count >= 0){
				endFlag --;
			}else if (notions.get(i).equals("}") && count > 0){
				count --;
				endFlag--;
			}else if (notions.get(i).equals("}") && count ==0){
				return notions.subList(0,endFlag+1);
			}
		}
		return notions;
	}
}