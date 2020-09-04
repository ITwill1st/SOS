package product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductDeleteProService;
import product.svc.ProductDetailService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	System.out.println("ProductModifyFormAction!");
	ActionForward forward = null;
	
	int item_num = Integer.parseInt(request.getParameter("item_num"));
	String page = request.getParameter("page");
	
	ProductDetailService productDetailService = new ProductDetailService();
	ProductBean product = productDetailService.getProduct(item_num);
	
	request.setAttribute("product",product);
	request.setAttribute("page",page);
	
	forward = new ActionForward();
	forward.setPath("/product/product_modify.jsp");
		return forward;
	}

}
