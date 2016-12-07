package dto;

public class Staff {
	private int no;
	private String name;
	private String sn;
	private String graduateDay;
	private School school;
	private Religion religion;
	public Staff() {
		super();
	}
	public Staff(int no, String name, String sn, String graduateDay, School school, Religion religion) {
		super();
		this.no = no;
		this.name = name;
		this.sn = sn;
		this.graduateDay = graduateDay;
		this.school = school;
		this.religion = religion;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getGraduateDay() {
		return graduateDay;
	}
	public void setGraduateDay(String graduateDay) {
		this.graduateDay = graduateDay;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
	@Override
	public String toString() {
		return "Staff [no=" + no + ", name=" + name + ", sn=" + sn + ", graduateDay=" + graduateDay + ", school="
				+ school + ", religion=" + religion + "]";
	}
	

}
