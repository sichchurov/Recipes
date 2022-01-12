package recipes.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignUpDto {
    private String name;
    private String username;

    @NotNull
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @Size(min = 8)
    @NotBlank
    @NotEmpty
    private String password;
}
