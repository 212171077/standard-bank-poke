package za.co.standard.bank.pokemon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import za.co.standard.bank.pokemon.model.entities.Role;
import za.co.standard.bank.pokemon.model.entities.User;
import za.co.standard.bank.pokemon.model.enums.ERole;
import za.co.standard.bank.pokemon.payload.ChangePassRequest;
import za.co.standard.bank.pokemon.payload.LoginRequest;
import za.co.standard.bank.pokemon.payload.SignupRequest;
import za.co.standard.bank.pokemon.payload.UserDetails;
import za.co.standard.bank.pokemon.repository.RoleRepository;
import za.co.standard.bank.pokemon.repository.UserRepository;

@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:test",
    "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"
})
public class CommonTestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTestUtil.class);
    @Autowired
    private RoleRepository roleRepository;

    public SignupRequest getAdminSignupRequest() {
        List<String> roles = new ArrayList<>();
        roles.add(ERole.ROLE_USER.name());
        roles.add(ERole.ROLE_ADMIN.name());
        return new SignupRequest("admin@gmail.com", "9304246082082",
            "Christoph", "Sibiya", "0729266076", "Admin12345", roles);
    }

    public LoginRequest getAdminLoginRequest() {
        return new LoginRequest("admin@gmail.com", "Admin12345");
    }


    public SignupRequest getSignupRequest() {
        return new SignupRequest("venussibiya@gmail.com", "9304246082082",
            "Christoph", "Sibiya", "0729266076", "Password@123", null);
    }


    public LoginRequest getLoginRequest() {
        return new LoginRequest("venussibiya@gmail.com", "Password@123");
    }

    public UserDetails getUserDetails(Long id) {
        return new UserDetails(id, "NewName", "NewSurname", "venussibiya@gmail.com", null,
            "9304246082082", "0729266076");
    }

    public ChangePassRequest getChangePassRequest(Long id) {
        return new ChangePassRequest(id, "Invalid@123", "Password@2022");
    }


    public LoginRequest getInvalidLoginRequest() {
        return new LoginRequest("invalidemail@gmail.com", "Password@123");
    }

    public void saveRoles() {
        try {
            Role role1 = new Role();
            role1.setName(ERole.ROLE_USER);
            roleRepository.saveAndFlush(role1);

            role1 = new Role();
            role1.setName(ERole.ROLE_ADMIN);
            roleRepository.saveAndFlush(role1);
        } catch (Exception e) {
            LOGGER.error("ERROR: ", e);
        }
    }

    public static List<String> ENDPOINT_LIST = Arrays.asList("ability"
        , "berry"
        , "berry-firmness"
        , "berry-flavor"
        , "characteristic"
        , "contest-effect"
        , "contest-type"
        , "egg-group"
        , "encounter-condition"
        , "encounter-condition-value"
        , "encounter-method"
        , "evolution-chain"
        , "evolution-trigger"
        , "gender"
        , "generation"
        , "growth-rate"
        , "item"
        , "item-attribute"
        , "item-category"
        , "item-fling-effect"
        , "item-pocket"
        , "language"
        , "location"
        , "location-area"
        , "machine"
        , "move"
        , "move-ailment"
        , "move-battle-style"
        , "move-category"
        , "move-damage-class"
        , "move-learn-method"
        , "move-target"
        , "nature"
        , "pal-park-area"
        , "pokeathlon-stat"
        , "pokedex"
        , "pokemon"
        , "pokemon-color"
        , "pokemon-form"
        , "pokemon-habitat"
        , "pokemon-shape"
        , "pokemon-species"
        , "region"
        , "stat"
        , "super-contest-effect"
        , "type"
        , "version"
        , "version-group");
}
