package org.english.dao.impl;

import java.util.List;

import org.english.dao.api.AdminDao;
import org.english.dao.api.ArticleDao;
import org.english.dao.api.StudentDao;
import org.english.form.Admin;
import org.english.form.Article;
import org.english.form.Student;
import org.english.form.Word;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository(value = "artickeDaoImpl")
public class ArtickeDaoImpl implements ArticleDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	public boolean addArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.merge(article);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public Article getWordById(int id) {
		return (Article) sessionFactory.getCurrentSession().get(Article.class, id);
	}

	public List<Article> getArticleByKeyWordPage(String keyword, int page,
			int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			String hqlStr =  "select article from Article article where article.name like :keyword ";
			query = session.createQuery(hqlStr);
			query.setParameter("keyword", "%"+keyword+"%");
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		@SuppressWarnings("unchecked")
		List<Article> resultList = query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList;
		}
	}

	public long getArticleCount(String keyword) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Article article where article.name like :keyword");
		query.setParameter("keyword", "%"+keyword+"%");
		return (Long) query.iterate().next();
	}

	public boolean updateArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.update(article);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.delete(article);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

}
