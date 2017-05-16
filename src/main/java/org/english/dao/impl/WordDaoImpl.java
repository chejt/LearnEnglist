package org.english.dao.impl;

import java.util.List;

import org.english.dao.api.WordDao;
import org.english.form.Word;
import org.english.form.WordType;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository(value = "wordDaoImpl")
public class WordDaoImpl implements WordDao{
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	public boolean addWord(Word word) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.merge(word);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public Word getWordById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Word word = null;
		try {
			word = (Word) session.get(Word.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		return word;
	}

	public List<Word> getWordByKeyWordPage(String keyword,int wordTypeId, int page,
			int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			String hqlStr = "from Word word where word.name like :keyword";
			if(wordTypeId!=0){
				hqlStr = "select word from Word word join word.wordTypes wt where word.name like :keyword and wt.id="+wordTypeId;
			}
			query = session.createQuery(hqlStr);
			query.setParameter("keyword", "%"+keyword+"%");
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		@SuppressWarnings("unchecked")
		List<Word> resultList = query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList;
		}
	}

	public long getWordCount(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Word word where word.name like :keyword");
		query.setParameter("keyword", "%"+keyword+"%");
		return (Long) query.iterate().next();
	}

	public boolean updateWord(Word word) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteWord(Word word) {
		System.out.println("***************before**************");
		Session session = sessionFactory.getCurrentSession();
		System.out.println("***************end**************");
		try{
			session.delete(word);
			//Query query = session.createSQLQuery("delete from words  where id="+word.getId());//delete(word);
			//query.executeUpdate();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public List<Word> getWordByKeyWordPage(int studentId, int page, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			String hqlStr =  "select word from Word word,StudentWord sw where word.id = sw.word.id and sw.student="+studentId;
			query = session.createQuery(hqlStr);
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		@SuppressWarnings("unchecked")
		List<Word> resultList = query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList;
		}
	}

	public Long getWordByStudentIdCount(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Word word,StudentWord sw where word.id = sw.word.id and sw.student="+id);
		return (Long) query.iterate().next();
	}

}
