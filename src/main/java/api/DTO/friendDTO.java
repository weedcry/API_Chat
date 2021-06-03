package api.DTO;

import api.entity.user;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class friendDTO {
    private String id;

    private user friend;

    private int friend_active;

    private int active;

    public friendDTO() {
    }

    public friendDTO(String id, user friend, int friend_active, int active) {
        this.id = id;
        this.friend = friend;
        this.friend_active = friend_active;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public user getFriend() {
        return friend;
    }

    public void setFriend(user friend) {
        this.friend = friend;
    }

    public int getFriend_active() {
        return friend_active;
    }

    public void setFriend_active(int friend_active) {
        this.friend_active = friend_active;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
