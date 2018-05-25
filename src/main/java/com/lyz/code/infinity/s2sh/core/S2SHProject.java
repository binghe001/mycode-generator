package com.lyz.code.infinity.s2sh.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.lyz.code.infinity.core.WebXml;
import com.lyz.code.infinity.domain.ConfigFile;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.FileCopyer;
import com.lyz.code.infinity.domain.Include;
import com.lyz.code.infinity.domain.Prism;
import com.lyz.code.infinity.domain.Project;
import com.lyz.code.infinity.domain.Util;
import com.lyz.code.infinity.domain.ValidateInfo;
import com.lyz.code.infinity.generator.DBDefinitionGenerator;
import com.lyz.code.infinity.include.AdminNav;
import com.lyz.code.infinity.include.Footer;
import com.lyz.code.infinity.include.Header;
import com.lyz.code.infinity.include.Updates;
import com.lyz.code.infinity.include.UserNav;
import com.lyz.code.infinity.utils.ConfigParser;
import com.lyz.code.infinity.utils.DBConf;
import com.lyz.code.infinity.utils.DbconfigXml;
import com.lyz.code.infinity.utils.ParseXML;
import com.lyz.code.infinity.utils.ReadConfigXml;
import com.lyz.code.infinity.utils.ZipCompressor;

public class S2SHProject extends Project{
	
	public S2SHProject(){
		super();
		addUtil(new DBConf());
		addUtil(new ConfigParser());
		addUtil(new ParseXML());
		addUtil(new ReadConfigXml());
		addUtil(new DbconfigXml());
		addConfigFile(new WebXml());
		
		addInclude(new Header());
		addInclude(new Footer());
		addInclude(new Updates());
		addInclude(new AdminNav());
		addInclude(new UserNav());
	}
	
	public S2SHProject(String projectName, String dbUser, String dbpassword,boolean emptypassword, String packageToken){
		super();
		this.standardName = projectName;
		this.packageToken = packageToken;
		addUtil(new DBConf(packageToken));
		addUtil(new ConfigParser(packageToken));
		addUtil(new ParseXML(packageToken));
		addUtil(new ReadConfigXml(packageToken));
		addUtil(new DbconfigXml(projectName, dbUser, dbpassword, emptypassword));
		WebXml webxml = new WebXml();
		webxml.setPackageToken(packageToken);
		webxml.setProjectName(this.getStandardName());
		addConfigFile(new WebXml());
		
		addInclude(new Header());
		addInclude(new Footer());
		addInclude(new Updates());
		addInclude(new AdminNav());
		addInclude(new UserNav());
	}

