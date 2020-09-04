package product.svc;
import static db.JdbcUtil.*;

import java.sql.Connection;

import product.dao.ProductDAO;
public class ProductDeleteProService {
	
	public boolean removeArticle(int item_num) {
		boolean isDeleteSuccess = false;
		int deleteCount = 0;
		
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		deleteCount = productDAO.deleteProduct(item_num);
		
		if (deleteCount>0) {
			commit(con);
			isDeleteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isDeleteSuccess;
	}

	public static boolean removeProduct(int item_num) {
		boolean isDeleteSuccess = false;
		
		int deleteCount = 0;
		
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		deleteCount = productDAO.deleteProduct(item_num);
		
		if (deleteCount>0) {
			commit(con);
			isDeleteSuccess = true;
		}else {
			rollback(con);
		}
		close(con);
		return isDeleteSuccess;
	}

	

}
