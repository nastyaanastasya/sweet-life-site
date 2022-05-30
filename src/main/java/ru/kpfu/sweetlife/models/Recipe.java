package ru.kpfu.sweetlife.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private Integer timeOfCooking;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "recipe")
    private List<Image> images;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(mappedBy = "savedRecipes")
    private List<User> savedBy;

    @ManyToMany(mappedBy = "likedRecipes")
    private List<User> likedBy;

    private LocalDate dateOfEditing;
}
