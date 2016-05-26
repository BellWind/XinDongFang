package term;

public class Teacher {
	
	private String name;
	private long teacherId;
	
	public Teacher() {}
	
	public Teacher(String name, long teacherId) {
		this.name = name;
		this.teacherId = teacherId;
	}
	
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

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", teacherId=" + teacherId + "]";
	}
	
	@Override 
	public int hashCode() {
		return (teacherId+"").hashCode();
	}
}
