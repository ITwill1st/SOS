package member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import member.svc.MemberListService;
import vo.ActionForward;
import vo.MemberBean;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		MemberListService memberListService = new MemberListService();
		
		// 파라미터로 전달되는 항목이 하나라도 존재하면
		// getMemberList() 메서드에 파라미터를 전달하여 SELECT 구문의 조건을 설정하ㅗ
		// 파라미터가 없을 경우 SELECT 구문의 조건 설정 없이 기본 조회 수행
		String orderTarget = request.getParameter("orderTarget");
		String orderType = request.getParameter("orderType");
		
		ArrayList<MemberBean> list = memberListService.getMemberList();
		
		if(orderTarget == null) {
			list = memberListService.getMemberList();
		}else {
			list = memberListService.getMemberList(orderTarget, orderType);
		}
		
		// 조회된 전체 목록을 ArrayList<MemberBean> 객체로 전달받아
		// request 객체에 저장(memberList)
		request.setAttribute("memberList", list);
		
		forward = new ActionForward();
		forward.setPath("/member/member_list.jsp");
		
		
		return forward;
	}

}
