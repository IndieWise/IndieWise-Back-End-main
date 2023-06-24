package com.api.indiewise.service;

import com.api.indiewise.Security.JwtUtil;
import com.api.indiewise.dto.UserDto;
import com.api.indiewise.models.UserModel;
import com.api.indiewise.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public void cadastrarUsuario(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Senha e confirmação de senha não coincidem");
        }

        Optional<UserModel> existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Usuário já existe");
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());

        String senhaCriptografada = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        newUser.setPassword(senhaCriptografada);

        userRepository.save(newUser);
    }

    public String autenticarUsuario(String username, String password) {
        Optional<UserModel> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                return jwtUtil.generateToken(username);
            }
        }
        return null;
    }

    public boolean validarToken(String token) {
        return jwtUtil.validateToken(token);
    }

    public String extrairUsernameDoToken(String token) {
        return jwtUtil.extractUsername(token);
    }
}



















//package com.api.indiewise.service;
//
//import com.api.indiewise.Security.JwtUtil;
//import com.api.indiewise.dto.UserDto;
//import com.api.indiewise.models.UserModel;
//import com.api.indiewise.repositories.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserService {
//    private UserRepository userRepository;
//    private JwtUtil jwtUtil;
//
//    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//    public void cadastrarUsuario(UserDto userDto) {
//        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
//            throw new IllegalArgumentException("Senha e confirmação de senha não coincidem");
//        }
//
//        Optional<UserModel> existingUser = userRepository.findByUsername(userDto.getUsername());
//        if (existingUser.isPresent()) {
//            throw new IllegalArgumentException("Usuário já existe");
//        }
//
//        UserModel newUser = new UserModel();
//        newUser.setUsername(userDto.getUsername());
//        newUser.setEmail(userDto.getEmail());
//        newUser.setPassword(userDto.getPassword());
//        userRepository.save(newUser);
//    }
//
//    public String autenticarUsuario(String username, String password) {
//        Optional<UserModel> userOptional = userRepository.findByUsername(username);
//        if (userOptional.isPresent()) {
//            UserModel user = userOptional.get();
//            if (user.getPassword().equals(password)) {
//                return jwtUtil.generateToken(username);
//            }
//        }
//        return null;
//    }
//
//    public boolean validarToken(String token) {
//        return jwtUtil.validateToken(token);
//    }
//
//    public String extrairUsernameDoToken(String token) {
//        return jwtUtil.extractUsername(token);
//    }
//}