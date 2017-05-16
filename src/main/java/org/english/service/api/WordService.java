package org.english.service.api;

import java.util.List;

import org.english.form.Word;

public interface WordService {
	boolean addWord(Word word);
	public Word getWordById(int id);

	public long getWordCount(String keyword);

	boolean updateWord(Word word);

	boolean deleteWord(Word word);
	List<Word> getWordByKeyWordPage(String wordName, Integer wordTypeId,
			int page, int pageSize);
	List<Word> getWordByKeyWordPage(int studentId, int page, int pageSize);
	Long getWordByStudentIdCount(int id);
}
