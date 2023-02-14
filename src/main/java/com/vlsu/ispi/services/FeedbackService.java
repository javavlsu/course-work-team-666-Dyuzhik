package com.vlsu.ispi.services;

import com.vlsu.ispi.models.Feedback;
import com.vlsu.ispi.models.Record;
import com.vlsu.ispi.models.User;
import com.vlsu.ispi.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final RecordService recordService;
    private final UserServiceImpl userService;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, RecordService recordService, UserServiceImpl userService){
        this.feedbackRepository = feedbackRepository;
        this.recordService = recordService;
        this.userService = userService;
    }

    public boolean isRecord(int client, int barber){
        List<Record> records = recordService.findByClientBarberStatus(client,barber,4);
        List<Feedback> feedbacks = feedbackRepository.findByBarberIdAndClientId(barber,client);
        boolean flag = (!records.isEmpty()) && (feedbacks.isEmpty());
        return flag;
    }

    public int findBarberByFeedback(int id){
        Feedback feedback = feedbackRepository.findById(id);
        return feedback.getBarber().getId();
    }

    @Transactional
    public void save(User client, int barber, Feedback feedback){
        feedback.setClient(client);
        feedback.setBarber(userService.findOne(barber));
        feedbackRepository.save(feedback);
    }

    public List<Feedback> findAll(int barber){
        return feedbackRepository.findByBarberId(barber);
    }

    public Feedback findOne(int id){
        return feedbackRepository.findById(id);
    }

    @Transactional
    public void update(Feedback feedback, int id){
        Feedback old = feedbackRepository.findById(id);
        feedback.setBarber(old.getBarber());
        feedback.setId(old.getId());
        feedback.setClient(old.getClient());
        feedbackRepository.save(feedback);
    }

    @Transactional
    public void delete(int id){
        Feedback feedback = feedbackRepository.findById(id);
        feedbackRepository.delete(feedback);
    }
}
