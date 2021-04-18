package api.Convert;

import api.DTO.userDTO;
import api.entity.user;

public class userConvert {
	
	public user touser(userDTO udto) {
		user u = new user();
		
		return u;
	}
	
	public userDTO touserDTO(user u) {
		userDTO udto = new userDTO();
		udto.setId(u.getId());
		udto.setName(u.getName());
		udto.setPassword(u.getPassword());
		udto.setPhone(u.getPhone());
		udto.setPhoto(u.getPhoto());
		udto.setBirthday(u.getBirthday());
		udto.setDate_create(u.getDate_create());
		udto.setActive(u.getActive());
		return udto;
	}
	
	public userDTO touserDTO_channel(user u) {
		userDTO udto = new userDTO();
		udto.setId(u.getId());
		udto.setName(u.getName());
		udto.setPassword(u.getPassword());
		udto.setPhone(u.getPhone());
		udto.setPhoto(u.getPhoto());
		udto.setBirthday(u.getBirthday());
		udto.setDate_create(u.getDate_create());
		udto.setActive(u.getActive());
		return udto;
	}
}
