<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<script type="text/javascript" src="resource/js/jquery-1.8.3.min.js"></script>
	<script src="resource/kindeditor/kindeditor.js" type="text/javascript"></script>
	<script type="text/javascript">
		var editor1 = null;
		var editor2 = null;
		$(function(){
			KindEditor.ready(function(K) {
	            editor1 = K.create('#content1');
	            //editor2 = K.create('#content2');
	            editor1 = K.create('[name=content1]', {
					cssPath : 'kindeditor/plugins/code/prettify.css',
					uploadJson : '<%=path%>/doUpload',
					allowFileManager : true
				});
		    	prettyPrint();
	        });
	        
			$("#getValue").click(function(){
				alert(editor1.html());
			});
		});

		function setValue(){
			$("#divDemo").show();
		}
	</script>
  </head>
  
  <body>
	<div id="divDemo">
		<textarea id="content1" name="content1" style="width:700px;height:500px;"></textarea><br/>
		<textarea id="content2" name="content2" style="width:700px;height:300px;"></textarea><br/>
	</div>
  </body>
</html>
