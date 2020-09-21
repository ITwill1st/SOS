package product.action;

import java.awt.*;
import java.awt.image.*;
import java.awt.image.renderable.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.media.jai.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

import action.*;
import product.svc.*;
import vo.*;

public class ProductAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductAddAction");
		ActionForward forward = null;
		int fileSize = 1024 * 1024 * 10;
		
		ServletContext context = request.getServletContext();
		String saveFolder = "product/productUpload";

		String realFolder = context.getRealPath(saveFolder); 
		System.out.println(realFolder);
		
		String fileName ="";
		
		
		
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(
					request,
					realFolder, 
					fileSize, 
					"UTF-8", 
					new DefaultFileRenamePolicy());
			 Enumeration files = multi.getFileNames();
		      String file = (String) files.nextElement();
		      fileName = multi.getFilesystemName(file);
		      
			ParameterBlock parameterBlock =new ParameterBlock();
			parameterBlock.add(realFolder+"/"+fileName);
			RenderedOp rOP = JAI.create("fileload",parameterBlock);
			
			BufferedImage bi = rOP.getAsBufferedImage();
			BufferedImage thumb = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = thumb.createGraphics();
			g.drawImage(bi, 0, 0, 300, 300, null);
			File f = new File(realFolder+"/"+fileName);
			ImageIO.write(thumb,"jpg",f);

			ProductBean pb = new ProductBean();
//		pb.setProduct_num(Integer.parseInt(multi.getParameter("product_num")));
			pb.setItem_name(multi.getParameter("item_name"));
			pb.setItem_price(Integer.parseInt(multi.getParameter("item_price")));
			pb.setItem_origin(multi.getParameter("item_origin"));
			pb.setItem_calorie(Integer.parseInt(multi.getParameter("item_calorie")));
			pb.setItem_info(multi.getParameter("item_info"));
			pb.setItem_category(multi.getParameter("item_category"));
			pb.setItem_allergies(multi.getParameter("item_allergies"));
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
			

			
			     
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
		
		   
		   
		
		
		
		return forward;
		
		
	}

}
