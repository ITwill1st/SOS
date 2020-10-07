<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="product/style/list.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->

</head>
<body>
     <div class="top">           

                    <div class="logo_img">                  
                    <img alt="" src="inc/SOS logo_v2.png" height="70px">
                    </div>
    
                    <div class="logo">                  
                        <p class="logo_title">Silent Order</p>
                        <p class="logo_subtitle">restaurant</p>
                    </div>
                    
                    <div class="logo_pos">
                        <p class="pos_title">PRODUCT MODIFY</p>
                    </div>

    </div>



<!-- ----------상품 등록---------- -->

	<div class="content">  	
	   <div class = "view-left">
	   
		<form action="ProductAddPro.po" method="post"
			enctype="multipart/form-data" name="boardform">
			<table class = "addProduct">
			
				<tr>
					<td class="td-th">사진등록 </td>
					<td><input name="item_img" type="file"
						id="item_img" required="required"/></td>
				</tr>

				<tr>
					<td class="td-th">상품명</td>
					<td ><input type="text" name="item_name"
						id="item_name" required="required" /></td>
				</tr>
				<tr>
					<td class="td-th">가격</td>
					<td><input type="number" name="item_price"
						id="item_price" required="required" /></td>
				</tr>
				<tr>
					<td class="td-th">원산지</td>
					<td><input type="text" name="item_origin"
						id="item_origin" required="required" /></td>
				</tr>
				<tr>
					<td class="td-th">칼로리</td>
					<td><input type="number" name="item_calorie"
						id="item_calorie" required="required" /></td>
				</tr>
				<tr>
					<td class="td-th">상품정보</td>
					<td><textarea name="item_info" required="required"></textarea></td>
				</tr>
				<tr>
					<td class="td-th">카테고리</td>
					<td><input type="text" name="item_category"
						id="item_category" required="required" /></td>
				</tr>
				<tr>
					<td class="td-th">알레르기정보</td>
					<td><input type="text" name="item_allergie"
						id="item_allergie" required="required" /></td>
				</tr>
				
				
				<tr><td colspan="2" style="border-bottom: none; text-align: right;">
				<input type="submit" value="등록">
                <input type="reset" value="다시쓰기" /></td>
                </tr>
			
			</table>
		
		  </form>
     </div>
	


<!----------사이드메뉴------------>
        <section>
        
                    <div class="right-bar">
                        <div class="right-bar-List">
                            <button>주문 내역</button>
                        </div>
                        <div class="right-bar-List">
                            <button onclick="location.href='MyRsvList.rsv">예약 내역</button>
                        </div>
                        <div class="right-bar-List">
                            <button onclick="location.href='ProductList.po'">상품 관리</button>
                        </div>
                        <div class="right-bar-List">
                            <button>매출 관리</button>
                        </div>
                        <div class="right-bar-List">
                            <button onclick="location.href='TablesEditForm.tb'">테이블 관리</button>
                        </div>
                        <div class="right-bar-List">
                            <button>설정</button>
                        </div>
                    </div>
        
        </section>      
  </div>     
</body>
</html>