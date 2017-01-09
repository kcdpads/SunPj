package finalWeb.service;

import java.util.List;

import org.springframework.stereotype.Controller;

import finalWeb.command.NoticeCommand;
@Controller
public interface NoticeService {
	public List<NoticeCommand> getArticles(int startRow,int endRow,String search, int searchn);
	public NoticeCommand getArticle(int qnANo);
	public int getArticleCount(String search, int searchn);
	public int insert(NoticeCommand article);
	public int delete(int qnANo, String passwd);
	public int updateArticle(NoticeCommand article,String passwd);
	public void NoticeHits(int qnANo);
}
