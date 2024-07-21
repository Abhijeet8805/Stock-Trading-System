<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container">
<div class="row">
	<div class="col-sm-12">
		<h4 class="p-2 text-center">My Stock List</h4>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>Stock Company</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="p">
				<c:if test="${p.qty >0 }">
					<tr>
						<td>${p.id }</td>
						<td>${p.stock.company }</td>
						<td><fmt:formatNumber value="${p.stock.price}" maxFractionDigits="2" /></td>
						<td>${p.qty}</td>
						<td>
						<a href="/createorder/${p.id}" class="btn btn-sm btn-primary">Create Sale Order</a>
						<a href="/marketsale/${p.id}" class="btn btn-sm btn-primary">Market Sale</a>
						</td>						
					</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>
</body>
</html>