	@Override
	public void generateProjectFiles(){
		ValidateInfo info = this.validate();
		if (info.success() == false) {
			for (String s:info.getCompileErrors()){
				System.out.println(s);
			}
			return;
		}
		File f;
		FileWriter fw;
		String s;
		try {
			for (Domain d: this.domains){
				String srcfolderPath = this.getProjectFolderPath();
				if (this.packageToken != null && !"".equals(this.packageToken))
					srcfolderPath = folderPath + this.getStandardName() +"/src/" + packagetokenToFolder(this.packageToken)+"/domain/";
				f = new File(srcfolderPath+d.getStandardName()+".java");
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fw = new FileWriter(f);    
				s = d.generateClassString();   
				fw.write(s,0,s.length());    
				fw.flush();
				fw.close();
			}
			for (Prism ps: this.prisms){
				ps.setFolderPath(this.getProjectFolderPath());
				ps.generatePrismFiles();
			}
			for (Util u:utils){
				String utilPath = this.getProjectFolderPath() + "src/" + packagetokenToFolder(u.getPackageToken());
				f = new File(utilPath+u.getFileName());
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fw = new FileWriter(f);    
				s = u.generateUtilString();   
				fw.write(s,0,s.length());    
				fw.flush();
				fw.close();
			}
			
			for (Include include:this.includes){
				String includePath = this.getProjectFolderPath() + "WebContent/include/";
				f = new File(includePath+include.getFileName());
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fw = new FileWriter(f);    
				s = include.generateIncludeString();   
				fw.write(s,0,s.length());    
				fw.flush();
				fw.close();
			}
			
			String homePath = this.getProjectFolderPath() + "WebContent/jsp/";
			f = new File(homePath+this.homepage.getFileName());
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fw = new FileWriter(f);    
			s = this.homepage.generateIncludeString();   
			fw.write(s,0,s.length());    
			fw.flush();
			fw.close();
			
			String jumpHomePath = this.getProjectFolderPath() + "WebContent/";
			f = new File(jumpHomePath+this.jumphomepage.getFileName());
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fw = new FileWriter(f);    
			s = this.jumphomepage.generateIncludeString();   
			fw.write(s,0,s.length());    
			fw.flush();
			fw.close();
			
			File libto = new File(this.getProjectFolderPath() + "WebContent/WEB-INF/lib/");
			File libfrom = new File(this.getSourceFolderPath() + "lib/");
			
			FileCopyer copy = new FileCopyer();
			// 设置来源去向
			copy.dirFrom = libfrom;
			copy.dirTo = libto;
			copy.listFileInDir(libfrom);
			
			File cssfrom = new File(this.getSourceFolderPath() + "css/");
			File cssto = new File(this.getProjectFolderPath() + "WebContent/css/");
			
			copy.dirFrom = cssfrom;
			copy.dirTo = cssto;
			copy.listFileInDir(cssfrom);
			
			File jsfrom = new File(this.getSourceFolderPath() + "js/");
			File jsto = new File(this.getProjectFolderPath() + "WebContent/js/");
			
			copy.dirFrom = jsfrom;
			copy.dirTo = jsto;
			copy.listFileInDir(jsfrom);
			
			StringBuilder sql = new StringBuilder();
			boolean createDb = true;
			for (DBDefinitionGenerator dbd:dbDefinitionGenerators){
				sql.append(dbd.generateDBSql(createDb)).append("\n");
				if (createDb) createDb = false;
	       }
		
		f = new File(this.getProjectFolderPath()+"sql/"+this.dbName+".sql");
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fw = new FileWriter(f);    
	    s = sql.toString();
        fw.write(s,0,s.length());    
        fw.flush();
		fw.close();
		} catch(Exception e){
			e.printStackTrace();
		}	
		
        for (ConfigFile cf : this.configFiles){
        	f = new File(this.getProjectFolderPath()+"WebContent/WEB-INF/"+cf.getStandardName());
    		if (!f.getParentFile().exists()) {
    			f.getParentFile().mkdirs();
    		}
    		try {
    			f.createNewFile();
            	//fw = new FileWriter(f);    
    	        //s = cf.generateConfigFileString();    
    	        //fw.write(s,0,s.length());    
    	        //fw.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

        }
	}

	@Override
	public void generateProjectZip(){
		delFolder(this.getProjectFolderPath());
		File f = new File(this.getProjectFolderPath());
		if (!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
				
		//generateProjectFiles();
		ZipCompressor compressor = new ZipCompressor(this.folderPath+this.standardName+".zip");
		compressor.compressExe(this.getProjectFolderPath());
	}

	public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file:files)
				zipFile(file, zos, dir + "/" + inFile.getName());
		} else {
			String entryName = null;
			if (!"".equals(dir))
				entryName = dir + "/" + inFile.getName();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			int len = 0;
			while ((len = is.read()) != -1)
				zos.write(len);
			is.close();
		}
	}

	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public ValidateInfo validate(){
		ValidateInfo info = new ValidateInfo();
		info.setSuccess(true);
		for (Prism ps:this.prisms){
			ValidateInfo v = ps.validate();
			if (v.success() == false){
				info.setSuccess(false);
				info.addAllCompileErrors(v.getCompileErrors());
				info.addAllCompileWarnings(v.getCompileWarnings());
			}
		}
		return info;
	}
	
}
