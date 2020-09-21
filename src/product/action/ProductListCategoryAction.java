package product.action;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.json.simple.*;

import action.*;
import product.svc.*;
import vo.*;

public class ProductListCategoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		System.out.println("ProductListCategory");
		String category = request.getParameter("category");
		int page = 1;
		int limit = 50;
		ProductListService productListService = new ProductListService();

		JSONArray productList = productListService.getProductList(page,limit,category);
		int listCount = productListService.getListCount();

		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(productList);
		return null;
	}

}
