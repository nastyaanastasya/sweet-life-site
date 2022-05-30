<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="usernameLabel"><spring:message code="user.email.title"/></c:set>
<c:set var="usernamePlaceholder"><spring:message code="user.email.placeholder"/></c:set>

<c:set var="passwordLabel"><spring:message code="user.password.title"/></c:set>
<c:set var="passwordPlaceholder"><spring:message code="user.password.placeholder"/></c:set>

<c:set var="rememberMe"><spring:message code="site.remember.me"/></c:set>

<c:set var="signIn"><spring:message code="signIn.title"/></c:set>
<c:set var="signInWithGitHub"><spring:message code="signIn.oauth"/></c:set>
<c:set var="signUp"><spring:message code="signUp.title"/></c:set>
<c:set var="registration"><spring:message code="site.registration"/></c:set>
<c:set var="notRegistered"><spring:message code="site.not.registered.yet"/></c:set>
<c:set var="loginTitle"><spring:message code="site.login"/></c:set>

<t:layout title="${loginTitle}">

    <div class="form-container">

        <h2>${loginTitle}</h2>

        <form:form class="login-form mx-auto">
            <div class="mb-3">
                <label for="username" class="form-label">${usernameLabel}</label>
                <input type="email" placeholder="${usernamePlaceholder}" class="form-control" id="username"
                       name="username" aria-describedby="emailHelp" required="true">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">${passwordLabel}</label>
                <input type="password" id="password" placeholder="${passwordPlaceholder}" name="password"
                       class="form-control" aria-describedby="passwordHelp" required="true">
            </div>
            <div class="form-check form-checkbox">
                <input class="form-check-input" type="checkbox" name="remember-me" id="remember-me">
                <label class="form-check-label" for="remember-me">${rememberMe}</label>
            </div>
            <div class="redirect-registration">
                <p>
                        ${notRegistered} <a class="page-redirect" href="<spring:url value="/registration"/>">${signUp}</a>
                </p>
            </div>

            <div class="button-container">
                <button type="submit" class="btn btn-secondary mb-3">${signIn}</button>
            </div>
            <div class="button-container">
                <a href="https://github.com/login/oauth/authorize?client_id=${clientId}&scope=user:email">
                        ${signInWithGitHub}
                </a>
            </div>
        </form:form>
    </div>

</t:layout>