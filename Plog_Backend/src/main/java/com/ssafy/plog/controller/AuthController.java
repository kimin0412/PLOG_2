package com.ssafy.plog.controller;

import java.util.Base64.Decoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.models.ERole;
import com.ssafy.plog.models.Role;
import com.ssafy.plog.models.User;
import com.ssafy.plog.payload.request.LoginRequest;
import com.ssafy.plog.payload.request.SignupRequest;
import com.ssafy.plog.payload.response.JwtResponse;
import com.ssafy.plog.payload.response.MessageResponse;
import com.ssafy.plog.repository.RoleRepository;
import com.ssafy.plog.repository.UserRepository;
import com.ssafy.plog.security.jwt.JwtUtils;
import com.ssafy.plog.service.UserDetailsImpl;
import com.sun.xml.messaging.saaj.util.Base64;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		System.out.println(loginRequest.getEmail());

		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getBirthday(),
												 userDetails.getPhone(),
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("이미 있는 ID 입니다😤"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("이미 있는 Email 입니다😤"));
		}

		// 새로운 가입자 생성. 
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: ADMIN Role not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: MODERATOR Role not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: USER Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@GetMapping("/withdraw")
	public ResponseEntity<?> withDrawUser(@RequestParam int id, @RequestParam String username) {
		System.out.println(id + "   " + username);
		System.out.println(id);
		if(userRepository.existsByUsername(username)) {
			User user = userRepository.findByUsername(username).get();
			userRepository.delete(user);
			//EntityManager em = null;
			//em.remove(user);
		}
		
		return null;
	}
	 
	@GetMapping("/editprofile")
	public ResponseEntity<?> EditUser(@RequestParam String email, @RequestParam String birthday, @RequestParam String phone, @RequestParam String password) {
		System.out.println( " " + email + " " + birthday + "  " + phone + "  " + password );
		User edituser = userRepository.findByEmail(email).get();
		if(birthday.equals("")) {
			birthday = edituser.getBirthday();
		}
		if(phone.equals("")) {
			phone = edituser.getPhone();
		}
		
		edituser.setBirthday(birthday);
		edituser.setPhone(phone);
		edituser.setPassword(encoder.encode(password));
		userRepository.save(edituser);
		System.out.println(edituser.getUsername() + " " + edituser.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(edituser.getUsername(), password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getBirthday(),
												 userDetails.getPhone(),
												 roles));
	}
}