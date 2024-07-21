<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Stock Market</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script src="/js/bootstrap.bundle.min.js"></script>
	<link rel="icon" href="/images/logo.png" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Stock Trading</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/">Home</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/stocks">Stocks</a>
      </li>
      <c:if test="${sessionScope.userid eq null }">
      <li class="nav-item">
        <a class="nav-link" href="/login">Login</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/register">Register</a>
      </li>
      </c:if>
      <c:if test="${sessionScope.userid ne null }">
      
      <c:if test="${sessionScope.role eq 'Admin' }">
      <li class="nav-item">
        <a class="nav-link" href="/addstock">Add Stock</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/users">Users</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/settings">Market Settings</a>
      </li>
      </c:if>
      <c:if test="${sessionScope.role eq 'Customer' }">
      <li class="nav-item">
        <a class="nav-link" href="/mystocks">My Stocks</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/addmoney">Add money</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/saleorders">Sale Orders</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/history">Transaction History</a>
      </li>
      </c:if>
      <li class="nav-item">
        <a class="nav-link" href="/">Hi !${sessionScope.uname }</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/logout">Logout</a>
      </li>
      </c:if>
    </ul>
  </div>
</nav>

