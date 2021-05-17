package api.controller;

import api.DTO.JwtResponse;
import api.DTO.LoginRequest;
import api.DTO.MessageResponse;
import api.DTO.SignupRequest;
import api.common.JwtUtils;
import api.entity.user;
import api.repository.userRepository;
import api.service.UserDetailsImpl;
import api.service.userService;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    @Autowired
    userService userS;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    userRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> signinUser(@Validated @RequestBody LoginRequest loginRequest) throws ParseException {
//        System.out.println("account :"+loginRequest.getUsername() +"-"+ loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                userDetails.getUsername(),
                userDetails.getName(),
                jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Create new user's account
        String linkphotodefault = "linkphotodefault";

        user u = new user(signUpRequest.getUsername(),
                signUpRequest.getName(),
                encoder.encode(signUpRequest.getPassword()),linkphotodefault,signUpRequest.getBirthday(),signUpRequest.getPhone());
        // create setting
        settingController settingCon = new settingController();
        settingCon.create(u.getId());

        return (ResponseEntity<?>) userS.create(u);
    }

}
