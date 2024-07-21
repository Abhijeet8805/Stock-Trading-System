<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container">
<div class="row">
	<div class="col-sm-12">
		<h4 class="p-2 text-center">My Stock List</h4>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>User Name</th>
					<th>Stock Company</th>
					<th>Offer Price</th>
					<th>Quantity</th>
					<th>Last Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="p">
					<tr>
						<td>${p.id }</td>
						<td>${p.user.username }</td>
						<td>${p.stock.stock.company }</td>
						<td>${p.desiredprice }</td>
						<td>${p.qty}</td>
						<td>${p.lastdate }</td>
						<td>
						<c:choose>
							<c:when test="${sessionScope.userid eq p.user.userid }">
								<a href="/cancelorder/${p.id}" onclick="return confirm('Are you sure to cancel the order ?')" class="btn btn-sm btn-danger">Cancel</a>
							</c:when>
							<c:otherwise>
							<a href="/buynow/${p.id}" class="btn btn-sm btn-primary">Buy Now</a>
							</c:otherwise>
							</c:choose>
						</td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>
</body>
</html>