<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="serverError"><spring:message code="error.server"/></c:set>

<t:layout title="${serverError}">

    <div class="form-container">

        <h2>${serverError}</h2>
        <div class="error-image">
            <img src="<spring:url value="/img/error-cake.png"/>" class="error-img" style="alignment: center;">
        </div>
    </div>

</t:layout>