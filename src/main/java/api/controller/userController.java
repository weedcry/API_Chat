package api.controller;


import api.DTO.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.entity.user;
import api.service.userService;


@RestController
@RequestMapping("/api/user")
public class userController {
	@Autowired
	userService userS;
	
	@GetMapping("")
	public ResponseEntity<Object> findByUserId(){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();

		}
		return new ResponseEntity<Object>(userS.findById(username),HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Object> finduser(@PathVariable String id){
		return new ResponseEntity<Object>(userS.findById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<Object> delete(){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();

		}
		return new ResponseEntity<Object>(userS.delete(username),HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody user u){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails){
			String username = ((UserDetails) principal).getUsername();
			if(u.getId().equals(username)){
				return new ResponseEntity<Object>(userS.update(u),HttpStatus.OK);
			}
		}
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("user not found!"));
	}

}
