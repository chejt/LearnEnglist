package org.english.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.english.dao.api.AdminDao;
import org.english.dao.api.ArticleDao;
import org.english.form.Admin;
import org.english.form.Article;
import org.english.form.Student;
import org.english.form.StudentWord;
import org.english.form.Word;
import org.english.form.WordType;
import org.english.service.api.StudentWordService;
import org.english.service.api.WordService;
import org.english.service.api.WordTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
@Controller
public class ArticleController {
	@Resource(name = "artickeDaoImpl")
	ArticleDao articleDao;
	@Autowired
	AdminDao adminDao;
	@RequestMapping(value="back/addArticle.do",method={RequestMethod.POST,RequestMethod.GET})
	public String addWord(HttpServletRequest request, HttpServletResponse response,@RequestParam("contentPath") MultipartFile contentPath,@RequestParam("name")String name) {
		String filePath = request.getSession().getServletContext().getRealPath("/") + "upload"+File.separator+contentPath.getOriginalFilename();
		System.out.println();
		try {
			contentPath.transferTo(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Article article = new Article();
		article.setName(name);
		article.setContentPath(filePath);
		articleDao.addArticle(article);
		return "redirect:article_list.html";
	}
	@RequestMapping(value="back/allArticlePaging.do")
	public void allArticlePaging(HttpServletRequest request, HttpServletResponse response,@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,String name) throws IOException  {
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		System.out.println(page+" "+pageSize);
		List<Article> articles = articleDao.getArticleByKeyWordPage(name, page, pageSize);
		JSONArray arr = new JSONArray();
		if(articles!=null){
			for(Article w:articles){
				JSONObject tmp = new JSONObject();
				tmp.put("id", w.getId());
				tmp.put("name", w.getName());
				String filepath = w.getContentPath();
				String[] filePaths = filepath.split("\\\\");
				tmp.put("filepath","upload"+File.separator+filePaths[filePaths.length-1] );
				arr.add(tmp);
			}
		}
		result.put("info2", arr);
		result.put("currentpage", page);
		Admin admin = (Admin) request.getSession().getAttribute("currentAdmin");
		result.put("adminRole",3);
		if(admin!=null){
			result.put("adminRole",adminDao.getAdminRoleId(admin.getId()));
		}
		result.put("totalpage", Math.ceil(articleDao.getArticleCount(name)/pageSize));
		response.getWriter().write(result.toString());
	}
	
	@RequestMapping(value="back/deleteArticle.do",method={RequestMethod.POST,RequestMethod.GET})
	public void deleteWordAction(HttpServletRequest request, HttpServletResponse response,int articleId) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Article article = new Article();
		article.setId(articleId);
		if(articleDao.deleteArticle(article)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		response.getWriter().write(result.toString());
	}
}
