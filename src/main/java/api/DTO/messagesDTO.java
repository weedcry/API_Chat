package api.DTO;

import api.entity.user;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class messagesDTO {
	private long id;
	
	private long channel_id;
	
	private user user;
	
	private String type;
	
	private String content;
	
	private String Status;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="IST")
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

	public api.entity.user getUser() {
		return user;
	}

	public void setUser(api.entity.user user) {
		this.user = user;
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
