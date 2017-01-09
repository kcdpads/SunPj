package finalWeb.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import finalWeb.command.ProcedureQACommand;
import finalWeb.command.StyleCommand;
import finalWeb.dao.StyleDao;
import finalWeb.service.ProcedureQAService;


@Controller
public class StyleController {

	@Autowired
	private StyleDao styleDao;
	@Autowired
	private ProcedureQAService service;

	public void setStyleDao(StyleDao styleDao) {
		this.styleDao = styleDao;
	}

	@ModelAttribute("style")
	public StyleCommand interceptor() {
		return new StyleCommand();
	}

	@RequestMapping("/mTotalList.do")
	public ModelAndView mtotalList() {

		ModelAndView mav = new ModelAndView("style/mTotalList", "style", styleDao.getStyle());
		return mav;
	}

	@RequestMapping("/mCutList.do")
	public ModelAndView cutList() {
		
		ModelAndView mav = new ModelAndView("style/mCutList", "style", styleDao.getStyle());
		return mav;
	}
	
	@RequestMapping("/mPermList.do")
	public ModelAndView permList() {
		
		ModelAndView mav = new ModelAndView("style/mPermList", "style", styleDao.getStyle());
		return mav;
	}
	
	@RequestMapping("/mColorList.do")
	public ModelAndView colorList() {
		
		ModelAndView mav = new ModelAndView("style/mColorList", "style", styleDao.getStyle());
		return mav;
	}
	
	@RequestMapping("/wTotalList.do")
	public ModelAndView wtotalList() {
		
		ModelAndView mav = new ModelAndView("style/wTotalList", "style", styleDao.getStyle());
		return mav;
	}
	
	@RequestMapping("/wLongList.do")
	public ModelAndView longList() {
		
		ModelAndView mav = new ModelAndView("style/wLongList", "style", styleDao.getStyle());
		return mav;
	}
	
	@RequestMapping("/wMediumList.do")
	public ModelAndView mediumList() {
		
		ModelAndView mav = new ModelAndView("style/wMediumList", "style", styleDao.getStyle());
		return mav;
	}
	
	@RequestMapping("/wShortList.do")
	public ModelAndView shortList() {
		
		ModelAndView mav = new ModelAndView("style/wShortList", "style", styleDao.getStyle());
		return mav;
	}

	@RequestMapping(value = "/insertStyleForm.do", method = RequestMethod.GET)
	public String insertStyleForm(ModelMap model) {
		
		List<String> list = new ArrayList<String>();
		
		list = styleDao.getDesigner();
		
		model.addAttribute("designerList", list);
		
		return "style/insertStyleForm";
	}

