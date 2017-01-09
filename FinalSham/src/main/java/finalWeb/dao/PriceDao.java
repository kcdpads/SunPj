package finalWeb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import finalWeb.command.NoticeCommand;
import finalWeb.command.PriceCommand;

//가격표
@Component
public class PriceDao {
	@Autowired
	SqlSession session;

	@SuppressWarnings("rawtypes")
	public List<Map> selectPriceList() {
		// TODO Auto-generated method stub
		return session.selectList("price.selectpriceList");
	}

	public void insertPrice(PriceCommand pricecommand) {
		// TODO Auto-generated method stub
		System.out.println(pricecommand.getPrice());
		System.out.println(pricecommand.getPriceGender());
		System.out.println(pricecommand.getPriceName());
		System.out.println(pricecommand.getPriceType());
		
		session.insert("price.insertprice", pricecommand);
	}

	public int update(PriceCommand article) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("priceNo",article.getPriceNo());
		map.put("priceGender",article.getPriceGender());
		map.put("priceType", article.getPriceType());
		map.put("priceName", article.getPriceName());
		map.put("price", article.getPrice());
		
		session.update("price.update", map);
		
		System.out.println("updateArticle입니다");
		return 1;
	}
	
	public int delete(int priceNo) {
		int check =session.delete("price.delete", priceNo);
		return check;
	}

	public PriceCommand selectPriceList(int priceNo) {
		PriceCommand list = null;
		return list;
	}
	
	}


