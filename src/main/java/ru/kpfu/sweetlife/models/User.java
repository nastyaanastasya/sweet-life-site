package ru.kpfu.sweetlife.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String username;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image profileImage;

    private LocalDate dateOfRegistration;

    @ToString.Exclude
    @ManyToMany(mappedBy = "following")
    private List<User> subscribers;

    @ToString.Exclude
    @ManyToMany
    private List<User> following;

    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    private List<Recipe> recipes;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "liked_recipes",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"user_id", "recipe_id"})
            }
    )
    private List<Recipe> likedRecipes;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "saved_recipes",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"user_id", "recipe_id"})
            }
    )
    private List<Recipe> savedRecipes;

    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "liked_comments",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "comment_id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"user_id", "comment_id"})
            }
    )
    private List<Comment> likedComments;
}
