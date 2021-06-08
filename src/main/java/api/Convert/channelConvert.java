package api.Convert;

import java.util.ArrayList;
import java.util.List;

import api.DTO.channelDTO;
import api.entity.channel;
import api.entity.user;

public class channelConvert {
	

	public channel tochannel(channelDTO cDTO) {
		channel c = new channel();
		channel.channeluser ch = new channel.channeluser();
		ch.setId(cDTO.getId());
		ch.setAuthor_id(cDTO.getAuthor_id());
		c.setChanneluser(ch);
		c.setTopic(cDTO.getTopic());
		c.setPassword(cDTO.getPassword());
		c.setPhoto(cDTO.getPhoto());
		c.setStatus(cDTO.getStatus());
		c.setExits(cDTO.getExits());
		return c;
	}
	
	public channelDTO tochannelDTO(channel c) {

		channelDTO cDTO = new channelDTO();
		cDTO.setId(c.getChanneluser().getId());
		cDTO.setAuthor_id(c.getChanneluser().getAuthor_id());
		cDTO.setTopic(c.getTopic());
		cDTO.setPassword(c.getPassword());
		cDTO.setPhoto(c.getPhoto());
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
