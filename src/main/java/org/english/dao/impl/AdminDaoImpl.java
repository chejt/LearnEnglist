package org.english.dao.impl;

import java.util.List;

import org.english.dao.api.AdminDao;
import org.english.dao.api.StudentDao;
import org.english.form.Admin;
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
@Repository(value = "adminDaoImpl")
public class AdminDaoImpl implements AdminDao {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sessionFactory;
	public boolean addAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.merge(admin);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return true;
	}

	public Admin getAdminById(int id) {
		return (Admin) sessionFactory.getCurrentSession().get(Admin.class, id);
	}
	public int getAdminRoleId(int id){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select role_id from admin_role where admin_id="+id;
		Integer role_id = (Integer) session.createSQLQuery(sql).list().get(0);
		return role_id;
	}
	public Admin getAdminByEmail(String email, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			String hqlStr = "from Admin a where a.email =:email and a.password =:password";
			query = session.createQuery(hqlStr);
			query.setParameter("email", email);
			query.setParameter("password", password);
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		@SuppressWarnings("unchecked")
		List<Admin> resultList = query.list();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList.get(0);
		}
	}

	public List<Admin> getAllAdminPage(int page, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		try {
			String hqlStr = "from Admin a";
			query = session.createQuery(hqlStr);
		} catch (HibernateException e) {
			e.printStackTrace();
		} 
		@SuppressWarnings("unchecked")
		List<Admin> resultList = query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize).list();
		if (resultList.isEmpty()) {
			return null;
		} else {
			return resultList;
		}
	}

	public Long getAllAdminCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Admin a");
		return (Long) query.iterate().next();
	}

	public boolean updateRoleByAdminId(int adminId, int roleId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "update admin_role set role_id="+roleId+" where admin_id="+adminId;
		try{
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		}catch(HibernateException e){
			return false;
		}
		return true;
	}

}
