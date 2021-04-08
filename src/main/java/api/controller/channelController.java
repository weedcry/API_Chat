package api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.DTO.channelDTO;
import api.entity.channel;
import api.service.channelService;

@RestController
@RequestMapping("/channel")
public class channelController {
	@Autowired
	channelService channelS;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<List<channel>> findById(@PathVariable long id){
		return new ResponseEntity<List<channel>>(channelS.findById(id),HttpStatus.OK);	
	}	
	
	@GetMapping("/user/{user_id}")
	public ResponseEntity<List<channelDTO>> findByUser(@PathVariable String user_id){
		return new ResponseEntity<List<channelDTO>>(channelS.findByUser(user_id),HttpStatus.OK);	
	}
	
	
//	@GetMapping("/{id}/{user_id}")
//	public ResponseEntity<List<channel>> findByUserId(@PathVariable long id,@PathVariable String user_id){
//		return new ResponseEntity<List<channel>>(channelS.findById(id,user_id),HttpStatus.OK);	
//	}	
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Object> delete(@PathVariable long id){
//		return new ResponseEntity<Object>(channelS.delete(id),HttpStatus.OK);	
//	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody channelDTO uDTO){	
		return new ResponseEntity<Object>(channelS.update(uDTO),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody channelDTO uDTO){
		return new ResponseEntity<Object>(channelS.create(uDTO),HttpStatus.CREATED);
	}
}
