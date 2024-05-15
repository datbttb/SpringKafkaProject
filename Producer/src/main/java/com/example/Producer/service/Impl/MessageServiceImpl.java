package com.example.Producer.service.Impl;


import com.example.Producer.entity.Message;
import com.example.Producer.repository.MessageRepo;
import com.example.Producer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepo messageRepo;

    @Override
    public void saveMessage(Message message) {
        messageRepo.save(message);
    }
}
