package term;

import java.util.*;

public class School {
	public static SchoolClass[] classArray = new SchoolClass[100];
	public static int classTotalNumber = 0;
	
	School() {}
	
	public static void addSchoolClass(SchoolClass... classSet) {
		for(SchoolClass aClass : classSet) {
			classArray[classTotalNumber] = aClass;
			classTotalNumber++;
		}
	}
	
//	1. 输入学生编号，得到学生的信息；
	public static Student getStudent(long studentId) {
		for(int i = 0; i < classTotalNumber; i++) {
			Set<Student> aStudentSet = classArray[i].getStudentSet();
			for(Student aStudent : aStudentSet) {
				if(aStudent.getStudentId() == studentId) {
					return aStudent;
				}			
			}
		}
		return null;
	}
	
	public static SchoolClass findSchoolClass(String aClassName) {
		for(int i = 0; i < classTotalNumber; i++) {
			String ithClassName = classArray[i].getName();
			if(ithClassName.equals(aClassName)) 
				return classArray[i];
		}	
		return null;
	}
	
//	2. 输入学生所在的班级，科目，得出该科目不及格的所有学生，按照学生的科目成绩进行排序；
	public static Set<Student> findAllFailStudents(String aClassName, String aSubject) {
		SchoolClass aSchoolClass = findSchoolClass(aClassName);
		if(aSchoolClass == null)
			return null;
		return aSchoolClass.findAllFailStudents(aSubject);

	}
	
//	3. 输入学生所在的班级，得出所有学生的信息，按照学生的出生年月、学生编号进行排序；
	public static Set<Student> findAllStudent(String aClassName) {
		SchoolClass aSchoolClass = findSchoolClass(aClassName);
		if(aSchoolClass == null)
			return null;
		return aSchoolClass.findAllSortedStudent();
	}
	
//	4. 找出所有班级中，单科排名第一的学生；
	public static Map<SchoolClass, Map<Subject, Student>> findTopSubjectStudents() {
		Map<SchoolClass, Map<Subject, Student>> subjectStudentsMap = new HashMap<SchoolClass, Map<Subject, Student>>();
		
		for(int i = 0; i < classTotalNumber; i++) {
			subjectStudentsMap.put(classArray[i], classArray[i].findTopSubjectStudents());
		}
		
		return subjectStudentsMap;
	}

//	5. 找出所有班级中，每个科目的班级平均分最高的任课老师的信息；
	public static Map<Subject, Teacher> findSubjectBestTeacher() {
		Map<Subject, Teacher> bestSubjectTeacher = new HashMap<Subject, Teacher>();
		Set<Subject> totalSubjectSet = new HashSet<Subject>();
		for(int i = 0; i < classTotalNumber; i++) 
			totalSubjectSet.addAll(classArray[i].getSubjectSet());
		
		for(Subject aSubject : totalSubjectSet) {
			Teacher bestTeacher = null;
			for(int i = 0; i < classTotalNumber; i++) {
				Teacher classSubjectTeacher = (classArray[i].getSubjetcTeacherMap()).get(aSubject);
				if(classSubjectTeacher == null) 
					continue;
				if(bestTeacher == null || bestTeacher.getAverageScore() < classSubjectTeacher.getAverageScore()) 
					bestTeacher = classSubjectTeacher;
			}
			bestSubjectTeacher.put(aSubject, bestTeacher);
		}
		
		return bestSubjectTeacher;
	}
}
