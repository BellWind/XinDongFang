package term;

public class Subject {
	
	private String name;
	private int score;
	private Teacher teacher;
	private double averageScore;
	
	public Subject() {}
	
	public Subject(String name) {
		this.name = name;
	}

	public Subject(String name, int score, Teacher teacher, double averageScore) {
		this.name = name;
		this.score = score;
		this.teacher = teacher;
		this.averageScore = averageScore;
	}

	public static Subject initSubject() {
		
		Subject aSubject = new Subject();
		
		if(Debug.TIP_ENABLE)
			System.out.println("请输入该课程名称：");
		String aName = Main.in.next();
		aSubject.setName(aName);
		
		if(Debug.TIP_ENABLE)
			System.out.println("请输入该课程分数：");
		int aScore = Main.in.nextInt();
		aSubject.setScore(aScore);
		
		if(Debug.TIP_ENABLE)
			System.out.println("请输入该课程老师信息：");
		Teacher aTeacher = Teacher.initTeacher();
		aSubject.setTeacher(aTeacher);
		
		if(Debug.TIP_ENABLE)
			System.out.println("该课程信息已录入完毕！");
		
		return aSubject;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}
	
	@Override
	public String toString() {
		return "Subject [name=" + name + ", score=" + score + ", teacher=" + teacher + "]";
	}

	@Override
	public int hashCode() {
		return (name + teacher.getTeacherId()).hashCode();
	}
}
