package com.zk;

import com.zk.controller.BookController;
import com.zk.controller.TopicExchangeTestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

	@Autowired
	BookController bookController;

	@Autowired
	TopicExchangeTestController topicExchangeTestController;

	/**
	 * 测试一生产者，一消费者的消息发送与接收
	 */
	@Test
	public void contextLoads() {
		bookController.send();

	}

	/**
	 * 测试一生产者多消费者的消息发送与接收
	 * 结论：一个队列，一个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者接收
	 */
	@Test
	public void oneToMany(){
		for (int i = 0; i < 1000; i++){
			bookController.send();
		}
	}

	/**
	 * 测试多生产者多消费者的消息发送与接收
	 * 结论：一个队列，N个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者接收
	 */
	@Test
	public void manyToMany(){
		for (int i = 0; i < 1000; i++){
			bookController.send();
			bookController.send2();
		}
	}

	@Test
	public void topicExchangeTest(){
		for (int i = 0; i < 100; i++) {
			topicExchangeTestController.send1();
			topicExchangeTestController.send2();
		}
	}
}
