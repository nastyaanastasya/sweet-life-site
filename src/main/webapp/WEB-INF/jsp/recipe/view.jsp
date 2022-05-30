<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="recipeTitle"><spring:message code="recipe.edit"/></c:set>
<c:set var="selectedImages"><spring:message code="recipe.images"/></c:set>
<c:set var="recipeTitlePlaceholder"><spring:message code="recipe.title"/></c:set>
<c:set var="timeOfCooking"><spring:message code="recipe.time"/></c:set>
<c:set var="ingredientsTitle"><spring:message code="recipe.ingredients"/></c:set>
<c:set var="recipeDescription"><spring:message code="recipe.description"/></c:set>
<c:set var="commentPlaceholder"><spring:message code="comment.placeholder"/></c:set>
<c:set var="commentAction"><spring:url value="/comment/add?recipeId=${recipe.id}"/></c:set>
<c:set var="previous"><spring:message code="recipe.image.prev"/></c:set>
<c:set var="next"><spring:message code="recipe.image.next"/></c:set>
<c:set var="noImagesAdded"><spring:message code="recipe.no.images"/></c:set>
<c:set var="postComment"><spring:message code="recipe.post.comment"/></c:set>
<c:set var="commentsTitle"><spring:message code="recipe.comments.title"/></c:set>


<t:layout title="${recipe.title}">

    <script src="<spring:url value="/js/recipeViewActions.js"/>"></script>
    <!-- recipe -->
    <div class="recipe-view">
        <div class="container">
            <div class="preview">
                <div class="preparation h-100 mb-3">
                    <h2 class="recipe-title mt-2 mb-3">${recipe.title}</h2>
                    <ul class="ingredient-list-dots mb-2">
                        <div class="mb-3"><i class="fas fa-clock"></i><span class="ms-1">${timeOfCooking}:
                        <c:choose>
                            <c:when test="${recipe.hours != 0}">
                               <b><span>${recipe.hours}h ${recipe.minutes}m</span></b>
                            </c:when>
                            <c:otherwise>
                                <b><span>${recipe.minutes}m</span></b>
                            </c:otherwise>
                        </c:choose>
                        </span></div>
                        <c:forEach var="ingredient" items="${recipe.ingredients}">
                            <li>
                                <span class="title">${ingredient.name}</span>
                                <span class="chapter">${ingredient.amount} ${ingredient.units}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="result-images h-100">
                    <c:choose>
                        <c:when test="${recipe.images.size()>0}">
                            <div id="carouselExampleDark" class="carousel carousel-dark slide mt-2"
                                 data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active" data-bs-interval="4000">
                                        <img src="<spring:url value="/media/${recipe.images.get(0).name}"/>"
                                             class="d-block" alt="...">
                                        <div class="carousel-caption d-none d-md-block">
                                        </div>
                                    </div>
                                    <c:forEach var="img" items="${recipe.images.subList(1, recipe.images.size())}">
                                        <div class="carousel-item" data-bs-interval="4000">
                                            <img src="<spring:url value="/media/${img.name}"/>" class="d-block"
                                                 alt="...">
                                            <div class="carousel-caption d-none d-md-block">
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <button class="carousel-control-prev" type="button"
                                        data-bs-target="#carouselExampleDark" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">${previous}</span>
                                </button>
                                <button class="carousel-control-next" type="button"
                                        data-bs-target="#carouselExampleDark" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">${next}</span>
                                </button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h3 style="margin-top: 8vh; color: rgba(0,0,0,0.2)">${noImagesAdded}</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="description mx-auto">
                <h5 class="description-title mx-auto mb-3 mt-3">${recipeDescription}</h5>
                <div class="recipe-text mb-2" id="description">
                        ${recipe.description}
                </div>
            </div>
            <hr>
            <div class="action-icons d-flex justify-content-end mb-3">
                <div class="icon" id="icon-save">
                    <c:choose>
                        <c:when test="${recipe.likedByCurrentUser}">
                            <i class="fas fa-thumbs-up state-active" data-id="${recipe.id}" data-state="1" id="like-recipe-icon"></i>
                        </c:when>
                        <c:otherwise>
                            <i class="fas fa-thumbs-up" data-id="${recipe.id}" data-state="0" id="like-recipe-icon"></i>
                        </c:otherwise>
                    </c:choose>
                    <span class="ms-1" id="likes-count">${recipe.likesCount}</span>
                </div>
                <div class="icon ms-3" id="icon-like">
                    <c:choose>
                        <c:when test="${recipe.savedByCurrentUser}">
                            <i class="fas fa-bookmark state-active" data-id="${recipe.id}" data-state="1" id="save-recipe-icon"></i>
                        </c:when>
                        <c:otherwise>
                            <i class="fas fa-bookmark" id="save-recipe-icon" data-id="${recipe.id}" data-state="0"></i>
                        </c:otherwise>
                    </c:choose>
                    <span class="ms-1" id="saves-count">${recipe.savesCount}</span>
                </div>
            </div>
        </div>
    </div>
    <!-- recipe -->

    <div class="comment-content mt-3 mb-2">
        <div class="container">
            <div class="new-comment mb-2">
                <!-- comment box -->
                <form:form action="${commentAction}" method="post"
                      class="comment-area-box mb-3" modelAttribute="form">
                    <span class="input-icon">
                        <textarea rows="3" name="text" class="form-control mb-2"
                                  placeholder="${commentPlaceholder}"></textarea>
                    </span>
                    <div class="comment-area-btn">
                        <div class="float-end">
                            <button type="submit" name="add-comment"
                                    class="btn btn-sm btn-secondary waves-effect waves-light">
                                    ${postComment}
                            </button>
                        </div>
                        <div class="comment-actions d-flex justify-content-start">
                            <div class="rating-area">
                                <input type="radio" id="star-5" name="rating" value="5">
                                <label for="star-5" title="Rate «5»"></label>
                                <input type="radio" id="star-4" name="rating" value="4">
                                <label for="star-4" title="Rate «4»"></label>
                                <input type="radio" id="star-3" name="rating" value="3">
                                <label for="star-3" title="Rate «3»"></label>
                                <input type="radio" id="star-2" name="rating" value="2">
                                <label for="star-2" title="Rate «2»"></label>
                                <input type="radio" id="star-1" name="rating" value="1">
                                <label for="star-1" title="Rate «1»"></label>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
            <h5 class="comments-title mt-3">${commentsTitle}</h5>
            <hr>
            <div class="old-comments">
                <c:forEach var="c" items="${recipe.comments}">
                    <t:comment comment="${c}"/>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- comment-content -->
</t:layout>