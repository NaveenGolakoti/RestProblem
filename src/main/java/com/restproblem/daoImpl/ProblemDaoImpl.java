package com.restproblem.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.restproblem.dao.ProblemDao;
import com.restproblem.dto.ProblemDto;
import com.restproblem.entity.ProblemEntity;
import com.restproblem.util.HibernateUtil;


@Repository
public class ProblemDaoImpl extends HibernateUtil implements ProblemDao {

	public List<ProblemDto> fetchAllProblems() {
		// TODO Auto-generated method stub
		String hql = "";
		Session session = this.getSession();
		
			hql = "FROM ProblemEntity ORDER BY pid DESC";
		Query query = session.createQuery(hql);
		List<ProblemEntity> list = query.list();
		List<ProblemDto> list1 = new ArrayList<ProblemDto>();
		for(ProblemEntity q : list){
			System.out.println(q.toString());
			ProblemDto problemDto = new ProblemDto();
			problemDto.setPid(q.getPid());
			problemDto.setContent(q.getContent());
			list1.add(problemDto);
		}
		return list1;
	}

	@Override
	public void deleteProblem(int pid) {
		Session session = this.getSession();
		Query query = session.createQuery("delete from ProblemEntity where pid = :pid");
		query.setParameter("pid",pid);
		 
		int result = query.executeUpdate();
		System.out.println("result"+result);
	}

	@Override
	public void updateProblem(ProblemDto problemDto) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Query query = session.createQuery("update ProblemEntity set content = :content where pid = :pid");
		query.setParameter("pid",problemDto.getPid());
		query.setParameter("content", problemDto.getContent());
		int result = query.executeUpdate();
		System.out.println("result"+result);
	}

	@Override
	public void addProblem(ProblemDto problemDto) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		ProblemEntity problemEntity = new ProblemEntity();
		problemEntity.setPid(problemDto.getPid()+1);
		problemEntity.setContent(problemDto.getContent());
		session.save(problemEntity);
	}
}
