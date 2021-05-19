package api.controller;

import api.DTO.messagesDTO;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api.service.friendService;

import java.util.List;
import api.entity.friend;

@RestController
@RequestMapping("/api/friend")
public class friendController {
    @Autowired
    friendService friendSer;

    @GetMapping("")
    public ResponseEntity<List<friend>> findByChannel(){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        return new ResponseEntity<List<friend>>(friendSer.findfriendById(username), HttpStatus.OK);
    }



}
