<!DOCTYPE html>
<html lang="en">
<head>
    <!--  AUI Core -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
    <script src="//aui-cdn.atlassian.com/aui-adg/6.0.9/js/aui.min.js"></script>
    <link rel="stylesheet" href="//aui-cdn.atlassian.com/aui-adg/6.0.9/css/aui.min.css" media="all">

    <!-- AUI Experimental components -->
    <link rel="stylesheet" href="//aui-cdn.atlassian.com/aui-adg/6.0.9/css/aui-experimental.min.css" media="all">
    <script src="//aui-cdn.atlassian.com/aui-adg/6.0.9/js/aui-experimental.min.js"></script>

    <!-- AUI Datepicker and Soy templates -->
    <script src="//aui-cdn.atlassian.com/aui-adg/6.0.9/js/aui-datepicker.min.js"></script>
    <script src="//aui-cdn.atlassian.com/aui-adg/6.0.9/js/aui-soy.min.js"></script>
</head>
<body>
<form action="/save-configuration" method="post">
    <table>
        <tr>
            <label for="projectKey">Project Key</label>
            <input type="text" name="projectKey" id="projectKey" value="${(ctProjectCredentials.projectKey)!}" required autofocus/>
        </tr>
        <tr>
            <label for="clientId">Client Id</label>
            <input type="text" name="clientId" id="clientId" value="${(ctProjectCredentials.clientId)!}" required autofocus/>
        </tr>
        <tr>
            <label for="clientSecret">Client Secret</label>
            <input type="text" name="clientSecret" id="clientSecret" value="${(ctProjectCredentials.clientSecret)!}" required autofocus/>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>


</form>
</body>
</html>

