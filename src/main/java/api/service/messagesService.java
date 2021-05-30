package api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.Convert.messagesConvert;
import api.DTO.messagesDTO;
import api.entity.messages;
import api.repository.messagesRepository;

@Service
@Component
public class messagesService {
	@Autowired
	messagesRepository messagesRes;
	
//	@Autowired
//	private messagesConvert messagesConv;
	messagesConvert messagesConv = new messagesConvert();
	
	public Object findByChannel(Long channel_id){
		ServiceResult result = new ServiceResult();
		List<messages> list = messagesRes.findByChannel_General(channel_id);
		if(list == null){
			result.setMessage("messages not found");
			return  result.getMessage();
		}
		return messagesConv.listmessagesDTO(list);
	}

	public Object findMessageByChannel(Long channel_id){
		ServiceResult result = new ServiceResult();
		messages messages = messagesRes.findMessageByChannel_General(channel_id);
		if(messages == null){
			result.setMessage("messages not found");
			return  result.getMessage();
		}
		return messagesConv.tomessagesDTO(messages);
	}

	public Object findLastMessageChannel(String userid){
		ServiceResult result = new ServiceResult();
		List<messages> list = messagesRes.findLastMessageByUserid(userid);
		if(list == null){
			result.setMessage("messages not found");
			return  result.getMessage();
		}
		return messagesConv.listmessagesDTO(list);
	}


	public Object create(messagesDTO mDTO) {
		ServiceResult result = new ServiceResult();
//		result.setMessage("success");
		result.setData(messagesConv.tomessagesDTO(messagesRes.save(messagesConv.tomessages(mDTO))));
		return result.getData();
	}

	public Object createmess(messages m ) {
		ServiceResult result = new ServiceResult();
		messagesRes.save(m);
		result.setMessage("success");
		return result.getMessage();
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
