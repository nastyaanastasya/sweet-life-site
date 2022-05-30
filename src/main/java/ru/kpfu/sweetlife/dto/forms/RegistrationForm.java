package ru.kpfu.sweetlife.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.sweetlife.validation.EqualValues;
import ru.kpfu.sweetlife.validation.Password;
import ru.kpfu.sweetlife.validation.Username;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualValues(
        firstValue = "password",
        secondValue = "repPassword",
        message = "{signUp.repPassword.validation}"
)
public class RegistrationForm {

    @Username(message = "{signUp.username.validation}")
    private String nickname;

    @Email(message = "{signUp.email.validation}")
    private String username;

    @Password(message = "{signUp.password.validation}")
    private String password;
    private String repPassword;
}
