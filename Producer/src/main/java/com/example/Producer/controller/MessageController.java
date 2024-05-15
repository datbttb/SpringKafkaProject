package com.example.Producer.controller;

import com.example.Producer.entity.Message;
import com.example.Producer.service.Impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/message")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/save")
    public ResponseEntity<?> saveMessage(@RequestBody Message message){
        try {
            message.setSendTime(new Date());
            CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send("message", message);
            completableFuture.whenComplete((result, ex) -> {
                if (ex != null) {
                    message.setStatusKafka(true);
                    messageService.saveMessage(message);
                } else {
                    message.setStatusKafka(false);
                    messageService.saveMessage(message);
                }
            });
            return ResponseEntity.ok().body("Message was send");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Message was not send");
        }
    }
}
