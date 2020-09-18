package order.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.svc.BasketProService;
import order.svc.OrderService;
import order.svc.StringToArrayListService;
import vo.ActionForward;
import vo.BasketBean;
import vo.ProductInfoBean;


public class OrderProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 결제하기 
		// preorder의 정보 -> order 테이블과  review check table 테이블에 넣어주기 
		ActionForward forward = null;
		
		// mem_num, table_num 가져오기
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		int table_num = Integer.parseInt(request.getParameter("table_num"));

		BasketBean basket = new BasketBean();
		basket.setMem_num(mem_num);
		basket.setTable_num(table_num);
		
		
		// mem_num, table_num에 해당하는 preorder가 있는지 먼저 확인! 
		OrderService os = new OrderService();
		int preorderCount = os.getPreorderCount(basket);
	
		
		// preorder가 존재할 경우 
		if(preorderCount > 0) {
			// preorder를 order테이블에 담기 위한 서비스 호출  
			OrderService os2 = new OrderService();
			int insertResult = os2.insertOrder(basket);

						
			// 동현쓰를 위한 review check table에 값 넘겨주기 
			// 이전에 만들어놓은 메서드들 활용 
			// mem_num, table_num 에 해당하는 preorder를 String타입으로 가져오기 
			BasketProService bps = new BasketProService();
			String PreOrderInfo = bps.getPreOrder(mem_num,table_num);

			// String 타입으로 된 preorder를 preorderarray으로 담음 
			StringToArrayListService stal = new StringToArrayListService();
			ArrayList<ProductInfoBean> preorderarray = stal.getArray(PreOrderInfo);
		
			// item_num이 중복되는 항목들의 수량을 합쳐주기 위한 메서드 호출 
			OrderService os3 = new OrderService();
			String OrderInfo = os3.PreOrderToOrder(preorderarray);
			
			// reviewinfo 테이블에 담기 위한 서비스 호출
			OrderService os4 = new OrderService();
			int insertResult2 = os4.insertOrder(basket,OrderInfo);
			
			
			if (insertResult>0) {
						
				System.out.println("order 성공!");
						
				// preorder 항목이 order테이블에 insert 성공했을시 결제된적이 있는지 구분해주기 위해 
				// 기존 preorder의 order_tossed 값을 1로 바꿔주기 
				OrderService os5 = new OrderService();
				int updateResult = os5.updatePreOrder(mem_num,table_num);
						
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
						
		} else {
			
			//자바스크립트로 주문목록 비어있음 출력 
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('주문목록이 비어있습니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		
		}
	
		return forward;
	}
}

