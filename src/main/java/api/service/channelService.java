package api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.Convert.channelConvert;
import api.DTO.channelDTO;
import api.entity.channel;
import api.repository.channelRepository;

@Service
public class channelService {
	@Autowired
	channelRepository channelRes;
	
	channelConvert channelC = new channelConvert();
	
	public List<channelDTO> findById(long id) {	
		ServiceResult result = new ServiceResult();
		List<channel> listc = channelRes.findById(id);
		if(listc.isEmpty()) {
			System.out.println("fail");
			result.setMessage("channel not found");
			//return listc;
		}
		return channelC.listchannelDTO(listc);
	}
	
	// hiển thị tất cả channel user tham gia
	public List<channelDTO> findByUser(String user_id) {	
		ServiceResult result = new ServiceResult();
		List<channel> listc = channelRes.findByUser(user_id);
		if(listc.isEmpty()) {
			System.out.println("fail");
			result.setMessage("channel not found");
		//	return c;
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
	
	public Object create(channelDTO cDTO) {	
		ServiceResult result = new ServiceResult();	
		result.setData(channelC.tochannelDTO(channelRes.save(channelC.tochannel(cDTO))));
		return result.getData();
	}
	
	public Object update(channelDTO cDTO) {	
		ServiceResult result = new ServiceResult();	
		channel channeln = channelRes.findByUserId(cDTO.getId(),cDTO.getUser().getId());
		if(channeln == null) {
			result.setMessage("channel not found");
			return result.getMessage();
		}
		result.setData(channelC.tochannelDTO(channelRes.save(channelC.tochannel(cDTO))));
		return result.getData();
	}
}
