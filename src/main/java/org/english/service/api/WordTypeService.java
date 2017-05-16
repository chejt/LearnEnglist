package org.english.service.api;

import java.util.List;

import org.english.form.WordType;

public interface WordTypeService {
	boolean addWordType(WordType wordType);
	public WordType getWordTypeById(int id);

	public List<WordType> getWordByKeyWordPage(String keyword, int page,
			int pageSize);
	
	public long getWordCount(String keyword);

	boolean updateWordType(WordType wordType);

	boolean deleteWordType(WordType wordType);
}
