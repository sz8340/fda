<html>
<head>
    <title>Applications</title>
</head>
<body>
    <form method="get">
        Application: <input type="text" size="20" name="application"/> <br>
        ID <input type="text" size="20" name="id"/> <br>
        <input type="submit" value="Add" />
    </form>

  <%
  String get_application = request.getParameter("application");
  String get_id = request.getParameter("id");
  %>
        <li><%= get_application %></li>
        <li><%= get_id %></li>
  <%
  //if (get_application == null || get_id == null) {
  if (get_application == "a" || get_id == "b") {
  %>
    <h3>Both fields must have some value! </h3>
    <ul>
  <%
  } else {
  %>
    <jsp:forward page='Applications'>
    <jsp:param name='application' value="get_application" />
    <jsp:param name='id' value="11"  />
    </jsp:forward> 
  <%
  }
  %>

</body>
</html>
  <h3>Choose an author:</h3>
  <form method="get">
    <input type="checkbox" name="author" value="Tan Ah Teck" checked>Tan
    <input type="checkbox" name="author" value="Mohd Ali">Ali
    <input type="checkbox" name="author" value="Kumar">Kumar
    <input type="submit" value="Query">
  </form>
 
  <%
  String[] authors = request.getParameterValues("author");
  if (authors != null) {
  %>
    <h3>You have selected author(s):</h3>
    <ul>
  <%
      for (int i = 0; i < authors.length; ++i) {
  %>
        <li><%= authors[i] %></li>
  <%
      }
  %>
    </ul>
    <a href="<%= request.getRequestURI() %>">BACK</a>
  <%
  }
  %>
</body>
</html>
