package api.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "friend")
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class friend {
    @EmbeddedId
    private userfriend userfriend;

    //0 gửi lời mời kb
    //1 đã là bạn
    //2 nhận đc lời mời kb
    private int friend_active;

    private int active;

    @Embeddable
    public static class userfriend implements Serializable {
        @Column(name = "id")
        private String id;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "friend_id")
        private user friend;


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
    }

    public friend() {
    }

    public friend(friend.userfriend userfriend, int friend_active, int active) {
        this.userfriend = userfriend;
        this.friend_active = friend_active;
        this.active = active;
    }

    public friend.userfriend getUserfriend() {
        return userfriend;
    }

    public void setUserfriend(friend.userfriend userfriend) {
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
