<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>后台管理系统</title>
<base  href="<?=base_url()?>"/>
<link  href="css/style.css"  rel="stylesheet"  type="text/css"/>
</head>
<body>
<div class="navbar-inner"> 
<div style="float:left; font-size:20px; font-weight:bold;">
应用后台管理
</div>
<div style="float:right">
<?php  echo $this->session->userdata("admin");  ?> <a href="index.php/welcome/unsession" style="color:#fff" >退出</a>
</div>
</div>
<div style="margin:auto; width:1275px; overflow:hidden">
<div style="float:left;margin-left:20px;">
<?php include 'gn.php'; ?>
</div>
<div class="main_right" style="text-align:center; font-size:18px;">
欢迎使用后台管理系统！
<li><a href="index.php/welcome/apk"><i class="icos"></i>安卓资源列表</a></li>
</div>
</div>
</body>
</html>