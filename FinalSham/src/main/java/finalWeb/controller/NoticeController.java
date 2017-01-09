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

import finalWeb.command.NoticeCommand;
import finalWeb.service.NoticeService;


//�떆�닠 Q&A肄붾찘�듃
@Controller
public class NoticeController {

		@Autowired
		private NoticeService service; // CityServiceImpl媛앹껜瑜� 諛쏆븘�삩�떎. CityService
										// interface�씠誘�濡�

		public void setService(NoticeService service) {
			this.service = service;
		}

		@RequestMapping(value = "/noticelist.do")
		public String view(String pageNum, String search, @RequestParam(defaultValue = "0") int searchn, Model model)
				throws Throwable {
			System.out.println("/noticelist.do�슂泥�");

			if (pageNum == null) {
				pageNum = "1";
			}
			int pageSize = 5;// �븳 �럹�씠吏��쓽 湲��쓽 媛쒖닔
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage - 1) * pageSize + 1;// �븳 �럹�씠吏��쓽 �떆�옉湲� 踰덊샇
			int endRow = currentPage * pageSize;// �븳 �럹�씠吏��쓽 留덉�留� 湲�踰덊샇
			int count = 0;
			int number = 0;

			List<NoticeCommand> articleList = null;

			count = service.getArticleCount(search, searchn);

			if (count > 0) {
				articleList = service.getArticles(startRow, endRow, search, searchn);// �쁽�옱
																						// �럹�씠吏��뿉
																						// �빐�떦�븯�뒗
				for (NoticeCommand b : articleList) {
					System.out.println(b.getNoticeHits());
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

			return "noticeboard/list";// �빐�떦 酉�
		}

		@RequestMapping(value = "/noticeContent.do", method = RequestMethod.GET)
		public String requestPro(@RequestParam("noticeNo") int noticeNo, String pageNum, Model model) {

			NoticeCommand articleList = null;
			service.NoticeHits(noticeNo);
			articleList = service.getArticle(noticeNo);// �빐�떦 湲�踰덊샇�뿉 ���븳 �빐�떦 �젅肄붾뱶
			System.out.println("articleList::" + articleList.getNoticeHits());

			// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
			model.addAttribute("noticeNo", new Integer(noticeNo));
			model.addAttribute("pageNum", new Integer(pageNum));
			model.addAttribute("article", articleList);

			return "noticeboard/noticeContent";// �빐�떦 酉�
		}

		@RequestMapping(value = "/noticewriteForm.do")
		public String write(@ModelAttribute("board") NoticeCommand board) throws Throwable {
			System.out.println(board.getNoticeNo());
			return "noticeboard/writeForm";// �빐�떦 酉�
		}

		@RequestMapping(value = "/noticewritePro.do")
		public String writePro(NoticeCommand article, HttpServletRequest request) throws Throwable {

			System.out.println("article.getQnATitle():::" + article.getNoticeTitle());
			service.insert(article);
			return "noticeboard/writePro";
		}

		@RequestMapping(value = "/noticedeleteForm.do")
		public String delete(int noticeNo, String pageNum, Model model) throws Throwable {

			// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
			model.addAttribute("noticeNo", new Integer(noticeNo));
			model.addAttribute("pageNum", new Integer(pageNum));

			return "noticeboard/deleteForm";// �빐�떦酉�
		}

		@RequestMapping(value = "/noticedeletePro.do")
		public String deletePro(int noticeNo, String pageNum, String passwd, Model model) throws Throwable {

			int check = service.delete(noticeNo, passwd);

			// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("check", check);

			return "noticeboard/deletePro";// �빐�떦酉�
		}

		@RequestMapping(value = "/noticeupdateForm.do")
		public String update(int noticeNo, String pageNum, Model model) throws Throwable {

			NoticeCommand article = service.getArticle(noticeNo);

			// �빐�떦 酉곗뿉�꽌 �궗�슜�븷 �냽�꽦
			model.addAttribute("pageNum", new Integer(pageNum));
			model.addAttribute("article", article);

			return "noticeboard/updateForm";// �빐�떦酉�
		}

		@RequestMapping(value = "/noticeupdatePro.do")
		public String updatePro(NoticeCommand article, String pageNum, Model model) throws Throwable {

			int check = service.updateArticle(article, article.getPasswd());

			model.addAttribute("pageNum", pageNum);
			model.addAttribute("check", check);

			return "noticeboard/updatePro";
		}
}
