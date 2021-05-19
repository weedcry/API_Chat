package api.service;

import api.DTO.messagesDTO;
import api.entity.messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.repository.friendRepository;

import java.util.List;
import api.entity.friend;

@Service
@Component
public class friendService {
    @Autowired
    friendRepository friendRes;


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



}