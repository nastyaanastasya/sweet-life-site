<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="settings"><spring:message code="site.settings"/></c:set>
<c:set var="username"><spring:message code="user.username.title"/></c:set>
<c:set var="usernamePlaceholder"><spring:message code="user.username.placeholder"/></c:set>
<c:set var="currentPassword"><spring:message code="user.password.title"/></c:set>
<c:set var="newPassword"><spring:message code="user.newPassword.title"/></c:set>
<c:set var="repeatPassword"><spring:message code="user.repPassword.title"/></c:set>
<c:set var="save"><spring:message code="button.save"/></c:set>

<t:layout title="${settings}">

    <script src="<spring:url value="/js/settingsActions.js"/>"></script>

    <!-- settings-content -->
    <div class="settings-content">
        <div class="container">
            <form:form modelAttribute="form" method="post" enctype="multipart/form-data">
                <div class="col d-flex justify-content-center">
                    <div class="editing">
                        <div class="col mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="editing-profile">
                                        <div class="row">
                                            <div class="col-12 col-sm-auto mb-3">
                                                <div class="mx-auto">
                                                    <c:choose>
                                                        <c:when test="${user.profileImage != null}">
                                                            <img src="<spring:url value="/media/${user.profileImage.name}"/>"
                                                                 class="profile-image" id="image-changed"
                                                                 alt="profile-image">
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="<spring:url value="/img/user_default.png"/>"
                                                                 class="profile-image" id="image-changed"
                                                                 alt="profile-image">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                            <div class="col d-flex flex-column flex-sm-row justify-content-between mb-3">
                                                <div class="text-sm-left mb-3 mb-sm-0">
                                                    <h4 class="pt-sm-2 pb-1 mb-3 text-nowrap">${user.nickname}</h4>
                                                    <div class="mt-3">
                                                        <input class="form-control" type="file" name="file"
                                                               aria-label="Select file" id="file-input-settings"
                                                               accept="image/*">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tab-content pt-3">
                                            <div class="tab-pane active">
                                                <div class="row">
                                                    <div class="col">
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>${username}</label>
                                                                    <input class="form-control" type="text"
                                                                           name="nickname" value="${user.nickname}"
                                                                           placeholder="${usernamePlaceholder}">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col col-sm-6 mb-3">
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>${currentPassword}</label>
                                                                    <input class="form-control" name="currentPassword"
                                                                           type="password">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label>${newPassword}</label>
                                                                    <input class="form-control" name="newPassword"
                                                                           type="password">
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col">
                                                                <div class="form-group">
                                                                    <label><span
                                                                            class="d-none d-xl-inline">${repeatPassword}</span></label>
                                                                    <input class="form-control" name="repPassword"
                                                                           type="password">
                                                                </div>
                                                                    <%--                                                                <p class="text-danger text-center"--%>
                                                                    <%--                                                                   id="helpMessage">${message}</p>--%>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col d-flex justify-content-end">
                                                        <button class="btn btn-outline-secondary ms-2"
                                                                id="save-changes-btn"
                                                                name="save-changes" type="submit">
                                                                ${save}
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
    <!-- settings-content -->

</t:layout>