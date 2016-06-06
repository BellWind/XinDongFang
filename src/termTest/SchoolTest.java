package termTest;

import term.*;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

import org.junit.Before;

public class SchoolTest {

	@Before
	public void setUp() throws Exception {
		SAXparser.init();
	}

	@Test
	public void testGetStudent() {
		System.out.println("====================== 测试一【输入学生编号，得到学生的信息】 ======================");
		System.out.println("待查找学生编号为：【20130101】");
		long aStudentId = 20130101;
		Student aStudent = School.getStudent(aStudentId);
		if(aStudent != null) System.out.println(aStudent);
		else System.out.println("学号不合法...");
		System.out.println();
	}

	@Test
	public void testFindAllFailStudents() {
		System.out.println("=== 测试二【输入学生所在的班级，科目，得出该科目不及格的所有学生，按照学生的科目成绩进行排序】 ===");
		System.out.println("待查找的班级名称为【一班】，科目名称为【数学】");
		String aSchoolClassName = "一班";
		String aSubejectName = "数学";
		if(School.findSchoolClass(aSchoolClassName) == null)
			System.out.println("请输入合法存在的班级名称！");
		Set<Student> failStudentSet = School.findAllFailStudents(aSchoolClassName, aSubejectName);
		if(failStudentSet == null)
			System.out.println("请输入该班级存在的课程！");
		if(failStudentSet.size() == 0)
			System.out.println("没有不合格的学生。");
		for(Student aStudent : failStudentSet)
			System.out.println(aStudent);
		System.out.println();
	}

	@Test
	public void testFindAllStudent() {
		System.out.println("==== 测试三【输入学生所在的班级，得出所有学生的信息，按照学生的出生年月、学生编号进行排序】 ====");
		System.out.println("待查找的班级名称为【一班】");
		String aSchoolClassName = "一班";
		Set<Student> sortedStudentSet = School.findAllStudent(aSchoolClassName);
		for(Student aFailedStudent : sortedStudentSet) 
			System.out.println(aFailedStudent);
		System.out.println();
	}

	@Test
	public void testFindTopSubjectStudents() {
		System.out.println("===================== 测试四【找出所有班级中，单科排名第一的学生】 =====================");
		Map<SchoolClass, Map<Subject, Student>> subjectStudentsMap = School.findTopSubjectStudents();
		for(SchoolClass aSchoolClass : subjectStudentsMap.keySet()) {
			System.out.println("班级名称: " + aSchoolClass.getName());
			Map<Subject, Student> classTopStudent = subjectStudentsMap.get(aSchoolClass);
			for(Subject aSubject : classTopStudent.keySet()) {	
				System.out.println("课程名称: " + aSubject.getName());
				Student aStudent = classTopStudent.get(aSubject);
				System.out.println(aStudent);
			}
		}
		System.out.println();
	}

	@Test
	public void testFindSubjectBestTeacher() {
		System.out.println("============ 测试五【找出所有班级中，每个科目的班级平均分最高的任课老师的信息】 ============");
		Map<Subject, Teacher> bestSubjectTeacher = School.findSubjectBestTeacher();
		for(Subject aSubject : bestSubjectTeacher.keySet()) {
			System.out.println("课程名称："+aSubject.getName());
			Teacher aTeacher = bestSubjectTeacher.get(aSubject);
			System.out.println(aTeacher);
			System.out.println("课程平均分："+aTeacher.getAverageScore());
		}
		System.out.println();
	}

}
