package api.controller;

import java.util.ArrayList;
import java.util.List;

import api.DTO.channelDTO;
import api.DTO.friendDTO;
import api.entity.friend;
import api.entity.user;
import api.service.friendService;
import api.service.messagesService;
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

    @Autowired
    messagesService messagesService;

    @Autowired
    channelService channelS;

    @Autowired
    friendService friendSer;

    @MessageMapping("/chat.sendMessage/{channelId}/{userId}")
    public void sendMessage(@Payload messagesDTO message,@DestinationVariable long channelId,@DestinationVariable String userId) {
        //them message vao db
        messagesService.create(message);

        System.out.println("Receive Message: "+ message.getContent());
        //tu channel_id gui tu client se tim dc cac user thuoc channel_id do

        listUser = userService.listuserbychannelid(channelId);
        System.out.println("Size" +listUser.size());
        for(int i=0;i<listUser.size();i++) {
//           if(listUser.get(i).getId().equals(userId)) continue;
            System.out.println("/message_receive/"+listUser.get(i).getId());
            String[] fn = listUser.get(i).getId().split("\\.");
            String username = fn[0];
            simpMessagingTemplate.convertAndSend("/message_receive/"+username,message);
        }




    }

    @MessageMapping("/chat.sendInvitefriend/{userId}")
    public void Invitefriendreceive(@Payload user friend,@DestinationVariable String userId){
      friend fri = (friend) friendSer.inviteFriendsocket(userId,friend);
      simpMessagingTemplate.convertAndSend("/friend_receive/"+friend.getId(),fri);
    }

    @MessageMapping("/chat.sendAcceptfriend/{userId}")
    public void Acceptfriendreceive(@Payload friendDTO Friend, @DestinationVariable String userId){
        friend fri = (friend) friendSer.acceptFriendSocket(userId,Friend);
        simpMessagingTemplate.convertAndSend("/friend_accept/"+Friend.getFriend().getId(),fri);
    }

    @MessageMapping("/chat.sendUnfriend/{userId}")
    public void Unfriendreceive(@Payload friendDTO Friend, @DestinationVariable String userId){
        String username = (String) friendSer.deletefriendSocket(Friend);
        simpMessagingTemplate.convertAndSend("/friend_un/"+username,userId);
    }

    @MessageMapping("/chat.sendupdatestatusmess/{userId}/{channel_id}")
    public void Unfriendreceive( @DestinationVariable String userId,@DestinationVariable long channel_id){
        messagesService.updateStatusMessages(channel_id);
        listUser = userService.listuserbychannelid(channel_id);
        for(userDTO u : listUser){
            if(!u.getId().equals(userId)){
                simpMessagingTemplate.convertAndSend("/updatestatusmess/"+u.getId(),channel_id);
                break;
            }
        }
    }

    @MessageMapping("/chat.deletachannel/{userId}/{channel_id}")
    public void DeleteChannel(@Payload channelDTO channelDTO, @DestinationVariable String userId, @DestinationVariable long channel_id){
        channelDTO cdto = (channelDTO) channelS.deletechannelfor2user(channelDTO);
        listUser = userService.listuserbychannelid(channel_id);
        for(userDTO u : listUser){
            if(!u.getId().equals(userId)){
                simpMessagingTemplate.convertAndSend("/deletachannel/"+u.getId(),cdto);
                break;
            }
        }

    }

}