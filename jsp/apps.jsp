<html>
<head>
    <title>Applications</title>
</head>
<body>
    <form method="get">
        Application: <input type="text" size="20" name="application"> <br>
        ID <input type="text" size="20" name="id"> <br>
        <input type="submit" value="Add">
    </form>

  <%
  String get_application = request.getParameter("application");
  String get_id = request.getParameter("id");
  %>
    App:<%= get_application %>:ppA
  <%
  if ( get_application == null ) {
  %>
    <h3>You have selected null</h3>
    <%= get_application %>
  <%
  }
  %>
</body>
</html>
