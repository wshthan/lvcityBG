package com.neuedu.lvcity.dao;

import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.model.Team;


	public interface TeamDao {
		public List<Team> findAllTeam(Map<String, Object> map);
		public int updateTeam(Team team);
		
}
