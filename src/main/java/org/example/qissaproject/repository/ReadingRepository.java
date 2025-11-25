package org.example.qissaproject.repository;

import org.example.qissaproject.model.ReadingProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<ReadingProcess, Long> {

    ReadingProcess findByChild_Id(Long childId);
}
