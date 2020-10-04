package product.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import order.dao.*;
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
	// product 전체 메뉴 가져오기///
	public ArrayList<ProductBean> getProductList(){
		
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		ArrayList<ProductBean> productList = productDAO.selectProductList();
		
		close(con);
		
		return productList;
	}
//------------------------------------------------------------

	//카테고리 가져오기 ///
	public ArrayList<ProductBean> getCategoryList() {
		Connection con = getConnection();
		ProductDAO dao = ProductDAO.getInstance();
		dao.setConnection(con);
		ArrayList<ProductBean> categoryList = dao.selectCategory();
		
		close(con);
		return categoryList;
	}
	
	// 카테고리에 해당하는 아템 가져오기
		public JSONArray getProductList(String category) {
			
			Connection con = getConnection();
			 ProductDAO dao = ProductDAO.getInstance();
			dao.setConnection(con);
			
			// 카테고리에 해당하는 아이템 찾아오기
			JSONArray list = dao.selectListTest(category);
			
			close(con);
			
			return list;
		}

}
