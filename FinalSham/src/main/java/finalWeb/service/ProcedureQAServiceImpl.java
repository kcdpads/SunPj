package finalWeb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import finalWeb.command.ProcedureQACommand;
import finalWeb.dao.ProcedureQADao;

@Controller
public class ProcedureQAServiceImpl implements ProcedureQAService {
	
	@Autowired
	private ProcedureQADao dao;

	public void setDao(ProcedureQADao dao) {
		this.dao = dao;
	}

	public List<ProcedureQACommand> getArticles(int startRow,int endRow,String search,int searchn) {
		List<ProcedureQACommand> list = null;
		
		if(search == null || search.equals(""))
			list = dao.getArticles(startRow, endRow);
		else
			list = dao.getArticles(startRow, endRow, searchn, search);
	

		return list;
	}
	
	public ProcedureQACommand getArticle(int pdqNo) {
		ProcedureQACommand list = null;
		System.out.println(pdqNo);
		try {                        //city는 mepper파일의 id값에 해당한다.
			list = dao.getArticle(pdqNo);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return list;
	}
	
	public int getArticleCount(String search, int searchn){
		int count = 0;
		if(search == null || search.equals(""))
			count = dao.getArticleCount();
		else
			count = dao.getArticleCount(searchn,search);
		
		return count;
	}
 
//	public int insert(ProcedureQACommand article) {
//		int check =0;
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		
//	
//		
//		int num=article.getNum();
//		int ref=article.getRef();
//		int re_step=article.getRe_step();
//		int re_level=article.getRe_level();
//		String number=null;
//		int number1=0;
//		
//		number=dao.getMax();
//		
//		if(number!=null)
//			number1=dao.getMax1();
//		
//		if(number!=null){
//		number1 += 1;	
//		}
//		else{
//			number1 = 1;
//		}
//		
//		if(num!=0){
//			dao.updateRef(article);
//			re_step += 1;
//			re_level = re_level+1;
//		}else{
//			ref=number1;
//			re_step=0;
//			re_level=0;
//		}
//		
//		try {
//			System.out.println(article.getpdqContent());
//			map.put("writer", article.getWriter());
//			map.put("subject", article.getSubject());
//			map.put("passwd", article.getPasswd());
//			map.put("reg_date", article.getReg_date());
//			map.put("ref", ref);
//			map.put("re_step", re_step);
//			map.put("re_level", re_level);
//			map.put("pdqContent", article.getpdqContent());	
//			check = dao.Insert("performReview.insert", map);  //city는 내가 선택한 도시명
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//
//		return check;
//	}

	public int insert(ProcedureQACommand article) {
		int check =0;
		
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("styleName : "+article.getStyleName());
		
	
		
		int pdqNo=article.getPdqNo();
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		String number=null;
		int number1=0;
		
		number=dao.getMax();
		
		if(number!=null)
			number1=dao.getMax1();
		
		if(number!=null){
		number1 += 1;	
		}
		else{
			number1 = 1;
		}
		
		if(pdqNo!=0){
			dao.updateRef(article);
			re_step += 1;
			re_level = re_level+1;
		}else{
			ref=number1;
			re_step=0;
			re_level=0;
		}
		
		try {
			System.out.println(article.getPdqContent());
			System.out.println(article.getRe_level());
			System.out.println(article.getRe_step());
			map.put("writer", article.getWriter());
			map.put("pdqTitle", article.getPdqTitle());
			map.put("passwd", article.getPasswd());
			map.put("pdqDate", article.getPdqDate());
			map.put("ref", ref);
			map.put("re_step", re_step);
			map.put("re_level", re_level);
			map.put("pdqContent", article.getPdqContent());	
			
			article.setRe_step(re_step);
			article.setRe_level(re_level);
			article.setRef(ref);
			check = dao.Insert("performReview.insert", article); 
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return check;
	}
	
	
	public int delete(int pdqNo, String passwd) {
		int list;
		
		String check = dao.deletecheck(pdqNo);
		
		if(check.equals(passwd)){
		
		try {
			list = dao.delete(pdqNo);  //city는 내가 선택한 도시명
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return 1;
		}
		else{
			return 0;
		}
		
	}
	
	
	public int updateArticle(ProcedureQACommand article,String passwd){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String pwcheck = dao.deletecheck(article.getPdqNo());
		
		if(pwcheck.equals(article.getPasswd())){
		
		map.put("pdqNo",article.getPdqNo());
		map.put("writer",article.getWriter());
		map.put("pdqTitle", article.getPdqTitle());
		map.put("passwd", article.getPasswd());
		map.put("pdqContent", article.getPdqContent());
		
		System.out.println("map:::"+map.get("writer"));
		dao.update(map);
		
		System.out.println("updateArticle입니다");
		return 1;
		}
		
		else
			return 0;
		
	}
	
	public void PdqHits(int pdqNo){
		System.out.println("pdqHits()실행");
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("pdqNo", pdqNo);
		dao.PdqHits(pdqNo);
	}

}
