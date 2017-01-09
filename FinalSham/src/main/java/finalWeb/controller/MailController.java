package finalWeb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
import finalWeb.service.*;

@Controller
public class MailController {
    @Autowired
    private EmailSender emailSender;
     
    @RequestMapping("/send")
    public ModelAndView sendEmailAction () throws Exception {

        String reciver = "poreutopi@gmail.com"; //��������� �̸����Դϴ�.
        String subject = "�̸��� ����";
        String content = "�̸��� �����Դϴ�.";
         

        emailSender.SendEmail(subject,content,reciver);
         
        return new ModelAndView("success");
    }
}
