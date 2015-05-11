package org.jingfu.paperbrowser;

import java.util.ArrayList;
import java.util.List;

public class PaperService {
	public static final String ALL_SUBJECT = "All Subjects";
	public static final String SUBJECT_CGD = "Computer Graphic Design";
	public static final String SUBJECT_CS = "Computer Science";
	public static final String SUBJECT_MATH = "Mathmatics";
	public static final String SUBJECT_SE = "Software Engineering";
	public static final String SUBJECT_STAT = "Statistics";

	public static final String ALL_LEVEL = "All Levels";
	public static final String LEVEL_100 = "100 Level";
	public static final String LEVEL_200 = "200 Level";
	public static final String LEVEL_300 = "300 Level";
	public static final String LEVEL_400 = "400 Level";
	public static final String LEVEL_500 = "500 Level";

	public static final String ALL_SEMESTER = "All Semesters";
	public static final String SEMESTER_A = "A Semester";
	public static final String SEMESTER_B = "B Semester";
	public static final String SEMESTER_S = "S Semester";

	private List<Paper> papers = new ArrayList<Paper>();
	private List<Paper> filteredPapers;
	private static PaperService instance = new PaperService();

	private PaperService() {
		initPapers();
	}

	private void initPapers() {
		papers.add(new Paper(SUBJECT_CGD, LEVEL_100, SEMESTER_A, "CGRD141",
				"Design 1"));
		papers.add(new Paper(SUBJECT_CGD, LEVEL_100, SEMESTER_B, "CGRD142",
				"Design 2"));
		papers.add(new Paper(SUBJECT_CGD, LEVEL_100, SEMESTER_S, "CGRD151",
				"A History of Visual Communication"));

		papers.add(new Paper(SUBJECT_CGD, LEVEL_200, SEMESTER_A, "CGRD224",
				"Visual Design for Interactive Media"));
		papers.add(new Paper(SUBJECT_CGD, LEVEL_200, SEMESTER_B, "CGRD241",
				"Computer Graphic Design 1"));
		papers.add(new Paper(SUBJECT_CGD, LEVEL_200, SEMESTER_S, "CGRD343",
				"Computer Graphic Design 2"));

		papers.add(new Paper(SUBJECT_CGD, LEVEL_300, SEMESTER_A, "CGRD343",
				"Computer Graphic Design 3"));
		papers.add(new Paper(SUBJECT_CGD, LEVEL_300, SEMESTER_B, "CGRD343",
				"Computer Graphic Design 4"));

		papers.add(new Paper(SUBJECT_CS, LEVEL_100, SEMESTER_A, "COMP103",
				"Introduction to Computer Science 1"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_100, SEMESTER_B, "COMP104",
				"Introduction to Computer Science 2"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_100, SEMESTER_S, "COMP125",
				"Visual Computing"));

		papers.add(new Paper(SUBJECT_CS, LEVEL_200, SEMESTER_A, "COMP200",
				"Computer Systems"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_200, SEMESTER_B, "COMP202",
				"Computer Communications"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_200, SEMESTER_S, "COMP203",
				"Programming with Data Structure"));

		papers.add(new Paper(SUBJECT_CS, LEVEL_300, SEMESTER_A, "COMP301",
				"Operating Systems"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_300, SEMESTER_B, "COMP311",
				"Computer Systems Architecture"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_300, SEMESTER_S, "COMP301",
				"Computer Networks"));

		papers.add(new Paper(SUBJECT_CS, LEVEL_400, SEMESTER_A, "COMP401",
				"Topics in Operating Systems"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_400, SEMESTER_B, "COMP413",
				"Topics in Computer Networks"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_400, SEMESTER_S, "COMP424",
				"Interaction Design"));

		papers.add(new Paper(SUBJECT_CS, LEVEL_500, SEMESTER_B, "COMP518",
				"Cyber Security"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_500, SEMESTER_S, "COMP521",
				"Machine Learning Algorithms"));
		papers.add(new Paper(SUBJECT_CS, LEVEL_500, SEMESTER_A, "COMP548",
				"Developing Mobile Applications"));

		papers.add(new Paper(SUBJECT_MATH, LEVEL_100, SEMESTER_A, "MATH101",
				"Introduction to Calculus"));
		papers.add(new Paper(SUBJECT_MATH, LEVEL_100, SEMESTER_B, "MATH165",
				"General Mathematics"));

		papers.add(new Paper(SUBJECT_MATH, LEVEL_200, SEMESTER_A, "MATH251",
				"Multivariable Calculus"));
		papers.add(new Paper(SUBJECT_MATH, LEVEL_200, SEMESTER_B, "MATH253",
				"Linear Algebra"));

		papers.add(new Paper(SUBJECT_MATH, LEVEL_300, SEMESTER_A, "MATH310",
				"Modern Algebra"));
		papers.add(new Paper(SUBJECT_MATH, LEVEL_300, SEMESTER_B, "MATH329",
				"Topics in Applied Mathematics"));

		papers.add(new Paper(SUBJECT_MATH, LEVEL_500, SEMESTER_A, "MATH506",
				"Combinatorics"));
		papers.add(new Paper(SUBJECT_MATH, LEVEL_500, SEMESTER_B, "MATH512",
				"Continuous Groups"));

		papers.add(new Paper(SUBJECT_SE, LEVEL_100, SEMESTER_A, "ENGG180",
				"Foundations of Engineering"));

		papers.add(new Paper(SUBJECT_SE, LEVEL_200, SEMESTER_A, "ENGG279",
				"Preparation for the Professional Work Place"));
		papers.add(new Paper(SUBJECT_SE, LEVEL_200, SEMESTER_B, "ENGG282",
				"Engineering Design"));
		papers.add(new Paper(SUBJECT_SE, LEVEL_200, SEMESTER_S, "ENGG283",
				"Linear Algebra fro Engineers"));

		papers.add(new Paper(SUBJECT_SE, LEVEL_300, SEMESTER_A, "ENGG372",
				"Engineering Placement 2"));
		papers.add(new Paper(SUBJECT_SE, LEVEL_300, SEMESTER_B, "ENGG381",
				"Engineering Statistics"));

		papers.add(new Paper(SUBJECT_STAT, LEVEL_100, SEMESTER_A, "STAT111",
				"Statistics for Science"));
		papers.add(new Paper(SUBJECT_STAT, LEVEL_100, SEMESTER_B, "STAT121",
				"Introduction to Statistical Methods"));
		papers.add(new Paper(SUBJECT_STAT, LEVEL_100, SEMESTER_S, "STAT160",
				"Management Statistics"));

		papers.add(new Paper(SUBJECT_STAT, LEVEL_200, SEMESTER_A, "STAT221",
				"Statistical Data Analysis"));
		papers.add(new Paper(SUBJECT_STAT, LEVEL_200, SEMESTER_B, "STAT226",
				"Bayesian Statistics"));

		papers.add(new Paper(SUBJECT_STAT, LEVEL_300, SEMESTER_A, "STAT321",
				"Advanced Data Analysis"));
		papers.add(new Paper(SUBJECT_STAT, LEVEL_300, SEMESTER_B, "STAT326",
				"Computational Bayesian Statistics"));
		papers.add(new Paper(SUBJECT_STAT, LEVEL_300, SEMESTER_S, "STAT3252",
				"Statistics for Quality Improvement"));

		papers.add(new Paper(SUBJECT_STAT, LEVEL_500, SEMESTER_A, "STAT521",
				"Computational Statistics"));
		papers.add(new Paper(SUBJECT_STAT, LEVEL_500, SEMESTER_B, "STAT522",
				"Statistical Inference"));
		papers.add(new Paper(SUBJECT_STAT, LEVEL_500, SEMESTER_S, "STAT590",
				"Directed Study"));

	}

