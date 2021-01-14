<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="title">
	<h1>HCM Safety incident Management Registration</h1>
</div>
<div>
	<form>
		<div class="login-form">
			<div class = "input-fields">
				<input type="text" class="input" placeholder="First Name" id="firstname" >
				<input type="text" class="input" placeholder="Last Name" id="lastname" >
				<input type="text" class="input" placeholder="Company Name" id="companyname" >				
				<input type="email" class="input" placeholder="Email Address" id="emailId" >
				<input type="text" class="input" placeholder="Phone Number" id="contact" maxlength="10" >		
				<input type="text" class="input" placeholder="Country" id="country" >		
			</div>
			<div class="msg">
				<div class="btn" id="submit_id">Contact Me</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			register.init();
		})
	</script>
</div>