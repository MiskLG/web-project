Vue.component("edit-profile", {
	data: function () {
	    return {

			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

			profileData: {username: "", name:"", lastname:"", gender:"", dateOfBirth:""},
            updateData: {username:"", name:"", lastname:"", password:"", password2:"", type:""}
	    }
	},
	    template: `
		<div>
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
            <form>
                <div class="bg-secondary rounded text-light row mx-4 mt-2 border border-primary">
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Username:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="profileData.username" type="text" disabled class="form-control" placeholder="username"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Name:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="updateData.name" type="text" class="form-control" placeholder="name"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Lastname:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="updateData.lastname" type="text" class="form-control" placeholder="lastname"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Password:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="updateData.password" type="password" class="form-control" placeholder="password"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Repeat password:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="updateData.password2" type="password" class="form-control" placeholder="repeat password"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Date of Birth:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="profileData.dateOfBirth" disabled class="form-control input-group" type="text" />
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Gender:</label>
                        </div>
                        <div class="form-check col-md-2 m-2">
                            <input v-model="profileData.gender" value="MALE" class="form-check-input" disabled type="radio" name="searchRadio" />
                            <label class="form-check-label" for="searchRadio">
                            Male
                            </label>
					    </div>
                        <div class="form-check col-md-2 m-2">
                            <input v-model="profileData.gender" value="FEMALE" class="form-check-input" disabled type="radio" name="searchRadio" />
                            <label class="form-check-label" for="searchRadio">
                            Female
                            </label>
					    </div>
                    </div>
                    <div class="d-flex justify-content-center align-center row m-1">
					<button @click="update" type="button" class="btn btn-primary">
						Update
					</button>
				</div>
                </div>
            </form>
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
                        this.getProfileData();
                    }
                    else{
                        router.push('/');
                    }
                })
                .catch(error => {
                    router.push('/');
                })
        },


        update : function () {
            this.updateData.name.trim();
            this.updateData.lastname.trim();
            this.updateData.password.trim();
            this.updateData.password2.trim();
            
            if(this.updateData.name.length === 0 || this.updateData.lastname.length === 0 
                || this.updateData.password.length === 0 || this.updateData.password2.length === 0) {
                alert("Every field must be filled");
                return;
            }
            if (this.updateData.password != this.updateData.password2) {
                alert("Password does not match");
                return;
            }
            
            axios.put('/rest/user/update', this.updateData).
                then(response => {
                    window.location.reload();
                })	
                .catch(error => {
                    alert("Error");
                })
        },
        getProfileData : function () {
            axios.get('/rest/user/getById', {params : {username: this.user.username, type: this.user.type}})
                .then(response => {
                    this.profileData = response.data; 
                    this.updateData.name = this.profileData.name;
                    this.updateData.lastname = this.profileData.lastname;
                    this.updateData.username = this.profileData.username;
                    this.updateData.type = this.user.type;
                })
                .catch(error => {

                })
        }

    	},
    	mounted () {
            this.getUser();
        }
});