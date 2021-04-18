package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.Convert.userConvert;
import api.entity.user;
import api.repository.userRepository;

@Service
@Component
public class userService {
	@Autowired
	userRepository userRes;
	
	userConvert uconvert = new userConvert();
	
	public Object findById(String id) {		
		ServiceResult result = new ServiceResult();
		user u = userRes.findById(id);
//		user u = userRes.findByQ(id);
		if(u == null) {
			System.out.println("fail");
			result.setMessage("user not found");
			return result.getMessage();
		}
		System.out.println("succe");
		System.out.println(u);
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
		result.setData(userRes.save(u));
		return result.getData();
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
