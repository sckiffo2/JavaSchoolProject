package com.voronov.utils;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JmsSender {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static String subject = "JCG_QUEUE";


	public void sendMessage() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(subject);

		// MessageProducer is used for sending messages to the queue.
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello World!!!'
		TextMessage message = session
				.createTextMessage("Schedule have some changes");

		// Here we are sending our message!
		producer.send(message);

		System.out.println("JCG printing@@ '" + message.getText() + "'");
		connection.close();
	}
}