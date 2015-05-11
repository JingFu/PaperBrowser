package org.jingfu.paperbrowser;

public class Paper {
	private String subject;
	private String level;
	private String semester;
	private String code;
	private String name;
	
	public Paper(String subject, String level, String semester, String code, String name) {
		this.subject = subject;
		this.level = level;
		this.semester = semester;
		this.code = code;
		this.name = name;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return code + " " + name;
	}
	
}
