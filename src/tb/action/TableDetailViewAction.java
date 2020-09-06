package tb.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import tb.svc.TableDetailViewService;
import vo.ActionForward;
import vo.ProductBean;

public class TableDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		ActionForward forward = null;
		
		String tableNo = request.getParameter("tableNo");
		
		TableDetailViewService tableDetailViewService = new TableDetailViewService();
		
		//메뉴불러오기
		ArrayList<ProductBean> list = tableDetailViewService.getArticle();
		
		request.setAttribute("menu", list);
		request.setAttribute("tableNo", tableNo);
		
		forward = new ActionForward();
		forward.setPath("/table/detail.jsp");	
		
		return forward;
	}

}
