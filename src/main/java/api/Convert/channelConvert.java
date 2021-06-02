package api.Convert;

import java.util.ArrayList;
import java.util.List;

import api.DTO.channelDTO;
import api.entity.channel;
import api.entity.user;

public class channelConvert {
	

	public channel tochannel(channelDTO cDTO) {
		user u = new user();
		channel c = new channel();	
		c.setId(cDTO.getId());
		c.setAuthor_id(cDTO.getAuthor_id());
		c.setTopic(cDTO.getTopic());
		c.setPassword(cDTO.getPassword());
		c.setStatus(cDTO.getStatus());
		c.setExits(cDTO.getStatus());	
		return c;
	}
	
	public channelDTO tochannelDTO(channel c) {

		channelDTO cDTO = new channelDTO();
		cDTO.setId(c.getId());
		cDTO.setAuthor_id(c.getAuthor_id());
		cDTO.setTopic(c.getTopic());
		cDTO.setPassword(c.getPassword());
		cDTO.setStatus(c.getStatus());
		cDTO.setExits(c.getStatus());		
		return cDTO;
	}
	
	
	public List<channelDTO> listchannelDTO(List<channel> listc){
		List<channelDTO> list = new ArrayList<channelDTO>();
		for (channel c : listc) {
			list.add(tochannelDTO(c));
		}
		
		return list;
	}
	
}
