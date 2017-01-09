package finalWeb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finalWeb.command.NoticeCommand;

@Component
public class NoticeDao{ // sql�꽭�뀡�뙥�넗留� 二쇱엯諛쏆쑝硫�
														// SqlSessionDaoSupport
														// �궗�슜媛��뒫
	@Autowired
	SqlSession session;
	
	public List<NoticeCommand> getArticles(int startRow, int endRow) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<NoticeCommand> getArticles =session.selectList("Notice.getArticles", map);
		System.out.println(getArticles.size());
		return getArticles;
	}

	public int getArticleCount() {
		int count =session.selectOne("Notice.getArticleCount", Integer.class); // cityMapper																		// �떎�뻾
		return count;
	}

	public NoticeCommand getArticle(int noticeNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("noticeNo", noticeNo);
		NoticeCommand getArticle =session.selectOne("Notice.getArticle", map);
		return getArticle;
	}

//	public int Insert(String string, Map<String, Object> list) {
//		int check =session.insert(string, list);
//		return check;
//	}

	public int Insert(String string, NoticeCommand list) {
		int check =session.insert(string, list);
		return check;
	}
	public String deletecheck(int noticeNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("noticeNo", noticeNo);

		String passwd =session.selectOne("Notice.deletepw", map);

		return passwd;
	}

	public int delete(int noticeNo) {
		int check =session.delete("Notice.delete", noticeNo);
		return check;
	}

	public int update(Map<String, Object> map) {
		session.update("Notice.update", map);
		return 1;
	}

	public String getMax() {
		String number =session.selectOne("Notice.getMax");
		return number;
	}

	public int getMax1() {
		
		int number =session.selectOne("Notice.getMax1");
		
		return number;
	}

	public void updateRef(NoticeCommand article) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ref", article.getRef());
		map.put("re_step", article.getRe_step());
		session.update("Notice.updateRef", map);

	}

	public void NoticeHits(int noticeNo) {
		int i =session.update("Notice.noticeHits", noticeNo);
	}

	public int getArticleCount(int n, String searchKeyword){
		int x = 0;
		Map map = new HashMap<String,Object>();	
		map.put("searchN", n);
		map.put("searchKeyword", searchKeyword);
		x =session.selectOne("Notice.getArticleCount", Integer.class); // cityMapper		
		return x;
	}

	public List getArticles(int start, int end, int n, String searchKeyword){
		Map map = new HashMap<String,Object>();
		map.put("startRow", start);
		map.put("endRow", end);
		map.put("searchN", n);
		map.put("searchKeyword", searchKeyword);
		
		List<NoticeCommand> list =session.selectList("Notice.searchgetArticles",map);
		return list;
	}
	
	
	/////////////////////////////////////
	
}