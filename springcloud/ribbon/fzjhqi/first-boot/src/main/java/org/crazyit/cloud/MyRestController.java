package org.crazyit.cloud;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person getPerson(@PathVariable Integer id, HttpServletRequest request) {
		Person p = new Person();
		p.setId(id);
		p.setName("angus");
		p.setAge(30);
		p.setMessage(request.getRequestURL().toString());
		return p;
	}
}
