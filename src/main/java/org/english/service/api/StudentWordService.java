package org.english.service.api;

import java.util.List;

import org.english.form.StudentWord;

public interface StudentWordService {
	public StudentWord getStudentWordById(int id);
	boolean updateStudent(StudentWord studentword);
	boolean deleteStudentWord(StudentWord sw);
	public List<StudentWord> getStudentWordByStudentIdAndWordId(int wordid,int studentId);
	public boolean updateStudentWordFlagById(int id, boolean flag);
}
