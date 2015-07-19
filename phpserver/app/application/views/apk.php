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
  $("#tjnr").click(function(){
	  $(".zdc").css("display","block");
    $(".add").css("display","block");
  });
  $("#close").click(function(){
	$(".zdc").css("display","none");
    $(".add").css("display","none");
  });
    $("#xz").change(function(){
	$("#form2").submit();
  });
});
function a(){
	if(confirm("确定要删除吗？")){
		return true;
		}else{
		return false;
			}
	
	}
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
<div style=" float:left" >
<p style="font-size: 20px;
    font-weight: bold;
    height: 30px;
    line-height: 30px;
    margin-bottom: 0;
    margin-top: 0;
    text-align: center;">安卓资源列表</p>
</div>
<div style=" float:right">

<button type="button" id="tjnr" class="btn btn-primary">添加内容</button>
</div>
<div class="box">
<div class="nr_list">
       <div class="list_s">
       <ul>
       <li style="width:50px">appid</li>
       <li style="width:100px">应用名称</li>
       <li style="width:30px">类型</li>
       <li style="width:110px">图标地址</li>
       <li style="width:60px">下载地址</li>
       <li style="width:70px">包名</li>
       <li style="width:170px">描述</li>
       <li style="width:73px">应用大小</li>
       <li style="width:50px">排序</li>
       <li style="width:60px">应用分类</li>
       <li style="width:40px">星级</li>
       <li style="width:100px">版本</li>
       <li style="width:130px">操作</li>
       </ul>
       </div>
       
       <div class="list_ss">
       <ul>
       <?php foreach($apklist as $row){ ?>
       <li style="width:50px"><?php echo $row->id;?></li>
       <li style="width:100px" title="<?php echo $row->title; ?>"><?php echo mb_substr($row->title,0,6);?></li>
       <li style="width:30px">apk</li>
       <li style="width:110px"><img src="<?php echo $row->icon;?>" style="width:88px; height:88px;"></li>
       <li style="width:60px"><a href="<?php echo $row->url;?>">下载地址</a></li>
       <li style="width:70px" title="<?php echo $row->pkg; ?>"><?php echo mb_substr($row->pkg,0,4);?></li>
       <li style="width:170px; line-height:16px;" title="<?php echo $row->des;?>" ><?php echo mb_substr($row->des,0,60);?>...</li>
       <li style="width:73px"><?php echo $row->size;?></li>
       <li style="width:50px"><?php echo $row->orderNum;?></li>
       <li style="width:60px"><?php echo $row->fl;?></li>
       <li style="width:40px"><?php echo $row->rjxj;?></li>
       <li style="width:100px" title="<?php echo $row->bb; ?>"><?php echo mb_substr($row->bb,0,10)?></li>
       <li style="width:130px"><a href="index.php/welcome/edit/<?php echo $row->ids;?>" class="btn btn-info">编辑</a> <a href="index.php/welcome/del/<?php echo $row->ids;?>" onClick="return a();"  class="btn btn-danger">删除</a> </li>
       <?php } ?>
       </ul>
       </div>
   <div class="ctlist" >
   <ul style="border-radius: 3px; box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);display: inline-block; margin-bottom: 0;
    margin-left: 0;">
   <?php echo $this->pagination->create_links();?>
   <li> 
   <form id="form2" method="post"  action="index.php/welcome/apk">
  <select id="xz" name="num"  style="float: left; border:1px solid #DDDDDD;  margin: auto auto 8px; padding: 0;">
  <option value ="10" <?php if(isset($nums)&&$nums==10){ echo 'selected';} ?> >10</option>
  <option value ="25" <?php if(isset($nums)&&$nums==25){ echo 'selected';} ?> >25</option>
  <option value="50" <?php if(isset($nums)&&$nums==50){ echo 'selected';} ?>>50</option>
  <option value="100" <?php if(isset($nums)&&$nums==100){ echo 'selected';} ?>>100</option>
  <option value="250" <?php if(isset($nums)&&$nums==250){ echo 'selected';} ?>>250</option>
  <option value="1000" <?php if(isset($nums)&&$nums==1000){ echo 'selected';} ?>>1000</option>
  </select>
  </form>
   </li>
   </ul>
   </div>
</div>
</div>
</div>
</div>
<div class="add">
<div style="background-color:#369BD7; height:20px; line-height:20px; font-size:15px; font-weight:bold;
	border-radius: 5px; text-align:center; padding: 0.4em 1em; margin-bottom:10px;color:#fff;">添加应用</div>
<form style="padding:0 10px;" action="index.php/welcome/add" method="post">
<input type="hidden" name="id" value="1001" />
应用名称：<input type="text" name="title"/>
<hr/>
应用包名：<input type="text" name="pkg"/><hr/>
图标地址：<input type="text" name="icon"/><hr/>
下载地址：<input type="text" name="url"/><hr/>
应用排序：<input type="text" name="orderNum"/><hr/>

<input type="hidden" name="type" value="2" readonly/>
<input type="hidden" name="oId" value="<?php echo time(); ?>" />

应用大小：<input type="text" name="size"/><hr/>
应用版本：<input type="text" name="bb"/><hr/>
应用分类：<input type="radio" name="fl" value="角色扮演" checked/>角色扮演  <input type="radio" name="fl" value="体育竞速" checked/>体育竞速  <input type="radio" name="fl" value="卡牌益智" checked/>卡牌益智  <input type="radio" name="fl" value="跑酷游戏 " checked/>跑酷游戏  <input type="radio" name="fl" value="塔防策略 " checked/>塔防策略  <input type="radio" name="fl" value="创意休闲" checked/>创意休闲  <input type="radio" name="fl" value="动作射击" checked/>动作射击  <input type="radio" name="fl" value="益智棋牌" checked/>益智棋牌  <input type="radio" name="fl" value="热门网游">热门网游<hr/>
应用星级：<input type="text" name="rjxj"/><hr/>
应用描述：<textarea  name="des"></textarea><hr/>
<input style="margin-left:120px" type="submit" class="btn btn-primary" value="提交">
<input style="margin-left:40px" type="button" class="btn btn-primary" id="close" value="关闭">
</form>
</div>

</body>
</html>