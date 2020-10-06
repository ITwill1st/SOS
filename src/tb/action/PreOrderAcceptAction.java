package tb.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import tb.svc.PreOrderAcceptService;
import vo.ActionForward;

public class PreOrderAcceptAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		
		PreOrderAcceptService service = new PreOrderAcceptService();
		
		int updateResult = service.acceptPreOrder(table_num);
		
		if (updateResult>0) {
			
			System.out.println("Preorder accept 성공!");
			
		} else {
			System.out.println("Preorder accept 실패!");
		}
		
		forward = new ActionForward();
		forward.setPath("/TablesMain.tb");
		
		return forward;
	}

}
