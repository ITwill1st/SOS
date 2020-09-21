package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.action.ProductModifyProAction;
import product.action.ProductAddAction;
import product.action.ProductDeleteProAction;
import product.action.ProductDetailAction;
import product.action.ProductListAction;
import product.action.ProductListCategoryAction;
import product.action.ProductModifyFormAction;
import vo.ActionForward;


/**
 * Servlet implementation class ProductAddFrontController
 */
@WebServlet("*.po")
public class ProductFrontController extends HttpServlet {
	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("ProductFrontController");
    	
    	request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		System.out.println("command: " + command);

		ActionForward forward = null;
		Action action = null;

    	
		if (command.equals("/ProductAddForm.po")) {
			forward = new ActionForward();
			forward.setPath("/product/product_add.jsp");
		}
		else if (command.equals("/ProductAddPro.po")) {
			action = new ProductAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProductList.po")) {
			// 글목록 요청 비즈니스 로직을 위한 Action 클래스 인스턴스 생성
			// => BoardListAction 클래스 인스턴스 생성 및 공통 메서드 execute() 호출
			// => 로직 수행 후 ActionForward 객체를 리턴받아 포워딩 작업 수행
			action = new ProductListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/ProductDetail.po")) {
			action = new ProductDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/ProductModifyForm.po")) {
			action = new ProductModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (command.equals("/ProductModifyPro.po")) {
			action = new ProductModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/ProductDeleteForm.po")) {
			forward = new ActionForward();
			forward.setPath("/product/product_delete.jsp");
		}
		
		else if (command.equals("/ProductDeletePro.po")) {
			action = new ProductDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}
		else if(command.equals("/ProductListCategory.po")) {
			// 글목록 요청 비즈니스 로직을 위한 Action 클래스 인스턴스 생성
			// => BoardListAction 클래스 인스턴스 생성 및 공통 메서드 execute() 호출
			// => 로직 수행 후 ActionForward 객체를 리턴받아 포워딩 작업 수행
			action = new ProductListCategoryAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if (forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
		
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
