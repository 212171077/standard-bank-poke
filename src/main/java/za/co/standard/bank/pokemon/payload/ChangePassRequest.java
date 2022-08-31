package za.co.standard.bank.pokemon.payload;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassRequest implements Serializable {

    @NotNull
    private Long userId;
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;

}