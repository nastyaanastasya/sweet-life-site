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
<c:set var="hoursPlaceholder"><spring:message code="recipe.time.hours.placeholder"/></c:set>
<c:set var="minutesPlaceholder"><spring:message code="recipe.time.minutes.placeholder"/></c:set>
<c:set var="ingredientsTitle"><spring:message code="recipe.ingredients"/></c:set>
<c:set var="describeRecipe"><spring:message code="recipe.description.full"/></c:set>
<c:set var="saveRecipe"><spring:message code="button.save"/></c:set>
<c:set var="deleteRecipe"><spring:message code="button.delete"/></c:set>

<t:layout title="${recipeTitle}">

    <script src="<spring:url value="/js/editorAreaInit.js"/>"></script>
    <script src="<spring:url value="/js/recipeViewActions.js"/>"></script>
    <script src="<spring:url value="/js/recipeEditActions.js"/>"></script>

    <!-- recipe-content -->
    <div class="recipe-content">
        <div class="container mb-3">
            <h3 class="title">${recipeTitle}</h3>
            <form:form modelAttribute="form" class="recipe-form mx-auto" id="form-editing" method="POST"
                       enctype="multipart/form-data">
                <div class="col">
                    <div class="select mb-3">
                        <input class="form-control" type="file" name="images"
                               aria-label="Select file" id="loaded-img" multiple accept="image/*">
                    </div>
                    <div class="container-content">
                        <div class="recipe-images ms-0 mb-3">
                            <div class="images-title text-center mb-2">${selectedImages}</div>
                            <div class="row row-cols-1 row-cols-md-2 g-4" data-attrs="${images}" id="selected-img">
                            </div>
                        </div>
                        <div class="recipe-attributes ms-3 mb-3 justify-content-end">
                            <div class="form-add">
                                <div class="inputs">
                                    <div class="title mb-3">
                                        <input type="text" class="form-control" id="title" value="${title}"
                                               name="title"
                                               placeholder="${recipeTitlePlaceholder}">
                                    </div>
                                    <div class="time-of-cooking mb-3">
                                        <span class="time-title">${timeOfCooking}</span>
                                        <input type="text" class="form-control ms-3" name="hours"
                                               placeholder="${hoursPlaceholder}">
                                        <span class="colon ms-1">:</span>
                                        <input type="text" class="form-control ms-1" name="minutes"
                                               placeholder="${minutesPlaceholder}">
                                    </div>
                                    <div class="ingredients mb-3" id="ingredients-view">
                                        <div class="ingredients-title mb-3">${ingredientsTitle}</div>
                                        <div class="ingredient-item mb-2">
                                            <input class="form-control ingredient ms-3" type="text" id="ingredient"
                                                   value="${ingredients.get(0).name}" name="ingredient"
                                                   placeholder="Ingredient" style="width: 250px;">
                                            <input class="form-control amount ms-3" type="text" id="amount"
                                                   value="${ingredients.get(0).amount}" name="amount"
                                                   placeholder="Amount" style="width: 150px;">
                                            <input class="form-control amount ms-3" type="text" id="unit"
                                                   value="${ingredients.get(0).units}" name="unit"
                                                   placeholder="Unit" style="width: 150px;">
                                            <div class="new-ingredient" id="new-ingredient"><i
                                                    class="fas fa-plus ms-3 mt-2"></i></div>
                                        </div>
                                        <c:if test="${ingredients.size()>1}">
                                            <c:forEach var="ingr"
                                                       items="${ingredients.sublist(1, ingredients.size())}">
                                                <script>
                                                    addNextIngredient(${ingr.name}, ${ingr.amount}, ${ingr.units});
                                                </script>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <textarea class="form-control" name="description" id="editorTextArea">
                                                ${recipe.description}
                                        </textarea>
                                        <label for="editorTextArea">${describeRecipe}</label>
                                    </div>
                                    <div class="feedback">
                                        <p class="text-danger text-center" id="helpMessage">${message}</p>
                                    </div>
                                </div>
                                <div class="action-buttons">
                                    <button type="submit" name="save-changes" id="recipe-save-btn"
                                            class="btn btn-outline-success">${saveRecipe}
                                    </button>
                                    <c:if test="${recipe != null}">
                                        <button type="button" id="recipe-delete-btn" name="delete"
                                                data-id="${recipe.id}"
                                                class="btn btn-outline-danger ms-2">${deleteRecipe}
                                        </button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
    <!-- recipe-content -->

</t:layout>