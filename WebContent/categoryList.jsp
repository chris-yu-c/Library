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
    </head>

    <body>
       <header>
            <div class="container">
                    <nav>
                            <a href="${pageContext.request.contextPath }/bookList.jsp" >图书信息管理</a>
                    </nav>
                    <nav>
                            <a href="${pageContext.request.contextPath }categoryList.jsp" >分类管理</a>
                    </nav>
                   
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>图书管理系统</h1>
                    <p>图书分类管理</p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>分类编号</th>
                        <th>分类名称</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    	<c:forEach var="c" items="${applicationScope.categoryList }" varStatus="idx">
                            <tr>
                                <td>${idx.index + 1}</td>
                                <td>${applicationScope.categoryMap[c].id}</td>
                                <td>${applicationScope.categoryMap[c].catName}</td>
                                <td><a href="${pageContext.request.contextPath }/DeleteCategory?categoryId=${applicationScope.categoryMap[c].id}">删除</a></td>

                            </tr>
						</c:forEach>
                    </tbody>
                </table>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <div id="fatie">
                    <a href="${pageContext.request.contextPath }/addCategory.jsp"><button>新建</button></a>
                </div>
            </div>
        </section>
        <footer>
            copy@ysk
        </footer>
    </body>
</html>