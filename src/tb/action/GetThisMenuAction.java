package tb.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import tb.svc.GetThisMenuService;
import vo.ActionForward;
import vo.ProductBean;

public class GetThisMenuAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		String thisCategory = request.getParameter("thisCategory");
		
		GetThisMenuService getThisMenuService = new GetThisMenuService();
		
		ArrayList<ProductBean> list = getThisMenuService.getArticle();
		
		
		
		
		forward = new ActionForward();
		forward.setPath("/table/detail.jsp");	
		
		return forward;
	}

}
