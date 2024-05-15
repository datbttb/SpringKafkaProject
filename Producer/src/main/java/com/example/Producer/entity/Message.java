package com.example.Producer.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Data
@Table(name = "tblMessage")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String toEmail;

    private String fromEmail;

    private String subject;

    private String content;

    private Date sendTime;

    private boolean statusKafka;

}
