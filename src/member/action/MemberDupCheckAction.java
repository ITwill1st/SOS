package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.svc.MemberJoinProService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberDupCheckAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = null;
      System.out.println("memberJoinPro.me");
      String mem_id = request.getParameter("mem_id");
      
      
      MemberJoinProService memberJoinProService = new MemberJoinProService();
      int check = memberJoinProService.dupCheckMember(mem_id);
      if(check == 1 ) {
         response.setContentType("text/html;charset=UTF-8"); 
         PrintWriter out = response.getWriter();   
         out.print("중복된 ID입니다");   
      }else {         
         response.setContentType("text/html;charset=UTF-8"); 
         PrintWriter out = response.getWriter();   
         out.print("사용 가능한 ID입니다.");   
      }
      
      
      return forward;
   }

}