package tb.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import tb.svc.TableInfoProService;
import vo.ActionForward;
import vo.TableDTO;

public class TablesMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		TableInfoProService tableInfoProService = new TableInfoProService();
		
		 ArrayList<TableDTO> list = tableInfoProService.getArticle();
			
		request.setAttribute("tableInfo", list);
		 
		 
		forward = new ActionForward();
		forward.setPath("/table/main.jsp");	
		
		return forward;
	}

}
