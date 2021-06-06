package api.controller;


import api.DTO.FileResponse;
import api.DTO.MessageResponse;
import api.DTO.userDTO;
import api.config.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import api.entity.user;
import api.service.userService;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/user")
public class userController {
	@Autowired
	userService userS;

	private AmazonClient amazonClient;

	@Autowired
	userController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}
	
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
		id = id+".com";
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
	public ResponseEntity<Object> update(@RequestBody userDTO u){
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

	@PostMapping("/uploadAvatar")
	public ResponseEntity<Object> uploadAvatar(@RequestPart(value = "file") MultipartFile file) {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		return  new ResponseEntity<Object>(amazonClient.uploadAvatar(file,username),HttpStatus.OK);
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<FileResponse>uploadFile(@RequestPart(value = "file") MultipartFile file) {
		return new ResponseEntity<FileResponse>(amazonClient.uploadFile(file),HttpStatus.OK);
	}




}
