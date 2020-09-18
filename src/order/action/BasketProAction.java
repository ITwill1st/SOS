package order.action;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketProService;
import vo.ActionForward;
import vo.BasketBean;




public class BasketProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 장바구니 담기 
		ActionForward forward = null;


		// mem_num, table_num 가져오기 
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));		
		
		// 장바구니에 담을 정보 가져오기 
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		int item_qty = Integer.parseInt(request.getParameter("item_qty"));		
		
		// BasketBean에 전달할 정보 저장하기 위해 BasketBean 객체 생성
		BasketBean basket = new BasketBean();
		basket.setItem_num(item_num);
		basket.setItem_qty(item_qty);
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		
		// 장바구니에 이미 담겨있는 항목인지 확인하기 위해 서비스 호출
		BasketProService bps = new BasketProService();
		boolean isAlreadyInsert = bps.basketInsertCheck(basket);
		
		// 이미 담겨있는 항목이라면 update
		// 처음 담는 항목이라면 insert 
		
		if(isAlreadyInsert) {
			// 이미 담겨있는 항목이므로 수량을 update 
			
			BasketProService bps2 = new BasketProService();
			int updateResult = bps2.updateBasket(basket);
			
			if(updateResult>0) {
				System.out.println("장바구니 담기 성공!");
			} else {
				System.out.println("장바구니 담기 실패!");
			}
			
		} else {
			
			// 처음 담는 항목이므로 insert 
			// 담긴 정보를 basket table에 저장하기 위한 서비스 호출
			BasketProService bps3 = new BasketProService();
			int insertResult = bps3.insertBasket(basket);
			
			if(insertResult>0) {
				
				System.out.println("장바구니 담기 성공!");
				//자바스크립트로 실패 메세지 출력
				response.setContentType("text/html;charset=UTF-8");//문서타입지정
				PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
				//println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('장바구니 담기에 성공했습니다!')");//메세지 출력
				out.println("</script>");
				
			} else {
				
				System.out.println("장바구니 담기 실패!");
				//자바스크립트로 실패 메세지 출력
				response.setContentType("text/html;charset=UTF-8");//문서타입지정
				PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
				//println()메서드로 문자열 출력
				out.println("<script>");
				out.println("alert('장바구니 담기에 실패했습니다!')");//메세지 출력
				out.println("</script>");
			}
			
		}
		
		forward = new ActionForward();
		forward.setPath("OrderMain.or");
		// 장바구니에 담은 후 메인페이지로 다시 이동 
		
		return forward;
		
	}

}
