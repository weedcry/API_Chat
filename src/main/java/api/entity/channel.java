package api.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="channel")
public class channel  {
//	@Id
//	@ManyToOne()
//	@JoinColumn(name="channel_id")
//	private channel_general channel_general;
	@Id
	private long id;
	
	
	private String author_id; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private user user;
	
	private String topic;
	
	private String password;
	
	private String status;
	
	private String exits;	




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	
	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExits() {
		return exits;
	}

	public void setExits(String exits) {
		this.exits = exits;
	}	

	
	
}
