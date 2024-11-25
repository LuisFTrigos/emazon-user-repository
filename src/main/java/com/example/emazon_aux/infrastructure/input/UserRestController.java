package com.example.emazon_aux.infrastructure.input;


import com.example.emazon_aux.aplication.dto.request.UserRequestDto;
import com.example.emazon_aux.aplication.dto.response.GenericResponse;
import com.example.emazon_aux.aplication.dto.response.UserResponseDto;
import com.example.emazon_aux.aplication.handler.IUserHandler;
import com.example.emazon_aux.domain.util.constants.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class UserRestController {

    private final IUserHandler userHandler;

    @Secured({Constants.ADMIN_ROLE})
    @Operation(summary = "Add a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "403", description = "The user must be of legal age",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "409", description = "User already exists",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for user creation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/create")
    public ResponseEntity<GenericResponse> saveCategory(@RequestBody UserRequestDto userRequestDto){
        GenericResponse genericResponse = userHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    }
    //crear cliente

    @Secured({Constants.ADMIN_ROLE})
    @Operation(summary = "Get all users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All users returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(
            @Parameter(description = "Number of the page to list providers") @RequestParam int page) {
        return ResponseEntity.ok(userHandler.getAllUsers(page));
    }
    @Secured({Constants.ADMIN_ROLE, Constants.OWNER_ROLE, Constants.AUX_ROLE, Constants.CUSTOMER_ROLE})
    @Operation(summary = "Get a user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User returned",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found with provider role",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return null;
    }
}
