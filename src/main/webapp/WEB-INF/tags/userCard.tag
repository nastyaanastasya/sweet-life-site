<%@tag description="user-card" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="user" type="ru.kpfu.sweetlife.dto.UserDto" %>

<!-- user-card -->
<div class="col" id="userCard">
    <div class="card">
        <a href="<spring:url value="/profile/${user.id}"/>">
            <c:choose>
                <c:when test="${user.profileImage != null}">
                    <img src="<spring:url value="/media/${user.profileImage.name}"/>" class="card-img-top" alt="profile-image">
                </c:when>
                <c:otherwise>
                    <img src="<spring:url value="/img/user_default.png"/>" class="card-img-top" alt="profile-image">
                </c:otherwise>
            </c:choose>
        </a>
        <div class="card-body">
            <a href="<spring:url value="/profile/${user.id}"/>">
                <h5 class="card-title">${user.nickname}</h5>
            </a>
            <hr>
            <div class="user-info d-flex justify-content-around">
                <div class="item ms-2" id="user-subscribers-count"><i
                        class="fas fa-users"></i><span>${user.subscribersNum}</span></div>
                <div class="item ms-2" id="user-recipes-count"><i
                        class="fas fa-birthday-cake"></i><span>${user.recipeNum}</span></div>
            </div>
        </div>
        <div class="card-footer d-md-flex justify-content-md-end">
            <small class="text-muted">From ${user.dateOfRegistration}</small>
        </div>
    </div>
</div>
<!-- user-card -->