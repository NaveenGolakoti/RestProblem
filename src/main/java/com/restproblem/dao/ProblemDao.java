package com.restproblem.dao;

import java.util.List;

import com.restproblem.dto.ProblemDto;



public interface ProblemDao {
	
	public List<ProblemDto> fetchAllProblems();
	
	public void deleteProblem(int pid);
	public void updateProblem(ProblemDto problemDto);
	public void addProblem(ProblemDto problemDto);

}
