package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.dao.MemberDAO;
import vo.ActionForward;
import vo.MemberBean;

public class MemberMyInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		HttpSession session= request.getSession(); 
		String mem_id = (String)session.getAttribute("mem_id");
		System.out.println(mem_id);
		MemberDAO dao = MemberDAO.getInstance();
		MemberBean mb = new MemberBean();
	    mb = dao.getUserInfo(mem_id);
		session.setAttribute("mem_id", request.getParameter("mem_id"));
	    // UserInfoForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
	    session.setAttribute("memberInfo", mb);
	    
		
		forward=new ActionForward();
		forward.setPath("/member/myInfo.jsp");
		forward.setRedirect(false);
		
		
		
		
		return forward;
	}

}
