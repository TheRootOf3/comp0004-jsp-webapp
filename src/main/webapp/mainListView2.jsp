<%@ page import="java.util.List" %>
<%@ page import="comp0004.model.element.ElementList" %>
<%@ page import="comp0004.model.element.Element" %>
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
    <div class="main">
        <%ElementList mainList = (ElementList) request.getAttribute("main_list");%>
        <div class="row label themed-container text-center">
            <h2><%=mainList.getLabel()%>
            </h2>
        </div>
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
                    <input type="submit" class="btn btn-primary " value="Delete list">
                </form>
            </div>
        </div>
        <% } %>
    </div>
    <hr>
    <div class="adding-section">
        <form action="addElement.html" method="POST">
            <h2>Add new list:</h2>
            List label: <input type="text" name="element_label" required>
            <input type="hidden" name="type" value="list">
            <input type="hidden" name="list" value="<%=mainList.getID()%>">
            <input type="submit" value="Add list"/>
        </form>
    </div>


    <hr>
    <div class="search-section">
        <h2>Search in element labels</h2>
        <form action="searchInLabels.html" method="POST">
            Keyword to search: <input type="text" name="keyword_to_search" required>
            <input type="hidden" name="list" value="<%=mainList.getID()%>">
            <input type="submit" value="Search!">
        </form>
    </div>

    <hr>
    <div class="saving-section">
        <h2>Settings</h2>


        <%
            String autosaveState = "";
            String radio1_state = "checked";
            String radio2_state = "";
            if (request.getAttribute("autosave").equals("true"))
                autosaveState = "On.";
            else {
                autosaveState = "Off.";
                radio1_state = "";
                radio2_state = "checked";
            }
        %>
        Auto save: <%=autosaveState%>
        <form action="setAutoSave.html" method="POST">
            <input type="hidden" name="list" value="<%=mainList.getID()%>">
            <input type="radio" name="autosave_state" value="yes" <%=radio1_state%>> On
            <input type="radio" name="autosave_state" value="no" <%=radio2_state%>> Off
            <input type="submit" name="set_autosave" value="Set">
        </form>

        <form action="saveAll.html" method="POST">
            <input type="hidden" name="list" value="<%=mainList.getID()%>">
            <input type="submit" name="save" value="Save all!">
        </form>
        <form action="loadLastSave.html" method="POST">
            <input type="hidden" name="list" value="<%=mainList.getID()%>">
            <input type="submit" name="save" value="Load last save!">
        </form>
    </div>
    <hr>
    <a href="index.html">Go back to the main page.</a>

</div>
</body>
</html>
