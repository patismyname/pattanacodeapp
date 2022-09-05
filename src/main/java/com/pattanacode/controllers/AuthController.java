package com.pattanacode.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pattanacode.models.Customer;
import com.pattanacode.models.ERole;
import com.pattanacode.models.Role;
import com.pattanacode.models.User;
import com.pattanacode.payload.request.LoginRequest;
import com.pattanacode.payload.request.SignupRequest;
import com.pattanacode.payload.response.JwtResponse;
import com.pattanacode.payload.response.MessageResponse;
import com.pattanacode.payload.response.ModelResponse;
import com.pattanacode.repository.CustomerRepository;
import com.pattanacode.repository.RoleRepository;
import com.pattanacode.repository.UserRepository;
import com.pattanacode.security.jwt.JwtTokenProvider;
import com.pattanacode.services.UserDetailsImpl;
import com.pattanacode.utils.CorrelationInterceptor;
import com.pattanacode.utils.DateTimeUtils;
import com.pattanacode.utils.RequestCorrelation;
import com.pattanacode.utils.Utility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "Operations related to users")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/v1")
public class AuthController {
	private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	private  final  static  String Authorized="Authorized.";
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtTokenProvider jwtUtils;

	
	//@PostMapping("/login")
   @Operation(summary = "Login the user into the api")
 // @PostMapping("/login")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		logger.info("username:"+loginRequest.getUsername()+" password:"+loginRequest.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		//logger.info("jwt:"+jwt);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		/*
		 * List<User> roles = userDetails.getAuthorities().stream() .map(item ->
		 * item.getAuthority()) .collect(Collectors.toList());
		 */
		
		List<Role> roles =new ArrayList<>();
		//roles.add(ERole.ROLE_ADMIN);
		//String[] rolesString= {""} ;
		
		Role role=new Role();
		role.setName(ERole.ROLE_ADMIN);
		roles.add(role);
		
		//System.out.println("Http Status:"+HttpStatus.OK);
		userDetails.setStatus(200);
		userDetails.setExpires(3600);
		userDetails.setMessage(Authorized);
		
		JwtResponse jwtResponse=new JwtResponse(HttpStatus.OK,jwt,
												 userDetails.getId(), 
												// loginRequest.getUsername(),
												 userDetails.getEmail(),
												 userDetails.getStatus(),
												 userDetails.getMessage(),
												 userDetails.getExpires(),
												 roles);

		return ResponseEntity.ok(jwtResponse);
	}

    @Operation(summary = "Create a new user by email address")
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		/*
		 * if
		 * (userRepository.existsByUsername(Utility.getUserNameFromEmail(signUpRequest.
		 * getEmail()))) { return ResponseEntity .badRequest() .body(new
		 * MessageResponse(signUpRequest.getUsername()
		 * ,"Error: Username is already taken!")); }//if
		 */
    	String correlationId  = RequestCorrelation.getCorrelationId();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RequestCorrelation.CORRELATION_ID, correlationId);
        
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			logger.info("correlationId:"+correlationId);
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(correlationId, signUpRequest.getEmail(),"Error: This Email is already in used!"));
		}//if

		// Create new user's account
		//Utility.getUserNameFromEmail(signUpRequest.getEmail())
		User user = new User(signUpRequest.getEmail(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 encoder.encode(signUpRequest.getPinCode()),
							 signUpRequest.getPasswordType(),
							 DateTimeUtils.getSystemDate(),
							 DateTimeUtils.getExpirationDate(),
							 signUpRequest.getFlagStatus(),
							 DateTimeUtils.getSystemDate(),
							 "System");
		//customer
		Customer customer=new Customer(signUpRequest.getFirstName(),
				signUpRequest.getLastName(),
				signUpRequest.getNickName(),
				signUpRequest.getBirthDate(),
				signUpRequest.getEmail(),
				signUpRequest.getMobileNumber(),
				signUpRequest.getCustomerType(),
				signUpRequest.getFlagStatus(),
				DateTimeUtils.getSystemDate(),
				 "System"
				);

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: ROLE_USER is not found."));
			//logger.info("userRole=>"+userRole);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				logger.info("role=>"+role);
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: ROLE_ADMIN is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: ROLE_MODERATOR is not found."));
					roles.add(modRole);

					break;
				default:
					logger.info("ERole.ROLE_USER="+ERole.ROLE_USER);
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: ROLE_USER is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		
		customerRepository.save(customer);
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(RequestCorrelation.getCorrelationId(),signUpRequest.getEmail(), " Registered success."));
	}//end method POSt
	
    
 
    
    @Operation(summary = "Update the user into the api")
	//@PutMapping("/register")
	@RequestMapping(method=RequestMethod.PUT, value="/register")
	public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signUpRequest) {

		User user =new User();
		logger.info("id="+signUpRequest.getId());
		user.setUserId(signUpRequest.getId());
		user.setEmail(signUpRequest.getEmail());
		user.setStartDate(signUpRequest.getStartDate());
		user.setExpiredDate(signUpRequest.getExpiredDate());
		user.setCreatedDate(new Date());
		user.setFlagStatus("U");
		user.setCreatedBy("System");
		

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: ROLE_USER is not found."));
			logger.info("userRole=>"+userRole);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				logger.info("role=>"+role);
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: ROLE_ADMIN is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: ROLE_MODERATOR is not found."));
					roles.add(modRole);

					break;
				default:
					logger.info("ERole.ROLE_USER="+ERole.ROLE_USER);
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: ROLE_USER is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse(RequestCorrelation.getCorrelationId(),signUpRequest.getUsername(),"  Registered success."));
	}//end method PUT
	
    @Operation(summary = "Register Flag Status the user into the api")
	@PatchMapping("/registerFlagStatus/{id}")
	public ResponseEntity<?> updateFlagStatusUser(@PathVariable (value = "id") String id) {
	
		User userDetails =new User();
		logger.info("userId="+id);
		Optional<User> user=userRepository.findById(id);
		userDetails.setUsername(user.get().getUsername());
		userDetails.setPassword(user.get().getPassword());
		userDetails.setEmail(user.get().getEmail());
		userDetails.setStartDate(user.get().getStartDate());
		userDetails.setExpiredDate(user.get().getExpiredDate());
		userDetails.setCreatedBy("System");
		userDetails.setFlagStatus(user.get().getFlagStatus());
		userDetails.setCreatedDate(new Date());
		userRepository.save(userDetails);

		return ResponseEntity.ok(new MessageResponse(RequestCorrelation.getCorrelationId(),id," is updated Flag Status User successfully."));
	}//end method Put to PATCH
	
	
	//@RequestMapping(method=RequestMethod.DELETE, value="/register")
    @Operation(summary = "Delete the user into the api")
	@DeleteMapping("/register/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable (value = "id") String id) {
	
		//User user =new User();
		logger.info("deleteUser id="+id);
		//user.setId(id);
		userRepository.deleteById(id);

		return ResponseEntity.ok(new MessageResponse(RequestCorrelation.getCorrelationId(),id," is deleted successfully."));
	}//end method Delete
	
}//end class

