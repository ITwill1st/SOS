package mgr.staff.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import order.svc.MainPageProService;
import vo.ActionForward;
import vo.ProductBean;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session= request.getSession(); 
		MainPageProService mls = new MainPageProService();
		ArrayList<ProductBean> menuList = mls.getMenuList();
		
		MainPageProService mls2 = new MainPageProService();
		ArrayList<ProductBean> category = mls2.getCategory();
			
		forward = new ActionForward();
		forward.setPath("index.jsp");
		session.setAttribute("menuList", menuList);
		session.setAttribute("category", category);
		return forward;
	}

}
