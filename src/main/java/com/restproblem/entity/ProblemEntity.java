package com.restproblem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Problem")
public class ProblemEntity {
	
	@Override
	public String toString() {
		return "ProblemEntity [pid=" + pid + ", content=" + content + "]";
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Id
	private int pid;
	private String content;

}
