<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<div class="hero bg-dark text-white">
    <div class="container">
        <div class="row justify-content-between align-items-center">
            <!-- Text content on the left -->
            <div class="col-lg-6 col-md-12 mb-4 mb-lg-0">
                <div class="hero-text">
                    <h1 class="display-4 fw-bold">Chào mừng đến với <span class="text-danger">Star Shop</span></h1>
                    <p class="lead mb-4">Khám phá các sản phẩm điện tử chất lượng cao và giá cả hợp lý, mang đến trải nghiệm tuyệt vời cho bạn.</p>
                    <a href="${pageContext.request.contextPath}/products" class="btn btn-danger btn-lg me-3">Khám phá ngay</a>
                    <a href="${pageContext.request.contextPath}/about" class="btn btn-outline-light btn-lg">Tìm hiểu thêm</a>
                </div>
            </div>
            <!-- Image on the right -->
            <div class="col-lg-6 col-md-12">
                <div class="hero-img-wrap text-center">
                    <img src="${URL }assets/images/electronic.webp" alt="Sản phẩm điện tử" class="img-fluid rounded shadow-lg" width="300">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row justify-content-center mb-4 py-3">
    <div class="col-10 col-md-8">
        <form action="${pageContext.request.contextPath}/search" method="get" class="d-flex">
            <input type="text" class="form-control form-control-sm border-secondary bg-light" name="query" placeholder="Tìm kiếm sản phẩm..." aria-label="Search">

            <button type="submit" class="btn btn-outline-dark btn-sm ms-2">Tìm kiếm</button>
        </form>
    </div>
</div>



<div class="container py-2 border">
		<div id="demo" class="carousel slide" data-bs-ride="carousel">
		    <div class="carousel-inner">
		        <c:set var="slideIndex" value="0"/>
		        <c:forEach items="${genres}" var="genre" varStatus="status">
		            <!-- Mỗi slide mới bắt đầu sau 4 genre -->
		            <c:if test="${status.index % 4 == 0}">
		                <!-- Nếu là slide đầu tiên -->
		                <div class="carousel-item ${status.index == 0 ? 'active' : ''}">
		                    <div class="row justify-content-center">
		            </c:if>
		
		            <!-- Hiển thị genre -->
		            <div class="col-12 col-md-3 mb-5 mb-md-0">
		                <div class="card shadow-sm border-light cdbg-light-green">
		                    <div class="card-body text-center">
		                        <h5 class="card-title">${genre.gname}</h5>
		                        <p><em>${genre.productCount} sản phẩm</em></p>
		                        <a href="${pageContext.request.contextPath}/genre/products?gid=${genre.gid}" class="btn btn-primary">Xem chi tiết <i class="fa fa-arrow-right"></i></a>
		                    </div>
		                </div>
		            </div>
		
		            <!-- Kết thúc slide sau khi có 4 genre -->
		            <c:if test="${status.index % 4 == 3 || status.last}">
		                </div> <!-- End row -->
		                </div> <!-- End carousel-item -->
		            </c:if>
		        </c:forEach>
		    </div>
		
		    <!-- Left and right controls/icons -->
		    <button class="carousel-control-prev text-dark-gray" type="button" data-bs-target="#demo" data-bs-slide="prev">
		        <span class="carousel-control-prev-icon text-dark-gray"></span>
		    </button>
		    <button class="carousel-control-next text-dark-gray" type="button" data-bs-target="#demo" data-bs-slide="next">
				<span class="carousel-control-next-icon text-dark-gray"></span>
		    </button>
	</div>
	
<div class="product-section">		
	<div class="container py-5 border bg-white">
		<div class="col-12 text-center mb-5">
				<h2 class="mb-4 section-title">Sản phẩm mới.</h2>
			</div>
		<div class="row justify-content-center align-items-center g-4">
			<c:forEach items="${topprod}" var="prod">
				<div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-5">
				    <c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
				    <a class="product-item" href="${pageContext.request.contextPath }/product/detail?pid=${prod.pid}">
				        <div class="product-image">
				            <img src="${imgUrl}" class="img-fluid product-thumbnail" alt="${prod.pname}">
				        </div>
				        <div class="product-info">
				            <h3 class="product-title text-truncate" title="${prod.pname}">${prod.pname}</h3>
				            <strong class="product-price">
				                <fmt:formatNumber value="${prod.pprice}" type="number" currencySymbol="" /> vnđ
				            </strong>
				
				            <!-- Buttons inside product info section, displayed on the same row -->
				            <div class="product-actions mt-3 d-flex justify-content-between">
				                <form action="${pageContext.request.contextPath }/user/cart/add" method="post" class="d-inline-block">
				                    <input type="hidden" name="pid" value="${prod.pid}">
				                    <button type="submit" class="btn btn-primary btn-sm">
				                        <i class="fas fa-cart-plus"></i>
				                    </button>
				                </form>
				
				                <form action="${pageContext.request.contextPath}/user/product/like" method="post" class="d-inline-block">
				                    <input type="hidden" name="pid" value="${prod.pid}">
				                    <button type="submit" class="btn btn-danger btn-sm">
				                        <i class="fa fa-heart"></i>
				                    </button>
				                </form>
				            </div>
				        </div>
				    </a>
				</div>



			</c:forEach>
		</div>
	</div>
	<br></br>

	<div class="container py-5 border bg-white">
		<div class="row">
			<div class="col-12 text-center mb-5">
				<h2 class="mb-4 section-title">Một số sản phẩm bán chạy.</h2>
			</div>
		</div>
		<div class="row justify-content-center align-items-center g-4">
			<c:forEach items="${topSelling}" var="prod">
				<div class="col-6 col-md-4 col-lg-2 mb-5 mb-md-0">
				    <c:url value="/image?fname=${prod.product.ppicture}" var="imgUrl"></c:url>
				    <a class="product-item" href="${pageContext.request.contextPath }/product/detail?pid=${prod.product.pid}">
				        <div class="product-image">
				            <img src="${imgUrl}" class="img-fluid product-thumbnail" alt="${prod.product.pname}">
				        </div>
				        <div class="product-info">
				            <h3 class="product-title">${prod.product.pname}</h3>
				            <strong class="product-price">
				                <fmt:formatNumber value="${prod.product.pprice}" type="number" currencySymbol="" /> vnđ
				            </strong>
				            
				            <!-- Buttons inside product info section, displayed on the same row -->
				            <div class="product-actions mt-3 d-flex justify-content-between">
				                <form action="${pageContext.request.contextPath }/user/cart/add" method="post" class="d-inline-block">
				                    <input type="hidden" name="pid" value="${prod.product.pid}">
				                    <button type="submit" class="btn btn-primary btn-sm">
				                        <i class="fas fa-cart-plus"></i>
				                    </button>
				                </form>
								<p><em>${prod.ordernumber} lượt bán</em></p>
				                <form action="${pageContext.request.contextPath}/user/product/like" method="post" class="d-inline-block">
				                    <input type="hidden" name="pid" value="${prod.product.pid}">
				                    <button type="submit" class="btn btn-danger btn-sm">
				                        <i class="fa fa-heart"></i>
				                    </button>
				                </form>
				            </div>
				        </div>
				    </a>
				</div>
			</c:forEach>
		</div>
	</div>
	<br>

</div>

