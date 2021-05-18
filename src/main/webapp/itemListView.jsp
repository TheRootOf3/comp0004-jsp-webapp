<%@ page import="uk.ac.ucl.model.element.ElementList" %>
<%@ page import="uk.ac.ucl.model.element.Element" %>
<%@ page import="uk.ac.ucl.model.element.Thing" %>
<%@ page import="java.util.ArrayList" %>
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
        <jsp:include page="/renameLabel.jsp"/>
<%--        TODO Create nice layout of items--%>
        <%
            ElementList elementList = (ElementList) request.getAttribute("list");
        %>
        <h3>Text Elements</h3>
        <% int id = 0;
            for (Element thing : elementList.getElementList()) {
                if (thing.getLabel().equals("text")) {
                    String text = ((Thing) thing).getContent();
        %>

        <div id="text_edit_<%=id%>" contenteditable="false" style="white-space: pre-line"><%=text%>
        </div>
        <button id="button_edit_<%=id%>" onclick="onClickEdit(<%=id%>)">Edit text</button>
        <button type="submit" form="form_delete_<%=id%>" id="button_delete_<%=id%>">Delete</button>
        <button type="submit" form="form_edit_<%=id%>" id="button_yes_<%=id%>" style="display: none"
                onclick="onClickYes(<%=id%>)">Accept changes
        </button>
        <button id="button_no_<%=id%>" style="display: none" onclick="onClickNo(<%=id%>)">Cancel changes</button>

        <form id="form_edit_<%=id%>" action="editThing.html" method="POST">
            <input type="hidden" name="list" value="<%=elementList.getID()%>">
            <input type="hidden" name="thing" value="<%=thing.getID()%>">
            <input type="hidden" id="thing_content_<%=id%>" name="thing_content" value="">
        </form>

        <form id="form_delete_<%=id%>" action="deleteThing.html" method="POST">
            <input type="hidden" name="list" value="<%=elementList.getID()%>">
            <input type="hidden" name="thing" value="<%=thing.getID()%>">
        </form>

        <script>
            var textBeforeChanges;

            function onClickEdit(id) {
                textBeforeChanges = document.getElementById("text_edit_" + id.toString()).innerText;
                var text_edit = document.getElementById("text_edit_" + id.toString());
                if (text_edit.contentEditable === "false") {
                    text_edit.setAttribute("contenteditable", "true");
                    document.getElementById("button_edit_" + id.toString()).setAttribute("style", "display: none;");
                    document.getElementById("button_delete_" + id.toString()).setAttribute("style", "display: none;");
                    document.getElementById("button_yes_" + id.toString()).setAttribute("style", "");
                    document.getElementById("button_no_" + id.toString()).setAttribute("style", "");
                }
            }

            function onClickYes(id) {
                document.getElementById("thing_content_" + id.toString()).value = document.getElementById("text_edit_" + id.toString()).innerText
            }

            function onClickNo(id) {
                document.getElementById("button_yes_" + id.toString()).setAttribute("style", "display: none;");
                document.getElementById("button_no_" + id.toString()).setAttribute("style", "display: none;");
                document.getElementById("button_edit_" + id.toString()).setAttribute("style", "");
                document.getElementById("button_delete_" + id.toString()).setAttribute("style", "");
                document.getElementById("text_edit_" + id.toString()).innerText = textBeforeChanges;
                document.getElementById("text_edit_" + id.toString()).contentEditable = "false";
            }
        </script>
        <%
                }
                id++;
            }
        %>

        <h3>Urls</h3>
        <%
            for (Element thing : elementList.getElementList()) {
                if (thing.getLabel().equals("url")) {
                    String url = ((Thing) thing).getContent();
        %>
        <p class=""><a href="<%=url%>"><%=url%>
        </a></p>

        <form action="deleteThing.html" method="POST">
            <input type="hidden" name="list" value="<%=elementList.getID()%>">
            <input type="hidden" name="thing" value="<%=thing.getID()%>">
            <input type="submit" value="Delete">
        </form>
        <%
                }
            }
        %>

        <div class="adding-section">
            <form action="addThing.html" method="POST">
                <p>Add new thing:</p>
                Thing content: <input type="text" name="thing_content">
                <input type="radio" name="type" value="text" checked> Text
                <input type="radio" name="type" value="url"> URL
                <input type="hidden" name="list" value="<%=elementList.getID()%>">
                <input type="submit" value="Add thing"/>
            </form>
        </div>


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
                if (element.getType().equals("list") || element.getType().equals("item")) {
                    String href = "itemListView.html?list=" + element.getID();
                    StringBuilder type = new StringBuilder();
                    if (element.getType().equals("list")) {
//                    href = "listView.html?list=" + element.getID();
                        type = new StringBuilder("list (" + ((ElementList) element).getElementList().size() + " elements)");
                    } else if (element.getType().equals("item")) {
//                    href = "itemView.html?item=" + element.getID();
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
        <%
                }
            }
        %>
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

    <%--    <a href="itemListView.html?list=<%=((ElementList)request.getAttribute("parent_list")).getID()%>">--%>
    <%--        Back to the list: <%=((ElementList)request.getAttribute("parent_list")).getLabel()%></a>--%>
    <%--<jsp:include page="/footer.jsp"/>--%>
</div>
</body>
</html>
