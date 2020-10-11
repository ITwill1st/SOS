package order.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberLoginProService;
import rsv.svc.RsvListCheckProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberOrderLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 회원 로그인 
		ActionForward forward = null;
		// test.jps에서 table_num 받아오기
		System.out.println("MemberOrderLoginProAction");
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		String mem_id = request.getParameter("mem_id");
		String mem_passwd = request.getParameter("mem_passwd");
		System.out.println(request.getParameter("mem_id"));
		System.out.println(request.getParameter("mem_passwd"));
		
		int isLoginSuccess = memberLoginProService.loginMember(mem_id, mem_passwd); 
		
	
		if(isLoginSuccess==1) {
			HttpSession session= request.getSession(); 
			session.setAttribute("mem_id", request.getParameter("mem_id"));
			MemberBean mb = new MemberBean();
			RsvListCheckProService listCheck = new RsvListCheckProService();
			mb = listCheck.getMemberInfo(mem_id);
			
			System.out.println("여기 오나?");
			// 로그인한 mem_id에 해당하는 mem_num 받아오기 
			int mem_num = mb.getMem_num();
			// session값으로 mem_num과 table_num전달 
			session.setAttribute("mem_num", mem_num);
			session.setAttribute("table_num", table_num);
			
			forward=new ActionForward();
			forward.setPath("OrderMain.or");
			forward.setRedirect(false);
			
		}else if(isLoginSuccess==0) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('아이디 틀림!')"); 
			out.println("history.back()");
			out.println("</script>");
			
		}else if(isLoginSuccess==-1) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter(); 
			out.println("<script>"); 
			out.println("alert('비밀번호 틀림!')"); 
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		
		
		return forward;
	}

}
