<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<jsp:include page="cheader.jsp"></jsp:include>
<div class="container-fluid">
<div class="row">
	<div class="col-sm-3">
		<div class="card shadow mt-2">
			<div class="card-header text-center">
			<h5>${stk.stock.company }</h5>
<%-- 			<c:if test="${sessionScope.userid ne null and sessionScope.role eq 'Admin' }">
				<a class="btn btn-primary btn-sm float-end" href="/updateprice/${stk.stock.id}">Update Price</a>
				<h5>Stock Details</h5>
			</c:if> --%>
			</div>
			<%-- <div class="card-body text-center">
				<img src="${stk.stock.logo }" style="width:300px;height:300px;">
			</div> --%>
			<div class="card-footer text-center">
				<h5>Current Price : &#8377; <fmt:formatNumber value="${info.price }"  maxFractionDigits="2"/></h5>
				<h5>Start Price : &#8377; ${info.first_price }</h5>
				<h6>Low: &#8377; ${min } High: &#8377; ${max }</h6>
				<c:if test="${sessionScope.userid ne null }">
				<h5>Your Cash Balance: &#8377; <span id="balance">${uinfo.balance }</span></h5>				
				</c:if>
			</div>
		</div>
	</div>
	<%-- <div class="col-sm-3">
		<h4 class="p-2">Price Table</h4>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Date</th>
					<th>Price</th>				
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${prices }" var="p">
					<tr>
						<td>${p.createdon.dayOfMonth }-${p.createdon.month } ${p.createdon.hour }:${p.createdon.minute }</td>
						<td>${p.price }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> --%>
	<div class="col-sm-5">
		<canvas id="myChart" style="height:400px;"></canvas>
	</div>
	
	<div class="col-sm-3">
	<c:choose>
	<c:when test="${open }">
	<c:if test="${sessionScope.userid ne null and sessionScope.role eq 'Customer' }">
	<h4 class="p-2">Buy Now</h4>
		<form method="post" onsubmit="return validate(this)">
			<div class="mb-3">
				<label>Price per Stock</label>
				<input type="number" id="bprice" name="buyprice" value="${stk.price }" readonly class="form-control">
			</div>
			<div class="mb-3">
				<label>No. of Shares</label>
				<input type="number" id="qty" max="${stk.stock.volume }" onblur="updateAmount(this.value)" name="qty" min="1" required value="1" class="form-control">
			</div>
			<div class="mb-3">
				<label>Amount</label>
				<input type="number"  name="amount" id="amount" value="${stk.price }" readonly class="form-control">
			</div>
			<button class="btn btn-primary">Buy Now</button>
		</form>
	</c:if>
	<c:if test="${sessionScope.userid eq null and sessionScope.role eq 'Customer'}">
		<h5>Login to buy Stock</h5>
	</c:if>
	</c:when>
	<c:otherwise>
		<h4 class="text-danger p-4">Market is closed</h4>
	</c:otherwise>
	</c:choose>
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
	var bal=parseInt(document.querySelector("#balance").innerHTML);
	if(bal<amount){
		alert("Insufficient Balance")
		return false;
	}
	return true;
}
$(function(){
	let values=[];
	let dates=[];
	$.ajax({
		url:'/api/data/'+[[${stk.stock.id}]],
		success: function(resp){
			console.log("Stock response ",resp)
			for(let item of resp){
				values.push(item.price);
				let date=new Date(item.createdon)
				dates.push(date.getHours()+":"+date.getMinutes());
				console.log(date)
			}
			console.log(values)
			const data = {
			  labels: dates,
			  datasets: [{
			  	fill:'origin',
			    label: 'Market Flow for Shares',
			    backgroundColor: '#f3eedd',
			    borderColor: '#c23cdd',
			    data: values,
			  }]
			};
			const config = {
			  type: 'line',
			  data: data,
			  options: {}
			};

			const myChart = new Chart(
			  document.getElementById('myChart'),
			  config
			);
		}
	})
})


</script>
</body>
</html>