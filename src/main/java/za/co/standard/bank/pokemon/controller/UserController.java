package za.co.standard.bank.pokemon.controller;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.standard.bank.pokemon.model.entities.Role;
import za.co.standard.bank.pokemon.model.entities.User;
import za.co.standard.bank.pokemon.model.enums.ERole;
import za.co.standard.bank.pokemon.payload.ChangePassRequest;
import za.co.standard.bank.pokemon.payload.JwtResponse;
import za.co.standard.bank.pokemon.payload.LoginRequest;
import za.co.standard.bank.pokemon.payload.MessageResponse;
import za.co.standard.bank.pokemon.payload.SignupRequest;
import za.co.standard.bank.pokemon.payload.UserDetails;
import za.co.standard.bank.pokemon.repository.RoleRepository;
import za.co.standard.bank.pokemon.repository.UserRepository;
import za.co.standard.bank.pokemon.security.jwt.JwtUtils;
import za.co.standard.bank.pokemon.security.services.impl.UserDetailsImpl;

@RestController
@RequestMapping("/api/pokemon/user/v1")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(
            new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUserProfile(@Valid @RequestBody SignupRequest signUpRequest) {

        LOGGER.info(
            "Creating user profile. User Details [Name: {}, Surname: {}, Email: {}, Id Number: {}], Cell Number: {}, Roles: {}",
            signUpRequest.getName(), signUpRequest.getSurname(), signUpRequest.getEmail(),
            signUpRequest.getIdNumber(), signUpRequest.getCellNumber(), signUpRequest.getRoles());

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                .body(new MessageResponse(false, "Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setIdNumber(signUpRequest.getIdNumber());
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setCellNumber(signUpRequest.getCellNumber());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        if (signUpRequest.getRoles() != null && !signUpRequest.getRoles().isEmpty()) {
            if (signUpRequest.getRoles().contains(ERole.ROLE_USER.name())) {
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }
            if (signUpRequest.getRoles().contains(ERole.ROLE_ADMIN.name())) {
                Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }
        }

        if (roles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        user.setRoles(roles);
        userRepository.save(user);

        LOGGER.info("Profile Created. [User Id: {}, User Id Number: {}}", user.getId(),
            signUpRequest.getIdNumber());

        return ResponseEntity.ok(new MessageResponse(true, "User profile created!"));
    }

    @GetMapping("/find_users/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findUserById(@PathVariable("id") Long id) {
        LOGGER.info("Finding user by ID: {}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserDetails userDetails = new UserDetails();
            copyProperties(optionalUser.get(), userDetails);
            Set<String> roles = new HashSet<>();
            for (Role role : optionalUser.get().getRoles()) {
                roles.add(role.getName().name());
            }
            userDetails.setRoles(roles);
            LOGGER.info("User Details: {}", userDetails);
            return ResponseEntity.ok(userDetails);
        } else {
            LOGGER.error("User not found, User ID: {}", id);
            return ResponseEntity.badRequest()
                .body(new MessageResponse(false, "Error: user details not found"));
        }
    }

    @PostMapping("/update_users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUsers(@Valid @RequestBody UserDetails userDetails) {
        LOGGER.info("Updating user details: {}", userDetails);
        Optional<User> optionalUser = userRepository.findById(userDetails.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            copyProperties(userDetails, optionalUser.get());
            userRepository.save(user);
            LOGGER.info("User details updated, User ID: {}, ID Number: {}", user.getId(),
                user.getIdNumber());
            return ResponseEntity.ok().body(new MessageResponse(true, "User details updated"));
        } else {
            LOGGER.error("Failed to update user details, User Details: {}", userDetails);
            return ResponseEntity.badRequest().body(new MessageResponse(false, "Invalid user ID"));
        }
    }

    @PostMapping("/change_password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(
        @Valid @RequestBody ChangePassRequest changePassRequest) {
        LOGGER.info("Updating user password, User ID: {}", changePassRequest.getUserId());
        Optional<User> optionalUser = userRepository.findById(changePassRequest.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encoder.matches(changePassRequest.getOldPassword(), user.getPassword())) {
                user.setPassword(encoder.encode(changePassRequest.getNewPassword()));
                userRepository.save(user);
                LOGGER.info("Password updated, User ID: {}", changePassRequest.getUserId());
                return ResponseEntity.ok()
                    .body(new MessageResponse(false, "Password updated successful"));
            } else {
                LOGGER.error("Invalid old password, User ID: {}", changePassRequest.getUserId());
                return ResponseEntity.badRequest()
                    .body(new MessageResponse(false, "Invalid old password"));
            }
        } else {
            LOGGER.error("User not found, User ID: {}", changePassRequest.getUserId());
            return ResponseEntity.badRequest().body(new MessageResponse(false, "Invalid user ID"));
        }
    }

}