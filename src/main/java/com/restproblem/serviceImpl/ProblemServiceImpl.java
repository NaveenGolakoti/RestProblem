package com.restproblem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restproblem.dao.ProblemDao;
import com.restproblem.dto.ProblemDto;
import com.restproblem.service.ProblemService;


@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
	private ProblemDao problemDao;
	public List<ProblemDto> getAllProblems() {
		List<ProblemDto> list = problemDao.fetchAllProblems();
		return list;
		// TODO Auto-generated method stub
	}
	@Override
	public void deleteProblem(int pid) {
problemDao.deleteProblem(pid);		
	}
	@Override
	public void updateProblem(ProblemDto problemDto) {
		// TODO Auto-generated method stub
		problemDao.updateProblem(problemDto);
	}
	@Override
	public void addProblem(ProblemDto problemDto) {
		// TODO Auto-generated method stub
		problemDao.addProblem(problemDto);

	}

}
