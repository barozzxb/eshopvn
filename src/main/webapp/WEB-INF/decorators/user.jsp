<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:url value="/" var="URL"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="author" content="Untree.co">
<link rel="shortcut icon" href="favicon.png">

<meta name="description" content="" />
<meta name="keywords" content="bootstrap, bootstrap4" />

<!-- Bootstrap CSS -->
<link href="${URL}assets/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<link href="${URL}assets/css/tiny-slider.css" rel="stylesheet">
<link href="${URL}assets/css/styles.css" rel="stylesheet">
<link href="${URL}assets/css/my-styles.css" rel="stylesheet">
<title>EShop - Tech World</title>
</head>
<body>

<%@ include file="/common/user/header.jsp" %>

	<div>
	<sitemesh:write property="body"/>
	</div>

<%@ include file="/common/user/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>">
<script src="${URL}assets/js/tiny-slider.js"></script>
<script src="${URL}assets/js/custom.js"></script>
<script src="${URL}assets/js/mychart.js"></script>
<script src="${URL}assets/js/myscripts.js"></script>

</body>
</html>