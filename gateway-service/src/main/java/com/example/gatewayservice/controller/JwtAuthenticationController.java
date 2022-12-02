package com.example.gatewayservice.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.example.gatewayservice.models.AuthenticationStatus;
import com.example.gatewayservice.models.ErrorResponseDto;
import com.example.gatewayservice.models.AuthenticationRequest;
import com.example.gatewayservice.models.JwtResponse;
import com.example.gatewayservice.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class JwtAuthenticationController {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RestTemplate restTemplate;

    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationStatus status = authenticate(authenticationRequest);

        if (!status.getIsAuthenticated()) {
            List<String> details = new ArrayList<>();
            details.add(status.getMessage());
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, "uri");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        final String token = jwtTokenUtil.generateToken(authenticationRequest.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private AuthenticationStatus authenticate(AuthenticationRequest authRequest ) {
        AuthenticationStatus status;

        Boolean isUserValid = restTemplate.postForObject("http://account-service:9091/accounts/check/" ,authRequest,Boolean.class);
        if (!isUserValid) {
            status = new AuthenticationStatus(false, "Invalid Username/Password");
        }
        else {
            status = new AuthenticationStatus(true, "Authentication Successful");
        }

        return status;
    }
}
