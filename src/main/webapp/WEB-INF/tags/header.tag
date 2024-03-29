<%@tag description="header" pageEncoding="utf-16" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="logout"><spring:message code="site.logout"/></c:set>
<c:set var="settings"><spring:message code="site.settings"/></c:set>
<c:set var="profile"><spring:message code="site.profile"/></c:set>
<c:set var="searchUsers"><spring:message code="site.search.users"/></c:set>
<c:set var="searchRecipes"><spring:message code="site.search.recipes"/></c:set>
<c:set var="home"><spring:message code="site.home"/></c:set>

<header>
    <!-- header -->
    <div class="header">
        <!-- navbar -->
        <nav class="navbar navbar-expand-lg navbar-light p-3 mb-3 bg-light border-bottom">
            <div class="container-fluid">
                <div class="d-flex justify-content-start">
                    <div class="icon"><img src="<spring:url value="/img/icon.png"/>"></div>
                    <div class="nav-link px-2 link-secondary"><h3>SweetLife</h3></div>
                </div>
                <security:authorize access="isAuthenticated()">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="<spring:url value="/profile"/>">${home}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="<spring:url value="/search/recipes"/>">${searchRecipes}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="<spring:url value="/search/users"/>">${searchUsers}</a>
                            </li>
                        </ul>
                    </div>
                    <div class="dropdown text-end d-flex">
                        <div class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="<spring:url value="/img/user-side-icon.png"/>" alt="mdo" class="rounded-circle">
                        </div>
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                            <li><a class="dropdown-item" href="<spring:url value="/profile"/>">${profile}</a></li>
                            <li><a class="dropdown-item" href="<spring:url value="/settings"/>">${settings}</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<spring:url value="/logout"/>">${logout}</a></li>
                        </ul>
                    </div>
                </security:authorize>
            </div>
        </nav>
        <!-- navbar -->
    </div>
    <!-- header -->
</header>



