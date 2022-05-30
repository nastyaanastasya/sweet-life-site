package ru.kpfu.sweetlife.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.sweetlife.dto.ImageDto;

import java.util.List;

public interface ImageService {

    ImageDto saveImage(MultipartFile file);
    List<ImageDto> saveImages(List<MultipartFile> files);

    List<ImageDto> getRecipeImages(Long recipeId);
    ImageDto getProfileImage(Long userId);

    void saveWithRecipeId(MultipartFile img, Long id);

    void saveProfileImage(MultipartFile file, Long id);
}
