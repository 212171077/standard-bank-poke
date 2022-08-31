package za.co.standard.bank.pokemon.payload;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}