package api.service;

import api.Convert.friendConvert;
import api.Convert.userConvert;
import api.DTO.MessageResponse;
import api.DTO.friendDTO;
import api.DTO.messagesDTO;
import api.entity.*;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.repository.friendRepository;

import java.util.ArrayList;
import java.util.List;

import api.DTO.userDTO;

@Service
@Component
public class friendService {
    @Autowired
    friendRepository friendRes;

    @Autowired
    userService userService;

    friendConvert friendConvert = new friendConvert();
    userConvert userConvert = new userConvert();

    public Object findfriendById(String id){
        ServiceResult result = new ServiceResult();
        List<friend> list = friendRes.findById(id);
        for(friend f : list){
            System.out.println("-"+f.getUserfriend().getId());
        }

        if(list == null){
            result.setMessage("userfriend not found");
            List<friendDTO> listDTO = new ArrayList<>();
            return  listDTO;
        }
        return friendConvert.listfriendDTO(list);
    }



    public Object inviteFriend(String username,user friend){
        ServiceResult result = new ServiceResult();
        user u =  userConvert.touser((userDTO) userService.findById(username));
        friendDTO fr = new friendDTO(username,friend,0,friend.getActive());
        friendDTO fr1 = new friendDTO(friend.getId(),u,2,u.getActive());
        try {
            friendRes.save(friendConvert.tofriend(fr));
            result.setData(friendRes.save(friendConvert.tofriend(fr1)));
        }catch (Exception e){

        }
        return result.getData();
    }

    public Object inviteFriendsocket(String username,user friend){
        ServiceResult result = new ServiceResult();
        user u =  userConvert.touser((userDTO) userService.findById(username));
        friendDTO fr = new friendDTO(username,friend,0,friend.getActive());
        friendDTO fr1 = new friendDTO(friend.getId(),u,2,u.getActive());
        try {
            friendConvert.tofriendDTO(friendRes.save(friendConvert.tofriend(fr)));
            result.setData(friendRes.save(friendConvert.tofriend(fr1)));
        }catch (Exception e){

        }
        return result.getData();
    }



    public Object acceptFriend(String username,friendDTO fr1){
        ServiceResult result = new ServiceResult();
        user u =  userConvert.touser((userDTO) userService.findById(username));
        friendDTO fr = new friendDTO(fr1.getFriend().getId(),u,1,u.getActive());
        fr1.setFriend_active(1);
        try {
            friendRes.save(friendConvert.tofriend(fr));
            friendRes.save(friendConvert.tofriend(fr1));
        }catch (Exception e){

        }
        MessageResponse mes = new MessageResponse("success");
        return mes;
    }

    public Object acceptFriendSocket(String username,friendDTO fr1){
        ServiceResult result = new ServiceResult();
        user u =  userConvert.touser((userDTO) userService.findById(username));
        friendDTO fr = new friendDTO(fr1.getFriend().getId(),u,1,u.getActive());
        fr1.setFriend_active(1);
        try {
           result.setData(friendRes.save(friendConvert.tofriend(fr)));
            friendRes.save(friendConvert.tofriend(fr1));
        }catch (Exception e){

        }
        return result.getData();
    }


    public Object deletefriend(friendDTO fri){
        ServiceResult result = new ServiceResult();
        user u =  userConvert.touser((userDTO) userService.findById(fri.getId()));
        friendDTO fr = new friendDTO(fri.getFriend().getId(),u,1,u.getActive());
        friendRes.delete(friendConvert.tofriend(fri));
        friendRes.delete(friendConvert.tofriend(fr));
        MessageResponse mes = new MessageResponse("success");
        return mes;
    }

    public Object deletefriendSocket(friendDTO fri){
        ServiceResult result = new ServiceResult();
        user u =  userConvert.touser((userDTO) userService.findById(fri.getId()));
        friendDTO fr = new friendDTO(fri.getFriend().getId(),u,1,u.getActive());
        friendRes.delete(friendConvert.tofriend(fri));
        friendRes.delete(friendConvert.tofriend(fr));
        return fri.getFriend().getId();
    }



}
