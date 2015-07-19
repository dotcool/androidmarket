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
    text-align: center;">广告列表</p>
</div>
<div style=" float:right">
<button type="button" id="tjnr" class="btn btn-primary">添加内容</button>
</div>
<div class="box" >
<div class="nr_list">
       <div class="list_s">
       <ul>
       <li style="width:60px">排序</li>
       <li style="width:200px">展示文本</li>
       <li style="width:220px">图片路径</li>
       <li style="width:430px">链接地址</li>
       <li style="width:130px">操作</li>
       </ul>
       </div>
       
       <div class="list_ss">
       <ul>
       <?php foreach($apklist as $row){ ?>
       <li style="width:60px"><?php echo $row->ids;?></li>
       <li style="width:200px"><?php echo $row->name;?></li>
       <li style="width:220px"><img src="<?php echo $row->img;?>" style=" height:88px;"></li>
       <li style="width:430px"><?php echo $row->url;?></li>      
       <li style="width:130px"><a href="index.php/welcome/editad/<?php echo $row->id;?>" class="btn btn-info">编辑</a> <a href="index.php/welcome/delad/<?php echo $row->id;?>" onClick="return a();"  class="btn btn-danger">删除</a> </li>
       <?php } ?>
       </ul>
       </div>
       <div class="ctlist" >
   <ul style="border-radius: 3px; box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);display: inline-block; margin-bottom: 0;
    margin-left: 0;">
   <?php echo $this->pagination->create_links();?>
      <li> 
   <form id="form2" method="post"  action="index.php/welcome/ad">
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
<form style="padding:0 10px;" action="index.php/welcome/addad" method="post">
广告排序：<input type="text" name="ids"/><font color="red">*只能输入数字</font><hr>
展示文本：<input type="text" name="name"/><hr>
图片路径：<input type="text" name="img"/><hr>
链接地址：<input type="text" name="url"/><hr>

<input style="margin-left:120px" type="submit" class="btn btn-primary" value="提交">
<input style="margin-left:40px" type="button" class="btn btn-primary" id="close" value="关闭">
</form>
</div>
</body>
</html>