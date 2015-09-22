package honor.base.action;

import honor.model.User;
import honor.user.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
	private UserServiceImpl userService;
	
	@RequestMapping("/")
	public String toLogin(HttpServletRequest request,User user,Model model){
		
		
		System.out.println("1111111111");
		model.addAttribute("user",user);
		return "login";
		
	}
	
	@RequestMapping("/main")
	public String login(HttpServletRequest request,User user,Model model){
		
		User userFind = userService.getUserByNameAndPass(user.getLoginName(),user.getPassword());
//		if(userFind==null){
//			return "login";
//		}
		model.addAttribute("user",user);
		
		return "/main/index";
		
	}

}
