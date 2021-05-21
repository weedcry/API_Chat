package api.service;

import api.DTO.messagesDTO;
import api.entity.messages;
import api.entity.setting;
import api.entity.user;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.repository.friendRepository;

import java.util.List;
import api.entity.friend;
import api.DTO.userDTO;

@Service
@Component
public class friendService {
    @Autowired
    friendRepository friendRes;

    @Autowired
    userService userService;


    public List<friend> findfriendById(String id){
        ServiceResult result = new ServiceResult();
        List<friend> list = friendRes.findById(id);
        System.out.println(list.get(0).getId()
                +"-"+list.get(0).getUserfriend().getDate_create()
                +"-"+list.get(0).getUserfriend().getActive()
                +"-"+list.get(0).getUserfriend().getBirthday()
                +"-"+list.get(0).getUserfriend().getPhone());
        return list;
    }

    public Object inviteFriend(friend fri){
        ServiceResult result = new ServiceResult();
        friendRes.save(fri);
        result.setMessage("success");
        return result.getMessage();
    }

    public Object acceptFriend(friend fri){
        ServiceResult result = new ServiceResult();

        user u = (user) userService.findById(fri.getId());
        friend friendaccept = new friend(fri.getUserfriend().getId(),u,1,u.getActive());
        friendRes.save(friendaccept);
        friendRes.save(fri);
        result.setMessage("success");
        return result.getMessage();
    }

    public Object deletefriend(friend fri){
        ServiceResult result = new ServiceResult();
        friendRes.delete(fri);
        result.setMessage("success");
        return result.getMessage();
    }



}
