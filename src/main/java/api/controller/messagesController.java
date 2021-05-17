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

import api.DTO.messagesDTO;
import api.service.messagesService;

@RestController
@RequestMapping("/api/messages")
public class messagesController {
	@Autowired
	messagesService messagesS;

	@GetMapping("/{channel_id}")
	public ResponseEntity<List<messagesDTO>> findByChannel(@PathVariable long channel_id){
		return new ResponseEntity<List<messagesDTO>>(messagesS.findByChannel(channel_id),HttpStatus.OK);	
	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody messagesDTO mDTO){
		return new ResponseEntity<Object>(messagesS.update(mDTO),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody messagesDTO mDTO){
		return new ResponseEntity<Object>(messagesS.create(mDTO),HttpStatus.CREATED);
	}
}
