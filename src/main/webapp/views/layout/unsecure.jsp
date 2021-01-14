<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/assets/images/indrasol.jpg">
    <title>HCM Connector</title>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
   <!-- All Jquery -->
   	 <script src="${pageContext.request.contextPath}/assets/js/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css">
    <script src="https://kit.fontawesome.com/662d11ff80.js" crossorigin="anonymous"></script>
    <style>
    	
    	@import url('https://fonts.googleapis.com/css2?family=Big+Shoulders+Stencil+Text:wght@700&family=East+Sea+Dokdo&family=Hanalei+Fill&family=Redressed&family=Source+Code+Pro&family=Xanh+Mono:ital@1&display=swap');
    	* {
    		margin: 0;
    		padding: 0;
    		box-sizing: border-box;
    		outline: none;
    		font-family: 'Roboto', sans-serif;
    	}
    	body {
    		background: url('assets/images/blue2.jpg') no-repeat top center;
    		background-size: cover;
    		height: 100vh;
    	}
    	.title {
    		font-family: 'Roboto Mono', monospace;
    		text-align: center;
    		color: #c5ecfd;
    		padding-bottom: 50px;
    		text-transform: uppercase;
    	}
    	.hidden {
        	display: none;
	    }
	    .visible {
	        display: block;
	     }
    	.wrapper {
    		position: absolute;
    		top: 50%;
    		transform: translateY(-50%);
    		width: 100%;
    		padding: 0 20px;
    	}
    	.login-form {
    		max-width: 550px;
    		margin: 0 auto;
    		background: rgba(0,0,0,0.8);
    		padding: 30px;
    		border-radius: 5px;
    		display: flex;
    		box-shadow: 0 0 10px rgba(0,0,0,0.13);  
    		opacity: 0.80;
    	}
    	.thankyou-form {
    		max-width: 600px;
    		margin: 0 auto;
    		background: rgba(0,0,0,0.8);
    		padding: 30px;
    		border-radius: 5px;
    		display: flex;
    		box-shadow: 0 0 10px rgba(0,0,0,0.13);  
    		opacity: 0.80;
    	}
    	
    	.input-fields {
    		display: flex;
    		flex-direction: column;
    		margin-right: 4%;

    	}
    	.input-fields,
    	.msg {
    		width: 48%;
    	}
    	
    	.input-fields .input {
    		margin: 10px 0;
    		background: transparent;
    		border: 0;
    		border-bottom: 2px solid #c5ecfd;
    		padding: 10px;
    		color: #c5ecfd;
    		width: 100%;
    		font-weight: 700;
    	}
    	
    	.input-fields p {
    		color: #c5ecfd;
    		width: max-content;
    		font-weight: 700;
    	}
    	.butt {
    		margin: 10px
    	}
    	::-webkit-input-placeholder {
    		color: #c5ecfd;
    		font-weight: 400;
    	}
    	::-moz-input-placeholder {
    		color: #c5ecfd;
    		font-weight: 700;
    	}
    	::-ms-input-placeholder {
    		color: #c5ecfd;
    		font-weight: 700;
    	}
    	.btn {
    		margin: 40px 75px;
    		background: #39b7dd;
    		text-align: center;
    		padding: 15px 10px;
    		border-radius: 5px;
    		color: rgba(0,0,0,0.8);
    		cursor: pointer;
    		text-transform: uppercase;
    		font-family: 'Roboto Mono', monospace;
    		font-weight: bold;
    	
    	}
    	.btn:hover {
    		background-color: #c5ecfd;
    		color: rgba(0,0,0,0.8);
    		border: 1px solid rgba(0,0,0,0.8);
    		box-shadow: 0 2px #666;   		
    	}
    	.btn:active {
		  background-color: #c5ecfd;
		  box-shadow: 0 5px #666;
		  transform: translateY(4px);
		 }
		 .note {
    		font-family: 'Roboto Mono', monospace;
    		text-align: center;
    		color: #c5ecfd;
    		display: table;
    	}
    	.smiley {
    		display: inline-block;
    		font-size: 20px;    		
    	}
    	.par {
    		font-size: 20px;
    		display: inline-block;
    	}
		.btn-holder {
			display: inline-block;
		} 
		 .btn1 {
	   		padding: 5px;
	   		width: max-content;
	   		background: #39b7dd;
	   		text-align: center;
	   		border-radius: 5px;
	   		color: rgba(0,0,0,0.8);
	   		cursor: pointer;
	   		text-transform: uppercase;
	   		font-family: 'Roboto Mono', monospace;
	   		font-weight: bold;
	   	
    	}
    	.btn1:hover {
    		background-color: #c5ecfd;
    		color: rgba(0,0,0,0.8);
    		border: 1px solid rgba(0,0,0,0.8);
    		box-shadow: 0 2px #666;   		
    	}
    	.btn1:active {
		  background-color: #c5ecfd;
		  box-shadow: 0 5px #666;
		  transform: translateY(4px);
		 }
		 .btn2 {
    		margin: 40px 45px;
    		background: #39b7dd;
    		text-align: center;
    		padding: 3px 5px;
    		border-radius: 5px;
    		color: rgba(0,0,0,0.8);
    		cursor: pointer;
    		text-transform: uppercase;
    		font-family: 'Roboto Mono', monospace;
    		font-weight: bold;
    	
    	}
    	.btn2:hover {
    		background-color: #c5ecfd;
    		color: rgba(0,0,0,0.8);
    		border: 1px solid rgba(0,0,0,0.8);
    		box-shadow: 0 2px #666;   		
    	}
    	.btn2:active {
		  background-color: #c5ecfd;
		  box-shadow: 0 5px #666;
		  transform: translateY(4px);
		 }
		
    	.footerWrap {
		    width:100%;
		    position:fixed;
		    bottom: 20px;
		}
		.footer {
		    width:95%;
		    margin:auto;
		}
		.footerContent {
		    float:left;
		    width:100%;
		    background:rgba(0,0,0,0.8);
		    padding: 8px 0px;
		    text-align:center;
		}
		.footer p {
			float:left; 
			width:100%; 
			text-align:center;
			justify-content: center;
			color: #c5ecfd;
			font-family: 'Xanh Mono', monospace;
			font-size: 15px;
			padding-top: 3px;
		}
		
		.footer p a {
			font-family: 'Big Shoulders Stencil Text', cursive;
			font-size: 20px;
			text-transform: capitalize;
		}
		
    	@media screen and (max-width: 600px) {
    		.login-form {
    			flex-direction: column;
    		}
    		.input-fields,
    		.msg {
    			width: 100%;
    		}
    	} 
    </style>
</head>

<body>
    <!-- Main wrapper  -->
	<div class="wrapper" >
		 
			<tiles:insertAttribute name="body" />

		<!-- End Page wrapper  -->
	</div>
	<!-- End Wrapper -->
	<div class="footerWrap">
    <div class="footer">
      <div class="footerContent">
        <p>Copyright &copy;2021. All rights reserved by <a target="_blank" href="https://indrasol.com/corp/">Indrasol</a> </p>
      </div>     
    </div>
	</div>

    <script src="/assets/js/custom.min.js"></script>
    
</body>

</html>