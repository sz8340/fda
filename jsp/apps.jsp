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
  if ( get_application != null ) {
  %>
    <h3>Forwarding...</h3>
    <jsp:forward page='Applications'>
    <jsp:param name='application' value="get_application" />
    <jsp:param name='id' value="12"  />
    </jsp:forward> 
  <%
  } else {
  %>
    <h3>You have selected null</h3>
  <%
  }
  %>
</body>
</html>
