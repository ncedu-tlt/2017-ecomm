<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui hidden divider"></div>
<div class="ui text two column grid container main-content reviews jsReviewsComponent">
    <div class="ui top attached menu">
        <div class="item">
            <i class="comments icon large"></i>
        </div>
        <a class="item">Reviews</a>
    </div>
    <div class="ui bottom attached active segment">
        <c:forEach var="review" items="${requestScope.reviews}">
            <div class="ui grid<c:if test="${requestScope.userIdBySession == review.getUserId()}"> jsThisUserReview" data-value="${review.getProductId()}</c:if>">
                <div class="three wide column">
                    <img src="${pageContext.request.contextPath}${review.getUserAvatarLink()}" alt="avatar"
                         class="ui image small">
                </div>
                <div class="eleven wide column jsReviewParent">
                    <div class="ui grid four column">
                        <div class="column">
                            <a href="${pageContext.request.contextPath}/profile?user_id=${review.getUserId()}">
                                <h4>${review.getUserName()}</h4>
                            </a>
                        </div>
                        <div class="column">
                            <span class="datePrint">${review.getReviewDate()}</span>
                        </div>
                        <div class="right floated column">
                            <c:if test="${requestScope.userIdBySession == review.getUserId()}">
                                <span class="mini ui thisUserReview black label" data-value="review.getUserId()">This is your review</span>
                            </c:if>
                        </div>
                    </div>
                    <div class="ui grid one column jsReviewData">
                        <div class="ui grid one column">
                            <div class="column">
                            <span class="ui blue label">
                                Rating from user:
                            </span>
                                <span class="ui jsUsersRating eleven wide column large rating disabled"
                                      data-rating="${review.getRating()}"
                                      data-max-rating="5">
                            </span>
                            </div>
                        </div>
                        <div class="ui grid one column">
                            <div class="column">
                                <p class="jsReview">${review.getDescription()}</p>
                            </div>
                        </div>
                        <div class="ui grid one column">
                            <div class="column">
                                <c:if test="${requestScope.userIdBySession == review.getUserId()}">
                                    <div class="ui mini buttons right floated">
                                        <button class="ui jsRemoveButton button">
                                            <i class="trash outline icon"></i>
                                            Remove
                                        </button>
                                        <form method="post" action="review"
                                              class="ui fluid jsRemoveForm popup top left transition hidden">
                                            <input name="userId" type="hidden" value="${sessionScope.userId}">
                                            <input name="productId" type="hidden" value="${product.getId()}">
                                            <input name="reviewActions" type="hidden" value="remove">
                                            <div class="ui column divided center aligned grid">
                                                <div class="column">
                                                    <p>Remove a review?</p>
                                                    <div class="actions">
                                                        <button type="button" class="ui jsNegative button">
                                                            No
                                                        </button>
                                                        <button class="ui positive right labeled icon button">
                                                            Yes
                                                            <i class="checkmark icon"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <div class="or"></div>
                                        <button class="ui jsEdit button">
                                            <i class="icon write"></i>
                                            Edit
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ui divider"></div>
        </c:forEach>
        <c:if test="${requestScope.hasReview}">
            <form class="ui form" method="post" action="${pageContext.request.contextPath}/review">
                <input name="userId" type="hidden" value="${sessionScope.userId}">
                <input name="productId" type="hidden" value="${product.getId()}">
                <input class="jsRating" name="rating" type="hidden" value="2">
                <input name="reviewActions" type="hidden" value="add">
                <div class="field">
                    <label>Write your review:</label>
                    <span class="ui huge jsUserReviewRating rating"></span>
                    <textarea name="review"
                              title="review"></textarea>
                </div>
                <button class="positive ui mini button">Add review</button>
            </form>
        </c:if>
    </div>
</div>

<%-- JS controller initilization --%>
<script type="text/javascript">
    window.frm.components.init('productReviews', '.jsReviewsComponent');
</script>