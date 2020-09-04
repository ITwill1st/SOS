package product.svc;

import product.dao.ProductDAO;
import vo.ProductBean;
import static db.JdbcUtil.*;

import java.sql.Connection;
public class ProductAddProService {

	public boolean productAdd(ProductBean pb) {
		System.out.println("ProductAddProService!");
		boolean isAddSuccess = false;
		
		Connection con = getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int insertCount = productDAO.insertProduct(pb);
		
		if (insertCount > 0) {
			commit(con);
			isAddSuccess = true;
		}else {
			rollback(con);
		}
		
		return isAddSuccess;
	}

}
