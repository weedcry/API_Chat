package api.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "friend")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class friend {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private  user userfriend;

    private int friend_active;

    private int active;

    public friend() {
    }

    public friend(String id, user userfriend, int friend_active, int active) {
        this.id = id;
        this.userfriend = userfriend;
        this.friend_active = friend_active;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public user getUserfriend() {
        return userfriend;
    }

    public void setUserfriend(user userfriend) {
        this.userfriend = userfriend;
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
