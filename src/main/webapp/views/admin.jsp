<div class="title">
	<h1>Enter the details to establish the connection</h1>
</div>
<div>
	<form id="connect-form">
		<div class="login-form">
			<div class = "input-fields">
				<input type="text" class="input" placeholder="Admin Username" id="admin_username"  >				
				<input type="password" class="input" placeholder="Admin Password" id=admin_pwd">		
				<input type="email" class="input" placeholder="Enter Attachments email" id="admin_email">
				<input type="text" class="input" placeholder="Enter the client Id" id="client_id">
				<input type="text" class="input" placeholder="Service Instance Username" id="instance_username"  >				
				<input type="url" class="input" placeholder="Service Instance URL" id="instance_url"  >
				<input type="password" class="input" placeholder="Service Instance Password" id=instance_pwd">		
			</div>
			<div class="msg">
				<div class="btn2" id="connection_id">Establish Connection</div>
			</div>
		</div>
	</form>	
<script type="text/javascript">
	$(document).ready(function() {
		connection.link();
	})
</script>
</div>
