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
       <li style="width:80px">编辑页面</li>
       </ul>
       </div>    
       <div class="adds" >
<?php foreach($apklist as $row){ ?>
<form style="padding:0 10px;" action="index.php/welcome/updata" method="post">
<input type="hidden" name="id" value="<?php echo $row->id;?>" />
<input type="hidden" name="ids" value="<?php echo $row->ids;?>" />
应用名称：<input type="text" name="title" value="<?php echo $row->title;?>"/><hr>
应用包名：<input type="text" name="pkg" value="<?php echo $row->pkg;?>"/><hr>
图标地址：<input type="text" name="icon"  value="<?php echo $row->icon;?>"/><hr>
下载地址：<input type="text" name="url" value="<?php echo $row->url;?>"/><hr>
应用排序：<input type="text" name="orderNum" value="<?php echo $row->orderNum;?>"/><hr>
应用类型：<input type="text" name="type"  value="<?php echo $row->type;?>"/><font color="red"> &nbsp;&nbsp; 1代表mrp &nbsp;&nbsp;&nbsp;  2代表apk</font><hr>
<input type="hidden" name="oId" value="<?php echo $row->oId;?>" />

应用大小：<input type="text" name="size" value="<?php echo $row->size;?>"/><hr>
应用版本：<input type="text" name="bb" value="<?php echo $row->bb;?>"/><hr>
应用分类：<input type="radio" name="fl" value="角色扮演" <?php if($row->fl=='角色扮演'){echo 'checked';};?>/>角色扮演  <input type="radio" name="fl" value="体育竞速" <?php if($row->fl=='体育竞速'){echo 'checked';};?>/>体育竞速  <input type="radio" name="fl" value="卡牌益智" <?php if($row->fl=='卡牌益智'){echo 'checked';};?>/>卡牌益智  <input type="radio" name="fl" value="跑酷游戏" <?php if($row->fl=='跑酷游戏'){echo 'checked';};?>/>跑酷游戏  <input type="radio" name="fl" value="塔防策略" <?php if($row->fl=='塔防策略'){echo 'checked';};?>/>塔防策略  <input type="radio" name="fl" value="创意休闲" <?php if($row->fl=='创意休闲'){echo 'checked';};?>/>创意休闲  <input type="radio" name="fl" value="动作射击 " <?php if($row->fl=='动作射击 '){echo 'checked';};?>/>动作射击   <input type="radio" name="fl" value="益智棋牌" <?php if($row->fl=='益智棋牌'){echo 'checked';};?>/>益智棋牌  <input type="radio" name="fl" value="热门网游" <?php if($row->fl=='热门网游'){echo 'checked';};?>>热门网游<hr>
应用星级：<input type="text" name="rjxj"  value="<?php echo $row->rjxj;?>" /><hr>
应用描述：<textarea  name="des"><?php echo $row->des;?></textarea><hr>
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