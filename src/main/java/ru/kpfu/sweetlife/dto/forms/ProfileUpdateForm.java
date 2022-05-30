package ru.kpfu.sweetlife.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.sweetlife.validation.EqualValues;
import ru.kpfu.sweetlife.validation.Password;
import ru.kpfu.sweetlife.validation.Username;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualValues(
        firstValue = "newPassword",
        secondValue = "repPassword",
        message = "{signUp.repPassword.validation}"
)
public class ProfileUpdateForm {

    @Username(message = "{signUp.username.validation}")
    private String nickname;
    private String currentPassword;

    @Password(message = "{signUp.password.validation}")
    private String newPassword;
    private String repPassword;

    private MultipartFile file;;
}
