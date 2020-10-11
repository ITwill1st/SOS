package review.action;

import java.io.PrintWriter;
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

public class AJAXRE_ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		Re_ReviewListService re_ReviewListService = new Re_ReviewListService();
		JSONArray re_reviewList = re_ReviewListService.getJSONRe_reviewList();

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(re_reviewList);
		return null;
	}

}
