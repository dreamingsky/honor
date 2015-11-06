package honor.test.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/test")
public class TestController {
	
	
	
	@RequestMapping(value="/")
	public String TestMethod(HttpServletRequest request){
		
		
		return "/test/test";
				
	}

}
