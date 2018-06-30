package com.neuedu.lvcity.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.MessageDao;
import com.neuedu.lvcity.dao.impl.MessageDaoImpl;
import com.neuedu.lvcity.model.Message;
import com.neuedu.lvcity.service.MessageService;

public class MessageServiceImpl implements MessageService {
	
	private static final MessageService instance = new MessageServiceImpl();
	public static MessageService getInstance() {
		return instance;
	}

	@Override
	public int messageCount() {
				//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
				Connection conn = null;
				//�������������ڱ������ݿ��ѯ���
				int result = 0;
				try{
					//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
					conn = DBUtils.getConnection();
					//����userDao��ʵ�������
					MessageDao messageDao = new MessageDaoImpl(conn);
					
					//����dao�е�banarCount�������������ݿ��ѯ�����������ֵ����ѯ�������
					result = messageDao.messageCount();	
				
				} catch (Exception e) {
					System.out.println("��ѯͳ������message����"+e.getMessage());
				} finally {
					//�������ݿ⹤�����closeConnection�������ر�����
					DBUtils.closeConnection(conn);
				}
				//�������ݿ��ѯ���
				return result;
	}

	@Override
	public List<Message> findAllMessage(Map<String, Object> map) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		List<Message> message = null;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			//����userDao��ʵ�������
			MessageDao messageDao = new MessageDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			message = messageDao.findAllMessage(map);			
		
		} catch (Exception e) {
			System.out.println("��ѯ����message����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return message;
	}

	@Override
	public int deleteMessage(Message message) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		int result = 0;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//����userDao��ʵ�������
			MessageDao MessageDao = new MessageDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			result = MessageDao.deleteMessage(message);
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("ɾ��message����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return result;
	}

	@Override
	public Message findOneMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

}
