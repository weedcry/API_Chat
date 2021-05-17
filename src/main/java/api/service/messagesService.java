package api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.Convert.messagesConvert;
import api.DTO.messagesDTO;
import api.entity.messages;
import api.repository.messagesRepository;

@Service
public class messagesService {
	@Autowired
	messagesRepository messagesRes;
	
//	@Autowired
//	private messagesConvert messagesConv;
	messagesConvert messagesConv = new messagesConvert();
	
	public List<messagesDTO> findByChannel(Long channel_id){
		List<messages> list = messagesRes.findByChannel_General(channel_id);
		for (messages messages : list) {
			System.out.println(messages.getContent());
		}				
		return messagesConv.listmessagesDTO(list);
	}
	
	public Object create(messagesDTO mDTO) {			
		messagesRes.save(messagesConv.tomessages(mDTO));		
		return mDTO;
	}
	
	// chưa update 
	public Object update(messagesDTO mDTO) {	
		ServiceResult result = new ServiceResult();	
		messages messagesn = messagesRes.findById(mDTO.getId(),mDTO.getChannel_id());
		if(messagesn == null) {
			result.setMessage("messages not found");
			return result.getMessage();
		}
		messagesRes.save(messagesConv.tomessages(mDTO));
		return mDTO;
	}
}
