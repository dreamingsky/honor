package honor.site.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/manager/site")
public class SiteAction {

	
	
	@RequestMapping("/impress")
	public String Impress(HttpServletRequest request){
		
		
		
		return "/site/impress/impress";
	}
	
}
