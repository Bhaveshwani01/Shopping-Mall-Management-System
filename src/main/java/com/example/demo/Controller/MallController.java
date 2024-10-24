package com.example.demo.Controller;

import java.util.List;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.*;
import com.example.demo.Entity.Mall;

@RestController
public class MallController {
	@Autowired
	private MallService service;

	// RESTful API methods for Retrieval operations
	@GetMapping("/mall")
	public List<Mall> list() {
		return service.listAll();
	}

	@GetMapping("/mall/{id}")
	public ResponseEntity<Mall> get(@PathVariable Integer id)

	{
		try {
			Mall mall = service.get(id);
			return new ResponseEntity<Mall>(mall, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Mall>(HttpStatus.NOT_FOUND);
		}
	}

	// RESTful API method for Create operation
	@PostMapping("/mall")
	public void add(@RequestBody Mall mall) {
		service.save(mall);
	}

	// RESTful API method for Update operation
	@PutMapping("/mall/{id}")
	public ResponseEntity<?> update(@RequestBody Mall mall, @PathVariable Integer id) {
		try {
			Mall existMall = service.get(id);
			if (existMall!=null)
			{
				service.save(mall);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// RESTful API method for Delete operation
	@DeleteMapping("/mall/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			Mall existMall = service.get(id);
			if (existMall!=null)
			{
				service.delete(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
