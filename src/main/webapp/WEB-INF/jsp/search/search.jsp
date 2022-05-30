<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="queryPlaceholder"><spring:message code="${placeholder}"/></c:set>
<c:set var="searchTitle"><spring:message code="site.search"/></c:set>

<c:set var="noResultsFound"><spring:message code="site.search.no.results"/></c:set>

<t:layout title="${searchTitle}">

<%--    <script src="<spring:url value="/js/searchActions.js"/>"></script>--%>
    <!-- search-content -->
    <div class="search-content">
        <div class="container">

            <!-- search-area -->
            <form class="search-form" method="post">
                <div class="searh-area mb-3">
                    <div class="search-form d-flex justify-content-center">
                        <div class="form-search-field ms-3" style="width: 60vw;">
                            <input type="text" class="form-control" name="query"  placeholder="${queryPlaceholder}" id="queryInput">
                        </div>
                        <button class="btn search ms-2"    type="submit">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
            <!-- search-area -->

            <!-- content-area -->
            <div class="content-area">
                <div class="container">
                    <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4">
                        <c:choose>
                            <c:when test="${recipeList.size()>0}">
                                <c:forEach var="recipe" items="${recipeList}">
                                    <t:recipeCard recipe="${recipe}"/>
                                </c:forEach>
                            </c:when>
                            <c:when test="${accounts.size()>0}">
                                <c:forEach var="account" items="${accounts}">
                                    <t:userCard user="${account}"/>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <h1 class="no-content-text mx-auto">${noResultsFound}</h1>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <!-- content-area -->
        </div>
    </div>
    <!-- search-content -->

</t:layout>