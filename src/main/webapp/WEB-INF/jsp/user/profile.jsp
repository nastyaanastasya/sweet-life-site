<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="profile"><spring:message code="site.profile"/></c:set>

<c:set var="following"><spring:message code="user.profile.list.following"/></c:set>
<c:set var="subscribers"><spring:message code="user.profile.list.subscribers"/></c:set>
<c:set var="savedRecipes"><spring:message code="user.profile.list.saved"/></c:set>
<c:set var="likedRecipes"><spring:message code="user.profile.list.liked"/></c:set>
<c:set var="recipes"><spring:message code="user.profile.recipes"/></c:set>

<c:set var="follow"><spring:message code="user.profile.follow"/></c:set>
<c:set var="unfollow"><spring:message code="user.profile.unfollow"/></c:set>

<c:set var="ban"><spring:message code="user.profile.admin.ban"/></c:set>
<c:set var="unban"><spring:message code="user.profile.admin.unban"/></c:set>

<c:set var="addRecipe"><spring:message code="recipe.add"/></c:set>

<c:set var="noContent"><spring:message code="user.profile.no.content"/></c:set>

<c:set var="ratingTitle"><spring:message code="user.profile.rating"/></c:set>

<c:set var="contentTitle"><spring:message code="${title}"/></c:set>

<t:layout title="${profile}">

    <script src="<spring:url value="/js/profileActions.js"/>"></script>
    <script src="<spring:url value="/js/cardActions.js"/>"></script>

    <!-- page content -->
    <div class="profile-content" id="profileContent">

        <!-- user-info -->
        <div class="side-bar ms-3 bg-light" id="profileSideBar">
            <div class="p-2 ">
                <div class="user-info">
                    <div class="wrapper">
                        <div class="text-center" id="text-center">
                            <div>
                                <c:choose>
                                    <c:when test="${user.profileImage != null}">
                                        <img src="<spring:url value="/media/${user.profileImage.name}"/>" alt="profile-picture">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<spring:url value="/img/user_default.png"/>" alt="profile-picture">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item nickname"><span id="nickname">${user.nickname}</span></li>
                            <li class="list-group-item username"><span id="username">${user.username}</span></li>
                            <li class="list-group-item rating"><span id="userRating">${ratingTitle}: ${rating}</span>
                            </li>
                            <li class="list-group-item recipe-num">
                                <span id="userRecipeNum">${recipes}: ${user.recipeNum}</span>
                            </li>

                            <c:if test="${not user.viewer}">
                                <c:choose>
                                    <c:when test="${user.followed}">
                                        <li class="list-group-item recipe_num">
                                            <button id="subscribe-button" data-state="1" data-id="${user.id}"
                                                    class="btn btn-outline-secondary ms-2">
                                                    ${unfollow}
                                            </button>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="list-group-item recipe_num">
                                            <button id="subscribe-button" data-state="0" data-id="${user.id}"
                                                    class="btn btn-secondary ms-2">
                                                    ${follow}
                                            </button>
                                        </li>
                                    </c:otherwise>
                                </c:choose>

                                <c:if test="${user.viewerAdmin}">
                                    <c:choose>
                                        <c:when test="${user.state eq 'BANNED'}">
                                            <li class="list-group-item recipe_num">
                                                <button id="ban-button" data-state="1" data-id="${user.id}"
                                                        class="btn btn-outline-danger ms-2">
                                                        ${unban}
                                                </button>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="list-group-item recipe_num">
                                                <button id="ban-button" data-state="0" data-id="${user.id}"
                                                        class="btn btn-danger ms-2">
                                                        ${ban}
                                                </button>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:if>

                        </ul>
                    </div>
                </div>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li class="nav-item">
                        <a href="<spring:url value="/profile/${user.id}"/>" class="nav-link link-dark">
                            <i class="fas fa-birthday-cake"></i><span>${recipes}</span>
                        </a>
                    </li>
                    <c:if test="${user.viewer}">
                        <li class="nav-item">
                            <a href="<spring:url value="/profile/likedRecipes"/>" class="nav-link link-dark">
                                <i class="fas fa-thumbs-up"></i><span>${likedRecipes}</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="<spring:url value="/profile/savedRecipes"/>" class="nav-link link-dark">
                                <i class="fas fa-bookmark"></i><span>${savedRecipes}</span>
                            </a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a href="<spring:url value="/profile/following?userId=${user.id}"/>"
                           class="nav-link link-dark">
                            <i class="fas fa-heart"></i><span>${following}</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<spring:url value="/profile/subscribers?userId=${user.id}"/>"
                           class="nav-link link-dark">
                            <i class="fas fa-users"></i><span>${subscribers}</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- user-info -->

        <!-- user-content -->
        <div class="user-content ms-3" id="userProfileContent">
            <div class="recipe-actions d-flex justify-content-between">
                <h3 class="user-content-title ms-3" id="profileContentTitle">${contentTitle}</h3>
                <div class="buttons d-flex justify-content-end">
                    <c:if test="${user.viewer}">
                        <button id="add-new-recipe-btn" name="add-new-recipe-btn" class="btn btn-outline-secondary ms-2">
                                ${addRecipe}
                        </button>
                    </c:if>
                </div>
            </div>
            <div class="row row-cols-1 row-cols-md-3 g-3">
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
                        <h1 class="no-content-text mx-auto">${noContent}</h1>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!-- user-content -->
    </div>
    <!-- page content -->

</t:layout>