<%@ page import="uk.ac.ucl.model.element.ElementList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%ElementList elementList = (ElementList) request.getAttribute("list");%>

<div class="label themed-container">
      <div id="label_edit" contenteditable="true" onclick="onClickRename()">
        <h2>
          <%=elementList.getLabel()%>
        </h2>
      </div>
</div>
<%--      <button id="button_rename" onclick="onClickRename()">Rename <%=elementList.getType()%></button>--%>
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
          var text_edit = document.getElementById("label_edit");
          // if (text_edit.contentEditable === "false") {
          //   text_edit.setAttribute("contenteditable", "true");
          //   document.getElementById("button_rename").setAttribute("style", "display: none;");
            document.getElementById("button_rename_yes").setAttribute("style", "");
            document.getElementById("button_rename_no").setAttribute("style", "");
          // }
        }

        function onClickRenameYes() {
          document.getElementById("element_label").value = document.getElementById("label_edit").innerText
        }

        function onClickRenameNo() {
          document.getElementById("button_rename_yes").setAttribute("style", "display: none;");
          document.getElementById("button_rename_no").setAttribute("style", "display: none;");
          // document.getElementById("button_rename").setAttribute("style", "");
          document.getElementById("label_edit").innerHTML = labelBeforeChanges;
          // document.getElementById("label_edit").contentEditable = "false";
        }
      </script>