package study.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;




public class StaffDao {
	private final String driverClassName = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://127.0.0.1:3306/jjdev?useUnicode=true&characterEncoding=euckr";
	private final String username="root";
	private final String password = "java0000";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ResultSet skillRs;
	
	Staff staff;
	Religion religion;
	School school;
	Skill skill;
	StaffSkill staffSkill;
	String sql = "select * from ";
	String skillName;
	int rowCount;
	ArrayList<Religion> alr;
	ArrayList<School> als;
	ArrayList<Skill> alSkill;
	ArrayList<Staff> alStaff;
	ArrayList<String> alString;
	
	
	
	//12 staff ��系�� ��ȸ religion��school���̺�staff���� PK=FK�������� name�÷� �Ӽ����� ���� ��ȸ  .
	public Staff selectAll(int no){
		try{
			
			conn=this.getConnection();
			pstmt = conn.prepareStatement("select staff.no,staff.name,staff.sn,staff.graduateday,school.graduate,religion.name from staff,religion,school where staff.religionno=religion.no and staff.schoolno=school.no and staff.no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			alStaff = new ArrayList<Staff>();
			while(rs.next()){
				staff = new Staff();
				staff.setStaffNo(rs.getInt("no"));
				staff.setStaffName(rs.getString("name"));
				staff.setStaffSn(rs.getString("sn"));
				staff.setGraduateday(rs.getString("graduateday"));
				staff.setReligionName(rs.getString("religion.name"));
				//System.out.println(staff.getReligionName());
				staff.setSchoolGraduate(rs.getString("school.graduate"));
				//System.out.println(staff.getSchoolGraduate());
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs );
		}
		return staff;
	}
	
	//11  staff���̺� no������ ��ų�˻�
	public ArrayList<String> selectSkillByPK(int staffNo){
		try{
			conn=this.getConnection();
			pstmt = conn.prepareStatement("select * from staffskill where staffno = ?");
			pstmt.setInt(1, staffNo);
			rs = pstmt.executeQuery();
			alString = new ArrayList<String>();
			while(rs.next()){
				pstmt = conn.prepareStatement("select name from skill where no=?");
				pstmt.setInt(1, rs.getInt("skillno"));
				skillRs = pstmt.executeQuery();
				while(skillRs.next()){
					skillName = skillRs.getString("name");
					alString.add(skillName);
				}
			
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs );
			this.close(null,null,skillRs);
		}
		return alString;
	}
	
	
	//10 �����̸Ӹ�Ű������ staff���̺� ���Ǻ��� �˻�
	public Staff selectPK(int no){
		try{
			
			conn=this.getConnection();
			pstmt = conn.prepareStatement("select * from staff where no=?");
			pstmt.setInt(1,no);
			
			rs = pstmt.executeQuery();
			alStaff = new ArrayList<Staff>();
			while(rs.next()){
				staff = new Staff();
				staff.setStaffNo(rs.getInt("no"));
				staff.setStaffName(rs.getString("name"));
				staff.setStaffSn(rs.getString("sn"));
				staff.setGraduateday(rs.getString("graduateday"));
				staff.setSchoolNo(rs.getInt("schoolno"));
				staff.setReligionNo(rs.getInt("religionno"));
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs );
		}
		return staff;
	}
	
	
	//09 �����Ⱓ���� ���Ǻ��� �˻�
	public ArrayList<Staff> selectKeyGraduateDay(String graduateDayStart,String graduateDayLast){
		try{
			
			System.out.println("�����Ⱓ :"+graduateDayStart+"~"+graduateDayLast);
			conn=this.getConnection();
			pstmt = conn.prepareStatement("select * from staff where graduateday between ? and ? group by no");
			pstmt.setString(1, graduateDayStart);
			pstmt.setString(2, graduateDayLast);
			rs = pstmt.executeQuery();
			alStaff = new ArrayList<Staff>();
			while(rs.next()){
				staff = new Staff();
				staff.setStaffNo(rs.getInt("no"));
				staff.setStaffName(rs.getString("name"));
				staff.setStaffSn(rs.getString("sn"));
				staff.setGraduateday(rs.getString("graduateday"));
				staff.setSchoolNo(rs.getInt("schoolno"));
				staff.setReligionNo(rs.getInt("religionno"));
				alStaff.add(staff);
				System.out.println(alStaff);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs );
		}
		return alStaff;
	}
	
	//08 staff���̺� name�� �������� ���Ǻ��� ��ü�˻�
	public ArrayList<Staff> selectKeyNameGraduate(String staffName,int graduate){
		try{
			conn=this.getConnection();
			pstmt = conn.prepareStatement("select * from staff where name like ? and schoolno=?");
			pstmt.setString(1, "%"+staffName+"%");
			pstmt.setInt(2, graduate);
			rs = pstmt.executeQuery();
			alStaff = new ArrayList<Staff>();
			while(rs.next()){
				staff = new Staff();
				staff.setStaffNo(rs.getInt("no"));
				staff.setStaffName(rs.getString("name"));
				staff.setStaffSn(rs.getString("sn"));
				staff.setGraduateday(rs.getString("graduateday"));
				staff.setSchoolNo(rs.getInt("schoolno"));
				staff.setReligionNo(rs.getInt("religionno"));
				alStaff.add(staff);
				System.out.println(alStaff);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs );
		}
		return alStaff;
	}
	
	//07 �ֱ� �Է��� Ű�� �˻�
	public int selectLast(){
		try{
			
			conn=this.getConnection();
			pstmt = conn.prepareStatement("select * from staff no order by no desc limit 1");
			rs = pstmt.executeQuery();
			if(rs.next()){
				rowCount = rs.getInt("no");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs );
		}
		return rowCount;
	}
	
	//06 ���̺� �ִ� noī��Ʈ ���ϱ�
	public int selectCount(){
		try{
			conn=this.getConnection();
			pstmt = conn.prepareStatement("select count(*) from staff");
			rs = pstmt.executeQuery();
			if(rs.next()){
				rowCount = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs );
		}
		return rowCount;
	}
	
	//05 staffSkill���̺� �Է�
	public int staffSkillInsert(int staffNo, int skillNo){
		try{
			conn=this.getConnection();
			pstmt = conn.prepareStatement("insert into staffSkill(staffno,skillno) values(?,?)");
			pstmt.setInt(1, staffNo);
			pstmt.setInt(2, skillNo);
			
			rowCount = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,null );
		}
		return rowCount;
	}
	
