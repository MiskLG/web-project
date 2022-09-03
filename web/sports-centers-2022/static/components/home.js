Vue.component("home", {
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
							<a class="nav-link active" aria-current="page" href="#/" >Home</a>
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
			<button class="btn btn-primary mx-4 mt-1" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
				Show search options
			</button>
			<form>
			<div class="bg-secondary rounded text-light row mx-4 mt-2 collapse border border-primary" id="collapseExample">
				<div class="mx-4 mt-1 col-md-4 pt-1 ">
					<div class="row m-2">
						<label> Search parameters:</label>
					</div>	
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label col-md-6">Name:</label>
							</div>
							<div class="col-md-8 align-center justify-content-center">
								<input v-model="searchParameters.name" type="text" class="form-control" placeholder="name of facility"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">Type:</label>
							</div>
							<div class="col-md-8">
								<input v-model="searchParameters.type" type="text" class="form-control" placeholder="type of facility"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">City:</label>
							</div>
							<div class="col-md-8">
								<input v-model="searchParameters.city" type="text" class="form-control" placeholder="name of the city"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">Rating:</label>
							</div>
							<div class="col-md-8">
								<input v-model="searchParameters.rating" type="text" class="form-control" placeholder="number from 1 to 5" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 mx-4 mt-1 pt-1 bg-secondary">
					<div class="row m-2">
						<label>Sort by: </label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="sortParameter" value="name" class="form-check-input" type="radio" name="searchRadio" checked />
						<label class="form-check-label" for="searchRadio">
						Name
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="sortParameter" value="location" class="form-check-input" type="radio" name="searchRadio" />
						<label class="form-check-label" for="searchRadio">
						Location
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="sortParameter" value="rating" class="form-check-input" type="radio" name="searchRadio" />
						<label class="form-check-label" for="searchRadio">
						Rating
						</label>
					</div>
				</div>
				<div class="col-md-3 mx-4 mt-1 pt-1 bg-secondary">
					<div class="row-md-3 m-2">
						<label>Show only (filter): </label>
					</div>
					<div class="row-md-3 m-2">
						<label>Type:</label>
						<select class="form-select form-select-sm mx-3">
							<option>Show all</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
					  	</select> 
					</div>
					<div class="row-md-3 m-2">
						<label>Open status:</label>
						<select class="form-select form-select-sm mx-3">
							<option>Show all</option>
							<option>Opened</option>
							<option>Closed</option>
					  	</select> 
					</div>
				</div>
				<div class="d-flex justify-content-center align-center row m-1">
					<button type="submit" class="btn btn-dark">
						Search
					</button>
				</div>
			</div>
			
			</form>
			<div class="m-4 bg-primary row" style="height: 400px" >
				<div class="col-md-3 m-3 bg-secondary">
				test
				</div>
				<div class="col-md-8 m-3 bg-info">
				test
				</div>
			</div>
		</div>
    	`,
    methods : {
			login : function () {
				console.log(this.searchParameters.name);
				console.log(this.sortParameter)
				if(this.credentials.username.trim() != "" && this.credentials.password.trim() != ""){
					axios.post('rest/login', this.credentials).
						then(response => this.user)
				}
    		},
			register : function () {
				console.log(this.searchParameters.name);
				console.log(this.sortParameter)
				router.push('/register');
    		},
			home : function () {
				console.log(this.searchParameters.name);
				console.log(this.sortParameter)
				router.push('/');
    		}
    	},
    	mounted () {
			axios.get('rest/centers/getall')
			then(response => centers);
        }
});