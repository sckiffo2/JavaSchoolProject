package com.voronov.utils;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsSender {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL is : tcp://localhost:61616"
	private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your requirement.


	public void sendMessage() throws JMSException {
		// Getting JMS connection from the server and starting it
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		//Creating a non transactional session to send/receive JMS message.
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		//Destination represents here our queue 'JCG_QUEUE' on the JMS server.
		//The queue will be created automatically on the server.
		Destination destination = session.createQueue(subject);

		// MessageProducer is used for sending messages to the queue.
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello World!!!'
		TextMessage message = session
				.createTextMessage("Hello !!! Welcome to the world of ActiveMQ.");

		// Here we are sending our message!
		producer.send(message);

		System.out.println("JCG printing@@ '" + message.getText() + "'");
		connection.close();
	}

}