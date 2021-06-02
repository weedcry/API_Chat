package api.DTO;

public class channelDTO {
	
	private long id;
	
	private String author_id;
	
	private String topic;
	
	private String password;
	
	private String status;
	
	private String exits;

	public channelDTO() {
	}

	public channelDTO(long id, String author_id, String topic, String password, String status, String exits) {
		this.id = id;
		this.author_id = author_id;
		this.topic = topic;
		this.password = password;
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
