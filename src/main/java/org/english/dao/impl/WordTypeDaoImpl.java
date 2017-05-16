package org.english.dao.impl;

import java.util.List;

import org.english.dao.api.WordTypeDao;
import org.english.form.Word;
import org.english.form.WordType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository(value = "wordTypeDaoImpl")
public class WordTypeDaoImpl implements WordTypeDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	public boolean addWordType(WordType wordType) {
		return false;
	}

	public WordType getWordTypeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		WordType wordtype = null;
		try {
			wordtype = (WordType) session.get(WordType.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return wordtype;
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
