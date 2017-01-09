package finalWeb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import finalWeb.command.NoticeCommand;
import finalWeb.command.PriceCommand;
import finalWeb.dao.PriceDao;

//가격표
@Controller
public class PriceController {
@Autowired
private PriceDao dao;
@RequestMapping(value = "/price.do")
public String price(ModelMap model) throws Exception {

	@SuppressWarnings("rawtypes")
	List<Map> priceList = dao.selectPriceList();

	model.addAttribute("priceList", priceList);

	System.out.println(priceList);
	return "content/price";
}

@RequestMapping(value = "/writePrice.do")
public String writePrice(ModelMap model, PriceCommand priceCommand) throws Exception {

/*	dao.insertPrice(priceCommand);
*/
	return "content/pricewrite";
}

@RequestMapping(value = "/writePricePro.do")
public String writePricePro(ModelMap model, PriceCommand priceCommand) throws Exception {
	
	System.out.println("1번");
	dao.insertPrice(priceCommand);
    
	return "redirect:/price.do";
}

@RequestMapping(value = "/updatePrice.do")
public String update(int priceNo,Model model) throws Throwable {
	PriceCommand article = dao.selectPriceList(priceNo);
	
	model.addAttribute("article", article);

	return "content/updateForm";// �빐�떦酉�
}

@RequestMapping(value = "/deletePrice.do")
public String delete(int priceNo, Model model) throws Throwable {
		System.out.println("priceNo:::"+priceNo);
	// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
	model.addAttribute("priceNo", new Integer(priceNo));
	return "deleteProPrice.do";
}

@RequestMapping(value = "/deleteProPrice.do")
public String deletePro(int priceNo,Model model) throws Throwable {
	
	int check = dao.delete(priceNo);

	model.addAttribute("check", check);
	//model.addAttribute("styleNum", styleNum);
	return "redirect:/price.do";
}

}