	public static PaperService getInstance() {
		return instance;
	}

	public List<Paper> getAllPapers() {
		filteredPapers = new ArrayList<Paper>(papers);
		return filteredPapers;
	}

	public List<Paper> filterPapers(String subject, String level,
			String semester) {
		filteredPapers.clear();
		for (Paper paper : papers) {
			if (checkPaperBySubject(paper, subject)
					&& checkPaperByLevel(paper, level)
					&& checkPaperBySemester(paper, semester)) {
				filteredPapers.add(paper);
			}
		}
		return filteredPapers;
	}

	public List<Paper> filterPapersByCodeOrName(String queryString) {
		filteredPapers.clear();
		if (queryString == null || queryString.equals("")) {
			return filteredPapers;
		}
		for (Paper paper : papers) {
			if (paper.getCode().equalsIgnoreCase(queryString)
					|| paper.getName().equalsIgnoreCase(queryString)) {
				filteredPapers.add(paper);
				break;
			}
		}
		return filteredPapers;
	}

	public boolean checkPaperBySubject(Paper paper, String subject) {
		return ALL_SUBJECT.equals(subject)
				|| paper.getSubject().equals(subject);
	}

	public boolean checkPaperByLevel(Paper paper, String level) {
		return ALL_LEVEL.equals(level) || paper.getLevel().equals(level);
	}

	public boolean checkPaperBySemester(Paper paper, String semester) {
		return ALL_SEMESTER.equals(semester)
				|| paper.getSemester().equals(semester);
	}

	public String getSubjectAbbr(String subject) {
		if (ALL_SUBJECT.equals(subject)) {
			return ALL_SUBJECT;
		} else if (SUBJECT_CGD.equals(subject)) {
			return "GCRD";
		} else if (SUBJECT_CS.equals(subject)) {
			return "COMP";
		} else if (SUBJECT_MATH.equals(subject)) {
			return "MATH";
		} else if (SUBJECT_SE.equals(subject)) {
			return "ENGG";
		} else if (SUBJECT_STAT.equals(subject)) {
			return "STAT";
		} else {
			return "Paper Browser";
		}
	}
}
