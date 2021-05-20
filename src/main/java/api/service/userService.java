package api.service;

import api.DTO.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.Convert.userConvert;
import api.entity.user;
import api.repository.userRepository;

@Service
@Component
public class userService implements UserDetailsService {
	@Autowired
	userRepository userRes;
	
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
			return result.getMessage();
		}
		result.setData(uconvert.touserDTO(u));
		return result.getData();
	}
		
	public Object delete(String id ) {
		ServiceResult result = new ServiceResult();
		user u = userRes.findById(id);
		if(u == null) {
			result.setMessage("user not found");
			return result.getMessage();
		}
		userRes.delete(u);
		result.setMessage("success");
		return result.getMessage();	
	}
	
	public Object create(user u) {	
		ServiceResult result = new ServiceResult();

		if (userRes.existsById(u.getId())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		userRes.save(u);


		return ResponseEntity
				.ok()
				.body(new MessageResponse("User registered successfully!"));
	}


	public Object update(user u) {	
		ServiceResult result = new ServiceResult();	
		user usern = userRes.findById(u.getId());
		if(usern == null) {
			result.setMessage("user not found");
			return result.getMessage();
		}
		result.setData(uconvert.touserDTO(userRes.save(u)));
		return result.getData();
	}



}
