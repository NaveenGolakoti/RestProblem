package com.restproblem.service;

import java.util.List;

import com.restproblem.dto.ProblemDto;



public interface ProblemService {
	public List<ProblemDto> getAllProblems();
	public void deleteProblem(int pid);
	public void updateProblem(ProblemDto problemDto);
	public void addProblem(ProblemDto problemDto);

}
