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
		String category = request.getParameter("category");
		System.out.println(category);
		
		
		int page = 1;
		int limit = 50;
		
		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ProductListService productListService = new ProductListService();
		int listCount = productListService.getListCount();
		
		
//		if(category==null) {
//		
//		ArrayList<ProductBean> productList = productListService.getProductList(page, limit);
//		}else {
//		ArrayList<ProductBean> productList = productListService.getProductList(page, limit,category);
//
//		}
		ArrayList<ProductBean> productList =null;
		
		if(category == null) {
			System.out.println("카테고리 없음");
			productList = productListService.getProductList(page, limit);
		}else {
			System.out.println("카테고리 있음");
			productList = productListService.getProductList(page,limit,category);
			
		}
			
		
		int maxPage = (int)((double)listCount / limit+0.95);
		int startPage = ((int)((double)page / 10 + 0.9)-1)*10 + 1;
		int endPage = startPage + 10 -1;
		
		if (endPage>maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		
		
		//=========================
		
		HashSet<String> hash = new HashSet<String>();
		
		for(int i=0; i<productList.size(); i++) {
			hash.add(productList.get(i).getItem_category());
		}
		
		Iterator<String> iteratorCategory = hash.iterator();
		
		MakeJsonService makeJsonService = new MakeJsonService();
		
		category = makeJsonService.getCategoryJson(iteratorCategory);
		
//		System.out.println(category);
		
		request.setAttribute("category", category);
		
		
		System.out.println("aaa" + productList);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("productList", productList );
		
		forward = new ActionForward();
		forward.setPath("/product/product_list.jsp");
		
		
		return forward;
		
	}

}
