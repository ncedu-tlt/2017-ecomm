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
        <button class="ui mini green right floated jsEdit button">
            <i class="checkmark icon"></i>
            Confirm
        </button>
    </div>
</form>
<script type="text/javascript">
    window.frm.components.init('ReviewData', '.jsReviewData');
</script>