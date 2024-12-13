<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
	<div class="container">
		<!-- Logo và các menu chính căn giữa -->
		<a class="navbar-brand fs-3 fw-bold" href="${pageContext.request.contextPath}/user/home">Eshopvn<span class="text-danger">.</span></a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsFurni" aria-controls="navbarsFurni" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Menu chính, căn giữa -->
		<div class="collapse navbar-collapse" id="navbarsFurni">
			<ul class="navbar-nav mx-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/user/home">Trang chủ</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/products">Cửa hàng</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/about">Thông tin</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/services">Dịch vụ</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/posts">Bài đăng</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user/contact">Liên hệ</a></li>
			</ul>

			<!-- Phần Login, Cart, Order căn phải -->
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<c:choose>
					<c:when test="${sessionScope.account != null}">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.request.contextPath}/user/settings">
								<span class="fw-semibold">
									<c:choose>
										<c:when test="${sessionScope.account.name == null}">
											${sessionScope.account.userid}
										</c:when>
										<c:otherwise>
											${sessionScope.account.name}
										</c:otherwise>
									</c:choose>
								</span>
							</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
					</c:when>
				</c:choose>
				
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/user/product/favorites" data-bs-toggle="tooltip" title="Yêu thích">
						<i class="fa fa-heart"></i>
					</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/user/cart/view" data-bs-toggle="tooltip" title="Xem giỏ hàng">
						<i class="fa fa-shopping-bag"></i>
					</a>
				</li>

				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/user/orders" data-bs-toggle="tooltip" title="Theo dõi đơn hàng">
						<i class="fa fa-truck"></i>
					</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
