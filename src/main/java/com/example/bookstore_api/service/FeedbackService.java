package com.example.bookstore_api.service;

import com.example.bookstore_api.model.Feedback;
import com.example.bookstore_api.model.Product;
import com.example.bookstore_api.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback submitFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackForProduct(Long productId) {
        return feedbackRepository.findByProductId(productId);
    }
}
