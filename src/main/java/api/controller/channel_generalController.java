package api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.DTO.channel_generalDTO;
import api.service.channel_generalService;

@RestController
@RequestMapping("/channel_general")
public class channel_generalController {
	@Autowired
	channel_generalService channel_generalS;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findByUserId(@PathVariable long id){
		return new ResponseEntity<Object>(channel_generalS.findById(id),HttpStatus.OK);	
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable long id){
		return new ResponseEntity<Object>(channel_generalS.delete(id),HttpStatus.OK);	
	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody channel_generalDTO chanDTO){	
		return new ResponseEntity<Object>(channel_generalS.update(chanDTO),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody channel_generalDTO chanDTO){
		return new ResponseEntity<Object>(channel_generalS.create(chanDTO),HttpStatus.CREATED);
	}
}
