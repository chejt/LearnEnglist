package org.english.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.english.dao.api.StudentWordDao;
import org.english.form.StudentWord;
import org.english.service.api.StudentWordService;
import org.springframework.stereotype.Service;
@Service(value = "studentWordServiceImpl")
public class StudentWordServiceImpl implements StudentWordService {
	@Resource(name = "studentWordDaoImpl")
	StudentWordDao studentWordDao;
	public StudentWord getStudentWordById(int id) {
		return studentWordDao.getStudentWordById(id);
	}

	public boolean updateStudent(StudentWord studentword) {
		return studentWordDao.updateStudent(studentword);
	}

	public boolean deleteStudentWord(StudentWord sw) {
		return studentWordDao.deleteStudentWord(sw);
	}

	public List<StudentWord> getStudentWordByStudentIdAndWordId(int wordid,
			int studentId) {
		return studentWordDao.getStudentWordByStudentIdAndWordId(wordid, studentId);
	}

	public boolean updateStudentWordFlagById(int id, boolean flag) {
		return studentWordDao.updateStudentWordFlagById(id, flag);
	}

}
