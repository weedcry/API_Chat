package api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import api.DTO.messagesDTO;
import api.service.channelService;
import api.service.userService;
import api.DTO.userDTO;




@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private List<userDTO> listUser =new ArrayList<userDTO>();
    @Autowired
    userService userService;

    @MessageMapping("/chat.sendMessage/{channelId}/{userId}")
    public void sendMessage(@Payload messagesDTO message,@DestinationVariable long channelId,@DestinationVariable String userId) {
        //them message vao db

        System.out.println("Receive Message: "+ message.getContent());
        //tu channel_id gui tu client se tim dc cac user thuoc channel_id do

        listUser = userService.listuserbychannelid(channelId);
        System.out.println("Size" +listUser.size());
        for(int i=0;i<listUser.size();i++) {
            if(listUser.get(i).getId().equals(userId)) continue;
            System.out.println("/message_receive/"+listUser.get(i).getId());
            simpMessagingTemplate.convertAndSend("/message_receive/"+listUser.get(i).getId(),message);
        }




    }
}