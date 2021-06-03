package api.entity;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="channel")
public class channel  {
//	@Id
//	@ManyToOne()
//	@JoinColumn(name="channel_id")
//	private channel_general channel_general;
	@EmbeddedId
	private  channeluser channeluser;

	private String topic;
	
	private String password;

	private String photo;
	
	private String status;
	
	private String exits;

	@Embeddable
	public static class channeluser implements Serializable {
		@Column(name = "id")
		private long id;

		@Column(name = "author_id")
		private String author_id;

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
	}

	public channel.channeluser getChanneluser() {

		return channeluser;
	}

	public void setChanneluser(channel.channeluser channeluser) {
		this.channeluser = channeluser;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
