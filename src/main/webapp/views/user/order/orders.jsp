<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<div class="container align-self-center vh-100 py-5">
	<div class="container py-5 bg-white">
		<h2 class="text-black text-center mb-4">Danh sách đơn hàng</h2>

		<c:if test="${empty orders}">
			<div class="alert alert-warning text-center">Bạn chưa có đơn
				hàng nào.</div>
		</c:if>

		<c:if test="${not empty orders}">
			<table
				class="table table-responsive table-bordered table-striped text-center">
				<thead>
					<tr>
						<th>Mã đơn hàng</th>
						<th>Ngày đặt</th>
						<th>Trạng thái</th>
						<th>Hình thức thanh toán</th>
						<th>Tổng giá trị</th>
						<th>Chi tiết</th>
						<th>Đã nhận hàng<th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">
						<tr>
							<td>${order.oid}</td>
							<td><fmt:formatDate value="${order.odate}"
									pattern="dd/MM/yyyy HH:mm:ss" /></td>
							<td>${order.ostatus}</td>
							<td>${order.payment}</td>
							<td><fmt:formatNumber value="${order.cost}" type="number"
									currencySymbol="" /> vnđ</td>
							<td><a href="${pageContext.request.contextPath}/user/order/detail?oid=${order.oid }"
								role="button" >
									Xem chi tiết </a></td>
							<td><a href="${pageContext.request.contextPath}/user/order/setStatus?oid=${order.oid }" <c:if test="${order.ostatus == 'Đã nhận hàng' }">hidden</c:if>>Đã nhận</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>

