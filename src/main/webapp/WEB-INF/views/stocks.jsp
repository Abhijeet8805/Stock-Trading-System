<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<meta http-equiv="refresh" content="10" />
<jsp:include page="cheader.jsp"></jsp:include>
<style>
img{
width:30px;
height:30px;
}
</style>
<div class="main">

	<div class="content">

		<div class="container-fluid">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Stock Id</th>
					<th>Company</th>
					<th>Current Price</th>
					<th>Initial Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${prods }" var="p">
				<tr>
					<td>${p.id }</td>
					<td>					
					<h2 class="p-2 font-weight-bold" style="font-size: 14px;margin-left:20px;">${p.company}</h2> </td>
					<td>&#8377;<fmt:formatNumber value="${p.price}" maxFractionDigits="2" /> ${p.delta gt 0 ? "<img src='/images/up.png'>" : "<img src='images/down.png'>"}</td>
					<td>&#8377;${p.first_price}</td>
					<td><a href="details/${p.id}"
										class="btn btn-success btn-block"><i class="fa fa-cart"></i>Show Details</a></td>
				</tr>									
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<jsp:include page="cfooter.jsp"></jsp:include>