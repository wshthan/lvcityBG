package com.neuedu.lvcity.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.TeamDao;
import com.neuedu.lvcity.dao.impl.TeamDaoImpl;
import com.neuedu.lvcity.model.Team;
import com.neuedu.lvcity.service.TeamService;

public class TeamServiceImpl implements TeamService {
	
	private static final TeamService instance = new TeamServiceImpl();
	public static TeamService getInstance() {
		return instance;
	}
	
	@Override
	public int updateTeam(Team team) {
				//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
				Connection conn = null;
				//�������������ڱ������ݿ��ѯ���
				int result = 0;
				try{
					//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
					conn = DBUtils.getConnection();
					DBUtils.beginTransaction(conn);
					//����userDao��ʵ�������
					TeamDao TeamDao = new TeamDaoImpl(conn);
					//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
					result = TeamDao.updateTeam(team);			
				    DBUtils.commit(conn);
				} catch (Exception e) {
					System.out.println("����team����"+e.getMessage());
				} finally {
					//�������ݿ⹤�����closeConnection�������ر�����
					DBUtils.closeConnection(conn);
				}
				//�������ݿ��ѯ���
				return result;
	}

	@Override
	public List<Team> findAllTeam(Map<String, Object> map) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		List<Team> team = null;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			//����userDao��ʵ�������
			TeamDao teamDao = new TeamDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			team = teamDao.findAllTeam(map);			
		
		} catch (Exception e) {
			System.out.println("��ѯ����team����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return team;
	}

	

}
