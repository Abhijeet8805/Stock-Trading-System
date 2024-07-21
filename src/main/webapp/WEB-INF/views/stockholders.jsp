<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container">
<div class="row">
	<div class="col-sm-12">
		<h4 class="p-2 text-center">List of Users</h4>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Email id</th>
					<th>User Name</th>
					<th>Role</th>
					<th>Wallet Balance</th>
					<th>Password</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="p">
					<tr>
						<td>${p.userid }</td>
						<td>${p.username }</td>
						<td>${p.role }</td>
						<td>${p.balance }</td>
						<td>${p.pwd }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</div>
</body>
</html>