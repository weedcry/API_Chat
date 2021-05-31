package api.controller;

import java.util.List;

import api.config.AmazonClient;
import api.entity.messages;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import api.DTO.messagesDTO;
import api.service.messagesService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/messages")
public class messagesController {
	@Autowired
	messagesService messagesS;


	private AmazonClient amazonClient;

	@Autowired
	messagesController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

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

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
		return this.amazonClient.uploadFile(file);
	}

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}

}
