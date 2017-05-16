package org.english.dao.impl;

import java.util.List;

import org.english.dao.api.StudentDao;
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
@Repository(value = "studentDaoImpl")
public class StudentDaoImpl implements StudentDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	public boolean addStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.save(student);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> getStudentByKeyWordPage(String keyword, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getStudentCount(String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.merge(student);//update(student);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	public Student getStudentByEmail(String email, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			String hqlStr = "from Student s where s.email =:email and s.password =:password";
			query = session.createQuery(hqlStr);
			query.setParameter("email", email);
			query.setParameter("password", password);
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		@SuppressWarnings("unchecked")
		List<Student> resultList = query.list();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

}
