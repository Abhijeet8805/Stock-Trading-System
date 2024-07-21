<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container-fluid">
	<div class="row">
			<div class="col-sm-4 mx-auto mt-2">
				<div class="card shadow">
					<div class="card-header text-center bg-dark text-white">
						<h5>Purchase from Sale Order</h5>
					</div>
					<div class="card-body">
						<form method="post" onsubmit="return validate(this)">
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">Company Name</label>
							<div class="col-sm-8">
							<input type="text" value="${so.stock.stock.company }" readonly class="form-control">
							</div>
						</div>
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">Current Price</label>
							<div class="col-sm-8">
							<input type="text" value="${so.stock.stock.price }" readonly class="form-control">
							</div>
						</div>
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">Available Quantity</label>
							<div class="col-sm-8">
							<input type="text" value="${so.qty }" readonly class="form-control">
							</div>
						</div>
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">Last Date</label>
							<div class="col-sm-8">
							<input type="date" readonly value="${so.lastdate }" class="form-control">
							</div>
						</div>
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">Desired Price</label>
							<div class="col-sm-8">
							<input type="number" id="bprice" readonly value="${so.desiredprice }" required class="form-control">
							</div>
						</div>
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">No. of Shares</label>
							<div class="col-sm-8">
							<input type="number" id="qty" max="${so.qty }" onblur="updateAmount(this.value)" name="qty" min="1" required value="1" class="form-control">
							</div>
						</div>
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">Amount</label>
							<div class="col-sm-8">
							<input type="number"  name="amount" id="amount" value="${so.desiredprice }" readonly class="form-control">
							</div>
						</div>
						<div class="mb-3 row">
							<label class="col-sm-4 col-form-label">Wallet Balance</label>
							<div class="col-sm-8">
							<input type="number" id="balance" value="${user.balance }" readonly class="form-control">
							</div>
						</div>
						<c:choose>
						<c:when test="${expire }">
							<h6 class="text-danger float-end">Sale order expired</h6>
						</c:when>
						<c:otherwise>
						<input type="submit" value="Create Now" class="btn btn-primary float-end px-4">
						</c:otherwise>
						</c:choose>
					</form>
					</div>
				</div>
			</div>
		</div>
</div>
<script>
function updateAmount(qty){
	var bprice=parseInt(document.querySelector("#bprice").value);
	document.getElementById("amount").value=qty*bprice;
}

function validate(f){
	var amount=f.amount.value;
	var bal=parseInt(document.querySelector("#balance").value);
	if(bal<amount){
		alert("Insufficient Balance")
		return false;
	}
	return true;
}
</script>
</body>
</html>