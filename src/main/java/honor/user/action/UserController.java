package honor.user.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import honor.user.service.impl.UserServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserServiceImpl userService;
	
	@RequestMapping("/list")
	public String userList(HttpServletRequest reqeuest,Model model){
		
		System.out.println("1111111111111");
		
		
		return "/user/list";
	}

}
