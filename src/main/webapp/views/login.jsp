<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div>
<div id="adminlogin" class="visible">
	<div class="title">
		<h1>Enter the Login details to enter</h1>
	</div>
	<form>
		<div class="login-form">
			<div class = "input-fields">
				<input type="text" class="input" placeholder="Admin Username" id="login_username"  >				
				<input type="password" class="input" placeholder="Admin Password" id="login_pwd">			
			</div>
			<div class="msg">
				<div class="btn2" id="login_id">Enter</div>
			</div>
		</div>
	</form>
</div>	
<div id="adminadd" class="hidden">
	<div class="title">
		<h1>Enter the credentials to be Admin</h1>
	</div>
	<form id="connect-form">
		<div class="login-form">
			<div class = "input-fields">
				<input type="text" class="input" placeholder="New Admin Username" id="new_username"  >				
				<input type="password" class="input" placeholder="New Admin Password" id="new_pwd">			
			</div>
			<div class="msg">
				<div class="btn2" id="addcred_id">Add</div>
			</div>
		</div>
	</form>
</div>	
<script type="text/javascript">
	$(document).ready(function() {
		admin.login();
	})
</script>
<script type="text/javascript">
	$(document).ready(function() {
		credentials.add();
	})
</script>
</div>
