package za.co.standard.bank.pokemon.payload;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse implements Serializable {

    private boolean success;
    private String message;
}