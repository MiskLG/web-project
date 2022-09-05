Vue.component("home", {
	data: function () {
	    return {
			
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

			centers: null,
			types: "",
			searchParameters: {name: "", city: "", type: "", rating: ""},
			sortParameter: "",
			sortOrientation: "",
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
							<a class="nav-link active" aria-current="page" @click="homeMove" href="#/">Home</a>
							</li>
							<li v-if="user.type == 'BUYER'" class="nav-item">
							<a class="nav-link active" aria-current="page" @click="workoutsMove" href="#/workouts" >Workouts</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="addCentersMove" href="#/add-centers" >Add Centers</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="addManagersMove" href="#/add-managers" >Add Managers</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="addCoachesMove" href="#/add-coaches">Add Coaches</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="allUsersMove" href="#/all-users">All Users</a>
							</li>
							<li v-if="user.type == 'MANAGER' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="addWorkoutsMove" href="#/add-workouts">Add workouts</a>
							</li>
						</ul>
						<div v-if="user.username == ''" class="bg-secondary btn btn-dark mx-2" @click="registerMove">
							Register
						</div>
						<button v-if="user.username == ''" type="button" class="btn bg-secondary btn-dark" data-bs-toggle="modal" data-bs-target="#exampleModal">
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
						<div v-if="user.username != ''"  class="dropdown dropstart">
							<button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" >
								{{user.username}}
							</button>
							<ul class="dropdown-menu dropdown-menu-dark">
								<li><a class="dropdown-item" href="#edit-profile">Edit Profile</a></li>
								<li><a v-if="user.type=='BUYER' "class="dropdown-item" href="#subscription" >Subscription</a></li>
								<li><a class="dropdown-item" @click="logout">Logout</a></li>
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
					<div class="row-md-4 m-6" style="height: 20px">
					<span> </span>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="sortOrientation" value="asc" class="form-check-input" type="radio" name="sortOrientation" />
						<label class="form-check-label" for="sortOrientation">
						Ascending
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="sortOrientation" value="desc" class="form-check-input" type="radio" name="sortOrientation" />
						<label class="form-check-label" for="sortOrientation">
						Descending
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
							<option v-for="type in types">{{type}}</option>
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
					<button type="button" class="btn btn-dark" @click="search" >
						Search
					</button>
				</div>
			</div>
			
			</form>
			<div v-for="center in centers" class="m-4 bg-secondary row rounded" >
				<div class="col-md-3 m-3">
					<img :src="center.image" class="img-thumbnail"/>
				</div>
				<div class="col-md-8 m-3 bg-secondary">
					<div class="row mb-3">
						<label class="col-6 fs-2 d-flex justify-content-end text-light"> {{center.name}} </label>
						<label class="col-6 fs-4 d-flex justify-content-end text-light"> Rating:  <span class="mx-2 text-info">{{center.rating}}/5</span> </label>
					</div>
					<div class="row">
						<label class="text-light fs-5">Type: <span class="text-info mx-2">{{center.type}}</span></label>
					</div>
					<div class="row">
						<label class="text-light fs-5">Status: <span class="text-info mx-2">{{center.status}}</span></label>
					</div>
					<div class="row">
						<label class="text-light fs-5">Work hours: <span class="text-info mx-2">{{center.startTime}}h - {{center.endTime}}h </span></label>
					</div>
					<div class="row">
						<label class="text-light fs-5">Location: <span class="text-info mx-2">{{center.city}}, {{center.poNumber}}, {{center.street}} {{center.stNumber}} </span></label>
					</div>
					<div class="row">
						<label class="col-6 text-light fs-5">Longitude: <span class="text-info mx-2">{{center.longitude}}</span> Latitude: <span class="text-info mx-2">{{center.latitude}}</span></label>
						<div class="col-6 d-flex justify-content-end">
							<button class="btn btn-primary" type="button" @click="seeContent(center.id)">
								See content =>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
    	`,
    methods : {
			login : function () {
				if(this.credentials.username.trim() != "" && this.credentials.password.trim() != ""){
					axios.post('rest/user/login', this.credentials).
						then(response => {
							this.user = response.data;
						})	
				}
				console.log(this.user);
				setTimeout(function(){
					window.location.reload();
				}, 500);
				
    		},
			logout : function () {
				this.user.username = "";
				this.user.type = "";
				axios.post('rest/user/logout');
				console.log(this.user);
				setTimeout(function(){
					window.location.reload();
				}, 500);
			},
			registerMove : function () {
				console.log(this.searchParameters.name);
				console.log(this.sortParameter)
				router.push('/register');
    		},
			homeMove : function () {
				router.push('/');
				window.location.reload();
    		},
			workoutsMove : function () {
				router.push('/workouts');
				window.location.reload();
    		},
			addCentersMove : function () {
				router.push('/add-centers');
				window.location.reload();
    		},
			addManagersMove : function () {
				router.push('/add-managers');
				window.location.reload();
    		},
			addCoachesMove : function () {
				router.push('/add-coaches');
				window.location.reload();
    		},
			allUsersMove : function () {
				router.push('/all-users');
				window.location.reload();
    		},
			addWorkoutsMove : function () {
				router.push('/add-workouts');
				window.location.reload();
    		},
			getUser : function () {
				axios.get('rest/user/current').
					then(response => {
						if(response.data != 'NOUSER') {
							this.user = response.data;
						}
					})	
			},
			editProfile() {
				if (user.username.length === 0) {
					return;
				}
				router.push('edit-profile');
				window.location.reload();
			},
			

			search : function () {
			},
			getCenters : function () {
				axios.get('rest/centers/getAll').
					then(response => {
						this.centers = response.data;
					})
			},
			getAllTypes : function() {
				axios.get('rest/centers/getAllTypes').
					then(response => {
						this.types = response.data;
					})
			},
			seeContent : function(id) {
				router.push('/'+id);
			}
    	},
    	mounted () {
			this.getUser();
			this.getCenters();
			this.getAllTypes();
        }
});