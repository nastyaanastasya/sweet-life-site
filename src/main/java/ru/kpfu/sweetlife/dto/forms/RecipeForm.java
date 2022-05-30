package ru.kpfu.sweetlife.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.sweetlife.dto.IngredientDto;
import ru.kpfu.sweetlife.dto.UserDto;

import javax.validation.constraints.Null;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeForm {

    private String title;
    private String description;

    @Nullable
    private Integer hours;
    private Integer minutes;
    private Long authorId;

    private String[] ingredient;
    private String[] amount;
    private String[] unit;

    private List<MultipartFile> images;
}
