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
        <%ElementList elementList = (ElementList) request.getAttribute("list");%>
        <h2><%=elementList.getLabel()%>
        </h2>
        <div class="row" style="font-weight: bold">
            <div class="col-md-8 themed-grid-col ">
                Element name
            </div>
            <div class="col-md-2 themed-grid-col">
                Element contents
            </div>
            <div class="col-md-2 themed-grid-col">
                Actions
            </div>
        </div>
        <%
            for (Element element : elementList.getElementList()) {
                String href = "#";
                StringBuilder type = new StringBuilder();
                if (element.getType().equals("list")) {
                    href = "itemListView.html?list=" + element.getID();
                    type = new StringBuilder("list (" + ((ElementList) element).getElementList().size() + " elements)");
                } else {
                    href = "itemListView.html?list=" + element.getID();
                    for (Element thing : ((ElementList) element).getElementList()) {
                        type.append(thing.getType()).append(", ");
                    }
                    if (!type.isEmpty())
                        type.delete(type.length() - 2, type.length());
                    else
                        type.append("empty item");
                }
                //        System.out.println(href);
        %>
        <div class="row ">
            <div class="col-md-8 themed-grid-col">
                <a href="<%=href%>"><%=element.getLabel()%>
                </a>
            </div>
            <div class="col-md-2 themed-grid-col">
                <%=type%>
            </div>
            <div class="col-md-2 themed-grid-col text-center">
                <form action="deleteElement.html" method="POST">
                    <input type="hidden" name="item_to_delete" value="<%=element.getID()%>">
                    <input type="hidden" name="list" value="<%=elementList.getID()%>">
                    <input type="submit" class="btn btn-primary " value="Delete item">
                </form>
            </div>
        </div>
        <% } %>
    </div>
    <div class="adding-section">
        <form action="addElement.html" method="POST">
            <p>Add new element:</p>
            Element label: <input type="text" name="element_label">
            <input type="radio" name="type" value="list"> List
            <input type="radio" name="type" value="item" checked> Item
            <input type="hidden" name="list" value="<%=elementList.getID()%>">
            <input type="submit" value="Add element"/>
        </form>
    </div>
    <%
        ElementList backList = (ElementList) elementList.getParent();

        String hrefBack = "#";
        if (backList.getID() == 0)
            hrefBack = "mainListView2.html";
        else
            hrefBack = "itemListView.html?list=" + backList.getID();
    %>
    <a href="<%=hrefBack%>">
        Back to the previous list: <%=backList.getLabel()%>
    </a>
    <%--  <jsp:include page="/footer.jsp"/>--%>
</div>
</body>
</html>
