package org.example.qissaproject.repository;

import org.example.qissaproject.model.ChildProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildProfileRepository extends JpaRepository<ChildProfile, Long> {

}
