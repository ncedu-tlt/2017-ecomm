<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui text two column grid container main-content reviews jsReviewsComponent" style="margin-top: 2em">
    <div class="ui top attached menu">
        <div class="item">
            <i class="comments icon large"></i>
        </div>
        <a class="item">Reviews</a>
    </div>
    <div class="ui bottom attached active segment">
        <c:forEach var="review" items="${requestScope.reviews}">
            <div class="ui grid">
                <div class="three wide column">
                    <img src="${review.getUserAvatarLink()}" alt="avatar" class="ui image small">
                </div>
                <div class="eleven wide column">
                    <div class="ui grid four column">
                        <div class="column">
                            <a href="${pageContext.request.contextPath}/profile?user_id=${review.getUserId()}">
                                <h4>${review.getUserName()}</h4>
                            </a>
                        </div>
                        <div class=" column">
                            <span class="datePrint">${review.getReviewDate()}</span>
                        </div>
                    </div>
                    <div class="ui grid one column">
                        <div class="column">
                            <div class="ui eleven wide column large rating disabled" data-rating="${review.getRating()}"
                                 data-max-rating="5"></div>
                        </div>
                    </div>
                    <div class="ui grid one column">
                        <div class="column">
                            <p>${review.getDescription()}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ui divider"></div>
        </c:forEach>
    </div>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('productReviews', '.jsReviewsComponent');
</script>