package api.controller;

import java.nio.channels.Channel;
import java.util.List;

import api.DTO.MessageResponse;
import api.DTO.messagesDTO;
import api.DTO.userDTO;
import api.entity.channel;
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
		List<channelDTO> list = (List<channelDTO>)channelS.findByAuthor_id(username);
		if(list.size() != 0){
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("channel not found");
	}

	@GetMapping("/find/{friendid}")
	public ResponseEntity<Object> findchannelbyfriendId(@PathVariable String friendid) {
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		friendid = friendid+".com";

		channelDTO channel = (api.DTO.channelDTO) channelS.findchannelbyfriendId(username,friendid);
		if(channel.getId() != 0){
			return new ResponseEntity<Object>(channel,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("channel not found");
	}


	
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
		channelDTO cDTO = (channelDTO )channelS.create(username,friendid);
		if(cDTO != null){
			return ResponseEntity.status(HttpStatus.CREATED).body(cDTO);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
	}

    @PostMapping("/create/group")
    public ResponseEntity<Object> creategroup(@RequestBody List<userDTO> list){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return new ResponseEntity<Object>(channelS.creategroup(username,list),HttpStatus.CREATED);
    }


}
