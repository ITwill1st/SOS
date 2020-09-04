package product.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import product.svc.ProductAddProService;
import vo.ActionForward;
import vo.ProductBean;

public class ProductAddAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductAddAction");
		ActionForward forward = null;
		int fileSize = 1024 * 1024 * 10;
		
		ServletContext context = request.getServletContext();
		String saveFolder = "product/productUpload";

		String realFolder = context.getRealPath(saveFolder); 
		
		MultipartRequest multi = new MultipartRequest(
				request,
				realFolder, 
				fileSize, 
				"UTF-8", 
				new DefaultFileRenamePolicy());
		ProductBean pb = new ProductBean();
//		pb.setProduct_num(Integer.parseInt(multi.getParameter("product_num")));
		pb.setItem_name(multi.getParameter("item_name"));
		pb.setItem_price(Integer.parseInt(multi.getParameter("item_price")));
		pb.setItem_origin(multi.getParameter("item_origin"));
		pb.setItem_calorie(Integer.parseInt(multi.getParameter("item_calorie")));
		pb.setItem_info(multi.getParameter("item_info"));
		pb.setItem_category(multi.getParameter("item_category"));
		pb.setItem_allergie(multi.getParameter("item_allergie"));
		pb.setItem_img(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
//		pb.setProduct_like(Integer.parseInt(multi.getParameter("product_like")));
		
		ProductAddProService productAddProService = new ProductAddProService();
		boolean isAddSuccess = productAddProService.productAdd(pb);
		
		if(!isAddSuccess) {
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('글 등록 실패!')"); 
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("ProductList.po");
		}
		
		return forward;
		
		
	}

}
