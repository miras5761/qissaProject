package org.example.qissaproject.repository;

import org.example.qissaproject.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query("SELECT s FROM Story s WHERE " +
            "(:ageGroup IS NULL OR s.ageGroup = :ageGroup) AND " +
            "(:genre IS NULL OR s.genre = :genre) AND " +
            "(:language IS NULL OR s.language = :language)")
    List<Story> findByFilters(@Param("ageGroup") String ageGroup,
                              @Param("genre") String genre,
                              @Param("language") String language);
}
