package com.voronov.utils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class JmsTopicListener implements MessageListener {
	private String consumerName;

	public JmsTopicListener(String consumerName) {
		this.consumerName = consumerName;
	}

	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;

		try {
			System.out.println(consumerName + " received " + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
