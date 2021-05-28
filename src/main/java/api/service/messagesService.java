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
	
	public List<messagesDTO> findByChannel(Long channel_id){
		List<messages> list = messagesRes.findByChannel_General(channel_id);
		for (messages messages : list) {
			System.out.println(messages.getContent());
		}

		return messagesConv.listmessagesDTO(list);
	}

	public messagesDTO findMessageByChannel(Long channel_id){
		messages messages = messagesRes.findMessageByChannel_General(channel_id);
		return messagesConv.tomessagesDTO(messages);
	}

	public Object create(messagesDTO mDTO) {
		System.out.println("mdto - "+mDTO.getDatetime());
		ServiceResult result = new ServiceResult();
		messagesRes.save(messagesConv.tomessages(mDTO));
		result.setMessage("success");
		return result.getMessage();
	}

	public Object createmess(messages m ) {
		System.out.println("m  - "+m.getDatetime());
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
