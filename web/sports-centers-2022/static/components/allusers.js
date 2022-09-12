Vue.component("all-users", {
	data: function () {
	    return {
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

			users: null,
			wrapper: {name: "", lastname: "", username: "", filterType: "Show all", sortParameter: "NAME", sortOrientation: "ASC",}
	    }
	},
	    template: `
		<div>
            <div v-if="user.type == 'ADMIN'">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark border border-secondary">
				<div class="container-fluid">
					<a class="navbar-brand" href="#/">SportsCenters</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						Menu
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/')" href="#/">Home</a>
							</li>
							<li v-if="user.type == 'BUYER'" class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/workouts')" href="#/workouts" >Workouts</a>
							</li>
							<li v-if="user.type == 'BUYER' || user.type == 'COACH'" class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/user-workouts')" href="#/user-workouts" >Appointed workouts</a>
							</li>
							<li v-if="user.type == 'BUYER'" class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/user-comments')" href="#/user-comments" >Comment</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/add-centers')" href="#/add-centers" >Add Centers</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/add-managers')" href="#/add-managers" >Add Managers</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/add-coaches')" href="#/add-coaches">Add Coaches</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/all-users')" href="#/all-users">All Users</a>
							</li>
							<li v-if="user.type == 'MANAGER' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/facility-overview')" href="#/facility-overview">Facility overview</a>
							</li>
							<li v-if="user.type == 'MANAGER' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/add-workouts')" href="#/add-workouts">Add workouts</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/promo-codes')" href="#/promo-codes">Define promo codes</a>
							</li>
							<li v-if="user.type == 'ADMIN' "class="nav-item">
							<a class="nav-link active" aria-current="page" @click="changePage('/comments-overview')" href="#/comments-overview">Approve comments</a>
							</li>
							
						</ul>
						<div v-if="user.username == ''" class="bg-secondary btn btn-dark mx-2" @click="changePage('/register')">
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
						<div v-if="user.username != ''"  class="dropdown">
							<button class="btn btn-secondary dropdown-toggle" style="min-width:120px" type="button" data-bs-toggle="dropdown" aria-expanded="false" >
								{{user.username}}
							</button>
							<ul class="dropdown-menu dropdown-menu-dark">
								<li><a class="dropdown-item" @click="changePage('/edit-profile')" href="#edit-profile">Edit Profile</a></li>
								<li><a v-if="user.type=='BUYER'" @click="changePage('/subscriptions')" class="dropdown-item" href="#subscriptions" >Subscription</a></li>
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
								<input v-model="wrapper.name" type="text" class="form-control" placeholder="name"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">Lastname:</label>
							</div>
							<div class="col-md-8">
								<input v-model="wrapper.lastname" type="text" class="form-control" placeholder="lastname"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">Username:</label>
							</div>
							<div class="col-md-8">
								<input v-model="wrapper.username" type="text" class="form-control" placeholder="username"/>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 mx-4 mt-1 pt-1 bg-secondary">
					<div class="row m-2">
						<label>Sort by: </label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="wrapper.sortParameter" value="NAME" class="form-check-input" type="radio" name="searchRadio" checked />
						<label class="form-check-label" for="searchRadio">
						Name
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="wrapper.sortParameter" value="LASTNAME" class="form-check-input" type="radio" name="searchRadio" />
						<label class="form-check-label" for="searchRadio">
						Last name
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="wrapper.sortParameter" value="USERNAME" class="form-check-input" type="radio" name="searchRadio" />
						<label class="form-check-label" for="searchRadio">
						Username
						</label>
					</div>
					<div class="row-md-4 m-6" style="height: 20px">
					<span> </span>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="wrapper.sortOrientation" value="ASC" class="form-check-input" type="radio" name="sortOrientation" />
						<label class="form-check-label" for="sortOrientation">
						Ascending
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input v-model="wrapper.sortOrientation" value="DESC" class="form-check-input" type="radio" name="sortOrientation" />
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
						<select v-model="wrapper.filterType" class="form-select form-select-sm mx-3">
							<option>Show all</option>
							<option>Buyer</option>
                            <option>Manager</option>
                            <option>Coach</option>
                            <option>Admin</option>
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
			<div v-for="user in users" class="m-2 bg-secondary row rounded" >
                <div class="mx-1 row">
				    <label class="fs-5 text-light col-md-2">Username: <span class="mx-1 text-info">{{user.username}}</span> </label>
                    <label class="fs-5 text-light col-md-2">User Type: <span class="mx-1 text-info">{{user.userType}}</span> </label>
                </div>
                <div class="mx-1 row">
                    <label class="fs-5 text-light col-md-2">Name: <span class="mx-1 text-info">{{user.name}}</span> </label>
                    <label class="fs-5 text-light col-md-2">Lastname: <span class="mx-1 text-info">{{user.lastname}}</span> </label>
                    <label class="fs-5 text-light col-md-2">Gender: <span class="mx-1 text-info">{{user.gender}}</span></label>
                    <label class="fs-5 text-light col-md-5">Date of birth: <span class="mx-1 text-info">{{user.dateOfBirth}}</span> </label>
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
							window.location.reload();
						})	
						.catch(error => {
							alert("Wrong username or password");
						})
				}		
			},
			logout : function () {
				this.user.username = "";
				this.user.type = "";
				axios.post('rest/user/logout')
					.then(response => {
						router.push('/');
					})
			},
			changePage : function(path) {
				router.push(path);
			},
            getUser : function () {
                axios.get('rest/user/current').
                    then(response => {
                        if(response.data != 'NOUSER') {
                            this.user = response.data;
							if (this.user.type != "ADMIN" ) {
                                router.push("/");
                                window.location.reload();
                            }
						}
                        else{
                            router.push("/");
                            window.location.reload();
                        }
                    }).catch(error => {
                        
                    })	
            },

			search : function () {
				axios.post('rest/user/search',this.wrapper).
					then(response => {
						this.users = response.data;
					})
                    .catch(error => {
                        
                    })
			},
            getAllUsers : function () {
                axios.get("rest/user/getAll").
                    then(response => {
                        this.users = response.data;
                    })
                    .catch(error => {

                    })
            }
    	},
    	mounted () {
			this.getUser();
            this.getAllUsers();
        }
});