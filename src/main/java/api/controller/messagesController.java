package api.controller;

import java.io.File;
import java.util.List;

import api.DTO.FileResponse;
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
		List<messagesDTO> list = (List<messagesDTO>)messagesS.findByChannel(channel_id);
		if(list.size() != 0){
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("messages not found");
	}

	@GetMapping("/last/{channel_id}")
	public ResponseEntity<Object> findMessageByChannel(@PathVariable long channel_id){
		List<messagesDTO> list = (List<messagesDTO>)messagesS.findMessageByChannel(channel_id);
		if(list.size() != 0){
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("messages not found");
	}

	@GetMapping("/last")
	public ResponseEntity<Object> findLastMessageByUserid(){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();

		}
		List<messagesDTO> list = (List<messagesDTO>)messagesS.findLastMessageChannel(username);
		if(list.size() != 0){
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("messages not found");
	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody messagesDTO mDTO){
		messagesDTO messagesDTO = (api.DTO.messagesDTO)messagesS.update(mDTO);
		if(messagesDTO != null){
			return new ResponseEntity<Object>(messagesDTO,HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody messagesDTO mDTO){
		messagesDTO messagesDTO = (api.DTO.messagesDTO)messagesS.create(mDTO);
		if(messagesDTO != null){
			return new ResponseEntity<Object>(messagesDTO,HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<FileResponse>uploadFile(@RequestPart(value = "file") MultipartFile file) {
		return new ResponseEntity<FileResponse>(amazonClient.uploadFile(file),HttpStatus.OK);
	}

	@DeleteMapping("/deleteFile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}

}
