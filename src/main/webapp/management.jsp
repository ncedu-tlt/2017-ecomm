<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Management</title>

    <script src="dist/management/js/vendor.js" type="text/javascript"></script>
    <script src="dist/management/js/app.js" type="text/javascript"></script>

    <script>
        window.contextPath = '${pageContext.request.contextPath}';
    </script>

</head>
<body ng-app="management">
    <nc-root></nc-root>
</body>
</html>