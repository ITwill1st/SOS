package order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import order.action.BasketDeleteAction;
import order.action.BasketListAction;
import order.action.BasketPreOrder;
import order.action.BasketProAction;
import order.action.BasketQtyMinusAction;
import order.action.BasketQtyPlusAction;
import order.action.MenuDetailAction;
import order.action.MenuListAction;
import order.action.OrderProAction;
import vo.ActionForward;

@WebServlet("*.or")
public class OrderFrontController extends HttpServlet{

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// 인코딩 
		request.setCharacterEncoding("UTF-8");
		
		// Servlet 주소 가져오기 
		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/OrderMain.or")) {
			// 전체 메뉴 및 장바구니 수량 조회하여 main.jsp에 출력
			// id에 장바구니 없을 시 장바구니 생성 
			action = new MenuListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else if(command.equals("/detail.or")) {
			// 메뉴 이름 클릭 시 해당 메뉴 상세 조회 페이지
			action = new MenuDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else if(command.equals("/BasketPro.or")) {
			// 선택한 메뉴 장바구니에 담기 
			action = new BasketProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/BasketList.or")) {
			// 장바구니 리스트 확인  
			action = new BasketListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/MinusBasketQty.or")) {
			// 장바구니 수량 (-1)
			action = new BasketQtyMinusAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/PlusBasketQty.or")) {
			// 장바구니 수량 (+1)
			action = new BasketQtyPlusAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/DeleteBasket.or")) {
			// 장바구니 항목 삭제
			action = new BasketDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/Order.or")) {
			// 결제하기!
			action = new OrderProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/PreOrder.or")) {
			// 장바구니 항목 -> Preorder에 insert
			action = new BasketPreOrder();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	
		
		
		
		
		
		
		
		
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		//Redirect 방식과 Dispatch방식에 대한 포워딩을 처리하기 위한 영역
		//1.ActionForward객체가 null이 아닐때만 포워딩 작업 수행
		if(forward != null) {
			
			if(forward.isRedirect()) {
				//response객체의 sendRedirect() 메서드를 호출하여 ActionForward 객체의 저장되어 있는 URL 정보 전달
				response.sendRedirect(forward.getPath());
			}else {
				//RequestDispatcher 객체를 리턴받기 위해
				//request 객체의 getRequestDispatcher()메서드를 호출 파라미터로 ActionForward 객체에 저장되어 있는 URL 정보 전달
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				
				dispatcher.forward(request, response);
			}
			
		}
		
		
		
		
		
		
		
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);
	}
	
}