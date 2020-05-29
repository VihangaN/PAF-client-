package com;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Staff;
import model.StaffRepository;



/**
 * Servlet implementation class StaffAPI
 */
@WebServlet("/StaffAPI")
public class StaffAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffRepository repository = new StaffRepository();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Staff staff = new Staff();
		staff.setNic(request.getParameter("StaffNic"));
		staff.setAddress(request.getParameter("StaffAddress"));
		staff.setAge(Integer.valueOf(request.getParameter("StaffAge")));
		staff.setEmail(request.getParameter("StaffEmail"));
		staff.setGender(request.getParameter("StaffGender"));
		staff.setName(request.getParameter("staffName"));
		staff.setType(Boolean.valueOf(request.getParameter("StaffType")));
		
		String	output = repository.createMember(staff);
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println(request);
		Staff staff = new Staff();
		Map paras = getParasMap(request);	
		System.out.println(paras);
		
		staff.setNic(URLDecoder.decode(paras.get("StaffNic").toString(), "UTF-8"));
		staff.setAddress(URLDecoder.decode(paras.get("StaffAddress").toString(), "UTF-8"));
		staff.setAge(Integer.valueOf(paras.get("StaffAge").toString()));
		staff.setEmail(URLDecoder.decode(paras.get("StaffEmail").toString(), "UTF-8"));
		staff.setGender(paras.get("StaffGender").toString());
		staff.setName(URLDecoder.decode(paras.get("staffName").toString(), "UTF-8"));
		staff.setType(Boolean.valueOf(paras.get("StaffType").toString()));
		staff.seteId(paras.get("StaffId").toString());
		
		String	 output = repository.updateMember(staff);
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		System.out.println(paras);

		int staffId = Integer.valueOf(paras.get("StaffId").toString());
		String	 output = repository.deleteMember(staffId);
		response.getWriter().write(output);
	}

private static Map getParasMap(HttpServletRequest request) { 
		
		Map<String, String> map = new HashMap<String, String>(); 
		
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?          
			scanner.useDelimiter("\\A").next() : "";  
			scanner.close(); 
			
			String[] params = queryString.split("&"); 
			
			for (String param : params) {
				String[] p = param.split("=");    
				map.put(p[0], p[1]); 
				
			}
		}
		catch (Exception e) {
			
			
		}
		
		return map; 
	}
}
