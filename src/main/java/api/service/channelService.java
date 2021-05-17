package api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.Convert.channelConvert;
import api.DTO.channelDTO;
import api.entity.channel;
import api.repository.channelRepository;

@Service
@Component
public class channelService {
	@Autowired
	channelRepository channelRes;
	
	channelConvert channelC = new channelConvert();

	
	// hiển thị tất cả channel user tham gia
	public List<channelDTO> findByAuthor_id(String author_id) {
		ServiceResult result = new ServiceResult();
		List<channel> listc = channelRes.findByauthorid(author_id);
		if(listc.isEmpty()) {
			System.out.println("fail");
			result.setMessage("channel not found");
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
