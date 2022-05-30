package ru.kpfu.sweetlife.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.sweetlife.models.Image;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<List<Image>> getImagesByRecipeId(Long recipeId);
    Optional<Image> getImagesByUserId(Long userId);
}
