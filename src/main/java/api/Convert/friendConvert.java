package api.Convert;


import api.DTO.channelDTO;
import api.DTO.friendDTO;
import api.entity.channel;
import api.entity.friend;

import java.util.ArrayList;
import java.util.List;

public class friendConvert {

    public friendDTO tofriendDTO(friend f){
        friendDTO fDTO = new friendDTO();
        fDTO.setId(f.getUserfriend().getId());
        fDTO.setFriend(f.getUserfriend().getFriend());
        fDTO.setFriend_active(f.getFriend_active());
        fDTO.setActive(f.getActive());
        return fDTO;
    }

    public friend tofriend(friendDTO fDTO){
       friend f = new friend();
       friend.userfriend uf = new friend.userfriend();
       uf.setId(fDTO.getId());
       uf.setFriend(fDTO.getFriend());
        f.setUserfriend(uf);
        f.setFriend_active(fDTO.getFriend_active());
        f.setActive(fDTO.getActive());
        return f;
    }


    public List<friendDTO> listfriendDTO(List<friend> listc){
        List<friendDTO> list = new ArrayList<friendDTO>();
        for (friend f : listc) {
            list.add(tofriendDTO(f));
        }
        return list;
    }



}
