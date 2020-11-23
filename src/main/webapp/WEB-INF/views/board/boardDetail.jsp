<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 상세페이지</title>
<%
	String boardSeq = request.getParameter("boardSeq");
%>
<c:set var="boardSeq" value="<%= boardSeq %>"/> <!-- 게시글 번호 -->

<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>

<script type="text/javascript" src="/js/common/jquery.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		getBoardDetail();
	});
	
	//게시글 목록 페이지 이동
	function goBoardList(){
		location.href = "/board/boardList";
	}

	//게시글 상세 조회
	function getBoardDetail(boardSeq){
		var boardSeq = $("#board_seq").val();
		if(boardSeq!=""){
			$.ajax({	
				
			    url		: "/board/getBoardDetail",
			    data    : $("#boardForm").serialize(),
		        dataType: "JSON",
		        cache   : false,
				async   : true,
				type	: "POST",	
		        success : function(obj) {
		        	getBoardDetailCallback(obj);				
		        },	       
		        error 	: function(xhr, status, error) {}
		        
		     });
		}else{
			alert("오류가 발생했습니다. \n 관리자에게 문의하세요.");
		}
	}

	//게시글 상세 조회 콜백함수
	function getBoardDetailCallback(obj){
		var str = "";
		
		if(obj != null){								
							
			var boardSeq		= obj.board_seq; 
			var boardReRef 		= obj.board_re_ref; 
			var boardReLev 		= obj.board_re_lev; 
			var boardReSeq 		= obj.board_re_seq; 
			var boardWriter 	= obj.board_writer; 
			var boardSubject 	= obj.board_subject; 
			var boardContent 	= obj.board_content; 
			var boardHits 		= obj.board_hits;
			var delYn 			= obj.del_yn; 
			var insUserId 		= obj.ins_user_id;
			var insDate 		= obj.ins_date; 
			var updUserId 		= obj.upd_user_id;
			var updDate 		= obj.upd_date;
						
			str += "<tr>";
			str += "<th>제목</th>";
			str += "<td>"+ boardSubject +"</td>";
			str += "<th>조회수</th>";
			str += "<td>"+ boardHits +"</td>";
			str += "</tr>";		
			str += "<tr>";
			str += "<th>작성자</th>";
			str += "<td>"+ boardWriter +"</td>";
			str += "<th>작성일시</th>";
			str += "<td>"+ insDate +"</td>";
			str += "</tr>";		
			str += "<tr>";
			str += "<th>내용</th>";
			str += "<td colspan='3'>"+ boardSubject +"</td>";
			str += "</tr>";
			
		} else{
			alert("등록된 글이 존재하지 않습니다.");
			return;
		}
		$("#tbody").html(str);
	}
	
	//게시글 수정 페이지 이동
	function updateBoard(){
		var boardSeq = $("#board_seq").val();
		location.href = "/board/boardUpdate?boardSeq="+boardSeq;
	}
	
	//게시글 삭제
	function goBoardDelete(){
		var boardSeq = $("#board_seq").val();
		
		var yn = confirm("게시글을 삭제하시겠습니까?");		
		if(yn){
			
			$.ajax({	
				
			    url		: "/board/deleteBoard",
			    data    : $("#boardForm").serialize(),
		        dataType: "JSON",
		        cache   : false,
				async   : true,
				type	: "POST",	
		        success : function(obj) {
		        	deleteBoardCallback(obj);				
		        },	       
		        error 	: function(xhr, status, error) {}
		        
		     });
		}		
	}
	
	//게시글 삭제 콜백 함수
	function deleteBoardCallback(obj){
	
		if(obj != null){		
			
			var result = obj.result;
			
			if(result == "SUCCESS"){				
				alert("게시글 삭제를 성공하였습니다.");				
				goBoardList();				
			} else {				
				alert("게시글 삭제를 실패하였습니다.");	
				return;
			}
		}
	}
	
</script>

</head>
<body>
<div id="wrap">
	<div id="container">
		<div class="inner">	
			<h2>게시글 상세</h2>
			<form id="boardForm" name="boardForm" action="/board/updateBoard" method="post" onsubmit="return false;">	
				<table width="100%" class="table02">
				<caption>게시글 상세 조회</caption>
				    <colgroup>
				         <col width="20%">
				        <col width="*">
				    </colgroup>
				    <tbody id="tbody">
				       <tr>
							<th>제목<span class="t_red">*</span></th>
							<td><input id="board_subject" name="board_subject" value="" class="tbox01"/></td>
						</tr>
						 <tr>
							<th>작성자</th>
							<td id="board_writer"></td>
						</tr>
						<tr>
							<th>내용<span class="t_red">*</span></th>
							<td colspan="3"><textarea id="board_content" name="board_content" cols="50" rows="5" class="textarea01"></textarea></td>
						</tr>
				    </tbody>
				</table>	
				<input type="hidden" id="board_seq"		name="board_seq"	value="${boardSeq}"/> <!-- 게시글 번호 -->
				<input type="hidden" id="search_type"	name="search_type"	value="U"/> <!-- 조회 타입 - 상세(S)/수정(U) -->
			</form>
			<div class="btn_right mt15">
				<button type="button" class="btn black" onclick="javascript:goBoardList();">목록으로</button>
				<button type="button" class="btn black" onclick="javascript:updateBoard();">수정하기</button>
				<button type="button" class="btn black" onclick="javascript:goBoardDelete();">삭제하기</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>