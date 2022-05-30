package ru.kpfu.sweetlife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.kpfu.sweetlife.models.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {

    private Long id;
    private String name;

    public static ImageDto from(Image image){
        if(image == null) return null;
        return ImageDto.builder()
                .id(image.getId())
                .name(image.getName())
                .build();
    }

    public static List<ImageDto> from(@Nullable List<Image> images){
        return images != null ? images.stream()
                .map(ImageDto::from)
                .collect(Collectors.toList()) : new ArrayList<>();
    }
}
