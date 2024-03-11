package com.simplestore.UserService.services;

import com.simplestore.UserService.dtos.LoginResponseDTO;
import com.simplestore.UserService.dtos.UserDTO;
import com.simplestore.UserService.exceptions.NoValidSessionException;
import com.simplestore.UserService.exceptions.PasswordIncorrectException;
import com.simplestore.UserService.exceptions.SessionLimitExceededException;
import com.simplestore.UserService.exceptions.UserNotFoundException;
import com.simplestore.UserService.mappers.UserMapper;
import com.simplestore.UserService.models.Session;
import com.simplestore.UserService.models.SessionStatus;
import com.simplestore.UserService.models.User;
import com.simplestore.UserService.repositories.SessionRepository;
import com.simplestore.UserService.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;


import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<Session> getAllSessions() {

        return sessionRepository.findAll();
    }

    public List<UserDTO> getAllUsers() {

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users) userDTOS.add(UserMapper.userToUserDTO(user));
        return userDTOS;
    }

    public LoginResponseDTO login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) throw new UserNotFoundException(email);

        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) throw new PasswordIncorrectException();

        Date createdDate = new Date(LocalDate.now().toEpochDay());
        Date expiredDate = new Date(LocalDate.now().plusDays(1).toEpochDay());

        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("email", user.getEmail());
        jsonForJWT.put("roles", user.getRoles());
        jsonForJWT.put("createdAt", createdDate);
        jsonForJWT.put("expiryAt", expiredDate);

        MacAlgorithm alg = Jwts.SIG.HS256; // HS256 algo added for JWT
        SecretKey key = alg.key().build(); // generating the secret key

        String token = Jwts.builder().claims(jsonForJWT).signWith(key, alg).compact();

        List<Session> sessions = sessionRepository.findAllByUser_Id(user.getId());
        if(sessions.size() >= 2) {
            throw new SessionLimitExceededException(sessions.get(0).getLoginAt(), sessions.get(1).getLoginAt());
        }

        Session session = new Session();
        session.setUser(user);
        session.setLoginAt(createdDate);
        session.setExpireAt(expiredDate);
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, token);

        UserDTO userDTO = UserMapper.userToUserDTO(user);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setHeaders(headers);
        loginResponseDTO.setUserDTO(userDTO);

        return loginResponseDTO;
    }

    public ResponseEntity<Void> logOut(long userId, String token) {

        Optional<Session> optionalSession = sessionRepository.findByUser_IdAndToken(userId, token);
        if(optionalSession.isEmpty()) throw new NoValidSessionException();
        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return null;
    }

    public UserDTO signUp(String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);

        return UserMapper.userToUserDTO(savedUser);
    }

    public SessionStatus validate(String token, long userId) {

        Optional<Session> optionalSession = sessionRepository.findByUser_IdAndToken(userId, token);
        if(optionalSession.isEmpty() || optionalSession.get().getSessionStatus().equals(SessionStatus.ENDED)) throw new NoValidSessionException();

        return SessionStatus.ACTIVE;
    }
}
