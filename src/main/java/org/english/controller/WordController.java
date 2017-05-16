package org.english.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.english.dao.api.AdminDao;
import org.english.form.Admin;
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
public class WordController {
	@Resource(name = "wordServiceImpl")
	WordService wordService;
	@Resource(name="wordTypeServiceImpl")
	WordTypeService wordTypeService;
	@Resource(name="studentWordServiceImpl")
	StudentWordService studentWordService;
	@Autowired
	AdminDao adminDao;
	@RequestMapping(value="back/addWord.do",method={RequestMethod.POST,RequestMethod.GET})
	public String addWord(HttpServletRequest request, HttpServletResponse response,@RequestParam("pronunciation") MultipartFile pronunciation,@RequestParam("name")String name,@RequestParam("soundmark")String soundmark,@RequestParam("meaning")String meaning) {
		String filePath = request.getSession().getServletContext().getRealPath("/") + "upload"+File.separator+pronunciation.getOriginalFilename();
		System.out.println();
		try {
			pronunciation.transferTo(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Word word = new Word();
		word.setName(name);
		word.setMeaning(meaning);
		word.setPronunciation(filePath);
		word.setSoundmark(soundmark);
		String[] wordTypes = request.getParameterValues("wordType");
		Set<WordType> wordTypeSet = new HashSet<WordType>();
		for(String wordType:wordTypes){
			wordTypeSet.add(wordTypeService.getWordTypeById(Integer.parseInt(wordType)));
		}
		word.setWordTypes(wordTypeSet);
		wordService.addWord(word);
		return "back/word_list";
	}
	@RequestMapping(value="wordDetail.do",method={RequestMethod.POST,RequestMethod.GET})
	public String wordDetail(HttpServletRequest request, HttpServletResponse response,@RequestParam("wordId")Integer wordId) {
		Word word = wordService.getWordById(wordId);
		String filepath = word.getPronunciation();
		String[] filePaths = filepath.split("\\\\");
		word.setPronunciation("upload"+File.separator+filePaths[filePaths.length-1]);
		request.setAttribute("word", word);
		return "word_detail";
	}
	@RequestMapping(value="allWordPaging.do")
	public void allReviewPaging(HttpServletRequest request, HttpServletResponse response,@RequestParam("page")int page,@RequestParam("pageSize")int pageSize,String wordType,String wordName) throws IOException  {
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		System.out.println(page+" "+pageSize);
		Integer wordTypeId = Integer.parseInt(wordType);
		List<Word> words = wordService.getWordByKeyWordPage(wordName,wordTypeId, page, pageSize);
		JSONArray arr = new JSONArray();
		if(words!=null){
			for(Word w:words){
				JSONObject tmp = new JSONObject();
				tmp.put("id", w.getId());
				tmp.put("name", w.getName());
				tmp.put("meaning", w.getMeaning());
				tmp.put("pronunciation", w.getPronunciation());
				tmp.put("soundmark", w.getSoundmark());
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
		double d = pageSize;
		result.put("totalpage", Math.ceil(wordService.getWordCount("")/d));
		response.getWriter().write(result.toString());
	}
	@RequestMapping(value="updateWordTime.do")
	public void updateWordTime(HttpServletRequest request, HttpServletResponse response,@RequestParam("time")int time,int student_word_id) throws IOException  {
		JSONObject result = new JSONObject();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		System.out.println(time+" "+student_word_id);
		StudentWord sw = studentWordService.getStudentWordById(student_word_id);
		int beforeTime = sw.getMemoryTime();
		sw.setMemoryTime(beforeTime+time);
		studentWordService.updateStudent(sw);
		result.put("totalpage", "success");
		response.getWriter().write(result.toString());
	}
}
