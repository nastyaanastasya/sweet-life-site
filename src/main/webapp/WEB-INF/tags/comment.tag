<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@tag description="footer" pageEncoding="UTF-16" %>
<%@attribute name="comment" type="ru.kpfu.sweetlife.dto.CommentDto" %>

<c:set var="recipeTitle"><spring:message code="recipe.edit"/></c:set>
<c:set var="selectedImages"><spring:message code="recipe.images"/></c:set>
<c:set var="recipeTitlePlaceholder"><spring:message code="recipe.title"/></c:set>
<c:set var="timeOfCooking"><spring:message code="recipe.time"/></c:set>
<c:set var="ingredientsTitle"><spring:message code="recipe.ingredients"/></c:set>
<c:set var="description"><spring:message code="recipe.description.full"/></c:set>
<c:set var="commentPlaceholder"><spring:message code="comment.placeholder"/></c:set>


<!-- comment -->
<div class="comment-container">
    <div class="media d-flex justify-content-start">
        <div>
            <a href="<spring:url value="/profile/${comment.author.id}"/>">
                <c:choose>
                    <c:when test="${comment.author.profileImage != null}">
                        <img src="<spring:url value="/media/${comment.author.profileImage.name}"/>" alt="profile-image">
                    </c:when>
                    <c:otherwise>
                        <img src="<spring:url value="/img/user_default.png"/>" alt="profile-image">
                    </c:otherwise>
                </c:choose>
            </a>
        </div>
        <div class="media-body ms-2">
            <h4 class="media-heading"><a
                    href="<spring:url value="/profile/${comment.author.id}"/>">${comment.author.nickname}</a>
            </h4>
            <p>${comment.text}</p>
            <div class="info d-flex justify-content-between">
                <ul class="list-unstyled list-inline media-detail pull-left d-flex justify-content-start mt-1 ms-3">
                    <li><i class="fa fa-calendar"></i>${comment.dateOfAdding}</li>
                </ul>
                <div class="rating-result mb-3 ms-3" id="comment-rate">
                    <li><i class="fa fa-star"></i>${comment.recipeRate}</li>
                </div>
                <div class="comment-like mb-3 ms-3 " id="like-comment">
                    <c:choose>
                        <c:when test="${comment.likedByCurrentUser}">
                            <li><i class="fa fa-thumbs-up state-active" data-state="1" data-id="${comment.id}" id="like-comment-icon"></i>
                                <span class="comment-likes-count-no-${comment.id}">${comment.likesNumber}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li><i class="fa fa-thumbs-up" data-state="0" data-id="${comment.id}" id="like-comment-icon"></i>
                                <span class="comment-likes-count-no-${comment.id}">${comment.likesNumber}</span>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>
<!-- comment -->