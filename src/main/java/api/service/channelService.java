package api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.Convert.userConvert;
import api.DTO.MessageResponse;
import api.DTO.channel_generalDTO;
import api.DTO.userDTO;
import api.entity.channel_general;
import api.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	channel_generalService channel_generalSer;

	@Autowired
	userService userSer;


	userConvert userConvert = new userConvert();
	channelConvert channelC = new channelConvert();

	
	// hiển thị tất cả channel user tham gia
	public Object findByAuthor_id(String author_id) {
		ServiceResult result = new ServiceResult();
		List<channel> listc = channelRes.findchannelByauthorid(author_id);
		if(listc == null) {
			result.setMessage("channel not found");
			List<channelDTO> listDTO = new ArrayList<>();
			return listDTO;
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
		userDTO u = (userDTO)userSer.findById(userid);
		friendid = friendid+".com";
		System.out.println("a-"+friendid);
		userDTO u1 = (userDTO)userSer.findById(friendid);
		channel_generalDTO chanDTO = (channel_generalDTO )channel_generalSer.create();
		channelDTO cDTO = new channelDTO(chanDTO.getId(),u.getId(),u1.getName(),"null",u1.getPhoto(),1,1);
		channelDTO c1DTO = new channelDTO(chanDTO.getId(),u1.getId(),u.getName(),"null",u.getPhoto(),1,1);
		ServiceResult result = new ServiceResult();
		try {
			channelRes.save(channelC.tochannel(c1DTO));
			result.setData(channelC.tochannelDTO(channelRes.save(channelC.tochannel(cDTO))));
		}catch (Exception e){

		}
		return result.getData();
	}

	public Object creategroup(String userid,List<userDTO> listfriendid) {
		userDTO uid = (userDTO)userSer.findById(userid);
		String[] worduserid = uid.getName().split("\\s");
		String photogr = "https://s3.us-east-2.amazonaws.com/myawsbucketappfile/1622610729701-img_group.jpg";
		channel_generalDTO chanDTO = (channel_generalDTO )channel_generalSer.create();
		String namegr = worduserid[worduserid.length-1];
		for(userDTO udto : listfriendid ){
			String[] words = udto.getName().split("\\s");
			namegr+=","+words[words.length-1];
		}
		for(userDTO udto : listfriendid ){
			channelDTO c1DTO = new channelDTO(chanDTO.getId(),udto.getId(),namegr,"null",photogr,2,1);
			try {
				channelRes.save(channelC.tochannel(c1DTO));
			}catch (Exception e){
			}
		}
		channelDTO cDTO = new channelDTO(chanDTO.getId(),userid,namegr,"null",photogr,2,1);
		ServiceResult result = new ServiceResult();
		try {
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
		List<channel> list = channelRes.findByid(cDTO.getId());
		// xóa channel từ 1 phía người dùng gửi lên dành cho nhóm
		if(cDTO.getExits() == 0){
			try {
				for(channel ch : list){
					if(ch.getChanneluser().getAuthor_id().equals(cDTO.getAuthor_id())){
						channelRes.save(ch);
						break;
					}
				}
			}catch (Exception e){}
			return cDTO;
		}
		try {
			for(channel ch : list){
				ch.setTopic(cDTO.getTopic());
				channelRes.save(ch);
			}
			result.setData(cDTO);
		}catch (Exception e){}
		return result.getData();
	}

	// xóa channel cho 2 user
	public Object deletechannelfor2user(channelDTO cDTO) {
		ServiceResult result = new ServiceResult();
		channel channeln = channelRes.findOneByAuthorid(cDTO.getId(), cDTO.getAuthor_id());
		if (channeln == null) {

		}
		List<channel> list = channelRes.findByid(cDTO.getId());
		if (list.size() == 2 && cDTO.getExits() == 0) {
			channelDTO chan = new channelDTO();
			try {
				for (channel ch : list) {
					if(! ch.getChanneluser().getAuthor_id().equals(cDTO.getAuthor_id())){
						chan = channelC.tochannelDTO(ch);
					}
					ch.setExits(0);
					channelRes.save(ch);
				}
			} catch (Exception e) {
			}
			return chan;
		}
		return cDTO;
	}


	public Object findchannelbyfriendId(String username,String friendid){
		ServiceResult result = new ServiceResult();
		channel ch = channelRes.findchannelbyfriend(username,friendid);

        if(ch == null){
            MessageResponse mes = new MessageResponse("not found");
            channelDTO channelDTO = new channelDTO();
            return channelDTO;
        }

        return channelC.tochannelDTO(ch);
	}

}
