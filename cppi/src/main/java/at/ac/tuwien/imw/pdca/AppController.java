package at.ac.tuwien.imw.pdca;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class AppController {
	@RequestMapping(value="/{input}/{number}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String test(
			@PathVariable String input,
			@PathVariable int number) {
		    int numbers=number+1;
		    String result = "returning " + input + "." + numbers;
		return result;
	}
}
