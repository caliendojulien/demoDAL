<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.demo.demodal.bo.Voiture" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Voitures</title>
</head>
<body>
<%
    ArrayList<Voiture> toutesLesVoitures = (ArrayList<Voiture>) request.getAttribute("mesVoitures");
%>
    <%
    for (Voiture chaquevoiture : toutesLesVoitures) {
    %>
        <p><%= chaquevoiture.getMarque() %></p>
    <%
    }
    %>
</body>
</html>
