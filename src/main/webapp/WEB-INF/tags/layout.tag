<%@tag description="layout" pageEncoding="utf-16" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" required="true" type="java.lang.String" %>

<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <t:head/>
</head>
<body>
    <t:header/>
    <jsp:doBody/>
    <t:footer/>
</body>
</html>