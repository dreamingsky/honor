package honor.base.action;

import honor.user.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Login {
	
	@Resource
	private UserServiceImpl userService;
	
	@RequestMapping("/main")
	public String login(HttpServletRequest request,Model model){
		
		
		System.out.println("1111111111");
		
		
		return "/main/main";
		
	}

}
