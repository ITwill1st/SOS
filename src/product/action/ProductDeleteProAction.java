package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductDeleteProService;
import vo.ActionForward;

public class ProductDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductDeleteProAction!");
		
		ActionForward forward = null;
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		
		boolean isDeleteSuccess = ProductDeleteProService.removeProduct(item_num);
	
		if (!isDeleteSuccess) {
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			
			out.println("<script>"); 
			out.println("alert('글 삭제 실패!')"); 
			out.println("history.back()"); 
			out.println("</script>"); 
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("ProductList.po?page=" + request.getParameter("page"));
		}
		
		return forward;
	}

	

}
