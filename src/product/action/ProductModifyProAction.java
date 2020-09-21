package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import product.svc.ProductModifyProService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardModifyProAction");
		ActionForward forward = null;
		boolean isModifySuccess = false;
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		String page = request.getParameter("page");
		
		ProductBean productBean = new ProductBean();
		productBean.setItem_num(Integer.parseInt(request.getParameter("item_num")));
		productBean.setItem_name(request.getParameter("item_name"));
		productBean.setItem_price(Integer.parseInt(request.getParameter("item_price")));
		productBean.setItem_origin(request.getParameter("item_origin"));
		productBean.setItem_calorie(Integer.parseInt(request.getParameter("item_calorie")));
		productBean.setItem_info(request.getParameter("item_info"));
		productBean.setItem_category(request.getParameter("item_category"));
		productBean.setItem_img(request.getParameter("item_img"));

		
		isModifySuccess = ProductModifyProService.modifyProduct(productBean);
		
		if(!isModifySuccess) {
			// 수정 작업이 실패했을 경우 자바스크립트를 사용하여
			// "글 수정 실패!" 메세지 출력 후 이전페이지로 이동
			response.setContentType("text/html;charset=UTF-8"); // 문서 타입 설정
			PrintWriter out = response.getWriter(); // PrintWriter 객체 가져오기
			// println() 메서드를 사용하여 자바스크립트를 문자열로 출력
			out.println("<script>"); // 자바스크립트 시작
			out.println("alert('글 수정 실패!')"); // 오류 메세지 출력
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>"); // 자바스크립트 끝
		} else {
			// 수정 작업이 성공했을 경우 게시물 상세 내용 요청을 위해
			// BoardDetail.bo 주소로 포워딩(Redirect 방식)
			// => 파라미터로 게시물 번호와 페이지 전달
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("ProductDetail.po?item_num=" + item_num + "&page=" + page);
		}
		
	
		
		return forward;
	}

}
