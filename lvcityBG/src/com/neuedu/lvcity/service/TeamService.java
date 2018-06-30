package com.neuedu.lvcity.service;

import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.model.Team;

public interface TeamService {
	public List<Team> findAllTeam(Map<String, Object> map);
	public int updateTeam(Team team);
}
