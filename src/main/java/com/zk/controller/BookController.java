package com.zk.controller;

import com.zk.entity.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zk.config.rabbitmqConfig;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zk on 18/7/17.
 */
@ResponseBody
@Controller
@RequestMapping(value = "/books")
public class BookController {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public BookController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book); 对应 {@link BookHandler#listenerAutoAck}
     * this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book); 对应 {@link BookHandler#listenerManualAck}
     */
    @GetMapping
    public void defaultMessage(){
        Book book = new Book();
        book.setId("1");
        book.setName("一起学习springboot");
        this.rabbitTemplate.convertAndSend(rabbitmqConfig.DEFAULT_BOOK_QUEUE,book);
        this.rabbitTemplate.convertAndSend(rabbitmqConfig.MANUAL_BOOK_QUEUE,book);
    }


}
