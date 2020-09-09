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
		String member_id=request.getParameter("member_id");
		String member_email = request.getParameter("member_email");
		String member_birth = request.getParameter("member_birth");
		String member_gender = request.getParameter("member_gender");
		String member_name = request.getParameter("member_name");
		String member_passwd = request.getParameter("member_passwd");
		System.out.println(member_id);
		System.out.println(member_email);
		System.out.println(member_birth);
		System.out.println(member_gender);
		System.out.println(member_name);
		System.out.println(member_passwd);
		
//		boolean gender = false;
//		if(member_gender.equals("male")||member_gender.equals("M")) {
//			gender = true;
//		} 젠더 boolean 타입으로 바꾸고 회원 등급 추가
		
		MemberBean memberBean = new MemberBean();
		memberBean.setMember_name(request.getParameter("member_name"));
		memberBean.setMember_id(request.getParameter("member_id"));
		memberBean.setMember_email(request.getParameter("member_email"));
		memberBean.setMember_birth(request.getParameter("member_birth"));
		memberBean.setMember_gender(request.getParameter("member_gender"));
		memberBean.setMember_passwd(request.getParameter("member_passwd"));
		MemberSnsLoginProService SnsProService=new MemberSnsLoginProService();
		isSnsLoginsuccess=SnsProService.snsLogin(memberBean);
		
		
		
		if(isSnsLoginsuccess) {
//			request.setAttribute("email",email);
			HttpSession session= request.getSession(); 
			session.setAttribute("member_id", request.getParameter("member_id"));
			session.setAttribute("member_email", request.getParameter("member_email"));
			session.setAttribute("member_passwd", request.getParameter("member_passwd"));
			MemberDAO dao = MemberDAO.getInstance();
		    memberBean = dao.getUserInfo(member_id);
		       
		    // UserInfoForm.jsp에 회원정보를 전달하기 위해 request에 MemberBean을 세팅한다.
		    session.setAttribute("memberInfo", memberBean);
			
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
