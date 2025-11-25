package org.example.qissaproject.service;

import lombok.RequiredArgsConstructor;
import org.example.qissaproject.model.Story;
import org.example.qissaproject.model.User;
import org.example.qissaproject.repository.StoryRepository;
import org.example.qissaproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    public List<Story> getAllStories(String ageGroup, String genre, String language) {
        return storyRepository.findByFilters(ageGroup, genre, language);

    }

    public Story getStoryById(Long storyId) {
        return storyRepository.getById(storyId);
    }

    public Story createStory(User user, Story story) {
        User author = userRepository.findById(user.getId())
                .orElseThrow();
        Story newStory = Story.builder()
                .author(user)
                .title(story.getTitle())
                .language(story.getLanguage())
                .pageAmount(story.getPageAmount())
                .ageGroup(story.getAgeGroup())
                .genre(story.getGenre())
                .build();
        storyRepository.save(newStory);
        author.getStories().add(newStory);
        userRepository.save(user);
        return newStory;
    }

    public void deleteStory(Long storyId) {
        storyRepository.deleteById(storyId);
    }
}
