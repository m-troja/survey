<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="survey" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Formularz Kłamczuch</title>

	<survey:css-import/>
	<survey:script-import/>

</head>
<body>

<h2>Formularz pytań do Kłamczucha :)</h2>

<form:form method="post" modelAttribute="survey" action="/survey">
    
    <c:forEach var="question" items="${survey.questions}" varStatus="surveyStatus">
        <div class="question-block" id="question-${surveyStatus.index}">
                <strong>Pytanie ${surveyStatus.index + 1}: ${question.text}</strong>
                <form:hidden path="questions[${surveyStatus.index}].text"/>
                <form:hidden path="questions[${surveyStatus.index}].id"/>

			<div class="answers-container" id="answers-container-${surveyStatus.index}">
			    <c:forEach begin="0" end="2" var="aIndex">
			        <form:input path="questions[${surveyStatus.index}].answers[${aIndex}].text" maxlength="20" placeholder="Odpowiedź ${aIndex + 1}" />
			        <form:hidden path="questions[${surveyStatus.index}].answers[${aIndex}].id"/>
			    </c:forEach>
			    
			    <button type="button" onclick="addAnswerInput(${surveyStatus.index})">Dodaj odpowiedź</button>
			</div>

         </div>
    </c:forEach>
            
    <button type="submit">Wyślij</button>
</form:form>

	<c:if test="${message != null}">
		<div>
			${message}
		</div>
	</c:if>
</body>
</html>
