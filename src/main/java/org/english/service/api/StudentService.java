package org.english.service.api;

import java.util.List;

import org.english.form.Student;
import org.english.form.Word;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface StudentService {
	boolean addStudent(Student student);
	public Student getStudentById(int id);

	public List<Student> getStudentByKeyWordPage(String keyword, int page,
			int pageSize);
	
	public long getStudentCount(String keyword);

	boolean updateStudent(Student student);

	boolean deleteStudent(Student student);
	Student getStudentByEmail(String email, String password);
}
