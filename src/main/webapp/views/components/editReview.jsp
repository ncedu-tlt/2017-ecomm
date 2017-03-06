<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="review" scope="page" value="${session.getAttribute('review')}"/>

<form method="post" action="review" class="ui form grid one column jsReviewEditData" style="display: none">
    <input name="productId" type="hidden" value="${review.productId}">
    <input class="jsEditRating" name="rating" type="hidden" value="${review.rating}">
    <input name="reviewActions" type="hidden" value="update">
    <div class="ui grid one column">
        <div class="column">
            <span class="ui blue label">
                New rating:
            </span>
            <span class="ui jsEditRating eleven wide column large rating"
                  data-rating="${review.rating}"
                  data-max-rating="5">
            </span>
        </div>
    </div>
    <div class="ui grid one column">
        <textarea class="editTextArea" name="reviewDescription" class="column jsReview">${review.description}</textarea>
    </div>
    <div class="ui grid one column">
        <div class="ui right floated mini buttons">
            <button class="ui button jsCancel" type="button">Cancel</button>
            <div class="or"></div>
            <button class="ui mini green jsEdit button">
                <i class="checkmark icon"></i>
                Confirm
            </button>
        </div>
    </div>
</form>