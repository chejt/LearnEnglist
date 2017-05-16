package org.english.dao.impl;

import java.util.List;

import org.english.dao.api.StudentWordDao;
import org.english.form.StudentWord;
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
@Repository(value = "studentWordDaoImpl")
public class StudentWordDaoImpl implements StudentWordDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;

	public StudentWord getStudentWordById(int id) {
		return (StudentWord) sessionFactory.getCurrentSession().get(StudentWord.class, id);
	}

	public boolean updateStudent(StudentWord studentword) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(studentword);//();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteStudentWord(StudentWord sw) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "delete from student_word where student_id="+sw.getStudent().getId()+" and word_id="+sw.getWord().getId();
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			//session.delete(sw);//();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public List<StudentWord> getStudentWordByStudentIdAndWordId(int wordid,
			int studentId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			//String hqlStr = "select sw from StudentWord sw where sw.student.id =: studentId and sw.word.id =: wordid";
			String hqlStr = "select sw from StudentWord sw where sw.student.id ="+studentId+" and sw.word.id ="+ wordid;
			query = session.createQuery(hqlStr);
			/*query.setParameter("studentId", studentId);
			query.setParameter("wordid", wordid);*/
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		@SuppressWarnings("unchecked")
		List<StudentWord> resultList = query.list();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList;
		}
	}

	public boolean updateStudentWordFlagById(int id, boolean flag) {
		Session session = sessionFactory.getCurrentSession();
		try{
			String sql = "update student_word set flag="+flag+" where id="+id;
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

}
