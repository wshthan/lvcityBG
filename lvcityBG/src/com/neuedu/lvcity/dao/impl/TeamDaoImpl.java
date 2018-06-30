package com.neuedu.lvcity.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.TeamDao;
import com.neuedu.lvcity.model.Team;

public class TeamDaoImpl implements TeamDao {

	private Connection conn;
	
	public TeamDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public List<Team> findAllTeam(Map<String, Object> map) {
		//�������������ڱ����ѯ���
		List<Team> list = new ArrayList<Team>();
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;
		//���������������������ڱ������ݿ��ѯ���
		ResultSet rs = null;
		int startPage = (int) map.get("startPage");
		int endPage = (int) map.get("endPage");
		
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("select * from team limit ?,?");
			//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
			pstam.setInt(1, startPage);
			pstam.setInt(2, endPage);
			rs = pstam.executeQuery();
			//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
			while(rs.next()){
			//�����û��������ڷ�װ��ǰ�α��еĸ����ֶε�ֵ
			Team team = new Team();
			team.setTeamid(rs.getInt("teamid"));
			team.setContent(rs.getString("content"));
						
			//���������������������ڱ������ݿ��ѯ���
			list.add(team);
			}	
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("�ڲ�ѯȫ��team��ʱ�������.������Ϣ�� ��" + e.getMessage());			
		} finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(rs, pstam);
		}
	    //���ز�ѯ�����û��б�
		return list;
	}
	
	@Override
	public int updateTeam(Team team) {
				//�������������ڱ����ѯ���
				int result = 0;
				//����Ԥ���������������������ڽ������ݿ����������
				PreparedStatement pstam = null;				
				try {
					//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
					pstam = conn.prepareStatement("UPDATE team SET content='?' WHERE teamid=1;");
					pstam.setString(1,team.getContent());
					
					//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
					result = pstam.executeUpdate();
					//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
						
				}catch (SQLException e) {
					//��������쳣������쳣��Ϣ
					System.out.println("����team����.������Ϣ�� ��" + e.getMessage());			
				}finally {
					//�������ݿ⹤���࣬�رս�����������������
					DBUtils.closeStatement(null, pstam);
				}
			    //���ز�ѯ�����û��б�
				return result;
	}

	

}
