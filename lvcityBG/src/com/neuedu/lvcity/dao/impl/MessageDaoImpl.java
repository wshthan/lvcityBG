package com.neuedu.lvcity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.MessageDao;
import com.neuedu.lvcity.model.Message;

public class MessageDaoImpl implements MessageDao {
	
	private Connection conn;
	
	public MessageDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int messageCount() {
				//�������������ڱ����ѯ���
				int result = 0;
				//����Ԥ���������������������ڽ������ݿ����������
				PreparedStatement pstam = null;
				//���������������������ڱ������ݿ��ѯ���
				ResultSet rs = null;
				try {
					//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
					pstam = conn.prepareStatement("select count(*) from message");
					//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
					rs = pstam.executeQuery();
					
					//�����ѯ�����Ϊ�գ���ȡ�������ͳ�Ƶ�����
					if(rs.next()){
						result = rs.getInt("count(*)");
									}	
				}catch (SQLException e) {
					//��������쳣������쳣��Ϣ
					System.out.println("�ڲ�ѯͳ��message��ʱ�������.������Ϣ�� ��" + e.getMessage());			
				} finally {
					//�������ݿ⹤���࣬�رս�����������������
					DBUtils.closeStatement(rs, pstam);
				}
			    //���ز�ѯ�����û��б�
				return result;
	}

	@Override
	public List<Message> findAllMessage(Map<String, Object> map) {
		//�������������ڱ����ѯ���
		List<Message> list = new ArrayList<Message>();
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;
		//���������������������ڱ������ݿ��ѯ���
		ResultSet rs = null;
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("select * from message limit ?,?");
			//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
			pstam.setInt(1, startPage);
			pstam.setInt(2, endPage);
			rs = pstam.executeQuery();
			//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
			while(rs.next()){
			//�����û��������ڷ�װ��ǰ�α��еĸ����ֶε�ֵ
			Message message = new Message();
			message.setMid(rs.getInt("mid"));
			message.setTel(rs.getString("tel"));
			message.setSex(rs.getString("sex"));
			message.setName(rs.getString("name"));
			message.setMessage(rs.getString("message"));
						
			//���������������������ڱ������ݿ��ѯ���
			list.add(message);
			}	
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("�ڲ�ѯȫ��message��ʱ�������.������Ϣ�� ��" + e.getMessage());			
		} finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(rs, pstam);
		}
	    //���ز�ѯ�����û��б�
		return list;
	}

	@Override
	public int deleteMessage(Message message) {
		//�������������ڱ����ѯ���
		int result = 0;
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;				
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("DELETE FROM message WHERE mid =?");
			pstam.setInt(1, message.getMid());
			
			//����Ԥ��������executeQuery������
			result = pstam.executeUpdate();
			//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
				
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("ɾ��message����.������Ϣ�� ��" + e.getMessage());			
		} finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(null, pstam);
		}
	    //���ز�ѯ�����û��б�
		return result;
	}

	

}
