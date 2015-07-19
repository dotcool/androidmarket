<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>管理员登录</title>
<base  href="<?=base_url()?>"/>
<link  href="css/style.css"  rel="stylesheet"  type="text/css"/>
</head>
<body style="background-color:#0976af">
<div class="login_bg"> 
<div style=" width:200px; height:100px; margin:225px auto auto 330px;">
<form action="index.php/welcome/apk" method="post">
<div style="float:left; ">
<input type="text" style="background-color:#d3d3d3;border:none; width:260px"  name="name"/>
</div>
<div style="float:left; margin-top:35px; ">
<input type="password" style="background-color:#d3d3d3; border:none; width:260px"  name="pwd" />
</div>
<div style="float:left;margin-top:20px; margin-left:80px">
<input type="submit" value=" " style="background:url(images/login_bt.jpg) no-repeat; width:117px; height:26px; border:none; cursor:pointer; " />
</div>
</form>
</div>
</div>
	
</body>
</html>