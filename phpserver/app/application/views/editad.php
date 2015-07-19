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

<div class="box" style="width:100%">
<div class="nr_list">
       <div class="list_s">
       <ul>
       <li style="width:80px">编辑广告</li>
       </ul>
       </div>    
       <div class="adds" >
<?php foreach($apklist as $row){ ?>
<form style="padding:0 10px;" action="index.php/welcome/update_ad" method="post">
<input type="hidden" name="id" value="<?php echo $row->id;?>" />
排序：<input type="text" name="ids" value="<?php echo $row->ids;?>"/><hr>
展示文本：<input type="text" name="name"  value="<?php echo $row->name;?>"/><hr>
图片路径：<input type="text" name="img"  value="<?php echo $row->img;?>"/><hr>
链接地址：<input type="text" name="url" value="<?php echo $row->url;?>"/><hr>

<input style="margin-left:170px" type="submit" class="btn btn-primary" value="更新">
</form>
 <?php } ?>
</div>   
</div>
</div>
</div>
</div>

</body>
</html>