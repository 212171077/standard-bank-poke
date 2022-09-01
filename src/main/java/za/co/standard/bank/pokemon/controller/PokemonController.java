package za.co.standard.bank.pokemon.controller;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.standard.bank.pokemon.client.PokemonClient;
import za.co.standard.bank.pokemon.exception.InvalidUrlException;
import za.co.standard.bank.pokemon.model.dto.PokemonResources;
import za.co.standard.bank.pokemon.model.dto.PokemonResponse;
import za.co.standard.bank.pokemon.model.entities.Role;
import za.co.standard.bank.pokemon.model.enums.ERole;
import za.co.standard.bank.pokemon.payload.MessageResponse;
import za.co.standard.bank.pokemon.repository.RoleRepository;
import za.co.standard.bank.pokemon.utils.CommonUtil;
import za.co.standard.bank.pokemon.utils.ConstantUtil;


@RestController
@RequestMapping("/api/pokemon/v1")
public class PokemonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonController.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PokemonClient pokemonClient;
    @Autowired
    private CommonUtil commonUtil;


    @GetMapping("/get-resources")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getResources() {
        PokemonResources resources = new PokemonResources();
        String jsonResponse = pokemonClient.callGetMethod(ConstantUtil.POKE_API_HOSTNAME);
        if (jsonResponse != null) {
            try {
                return ResponseEntity.ok(commonUtil.convertJsonToObject(jsonResponse, resources));
            } catch (IOException e) {
                LOGGER.info(
                    "Service unavailable, we are unable to call convert json to java object");
                return ResponseEntity.badRequest()
                    .body(new MessageResponse(false, "Service unavailable"));
            }
        } else {
            LOGGER.info("Service unavailable, we are unable to call {}",
                ConstantUtil.POKE_API_HOSTNAME);
            return ResponseEntity.badRequest()
                .body(new MessageResponse(false, "Service unavailable"));
        }
    }


    @GetMapping("/get-pokemon/{endpoint}/{id-or-name}")
    public ResponseEntity<?> getPokemonDetailsByEndpointAndIdOrName(@RequestParam String endpoint,
        @RequestParam String idOrName) {
        PokemonResponse response = new PokemonResponse();
        try {
            String jsonResponse = pokemonClient.callGetMethod(buildUrl(endpoint, idOrName));
            if (jsonResponse != null) {
                return ResponseEntity.ok(commonUtil.convertJsonToObject(jsonResponse, response));
            } else {
                LOGGER.info("Service unavailable, we are unable to call {}",
                    ConstantUtil.POKE_API_HOSTNAME);
                return ResponseEntity.badRequest()
                    .body(new MessageResponse(false, "Service unavailable"));
            }
        } catch (Exception e) {
            LOGGER.info("Error:", e);
            return ResponseEntity.badRequest()
                .body(new MessageResponse(false, "Service unavailable"));
        }
    }

    @GetMapping("/get-pokemon/{endpoint}")
    public ResponseEntity<?> getPokemonDetailsByEndpoint(@RequestParam String endpoint) {
        PokemonResponse response = new PokemonResponse();
        try {
            String jsonResponse = pokemonClient.callGetMethod(buildUrl(endpoint, null));
            if (jsonResponse != null) {
                return ResponseEntity.ok(commonUtil.convertJsonToObject(jsonResponse, response));
            } else {
                LOGGER.info("Service unavailable, we are unable to call {}",
                    ConstantUtil.POKE_API_HOSTNAME);
                return ResponseEntity.badRequest()
                    .body(new MessageResponse(false, "Service unavailable"));
            }
        } catch (Exception e) {
            LOGGER.info("Error:", e);
            return ResponseEntity.badRequest()
                .body(new MessageResponse(false, "Service unavailable"));
        }
    }

    private String buildUrl(String endpoint, String idOrName) throws InvalidUrlException {
        String url;
        if (endpoint == null) {
            throw new InvalidUrlException("Invalid endpoint");
        } else if (endpoint != null && idOrName != null) {
            url = ConstantUtil.POKE_API_HOSTNAME.concat(
                "/".concat(endpoint).concat("/".concat(idOrName)));
        } else {
            url = ConstantUtil.POKE_API_HOSTNAME.concat("/".concat(endpoint));
        }
        LOGGER.info("Building URL: {}", url);
        return url;
    }

}
