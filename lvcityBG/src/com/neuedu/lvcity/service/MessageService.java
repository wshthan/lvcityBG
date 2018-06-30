package com.neuedu.lvcity.service;

import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.model.Message;

public interface MessageService {
	public int messageCount();
	public List<Message> findAllMessage(Map<String, Object> map);
	public int deleteMessage(Message message);
	public Message findOneMessage(Message message);
}
