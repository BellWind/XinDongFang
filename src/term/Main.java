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
		
		return subjectStudentsMap;
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
			Set<Student> failStudentSet = findAllFailStudents(aSchoolClassName, aSubejectName);
		}

		if(Debug._3RD_TEST_ENABLE) {
			System.out.println("==== 测试三【输入学生所在的班级，得出所有学生的信息，按照学生的出生年月、学生编号进行排序】 ====");
			System.out.println("请输入待查找的班级名称：");
			String aSchoolClassName = in.next();
			Set<Student> sortedStudentSet = findAllStudent(aSchoolClassName);
		}
		
		if(Debug._4TH_TEST_ENABLE) {
			
		}
		
		if(Debug._5TH_TEST_ENABLE) {
			
		}
		
	}
}
