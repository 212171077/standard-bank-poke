package za.co.standard.bank.pokemon.payload;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest implements Serializable {

    @NotBlank
    @Size(max = 250)
    @Email
    private String email;
    private String idNumber;
    private String name;
    private String surname;
    private String cellNumber;
    private String password;
    private List<String> roles;
}