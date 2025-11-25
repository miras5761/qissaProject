package org.example.qissaproject.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.qissaproject.model.ChildProfile;
import org.example.qissaproject.model.ReadingProcess;
import org.example.qissaproject.model.Story;
import org.example.qissaproject.repository.ChildProfileRepository;
import org.example.qissaproject.repository.ReadingRepository;
import org.example.qissaproject.repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReadingService {

    private final ReadingRepository readingRepository;
    private final ChildProfileRepository childProfileRepository;
    private final StoryRepository storyRepository;

    public ReadingProcess startReading(Long storyId, Long childId) {
        ChildProfile childProfile = childProfileRepository.findById(childId)
                .orElseThrow(EntityNotFoundException::new);
        Story story = storyRepository.findById(storyId)
                .orElseThrow(EntityNotFoundException::new);
        ReadingProcess readingProcess = readingRepository.findByChild_Id(childId);
        if (readingProcess == null) {
            readingProcess = new ReadingProcess();
            readingProcess.setChild(childProfile);
        }
        readingProcess.setStartedAt(LocalDateTime.now());
        readingProcess.setLastReadAt(LocalDateTime.now());
        readingProcess.setStory(story);
        readingProcess.setCurrentPosition(0);
        readingRepository.save(readingProcess);
        return readingProcess;
    }

    public ReadingProcess updateProgress(Long childId, Integer position) {
        ReadingProcess progress = readingRepository.findByChild_Id(childId);
        progress.setCurrentPosition(position);
        progress.setLastReadAt(LocalDateTime.now());

        Story story = progress.getStory();
        if (position >= story.getPageAmount() * 0.9) {
            progress.setCompleted(true);
            progress.setCompletedAt(LocalDateTime.now());

            ChildProfile childProfile = childProfileRepository.findById(childId)
                    .orElseThrow(EntityNotFoundException::new);
            childProfile.setLevel(childProfile.getLevel() + 1);
            childProfileRepository.save(childProfile);
        }
        return progress;
    }
}
