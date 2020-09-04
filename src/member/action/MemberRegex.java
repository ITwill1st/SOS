package member.action;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class MemberRegex implements  Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//패스워드, email, 핸드폰번호 정규표현식
		System.out.println("MemberRegex");
		ActionForward forward=null;
		
		//정규표현식을 판단하기 위한 Matcher 클래스 변수 선언
		Matcher matcher=null;
		
		String passwd=request.getParameter("passwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		
		//phone 정규표현식을 위한 기준.
		String phoneRegex="^(010|011)[-\\s]?\\d{3,4}[-\\s]?\\d{4}$";
		/*
		 * ^(010|011) => 010 또는 011 로 시작
		 * [-\s]? => - 기호 또는 공백이 있을 수도 있고 없을 수도 있음
		 * \d{3,4} => 숫자 3자리~4자리
		 * [-\s]? => - 기호 또는 공백이 있을 수도 있고 없을 수도 있음
		 * \d{4}$ => 숫자 4자리로 끝
		 */
		
		//phone 정규표현식을 만족하는지 검사하기위한 메서드에 파라미터 입력
		matcher=Pattern.compile(phoneRegex).matcher(phone);
		if(matcher.matches()) {//정규표현식에 일치했을 경우
			//자바스크립트로 phone 표현식일치결과 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('올바른 형식입니다..')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}else {//정규표현식에 일치하지 못했을 경우
			//자바스크립트로 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('잘못된 형식입니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}
		
		//email 정규표현식 구글링 참조
		String emailRegex="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
				+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
				+ "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\"
				+ "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
				+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		matcher=Pattern.compile(emailRegex).matcher(email);
		if(matcher.matches()) {//email이 정규표현식이 일치 하였을 경우
			//자바스크립트로 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('올바른 형식입니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}else {//email 이 정규표현식에 맞지 않았을 경우
			//자바스크립트로 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('잘못된 형식입니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}
		
		
		//passwd 정규표현식 ,대소문자 구별,특수문자 구별,9~12문자 사이
		String passwdRegex="^(?=.*\\\\d)(?=.*[~`!@#$%\\\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{9,12}$";
		matcher=Pattern.compile(passwdRegex).matcher(passwd);
		
		//같은 문자 4개이상 사용 불가 조건
		passwdRegex="(.)\\1\\1\\1";
		Matcher matcher2=Pattern.compile(passwdRegex).matcher(passwd);
		
		if(matcher.matches()) {//passwd 이 정규표현식에 일치 했을 경우
			//자바스크립트로 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('올바른 형식입니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}else {//passwd이 정규표현식에 일치 하지 못했을 경우
			//자바스크립트로 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('잘못된 형식입니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}
		
		if(matcher2.find()) {//passwd에 똑같은 문자가 4번 반복되지 않았을 경우
			//자바스크립트로 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('올바른 형식입니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}else {//passwd에 똑같은 문자가 4번 반복되었을 경우
			//자바스크립트로 메세지 출력
			response.setContentType("text/html;charset=UTF-8");//문서타입지정
			PrintWriter out=response.getWriter();//PrintWriter 객체 가져오기
			//println()메서드로 문자열 출력
			out.println("<script>");
			out.println("alert('잘못된 형식입니다.')");//메세지 출력
			out.println("history.back()");//이전페이지 이동
			out.println("</script>");
		}
		
		return forward;
	}
	
}
