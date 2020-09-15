package order.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;

import order.svc.OrderService;

import vo.ActionForward;
import vo.BasketBean;


public class OrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		// id, table_num 가져오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));

		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		
		// mem_num, table_num에 해당하는 preorder가져오기 
		OrderService os = new OrderService();
		ArrayList<BasketBean> preorderList = os.getPreOrder(basket);
		
		// preorder가 없을경우 
		if(preorderList == null) {
					
			//자바스크립트로 장바구니 없음 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('주문목록이 비어있습니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
					
		} else {
		
			// 가져온 preorder를 order테이블에 담기 위한 서비스 호출 
			OrderService os2 = new OrderService();
			int insertResult = os2.insertOrder(preorderList);

			if (insertResult>0) {
						
				System.out.println("order 성공!");
						
				// preorder 항목이 order테이블에 insert 성공했을시 
				// 기존 preorder의 order_tossed 값을 1로 바꿔주기 
				OrderService os3 = new OrderService();
				int updateResult = os3.updatePreOrder(mem_num,table_num);
						
				if (updateResult>0) {
					 System.out.println("order_tossed =1 성공!");
				} else {
					System.out.println("order_tossed =1 실패!");
				}
						
			} else {
				System.out.println("order 실패!");
			}
					
					
			forward = new ActionForward();
			forward.setPath("/OrderMain.or");
		
		}
	
		return forward;
	}
}

