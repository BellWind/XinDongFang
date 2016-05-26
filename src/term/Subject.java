package term;

public class Subject {
	
	private String name;
	private int score;
	private Teacher teacher;
	
	public Subject() {}
	
	public Subject(String name) {
		this.name = name;
	}

	public static Subject initSubject() {
		
		Subject aSubject = new Subject();
		
		if(Debug.TIP_ENABLE)
			System.out.println("������ÿγ����ƣ�");
		String aName = Main.in.next();
		aSubject.setName(aName);
		
		if(Debug.TIP_ENABLE)
			System.out.println("������ÿγ̷�����");
		int aScore = Main.in.nextInt();
		aSubject.setScore(aScore);
		
		if(Debug.TIP_ENABLE)
			System.out.println("������ÿγ���ʦ��Ϣ��");
		Teacher aTeacher = Teacher.initTeacher();
		aSubject.setTeacher(aTeacher);
		
		if(Debug.TIP_ENABLE)
			System.out.println("�ÿγ���Ϣ��¼����ϣ�");
		
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
	
	@Override
	public String toString() {
		return "Subject [name=" + name + ", score=" + score + ", teacher=" + teacher + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Subject other = (Subject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
