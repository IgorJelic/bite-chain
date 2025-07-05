package com.bitechain.restorauntservice.repository;

import com.bitechain.restorauntservice.model.WorkingHour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkingHoursRepository extends JpaRepository<WorkingHour, UUID> {
}
