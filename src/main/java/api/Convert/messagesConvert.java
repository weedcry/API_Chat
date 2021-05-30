package api.Convert;

import java.util.ArrayList;
import java.util.List;
import api.DTO.messagesDTO;
import api.entity.channel_general;
import api.entity.messages;
import api.entity.user;
import api.service.userService;
import org.springframework.beans.factory.annotation.Autowired;

public class messagesConvert {

	public messages tomessages(messagesDTO mDTO) {
		channel_general chan = new channel_general();
		chan.setId(mDTO.getChannel_id());
		messages m = new messages();
		m.setId(mDTO.getId());
		m.setChannel_general(chan);
		m.setUser(mDTO.getUser());
		m.setContent(mDTO.getContent());
		m.setType(mDTO.getType());
		m.setStatus(mDTO.getStatus());
		if(m.getReply() == null) {
			mDTO.setReply(0);
		}
		m.setReply(mDTO.getReply());
		m.setDatetime(mDTO.getDatetime());
		return m;
	}
	
	
	public messagesDTO tomessagesDTO(messages m) {
		messagesDTO mDTO = new messagesDTO();
		mDTO.setId(m.getId());
		mDTO.setChannel_id(m.getChannel_general().getId());
		mDTO.setUser(m.getUser());
		mDTO.setContent(m.getContent());
		mDTO.setType(m.getType());
		mDTO.setStatus(m.getStatus());
		mDTO.setReply(m.getReply());				
		mDTO.setDatetime(m.getDatetime());
		System.out.println("mess - "+m.getDatetime());
		System.out.println("messDTO - "+mDTO.getDatetime());
		long millis=System.currentTimeMillis();
		java.util.Date date=new java.util.Date(millis);
		System.out.println(date);
		return mDTO;
	}
	
	public List<messagesDTO> listmessagesDTO(List<messages> listm){
		List<messagesDTO> list = new ArrayList<messagesDTO>();
		for (messages mess : listm) {
			list.add(tomessagesDTO(mess));
		}
		return list;
	}
	
	
}
