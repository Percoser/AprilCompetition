<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency Exchanger-Percoski, Eric</title>

    <style>
        div{
            float: left;
            display: inline-block;
        }
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
        <h2 style="float: left; padding-left: 40%">Exchange Rate API</h2>

</head>
<div>
    <div>
<body>
    <div>
<form method ="post" action="/push">
        <textarea rows="5" cols="10" name="price">${price}</textarea>
        <textarea rows="5" cols="10" name="timestamp">${timestamp}</textarea>
    <button type="submit" style="height: 35px; width: 100px;">SUBMIT AND LOAD DB</button>
</form>
    </div>
</body>
    </div>
<table>
    <tr>
        <th>Ask Price</th>
        <th>Timestamp</th>

    </tr>
    <c:forEach var="itemList" items="${itemList}">
        <tr>
            <td>${itemList.price}</td>
            <td>${itemList.timestamp}</td>


        </tr>
    </c:forEach>
</table>
</div>
</html>