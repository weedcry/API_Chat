package api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="setting")
public class setting {
	@Id
	private String id;
	private int nofitication;
	private int can_find;

	public setting() {
	}

	public setting(String id, int nofitication, int can_find) {
		this.id = id;
		this.nofitication = nofitication;
		this.can_find = can_find;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getNofitication() {
		return nofitication;
	}

	public void setNofitication(int nofitication) {
		this.nofitication = nofitication;
	}

	public int getCan_find() {
		return can_find;
	}
	public void setCan_find(int can_find) {
		this.can_find = can_find;
	}
	
	
	
	
}