	/*staff����ϰ� getGeneratedKeys���Ͽ� staffskill���̺� Ű�������ϰ� �Է¹��� ��ų��ȣ �����Ͽ� 
	 * �Է�ó��. ConnectionAPI�� commit(),rollback()�� ����Ͽ� staffskill���̺� ��Ȯ��
	 * �������� ��ų�� ���õǰ� ��.
	 * */
	public int staffInsert(Staff s,String[] skillNo){
		try{
			conn=this.getConnection();
			int staffNo = 0;
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("insert into staff(name,sn,graduateday,schoolno,religionno) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, s.getStaffName());
			pstmt.setString(2, s.getStaffSn());
			pstmt.setString(3, s.getGraduateday());
			pstmt.setInt(4, s.getSchoolNo());
			pstmt.setInt(5, s.getReligionNo());
			rowCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				staffNo = rs.getInt(1);
				for(int i=0 ; i < skillNo.length ; i++){
					pstmt = conn.prepareStatement("insert into staffSkill(staffno,skillno) values(?,?)");
					pstmt.setInt(1, staffNo);
					pstmt.setInt(2, Integer.parseInt(skillNo[i]));
					pstmt.executeUpdate();
				}
				
			}
			conn.commit();
			conn.setAutoCommit(true);
		}catch(Exception e){
			try{conn.rollback();}catch(Exception ignore){};
			e.printStackTrace();
		}finally{
			this.close(conn,pstmt,rs);
			
		}
		
		return rowCount;
	}
	
	//03 ��ü ��� ��ȸ
	public ArrayList<Skill> skill_selectAll() throws SQLException{
		try{
			alSkill = new ArrayList<Skill>();
			conn=this.getConnection();
			pstmt = conn.prepareStatement(sql+"skill");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				skill= new Skill();
				skill.setSkillNo(rs.getInt("no"));
				skill.setSkillName(rs.getString("name"));
				alSkill.add(skill);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, pstmt, rs);
		}
		return alSkill;
	}
	
	//02 ��ü �з� ��ȸ
	public ArrayList<School> s_selectAll() throws SQLException{
		try{
			als = new ArrayList<School>();
			conn=this.getConnection();
			pstmt = conn.prepareStatement(sql+"school");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				school = new School();
				school.setSchoolNo(rs.getInt("no"));
				school.setSchoolGraduate(rs.getString("graduate"));
				als.add(school);
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, pstmt, rs);
		}
		return als;
	}
	
	//01 ��ü ���� ��ȸ
	public ArrayList<Religion> r_selectAll() throws SQLException{
		try{
			alr = new ArrayList<Religion>();
			conn=this.getConnection();
			pstmt = conn.prepareStatement(sql+"religion");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				religion = new Religion();
				religion.setReligionNo(rs.getInt("no"));
				religion.setReligionName(rs.getString("name"));
				alr.add(religion);
				System.out.println(alr);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(conn, pstmt, rs);
		}
		return alr;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driverClassName);
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
	
	private void close(Connection conn, Statement pstmt, ResultSet rs){
		if(rs != null){
			try{
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try{
				pstmt.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try{
				conn.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}

