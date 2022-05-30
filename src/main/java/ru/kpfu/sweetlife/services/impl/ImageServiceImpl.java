package ru.kpfu.sweetlife.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.sweetlife.dto.ImageDto;
import ru.kpfu.sweetlife.exceptions.ImageNotFoundException;
import ru.kpfu.sweetlife.models.Image;
import ru.kpfu.sweetlife.models.User;
import ru.kpfu.sweetlife.repositories.ImageRepository;
import ru.kpfu.sweetlife.repositories.RecipeRepository;
import ru.kpfu.sweetlife.repositories.UserRepository;
import ru.kpfu.sweetlife.services.ImageService;
import ru.kpfu.sweetlife.services.MediaService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final MediaService mediaService;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Override
    public ImageDto saveImage(MultipartFile file) {
        return ImageDto.from(imageRepository.save(
                Image.builder()
                        .name(mediaService.upload(file))
                        .build()
        ));
    }

    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files) {
        List<ImageDto> images = new ArrayList<>();
        files.forEach(multipartFile -> images.add(saveImage(multipartFile)));
        return images;
    }

    @Override
    public List<ImageDto> getRecipeImages(Long recipeId) {
        return ImageDto.from(imageRepository.getImagesByRecipeId(recipeId).orElseThrow(ImageNotFoundException::new));
    }

    @Override
    public ImageDto getProfileImage(Long userId) {
        return ImageDto.from(imageRepository.getImagesByUserId(userId).orElseThrow(ImageNotFoundException::new));
    }

    @Override
    public void saveWithRecipeId(MultipartFile img, Long id) {
        ImageDto imageDto = saveImage(img);
        Image image = imageRepository.getById(imageDto.getId());
        image.setRecipe(recipeRepository.getById(id));
        imageRepository.save(image);
    }

    @Override
    public void saveProfileImage(MultipartFile file, Long id) {
        ImageDto imageDto = saveImage(file);
        Image image = imageRepository.getById(imageDto.getId());
        User user = userRepository.getById(id);
        user.setProfileImage(image);
        userRepository.save(user);
    }
}
