package api.service;

import java.util.ArrayList;
import java.util.List;

import api.DTO.MessageResponse;
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
			List<messagesDTO> listDTO = new ArrayList<>();
			return listDTO  ;
		}
		return messagesConv.listmessagesDTO(list);
	}

	public Object findMessageByChannel(Long channel_id){
		ServiceResult result = new ServiceResult();
		messages messages = messagesRes.findMessageByChannel_General(channel_id);
		if(messages == null){
			result.setMessage("messages not found");
			List<messagesDTO> listDTO = new ArrayList<>();
			return listDTO  ;
		}
		return messagesConv.tomessagesDTO(messages);
	}

	public Object findLastMessageChannel(String userid){
		ServiceResult result = new ServiceResult();
		List<messages> list = messagesRes.findLastMessageByUserid(userid);
		if(list == null){
			result.setMessage("messages not found");
			List<messagesDTO> listDTO = new ArrayList<>();
			return listDTO  ;
		}
		return messagesConv.listmessagesDTO(list);
	}


	public Object create(messagesDTO mDTO) {
		ServiceResult result = new ServiceResult();
		try {
			result.setData(messagesConv.tomessagesDTO(messagesRes.save(messagesConv.tomessages(mDTO))));
		}catch (Exception e){

		}
		return result.getData();
	}

	public Object createmessFile(messages m) {
		ServiceResult result = new ServiceResult();
		try{
			messagesRes.save(m);
		}catch (Exception e){

		}
		result.setMessage("success");
		MessageResponse mes = new MessageResponse("success");
		return mes;
	}
	
	// chưa update 
	public Object update(messagesDTO mDTO) {	
		ServiceResult result = new ServiceResult();	
		messages messagesn = messagesRes.findById(mDTO.getId(),mDTO.getChannel_id());
		if(messagesn == null) {
			MessageResponse mes = new MessageResponse("messages not found");
			messagesDTO m = new messagesDTO();
			return m;
		}
		try{
			messagesRes.save(messagesConv.tomessages(mDTO));
		}catch (Exception e){
			messagesDTO m = new messagesDTO();
			return m;
		}
		return mDTO;
	}

	public int updateStatusMessages(long channelid){
//		try{
//			System.out.println("vào");
//			messagesRes.updateStatusMessages(channelid);
//			return  1;
//		}catch (Exception e){
//			System.out.println("lỗi "+e);
//			return 0;
//		}

		try {
			List<messages> list = messagesRes.findByChannel_General(channelid);
			for(messages m : list){
				if(Integer.parseInt(m.getStatus()) == 2){
					m.setStatus("1");
					messagesRes.save(m);
				}
			}
			return 1;
		}catch (Exception e){
			System.out.println("lỗi "+e);
			return 0;
		}

	}

}
