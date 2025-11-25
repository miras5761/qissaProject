package org.example.qissaproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.qissaproject.model.Story;
import org.example.qissaproject.model.User;
import org.example.qissaproject.service.StoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @GetMapping("/getStories")
    public List<Story> getStories(
            @RequestParam(required = false) String ageGroup,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String language
    ) {
        return storyService.getAllStories(ageGroup, genre, language);
    }

    @GetMapping("/getStory/{storyId}")
    public Story getStory(@PathVariable Long storyId) {
        return storyService.getStoryById(storyId);
    }

    @PostMapping("/createStory")
    public Story createStory(
            @AuthenticationPrincipal User user,
            @RequestBody Story story
    ) {
        return storyService.createStory(user, story);
    }

    @DeleteMapping("/deleteStory{storyId}")
    public void deleteStory(
            @PathVariable Long storyId
    ) {
        storyService.deleteStory(storyId);
    }
}
