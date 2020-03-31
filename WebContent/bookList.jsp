<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>图书后台管理</title>
        <link rel="stylesheet" href="css/index.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
      	<script type="text/javascript">
		   	function searchBook(){
				var name = $("#searchContent").val();
				$.ajax({
					"url": "/Library/searchBook",
					"type": "post",
					"data": "cat="+name,
					"dataType": "json",
					"success": function(json){
						console.log(json)
						var html = "";
						for(var i = 0; i < json.length; i++){
							var book = json[i];
							html += "<tr><td>"+(i+1)+"</td>";
							html += "<td>"+book.id+"</td>";
							html += "<td>"+book.bName+"</td>";
							html += "<td>"+name+"</td>";
							html += "<td>￥"+book.price+"</td>";
							html += "<td><img src=\""+book.cover+"\"></td>";
							html += "<td><a href=\"/Library/UpdateBook?bookId="+book.id+"\">修改</a>\n";
							html += "<a href=\"/Library/DeleteBook?bookId="+book.id+"\">删除</a></td></tr>";
						}
						$("#book_list").html(html);
					}
				})
		 	}
		</script>
    </head>
    <body>
       <header>
            <div class="container">
                    <nav>
                            <a href="${pageContext.request.contextPath }/bookList.jsp" >图书信息管理</a>
                    </nav>
                    <nav>
                            <a href="${pageContext.request.contextPath }/categoryList.jsp" >分类管理</a>
                    </nav>
                   
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>图书管理系统</h1>
                    <p>图书信息管理</p>
                </div>
            </div>
        </section>
        <section class="main">


            <div class="container">
                <form class="form-horizontal" action="" method="">
                <div class="form-group"  style="float: right;">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button id="search" type="button" class="btn btn-primary" onclick="searchBook()">查询</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
                <div class="form-group" style="float: right;width: 300px;">
                    <div class="col-sm-8">
                        <input name="searchContent" class="form-control" id="searchContent"
                        placeholder="输入要查询的分类" style="width: 250px">
                    </div>
                </div>

                
            </form>
            </div>
            <div class="container">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>图书编号</th>
                        <th>图书名称</th>
                        <th>分类</th>
                        <th>价格</th>
                        <th>图书封面</th>
                        <th>操作</th>

                    </tr>
                    </thead>
                    <tbody id="book_list">
                    	<c:forEach var="b" items="${applicationScope.bookList }" varStatus="idx">
                            <tr>
                                <td>${idx.index + 1 }</td>
                                <td>${b.id }</td>
                                <td>${b.bName }</td>
                                <td>${applicationScope.categoryMap[b.category].catName}</td>
                                <td>￥${b.price }</td>
                                <td><img src="${b.cover }"></td>
                                <td>
                                <a href="${pageContext.request.contextPath }/UpdateBook?bookId=${b.id }">修改</a>
                                <a href="${pageContext.request.contextPath }/DeleteBook?bookId=${b.id }">删除</a>

                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <div id="fatie">
                    <a href="${pageContext.request.contextPath }/addBook.jsp"><button>新建</button></a>
                </div>
            </div>
        </section>
        <footer>
            copy@ysk
        </footer>
    </body>
    
</html>