<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<div class="body-content">
	<div class="container">
		<div class="sign-in-page">
			<div class="row">
				<div class="col-md-8 ">
					<form action="${pageContext.request.contextPath }/admin/product/edit" method="post" enctype="multipart/form-data">
	                    <div class="form-group">
	                        <label for="pid">Product ID <span>*</span></label>
	                        <input type="text" class="form-control" id="pid" name="pid" placeholder="Enter Product ID" value="${prod.pid }" readonly>
	
	                        <label for="pname" class="mt-2">Product Name <span>*</span></label>
	                        <input type="text" class="form-control" id="pname" name="pname" placeholder="Enter Product Name" value="${prod.pname }">
	
	                        <label for="ppicture" class="mt-2">Product Image <span>*</span></label>
	                        <input type="file" class="form-control" id="ppicture" name="ppicture" value="${prod.ppicture }">
	
	                        <label for="pprice" class="mt-2">Product Price <span>*</span></label>
	                        <input type="text" class="form-control" id="pprice" name="pprice" placeholder="Enter Product Price" value="${prod.pprice }">
	
	                        <label for="pquantity" class="mt-2">Quantity <span>*</span></label>
	                        <input type="text" class="form-control" id="pquantity" name="pquantity" placeholder="Enter Product Quantity" value="${prod.pquantity}">
	
	                        <label for="pinfo" class="mt-2">Product Information <span>*</span></label>
	                        <textarea class="form-control" id="pinfo" name="pinfo" rows="5" placeholder="Enter Product Information" >${prod.pinfo }</textarea>
							
							<label class="mt-2" for="pmanufacturer">Manufacturer <span>*</span></label> 
							<input type="text" class="form-control" id="pmanufacturer" placeholder="Enter Product Manufacturer" name="pmanufacturer" value="${prod.pmanufacturer }"> 
	                        
	                        <label class="mt-2" for="gid">Genre <span>*</span></label> 
							<select class="btn-group dropright" name="pgenre" id="pgenre">
								<c:forEach items="${listgenre}" var="g">
									<option value="${g.gid}" <c:if test="${p.gid == g.gid}">selected</c:if>>${g.gname}</option>
								</c:forEach>
							</select>
	
	                        <div class="mt-3">
	                            <button type="submit" class="btn btn-primary">Edit Product</button>
	                            <a href="${pageContext.request.contextPath }/admin/products"
										class="btn-upper btn btn-primary">Back</a>
	                        </div>
	                    </div>
                	</form>
	
				</div>
			</div>
		</div>
	</div>
</div>