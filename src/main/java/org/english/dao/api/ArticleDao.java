package org.english.dao.api;

import java.util.List;

import org.english.form.Article;

public interface ArticleDao {
	boolean addArticle(Article article);
	public Article getWordById(int id);

	public List<Article> getArticleByKeyWordPage(String keyword, int page,
			int pageSize);
	
	public long getArticleCount(String keyword);

	boolean updateArticle(Article article);

	boolean deleteArticle(Article article);
}
