<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form Page</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/main"  enctype="multipart/form-data">
        <label>
            DOM-parser: <input type="radio" name="radios" value="dom"/>
        </label>
        <br>
        <label>
            SAX-parser :<input type="radio" name="radios" value="sax"/>
        </label>
        <br>
        <label>
            StAX-parser :  <input type="radio" name="radios" value="stax"/>
        </label>
        <br>
        Select File:<input type="file" name="file" required="required"/><br/>
        <input type="submit" value="Check"/>
</form>
</body>
</html>
