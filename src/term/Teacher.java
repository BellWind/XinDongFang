package term;

public class Teacher {
	
	private String name;
	private long teacherId;
	private double averageScore;
	
	public Teacher() {}
	
	public static Teacher initTeacher() {
		
		Teacher aTeacher = new Teacher();
		
		if(Debug.TIP_ENABLE)
			System.out.println("请输入该老师姓名：");
		String aName = Main.in.next();
		aTeacher.setName(aName);
		
		if(Debug.TIP_ENABLE)
			System.out.println("请输入该老师工号：");
		long aTeacherId = Main.in.nextLong();
		aTeacher.setTeacherId(aTeacherId);
		
		if(Debug.TIP_ENABLE)
			System.out.println("该老师信息已录入完毕！");
		
		aTeacher.averageScore = 0;
		
		return aTeacher;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getTeacherId() {
		return teacherId;
	}
	
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", teacherId=" + teacherId + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (teacherId ^ (teacherId >>> 32));
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
		Teacher other = (Teacher) obj;
		if (teacherId != other.teacherId)
			return false;
		return true;
	}
}
