package com.restproblem.dto;

public class ProblemDto {

	private int pid;
	private String content;
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
	@Override
	public String toString() {
		return "ProblemDto [pid=" + pid + ", content=" + content + "]";
	}
}
