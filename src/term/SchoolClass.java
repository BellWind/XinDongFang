package term;

import java.util.*;

public class SchoolClass {
	
	private String name;
	private Set<Student> studentSet = new HashSet<Student>();
	private Set<Subject> subjectSet = new HashSet<Subject>();
	private Map<Subject, Teacher> subjectTeacherMap = new HashMap<Subject, Teacher>();
	
	public SchoolClass() {}
	
	public SchoolClass(String name, Set<Student> studentSet, Set<Subject> subjectSet) {
		this.name = name;
		this.studentSet = studentSet;
		this.subjectSet = subjectSet;
	}
	
	public static SchoolClass initSchoolClass() {
		
		SchoolClass aSchoolClass = new SchoolClass();
		
		if(Debug.TIP_ENABLE)
			System.out.println("请输入班级名称：");
		String aName = Main.in.next();
		aSchoolClass.setName(aName);
		
		if(Debug.TIP_ENABLE)
			System.out.println("请输入班级学生总数量：");
		int classTotalStudentNumber = Main.in.nextInt();
		
		for(int i = 0; i < classTotalStudentNumber; i++) {
			if(Debug.TIP_ENABLE)
				System.out.print((i+1)+" ");
			Student student = Student.initStudent();
			aSchoolClass.addStudent(student);
		}
		
		for(Student aStudent : aSchoolClass.studentSet) {
			Set<Subject> aSubjectSet = aStudent.getSubjectSet();
			for(Subject aSubject : aSubjectSet) {
				aSchoolClass.addSubject(aSubject);
			}
		}
		
		
		for(Subject aSubject : aSchoolClass.subjectSet) {
			String aSubjectName = aSubject.getName();
			Teacher classSubjectTeacher = null;
			int totalScore = 0;
			int totalStudent = 0;
			for(Student aStudent : aSchoolClass.studentSet) {
				int score = aStudent.getSubjectScore(aSubjectName);
				if(score >= 0) {
					totalScore += score;
					totalStudent++;
					if(classSubjectTeacher == null) {
						classSubjectTeacher = aStudent.getSubjectTeacher(aSubjectName);
					}
				}
			}
			classSubjectTeacher.setAverageScore((double)totalScore / (double)totalStudent);
			aSchoolClass.subjectTeacherMap.put(aSubject, classSubjectTeacher);
		}
		
		if(Debug.TIP_ENABLE)
			System.out.println("该班级信息已录入完毕！");
		
		return aSchoolClass;
	}

	public void addStudent(Student... students) {
		for(Student aStudent : students)
			studentSet.add(aStudent);
	}
	
	public void addSubject(Subject... subjects) {
		for(Subject aSubject : subjects)
			subjectSet.add(aSubject);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudentSet() {
		return studentSet;
	}

	public void setStudentSet(Set<Student> studentSet) {
		this.studentSet = studentSet;
	}

	public Set<Subject> getSubjectSet() {
		return subjectSet;
	}

	public void setSubjectSet(Set<Subject> subjectSet) {
		this.subjectSet = subjectSet;
	}

	public Map<Subject, Teacher> getSubjetcTeacherMap() {
		return subjectTeacherMap;
	}

	public void setSubjetcTeacherMap(Map<Subject, Teacher> subjetcTeacher) {
		this.subjectTeacherMap = subjetcTeacher;
	}

	@Override
	public String toString() {
		return "SchoolClass [name=" + name + ", studentSet=" + studentSet + ", subjectSet=" + subjectSet + "]";
	}
	
	public Set<Student> findAllFailStudents(final String aSubjectName) {
		boolean findSubject = false;
		for(Subject aSubject : subjectSet) {
			String ithSubjectName = aSubject.getName();
			if(ithSubjectName.equals(aSubjectName)) {
				findSubject = true;
			}
		}
		if(!findSubject) {
			System.out.println("请输入该班级存在的课程！");
			return null;
		}
		
		Set<Student> failStudentSet = new TreeSet<Student>(new Comparator<Student>() {
			public int compare(Student student1, Student student2) {
            	Integer score1 = (Integer)student1.getSubjectScore(aSubjectName);
            	Integer score2 = (Integer)student2.getSubjectScore(aSubjectName);
            	if(score1.equals(score2))
            		return ((Integer)student1.hashCode()).compareTo(student2.hashCode());
            	return score2.compareTo(score1);
            }  
		});
		
		for(Student aStudent : studentSet) {
			int aSubjectScore = aStudent.getSubjectScore(aSubjectName);
			if(aSubjectScore >= 0 && aSubjectScore < 60) {
				failStudentSet.add(aStudent);
			}
		}

		return failStudentSet;
	}
	
	public Set<Student> findAllSortedStudent() {
		Set<Student> allSortedStudentSet = new TreeSet<Student>(new Comparator<Student>() {
			public int compare(Student stu1, Student stu2) {
				Date d1 = stu1.getBirthday();
				Date d2 = stu2.getBirthday();
            	if(d1.equals(d2)) {
            		Long id1 = (Long)stu1.getStudentId();
            		Long id2 = (Long)stu2.getStudentId();
            		return id1.compareTo(id2);
            	}
            	return d1.compareTo(d2);
            }  
		});
		
		for(Student aStudent : studentSet) 
			allSortedStudentSet.add(aStudent);
		
		return allSortedStudentSet;
	}
	
	public Map<Subject, Student> findTopSubjectStudents() {
		Map<Subject, Student> topSubjectStudentSet = new HashMap<Subject, Student>();
		
		for(Subject aSubject : subjectSet) {
			int topScore = 0;
			Student topStudent = null;
			String aSubjectName = aSubject.getName();
			for(Student aStudent : studentSet) {
				int score = aStudent.getSubjectScore(aSubjectName);
				if(score >= 0 & score >= topScore) {
					topScore = score;
					topStudent = aStudent;
				}
			}
			topSubjectStudentSet.put(aSubject, topStudent);
		}
		
		return topSubjectStudentSet;
	}
	
	public void testSubjectSet() {
		System.out.println("班级科目集合大小："+subjectSet.size());
		int cnt = 1;
		for(Subject aSubject : subjectSet) {
			System.out.println("第"+cnt+"个科目的名称为："+aSubject.getName());
			System.out.println("其hash值为："+aSubject.hashCode());
			cnt++;
		}
	}
	
}
