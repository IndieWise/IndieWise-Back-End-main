package com.api.indiewise.service;

import com.api.indiewise.Security.JwtUtil;
import com.api.indiewise.dto.UserDto;
import com.api.indiewise.models.UserModel;
import com.api.indiewise.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Object cadastrarUsuario(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Senha e confirmação de senha não coincidem");
        }

        Optional<UserModel> existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            return "Usuário já existe";
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setImageId(userDto.getImageId());
        newUser.setImagenFundoId(userDto.getImagenFundoId());

        String senhaCriptografada = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        newUser.setPassword(senhaCriptografada);

        return userRepository.save(newUser);
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
    public Optional<UserModel> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public boolean validarToken(String token) {
        return jwtUtil.validateToken(token);
    }

    public String extrairUsernameDoToken(String token) {
        return jwtUtil.extractUsername(token);
    }

    public Optional<UserModel> findById(String id) {
        return userRepository.findById(id);
    }
    public List<UserModel> findAllProf(){return userRepository.findByConhecimentoIsNotNull();}

    public void excluirUsuario(String id) {
        userRepository.deleteById(id);
    }

    public Optional<UserModel> atualizarUsuario(String id, UserDto userDto) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();

            user.setConhecimento(userDto.getConhecimento());
            user.setComoAdquiriu(userDto.getComoAdquiriu());
            user.setPrecoAula(userDto.getPrecoAula());
            user.setTituloPerfil(userDto.getTituloPerfil());
            user.setDescricaoPerfil(userDto.getDescricaoPerfil());

            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

}

