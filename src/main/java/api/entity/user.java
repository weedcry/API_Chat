package api.entity;

import java.util.Date;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user")
public class user {
	@Id
	private String id;
	
	private String name;
	
	private String password;
	
	private String phone;
	
	private String photo;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date  birthday;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date date_create;
	
	private int active = 0;

	public user() {

	}

	public user(String id, String name, String password, String photo, Date birthday, String phone) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.photo = photo;
		this.birthday = birthday;
		this.phone = phone;
	}

	@OneToMany(mappedBy = "user")
	private Collection<messages> messages;

	@OneToMany(mappedBy = "userfriend")
	private Collection<friend> friend;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getDate_create() {
		return date_create;
	}

	public void setDate_create(Date date_create) {
		this.date_create = date_create;
	}
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

//	public Collection<channel> getChannel() {
//		return channel;
//	}
//
//	public void setChannel(Collection<channel> channel) {
//		this.channel = channel;
//	}
//
//	public Collection<messages> getMessages() {
//		return messages;
//	}
//
//	public void setMessages(Collection<messages> messages) {
//		this.messages = messages;
//	}
	
}
