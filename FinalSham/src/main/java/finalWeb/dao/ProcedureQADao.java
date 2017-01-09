package finalWeb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finalWeb.command.ProcedureQACommand;

@Component
public class ProcedureQADao{ // sql�꽭�뀡�뙥�넗留� 二쇱엯諛쏆쑝硫�
														// SqlSessionDaoSupport
														// �궗�슜媛��뒫
	@Autowired
	SqlSession session;
	
	public List<ProcedureQACommand> getArticles(int startRow, int endRow) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<ProcedureQACommand> getArticles =session.selectList("performReview.getArticles", map);
		System.out.println(getArticles.size());
		return getArticles;
	}

	public int getArticleCount() {
		int count =session.selectOne("performReview.getArticleCount", Integer.class); // cityMapper																		// �떎�뻾
		return count;
	}

	public ProcedureQACommand getArticle(int pdqNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pdqNo", pdqNo);
		ProcedureQACommand getArticle =session.selectOne("performReview.getArticle", map);
		return getArticle;
	}

//	public int Insert(String string, Map<String, Object> list) {
//		int check =session.insert(string, list);
//		return check;
//	}

	public int Insert(String string, ProcedureQACommand list) {
		int check =session.insert(string, list);
		
		//
		return check;
	}
	
	public String deletecheck(int pdqNo) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pdqNo", pdqNo);

		String passwd =session.selectOne("performReview.deletepw", map);

		return passwd;
	}

	public int delete(int pdqNo) {
		int check =session.delete("performReview.delete", pdqNo);
		return check;
	}

	public int update(Map<String, Object> map) {
		session.update("performReview.update", map);
		return 1;
	}

	public String getMax() {
		String number =session.selectOne("performReview.getMax");
		return number;
	}

	public int getMax1() {
		
		int number =session.selectOne("performReview.getMax1");
		
		return number;
	}

	public void updateRef(ProcedureQACommand article) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("ref", article.getRef());
		map.put("re_step", article.getRe_step());
		session.update("performReview.updateRef", map);

	}

	public void PdqHits(int pdqNo) {
		int i =session.update("performReview.pdqHits", pdqNo);
	}

	public int getArticleCount(int n, String searchKeyword){
		int x = 0;
		Map map = new HashMap<String,Object>();	
		map.put("searchN", n);
		map.put("searchKeyword", searchKeyword);
		x =session.selectOne("performReview.getArticleCount", Integer.class); // cityMapper		
		return x;
	}

	public List getArticles(int start, int end, int n, String searchKeyword){
		Map map = new HashMap<String,Object>();
		map.put("startRow", start);
		map.put("endRow", end);
		map.put("searchN", n);
		map.put("searchKeyword", searchKeyword);
		
		List<ProcedureQACommand> list =session.selectList("performReview.searchgetArticles",map);
		return list;
	}
	
	
	/////////////////////////////////////
	
}