<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>修改图书信息</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/add.css">
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/dept/list.do">
                        图书信息管理
                    </a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="jumbotron">
                <h1>Hello, ${sessionScope.login.userName }!</h1>
                <p>请小心的修改图书信息。。。</p>
            </div>
            <div class="page-header">
                <h3><small>修改</small></h3>
            </div>
            <form class="form-horizontal" enctype="multipart/form-data" action="${pageContext.request.contextPath }/UpdateBook?flag=update&bookId=${requestScope.book.id }" method="post">

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书编号 ：</label>
                    <div class="col-sm-8">
                        <input name="bookId" class="form-control" id="bookId" readonly="readonly" value="${requestScope.book.id }">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">图书名称 ：</label>
                    <div class="col-sm-8">
                        <input name="bookName" class="form-control" id="bookName" value="${requestScope.book.bName }">
                    </div>
                </div>
                <div class="form-group">
                    <label for="categoryId" class="col-sm-2 control-label">分类 ：</label>
                    <select id="categoryId" name="categoryId" class="col-sm-2 form-control" style="width: auto;margin-left: 15px">
                        <c:forEach var="c" items="${applicationScope.categoryList }">
	                        <c:choose>
	                        	<c:when test="${c == requestScope.book.category}">
	                    			<option value="${c }" selected="">${applicationScope.categoryMap[c].catName }</option>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<option value="${c }">${applicationScope.categoryMap[c].catName }</option>
	                    		</c:otherwise>
	                    	</c:choose>
                    	</c:forEach>
                       <!-- 下拉列表的内容要从分类中进行读取，value值是分类id -->
                    </select>
                </div>

                 <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">价格 ：</label>
                    <div class="col-sm-8">
                        <input name="bookPrice" class="form-control" id="bookPrice" value="${requestScope.book.price }">
                    </div>
                  </div>
                   
                  <div class="form-group" >
                    <label for="name" class="col-sm-2 control-label">图书封面 ：</label>
                    <input type="file" id="bookPic" name="bookPic" style="padding-left: 15px">
                  </div>

                  <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">备注 ：</label>
                    <div class="col-sm-8">
                        <input name="remarks" class="form-control" id="remarks" value="${requestScope.book.ref }">
                    </div>
                  </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-primary">修改</button>&nbsp;&nbsp;&nbsp;
                    </div>
                </div>
            </form>
            <p style="color: red">${requestScope.msg }</p>
        </div>
        <footer class="text-center" >
            copy@ysk
        </footer>
    </body>
</html>
