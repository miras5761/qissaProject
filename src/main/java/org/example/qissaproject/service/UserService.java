package org.example.qissaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.qissaproject.dto.ChildProfileCreateRequest;
import org.example.qissaproject.dto.ChildProfileCreateResponse;
import org.example.qissaproject.dto.ProfileGetResponse;
import org.example.qissaproject.model.ChildProfile;
import org.example.qissaproject.model.User;
import org.example.qissaproject.repository.ChildProfileRepository;
import org.example.qissaproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ChildProfileRepository childProfileRepository;

    public ProfileGetResponse getProfile(Long userId) {
        User user = userRepository.getById(userId);
        return ProfileGetResponse.builder()
                .email(user.getEmail())
                .firstname(user.getFirstName())
                .lastname(user.getFirstName())
                .children(user.getChildren())
                .stories(user.getStories())
                .build();
    }

    public ChildProfileCreateResponse createChildProfile(User user, ChildProfileCreateRequest request) {
        User parent = userRepository.getById(user.getId());
        ChildProfile profile = ChildProfile.builder()
                .name(request.getChildName())
                .age(request.getAge())
                .level(0)
                .parent(parent)
                .build();
        childProfileRepository.save(profile);
        parent.getChildren().add(profile);
        userRepository.save(parent);
        return ChildProfileCreateResponse.builder()
                .childName(request.getChildName())
                .age(request.getAge())
                .level(0)
                .build();
    }
}
