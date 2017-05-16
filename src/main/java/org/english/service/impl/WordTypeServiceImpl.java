package org.english.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.english.dao.api.WordDao;
import org.english.dao.api.WordTypeDao;
import org.english.form.WordType;
import org.english.service.api.WordTypeService;
import org.springframework.stereotype.Service;

@Service(value = "wordTypeServiceImpl")
public class WordTypeServiceImpl implements WordTypeService{
	@Resource(name = "wordTypeDaoImpl")
	WordTypeDao wordTypeDao;

	public boolean addWordType(WordType wordType) {
		// TODO Auto-generated method stub
		return false;
	}

	public WordType getWordTypeById(int id) {
		return wordTypeDao.getWordTypeById(id);
	}

	public List<WordType> getWordByKeyWordPage(String keyword, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getWordCount(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean updateWordType(WordType wordType) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteWordType(WordType wordType) {
		// TODO Auto-generated method stub
		return false;
	}
}
