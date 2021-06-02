package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.Convert.channel_generalConvert;
import api.DTO.channel_generalDTO;
import api.entity.channel_general;
import api.repository.channel_generalRepository;

import java.util.List;

@Service
@Component
public class channel_generalService {
	@Autowired
	channel_generalRepository channel_generalRes;
	
	channel_generalConvert chanC = new channel_generalConvert();




	public Object findByUserid(String userid) {
		ServiceResult result = new ServiceResult();
		List<channel_general> list = channel_generalRes.findByUserid(userid);
		if(list == null) {
			result.setMessage("channel_general not found");
			return result.getMessage();
		}
		return list;
	}
		
	public Object delete(long id ) {
		ServiceResult result = new ServiceResult();
		channel_general c = channel_generalRes.findById(id);
		if(c == null) {
			result.setMessage("channel_general not found");
			return result.getMessage();
		}
		channel_generalRes.delete(c);
		result.setMessage("success");
		return result.getMessage();	
	}
	
	public Object create() {
		channel_generalDTO chanDTO = new channel_generalDTO();
		chanDTO.setColor("black");
		chanDTO.setPhoto("https://s3.us-east-2.amazonaws.com/myawsbucketappfile/1622610729701-img_group.jpg");

		ServiceResult result = new ServiceResult();	
		result.setData(chanC.tochannel_generalDTO(channel_generalRes.save(chanC.tochannel_general(chanDTO))));
		return result.getData();
	}
	
	public Object update(channel_generalDTO chanDTO) {	
		ServiceResult result = new ServiceResult();	
		channel_general channel_generaln = channel_generalRes.findById(chanDTO.getId());
		if(channel_generaln == null) {
			result.setMessage("channel_general not found");
			return result.getMessage();
		}
		channel_generalRes.save(chanC.tochannel_general(chanDTO));
		result.setData(chanDTO);
		return result.getData();
	}
}
