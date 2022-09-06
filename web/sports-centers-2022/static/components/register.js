Vue.component("register", {
	data: function () {
	    return {

			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            message: "",
			registerData: {username: "", name:"", lastname:"", password:"", password2:"", gender:"", date:"",}
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
                    <li v-if="user.type == 'ADMIN' "class="nav-item">
                    <a class="nav-link active" aria-current="page" @click="promoCodesMove" href="#/promo-codes">Define promo codes</a>
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
            <form>
                <div class="bg-secondary rounded text-light row mx-4 mt-2 border border-primary">
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Username:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="registerData.username" type="text" class="form-control" placeholder="username"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Name:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="registerData.name" type="text" class="form-control" placeholder="name"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Lastname:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="registerData.lastname" type="text" class="form-control" placeholder="lastname"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Password:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="registerData.password" type="password" class="form-control" placeholder="password"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Repeat password:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="registerData.password2" type="password" class="form-control" placeholder="repeat password"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Date of Birth:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="registerData.date" class="form-control date input-group" type="date" data-provide="datepicker"  />
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Gender:</label>
                        </div>
                        <div class="form-check col-md-2 m-2">
                            <input v-model="registerData.gender" value="male" class="form-check-input" type="radio" name="searchRadio" />
                            <label class="form-check-label" for="searchRadio">
                            Male
                            </label>
					    </div>
                        <div class="form-check col-md-2 m-2">
                            <input v-model="registerData.gender" value="female" class="form-check-input" type="radio" name="searchRadio" />
                            <label class="form-check-label" for="searchRadio">
                            Female
                            </label>
					    </div>
                    </div>
                    <div class="d-flex justify-content-center align-center row m-1">
					<button @click="register" type="button" class="btn btn-primary">
						Register
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
        registerMove : function () {
            router.push('/register');
            window.location.reload();
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
        promoCodesMove : function () {
            router.push('/promo-codes');
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
        register : function () {
            this.registerData.username.trim();
            this.registerData.name.trim();
            this.registerData.lastname.trim();
            this.registerData.password.trim();
            this.registerData.password2.trim();
            
            if(this.registerData.username.length === 0 || this.registerData.name.length === 0 || this.registerData.lastname.length === 0 
                || this.registerData.password.length === 0 || this.registerData.password2.length === 0 || this.registerData.gender.length === 0
                || this.registerData.date.length === 0) {
                alert("Every field must be filled");
                return;
            }
            if (this.registerData.password != this.registerData.password2) {
                alert("Password does not match");
                return;
            }
            
            axios.post('rest/buyer/register', this.registerData).
                then(response => {
                    this.user = response.data;
                    router.push('/');
                })	
                .catch(error => {
                    alert("User with that username already exists");
                })
            
            
        },
    	},
    	mounted () {
        }
});