package api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.entity.setting;
import api.repository.settingRepository;

@Service
public class settingService {
	@Autowired
	settingRepository settingRes;
	
	public Object findById(String id) {		
		ServiceResult result = new ServiceResult();
		setting s = settingRes.findById(id);
		if(s == null) {
			System.out.println("fail");
			result.setMessage("setting not found");
			return result.getMessage();
		}
		System.out.println("succe");
		System.out.println(s);
		result.setData(s);
		return result.getData();
	}
		
	public Object delete(String id ) {
		ServiceResult result = new ServiceResult();
		setting u = settingRes.findById(id);
		if(u == null) {
			result.setMessage("setting not found");
			return result.getMessage();
		}
		settingRes.delete(u);
		result.setMessage("success");
		return result.getMessage();	
	}
	
	public Object create(setting u) {	
		ServiceResult result = new ServiceResult();	
		result.setData(settingRes.save(u));
		return result.getData();
	}
	
	public Object update(setting s) {	
		ServiceResult result = new ServiceResult();	
		setting settingn = settingRes.findById(s.getId());
		if(settingn == null) {
			result.setMessage("setting not found");
			return result.getMessage();
		}
		result.setData(settingRes.save(s));
		return result.getData();
	}
}
