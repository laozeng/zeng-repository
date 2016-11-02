<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${webPath}/resource/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${webPath}/resource/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="${webPath}/resource/assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">

		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>
		<!-- 管理员注册和登录 -->
		     <div class="dl-log"> 
		     	欢迎您，<span class="dl-log-user">${sessionScope.admin }</span> 
		     	<a href="#" title="退出系统" class="dl-log-quit">[退出]</a> 
		      </div> 
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">快捷操作</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">会员中心</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">业务能人中心</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">培训机构中心</div></li>
			<!-- 
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">投票中心</div></li>
			 -->
			 		
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">公司中心</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript" src="${webPath }/resource/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${webPath }/resource/assets/js/bui-min.js"></script>
	<script type="text/javascript" src="${webPath }/resource/assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="${webPath }/resource/assets/js/config-min.js"></script>
	<script>
	$(function(){
		$(".dl-log-quit").click(function(){
			if(confirm("你确定要退出后台登陆吗？")){
				$.post("admin_loginOut",function(result){
					location.href = "admin_loginUI";
				});
			}
		});
	});

// 	function closeWin(){
// 		alert(123);
// 		window.close();
// 		alert(123);
// 	}
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				homePage : '1',
				menu : [ {
					text : '会员中心',
					items : [ {
						id : '1',
						text : '会员列表',
						href : 'admin_userUI'
					} ]
				}, {
					text : '业务能人中心',
					items : [ {
						id : '2',
						text : '业务能人列表',
						href : 'admin_businessUI'
					},{
						id : '3',
						text : '公司用人信息',
						href : 'admin_businessUI1'
					} ]
				} , {
					text : '培训机构中心',
					items : [ {
						id : '4',
						text : '培训机构列表',
						href : 'admin_trainUI'
					} ]
				}, {
					text : '公司中心',
					items : [ {
						id : '5',
						text : '问答列表',
						href : 'admin_questionUI'
					},{
						id : '6',
						text : '评论列表',
						href : 'admin_commentUI'
					},{
						id : '7',
						text : '类别列表',
						href : 'admin_categoryUI'
					},{
						id : '8',
						text : '滚动图修改',
						href : 'admin_manageLogoUI'
					},{
						id : '9',
						text : '服务条款修改',
						href : 'admin_serviceUI'
					},
					{
						id : '10',
						text : '广告操作',
						href : 'admin_advertisementUI'
					},
					{
						id : '11',
						text : '修改密码',
						href : 'admin_updatePwdUI'
					}
					]
				}]
			},
			{
				id : '2',
				homePage : '1',
				menu : [ {
					text : '会员中心',
					items : [ {
						id : '1',
						text : '会员列表',
						href : 'admin_userUI'
					} ]
			}]},
			{
				id : '3',
				homePage : '2',
				menu : [ {
					text : '业务能人中心',
					items : [ {
						id : '2',
						text : '业务能人列表',
						href : 'admin_businessUI'
					},{
						id : '3',
						text : '公司用人信息',
						href : 'admin_businessUI1'
					} ]
			}]},
			{
				id : '4',
				homePage : '4',
				menu : [ {
					text : '培训机构中心',
					items : [ {
						id : '4',
						text : '培训机构列表',
						href : 'admin_trainUI'
					} ]
			}]},
			{
				id : '5',
				homePage : '5',
				menu : [ {
					text : '公司中心',
					items : [ {
						id : '5',
						text : '问答列表',
						href : 'admin_questionUI'
					},{
						id : '6',
						text : '评论列表',
						href : 'admin_commentUI'
					},{
						id : '7',
						text : '类别列表',
						href : 'admin_categoryUI'
					},{
						id : '8',
						text : '滚动图修改',
						href : 'admin_manageLogoUI'
					},{
						id : '9',
						text : '服务条款修改',
						href : 'admin_serviceUI'
					},
					{
						id : '10',
						text : '广告',
						href : 'admin_advertisementUI'
					},
					{
						id : '11',
						text : '修改密码',
						href : 'admin_updatePwdUI'
					}
					]
			}]}
			];
			
			new PageUtil.MainPage({
				modulesConfig : config
			});
		});
	</script>
</body>
</html>
