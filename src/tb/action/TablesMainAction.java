package tb.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductListService;
import tb.svc.TableInfoProService;
import tb.svc.TablePreOrderListService;
import tb.svc.TableProductListService;
import vo.ActionForward;
import vo.PreOrderBean;
import vo.ProductBean;
import vo.TableDTO;

public class TablesMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		//제품 리스트 불러오기
		TableProductListService tableProductListService = new TableProductListService();
		
		ArrayList<ProductBean> product_info = tableProductListService.getArticle();
		
		//테이블 리스트 불러오기
		TableInfoProService tableInfoProService = new TableInfoProService();
		
		ArrayList<TableDTO> list = tableInfoProService.getArticle();
			
		//preorder 정보 불러오기
		TablePreOrderListService tablePreOrderListService = new TablePreOrderListService();
		
		ArrayList<PreOrderBean> list2 = tablePreOrderListService.getArticle();
		
		
		request.setAttribute("product_info", product_info);
		request.setAttribute("tableInfo", list);
		request.setAttribute("preorderInfo", list2);
		 
		 
		forward = new ActionForward();
		forward.setPath("/table/main.jsp");	
		
		return forward;
	}

}
