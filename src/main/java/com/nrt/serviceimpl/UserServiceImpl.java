package com.nrt.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nrt.Email.EmailSender;
import com.nrt.authentication.CustomUserDetails;
import com.nrt.authentication.CustomUserService;
import com.nrt.authentication.JwtUtil;
import com.nrt.entity.Role;
import com.nrt.entity.User;
import com.nrt.repository.UserRepository;
import com.nrt.request.LoginRequest;
import com.nrt.request.UserRequest;
import com.nrt.responce.LoginResponce;
import com.nrt.service.UserService;
import com.nrt.util.OTPGenerator;
import com.nrt.util.RandomPasswordGeneratorWithPattern;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailSender emailSender;

	private static final String OTP_COOKIE_NAME = "OTP";

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	public ResponseEntity<User> saveData(UserRequest userRequest) {
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		User user = new User();
		try {
			user.setEmail(userRequest.getRequestEmialId());
			user.setFirstName(userRequest.getRequestFirstName());
			user.setLastName(userRequest.getRequestLastName());
			user.setPhoneNo(userRequest.getRequestPhone());
			user.setStatus(0);
			user.setCreatedAt(date);
			user.setUpdatedAt(date);
			user.setRole(new Role("ROLE_" + userRequest.getRequestRole().toUpperCase()));
			String generateRandomPassword = RandomPasswordGeneratorWithPattern.generateRandomPassword();
			String hashedPassword = passwordEncoder.encode(generateRandomPassword);
			user.setPassword(hashedPassword);
			System.out.println(generateRandomPassword);
			user = userRepository.save(user);
			Map<String, String> sourceMap = new HashMap<String, String>();
			sourceMap.put("password", generateRandomPassword);
			sourceMap.put("username", user.getFirstName() + " " + user.getLastName());
			sourceMap.put("role", user.getRole().getRole());
			sourceMap.put("usermail", user.getEmail());
			emailSender.sendEmail(user.getEmail(), "Registration Successfully Done..!", "/html/email/welcome-template",
					sourceMap);
		} catch (Exception e) {
			log.info("error inside the user register method");
			log.error(e.getLocalizedMessage());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<LoginResponce> generateToken(LoginRequest loginRequest) {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		String token = null;
		Optional<User> userOptional = java.util.Optional.empty();
		try {
			CustomUserDetails userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getUserId());
			token = jwtUtil.generateToken(userDetails);
			userOptional = userRepository.findByEmail(loginRequest.getUserId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(new LoginResponce(token, userOptional.get().getRole().getRole(),
				userOptional.get().getPasswordUpdated()));
	}

	public ResponseEntity<User> getUserById(long id) {

		Optional<User> user = userRepository.findById(id);
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}

	public ResponseEntity<User> updatePassword(String oldPassword, String newPassword) {
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		Optional<User> optionalUser = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			optionalUser = userRepository.findByEmail(userDetails.getUsername());
		}
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			String currentPassword = user.getPassword();

			if (passwordEncoder.matches(oldPassword, currentPassword)) {
				user.setPasswordUpdated(date);
				user.setPassword(passwordEncoder.encode(newPassword));
				userRepository.save(user);
				try {
					Map<String, String> sourceMap = new HashMap<String, String>();
					sourceMap.put("password", currentPassword);
					sourceMap.put("username", user.getFirstName() + " " + user.getLastName());
					emailSender.sendEmail(user.getEmail(), "Password Updated Successfully",
							"/html/email/update-password-template");
				} catch (MessagingException e) {
					e.printStackTrace();
				}

				return new ResponseEntity<>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public UserRequest getUserByEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserRequest userRequest = new UserRequest();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			Optional<User> userOption = userRepository.findByEmail(userDetails.getUsername());

			if (userOption.isPresent())
				userRequest.setRequestEmialId(userOption.get().getEmail());
			userRequest.setRequestFirstName(userOption.get().getFirstName());
			userRequest.setRequestLastName(userOption.get().getLastName());
			userRequest.setRequestPhone(userOption.get().getPhoneNo());
			userRequest.setRequestRole(userOption.get().getRole().getRole());
		}
		return userRequest;
	}

	@Override
	public Boolean SendOTP(String email, HttpServletResponse response) {
		Optional<User> userOption = userRepository.findByEmail(email);
		if (userOption.isPresent()) {
			Map<String, String> sourceMap = new HashMap<String, String>();
			String generateOTP = OTPGenerator.generateOTP(6);
			sourceMap.put("username", userOption.get().getFirstName() + " " + userOption.get().getLastName());
			Cookie tokenCookie = new Cookie("OTP", generateOTP);
			tokenCookie.setMaxAge(24 * 60 * 60);
			tokenCookie.setPath("/");
			response.addCookie(tokenCookie);
			sourceMap.put("OTP", generateOTP);
			try {
				emailSender.sendEmail(email, "FORGOT PASSWORD ", "/html/email/forgot-password-template", sourceMap);
				return Boolean.TRUE;
			} catch (MessagingException e) {
				return Boolean.FALSE;
			}
		}
		return Boolean.FALSE;

	}

	@Override
	public Boolean OTPVelidation(Integer otp, HttpServletRequest request) {
		String OTP = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(OTP_COOKIE_NAME)) {
					OTP = cookie.getValue();
					if (otp == Integer.parseInt(OTP))
						return Boolean.TRUE;

					else
						return Boolean.FALSE;
				}
			}

		}
		return Boolean.FALSE;

	}

	@Override
	public void ForgotPassword(String newPassword) {
		Date date = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserRequest userRequest = new UserRequest();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			Optional<User> userOption = userRepository.findByEmail(userDetails.getUsername());

			if (userOption.isPresent()) {
				User user = userOption.get();
				user.setPasswordUpdated(date);
				user.setPassword(passwordEncoder.encode(newPassword));
				userRepository.save(user);
			}
		}
	}
}
