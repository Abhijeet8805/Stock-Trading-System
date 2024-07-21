<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
			<div class="col-sm-4 mx-auto mt-2">
				<div class="card shadow">
					<div class="card-header text-center bg-dark text-white">
						<h5>Add New Stock</h5>
					</div>
					<div class="card-body">
						<form method="post" enctype="multipart/form-data">
						<div class="mb-3 row">
						    <label for="inputPassword" class="col-sm-4 col-form-label">Name</label>
						    <div class="col-sm-8">
						      <input type="text" name="company" class="form-control" id="inputPassword">
						    </div>
					  	</div>
					  	<div class="mb-3 row">
						    <label for="inputPassword" class="col-sm-4 col-form-label">Volume</label>
						    <div class="col-sm-8">
						      <input type="number" name="volume" class="form-control" id="inputPassword">
						    </div>
					  	</div>
					  	<div class="mb-3 row">
						    <label for="inputPassword" class="col-sm-4 col-form-label">Initial Price</label>
						    <div class="col-sm-8">
						      <input type="number" name="price" class="form-control" id="inputPassword">
						    </div>
					  	</div>
					  	
					  	<!-- <div class="mb-3 row">
						    <label for="inputPassword" class="col-sm-4 col-form-label">Logo</label>
						    <div class="col-sm-8">
						      <input type="file" accept=".jpg,.png" name="photo" class="form-control">
						    </div>
					  	</div> -->
					  	
						<input type="submit" value="Register" class="btn btn-primary float-right px-4">
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