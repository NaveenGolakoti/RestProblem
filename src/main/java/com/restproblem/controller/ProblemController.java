package com.restproblem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.restproblem.dto.ProblemDto;
import com.restproblem.service.ProblemService;



@Controller
public class ProblemController {
	
	@Autowired
	private ProblemService problemService;

	@RequestMapping(value = "/getAllProblems", method = RequestMethod.GET)
	public ModelAndView generateAssignmentPdf(HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<ProblemDto> list = problemService.getAllProblems();
		session.setAttribute("list", list);
		session.setAttribute("size",list.size());
		request.setCharacterEncoding("UTF-8");
		
		return new ModelAndView("home", "list", list);
	}
	
	@RequestMapping(value = "getAllProblems/selectedList", method = RequestMethod.POST)
	public ModelAndView saveProblems(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		List<ProblemDto> list = new ArrayList<ProblemDto>();
		
	     String[] chkSms=request.getParameterValues("problem");
		System.out.println("inside saveProblems controller");
		for(String a:chkSms){
			ProblemDto problemDto = new ProblemDto();
			String[] b= a.split(",",2);
			problemDto.setPid(Integer.parseInt(b[0].substring(16,b[0].length())));
			problemDto.setContent(b[1].substring(9,b[1].length()));
			System.out.println("check1:::"+b[0].substring(16,b[0].length()));
			System.out.println("check1:::"+b[1].substring(9,b[1].length()));
			list.add(problemDto);
		}
		return new ModelAndView("selectedList", "list", list);
	}

	
	@RequestMapping(value = "/deleteProblem", method = RequestMethod.POST)
	public ModelAndView deleteProblem(HttpSession session,@RequestBody String data) throws JSONException {
		List<ProblemDto> list1 = (List<ProblemDto>) session.getAttribute("list");
		System.out.println("list:::"+list1.size());
		JSONObject jsonObject = new JSONObject(data);
		int pid = jsonObject.optInt("pid");
		System.out.println("pid:::"+pid);
		for(ProblemDto p : list1){
			if(p.getPid() == pid){
				System.out.println("found delete ele:::"+ p.getPid());
				list1.remove(p);
				break;
			}
		}
		session.setAttribute("list",list1);
		problemService.deleteProblem(pid);
		return new ModelAndView("selectedList", "list", list1);
	}
	@RequestMapping(value = "/editProblem/{pid}", method = RequestMethod.GET)
	public ModelAndView editProblem(HttpSession session,@PathVariable int pid,Model model) throws JSONException {
		System.out.println("pid:::"+ pid);
		ProblemDto problemDto = new ProblemDto();
		List<ProblemDto> list1 = (List<ProblemDto>) session.getAttribute("list");
		for(ProblemDto p : list1){
			if(p.getPid()==pid){
				problemDto.setPid(pid);
				problemDto.setContent(p.getContent());
				break;
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("problemDto", problemDto);
		mav.setViewName("editproblem");
		return mav;
	}
	@RequestMapping(value = "/editProblem/updateProblem", method = RequestMethod.POST)
	public ModelAndView updateProblem(HttpSession session,@ModelAttribute("problemDto") ProblemDto problemDto) throws JSONException {
		System.out.println("problemDto:::"+problemDto.toString());
		problemService.updateProblem(problemDto);
		List<ProblemDto> list = problemService.getAllProblems();
		session.setAttribute("list", list);
		return new ModelAndView("home","list",list);
	}
	
	@RequestMapping(value = "/addnewProblem", method = RequestMethod.GET)
	public ModelAndView addnewProblem(HttpSession session) throws JSONException {
		ProblemDto problemDto = new ProblemDto();
		ModelAndView mav = new ModelAndView();
		mav.addObject("problemDto", problemDto);
		mav.setViewName("addproblem");
		return mav;
	}
	@RequestMapping(value = "/addProblem", method = RequestMethod.POST)
	public ModelAndView addProblem(HttpSession session,@ModelAttribute("problemDto") ProblemDto problemDto) throws JSONException {
		int highnumber = 0;
		System.out.println("ProblemDto:::"+problemDto);
		List<ProblemDto> list1 = (List<ProblemDto>) session.getAttribute("list");
		for(ProblemDto p : list1){
			if(p.getPid()>highnumber){
				highnumber= p.getPid();
			}
		}
		System.out.println("high number:::"+highnumber);
		ProblemDto problemDto1 = new ProblemDto();
		problemDto1.setPid(highnumber);
	    problemDto1.setContent(problemDto.getContent());
	    problemService.addProblem(problemDto1);
		List<ProblemDto> list = problemService.getAllProblems();
		session.setAttribute("list", list);
		return new ModelAndView("home","list",list);
	}


}
