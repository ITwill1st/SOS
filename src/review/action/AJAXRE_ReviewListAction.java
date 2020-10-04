package review.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import action.Action;
import review.svc.Re_ReviewListService;
import vo.ActionForward;
import vo.ReviewDTO;

public class AJAXRE_ReviewListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		Re_ReviewListService re_ReviewListService = new Re_ReviewListService();
		ArrayList<ReviewDTO> reviewList = re_ReviewListService.getRe_reviewList();
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(reviewList);
		request.setAttribute("reviewList", jsonArray);
			
		return forward;
	}

}
