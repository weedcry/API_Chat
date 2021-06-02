package api.controller;

import java.util.List;

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

import api.DTO.channelDTO;
import api.service.channelService;

@RestController
@RequestMapping("/api/channel")
public class channelController {
	@Autowired
	channelService channelS;

	
	@GetMapping("/user")
	public ResponseEntity<Object> findByAuthorid(){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		return new ResponseEntity<Object>(channelS.findByAuthor_id(username),HttpStatus.OK);
	}

	@GetMapping("/{friendid}")
	public ResponseEntity<Object> findchannelbyfriendId(@PathVariable String friendid) {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		return new ResponseEntity<Object>(channelS.findchannelbyfriendId(username,friendid),HttpStatus.OK);
	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<Object> delete(@PathVariable long id){
//		return new ResponseEntity<Object>(channelS.delete(id),HttpStatus.OK);	
//	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody channelDTO cDTO){
		return new ResponseEntity<Object>(channelS.update(cDTO),HttpStatus.OK);
	}
	
	@PostMapping("/create/{friendid}")
	public ResponseEntity<Object> create(@PathVariable String friendid){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		return new ResponseEntity<Object>(channelS.create(username,friendid),HttpStatus.CREATED);
	}
}
