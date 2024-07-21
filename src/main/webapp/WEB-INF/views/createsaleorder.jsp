<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
			<div class="col-sm-4 mx-auto mt-2">
				<div class="card shadow">
					<div class="card-header text-center bg-dark text-white">
						<h5>Create Sale Order</h5>
					</div>
					<div class="card-body">
						<form method="post">
						<div class="mb-3">
							<label>Company Name</label>
							<input type="text" value="${us.stock.company }" readonly class="form-control">
						</div>
						<div class="mb-3">
							<label>Current Price</label>
							<input type="text" value="${us.stock.price }" readonly class="form-control">
						</div>
						<div class="mb-3">
							<label>Available Quantity</label>
							<input type="text" value="${us.qty }" readonly class="form-control">
						</div>
						<div class="mb-3">
							<label>Last Date</label>
							<input type="date" name="lastdate" required class="form-control">
						</div>
						<div class="mb-3">
							<label>Quantity</label>
							<input type="number" min="1" name="qty" required class="form-control">
						</div>
						<div class="mb-3">
							<label>Desired Price</label>
							<input type="number" name="desiredprice" required class="form-control">
						</div>
						<input type="submit" value="Create Now" class="btn btn-primary float-end px-4">
					</form>
					</div>
				</div>
			</div>
		</div>
</div>
</body>
</html>