package api.service;

import java.io.IOException;
import java.util.List;

import api.DTO.channel_generalDTO;
import api.DTO.userDTO;
import api.entity.channel_general;
import api.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.Convert.channelConvert;
import api.DTO.channelDTO;
import api.entity.channel;
import api.DTO.MessageResponse;
import api.repository.channelRepository;

@Service
@Component
public class channelService {
	@Autowired
	channelRepository channelRes;

	@Autowired
	channel_generalService channel_generalSer;

	@Autowired
	userService userSer;


	channelConvert channelC = new channelConvert();

	
	// hiển thị tất cả channel user tham gia
	public Object findByAuthor_id(String author_id) {
		ServiceResult result = new ServiceResult();
		List<channel> listc = channelRes.findByauthorid(author_id);
		if(listc.isEmpty()) {
			result.setMessage("channel not found");
			return  result.getMessage();
		}
		return channelC.listchannelDTO(listc);
	}

//	public Object delete(long id ) {
//		ServiceResult result = new ServiceResult();
//		List<channel> c = channelRes.findById(id);
//		if(c.isEmpty() == false) {
//			result.setMessage("channel not found");
//			return result.getMessage();
//		}
//		channelRes.delete(c);
//		result.setMessage("success");
//		return result.getMessage();	
//	}
	
	public Object create(String userid,String friendid) {
		channel_generalDTO chanDTO = (channel_generalDTO )channel_generalSer.create();
		userDTO u = (userDTO)userSer.findById(userid);
		userDTO u1 = (userDTO)userSer.findById(friendid);
		channelDTO cDTO = new channelDTO(chanDTO.getId(),u.getName(),u1.getName(),"null","1","1");
		channelDTO c1DTO = new channelDTO(chanDTO.getId(),u1.getName(),u.getName(),"null","1","1");
		ServiceResult result = new ServiceResult();
		try {
			channelRes.save(channelC.tochannel(c1DTO));
			result.setData(channelC.tochannelDTO(channelRes.save(channelC.tochannel(cDTO))));
		}catch (Exception e){

		}
		return result.getData();
	}
	
	public Object update(channelDTO cDTO) {	
		ServiceResult result = new ServiceResult();
		channel channeln = channelRes.findOneByAuthorid(cDTO.getId(),cDTO.getAuthor_id());
		if(channeln == null) {
			result.setMessage("channel not found");
			return result.getMessage();
		}
		result.setData(channelC.tochannelDTO(channelRes.save(channelC.tochannel(cDTO))));
		return result.getData();
	}

	public Object findchannelbyfriendId(String username,String friendid){
		ServiceResult result = new ServiceResult();
		channel c = channelRes.findchannelbyfriend(username,friendid);
		if(c == null){
			result.setMessage("not found");
			MessageResponse mes = new MessageResponse("not found");
			return mes;
		}
		return c;
	}

}
