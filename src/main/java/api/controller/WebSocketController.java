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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        messagesDTO mDTO = (messagesDTO) messagesService.create(message);

        System.out.println("Receive Message: "+ message.getContent());
        //tu channel_id gui tu client se tim dc cac user thuoc channel_id do

        listUser = userService.listuserbychannelid(channelId);
        System.out.println("Size" +listUser.size());
        for(int i=0;i<listUser.size();i++) {
//           if(listUser.get(i).getId().equals(userId)) continue;
            System.out.println("/message_receive/"+listUser.get(i).getId());
            String[] fn = listUser.get(i).getId().split("\\.");
            String username = fn[0];
            simpMessagingTemplate.convertAndSend("/message_receive/"+username,mDTO);
        }




    }

    @MessageMapping("/chat.sendinvitefriend/{userId}")
    public void Invitefriendreceive(@Payload user friend,@DestinationVariable String userId){
        friend fri = (friend) friendSer.inviteFriendsocket(userId,friend);
        String[] fn = friend.getId().split("\\.");
        String username = fn[0];
        simpMessagingTemplate.convertAndSend("/receiveinvitefriend/"+username,fri);
    }

    @MessageMapping("/chat.sendacceptfriend/{userId}")
    public void Acceptfriendreceive(@Payload friendDTO Friend, @DestinationVariable String userId){
        friend fri = (friend) friendSer.acceptFriendSocket(userId,Friend);
        String[] fn = Friend.getFriend().getId().split("\\.");
        String username = fn[0];
        simpMessagingTemplate.convertAndSend("/receiveacceptfriend/"+username,fri);
    }

    @MessageMapping("/chat.sendunfriend/{userId}")
    public void Unfriendreceive(@Payload friendDTO Friend, @DestinationVariable String userId){
        String u = (String) friendSer.deletefriendSocket(Friend);
        String[] fn = u.split("\\.");
        String username = fn[0];
        simpMessagingTemplate.convertAndSend("/receiveunfriend/"+username,userId);
    }

    @MessageMapping("/chat.sendupdatestatusmess/{userId}/{channel_id}")
    public void updatestatusmessreceive( @DestinationVariable String userId,@DestinationVariable long channel_id){
        messagesService.updateStatusMessages(channel_id);
        listUser = userService.listuserbychannelid(channel_id);
        for(userDTO u : listUser){
            if(!u.getId().equals(userId)){
                String[] fn = u.getId().split("\\.");
                String username = fn[0];
                simpMessagingTemplate.convertAndSend("/receiveupdatestatusmess/"+username,channel_id);
                break;
            }
        }
    }

    @MessageMapping("/chat.deletemessages/{userId}")
    public void Deletemessages(@Payload messagesDTO mDTO, @DestinationVariable String userId){
        messagesDTO messagesDTO = (api.DTO.messagesDTO)messagesService.update(mDTO);
        listUser = userService.listuserbychannelid(mDTO.getChannel_id());
        for(userDTO u : listUser){
            if(!u.getId().equals(userId)){
                String[] fn = u.getId().split("\\.");
                String username = fn[0];
                simpMessagingTemplate.convertAndSend("/receivedeletemessages/"+username,mDTO);
                break;
            }
        }

    }


    @MessageMapping("/chat.deletechannel/{userid}")
    public void DeleteChannel(@Payload channelDTO chanDTO, @DestinationVariable String userid){
        listUser = userService.listuserbychannelid(chanDTO.getId());
        channelDTO cDTO = (channelDTO) channelS.deletechannelfor2user(chanDTO);
        for(userDTO u : listUser){
            if(!u.getId().equals(userid)){
                String[] fn = u.getId().split("\\.");
                String username = fn[0];
                simpMessagingTemplate.convertAndSend("/receivedeletechannel/"+username,cDTO);
                break;
            }
        }

    }

    @MessageMapping("/chat.createchannel/{userid}/{friendid}")
    public void CreateChannel(@DestinationVariable String userid, @DestinationVariable String friendid){
        channelDTO cDTO = (channelDTO )channelS.create(userid,friendid);
        listUser = userService.listuserbychannelid(cDTO.getId());
        for(userDTO u : listUser){
            String[] fn = u.getId().split("\\.");
            String username = fn[0];
//                     simpMessagingTemplate.convertAndSend("/receivechannel/"+username,cDTO);
        }
    }

    @MessageMapping("/chat.creategroup/{userid}")
    public void CreateGroup(@Payload List<userDTO> list,@DestinationVariable String userid){
        simpMessagingTemplate.convertAndSend("/receivegroup/"+userid,userid);
//         List<channelDTO> listchanDTO = channelS.creategroupsocket(userid,list);
//         for (channelDTO chan : listchanDTO){
//             String[] fn = chan.getAuthor_id().split("\\.");
//             String username = fn[0];
//             simpMessagingTemplate.convertAndSend("/receivegroup/"+username,chan);
//         }
    }


    public void a(){

        System.out.println("alo");
    }


}
