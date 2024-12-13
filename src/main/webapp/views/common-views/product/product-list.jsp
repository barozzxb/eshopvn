<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/" var="URL"></c:url>

<div class="search-bar-section bg-white py-4">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8">
                <form action="${pageContext.request.contextPath}/user/product/search" method="get" class="search-bar-form d-flex">
                    <input 
                        type="text" 
                        name="query" 
                        class="form-control rounded-start" 
                        placeholder="Search for products, brands, or categories..." 
                        aria-label="Search"
                        required>
                    <button type="submit" class="btn btn-primary rounded-end">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="untree_co-section product-section before-footer-section">
    <div class="container">
        <div class="row">
            <!-- Sidebar Filter -->
            <div class="col-lg-12 mb-4">
                <div class="card">
                    <!-- Thêm thuộc tính data-bs-toggle và data-bs-target -->
                    <div class="card-header" id="filterHeading">
                        <h4 class="mb-0">
                            <button class="btn-no-border w-100 text-start" data-bs-toggle="collapse" data-bs-target="#filterCollapse" aria-expanded="true" aria-controls="filterCollapse">
                                Bộ lọc
                            </button>
                        </h4>
                    </div>
                    <div id="filterCollapse" class="collapse hide" aria-labelledby="filterHeading" data-bs-parent=".untree_co-section">
                        <div class="card-body">
                            <form action=" 
                                <c:choose>
                                    <c:when test="${not empty sessionScope.account}">
                                        ${pageContext.request.contextPath}/user/product/filter
                                    </c:when>
                                    <c:otherwise>
                                        ${pageContext.request.contextPath}/product/filter
                                    </c:otherwise>
                                </c:choose>" method="get" class="d-flex flex-column align-items-stretch">

                                <!-- Filter by Category (Genres) -->
                                <div class="mb-3">
                                    <label for="genre" class="form-label">Thể loại</label>
                                    <select id="genre" name="genres" class="form-select form-select-sm w-100">
                                        <option value="">Chọn thể loại</option>
                                        <c:forEach items="${genres}" var="genre">
                                            <option value="${genre.gid}" <c:if test="${param.genre == genre.gid}">selected</c:if>>${genre.gname}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Filter by Price -->
                                <div class="mb-3">
                                    <label for="price" class="form-label">Giá</label>
                                    <select id="price" name="price" class="form-select form-select-sm w-100">
                                        <option value="">Chọn giá</option>
                                        <option value="200000" <c:if test="${param.price == '200000'}">selected</c:if>>Dưới 200.000</option>
                                        <option value="500000" <c:if test="${param.price == '500000'}">selected</c:if>>Dưới 500.000</option>
                                        <option value="1000000" <c:if test="${param.price == '1000000'}">selected</c:if>>Dưới 1.000.000</option>
                                        <option value="2000000" <c:if test="${param.price == '2000000'}">selected</c:if>>Dưới 2.000.000</option>
                                    </select>
                                </div>

                                <!-- Submit Filter Button -->
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-primary btn-sm w-100">Áp dụng bộ lọc</button>
                                </div>

                                <!-- Reset Filter Button -->
                                <div class="mb-3">
                                    <a href="
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.account}">
                                                ${pageContext.request.contextPath}/user/product/reset
                                            </c:when>
                                            <c:otherwise>
                                                ${pageContext.request.contextPath}/product/reset
                                            </c:otherwise>
                                        </c:choose>" class="btn btn-light btn-sm w-100">Đặt lại</a>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="container py-5 border bg-white">
		<div class="row">
			<div class="col-12 text-center mb-5">
				<h2 class="mb-4 section-title">Sản phẩm</h2>
			</div>
		</div>
		<div class="row justify-content-center align-items-center g-4">

            <!-- Product Display -->
            <c:forEach items="${products}" var="prod">
                <div class="col-6 col-md-4 col-lg-2 mb-5 mb-md-0">
                    <c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
                    <c:choose>
                        <c:when test="${account != null}">
                            <c:url value="product/detail" var="baseURL" />
                        </c:when>
                        <c:otherwise>
                            <c:url value="product/detail" var="baseURL" />
                        </c:otherwise>
                    </c:choose>
                    
                    <c:url value="${baseURL}" var="detailURL">
                        <c:param name="pid" value="${prod.pid}" />
                    </c:url>
                    
                    <a class="product-item" href="${pageContext.request.contextPath }/product/detail?pid=${prod.pid}">
				        <div class="product-image">
				            <img src="${imgUrl}" class="img-fluid product-thumbnail" alt="${prod.pname}">
				        </div>
				        <div class="product-info">
				            <h3 class="product-title">${prod.pname}</h3>
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