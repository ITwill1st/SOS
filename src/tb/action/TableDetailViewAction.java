package tb.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import tb.svc.MakeJsonService;
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
		
		
		//중복을 제거하여 존재하는 카테고리를 HashSet에 저장한다
		//카테고리 갯수는  hash.size();
		HashSet<String> hash = new HashSet<String>();
		
		for(int i=0; i<list.size(); i++) {
			hash.add(list.get(i).getItem_category());
		}
		
		Iterator<String> iteratorCategory = hash.iterator();
		
		MakeJsonService makeJsonService = new MakeJsonService();
		
		String category = makeJsonService.getCategoryJson(iteratorCategory);
		
//		System.out.println(category);
		
		request.setAttribute("category", category);
		request.setAttribute("menu", list);
		request.setAttribute("tableNo", tableNo);
		
		forward = new ActionForward();
		forward.setPath("/table/detail.jsp");	
		
		return forward;
	}

}
