package study.action;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.db.Religion;
import study.db.School;
import study.db.Skill;
import study.db.Staff;
import study.db.StaffDao;
import study.db.StaffSkill;


@WebServlet("/StaffSearchServlet")
public class StaffSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Religion religion = new Religion();
    StaffDao staffDao = new StaffDao();
    StaffSkill staffSkill = new StaffSkill();
    Staff staff;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Skill> skillList = new ArrayList<Skill>(); 
		ArrayList<Religion> religinList = new ArrayList<Religion>();
		ArrayList<School> schoolList = new ArrayList<School>();
		System.out.println("staff정보 조회화면 get요청");
		try {
			religinList = staffDao.r_selectAll();
			schoolList = staffDao.s_selectAll();
			skillList = staffDao.skill_selectAll();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		request.setAttribute("alr", religinList);
		request.setAttribute("als", schoolList);
		request.setAttribute("alSkill", skillList);
		request.getRequestDispatcher("/staffSearchForm.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//staffListByNameAndGraduate 이름과 최종학력 비교후 결과값
		ArrayList<Staff> staffListByNameAndGraduate = new ArrayList<Staff>();
	    //staffListByGraduateDay 졸업일 비교후 결과값
		ArrayList<Staff> staffListByGraduateDay = new ArrayList<Staff>();
	    
		System.out.println("staff정보 조회화면 post요청");
		request.setCharacterEncoding("euc-kr");
		
		String staffName = request.getParameter("staffName");
		String[] gender = request.getParameterValues("gender");
		int religionNo = Integer.parseInt(request.getParameter("religion"));
		int graduate = Integer.parseInt(request.getParameter("schoolGraduate"));
		String[] skillName = request.getParameterValues("skillName");
		String graduateDayStart = request.getParameter("graduateDayStart");
		String graduateDayLast = request.getParameter("graduateDayLast");
		
		
		staffListByNameAndGraduate = staffDao.selectKeyNameGraduate(staffName, graduate);
		//staffListAfterNR 주민번호와 종교일치여부 비교후 결과값
		ArrayList<Staff> staffListBySnReligion = new ArrayList<Staff>();
		for(Staff s : staffListByNameAndGraduate){
			
			//System.out.println("비교할주민번호값 : "+s.getStaffSn().substring(6, 7));
			if(s.getStaffSn().substring(6, 7).equals(gender[0]) || s.getStaffSn().substring(6, 7).equals(gender[1]) ){
				if(s.getReligionNo() == religionNo){
					staff = new Staff();
					staff.setStaffNo(s.getStaffNo());
					staff.setGraduateday(s.getGraduateday());
					staffListBySnReligion.add(staff);
					System.out.println("살아남은 주소값 : "+ staffListBySnReligion);
				}
			}
		}
		// 스킬제외 모든값 비교후 추려진 staff테이블의 no값 lastNo
		ArrayList<Integer> lastNo = new ArrayList<Integer>();
		staffListByGraduateDay = staffDao.selectKeyGraduateDay(graduateDayStart, graduateDayLast);
		for(Staff a : staffListByGraduateDay){
			for(Staff b : staffListBySnReligion){
				if(a.getStaffNo()==(b.getStaffNo())){
					int no = a.getStaffNo();
					lastNo.add(no);
				}
			}
		}
		System.out.println("lastNo사이즈 : "+lastNo.size());
		// staffListByAll 모든조건 비교후 모든조건 일치하는 값
		ArrayList<Staff> staffListByAll = new ArrayList<Staff>();
		// skillList  : lastNo[]에 있는 staff PK값으로 조회한 스킬리스트
		ArrayList<String> skillList = new ArrayList<String>();
		ArrayList<Integer> lastNo2 = new ArrayList<Integer>();
		ArrayList<String> sName = new ArrayList<String>(Arrays.asList(skillName));
		for(int i=0; i < lastNo.size(); i++){
			skillList= staffDao.selectSkillByPK(lastNo.get(i));
			if(sName.equals(skillList)){
				int staffNo = lastNo.get(i);
				lastNo2.add(staffNo);
				
			}
		}
		System.out.println("최종결과"+lastNo2.size());
		
		for(int i=0; i < lastNo2.size(); i++){
			staff = staffDao.selectAllByPK(lastNo2.get(i));
			staffListByAll.add(staff);
			
		}
		request.setAttribute("staff", staffListByNameAndGraduate);
		request.getRequestDispatcher("/staffSearchAction.jsp").forward(request, response);
	}	
}
