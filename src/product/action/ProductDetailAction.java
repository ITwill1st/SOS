package product.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductDetailService;
import vo.ActionForward;
import vo.ProductDTO;

public class ProductDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("ProductDetailAction - execute");
		ActionForward forward = null;
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		
		ProductDetailService service = new ProductDetailService();
		
		ProductDTO pdt = service.getProduct(item_num);
		
		request.setAttribute("bb", pdt);
		
		forward = new ActionForward();
		forward.setPath("/product/detail.jsp");
		
		return forward;
	}

}
