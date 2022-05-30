<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="nicknameLabel"><spring:message code="user.username.title"/></c:set>
<c:set var="nicknamePlaceholder"><spring:message code="user.username.placeholder"/></c:set>

<c:set var="usernameLabel"><spring:message code="user.email.title"/></c:set>
<c:set var="usernamePlaceholder"><spring:message code="user.email.placeholder"/></c:set>

<c:set var="passwordLabel"><spring:message code="user.password.title"/></c:set>
<c:set var="passwordPlaceholder"><spring:message code="user.password.placeholder"/></c:set>

<c:set var="repPasswordLabel"><spring:message code="user.repPassword.title"/></c:set>
<c:set var="repPasswordPlaceholder"><spring:message code="user.repPassword.placeholder"/></c:set>


<c:set var="emailAlreadyExists"><spring:message code="signUp.email.already.registered"/></c:set>

<c:set var="signUp"><spring:message code="signUp.title"/></c:set>
<c:set var="signIn"><spring:message code="signIn.title"/></c:set>
<c:set var="login"><spring:message code="site.login"/></c:set>
<c:set var="alreadyRegistered"><spring:message code="site.already.registered"/></c:set>
<c:set var="registration"><spring:message code="site.registration"/></c:set>

<t:layout title="${registration}">

    <div class="form-container">

        <h2>${registration}</h2>

        <form:form class="registration-form mx-auto" modelAttribute="form">
            <div class="mb-3">
                <label for="nickname" class="form-label">${nicknameLabel}</label>
                <input type="text" class="form-control" id="nickname" placeholder="${nicknamePlaceholder}" value="${form.nickname}" name="nickname" required="true">
            </div>
            <div class="feedback">
                <p class="text-danger text-center">
                    <form:errors path="nickname"/>
                </p>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">${usernameLabel}</label>
                <input type="email" class="form-control" id="username" placeholder="${usernamePlaceholder}" name="username" value="${form.username}" aria-describedby="emailHelp" required="true">
            </div>
            <div class="feedback">
                <p class="text-danger text-center">
                    <form:errors path="username"/>
                </p>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">${repPasswordLabel}</label>
                <input type="password" id="password" name="password" placeholder="${passwordPlaceholder}" class="form-control" aria-describedby="passwordHelp" required="true">
            </div>
            <div class="feedback">
                <p class="text-danger text-center">
                    <form:errors path="password"/>
                </p>
            </div>
            <div class="mb-3">
                <label for="repPassword" class="form-label">${repPasswordLabel}</label>
                <input type="password" id="repPassword" placeholder="${repPasswordPlaceholder}" name="repPassword" class="form-control" required="true">
                <div class="feedback">
                    <p class="text-danger text-center">
                        <form:errors path="repPassword"/>
                    </p>
                </div>
                <c:if test="${errorMessage}">
                    <div class="feedback">
                        <p class="text-danger text-center" id="helpMessage">${emailAlreadyExists}</p>
                    </div>
                </c:if>
                <div class="redirect-login">
                    <p>
                        ${alreadyRegistered} <a class="page-redirect" href="<spring:url value="/login"/>">${signIn}</a>
                    </p>
                </div>

                <div class="button-container">
                    <button type="submit" name="sent" class="btn btn-secondary">${signUp}</button>
                </div>
            </div>
        </form:form>
    </div>

</t:layout>