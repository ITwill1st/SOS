package order.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import action.Action;
import order.svc.BasketProService;
import order.svc.PreOrderService;
import vo.ActionForward;
import vo.BasketBean;


public class BasketToPreOrder implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 장바구니 table에 담긴 항목을 preorder테이블에 담기 
		ActionForward forward = null;
		
		HttpSession session= request.getSession(); 
		
		// mem_num & table_num 가져오기
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));
		
		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		// mem_num & table_num에 해당하는 장바구니 가져오기 
		// 현재 장바구니 조회하기위해 BasketProService 호출 
		BasketProService bps = new BasketProService();
		ArrayList<BasketBean> basketList = bps.getBasketList(mem_num,table_num);
		
		// 장바구니가 비어있으면 경고 후 
		if(basketList.isEmpty()) {
			
			//자바스크립트로 실패 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('장바구니가 비어있습니다.')");//메세지 출력
			out.println("history.back()");//이전페이지로 이동
			out.println("</script>");
			
		} else {
			
			
			// basket에 저장된 정보를 preorder 테이블에 저장하기 위해 서비스 호출 
			PreOrderService pos = new PreOrderService();
			int insertResult = pos.basketToPreOrder(basketList);
			
			if (insertResult>0) {
				
				System.out.println("Preorder 성공!");
				
				//통신을 위한 세션 메서드
				session.setAttribute("preorder", "true");
				
				// 장바구니 항목이 preorder 테이블에 insert 성공했을시 기존 장바구니 테이블 삭제하기 
				BasketProService bps2 = new BasketProService();
				int deleteResult = bps2.deleteBasketTable(mem_num,table_num);
				
				if (deleteResult>0) {
					System.out.println("장바구니 삭제 성공!");
				} else {
					System.out.println("장바구니 삭제 실패!");
				}
				
			} else {
				System.out.println("Preorder 실패!");
			}
			
			
			forward = new ActionForward();
			forward.setPath("/OrderMain.or?");
			
			
		}

		
		return forward;
	}

}
