package product.action;

import java.io.*;

import javax.servlet.http.*;

import org.json.simple.*;

import action.*;
import order.svc.*;
import product.svc.*;
import vo.*;

public class CategoryShowProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  ActionForward forward = null;
		   
		  // 선택된 카테고리 값 가져오기 
	      String category = request.getParameter("item_category");

	      

	      // 카테고리에 해당하는 상품 가져오기 위한 메서드 호출 
	     ProductListService pls = new ProductListService();
	      // JSONARRAY 타입으로 받아오고 category 전달 
	      JSONArray productList = pls.getProductList(category);
	      
	      response.setContentType("application/json;charset=UTF-8");
	      PrintWriter out = response.getWriter();
	      out.println(productList);
	      return null;
	}

}
