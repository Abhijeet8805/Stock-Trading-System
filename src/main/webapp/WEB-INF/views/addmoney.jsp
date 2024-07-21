<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
			<div class="col-sm-4 mx-auto mt-2">
				<div class="card shadow">
					<div class="card-header text-center bg-dark text-white">
						<h5>Add Money</h5>
					</div>
					<div class="card-body">
						<form method="post">
						<div class="mb-2">
						<label>Current Balance</label>
						<input type="number" name="balance" readonly value="${uinfo.balance }" class="form-control">
						</div>
						<div class="mb-2">
						<label>Amount to Add</label>
						<input type="number" name="amount" required class="form-control">
						</div>
						<input type="submit" value="Add Money" class="btn btn-primary float-end px-4">
					</form>					
					<c:if test="${msg ne null }">
						<div class="alert text-success font-weight-bold">${msg }</div>
					</c:if>
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>