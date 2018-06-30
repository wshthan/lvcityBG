package com.neuedu.lvcity.dao;

import com.neuedu.lvcity.model.Team;


	public interface TeamDao {
		
		public int updateTeam(Team team);
		public Team findTeam();
		
}
