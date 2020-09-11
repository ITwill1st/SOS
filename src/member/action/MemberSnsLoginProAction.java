package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.dao.MemberDAO;
import member.svc.MemberSnsLoginProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberSnsLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberSnsLoginProAction");
		ActionForward forward=null;
		boolean isSnsLoginsuccess=false;
		String mem_id=request.getParameter("mem_id");
		String mem_email = request.getParameter("mem_email");
		String mem_birth = request.getParameter("mem_birth");
		String mem_name = request.getParameter("mem_name");
		boolean mem_gender = false;
		if(request.getParameter("mem_gender").equals("male") ||request.getParameter("mem_gender").equals("M") ) {
			mem_gender = true;
		}else {
			mem_gender = false;
		}
		
		System.out.println(mem_id);
		System.out.println(mem_email);
		System.out.println(mem_birth);
		System.out.println(mem_gender);
		System.out.println(mem_name);
		
		
		MemberBean mb = new MemberBean();
		mb.setMem_name(request.getParameter("mem_name"));
		mb.setMem_id(request.getParameter("mem_id"));
		mb.setMem_email(request.getParameter("mem_email"));
		mb.setMem_birth(request.getParameter("mem_birth"));
		mb.setMem_gender(mem_gender);
		MemberSnsLoginProService SnsProService=new MemberSnsLoginProService();
		isSnsLoginsuccess=SnsProService.snsLogin(mb);
		
		
		
		if(isSnsLoginsuccess) {
//			request.setAttribute("email",email);
			HttpSession session= request.getSession(); 
			session.setAttribute("mem_id", request.getParameter("mem_id"));
			session.setAttribute("mem_email", request.getParameter("mem_email"));
			MemberDAO dao = MemberDAO.getInstance();
		    mb = dao.getUserInfo(mem_id);
		       
		    // UserInfoForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
		    session.setAttribute("memberInfo", mb);
			
			forward=new ActionForward();
			forward.setPath("/index.jsp");
			forward.setRedirect(false);
		}else {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('로그인 실패!')"); 
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
