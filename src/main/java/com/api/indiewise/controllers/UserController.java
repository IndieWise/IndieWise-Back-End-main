package com.api.indiewise.controllers;

import com.api.indiewise.dto.UserDto;
import com.api.indiewise.models.UserModel;
import com.api.indiewise.service.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/indiewise")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.cadastrarUsuario(userDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> autenticarUsuario(@RequestBody UserDto loginDto) {
        String token = userService.autenticarUsuario(loginDto.getUsername(), loginDto.getPassword());
        if (token != null) {
            Optional <UserModel> optionalUserModel = userService.findByUsername(loginDto.getUsername());
            return ResponseEntity.ok(optionalUserModel);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Object> obterUsuarioPorId(@PathVariable String id) {
        Optional<UserModel> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object> excluirContaPorId(@PathVariable String id){
        try {
            userService.excluirUsuario(id);
            return ResponseEntity.ok("Usuário excluído com sucesso");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário");
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable String id, @RequestBody UserDto userDto) {
        Optional<UserModel> optionalUser = userService.atualizarUsuario(id, userDto);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @GetMapping("/professores")
    public List<UserModel> getAllUsers() {
        return userService.findAllProf();
        }
}