package api.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="channel_general")
public class channel_general {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String color;
//	
//	@OneToMany(mappedBy = "channel_general",fetch = FetchType.LAZY)
//	private Collection<channel> channel;
	
	@OneToMany(mappedBy = "channel_general",fetch = FetchType.LAZY)
	private Collection<messages> messages;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

//	public Collection<messages> getMessages() {
//		return messages;
//	}
//
//	public void setMessages(Collection<messages> messages) {
//		this.messages = messages;
//	}


	
	
}
