Vue.component("register", {
	data: function () {
	    return {
			centers: null,
			user: null,
	    	credentials: {username: "", password: ""},

			searchParameters: {name: "", city: "", type: "", rating: ""},
			sortParameter: ""
	    }
	},
	    template: `
		<div>
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark border border-secondary">
				<div class="container-fluid">
					<a class="navbar-brand" href="#/">SportsCenters</a>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="#/">Home</a>
							</li>
							<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="#/workouts">Workouts</a>
							</li>
						</ul>
						<div v-if="user == null" class="bg-secondary btn btn-dark mx-2" @click="register">
							Register
						</div>
						<button v-if="user == null" type="button" class="btn bg-secondary btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">
							Login
						</button>
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
													<input v-model="credentials.username" type="text" class="form-control" id="formGroupExampleInput" placeholder="username">
													</div>
													<div class="mb-3">
													<label for="formGroupExampleInput" class="form-label">Password</label>
													<input v-model="credentials.password" type="password" class="form-control" id="formGroupExampleInput2" placeholder="password">
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button @click="login" type="submit" class="btn btn-primary ">Login</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div v-if="user != null"  class="dropdown dropstart">
							<button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
								Profile
							</button>
							<ul class="dropdown-menu dropdown-menu-dark">
								<li><a class="dropdown-item" href="#">Action</a></li>
								<li><a class="dropdown-item" href="#">Another action</a></li>
								<li><a class="dropdown-item" href="#">Something else here</a></li>
							</ul>
						</div>
					</div>
				</div>
			</nav>
            <form>
            
            </form>
		</div>
    	`,
    methods : {
    	},
    	mounted () {
        }
});