package com.vlsu.ispi.repositories;

import com.vlsu.ispi.models.Feedback;
import com.vlsu.ispi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    Feedback findById(int id);
    List<Feedback> findByBarberId(int id);
    List<Feedback> findByBarberIdAndClientId(int barber, int client);
}
