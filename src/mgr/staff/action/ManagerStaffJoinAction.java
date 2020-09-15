package mgr.staff.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import mgr.staff.svc.ManagerStaffJoinService;
import vo.ActionForward;
import vo.StaffDTO;

public class ManagerStaffJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		String emp_name = request.getParameter("emp_name");
		String emp_id = request.getParameter("emp_id");
		String emp_contact = request.getParameter("emp_contact");
		String emp_passwd = request.getParameter("emp_passwd");
		String emp_birth = request.getParameter("emp_birth");
		
		StaffDTO staffInfo = new StaffDTO();
		staffInfo.setEmp_name(emp_name);
		staffInfo.setEmp_id(emp_id);
		staffInfo.setEmp_passwd(emp_passwd);
		staffInfo.setEmp_contact(emp_contact);
		staffInfo.setEmp_birth(emp_birth);
		
		ManagerStaffJoinService managerStaffJoinService = new ManagerStaffJoinService();
		int check = managerStaffJoinService.dupCheckStaff(emp_id);
		
		if (check == 1) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter();	
			out.println("<script>");	
			out.println("alert('아이디 중복')"); 
			out.println("history.back()");	
			out.println("</script>");
		} else {
			int result = managerStaffJoinService.staffInsert(staffInfo);
			if (result > 0) {
				forward = new ActionForward();
				forward.setPath("Main.me");
				forward.setRedirect(true);
			} else {
				response.setContentType("text/html;charset=UTF-8"); 
				PrintWriter out = response.getWriter();	
				out.println("<script>");	
				out.println("alert('회원가입 실패')"); 
				out.println("history.back()");	
				out.println("</script>");
			}
		}
		return forward;
	}

}
