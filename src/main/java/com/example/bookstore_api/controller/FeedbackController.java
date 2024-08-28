package com.example.bookstore_api.controller;

import com.example.bookstore_api.model.Feedback;
import com.example.bookstore_api.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback = feedbackService.submitFeedback(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Feedback>> getFeedbackForProduct(@PathVariable Long productId) {
        List<Feedback> feedbackList = feedbackService.getFeedbackForProduct(productId);
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }
}
