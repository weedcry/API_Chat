package api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findByUserId(@PathVariable String id){
		return new ResponseEntity<Object>(settingS.findById(id),HttpStatus.OK);	
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id){
		return new ResponseEntity<Object>(settingS.delete(id),HttpStatus.OK);	
	}
	
	@PutMapping("")
	public ResponseEntity<Object> update(@RequestBody setting u){	
		return new ResponseEntity<Object>(settingS.update(u),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@RequestBody setting u){
		return new ResponseEntity<Object>(settingS.create(u),HttpStatus.CREATED);
	}
}
