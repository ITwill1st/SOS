package tb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import tb.svc.TableInfoSaveService;
import vo.ActionForward;

public class TableInfoSaveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		boolean isSuccess = false;
		
		String tableParam = request.getParameter("tableParam");
		
		ActionForward forward = null;
		
		TableInfoSaveService tableInfoSaveService = new TableInfoSaveService();
		
		isSuccess = tableInfoSaveService.getArticle(tableParam);
		
		
		if(isSuccess) {
	
			response.setContentType("text/html;charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('테이블 저장 성공!')");// 메세지 출력
			out.println("location.href='TablesEdit.tb'");// 페이지 새로고침
			out.println("</script>");
			
		}else if(!isSuccess){
			response.setContentType("text/html;charset=UTF-8");

			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('테이블 저장 실패!')");// 오류메세지 출력
			out.println("location.href='TablesEdit.tb'");// 페이지 새로고침
			out.println("</script>");			
		
		}else {
			
			forward = new ActionForward();
			forward.setRedirect(true); // redirect 방식 지정
			forward.setPath("TablesEdit.tb");// 포워딩 주소 지정		
		}		
		
		return forward;
	}

}
