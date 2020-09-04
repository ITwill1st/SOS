package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ProductListService productListService = new ProductListService();
		int listCount = productListService.getListCount();
		
		ArrayList<ProductBean> productList = productListService.getProductList(page, limit);
		
		int maxPage = (int)((double)listCount / limit+0.95);
		int startPage = ((int)((double)page / 10 + 0.9)-1)*10 + 1;
		int endPage = startPage + 10 -1;
		
		if (endPage>maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("productList", productList );
		
		forward = new ActionForward();
		forward.setPath("/product/product_list.jsp");
		
		
		return forward;
	}

}
