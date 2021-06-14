package api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.entity.setting;
import api.service.settingService;

@RestController
@RequestMapping("/api/setting")
public class settingController {
	@Autowired
	settingService settingS;
	
	@GetMapping("")
	public ResponseEntity<Object> findByUserId(){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		setting setting = (setting)settingS.findById(username);
		if(setting != null){
			return new ResponseEntity<Object>(setting,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setting not found");
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id){
		return new ResponseEntity<Object>(settingS.delete(id),HttpStatus.OK);	
	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody setting u){	
		return new ResponseEntity<Object>(settingS.update(u),HttpStatus.OK);
	}

	public void create(String id){
		setting sett = new setting(id,1,1);
		settingS.create(sett);
	}
}
