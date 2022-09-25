package com.graceman.fashionblogrestapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.graceman.fashionblogrestapi.enums.Provider;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String uuid;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String role;
    private String password;
    private String username;
    private String displayPicture;
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Like> likeList = new HashSet<>();

}
