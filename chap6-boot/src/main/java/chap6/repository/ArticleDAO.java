package chap6.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import chap6.model.ArticleVO;
import chap6.model.ContentVO;


@Mapper
public interface ArticleDAO {
	
	public List<ArticleVO> getAllArticles();
	
	public int addArticle(ArticleVO article);
	
	public int addArticleContent(ContentVO content);
	
	public ArticleVO getArticleByAid(String article_no);
	
	public String getContentByAid(String article_no);
	
	public int updateReadCnt(String article_no);

	public int updateArticle(ArticleVO article);

	public int updateContent(String content,int article_no);
	
	public int deleteArticle(int article_no);
	
	public int deleteContent(int article_no);
}
