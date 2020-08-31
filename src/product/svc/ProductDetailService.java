package product.svc;


import java.sql.Connection;

import static db.JdbcUtil.*;
import product.dao.ProductDAO;
import vo.ProductDTO;


public class ProductDetailService {

	public ProductDTO getProduct(int item_num) {
		System.out.println("ProductDetailService - getProduct");
		
		ProductDAO pda = ProductDAO.getInstance();
		
		Connection con = getConnection();
		
		pda.setConnection(con);
		
		ProductDTO pdt = pda.getProduct(item_num);
		
		close(con);
		
		return pdt;
	}

}
