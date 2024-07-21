<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
			<div class="col-sm-4 mx-auto mt-2">
				<div class="card shadow">
					<div class="card-header text-center bg-dark text-white">
						<h5>Customer Registration Screen</h5>
					</div>
					<div class="card-body">
						<form method="post">
						<div class="mb-2">
						<label>Name</label>
						<input type="text" name="username" required class="form-control">
						</div>						
						<div class="mb-2">
						<label>Email</label>
						<input type="email" name="userid" required class="form-control">
						</div>
						<div class="mb-2">
						<label>Password</label>
						<input type="password" name="pwd" required class="form-control">
						</div>
						<input type="submit" value="Register" class="btn btn-primary float-end px-4">
					</form>
					<c:if test="${error ne null }">
					<div class="alert text-danger font-weight-bold">${error }</div>
					</c:if>
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>