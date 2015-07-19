<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>后台管理系统</title>
<base  href="<?=base_url()?>"/>
<link  href="css/style.css"  rel="stylesheet"  type="text/css"/>
<script src="js/jquery.js" type="text/javascript"></script>
</head>
<script>
$(document).ready(function(){
  $("button").click(function(){
	  $(".zdc").css("display","block");
    $(".add").css("display","block");
  });
  $("#close").click(function(){
	$(".zdc").css("display","none");
    $(".add").css("display","none");
  });
});
</script>
<body>
<div class="zdc"></div>
<div class="navbar-inner"> 

<div style="float:left; font-size:20px; font-weight:bold;">
应用后台管理
</div>
<div style="float:right">
<?php  echo $this->session->userdata("admin");  ?> <a href="index.php/welcome/unsession" style="color:#fff" >退出</a>
</div>
</div>
<div style="margin:auto; width:1275px; overflow:hidden">
<div style="float:left; margin-left:20px;">
<?php include 'gn.php'; ?>
</div>
<div class="main_right">
<div >
</div>
<div class="box" style="margin-top:0px;width:100%">
<div class="nr_list">
       <div class="list_s">
       <ul>
       <li style="width:100%">接口列表</li>
       </ul>
       </div>
       
       <div class="list_ss">
       <ul>
<li style="width:100%">
<form method="post" action="index.php/welcome/mes">
请输入消息：<input type="text" name="mes">
<input style="margin-left:20px" type="submit" class="btn btn-primary" value="提交">
</form>
</li>
       <li style="width:100%">资源接口： <font color="red">您的域名/index.php/welcome/api</font></li>
       <li style="width:100%">广告接口： <font color="red">您的域名/index.php/welcome/apiad</font></li>
       <li style="width:100%">消息接口： <font color="red">您的域名/index.php/welcome/apimes</font></li>
       </ul>
       </div>
       
</div>
</div>
</div>
</div>
</body>
</html>