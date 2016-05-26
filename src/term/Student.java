package term;

import java.util.*;

public class Student {
	
	private long studentId;
	private String name;
	private String sex;
	private int age;
	private Date birthday;
	private Set<Subject> subjectSet = new HashSet<Subject>();
	
	public Student() {}

	public static Student initStudent() {
		
		Student aStudent = new Student();
		
		if(Debug.TIP_ENABLE)
			System.out.println("�������ѧ��������");
		String aName = Main.in.next();
		aStudent.setName(aName);
		
		if(Debug.TIP_ENABLE)
			System.out.println("�������ѧ���Ա�");
		String aSex = Main.in.next();
		aStudent.setSex(aSex);
		
		if(Debug.TIP_ENABLE)
			System.out.println("�������ѧ�����䣺");
		int aAge = Main.in.nextInt();
		aStudent.setAge(aAge);
		
		if(Debug.TIP_ENABLE)
			System.out.println("�������ѧ��ѧ�ţ�");
		long aStudentId = Main.in.nextLong();
		aStudent.setStudentId(aStudentId);
		
		if(Debug.TIP_ENABLE)
			System.out.println("�������ѧ�����������գ����ÿո������");
		int bornYear = Main.in.nextInt();
		int bornMonth = Main.in.nextInt();
		int bornDay = Main.in.nextInt();
		Date aBirthday = new Date(bornYear-1900, bornMonth, bornDay);
		aStudent.setBirthday(aBirthday);
		
		if(Debug.TIP_ENABLE)
			System.out.println("�������ѧ���ܿ�Ŀ������");
		int subjectTotalNumber = Main.in.nextInt();
		for(int i = 0; i < subjectTotalNumber; i++) {
			if(Debug.TIP_ENABLE)
				System.out.print((i+1)+" ");
			Subject subject = Subject.initSubject();
			aStudent.addSubject(subject);
		}
		
		if(Debug.TIP_ENABLE)
			System.out.println("��ѧ����Ϣ��¼����ϣ�");
		
		return aStudent;
	}
	
	public void addSubject(Subject... subjects) {
		for(Subject aSubject : subjects)
			subjectSet.add(aSubject);
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Set<Subject> getSubjectSet() {
		return subjectSet;
	}

	public void setSubjectSet(Set<Subject> subjectSet) {
		this.subjectSet = subjectSet;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", sex=" + sex + ", age=" + age + ", birthday="
				+ birthday + ", subjectSet=" + subjectSet + "]";
	}
	
	public int getSubjectScore(String aSubjectName) {
		for(Subject aSubject : subjectSet) {
			String ithSubjectName = aSubject.getName();
			if(ithSubjectName.equals(aSubjectName)) {
				 return aSubject.getScore();
			}
		}
		return -1;
	}
	
	public Teacher getSubjectTeacher(String aSubjectName) {
		for(Subject aSubject : subjectSet) {
			String ithSubjectName = aSubject.getName();
			if(ithSubjectName.equals(aSubjectName)) {
				 return aSubject.getTeacher();
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (studentId ^ (studentId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (studentId != other.studentId)
			return false;
		return true;
	}
}
