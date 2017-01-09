package finalWeb.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import finalWeb.command.ProcedureQACommand;
import finalWeb.service.ProcedureQAService;

//�떆�닠 Q&A肄붾찘�듃
@Controller
public class ProcedureQAController {

	@Autowired
	private ProcedureQAService service; // CityServiceImpl媛앹껜瑜� 諛쏆븘�삩�떎.
										// CityService
	// interface�씠誘�濡�

	public void setService(ProcedureQAService service) {
		this.service = service;
	}

	@RequestMapping(value = "/pdqlist.do")
	public String view(String pageNum, String search, @RequestParam(defaultValue = "0") int searchn, Model model, String styleNum,String styleName)
			throws Throwable {
		System.out.println("/pdqlist.do�슂泥�");

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;// �븳 �럹�씠吏��쓽 湲��쓽 媛쒖닔
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;// �븳 �럹�씠吏��쓽 �떆�옉湲�
														// 踰덊샇
		int endRow = currentPage * pageSize;// �븳 �럹�씠吏��쓽 留덉�留� 湲�踰덊샇
		int count = 0;
		int number = 0;

		List<ProcedureQACommand> articleList = null;

		count = service.getArticleCount(search, searchn);

		if (count > 0) {
			articleList = service.getArticles(startRow, endRow, search, searchn);// �쁽�옱
																					// �럹�씠吏��뿉
																					// �빐�떦�븯�뒗
			for (ProcedureQACommand b : articleList) {
				System.out.println(b.getPdqHits());
			} // 湲� 紐⑸줉
		} else {
			articleList = Collections.EMPTY_LIST;
		}

		number = count - (currentPage - 1) * pageSize;// 湲�紐⑸줉�뿉 �몴�떆�븷 湲�踰덊샇
		// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
		model.addAttribute("currentPage", new Integer(currentPage));
		model.addAttribute("startRow", new Integer(startRow));
		model.addAttribute("endRow", new Integer(endRow));
		model.addAttribute("count", new Integer(count));
		model.addAttribute("pageSize", new Integer(pageSize));
		model.addAttribute("number", new Integer(number));
		model.addAttribute("articleList", articleList);

		return "redirect:/styleInfo.do?styleNum="+styleNum;// �빐�떦 酉�
	}

	@RequestMapping(value = "/pdqContent.do", method = RequestMethod.GET)
	public String requestPro(@RequestParam("pdqNo") int pdqNo, String pageNum,String styleNum ,Model model) {

		ProcedureQACommand articleList = null;
		service.PdqHits(pdqNo);
		articleList = service.getArticle(pdqNo);// �빐�떦 湲�踰덊샇�뿉 ���븳 �빐�떦 �젅肄붾뱶
		System.out.println("articleList::" + articleList.getPdqHits());

		// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
		System.out.println("requestPro styleNum "+styleNum);
		model.addAttribute("pdqNo", new Integer(pdqNo));
		model.addAttribute("pageNum", new Integer(pageNum));
		model.addAttribute("styleNum", styleNum);
		model.addAttribute("article", articleList);

		return "pdqboard/pdqContent";// �빐�떦 酉�
	}

	@RequestMapping(value = "/pdqwriteForm.do")
	public String write(@ModelAttribute("board") ProcedureQACommand board, String styleNum, String styleName, Model model) throws Throwable {		
		System.out.println(board.getPdqNo());
		System.out.println("styleName write"+styleName);
		System.out.println("write"+styleNum);
		model.addAttribute("styleNum", styleNum);
		model.addAttribute("styleName", styleName);
		return "pdqboard/writeForm";// �빐�떦 酉�
	}

	@RequestMapping(value = "/pdqwritePro.do")
	public String writePro(ProcedureQACommand article, HttpServletRequest request, String styleNum,String styleName) throws Throwable {
		System.out.println("styleName writePro"+styleName);
		System.out.println("getRe_level"+article.getRe_level());
		System.out.println("getRe_level"+article.getRe_step());
		System.out.println("article.getQnATitle():::" + article.getPdqTitle());
		service.insert(article);
		return "redirect:/styleInfo.do?styleNum="+styleNum;
	}

	@RequestMapping(value = "/pdqdeleteForm.do")
	public String delete(int pdqNo, String pageNum, Model model, String styleNum) throws Throwable {

		// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
		System.out.println("deleteform styleNum"+styleNum);
		model.addAttribute("pdqNo", new Integer(pdqNo));
		model.addAttribute("pageNum", new Integer(pageNum));
		model.addAttribute("styleNum", styleNum);
		return "pdqboard/deleteForm";// �빐�떦酉�
	}

	@RequestMapping(value = "/pdqdeletePro.do")
	public String deletePro(int pdqNo, String pageNum, String passwd, Model model, String styleNum) throws Throwable {
		System.out.println("delete styleNum"+styleNum);
		int check = service.delete(pdqNo, passwd);
		System.out.println("지움");
		// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("check", check);
		//model.addAttribute("styleNum", styleNum);
		return "redirect:/styleInfo.do?styleNum="+styleNum;// �빐�떦酉�
	}

	@RequestMapping(value = "/pdqupdateForm.do")
	public String update(int pdqNo, String pageNum,String styleNum, Model model) throws Throwable {
		System.out.println("update styleNum "+styleNum);
		ProcedureQACommand article = service.getArticle(pdqNo);

		// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
		model.addAttribute("pageNum", new Integer(pageNum));
		model.addAttribute("styleNum", styleNum);
		model.addAttribute("article", article);

		return "pdqboard/updateForm";// �빐�떦酉�
	}

	@RequestMapping(value = "/pdqupdatePro.do")
	public String updatePro(ProcedureQACommand article, String pageNum, Model model,String styleNum) throws Throwable {
		System.out.println("updatePro styleNum "+styleNum);
		System.out.println("article.getPdqNo():::"+article.getPdqNo());
		System.out.println("article.getwriter():::"+article.getWriter());
		int check = service.updateArticle(article, article.getPasswd());

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("check", check);
		
		return "redirect:/styleInfo.do?styleNum="+styleNum;
		/*return "redirect:/styleInfo.do?styleNum="+article.getPdqNo();*/
	}

/*	@RequestMapping(value = "/insertSelectSeq")
	public void insertSelectSeq(StyleCommand styleCommand) {

		try {
			styleCommand.setSeqtitle("insert styleCommand");
			System.out.println("등록전 idx 값 :" + styleCommand.getStyleNum());
			dao.insertSelectSeq(styleCommand);
			System.out.println("등록후 idx 값 :" + styleCommand.getStyleNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
