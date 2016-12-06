<div class="ui container jsProductListComponent" style="margin: 1.5em 0;">
    <h2 class="ui dividing header">Ultrabooks</h2>
    <div class="ui grid">
        <div class="three column row">
            <div class="column">
                <img class="ui fluid image" src="http://semantic-ui.com/images/wireframe/image.png">
                <h3 class="ui center aligned header">
                    MacBook Air
                </h3>
                <div class="ui center aligned massive rating"  data-rating="1"></div>
                <h3 class="ui center aligned grey header">
                    Out of Stock
                </h3>
                <button class="ui fluid secondary basic button">Add to card</button>
            </div>
            <div class="column">
                <img class="ui fluid image" src="http://semantic-ui.com/images/wireframe/image.png">
                <h3 class="ui center aligned header">
                    Dell XPS 13
                </h3>
                <div class="ui center aligned massive rating"  data-rating="1"></div>
                <h3 class="ui center aligned grey header">
                    $750.00
                    <div class="ui left pointing label" style="text-decoration: line-through">
                        $819.00
                    </div>
                </h3>
                <button class="ui fluid secondary basic button">Add to card</button>
            </div>
            <div class="column">
                <img class="ui fluid image" src="http://semantic-ui.com/images/wireframe/image.png">
                <h3 class="ui center aligned header">
                    HP Spectre 13
                </h3>
                <div class="ui center aligned massive rating"  data-rating="1"></div>
                <h3 class="ui center aligned grey header">
                    $890.00
                </h3>
                <button class="ui fluid secondary basic button">Add to card</button>
            </div>
        </div>
    </div>
    <h2 class="ui dividing header">Gaming</h2>
    <div class="ui grid">
        <div class="three column row">
            <div class="column">
                <img class="ui fluid image" src="http://semantic-ui.com/images/wireframe/image.png">
                <h3 class="ui center aligned header">
                    ASUS ROG Strix
                </h3>
                <div class="ui center aligned massive rating" data-rating="1"></div>
                <h3 class="ui center aligned grey header">
                    $999.00
                </h3>
                <button class="ui fluid secondary basic button">Add to card</button>
            </div>
            <div class="column">
                <img class="ui fluid image" src="http://semantic-ui.com/images/wireframe/image.png">
                <h3 class="ui center aligned header">
                    MSI Ghost Pro
                </h3>
                <div class="ui center aligned massive rating"  data-rating="1"></div>
                <h3 class="ui center aligned grey header">
                    $750.00
                    <div class="ui left pointing label" style="text-decoration: line-through">
                        $819.00
                    </div>
                </h3>
                <button class="ui fluid secondary basic button">Add to card</button>
            </div>
            <div class="column">
                <img class="ui fluid image" src="http://semantic-ui.com/images/wireframe/image.png">
                <h3 class="ui center aligned header">
                    HP Omen 17
                </h3>
                <div class="ui center aligned massive rating" data-rating="1"></div>
                <h3 class="ui center aligned grey header">
                    $1099.00
                </h3>
                <button class="ui fluid secondary basic button">Add to card</button>
            </div>
        </div>
    </div>
</div>
<script>
    /* TODO: remove later and init component from source js */
    $('.rating')
            .rating({
                initialRating: 2,
                maxRating: 5
            });
    window.frm.components.init('ProductListComponent', '.jsProductListComponent');
</script>