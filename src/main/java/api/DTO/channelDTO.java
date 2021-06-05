package api.DTO;

public class channelDTO {
	
	private long id;
	
	private String author_id;
	
	private String topic;
	
	private String password;

	private String photo;

	private int status;
	
	private int exits;

	public channelDTO() {
	}

	public channelDTO(long id, String author_id, String topic, String password, String photo, int status, int exits) {
		this.id = id;
		this.author_id = author_id;
		this.topic = topic;
		this.password = password;
		this.photo = photo;
		this.status = status;
		this.exits = exits;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getExits() {
		return exits;
	}

	public void setExits(int exits) {
		this.exits = exits;
	}
}
