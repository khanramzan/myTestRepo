package rams.app.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Petri Kainulainen
 */
@Controller
public class HomeController {

	@Resource
	private Environment environment;
	
	private static final String VALUE = "rams.app.main.title";
    protected static final String HOME_VIEW = "home";
    private String propsValue;
	
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name,String another,String myVal, Model model) {
    	
    	
    	String myStr = "This is the <h1>Test</h1> and I intend to do it this way";
    	myVal= environment.getRequiredProperty(VALUE);
    	
    	
    	model.addAttribute("another",myStr);
    	
        model.addAttribute("name", name);
         model.addAttribute("myVal",myVal);
        
        return HOME_VIEW;
    }
    
    
    
}