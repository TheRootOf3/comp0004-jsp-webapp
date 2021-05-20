<%@ page import="comp0004.model.element.ElementList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%ElementList elementList = (ElementList) request.getAttribute("list");%>

<div class="label row themed-container text-center">
    <div id="label_edit" contenteditable="true" onclick="onClickRename()">
        <h2>
            <%=elementList.getLabel()%>
        </h2>
    </div>
</div>
<button type="submit" form="form_rename" id="button_rename_yes" style="display: none"
        onclick="onClickRenameYes()">Accept changes
</button>
<button id="button_rename_no" style="display: none" onclick="onClickRenameNo()">Cancel changes</button>
<form id="form_rename" action="renameElement.html" method="POST">
    <input type="hidden" name="list" value="<%=elementList.getID()%>">
    <input type="hidden" id="element_label" name="element_label" value="">
</form>

<script>
    var labelBeforeChanges;

    function onClickRename() {
        labelBeforeChanges = document.getElementById("label_edit").innerHTML;
        document.getElementById("button_rename_yes").setAttribute("style", "");
        document.getElementById("button_rename_no").setAttribute("style", "");
    }

    function onClickRenameYes() {
        document.getElementById("element_label").value = document.getElementById("label_edit").innerText
        if (document.getElementById("label_edit").innerText === "" || document.getElementById("label_edit").innerText === "\n")
            document.getElementById("element_label").value = "empty_label"

    }

    function onClickRenameNo() {
        document.getElementById("button_rename_yes").setAttribute("style", "display: none;");
        document.getElementById("button_rename_no").setAttribute("style", "display: none;");
        document.getElementById("label_edit").innerHTML = labelBeforeChanges;
    }
</script>