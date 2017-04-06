<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ui two column centered grid jsProfileComponent">
    <div class="column">
        <div class="ui grid">
            <div class="four wide column">
                <div class="ui special cards">
                    <div class="card">
                        <div class="blurring image jsUserAvatar">
                            <img class="ui small rounded image bordered" src="${requestScope.avatar}"
                                 alt="avatar">
                        </div>
                        <c:set var="firstName" scope="session" value="${requestScope.firstName}"/>
                        <c:set var="lastName" scope="session" value="${requestScope.lastName}"/>
                        <c:if test="${firstName != '' || lastName != ''}">
                            <div class="content">
                                <a class="header">${firstName} ${lastName}</a>
                            </div>
                        </c:if>
                        <div class="extra content">
                            <p>
                                <i class="at icon"></i>
                                Email: ${requestScope.email}
                            </p>
                            <c:set var="phone" scope="session" value="${requestScope.phone}"/>
                            <c:if test="${phone != ''}">
                                <p>
                                    <i class="volume control phone icon"></i>
                                    Phone: ${phone}
                                </p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="nine wide column">
                <div class="ui aligned piled text container segment" data-tab="profile">
                    <form class="ui form jsProfileForm" method="post"
                          action="${pageContext.request.contextPath}/profile">
                        <div class="field">
                            <label for="firstName">First Name:</label>
                            <input type="text" name="firstName" placeholder="First Name" id="firstName"
                                   class="jsProfileTextParam" value="${requestScope.firstName}"/>
                        </div>
                        <div class="field">
                            <label for="lastName">Last Name:</label>
                            <input type="text" name="lastName" placeholder="Last Name" id="lastName"
                                   class="jsProfileTextParam" value="${requestScope.lastName}"/>
                        </div>
                        <div class="field">
                            <label for="email">Email:</label>
                            <input type="text" name="email" placeholder="E-mail" id="email"
                                   class="jsProfileTextParam" value="${requestScope.email}"/>
                        </div>
                        <div class="field">
                            <label for="phone">Phone:</label>
                            <input type="tel" name="phone" placeholder="Phone" id="phone"
                                   class="jsProfileTextParam" value="${requestScope.phone}"/>
                        </div>
                        <div class="field jsPasswordFields">
                            <label for="password">Password:</label>
                            <input type="password" name="password" placeholder="Password" id="password"
                                   class="jsPassword jsProfileTextParam">
                        </div>
                        <div class="field jsPasswordFields">
                            <label for="passwordConfirm">Password confirm:</label>
                            <input type="password" name="passwordConfirm" placeholder="Current Password"
                                   id="passwordConfirm" class="jsPasswordConfirm jsProfileTextParam">
                        </div>
                        <div class="field jsPasswordFields">
                            <label for="passwordConfirm">Old password:</label>
                            <input type="password" name="oldPassword" placeholder="Old Password"
                                   id="oldPassword" class="jsOldPassword jsProfileTextParam">
                        </div>
                        <button class="ui primary button jsSendFormProfileBtn">
                            <div class="content">Save</div>
                        </button>
                        <button type="button" class="positive ui button jsPasswordShowBtn">Change Password</button>
                    </form>
                    <c:set var="answer" scope="session" value="${requestScope.answer}"/>
                    <c:if test="${answer != null}">
                        <c:choose>
                            <c:when test='${answer == "Success"}'>
                                <div class="ui positive message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Success!
                                    </div>
                                    <p>Your profile was change successful!</p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorInputEmail"}'>
                                <div class="ui negative  message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Error typing email!
                                    </div>
                                    <p>Your email has been entered incorrectly.</p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorDiffersEmail"}'>
                                <div class="ui negative message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Error typing email!
                                    </div>
                                    <p>This email already exists</p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorInputPassword"}'>
                                <div class="ui negative  message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Password failed!
                                    </div>
                                    <p>
                                        Your password must contain uppercase, or
                                        lowercase Latin characters or Cyrillic
                                        (special characters: @ $ _) and be a length of 4 to 20 characters.
                                    </p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorInputPhone"}'>
                                <div class="ui negative  message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Phone failed!
                                    </div>
                                    <p>Phone input error</p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorInputFirstName"}'>
                                <div class="ui negative  message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Error entering first name!
                                    </div>
                                    <p>
                                        Your first name must contain Latin characters or
                                        Cyrillic characters and be from 2 to 20 characters in length.
                                    </p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorInputLastName"}'>
                                <div class="ui negative message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Error entering last name!
                                    </div>
                                    <p>
                                        Your last name must contain Latin characters or
                                        Cyrillic characters and be from 2 to 20 characters in length.
                                    </p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorUpdate"}'>
                                <div class="ui negative message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Profile change error!
                                    </div>
                                    <p>Please try again later.</p>
                                </div>
                            </c:when>
                            <c:when test='${answer == "ErrorInputOldPassword"}'>
                                <div class="ui negative message jsMessageFromServlet">
                                    <i class="close icon jsIconCloseMessageFromServlet"></i>
                                    <div class="header">
                                        Error entering old password!
                                    </div>
                                    <p>Please enter a valid password.</p>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:if>
                    <div class="ui page dimmer jsDimmerProfile">
                        <div class="content">
                            <div class="ui text loader">Changing Profile</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    window.frm.components.init('ProfileComponent', '.jsProfileComponent');
</script>