package com.example.bookstore_api.service;

import com.example.bookstore_api.model.Feedback;
import com.example.bookstore_api.model.Product;
import com.example.bookstore_api.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            assert authentication != null;
            return authentication.getName();
        }
    }

    public Feedback submitFeedback(Feedback feedback) {
        String username = getCurrentUsername();
        feedback.setUsername(username);
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackForProduct(Long productId) {
        return feedbackRepository.findByProductId(productId);
    }
}
