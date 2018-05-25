package com.lyz.code.infinity.facade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.lyz.code.infinity.compiler.SGSCompiler;
import com.lyz.code.infinity.domain.Project;
import com.lyz.code.infinity.domain.ValidateInfo;
import com.lyz.code.infinity.exception.ValidateException;

/**
 * Servlet implementation class GenerateSampleCodeController
 */

public class GenerateSampleCodeFacade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateSampleCodeFacade() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json;charset=UTF-8");
          		  	PrintWriter out = response.getWriter();
		 Map<String,Object> result = new TreeMap<String,Object>();
		 try {
			
			String sourcefolder = request.getSession().getServletContext().getRealPath("/")+"source/";
			sourcefolder = sourcefolder.replace('\\', '/');
			
			String sgscode = request.getParameter("code");
			String ignoreWarning = request.getParameter("ignoreWarning");
			boolean ignoreWarningBoolean = ignoreWarning == null || ignoreWarning.equals("")?false:Boolean.parseBoolean(ignoreWarning);
			System.out.println(sgscode);
			Project project = SGSCompiler.translate(sgscode,ignoreWarningBoolean);
			
			System.out.println(sourcefolder);
			project.setFolderPath(sourcefolder);
			if (project.getTechnicalstack()==null||"".equalsIgnoreCase(project.getTechnicalstack())||"simplejee".equalsIgnoreCase(project.getTechnicalstack())||"jsp".equalsIgnoreCase(project.getTechnicalstack())||"clocksimplejee".equalsIgnoreCase(project.getTechnicalstack())){
				project.setSourceFolderPath((request.getSession().getServletContext().getRealPath("/")+"templates/").replace('\\', '/'));
			} else if("s2sh".equalsIgnoreCase(project.getTechnicalstack())){
				project.setSourceFolderPath((request.getSession().getServletContext().getRealPath("/")+"s2shtemplates/").replace('\\', '/'));			
			} else if("s2shc".equalsIgnoreCase(project.getTechnicalstack())){
				project.setSourceFolderPath((request.getSession().getServletContext().getRealPath("/")+"s2shtemplates/").replace('\\', '/'));			
			}			
			project.generateProjectZip();
			
			result.put("success", true);
			result.put("projectName", project.getStandardName());
			
			out.print(JSONObject.fromObject(result));
		 } catch (ValidateException ev){
			result.put("success", false);
			ValidateInfo info = ev.getValidateInfo();
			List<String> compileErrors = info.getCompileErrors();
			result.put("compileErrors", compileErrors);
			List<String> compileWarnings = info.getCompileWarnings();
			result.put("compileWarnings", compileWarnings);
			out.print(JSONObject.fromObject(result));
		 }catch(Exception e){
			if (!(e instanceof ValidateException)) {
				e.printStackTrace();
				result.put("success", false);
				if (e.getMessage() != null && !e.getMessage().equals("")){
					result.put("reason", e.getMessage());
				}else {
					result.put("reason", "Null pointer or other syntax error.");
				}
				out.print(JSONObject.fromObject(result));
			}
		}finally{
			out.close();
		}
	 }

}
