package api.Convert;

import api.DTO.channel_generalDTO;
import api.entity.channel_general;

public class channel_generalConvert {
	
	
	public channel_general tochannel_general(channel_generalDTO chanDTO) {
		channel_general chan = new channel_general();	
		chan.setId(chanDTO.getId());
		chan.setColor(chanDTO.getColor());
		chan.setPhoto(chanDTO.getPhoto());
		return chan;
	}
	
	public channel_generalDTO tochannel_generalDTO(channel_general chan) {
		channel_generalDTO chanDTO = new channel_generalDTO();	
		chanDTO.setId(chan.getId());
		chanDTO.setColor(chan.getColor());
		chanDTO.setPhoto(chan.getPhoto());
		return chanDTO;
	}
	
}
