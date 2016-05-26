package term;

import java.io.*;
import java.util.*;

public class Main {
	
//	public static Scanner in = new Scanner(System.in);
	
	public static File file;
	public static FileInputStream fis;
	public static Scanner in = new Scanner(System.in);
	
	static SchoolClass[] classArray = new SchoolClass[100];
	static int classTotalNumber;
	
	public static void readFromFile() {
		try {
			file = new File("input.txt");
			fis = new FileInputStream(file);
			in = new Scanner(fis);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readFromConsole() throws IOException {
		fis.close();
		in = new Scanner(System.in);
	}
		
	public static void initAllClass() throws IOException {
		if(Debug.TIP_ENABLE)
			System.out.println("请输入班级总数量：");
		classTotalNumber = in.nextInt();
		
		for(int i = 0; i < classTotalNumber; i++) {
			if(Debug.TIP_ENABLE)
				System.out.print((i+1)+" ");
			classArray[i] = SchoolClass.initSchoolClass();
		}
	}
	
//	1. 输入学生编号，得到学生的信息；
	public static Student getStudent(long studentId) {
		for(int i = 0; i < classTotalNumber; i++) {
			Set<Student> aStudentSet = classArray[i].getStudentSet();
			for(Student aStudent : aStudentSet) {
				if(aStudent.getStudentId() == studentId) {
					if(Debug.SHOWINFO) 
						System.out.println(aStudent);
					return aStudent;
				}			
			}
		}
		System.out.println("请输入合法存在的学号！");
		return null;
	}
	
	public static SchoolClass findScoolClass(String aClassName) {
		boolean findClass = false;
		for(int i = 0; i < classTotalNumber; i++) {
			String ithClassName = classArray[i].getName();
			if(ithClassName.equals(aClassName)) {
				findClass = true;
				return classArray[i];
			}
		}
		if(!findClass)
			System.out.println("请输入合法存在的班级名称！");
		return null;
	}
	
//	2. 输入学生所在的班级，科目，得出该科目不及格的所有学生，按照学生的科目成绩进行排序；
	public static Set<Student> findAllFailStudents(String aClassName, String aSubject) {
		SchoolClass aSchoolClass = findScoolClass(aClassName);
		if(aSchoolClass == null)
			return null;
		
		Set<Student> failStudentSet = aSchoolClass.findAllFailStudents(aSubject);
		
		if(Debug.SHOWINFO) {
			for(Student aStudent : failStudentSet)
				System.out.println(aStudent);
		}
		
		return failStudentSet;
	}
	
//	3. 输入学生所在的班级，得出所有学生的信息，按照学生的出生年月、学生编号进行排序；
	public static Set<Student> findAllStudent(String aClassName) {
		SchoolClass aSchoolClass = findScoolClass(aClassName);
		if(aSchoolClass == null)
			return null;
		
		Set<Student> sortedStudentSet = aSchoolClass.findAllSortedStudent();
		
		if(Debug.SHOWINFO) {
			for(Student aFailedStudent : sortedStudentSet) {
				System.out.println(aFailedStudent);
			}
		}
		
		return sortedStudentSet;
	}
	
//	4. 找出所有班级中，单科排名第一的学生；
	public static Map<SchoolClass, Map<Subject, Student>> findTopSubjectStudents() {
		Map<SchoolClass, Map<Subject, Student>> subjectStudentsMap = new HashMap<SchoolClass, Map<Subject, Student>>();
		
		for(int i = 0; i < classTotalNumber; i++) {
			subjectStudentsMap.put(classArray[i], classArray[i].findTopSubjectStudents());
		}
		
		if(Debug.SHOWINFO) {
			for(SchoolClass aSchoolClass : subjectStudentsMap.keySet()) {
				System.out.println("班级名称: " + aSchoolClass.getName());
				Map<Subject, Student> classTopStudent = subjectStudentsMap.get(aSchoolClass);
				for(Subject aSubject : classTopStudent.keySet()) {	
					System.out.println("课程名称: " + aSubject.getName());
					Student aStudent = classTopStudent.get(aSubject);
					System.out.println(aStudent);
				}
			}
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
				if(bestTeacher == null || bestTeacher.getAverageScore() < classSubjectTeacher.getAverageScore()) 
					bestTeacher = classSubjectTeacher;
			}
			bestSubjectTeacher.put(aSubject, bestTeacher);
		}
		
		if(Debug.SHOWINFO) {
			for(Subject aSubject : bestSubjectTeacher.keySet()) {
				System.out.println("课程名称："+aSubject.getName());
				Teacher aTeacher = bestSubjectTeacher.get(aSubject);
				System.out.println(aTeacher);
				System.out.println("课程平均分："+aTeacher.getAverageScore());
			}
		}
		
		return bestSubjectTeacher;
	}
	
	public static void main(String[] args) throws IOException {
		
		readFromFile();
		
		initAllClass();
		
		readFromConsole();
		
		if(Debug._1ST_TEST_ENABLE) {
			System.out.println("====================== 测试一【输入学生编号，得到学生的信息】 ======================");
			System.out.println("请输入待查找学生编号：");
			long aStudentId = in.nextLong();
			Student aStudent = getStudent(aStudentId);
		}


		if(Debug._2ND_TEST_ENABLE) {
			System.out.println("=== 测试二【输入学生所在的班级，科目，得出该科目不及格的所有学生，按照学生的科目成绩进行排序】 ===");
			System.out.println("请输入待查找的班级名称，科目名称：");
			String aSchoolClassName = in.next();
			String aSubejectName = in.next();
			findAllFailStudents(aSchoolClassName, aSubejectName);
		}

		if(Debug._3RD_TEST_ENABLE) {
			System.out.println("==== 测试三【输入学生所在的班级，得出所有学生的信息，按照学生的出生年月、学生编号进行排序】 ====");
			System.out.println("请输入待查找的班级名称：");
			String aSchoolClassName = in.next();
			findAllStudent(aSchoolClassName);
		}
		
		if(Debug._4TH_TEST_ENABLE) {
			System.out.println("===================== 测试四【找出所有班级中，单科排名第一的学生】 =====================");
			findTopSubjectStudents();
		}
		
		if(Debug._5TH_TEST_ENABLE) {
			System.out.println("============= 测试五【找出所有班级中，每个科目的班级平均分最高的任课老师的信息】 =============");
			findSubjectBestTeacher();
		}
		
	}
}
