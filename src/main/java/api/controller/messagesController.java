package api.controller;

import java.util.List;

import api.entity.messages;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	public ResponseEntity<Object> findByChannel(@PathVariable long channel_id){
		return new ResponseEntity<Object>(messagesS.findByChannel(channel_id),HttpStatus.OK);
	}

	@GetMapping("/last/{channel_id}")
	public ResponseEntity<Object> findMessageByChannel(@PathVariable long channel_id){
		return new ResponseEntity<Object>(messagesS.findMessageByChannel(channel_id),HttpStatus.OK);
	}

	@GetMapping("/last")
	public ResponseEntity<Object> findLastMessageByUserid(){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();

		}
		return new ResponseEntity<Object>(messagesS.findLastMessageChannel(username),HttpStatus.OK);
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
