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
