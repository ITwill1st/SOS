package order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import member.action.MemberJoinProAction;
import member.action.MemberLoginProAction;
import order.action.AJAXMinusProAction;
import order.action.AJAXPlusProAction;
import order.action.BasketListAction;
import order.action.BasketToPreOrder;
import order.action.DeleteBasketProAction;
import order.action.BasketProAction;
import order.action.MenuDetailAction;
import order.action.NonMemberOrderAction;
import order.action.MainPageProAction;
import order.action.MemberOrderJoinProAction;
import order.action.MemberOrderLoginProAction;
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
			action = new MainPageProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}  else if(command.equals("/detail.or")) {
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
				
		}else if(command.equals("/PreOrder.or")) {
			// 장바구니 항목 -> Preorder에 insert
			action = new BasketToPreOrder();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}  	
		else if(command.equals("/Order.or")) {
			// 결제하기!
			action = new OrderProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/AJAX_Plus.or")) {
			// 장바구니 수량 +1 
			action = new AJAXPlusProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/AJAX_Minus.or")) {
			// 장바구니 수량 -1 
			action = new AJAXMinusProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/DeleteBasket.or")) {
			// 장바구니 항목 삭제 
			action = new DeleteBasketProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/StartOrder.or")) {
			// QR코드 찍었을때 진입 화면
			int table_num=1;
			if(request.getParameter("table_num")!=null) {
				// qr코드 찍히면 qr코드의 table_num 넘어올 수 있도록 
				table_num = Integer.parseInt(request.getParameter("table_num"));
			} 
			
			HttpSession session= request.getSession(); 
			session.setAttribute("table_num", table_num);
			
			forward = new ActionForward();
			forward.setPath("/SOS/order/test.jsp");
			forward.setRedirect(true);
			
		} else if(command.equals("/NonMemberOrder.or")) {
			// 비회원 로그인 선택
			action = new NonMemberOrderAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/MemberOrder.or")) {
			// 회원 로그인 선택 
			forward = new ActionForward();
			forward.setPath("/SOS/order/order_login.jsp");
			forward.setRedirect(true);
			
		} else if (command.equals("/MemberOrderLoginPro.or")) {
			// 회원 로그인 
			action = new MemberOrderLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command.equals("/MemberOrderJoinForm.or")) {
			// 회원 가입 (회원정보작성페이지)
    		forward = new ActionForward();
    		forward.setPath("/SOS/order/order_join.jsp");
    		forward.setRedirect(true);
    	} else if(command.equals("/MemberOrderJoinPro.or")){
    		// 회원 가입 
			action = new MemberOrderJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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