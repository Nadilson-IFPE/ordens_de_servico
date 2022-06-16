package com.nadilson.os.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nadilson.os.dtos.OSDTO;
import com.nadilson.os.services.OSService;

@RestController
@RequestMapping(value = "/os")
public class OSResource {

	@Autowired
	private OSService osService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
		OSDTO obj = new OSDTO(osService.findById(id));
		return ResponseEntity.ok().body(obj);
	}

}
