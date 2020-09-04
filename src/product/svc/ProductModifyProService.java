package product.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import product.dao.ProductDAO;
import vo.ProductBean;
public class ProductModifyProService {

	public static boolean modifyProduct(ProductBean productBean) {
		boolean isModifySuccess = false;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int updateCount = productDAO.updateProduct(productBean);
		
		if (updateCount>0) {
			commit(con);
			isModifySuccess = true;
		}else {
			rollback(con);
		}
		close(con);
	
		return isModifySuccess;
	}

}
