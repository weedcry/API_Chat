package api.service;

import api.DTO.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import api.entity.setting;
import api.repository.settingRepository;

@Service
@Component
public class settingService {
	@Autowired
	settingRepository settingRes;
	
	public Object findById(String id) {		
		ServiceResult result = new ServiceResult();
		setting s = settingRes.findById(id);
		result.setData(s);
		return result.getData();
	}
		
	public Object delete(String id ) {
		ServiceResult result = new ServiceResult();
		setting u = settingRes.findById(id);
		if(u == null) {
			MessageResponse mes = new MessageResponse("setting not found");
			return mes;
		}
		settingRes.delete(u);
		result.setMessage("success");
		return result.getMessage();	
	}
	
	public void create(setting u) {
		ServiceResult result = new ServiceResult();
		try {
			settingRes.save(u);
		}catch (Exception e){
		}
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
