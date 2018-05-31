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
  if ( get_application == null ) {
  %>
    <%= get_application %>
  <%
  } else if ( get_application == "" ) {
  %>
    <h3>Field Application can not be blank!</h3>
    <%= get_application %>
  <%
  } else {
  %>
    <jsp:forward page='Applications'>
    <jsp:param name='application' value="<%= get_application %>" />
    <jsp:param name='id' value="<%= get_id %>"  />
    </jsp:forward> 
  <%
  }
  %>
</body>
</html>



