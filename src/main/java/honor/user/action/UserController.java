package honor.user.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import honor.common.Page;
import honor.model.User;
import honor.user.service.impl.UserServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserServiceImpl userService;
	
	@RequestMapping("/list")
	public String userList(HttpServletRequest request,User user){
		
		Page page = userService.getUserListByPage(user);
		request.setAttribute("page",page);
		return "/user/list";
	}
	
	@RequestMapping("/one")
	public String findUser(HttpServletRequest request,User user){
		
		user = userService.getUserByNameAndPass(user.getLoginName(),user.getPassword());
		request.setAttribute("user",user);
		return "/user/list";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void addUser(HttpServletRequest request,User user){
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
