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
			System.out.println("������༶��������");
		classTotalNumber = in.nextInt();
		
		for(int i = 0; i < classTotalNumber; i++) {
			if(Debug.TIP_ENABLE)
				System.out.print((i+1)+" ");
			classArray[i] = SchoolClass.initSchoolClass();
		}
	}
	
//	1. ����ѧ����ţ��õ�ѧ������Ϣ��
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
		System.out.println("������Ϸ����ڵ�ѧ�ţ�");
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
			System.out.println("������Ϸ����ڵİ༶���ƣ�");
		return null;
	}
	
//	2. ����ѧ�����ڵİ༶����Ŀ���ó��ÿ�Ŀ�����������ѧ��������ѧ���Ŀ�Ŀ�ɼ���������
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
	
//	3. ����ѧ�����ڵİ༶���ó�����ѧ������Ϣ������ѧ���ĳ������¡�ѧ����Ž�������
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
	
//	4. �ҳ����а༶�У�����������һ��ѧ����
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
			System.out.println("====================== ����һ������ѧ����ţ��õ�ѧ������Ϣ�� ======================");
			System.out.println("�����������ѧ����ţ�");
			long aStudentId = in.nextLong();
			Student aStudent = getStudent(aStudentId);
		}


		if(Debug._2ND_TEST_ENABLE) {
			System.out.println("=== ���Զ�������ѧ�����ڵİ༶����Ŀ���ó��ÿ�Ŀ�����������ѧ��������ѧ���Ŀ�Ŀ�ɼ��������� ===");
			System.out.println("����������ҵİ༶���ƣ���Ŀ���ƣ�");
			String aSchoolClassName = in.next();
			String aSubejectName = in.next();
			Set<Student> failStudentSet = findAllFailStudents(aSchoolClassName, aSubejectName);
		}

		if(Debug._3RD_TEST_ENABLE) {
			System.out.println("==== ������������ѧ�����ڵİ༶���ó�����ѧ������Ϣ������ѧ���ĳ������¡�ѧ����Ž������� ====");
			System.out.println("����������ҵİ༶���ƣ�");
			String aSchoolClassName = in.next();
			Set<Student> sortedStudentSet = findAllStudent(aSchoolClassName);
		}
		
		if(Debug._4TH_TEST_ENABLE) {
			
		}
		
		if(Debug._5TH_TEST_ENABLE) {
			
		}
		
	}
}
