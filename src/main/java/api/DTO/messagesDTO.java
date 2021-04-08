package api.DTO;

import java.sql.Date;

public class messagesDTO {
	private long id;
	
	private long channel_id;
	
	private String user_id;
	
	private String type;
	
	private String content;
	
	private String Status;
	
	private Date datetime ;
	
	private long reply;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(long channel_id) {
		this.channel_id = channel_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public long getReply() {
		return reply;
	}

	public void setReply(long reply) {
		this.reply = reply;
	}
	
	
	
}
