package org.english.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.english.dao.api.StudentDao;
import org.english.dao.api.WordDao;
import org.english.form.Student;
import org.english.service.api.StudentService;
import org.springframework.stereotype.Service;
@Service(value = "studentServiceImpl")
public class StudentServiceImpl implements StudentService {
	@Resource(name = "studentDaoImpl")
	StudentDao studentDao;
	public boolean addStudent(Student student) {
		return studentDao.addStudent(student);
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
		return studentDao.updateStudent(student);
	}

	public boolean deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	public Student getStudentByEmail(String email, String password) {
		return studentDao.getStudentByEmail(email,password);
	}

}
