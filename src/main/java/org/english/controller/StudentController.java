package org.english.controller;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.english.dao.api.WordDao;
import org.english.form.Student;
import org.english.form.StudentWord;
import org.english.form.Word;
import org.english.service.api.StudentService;
import org.english.service.api.StudentWordService;
import org.english.service.api.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class StudentController {
	@Resource(name = "wordServiceImpl")
	WordService wordService;
	@Resource(name = "studentServiceImpl")
	StudentService studentService;
	@Resource(name = "studentWordServiceImpl")
	StudentWordService studentWordService;
	@RequestMapping(value="registerAction.do",method={RequestMethod.POST,RequestMethod.GET})
	public void registerAction(HttpServletRequest request, HttpServletResponse response,String email,String password,String name) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Student student = new Student();
		student.setName(name);
		student.setPassword(password);
		student.setEmail(email);
		if(studentService.addStudent(student)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		response.getWriter().write(result.toString());
	}
	@RequestMapping(value="login.do",method={RequestMethod.POST,RequestMethod.GET})
	public String loginView(HttpServletRequest request, HttpServletResponse response,String email,String password,String name) throws IOException {
		return "redirect:login.html";
	}
	@RequestMapping(value="loginAction.do",method={RequestMethod.POST,RequestMethod.GET})
	public void loginAction(HttpServletRequest request, HttpServletResponse response,String email,String password) throws IOException {
		System.out.println("hhh");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Student student = studentService.getStudentByEmail(email,password);
		if(student!=null){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		request.getSession().setAttribute("currentStudent", student);
		response.getWriter().write(result.toString());
	}
	
	@RequestMapping(value="student/addWord.do",method={RequestMethod.POST,RequestMethod.GET})
	public void addWordAction(HttpServletRequest request, HttpServletResponse response,String wordId) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Student student = (Student) request.getSession().getAttribute("currentStudent");
		Set<StudentWord> studentWords = new HashSet<StudentWord>();
		StudentWord sw = new StudentWord();
		sw.setStudent(student);
		sw.setWord(wordService.getWordById(Integer.parseInt(wordId)));
		sw.setMemoryTime(0);
		sw.setFlag(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sw.setAddDate(sdf.format(new Date()));
		studentWords.add(sw);
		student.setStudentWords(studentWords);
		if(studentService.updateStudent(student)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		response.getWriter().write(result.toString());
	}
	@RequestMapping(value="allStudentWordPaging.do")
	public void allStudentWordPaging(HttpServletRequest request, HttpServletResponse response,@RequestParam("page")int page,@RequestParam("pageSize")int pageSize) throws IOException  {
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		System.out.println(page+" "+pageSize);
		Student student = (Student) request.getSession().getAttribute("currentStudent");
		List<Word> words = null;
		JSONArray arr = new JSONArray();
		if(student==null){
			words=wordService.getWordByKeyWordPage("", 0, page, pageSize);
			if(words!=null){
				for(Word w:words){
					JSONObject tmp = new JSONObject();
					tmp.put("id", w.getId());
					tmp.put("name", w.getName());
					tmp.put("meaning", w.getMeaning());
					String filepath = w.getPronunciation();
					String[] filePaths = filepath.split("\\\\");
					w.setPronunciation("upload"+File.separator+filePaths[filePaths.length-1]);
					tmp.put("pronunciation", w.getPronunciation());
					tmp.put("soundmark", w.getSoundmark());
					//当前该人的该单词的id
					//tmp.put("student_word_id", w.getStudentWords().);
					arr.add(tmp);
				}
			}
			double d = pageSize;
			result.put("totalpage", Math.ceil(wordService.getWordCount("")/d));
		}else{
			words = wordService.getWordByKeyWordPage(student.getId(),page,pageSize);
			if(words!=null){
				for(Word w:words){
					JSONObject tmp = new JSONObject();
					tmp.put("id", w.getId());
					tmp.put("name", w.getName());
					tmp.put("meaning", w.getMeaning());
					String filepath = w.getPronunciation();
					String[] filePaths = filepath.split("\\\\");
					w.setPronunciation("upload"+File.separator+filePaths[filePaths.length-1]);
					tmp.put("pronunciation", w.getPronunciation());
					tmp.put("soundmark", w.getSoundmark());
					tmp.put("time",w.getStudentWords().iterator().next().getMemoryTime()/1000);
					tmp.put("flag", w.getStudentWords().iterator().next().isFlag());
					tmp.put("addDate",w.getStudentWords().iterator().next().getAddDate());
					System.out.println("cjttime="+w.getStudentWords().iterator().next().getMemoryTime());;
					tmp.put("student_word_id",w.getStudentWords().iterator().next().getId());
					//当前该人的该单词的id
					System.out.println("student_word_id="+w.getStudentWords().iterator().next().getId());;
					//tmp.put("student_word_id", w.getStudentWords().);
					arr.add(tmp);
				}
			}
			double d = pageSize;
			result.put("totalpage", Math.ceil(wordService.getWordByStudentIdCount(student.getId())/d));
		}
		
		
		result.put("info2", arr);
		result.put("currentpage", page);
		
		response.getWriter().write(result.toString());
	}
	@RequestMapping(value="student/deleteStudentWord.do",method={RequestMethod.POST,RequestMethod.GET})
	public void deleteStudentWordAction(HttpServletRequest request, HttpServletResponse response,int wordid) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Student student = (Student) request.getSession().getAttribute("currentStudent");
		StudentWord sw = new StudentWord();
		Student s = new Student();
		s.setId(student.getId());
		sw.setStudent(s);
		Word w = new Word();
		w.setId(wordid);
		sw.setWord(w);
		if(studentWordService.deleteStudentWord(sw)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		/*List<StudentWord> sws = studentWordService.getStudentWordByStudentIdAndWordId(wordid, student.getId());
		if(sws!=null){
			for(StudentWord sw:sws){
				if(studentWordService.deleteStudentWord(sw)){
					result.put("statue", "success");
				}else{
					result.put("statue", "fail");
				}
			}
		}*/
		response.getWriter().write(result.toString());
	}
	
	@RequestMapping(value="student/wordStudentSelect.do",method={RequestMethod.POST,RequestMethod.GET})
	public void wordStudentSelectAction(HttpServletRequest request, HttpServletResponse response,int wordid) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		Student student = (Student) request.getSession().getAttribute("currentStudent");
		List<StudentWord> sws = studentWordService.getStudentWordByStudentIdAndWordId(wordid, student.getId());
		if(sws!=null){
			result.put("statue", "true");
		}else{
			result.put("statue", "false");
		}
		response.getWriter().write(result.toString());
	}
	@RequestMapping(value="student/updateStudentWordFlag.do",method={RequestMethod.POST,RequestMethod.GET})
	public void updateStudentWordFlagAction(HttpServletRequest request, HttpServletResponse response,int student_word_id,boolean flag) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		if(studentWordService.updateStudentWordFlagById(student_word_id, flag)){
			result.put("statue", "success");
		}else{
			result.put("statue", "fail");
		}
		response.getWriter().write(result.toString());
	}
}
