<form method="post" action="review" class="ui form grid one column jsReviewData">
    <input type="hidden" class="productId" name="productId" value="0"/>
    <input class="jsEditRating" name="rating" type="hidden" value="2">
    <input name="reviewActions" type="hidden" value="update">
    <div class="ui grid one column">
        <div class="column">
            <span class="ui blue label">
                New rating:
            </span>
            <span class="ui large jsEditUserReviewRating rating" data-rating="" data-max-rating="5">

            </span>
        </div>
    </div>
    <div class="ui grid one column">
        <textarea class="editTextArea" name="reviewDescription" class="column jsReview">
        </textarea>
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