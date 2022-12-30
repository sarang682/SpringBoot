package chap6.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import chap6.model.ArticleVO;
import chap6.model.ContentVO;
import chap6.model.MemberVO;
import chap6.repository.ArticleDAO;
import chap6.repository.MemberDAO;



@Controller
public class ArticleController {

	@Autowired
	ArticleDAO dao;
	
	@Autowired
	MemberDAO mdao;
	
	@RequestMapping("/ex2/list")
	public String listArticles(Model model) {
		List<ArticleVO> articles=dao.getAllArticles();
		model.addAttribute("articles",articles);
		return "listArticles";
	}
	
	@RequestMapping(value="/ex2/write", method=RequestMethod.GET)
	public String writeReq(Model model) {
		
		return "writeForm";
	}
	
	@RequestMapping(value="/ex2/write", method=RequestMethod.POST)
	public String writePorcessing(ArticleVO article,String content) {
		dao.addArticle(article);
		ContentVO contentVO=new ContentVO(article.getArticle_no(),content);
		dao.addArticleContent(contentVO);
		return "redirect:list";
	}
	
	@RequestMapping("/ex2/read")
	public String readReq(@RequestParam String aid,Model model) {
		ArticleVO article=dao.getArticleByAid(aid);
		MemberVO member=mdao.getMember(Integer.toString(article.getWriter()));
		String content=dao.getContentByAid(aid);
		dao.updateReadCnt(aid);
		model.addAttribute("member", member);
		model.addAttribute("article", article);
		model.addAttribute("content", content);
		return "readForm";
	}
	
	@RequestMapping(value="/ex2/update",method=RequestMethod.POST)
	public String updateProcessing(ArticleVO article,String content,Model model) {
		dao.updateArticle(article);
		dao.updateContent(content,article.getArticle_no());
		model.addAttribute("title", "실행 완료");
		model.addAttribute("content", "실행 메세지:정상적으로 수정되었습니다.");
		model.addAttribute("link", "list");
		model.addAttribute("linkname", "목록");
		return "notice";
	}
	
	@RequestMapping(value="/ex2/update",method=RequestMethod.GET)
	public String updateReq(@RequestParam String aid,Model model,HttpSession session) {
		
		ArticleVO article=dao.getArticleByAid(aid);
		
		/////
		MemberVO member=(MemberVO) session.getAttribute("login");
		if(member==null||member.getId()!=article.getWriter()) {
			model.addAttribute("title", "오류발생");
			model.addAttribute("content", "오류 메세지:수정할 권한이 없습니다.");
			model.addAttribute("link", "list");
			model.addAttribute("linkname", "목록");
			return "notice";
		}else {
			String content=dao.getContentByAid(aid);
			model.addAttribute("article", article);
			model.addAttribute("content", content);
			return "updateForm";
		}
		
		

	}
	
	@RequestMapping(value="/ex2/delete",method=RequestMethod.GET)
	public String delete(@RequestParam String aid,Model model,HttpSession session) {
		ArticleVO article=dao.getArticleByAid(aid);
	/////
			MemberVO member=(MemberVO) session.getAttribute("login");
			if(member==null||member.getId()!=article.getWriter()) {
				model.addAttribute("title", "오류발생");
				model.addAttribute("content", "오류 메세지:삭제할 권한이 없습니다.");
				model.addAttribute("link", "list");
				model.addAttribute("linkname", "목록");
				return "notice";
			}else {
				//삭제
				dao.deleteArticle(article.getArticle_no());
				dao.deleteContent(article.getArticle_no());
				return "redirect:list";
			}
		
	}
	
	@RequestMapping("/ex2/logout")
	public String logout(HttpSession session) {
		MemberVO member=(MemberVO) session.getAttribute("login");
		session.invalidate();
		return "redirect:list";
	}
}
