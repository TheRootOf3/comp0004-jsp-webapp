<%@ page import="comp0004.model.element.ElementList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <jsp:include page="/meta.jsp"/>
    <title>List webapp</title>
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

    <h2>Search results</h2>
    <%
        ArrayList<ArrayList<ElementList>> traces = (ArrayList<ArrayList<ElementList>>) request.getAttribute("traces");
    %>
    <div class="row themed-container">
        <h3 class="text-center">Found <%=traces.size()%> results.</h3>
    </div>

    <div class="row fw-bold">
        <div class="col-md-6 themed-grid-col">
            Found element
        </div>
        <div class="col-md-6 themed-grid-col">
            Element trace
        </div>
    </div>

    <%
        for (ArrayList<ElementList> array : traces) {
    %>
    <div class="row">
        <div class="col-md-6 themed-grid-col">

            <h5><%=array.get(0).getLabel()%> - <%=array.get(0).getType()%>
        </div>

        <div class="col-md-6 themed-grid-col text-center">
            <%
                Collections.reverse(array);
                String href;
                String label = "";
                for (ElementList element : array) {
                    if (element.getID() == 0)
                        href = "mainListView.html";
                    else
                        href = "itemListView.html?list=" + element.getID();
                    if (element.getID() != array.get(array.size() - 1).getID())
                        label = " -> ";
                    else
                        label = "";
            %>

            <a href="<%=href%>"><%=element.getLabel()%>
            </a> <%=label%>
            <%

                }
            %>

        </div>

    </div>
    <%
        }
    %>

    <hr>
    <a href="mainListView.html">Go back to the main list.</a>

</div>
</body>
</html>
