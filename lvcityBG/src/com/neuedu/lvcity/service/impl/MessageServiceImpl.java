package com.neuedu.lvcity.service.impl;

import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.model.Message;
import com.neuedu.lvcity.service.MessageService;

public class MessageServiceImpl implements MessageService {
	
	private static final MessageService instance = new MessageServiceImpl();
	public static MessageService getInstance() {
		return instance;
	}

	@Override
	public int messageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> findAllMessage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMessage(Message message) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message findOneMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

}
