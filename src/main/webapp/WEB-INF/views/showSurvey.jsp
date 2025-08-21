<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="survey" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="pl">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    html, body {
      margin: 0;
      height: 100%;
    }

    .contentCentered {
      display: flex;
      justify-content: center; /* środek w poziomie */
      align-items: center;     /* środek w pionie */
      height: 100vh;           /* pełna wysokość okna */
      text-align: center;
    }


    a {
      -webkit-border-radius: 4px;
          -moz-border-radius: 4px;
          border-radius: 4px;
          border: solid 1px #20538D;
          text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);
          -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);
          -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);
          box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);
          background: #4479BA;
          color: #FFF;
          padding: 8px 12px;
          text-decoration: none;
    }
  </style>
</head>



<body>
  <div class="contentCentered">
  <div>
    <h1>Ankieta do teleturnieju Kłamczuch</h1>
    <p>Wraz ze znajomymi chcemy zrobić teleturniej Kłamczuch. Aby do tego doszło potrzebujemy odpowiedzi ankietowanych.</p>
    <p>Dziękuję za pomoc w wypełnieniu ankiety.</p>
    <a href="/survey">Przejdź do ankiety</a>
  </div>
  </div>
</body>
</html>