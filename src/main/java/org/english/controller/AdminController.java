package org.english.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.english.dao.api.AdminDao;
import org.english.dao.api.WordDao;
import org.english.form.Admin;
import org.english.form.Role;
import org.english.form.Student;
import org.english.form.StudentWord;
import org.english.form.Word;
import org.english.service.api.StudentWordService;
import org.english.service.api.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="back")
public class AdminController {
	@Resource(name = "adminDaoImpl")
	AdminDao adminDao;
	@Resource(name = "wordServiceImpl")
	WordService wordService;
	@Resource(name = "studentWordServiceImpl")
	StudentWordService studentWordService;
	@RequestMapping(value="/adminLoginAction.do",method={RequestMethod.POST,RequestMethod.GET})
	public void loginAction(HttpServletRequest request, HttpServletResponse response,String email,String password) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Admin admin = adminDao.getAdminByEmail(email, password);//studentService.getStudentByEmail(email,password);
		if(admin!=null){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		request.getSession().setAttribute("currentAdmin", admin);
		request.getSession().setAttribute("currentAdminRole", adminDao.getAdminRoleId(admin.getId()));
		response.getWriter().write(result.toString());
	}
	
	@RequestMapping(value="/register.do",method={RequestMethod.POST,RequestMethod.GET})
	public String registerView(HttpServletRequest request, HttpServletResponse response,String email,String password,String name) {
		return "forward:register.html";
	}
	@RequestMapping(value="/deleteWord.do",method={RequestMethod.POST,RequestMethod.GET})
	public void deleteWordAction(HttpServletRequest request, HttpServletResponse response,int wordId) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Set<StudentWord> sws = wordService.getWordById(wordId).getStudentWords();
		for(StudentWord sw:sws){
			studentWordService.deleteStudentWord(sw);
		}
		Word word = new Word();
		word.setId(wordId);
		if(wordService.deleteWord(word)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		response.getWriter().write(result.toString());
	}
	@RequestMapping(value="/adminRegisterAction.do",method={RequestMethod.POST,RequestMethod.GET})
	public void registerAction(HttpServletRequest request, HttpServletResponse response,String email,String password,String name) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(password);
		admin.setEmail(email);
		Set<Role> roles = new HashSet<Role>();
		Role r = new Role();
		r.setId(3);
		roles.add(r);
		admin.setRoles(roles);
		if(adminDao.addAdmin(admin)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		response.getWriter().write(result.toString());
	}
	
	@RequestMapping(value="allAdminPaging.do")
	public void allAdminPaging(HttpServletRequest request, HttpServletResponse response,@RequestParam("page")int page,@RequestParam("pageSize")int pageSize) throws IOException  {
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		System.out.println(page+" "+pageSize);
		List<Admin> admins = adminDao.getAllAdminPage(page, pageSize);
		JSONArray arr = new JSONArray();
		if(admins!=null){
			for(Admin w:admins){
				JSONObject tmp = new JSONObject();
				tmp.put("id", w.getId());
				tmp.put("name", w.getName());
				tmp.put("role_id", adminDao.getAdminRoleId(w.getId()));
				arr.add(tmp);
			}
		}
		result.put("info2", arr);
		result.put("currentpage", page);
		double d = pageSize;
		result.put("totalpage", Math.ceil(adminDao.getAllAdminCount()/d));
		response.getWriter().write(result.toString());
	}
	@RequestMapping(value="updateRolePre.do")
	public String updateRolePre(HttpServletRequest request, HttpServletResponse response,@RequestParam("adminId")int adminId) throws IOException  {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		Admin admin = adminDao.getAdminById(adminId);
		request.setAttribute("admin", admin);
		return "back/admin_update";
	}
	@RequestMapping(value="adminUpdateRole.do")
	public void adminUpdateRole(HttpServletRequest request, HttpServletResponse response,@RequestParam("adminId")int adminId,@RequestParam int roleId) throws IOException  {
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		if(adminDao.updateRoleByAdminId(adminId,roleId)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		response.getWriter().write(result.toString());
	}
	
}
