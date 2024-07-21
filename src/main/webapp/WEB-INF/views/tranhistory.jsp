<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container">
<div class="row">
	<div class="col-sm-12">
		<h4 class="p-2 text-center">Transaction history</h4>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Id</th>
					<th>Date</th>
					<th>Description</th>
					<th>Debit</th>
					<th>Credit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="p">
					<tr>
						<td>${p.id }</td>
						<td>${p.trandate}</td>
						<td>${p.description }</td>
						<td>${p.debit }</td>
						<td>${p.credit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>
</body>
</html>