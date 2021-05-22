<%@ page import="comp0004.model.element.ElementList" %>
<%@ page import="comp0004.model.element.Element" %>
<%@ page import="comp0004.model.element.thing.Thing" %>
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
    </style>

</head>
<body>
<div class="container">
    <jsp:include page="/header.jsp"/>
    <div class="main">
        <jsp:include page="/renameLabel.jsp"/>
        <%
            ElementList elementList = (ElementList) request.getAttribute("list");
        %>
        <div class="container border shadow">

            <h2 class="text-center">Text</h2>
            <% int id = 0;
                for (Element thing : elementList.getElementList()) {
                    if (thing.getLabel().equals("text")) {
                        String text = ((Thing) thing).getContent();
                        text = text.replaceAll("\n", "<br>");
            %>

            <div id="text_edit_<%=id%>" contenteditable="false" style=""><%=text%>
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
                var textBeforeChanges = "";

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

            <hr>
            <h2 class="text-center">Urls</h2>
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
            <hr>
            <h2 class="text-center">Images</h2>
            <%
                for (Element thing : elementList.getElementList()) {
                    if (thing.getLabel().equals("image")) {
                        String image_url = ((Thing) thing).getContent();
            %>
            <div class="container text-center">
                <img src="<%=image_url%>" class="img-fluid w-25 rounded" alt="image">
            </div>


            <form action="deleteThing.html" method="POST">
                <input type="hidden" name="list" value="<%=elementList.getID()%>">
                <input type="hidden" name="thing" value="<%=thing.getID()%>">
                <input type="submit" value="Delete">
            </form>
            <%
                    }
                }
            %>

            <hr>
            <div class="adding-section1">
                <form action="addThing.html" method="POST">
                    <h2>Add new thing:</h2>
                    Thing content: <input type="text" name="thing_content" required>
                    <input type="radio" name="type" value="text" checked> Text
                    <input type="radio" name="type" value="url"> URL
                    <input type="radio" name="type" value="image"> Image (url address)
                    <input type="hidden" name="list" value="<%=elementList.getID()%>">
                    <input type="submit" value="Add thing"/>
                </form>
            </div>
        </div>
        <hr>

        <div class="container border shadow">
            <h2 class="text-center">List</h2>
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
                            type = new StringBuilder("list (" + ((ElementList) element).getElementList().size() + " elements)");
                        } else if (element.getType().equals("item")) {
                            for (Element thing : ((ElementList) element).getElementList()) {
                                type.append(thing.getType()).append(", ");
                            }
                            if (!type.isEmpty())
                                type.delete(type.length() - 2, type.length());
                            else
                                type.append("empty item");
                        }
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
                        <input type="submit" class="btn btn-primary " value="Delete element">
                    </form>
                </div>
            </div>
            <%
                    }
                }
            %>
            <hr>
            <div class="adding-section2">
                <form action="addElement.html" method="POST">
                    <h2>Add new element:</h2>
                    Element label: <input type="text" name="element_label" required>
                    <input type="radio" name="type" value="list"> List
                    <input type="radio" name="type" value="item" checked> Item
                    <input type="hidden" name="list" value="<%=elementList.getID()%>">
                    <input type="submit" value="Add element"/>
                </form>
            </div>


        </div>
    </div>

    <%
        ElementList backList = (ElementList) elementList.getParent();
        String hrefBack = "#";
        if (backList.getID() == 0)
            hrefBack = "mainListView.html";
        else
            hrefBack = "itemListView.html?list=" + backList.getID();
    %>
    <hr>
    <a href="<%=hrefBack%>">
        Back to the previous list: <%=backList.getLabel()%>
    </a>

</div>
</body>
</html>
