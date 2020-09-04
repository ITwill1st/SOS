package product.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;

import product.dao.ProductDAO;
import vo.ProductBean;

public class ProductDetailService {

	public ProductBean getProduct(int item_num) {
		System.out.println("ProductDetailService - getArticle()");
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		ProductBean product= productDAO.selectProduct(item_num);
		
		close(con);
		
		return product;
	}

	
	

}
