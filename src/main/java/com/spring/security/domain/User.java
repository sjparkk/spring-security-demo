package com.spring.security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class User implements UserDetails {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long code;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private String auth;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();

        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }

    @Override
    public String getUsername() { // 사용자 id 반환 (유니크값)
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정 만료 여부
        return false;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠금 여부
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 패스워드 만료 여부
        return false;
    }

    @Override
    public boolean isEnabled() { // 계정 사용 가능 여부 반환
        return false;
    }
}
