package product.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import product.dao.ProductDAO;
import vo.ProductBean;
public class ProductListService {

	public int getListCount() {
		int listCount = 1;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		listCount = productDAO.selectListCount();
		System.out.println("전체 게시물 수" + listCount);
		close(con);
		return listCount;
				
	}
	public ArrayList<ProductBean> getProductList(int page, int limit){
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		ProductBean pb = new ProductBean();
//		pb.setItem_name("아무거나");
		productList.add(pb);
		
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		productList = productDAO.selectProductList(page,limit);
		
		close(con);
		
		return productList;
	}

}
