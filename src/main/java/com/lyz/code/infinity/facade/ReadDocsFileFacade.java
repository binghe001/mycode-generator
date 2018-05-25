package com.lyz.code.infinity.facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReadDocsFileFacade extends HttpServlet {

	private static final long serialVersionUID = -6561890602591625283L;

	public ReadDocsFileFacade() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json;charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 try {			
			String filename = request.getParameter("filename");
			String sourcefolder = request.getSession().getServletContext().getRealPath(File.separator)+File.separator+"docs"+File.separator;
			String result = readTxtFile(sourcefolder+filename);			
			out.print(result);
		} catch(Exception e){
			e.printStackTrace();
			out.print("");
		}
	 }
	 
	    public String readTxtFile(String fileName){
	        try {
	                String encoding="UTF-8";
	                StringBuilder sb = new StringBuilder();
	                File file=new File(fileName);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                        sb.append(lineTxt+"\n");
	                    }
	                    read.close();
	                    return sb.toString();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	        return "";
	    }

}
