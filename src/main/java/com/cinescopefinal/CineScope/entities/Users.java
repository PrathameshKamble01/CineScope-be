package com.cinescopefinal.CineScope.entities;

import com.cinescopefinal.CineScope.entities.enums.Role;
import com.cinescopefinal.CineScope.entities.enums.Status;
import com.cinescopefinal.CineScope.entities.enums.Subscription;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String email;
    @Column
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column
    private Subscription subscription = Subscription.FREE;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status = Status.ACTIVE;
    @Column(name = "movie_types")
    private String movieTypes;

    public String getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(String movieTypes) {
        this.movieTypes = movieTypes;
    }
    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Users(Long id, String name, String email, String password, Role role, Subscription subscription, Status status, String movieTypes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.subscription = subscription;
        this.status = status;
        this.movieTypes = movieTypes;
    }

    public Users() {}
}
