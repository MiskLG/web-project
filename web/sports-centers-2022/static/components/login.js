Vue.component("login", {
	data: function () {
	    return {
	      username: null,
	      password: null
	    }
	},
	    template: `
		<div>
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
			Launch demo modal
		</button>
		<link rel="stylesheet" href="css/css/login.css"/>
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Login</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form>
						<div class="modal-body" h-auto>
							<div class="border border-primary rounded mx-10 d-flex p-3">
								<div class="justify-content-center align-center">
									<div class="mb-3">
									<label for="formGroupExampleInput" class="form-label">Username</label>
									<input type="text" class="form-control" id="formGroupExampleInput" placeholder="username">
									</div>
									<div class="mb-3">
									<label for="formGroupExampleInput" class="form-label">Password</label>
									<input type="password" class="form-control" id="formGroupExampleInput2" placeholder="password">
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary ">Login</button>
						</div>
					</form>
				</div>
  			</div>
		</div>
		</div>
    	`,
    mounted () {
    },
});