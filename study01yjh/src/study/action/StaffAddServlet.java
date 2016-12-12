package study.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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


@WebServlet("/StaffAddServlet")
public class StaffAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    Religion religion = new Religion();
    StaffDao staffDao = new StaffDao();
    StaffSkill staffSkill = new StaffSkill();
    Staff staff = new Staff();
    ArrayList<Religion> alr = new ArrayList<Religion>();
    ArrayList<School> als = new ArrayList<School>();
    ArrayList<Skill> alSkill = new ArrayList<Skill>();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("등록요청 겟방식 시작!");

		try {
			alr = staffDao.r_selectAll();
			als = staffDao.s_selectAll();
			alSkill = staffDao.skill_selectAll();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//System.out.println(alr.get(2));
		request.setAttribute("alr", alr);
		request.setAttribute("als", als);
		request.setAttribute("alSkill", alSkill);
		request.getRequestDispatcher("/staffAdd.jsp").forward(request, response);
	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("등록요청 포스트방식 시작!");
		request.setCharacterEncoding("euc-kr");
		
		String staffName = request.getParameter("staffName");
		String sn = request.getParameter("snf")+request.getParameter("snl");
		int religionNo = Integer.parseInt(request.getParameter("religion"));
		int graduate = Integer.parseInt(request.getParameter("schoolGraduate"));
		String[] skillName = request.getParameterValues("skillName");
		String graduateDay = request.getParameter("graduateDay");
		
		//System.out.println(staffName);
		//System.out.println(sn);
		//System.out.println(religionName);
		//System.out.println(graduate);
		//System.out.println(graduateDay);
		
		staff.setStaffName(staffName);
		staff.setStaffSn(sn);
		staff.setGraduateday(graduateDay);
		staff.setSchoolNo(graduate);
		staff.setReligionNo(religionNo);
		
		int result = staffDao.staffInsert(staff);
		System.out.println(result);
		int no = staffDao.selectLast();
		 
		for(int i=0 ; i < skillName.length ; i++){
			//System.out.println(skillName[i]);
			staffDao.staffSkillInsert(no, Integer.parseInt(skillName[i]));
			
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
	}

}
