<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@include file="jsp/common/taglib.jsp" %>
<%@include file="jsp/common/common.jsp" %>
<html>
<head>
<link href="${BasePath}/ui/css/metro-bootstrap.css" rel="stylesheet" type="text/css">
<link href="${BasePath}/ui/css/metro-bootstrap-responsive.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${BasePath}/ui/js/metro.min.js"></script>
<script type="text/javascript" src="${BasePath}/ui/js/metro/metro-dropdown.js"></script>
</head>
<body class="metro" style="width:100%">

       <div class="carousel"  style="position: absolute;z-index: 1" data-role="carousel" data-period="6000" data-effect="slide" data-width="100%" data-height="100%">
           <div class="slide">
               <img src="ui/images/four.jpg" class="cover1" />
           </div>

           <div class="slide">
               <img src="ui/images/se.jpg" class="cover1" />
           </div>

           <div class="slide">
               <img src="ui/images/second.jpg" class="cover1"/>
            </div>
        </div>
<div style="position: absolute;z-index: 2;width: 100%;height: 100%">
<div style="margin-top: 160px;margin-bottom: 50px;" align="center">
    <h1>Log In</h1> 
</div>
<form action="login/main" id="login" name="login" method="post">
<div style="width: 100%;" align="center" class="form-group">
		<div >
        	<input id="loginName" name="loginName" class="form-control" style="width: 20%;height: 40px; display:inline-block;" type="text" placeholder="Username" autofocus required /> 
        	<input id="password" name="password" class="form-control" style="width: 20%;height: 40px;display:inline-block" type="password" placeholder="Password" required/> 
    	</div>
    	<div style="margin-top: 30px">
        <input type="submit" style="width: 100px;height: 30px" id="submit"  value="登   录"> 
        </div>
</div> 
</form>
</div>  
</body>
</html>
