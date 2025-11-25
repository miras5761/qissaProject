package org.example.qissaproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.qissaproject.model.ReadingProcess;
import org.example.qissaproject.service.ReadingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reading")
@RequiredArgsConstructor
public class ReadingController {

    private final ReadingService readingService;

    @PostMapping("/startReading/{storyId}/{childId}")
    public ReadingProcess startReading(
            @PathVariable Long storyId,
            @PathVariable Long childId
    ) {
        return readingService.startReading(storyId, childId);
    }

    @PutMapping("/updateReading/{childId}")
    public ReadingProcess updateReading(
            @PathVariable Long childId,
            @RequestParam Integer position
    ) {
       return readingService.updateProgress(childId, position);
    }
}
