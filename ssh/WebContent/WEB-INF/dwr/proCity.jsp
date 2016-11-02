<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>省市级联案例</title>
	<script type="text/javascript" src="/dwr/engine.js" ></script>
	<script type="text/javascript" src="/dwr/util.js" ></script>
	<script type="text/javascript" src="/dwr/interface/AreaUtil.js" ></script>
	<script type="text/javascript" src="/js/jquery-1.8.3.min.js" ></script>
	<script type="text/javascript" >
		$(function(){
			//隐藏城市和区
			$("#city").hide();
			$("#town").hide();
			
			AreaUtil.getAllProvinces(function(data){
				for(var i=data.length-1;i>=0;i--){
					var temp = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					$("#selectPro").after(temp);
				}
			});

			$("#province").change(function(){
				var id = $(this).val();
				if(id == -1){
					return;
				}
				getCitiesByProvinceId(id);
			});

			$("#city").change(function(){
				var id = $(this).val();
				if(id == -1){
					return;
				}
				getTownsByCityId(id);
			});
		});

		function getCitiesByProvinceId(id){
			AreaUtil.getCitiesByProvinceId(id,function(data){
				$("#city").hide();
				$("#town").hide();
				//清除之前的城市
				$("#city").children("[id!=selectCity]").remove();
			    for(var i=0;i<data.length;i++){
					$("#city").show();
					var temp = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					$("#city").append(temp);
				} 
			});
		}

		function getTownsByCityId(id){
			AreaUtil.getTownsByCityId(id,function(data){
				$("#town").hide();
				//清除之前的乡镇
				$("#town").children("[id!=selectTown]").remove();
				for(var i=0;i<data.length;i++){
					$("#town").show();
					var temp = "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
					$("#town").append(temp);
				}
			});
		}
	</script>
  </head>
  
  <body>
		请选择地区：
		<select id="province">
			<option id="selectPro" value="-1">--请选择省份--</option>
		</select>&nbsp;&nbsp;
		<select id="city">
			<option id="selectCity" value="-1">--请选择城市--</option>
		</select>&nbsp;&nbsp;
		<select id="town">
			<option id="selectTown" value="-1">--请选择乡镇--</option>
		</select>&nbsp;&nbsp;
  </body>
</html>
