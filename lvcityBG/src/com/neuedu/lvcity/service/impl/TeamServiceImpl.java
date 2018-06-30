package com.neuedu.lvcity.service.impl;

import com.neuedu.lvcity.model.Team;
import com.neuedu.lvcity.service.TeamService;

public class TeamServiceImpl implements TeamService {
	
	private static final TeamService instance = new TeamServiceImpl();
	public static TeamService getInstance() {
		return instance;
	}
	
	@Override
	public int updateTeam(Team team) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Team findTeam() {
		// TODO Auto-generated method stub
		return null;
	}

}
