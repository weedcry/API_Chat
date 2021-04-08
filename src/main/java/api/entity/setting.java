package api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="setting")
public class setting {
	@Id
	private String id;
	private String nofitication;
	private int can_find;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNofitication() {
		return nofitication;
	}
	public void setNofitication(String nofitication) {
		this.nofitication = nofitication;
	}
	public int getCan_find() {
		return can_find;
	}
	public void setCan_find(int can_find) {
		this.can_find = can_find;
	}
	
	
	
	
}
