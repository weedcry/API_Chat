package api.Convert;

import api.DTO.userDTO;
import api.entity.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class userConvert {
	
	public user touser(userDTO udto) {
		user u = new user();
		u.setId(udto.getId());
		u.setName(udto.getName());
		u.setPassword(udto.getPassword());
		u.setPhone(udto.getPhone());
		u.setPhoto(udto.getPhoto());
		u.setBirthday(udto.getBirthday());
		u.setDate_create(udto.getDate_create());
		u.setActive(udto.getActive());
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

	public List<userDTO> tolistDTO(List<user> list)
	{
		List<userDTO> listDTO = new ArrayList<>(); ;
		for(user u : list) {
			listDTO.add(touserDTO(u));
		}

		return listDTO;
	}

}
