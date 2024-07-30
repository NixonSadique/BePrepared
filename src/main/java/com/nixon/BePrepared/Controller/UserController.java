package com.nixon.BePrepared.Controller;


import com.nixon.BePrepared.DTO.Request.UserRequestDTO;
import com.nixon.BePrepared.DTO.Response.StatsResponse;
import com.nixon.BePrepared.DTO.Response.UserResponseDTO;
import com.nixon.BePrepared.Mapper.Mapper;
import com.nixon.BePrepared.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "5.User Controller")
public class UserController {

    private final Mapper mapper;

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){

        return new ResponseEntity<>(userService.createUser(
                mapper.mapUserRequestToUserModel(userRequestDTO)),
                HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(
                mapper.mapUserToResponseDtoList(userService.getAllUsers())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getAllUserById(@PathVariable Long id){
        return ResponseEntity.ok(
                mapper.mapUserToResponseDto(userService.getUserById(id))
        );
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats(){
        return ResponseEntity.ok(userService.getAllStats());
    }
}
