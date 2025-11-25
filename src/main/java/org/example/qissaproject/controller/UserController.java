package org.example.qissaproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.qissaproject.dto.ChildProfileCreateRequest;
import org.example.qissaproject.dto.ChildProfileCreateResponse;
import org.example.qissaproject.dto.ProfileGetResponse;
import org.example.qissaproject.model.Role;
import org.example.qissaproject.model.User;
import org.example.qissaproject.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/getProfile")
    public ProfileGetResponse getProfile(
            @AuthenticationPrincipal User user
    ) {
        System.out.println(user.getFirstName());
        if (user.getRole().equals(Role.CHILD)){
            throw new AccessDeniedException("You cannot get profile for this user");
        }
        return userService.getProfile(user.getId());
    }

    @PostMapping("/createChildProfile")
    public ChildProfileCreateResponse createChildProfile(
            @AuthenticationPrincipal User user,
            @RequestBody ChildProfileCreateRequest request
    ) {
        return userService.createChildProfile(user, request);
    }


}
