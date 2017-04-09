<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ui container main-content jsProductComparator">

    <div class="ui grid centered container gap">
        <h2 class="ui center aligned header horizontal divider">
            Compare
        </h2>
        <div class="four wide column">
            <img class="ui fluid image" src="${pageContext.request.contextPath}/images/defaultimage/image.png">
            <h3 class="ui center aligned header">
                <a>
                    item
                </a>
            </h3>
            <div class="ui grid centered container">
                <div class="ui column centered grid massive rating disabled centered-rating" data-max-rating="5">
                    <i class="icon active"></i>
                    <i class="icon active"></i>
                    <i class="icon active"></i>
                    <i class="icon"></i>
                    <i class="icon"></i>
                </div>
            </div>

            <h3 class="ui center aligned header">
                <b>$100</b>
            </h3>
            <div class="centered-button">
                <div class="ui buttons">
                    <button class="ui labeled icon blue button">Add to cart</button>
                    <button class="ui icon red button"><i class="remove icon"></i></button>
                </div>
            </div>
        </div>
        <div class="four wide column">
            <img class="ui fluid image" src="${pageContext.request.contextPath}/images/defaultimage/image.png">
            <h3 class="ui center aligned header">
                <a>
                    item
                </a>
            </h3>
            <div class="ui grid centered container">
                <div class="ui column centered grid massive rating disabled centered-rating" data-max-rating="5">
                    <i class="icon active"></i>
                    <i class="icon active"></i>
                    <i class="icon active"></i>
                    <i class="icon"></i>
                    <i class="icon"></i>
                </div>
            </div>

            <h3 class="ui center aligned header">
                <b>$100</b>
            </h3>
            <div class="centered-button">
                <div class="ui buttons">
                    <button class="ui labeled icon blue button">Add to cart</button>
                    <button class="ui icon red button"><i class="remove icon"></i></button>
                </div>
            </div>
        </div>
        <div class="four wide column">
            <img class="ui fluid image" src="${pageContext.request.contextPath}/images/defaultimage/image.png">
            <h3 class="ui center aligned header">
                <a>
                    item
                </a>
            </h3>
            <div class="ui grid centered container">
                <div class="ui column centered grid massive rating disabled centered-rating" data-max-rating="5">
                    <i class="icon active"></i>
                    <i class="icon active"></i>
                    <i class="icon active"></i>
                    <i class="icon"></i>
                    <i class="icon"></i>
                </div>
            </div>

            <h3 class="ui center aligned header">
                <b>$100</b>
            </h3>
            <div class="centered-button">
                <div class="ui buttons">
                    <button class="ui labeled icon blue button">Add to cart</button>
                    <button class="ui icon red button"><i class="remove icon"></i></button>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="column">
                <button class="ui icon right floated red button">Clear all<i class="remove icon"></i></button>
            </div>
        </div>

        <h4 class="ui horizontal divider header">
            <i class="bar chart icon"></i>
            Specifications
        </h4>
        <div>
            <table class="ui definition table">
                <tbody>
                <tr>
                    <td class="two wide column">Characteristic group:</td>
                </tr>
                <tr>
                    <td>characteristic</td>
                    <td>text</td>
                    <td>text</td>
                    <td>text</td>
                </tr>
                <tr>
                    <td>characteristic</td>
                    <td>text</td>
                    <td>text</td>
                    <td>text</td>
                </tr>
                <tr>
                    <td>characteristic</td>
                    <td>text</td>
                    <td>text</td>
                    <td>text</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    window.frm.components.init('productComparator', '.jsProductComparator', {
        addToCompareUrl: '${requestScope.addToCartURL}'
    });
</script>
