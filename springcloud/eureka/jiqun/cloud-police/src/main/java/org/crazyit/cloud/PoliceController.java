package org.crazyit.cloud;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoliceController {

	@RequestMapping(value = "/call/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Police call(@PathVariable Integer id, HttpServletRequest request) {
		Police p = new Police();
		p.setId(id);
		p.setName("angus");
		p.setMessage(request.getRequestURL().toString());
		return p;
	}
}
