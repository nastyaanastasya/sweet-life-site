package ru.kpfu.sweetlife.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.sweetlife.dto.UserDto;
import ru.kpfu.sweetlife.dto.forms.RegistrationForm;
import ru.kpfu.sweetlife.services.UserService;

import java.util.List;

@RestController("/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody RegistrationForm form) {
        userService.register(form);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/ban/{id}")
    public ResponseEntity<?> banUser(@PathVariable("id") @RequestBody Long userId) {
        userService.banUser(userId);
        return ResponseEntity
                .ok()
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/unban/{id}")
    public ResponseEntity<?> unbanUser(@PathVariable("id") @RequestBody Long userId) {
        userService.unbanUser(userId);
        return ResponseEntity
                .ok()
                .build();
    }
}