	@RequestMapping(value = "/insertStylePro.do", method = RequestMethod.POST)
	public String insertStylePro(ModelMap model, @RequestParam("photo") MultipartFile photo,
			@RequestParam("photo2") List<MultipartFile> photo2, @RequestParam("video") List<String> video,
			@ModelAttribute("style") StyleCommand styleCommand) {

		// ���몴�궗吏�
		String originalFilename = photo.getOriginalFilename();
		String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
		String extension = originalFilename.substring(originalFilename.indexOf(".")); // �솗�옣�옄
		String fullPath = "C:\\Users\\user2\\git\\Sham\\FinalSham\\WebContent\\fileList" + "\\" + onlyFileName
				+ extension;
		String fullName = onlyFileName + extension;

		// �씠 �쇅�쓽 �궗吏�
		List<String> originalFilename2 = new ArrayList<String>();
		List<String> onlyFileName2 = new ArrayList<String>();
		List<String> extension2 = new ArrayList<String>();
		List<String> fullPath2 = new ArrayList<String>();
		List<String> fullName2 = new ArrayList<String>();

		String setName = fullName + ",";

		for (int i = 0; i < photo2.size(); i++) {
			originalFilename2.add(photo2.get(i).getOriginalFilename());
		}

		for (int i = 0; i < originalFilename2.size(); i++) {
			onlyFileName2.add(originalFilename2.get(i).substring(0, originalFilename2.get(i).indexOf(".")));
			extension2.add(originalFilename2.get(i).substring(originalFilename2.get(i).indexOf(".")));
			fullPath2.add("C:\\Users\\user2\\git\\Sham\\FinalSham\\WebContent\\styleImages\\" + onlyFileName2.get(i)
					+ extension2.get(i));
			fullName2.add(onlyFileName2.get(i) + extension2.get(i));

			setName += fullName2.get(i) + ",";
		}

		styleCommand.setStylePhoto(setName);
		styleCommand.setFileCount(photo2.size() + 1);

		if (!photo.isEmpty()) {
			try {
				File file = new File(fullPath);
				photo.transferTo(file);// 寃쎈줈濡� 蹂대궡湲�
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!(photo2.size() == 0)) {
			try {
				for (int i = 0; i < photo2.size(); i++) {

					File file = new File(fullPath2.get(i));
					photo2.get(i).transferTo(file);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// �쁺�긽
		String fullVideo = "";
		styleCommand.setVideoCount(video.size());

		for (int i = 0; i < video.size(); i++) {
			fullVideo += video.get(i) + ",";
		}

		styleCommand.setStyleVideo(fullVideo);

		styleDao.insertStyle(styleCommand);

		model.addAttribute("type", styleCommand.getStyleType());
		
		return "style/insertStylePro";
	}

	
	@RequestMapping(value = "/styleInfo.do", method = RequestMethod.GET)
	public String styleInfo(String pageNum,String search,@RequestParam(defaultValue = "0") int searchn, ModelMap model, String styleNum) {
		System.out.println("styleNum:::"+styleNum);
		StyleCommand styleCommand = null;

		// 占쏙옙占쏙옙
		styleCommand = styleDao.getStyleInfo(styleNum);
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
		
		String str = "";
		String photoList = ""; // 첫占쏙옙째 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙

		str = styleCommand.getStylePhoto(); // 占쏙옙占썩선 占쏙옙체 占쏙옙占쏙옙 占쏙옙占쏙옙
		photoList = str.substring(str.indexOf(",") + 1, str.length()); // 첫占쏙옙째 占쏙옙占쏙옙
																		// 占쏙옙 占쏙옙占쏙옙

		str = str.substring(0, str.indexOf(",")); // 첫占쏙옙째 占쏙옙占쏙옙 占쏙옙占싱몌옙 占쏙옙占쌔쇽옙 占쏙옙占쏙옙
		styleCommand.setStylePhoto(str); // 첫占쏙옙째 占쏙옙占쏙옙占쏙옙占싱몌옙 占쌕쏙옙 占쏙옙체占쏙옙 占쏙옙占쏙옙

		int photoCount = styleCommand.getFileCount() - 1;// 占쏙옙표占쏙옙占쏙옙(첫占쏙옙째 占쏙옙占쏙옙) 占쏙옙 占쏙옙占쏙옙占쏙옙
															// 占쏙옙占쏙옙

		List<String> photo = new ArrayList<String>();

		int x = 0;

		for (int i = 0; i < photoCount; i++) {

			photo.add(photoList.substring(x, photoList.indexOf(",")));

			x = photoList.indexOf(",") + 1;
			photoList = photoList.substring(x, photoList.length());
			x = 0;
		}

		// 占쏙옙占쏙옙占쏙옙
		List<String> video = new ArrayList<String>();
		int videoCount = styleCommand.getVideoCount();
		String videoList = styleCommand.getStyleVideo();
		int y = 0;

		for (int i = 0; i < videoCount; i++) {
			video.add(videoList.substring(y, videoList.indexOf(",")));

			y = videoList.indexOf(",") + 1;
			videoList = videoList.substring(y, videoList.length());
			y = 0;
		}

		// if (video.get(0) == "") {
		// video.removeAll(video);
		// }
		// System.out.println("d"+video.get(0));
		// System.out.println(video.size());

		model.addAttribute("style", styleCommand);
		model.addAttribute("photoList", photo);
		model.addAttribute("videoList", video);

		
		//////////////////////////
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 5;// �븳 �럹�씠吏��쓽 湲��쓽 媛쒖닔
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;// �븳 �럹�씠吏��쓽 �떆�옉湲� 踰덊샇
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
		return "style/styleInfo";
	}

	@RequestMapping(value = "/updateStyleForm.do", method = RequestMethod.GET)
	public String updateStyleForm(ModelMap model, String styleNum) {

		StyleCommand styleCommand = null;
		styleCommand = styleDao.getStyleInfo2(styleNum);

		String str = "";
		String photoList = "";

		photoList = styleCommand.getStylePhoto(); // 占쏙옙占썩선 占쏙옙체 占쏙옙占쏙옙 占쏙옙占쏙옙
		// photoList = str.substring(str.indexOf(",")+1, str.length());
		str = photoList.substring(0, photoList.indexOf(",")); // 첫占쏙옙째 占쏙옙占쏙옙 占쏙옙占싱몌옙 占쏙옙占쌔쇽옙
																// 占쏙옙占쏙옙

		styleCommand.setStylePhoto(str); // 첫占쏙옙째 占쏙옙占쏙옙占쏙옙占싱몌옙 占쌕쏙옙 占쏙옙체占쏙옙 占쏙옙占쏙옙

		int photoCount = styleCommand.getFileCount();// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙

		List<String> photo = new ArrayList<String>();

		int x = 0;

		for (int i = 0; i < photoCount; i++) {

			photo.add(photoList.substring(x, photoList.indexOf(",")));

			x = photoList.indexOf(",") + 1;
			photoList = photoList.substring(x, photoList.length());
			x = 0;
		}

		// // 占쏙옙占쏙옙占쏙옙
		// List<String> video = new ArrayList<String>();
		// int videoCount = styleCommand.getVideoCount();// 占쏙옙占쏙옙 占쏙옙占쏙옙
		// String videoList = styleCommand.getStyleVideo();// 占쏙옙占쏙옙 占쏙옙占쏙옙
		// int y = 0;
		//
		// for (int i = 0; i < videoCount; i++) {
		// video.add(videoList.substring(y, videoList.indexOf(",")));
		//
		// y = videoList.indexOf(",") + 1;
		// videoList = videoList.substring(y, videoList.length());
		// y = 0;
		// }

		model.addAttribute("style", styleCommand);
		model.addAttribute("photoList", photo);
		// model.addAttribute("videoList", video);

		return "style/updateStyleForm";
	}

	@RequestMapping(value = "/updateStylePro.do", method = RequestMethod.POST)
	public String updateStylePro(ModelMap model, @RequestParam("photo") MultipartFile photo,
			@RequestParam("photo2") List<MultipartFile> photo2, @RequestParam("video") List<String> video,
			@RequestParam("strphoto") List<String> strphoto, @ModelAttribute("style") StyleCommand styleCommand) {

		// 占쏙옙표 占쏙옙占쏙옙
		String originalFilename = photo.getOriginalFilename();
		String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
		String extension = originalFilename.substring(originalFilename.indexOf(".")); // 확占쏙옙占쏙옙
		String fullPath = "C:\\Users\\user2\\git\\Sham\\FinalSham\\WebContent\\fileList" + "\\" + onlyFileName
				+ extension;
		String fullName = onlyFileName + extension;

		// 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
		List<String> originalFilename2 = new ArrayList<String>();
		List<String> onlyFileName2 = new ArrayList<String>();
		List<String> extension2 = new ArrayList<String>();
		List<String> fullPath2 = new ArrayList<String>();
		List<String> fullName2 = new ArrayList<String>();

		String setName = fullName + ",";

		for (int i = 0; i < photo2.size(); i++) {
			originalFilename2.add(photo2.get(i).getOriginalFilename());
		}

		for (int i = 0; i < originalFilename2.size(); i++) {
			onlyFileName2.add(originalFilename2.get(i).substring(0, originalFilename2.get(i).indexOf(".")));
			extension2.add(originalFilename2.get(i).substring(originalFilename2.get(i).indexOf(".")));
			fullPath2.add("C:\\Users\\user2\\git\\Sham\\FinalSham\\WebContent\\styleImages\\" + onlyFileName2.get(i)
					+ extension2.get(i));
			fullName2.add(onlyFileName2.get(i) + extension2.get(i));

			setName += fullName2.get(i) + ",";
		}


		if (!(strphoto.get(0).equals("strphoto"))) {

			styleCommand.setFileCount(photo2.size() + strphoto.size() + 1);

			for (int i = 0; i < strphoto.size(); i++) {
				setName += strphoto.get(i) + ",";
			}
		} else {
			styleCommand.setFileCount(photo2.size() + 1);
		}

		styleCommand.setStylePhoto(setName);

		if (!photo.isEmpty()) {
			try {
				File file = new File(fullPath);
				photo.transferTo(file);// 占쌔댐옙 占쏙옙管占� 占싱듸옙~
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!(photo2.size() == 0)) {
			try {
				for (int i = 0; i < photo2.size(); i++) {

					File file = new File(fullPath2.get(i));
					photo2.get(i).transferTo(file);// 占쌔댐옙 占쏙옙管占� 占싱듸옙~
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 占쏙옙占쏙옙占쏙옙
		String fullVideo = "";
		styleCommand.setVideoCount(video.size());

		for (int i = 0; i < video.size(); i++) {
			fullVideo += video.get(i) + ",";
		}

		styleCommand.setStyleVideo(fullVideo);
		styleDao.updateStyle(styleCommand);

		model.addAttribute("type", styleCommand.getStyleType());

		return "style/updateStylePro";
	}

	@RequestMapping(value = "/deleteStyleForm.do", method = RequestMethod.GET)
	public String deleteStyleForm(ModelMap model, String styleNum, String styleType) {

		styleDao.deleteStyle(styleNum);
		
		model.addAttribute("type", styleType);

		return "style/deleteStyleForm";
	}
}