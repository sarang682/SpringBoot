package chap6.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chap6.model.MemberVO;
import chap6.repository.MemberDAO;


@Controller
@RequestMapping("/ex2/login")
public class LoginController {
	@Autowired
	MemberDAO dao;
	
	@RequestMapping(method=RequestMethod.GET)
	public String loginReq(Model model) {
		return "loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String loginProcessing(HttpServletRequest req, Model model) {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		HashMap<String, Boolean> errors=new HashMap<>();
		if(email==null||email.isEmpty())
			errors.put("email",true);
		if(password==null||password.isEmpty())
			errors.put("password", true);
		
		if(!errors.isEmpty()) {
			model.addAttribute("email",email);
			model.addAttribute("errors",errors);
			return "loginForm";
		}
		
		MemberVO member=dao.getMemberByEmail(email);
		if(member==null)
			errors.put("notfound", true);
		else if(!member.getPassword().equals(password)) {
			errors.put("mismatch", true);
			model.addAttribute("title", "오류발생");
			model.addAttribute("content", "오류 메세지:암호가 일치하지 않습니다.");
			model.addAttribute("link", "login");
			model.addAttribute("linkname", "로그인");
			return "notice";
		}
			
		
		if(!errors.isEmpty()) {
			model.addAttribute("errors",errors);
			return "loginForm";
		}
		
		HttpSession session=req.getSession();
		session.setAttribute("login",member);
		return "redirect:list";
		
	}
}
