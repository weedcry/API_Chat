package api.Convert;

import java.util.ArrayList;
import java.util.List;

import api.DTO.channelDTO;
import api.entity.channel;
import api.entity.user;

public class channelConvert {
	
	userConvert ucon = new userConvert();
	
	public channel tochannel(channelDTO cDTO) {
		user u = new user();
		u.setId(cDTO.getUser().getId());
		u.setName(cDTO.getUser().getName());
		u.setPhoto(cDTO.getUser().getPhoto());
		channel c = new channel();	
		c.setId(cDTO.getId());
		c.setUser(u);
		c.setTopic(cDTO.getTopic());
		c.setPassword(cDTO.getPassword());
		c.setStatus(cDTO.getStatus());
		c.setExits(cDTO.getStatus());	
		return c;
	}
	
	public channelDTO tochannelDTO(channel c) {

		channelDTO cDTO = new channelDTO();
		cDTO.setId(c.getId());
		cDTO.setUser(ucon.touserDTO(c.getUser()));
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
