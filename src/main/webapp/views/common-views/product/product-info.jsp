<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<c:url value="/" var="URL"></c:url>
<c:url value="/image?fname=${product.ppicture}" var="productImage"></c:url>

<c:if test="${not empty message}">
    <div id="notification" class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>Thông báo:</strong> ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <script>
        // Tự động ẩn thông báo sau vài giây (5 giây)
        setTimeout(function() {
            var notification = document.getElementById('notification');
            if (notification) {
                notification.style.display = 'none';
            }
        }, 5000); // 5000ms = 5 giây
    </script>
</c:if>
<div class="container my-5 p-5 bg-white">
	
    <div class="row">
        <!-- Product Image -->
        <div class="col-md-6">
            <img src="${productImage}" alt="${product.pname}" class="img-fluid rounded shadow">
        </div>

        <!-- Product Information -->
        <div class="col-md-6">
            <h1 class="mb-3">${product.pname}</h1>
            <h4 class="text-primary mb-3">
                <fmt:formatNumber value="${product.pprice}" type="number" currencySymbol="" /> vnđ
            </h4>
            <p class="text-muted"><strong>Số lượng:</strong> ${product.pquantity}</p>
            <p class="mb-4"><strong>Thể loại:</strong> ${product.genre.gname}</p>
			<p class="mb-4"><strong>Nhà sản xuất:</strong> ${product.pmanufacturer}</p>
            <!-- Product Description -->
            <h5 class="mb-3">Thông tin sản phẩm:</h5>
            <p>${product.pinfo}</p>

            <!-- Ratings -->
            <div class="mb-3">
                <strong>Đánh giá:</strong>
                <span class="text-warning">
                    <c:forEach var="i" begin="1" end="${product.prating}">
                        <i class="fas fa-star"></i>
                    </c:forEach>
                    <c:forEach var="i" begin="${product.prating + 1}" end="5">
                        <i class="far fa-star"></i>
                    </c:forEach>
                </span>
            </div>
	
            <!-- Actions -->
            <div class="d-flex align-items-center gap-3">
                <form action="${pageContext.request.contextPath }/user/cart/add" method="post" class="mt-3">
                    <input type="hidden" name="pid" value="${product.pid}">
                    <button type="submit" class="btn btn-primary mt-2">
                        <i class="fas fa-cart-plus"></i> Thêm vào giỏ hàng
                    </button>
                </form>
                <form action="${pageContext.request.contextPath}/user/product/like"
					method="post" class="mt-3">
					<input type="hidden" name="pid" value="${product.pid}">
					<button type="submit" class="btn btn-danger mt-2">
						<i class="fa fa-heart"></i> Yêu thích
					</button>
				</form>
                
                <a href="${backUrl}" class="btn btn-outline-secondary mt-4">Quay lại</a>
            </div>
        </div>
    </div>
    
    <c:if test="${not empty account }">
    	<div class="recently-viewed-products mt-5">
			<h3 class="text-center mb-4">Recently Viewed Products</h3>

			<c:if test="${not empty viewedProducts}">
				<div class="row">
					<c:forEach var="viewedProduct" items="${viewedProducts}">
						<div class="col-6 col-md-3 col-lg-2 text-center mb-4">
							<a
								href="${pageContext.request.contextPath}/user/product/detail?pid=${viewedProduct.pid}"
								class="viewed-product-link"> <c:url
									value="/image?fname=${viewedProduct.ppicture}" var="imgUrl"></c:url>
								<img src="${imgUrl}" alt="${viewedProduct.pname}"
								class="img-fluid mb-3 viewed-product-img">
								<p class="product-name">${viewedProduct.pname}</p> <strong
								class="product-price"> <fmt:formatNumber
										value="${viewedProduct.pprice}" type="currency"
										currencySymbol="" /> vnđ
							</strong>
							</a>
						</div>
					</c:forEach>
				</div>
			</c:if>

			<c:if test="${empty viewedProducts}">
				<p class="text-center">No products have been viewed yet.</p>
			</c:if>
		</div>
    </c:if>
</div>

<!-- Product Reviews -->
<div class="container product-reviews mt-5 p-5 bg-light">
    <h3 class="mb-4 text-center text-primary">Reviews</h3>

    <c:if test="${not empty reviews}">
        <ul class="list-group review-list">
            <c:forEach var="review" items="${reviews}">
                <li class="list-group-item mb-4 review-item shadow-sm rounded">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <strong class="reviewer-name">${review.account.name}</strong>
                        <span class="badge bg-primary review-rating">${review.rating} <i class="bi bi-star-fill"></i></span>
                    </div>
                    <div class="review-content mb-3">
                        <p class="review-comment">${review.comment}</p>

                        <c:if test="${not empty review.mediaUrl}">
                            <c:choose>
                                <c:when test="${fn:endsWith(review.mediaUrl, '.mp4') || fn:endsWith(review.mediaUrl, '.avi')}">
                                    <div class="ratio ratio-16x9">
                                        <video controls class="w-100 rounded">
                                            <source src="${pageContext.request.contextPath}${review.mediaUrl}" type="video/mp4">
                                            Video không hỗ trợ.
                                        </video>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}${review.mediaUrl}" alt="Review Image" class="img-fluid rounded shadow">
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </div>
                    <small class="text-muted review-date">${review.createdAt}</small>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty reviews}">
        <p class="text-muted text-center">No reviews yet.</p>
    </c:if>
</div>
