package termTest;

import term.*;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("rawtypes")
public class SAXparser {
	
	@SuppressWarnings("deprecation")
	private static Date initDate(Element dat) {
		int year = 0, month = 0, day = 0;
		for(Iterator i = dat.elementIterator(); i.hasNext(); ) {
			Element ele = (Element)i.next();
			String tag = ele.getName();
			String text = ele.getTextTrim();
			if(tag.equals("year")) year = Integer.parseInt(text);
			else if(tag.equals("month")) month = Integer.parseInt(text);
			else if(tag.equals("day")) day = Integer.parseInt(text);
		}
		return new Date(year - 1900, month, day);
	}
	
	private static Teacher initTeacher(Element tea) {
		Teacher ret = new Teacher();
		for(Iterator i = tea.elementIterator(); i.hasNext(); ) {
			Element ele = (Element)i.next();
			String tag = ele.getName();
			String text = ele.getTextTrim();
			if(tag.equals("name")) ret.setName(text);
			else if(tag.equals("teacherId")) ret.setTeacherId(Integer.parseInt(text));
		}
//		System.out.println(ret);
		return ret;
	}
	
	private static Subject initSubject(Element sub) {
		Subject ret = new Subject();
		for(Iterator i = sub.elementIterator(); i.hasNext(); ) {
			Element ele = (Element)i.next();
			String tag = ele.getName();
			String text = ele.getTextTrim();
			if(tag.equals("name")) ret.setName(text);
			else if(tag.equals("score")) ret.setScore(Integer.parseInt(text));
			else if(tag.equals("teacher")) ret.setTeacher(initTeacher(ele));
		}
		return ret;
	}
	
	private static Student initStudent(Element stu) {
		Student ret = new Student();
		for(Iterator i = stu.elementIterator(); i.hasNext(); ) {
			Element ele = (Element)i.next();
			String tag = ele.getName();
			String text = ele.getTextTrim();
			if(tag.equals("name")) ret.setName(text);
			else if(tag.equals("sex")) ret.setSex(text);
			else if(tag.equals("age")) ret.setAge(Integer.parseInt(text));
			else if(tag.equals("studentId")) ret.setStudentId(Long.parseLong(text));
			else if(tag.equals("birthday")) ret.setBirthday(initDate(ele));
			else if(tag.equals("subject")) ret.addSubject(initSubject(ele));
		}
		return ret;
	}
	
	private static SchoolClass initSchoolClass(Element sc) {
		SchoolClass ret = new SchoolClass();
		for(Iterator i = sc.elementIterator(); i.hasNext(); ) {
			Element ele = (Element)i.next();
			String tag = ele.getName();
			String text = ele.getTextTrim();
			if(tag.equals("name")) ret.setName(text);
			else if(tag.equals("student")) ret.addStudent(initStudent(ele));
		}
		ret.initSubjectTeacherMap();
		return ret;
	}
	
	public static boolean init() {
		File data = new File("testdata.xml");
		SAXReader sax = new SAXReader();
		Document doc = null;
		try {
			doc = sax.read(data);
			Element root = doc.getRootElement();
			for(Iterator i = root.elementIterator(); i.hasNext(); ) {
				Element schoolClass = (Element)i.next();
				School.classArray[School.classTotalNumber] = initSchoolClass(schoolClass);
//				System.out.println(School.classArray[School.classTotalNumber]);
				School.classTotalNumber++;
			}
		} catch (DocumentException e) {
//          logger.error(e.getMessage());
      }
		return true;
	}
	
	public static void main(String[] args) {
		init();
	}
}
