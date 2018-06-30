package com.neuedu.lvcity.dao;

import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.model.Message;

public interface MessageDao {
	public int messageCount();
	public List<Message> findAllMessage(Map<String, Object> map);
	public int deleteMessage(Message message);
}
