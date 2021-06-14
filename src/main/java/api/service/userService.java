package api.service;

import api.DTO.MessageResponse;
import api.DTO.userDTO;
import api.entity.setting;
import api.repository.settingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.Convert.userConvert;
import api.entity.user;
import api.repository.userRepository;

import java.util.List;

@Service
@Component
public class userService implements UserDetailsService {
	@Autowired
	userRepository userRes;

	@Autowired
	settingRepository settingRes;

	userConvert uconvert = new userConvert();

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		user user = userRes.findByUserName(s).
				orElseThrow(() -> new UsernameNotFoundException("username not found with :"+s));
		return UserDetailsImpl.build(user);
	}

	
	public Object findById(String id) {
		ServiceResult result = new ServiceResult();
		user u = userRes.findById(id);
		if(u == null) {
			result.setMessage("user not found");
			MessageResponse mes = new MessageResponse("user not found");
//			return mes;
			return new userDTO();
		}


		result.setData(uconvert.touserDTO(u));
		return result.getData();
	}


	public Object finduserById(String id) {
		ServiceResult result = new ServiceResult();
		user u = userRes.finduserbyid(id);
		if(u == null) {
			result.setMessage("user not found");
			MessageResponse mes = new MessageResponse("user not found");
			return mes;
		}
		result.setData(uconvert.touserDTO(u));
		return result.getData();
	}
		
	public Object delete(String id) {
		ServiceResult result = new ServiceResult();
		user u = userRes.findById(id);
		if(u == null) {
			result.setMessage("user not found");
			MessageResponse mes = new MessageResponse("user not found");
			return mes;
		}
		userRes.delete(u);
		result.setMessage("success");
		MessageResponse mes = new MessageResponse("success");
		return mes;
	}

	
	public Object create(user u) {	
		ServiceResult result = new ServiceResult();
		if (userRes.existsById(u.getId())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		try {
			userRes.save(u);
			setting sett = new setting(u.getId(),1,1);
			settingRes.save(sett);

		}catch (Exception e){
			return ResponseEntity
					.ok()
					.body(new MessageResponse("error"+e));
		}
		return ResponseEntity
				.ok()
				.body(new MessageResponse("User registered successfully!"));
	}

	public Object update(userDTO u) {
		ServiceResult result = new ServiceResult();
		try {
			result.setData(userRes.save(uconvert.touser(u)));
		}catch (Exception e){}

		return result.getData();
	}

	public int updateAvatar(String username,String linkphoto){
		user u = userRes.findById(username);
		 if(u == null){
		 	return 0;
		 }
		 u.setPhoto(linkphoto);
		try {
			userRes.save(u);
		}catch (Exception e){
			System.out.println("lỗi "+e);
			return  0;
		}
		return  1;
	}

	public List<userDTO> listuserbychannelid(long id) {

		List<user> list = userRes.findlistuserbychannelid(id);

		return 	uconvert.tolistDTO(list);
	}


}
