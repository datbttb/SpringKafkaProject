package com.example.Consumer.service.Impl;

import com.example.Consumer.entity.Message;
import com.example.Consumer.repository.MessageRepo;
import com.example.Consumer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepo messageRepo;

    @Override
    public void saveMessage(Message message) {
        messageRepo.save(message);
    }


    @KafkaListener(id = "messageGroup", topics = "message")
    public void readKafkaSaveDB(Message message) {
        if(message.getToEmail().compareTo("Exeption")==0){
            throw new RuntimeException();
        }
        else {
            messageRepo.save(message);
        }
    }
}
