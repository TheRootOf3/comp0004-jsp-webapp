<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.element.ElementList" %>
<%@ page import="uk.ac.ucl.model.element.Element" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>

    <style>
        .themed-grid-col {
            padding-top: .75rem;
            padding-bottom: .75rem;
            background-color: rgba(86, 61, 124, .15);
            border: 1px solid rgba(86, 61, 124, .2);
        }

        .themed-container {
            padding: .75rem;
            margin-bottom: 1.5rem;
            background-color: rgba(0, 123, 255, .15);
            border: 1px solid rgba(0, 123, 255, .2);
        }
    </style>

</head>
<body>
<div class="container">
    <jsp:include page="/header.jsp"/>
    <div class="main">
        <%ElementList mainList = (ElementList) request.getAttribute("main_list");%>
        <h2><%=mainList.getLabel()%>
        </h2>
        <div class="row" style="font-weight: bold">
            <div class="col-md-8 themed-grid-col ">
                List name
            </div>
            <div class="col-md-2 themed-grid-col">
                Number of elements
            </div>
            <div class="col-md-2 themed-grid-col">
                Actions
            </div>
        </div>
        <%
            //      ElementList mainList = (ElementList) request.getAttribute("main_list");
            for (Element elementList : mainList.getElementList()) {
                String href = "itemListView.html?" + "list=" + elementList.getID();
        %>
        <div class="row ">
            <div class="col-md-8 themed-grid-col">
                <a href="<%=href%>"><%=elementList.getLabel()%>
                </a>
            </div>
            <div class="col-md-2 themed-grid-col">
                <%=((ElementList) elementList).getElementList().size()%>
            </div>
            <div class="col-md-2 themed-grid-col text-center">
                <form action="deleteElement.html" method="POST">
                    <input type="hidden" name="item_to_delete" value="<%=elementList.getID()%>">
                    <input type="hidden" name="list" value="<%=mainList.getID()%>">
                    <input type="submit" class="btn btn-primary " value="Delete item">
                </form>
            </div>
        </div>
        <% } %>
    </div>
    <div class="adding-section">
        <form action="addElement.html" method="POST">
            <p>Add new list:</p>
            List label: <input type="text" name="element_label">
            <input type="hidden" name="type" value="list">
            <input type="hidden" name="list" value="<%=mainList.getID()%>">
            <input type="submit" value="Add list"/>
        </form>
    </div>

    <%--  <jsp:include page="/footer.jsp"/>--%>
</div>
</body>
</html>
