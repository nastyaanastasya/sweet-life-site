<%@tag description="recipe-card" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@attribute name="recipe" type="ru.kpfu.sweetlife.dto.RecipeDto" %>
<%@attribute name="viewer" type="ru.kpfu.sweetlife.dto.UserDto" %>

<!-- recipe card -->
<div class="col" id="recipeCard">
    <div class="card">
        <a href="<spring:url value="/recipe/${recipe.id}"/>">
            <c:choose>
                <c:when test="${recipe.images.size()>0}">
                    <img src="<spring:url value="/media/${recipe.images.get(0).name}"/>" class="card-img-top"
                         alt="recipe-image">
                </c:when>
                <c:otherwise>
                    <img src="<spring:url value="/img/icon.png"/>" class="card-img-top" alt="recipe-image">
                </c:otherwise>
            </c:choose>
        </a>
        <div class="card-body">
            <a href="<spring:url value="/recipe/${recipe.id}"/>">
                <h5 class="card-title">${recipe.title}</h5>
            </a>
            <hr>
            <div class="recipe-info d-flex justify-content-md-around">
                <div class="item" id="recipe-time-of-cooking"><i class="fas fa-clock"></i>
                    <c:choose>
                        <c:when test="${recipe.hours != 0}">
                            <span>${recipe.hours}h ${recipe.minutes}m</span>
                        </c:when>
                        <c:otherwise>
                            <span>${recipe.minutes}m</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="item" id="recipe-rating"><i class="fas fa-star"></i>
                    <span>${recipe.recipeRating}</span>
                </div>
            </div>
            <hr>
            <div class="recipe-author d-md-flex justify-content-md-end" id="recipe-author">
                <a href="<spring:url value="/profile/${recipe.author.id}"/>">${recipe.author.nickname}</a>
            </div>
        </div>
        <div class="card-footer d-md-flex justify-content-md-between">
            <c:if test="${recipe.author.id.equals(viewer.id)}">
                <button type="button" data-id="${recipe.id}" id="recipe-edit-btn"
                        class="btn btn-outline-secondary ms-2 col-6">Edit
                </button>
            </c:if>
            <small class="text-muted">${recipe.dateOdEditing}</small>
        </div>
    </div>
</div>
<!-- recipe card -->