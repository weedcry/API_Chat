package api.service;
import api.entity.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {


    private String id;

    private String name;

    @JsonIgnore
    private String password;

    public Collection<? extends GrantedAuthority> Authorities;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
//        this.Authorities = Authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    public static UserDetailsImpl build(user user){
//        List<GrantedAuthority> Authorities = null;
//        List<GrantedAuthority> Authorities = user.getrole.().Stream()
//                .map(role ->new SimpleGrantedAuthority(role.getName().name()))
//        .collect(collector.tolist());

        return new UserDetailsImpl(user.getId(),user.getName(),user.getPassword());
//        return new UserDetailsImpl(user.getId(),user.getName(),user.getPassword());

    }



    @Override
    public String getPassword() {

        return password;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return  true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return  false;
        }
        UserDetailsImpl user = (UserDetailsImpl) obj;

        return Objects.equals(id,user.id);
    }
}
