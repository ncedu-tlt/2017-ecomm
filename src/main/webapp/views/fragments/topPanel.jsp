<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Used components:
    - categories
    - search
    - profileIcon
    - shoppingCartIcon
--%>

<div class="ui text menu">
    <div class="ui container">
        <a href="#" class="header item">Shop</a>
        <c:import url="../components/categories.jsp"/>
        <c:import url="../components/search.jsp"/>
    </div>
</div>
<script>
    /* TODO: remove later and init component from source js */
    $('.categories  ')
            .popup({
                popup: '.popup',
                inline: true,
                position: 'bottom left',
                hoverable: true
            });
</script>