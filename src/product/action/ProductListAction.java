package product.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import tb.svc.MakeJsonService;
import vo.ActionForward;
import vo.PageInfo;
import vo.ProductBean;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("ProductAction");
		
		//상품 리스트
		ProductListService pls = new ProductListService();
		ArrayList<ProductBean> productList = pls.getProductList();
		
	// 상품 넘버 카운트
		ProductListService productListService = new ProductListService();
		int listCount = productListService.getListCount();
		
		//상품 카테고리
		ProductListService pls2 = new ProductListService();
		ArrayList<ProductBean> category = pls2.getCategoryList();
		
		
		request.setAttribute("productList", productList );
		request.setAttribute("category", category);
		
		forward = new ActionForward();
		forward.setPath("/product/product_list.jsp");

		
		return forward;
		
	}

}
