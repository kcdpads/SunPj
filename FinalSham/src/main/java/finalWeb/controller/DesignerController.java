package finalWeb.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import finalWeb.command.DesignerCommand;
import finalWeb.dao.DesignerDao;

@Controller
public class DesignerController {

	@Autowired
	private DesignerDao designerDao;

	@RequestMapping(value = "/designer.do")
	public String designer(ModelMap model) throws Exception {

		List<Map> designerList = designerDao.selectDesignerList();

		model.addAttribute("designerList", designerList);

		System.out.println(designerList);
		return "content/designer";
	}

	@RequestMapping(value = "/addDesignerForm.do")
	public String addDesignerForm(ModelMap model) throws Exception {

		return "content/addDesignerForm";
	}

	@RequestMapping(value = "/addDesigner.do")
	public String addDesigner(DesignerCommand designerCommand,
			@RequestParam(value = "photo", required = false) MultipartFile photo) throws Exception {

		String originalFilename = photo.getOriginalFilename();
		String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
		String extension = originalFilename.substring(originalFilename.indexOf("."));
		String fullPath = "C:\\Users\\user2\\Documents\\workspace-sts-3.8.2.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FinalSham\\build\\img\\designer"
				+ "\\" + onlyFileName + extension;

		// ���Ѱ��� ��������ִ� �ڵ�
		if (!photo.isEmpty()) {
			try {
				File file = new File(fullPath);
				System.out.println("file::::" + file);
				photo.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		designerCommand.setDesignerPhoto("build/img/designer/" + onlyFileName + extension);
		designerDao.insertDesigner(designerCommand);
		return "redirect:/designer.do";
	}

	@RequestMapping(value = "/designerSubmit.do")
	public String designerSubmit(DesignerCommand designerCommand) throws Exception {

		designerDao.deleteDesignerAll(designerCommand);
		designerDao.insertDesignerAll(designerCommand);

		return "redirect:/designer.do";
	}

	@RequestMapping(value = "/updateDesignerForm.do")
	public String updateDesignerForm(DesignerCommand designerCommand, Model model) throws Exception {
		System.out.println("111");
	    System.out.println(designerCommand.getDesignerNo());
		model.addAttribute("designerInfo",designerDao.selectDesigner(designerCommand));
		return "content/updateDesignerForm";
	}

	@RequestMapping(value = "/updateDesignerSubmit.do")
	public String updateDesignerSubmit(DesignerCommand designerCommand, Model model, @RequestParam(value = "photo", required = false) MultipartFile photo) throws Exception {

		String originalFilename = photo.getOriginalFilename();
		System.out.println(originalFilename);
		String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
		String extension = originalFilename.substring(originalFilename.indexOf("."));
		String fullPath = "C:\\Users\\user2\\Documents\\workspace-sts-3.8.2.RELEASE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\FinalSham\\build\\img\\designer"
				+ "\\" + onlyFileName + extension;

		// ���Ѱ��� ��������ִ� �ڵ�
		if (!photo.isEmpty()) {
			try {
				File file = new File(fullPath);
				System.out.println("file::::" + file);
				photo.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		designerCommand.setDesignerPhoto("build/img/designer/" + onlyFileName + extension);
		designerDao.updateDesigner(designerCommand);
		
		return "content/updateDesignerForm";
	}
	
	
}
