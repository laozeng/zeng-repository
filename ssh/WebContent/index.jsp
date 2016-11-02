<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 此处加不加项目名称都可以，加不加 basePath也可以-->
<script type="text/javascript">location.href="<%=basePath%>index/indexUI.mvc"</script>