package tb.svc;

import java.util.Iterator;

public class MakeJsonService {

	//Iterator안에 저장되어 있던 category를 json형식의 문자열로 변형시키는 메서드
	public String getCategoryJson(Iterator<String> iteratorCategory) {

		String c = "";

		while (iteratorCategory.hasNext()) {

			c += "'"+iteratorCategory.next() + "',";
		}

		c = c.trim();
		
		c = c.substring(0, c.length() - 1);

		c = "[" + c + "]";
		
		return c;
	}

}
