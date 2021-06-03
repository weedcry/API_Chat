package api.controller;

import api.DTO.friendDTO;
import api.DTO.messagesDTO;
import api.DTO.userDTO;
import api.entity.user;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import api.service.friendService;

import java.util.List;
import api.entity.friend;

@RestController
@RequestMapping("/api/friend")
public class friendController {
    @Autowired
    friendService friendSer;

    @GetMapping("")
    public ResponseEntity<Object> findFriendById(){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return new ResponseEntity<Object>(friendSer.findfriendById(username), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> sendInviteFriend(@RequestBody user friend) {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return new ResponseEntity<Object>(friendSer.inviteFriend(username,friend),HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Object> sendAcceptFriend(@RequestBody friendDTO fri) {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return new ResponseEntity<Object>(friendSer.acceptFriend(username,fri),HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> sendDeleteFriend(@RequestBody friend fri) {
        return new ResponseEntity<Object>(friendSer.deletefriend(fri),HttpStatus.OK);
    }


}
