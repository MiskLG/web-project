Vue.component("user-workouts", {
	data: function () {
	    return {
			
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            pastWorkouts: "",
            futureWorkouts: ""
	    }
	},
	    template: `
		<div>
            <div v-if="user.type=='BUYER' || user.type=='COACH'">
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
                                <li v-if="user.type == 'MANAGER' "class="nav-item">
                                <a class="nav-link active" aria-current="page" @click="changePage('/approve-arrival')" href="#/approve-arrival">Approve arrivals</a>
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
                                    <li><a class="dropdown-item" @click="changePage('edit-profile')" href="#edit-profile">Edit Profile</a></li>
                                    <li><a v-if="user.type=='BUYER'" @click="changePage('subscriptions')" class="dropdown-item" href="#subscriptions" >Subscription</a></li>
                                    <li><a class="dropdown-item" @click="logout">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
                <div class="d-flex row">
                    <div class="col-md-6 justify-content-center">
                        <div class="bg-secondary rounded m-2 row">
                            <div class="d-flex justify-content-center row fs-2 text-light">
                                Past workouts
                            </div>
                            <div class="row" v-for="w in pastWorkouts">
                                <div class="col-md-4 ">
                                    <img :src="w.base.photo" class="img-thumbnail"/>
                                </div>
                                <div class="col-md-8 fs-5">
                                    <div class="row my-2">
                                        <label class="text-light">Facility Name: <span class="text-info mx-2">{{w.facility}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Name: <span class="text-info mx-2">{{w.base.name}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Type: <span class="text-info mx-2">{{w.base.type}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Duration: <span class="text-info mx-2">{{w.base.duration}}min </span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Coach: <span class="text-info mx-2">{{w.base.coachUsername}} - {{w.base.coachName}} {{w.base.coachLastname}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Description: <span class="text-info mx-2">{{w.base.description}}</span></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 justify-content-center">
                        <div class="bg-secondary rounded m-2 row">
                            <div class="d-flex justify-content-center row fs-2 text-light">
                                Future workouts
                            </div>
                            <div class="row" v-for="w in futureWorkouts">
                                <div class="col-md-4 ">
                                    <img :src="w.base.photo" class="img-thumbnail"/>
                                </div>
                                <div class="col-md-8 fs-5">
                                    <div class="row my-2">
                                        <label class="text-light">Facility Name: <span class="text-info mx-2">{{w.facility}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Name: <span class="text-info mx-2">{{w.base.name}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Type: <span class="text-info mx-2">{{w.base.type}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Duration: <span class="text-info mx-2">{{w.base.duration}}min </span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Coach: <span class="text-info mx-2">{{w.base.coachName}} {{w.base.coachLastname}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Description: <span class="text-info mx-2">{{w.base.description}}</span></label>
                                    </div>
                                    <div class="row my-2">
                                        <label class="text-light">Date: <span class="text-info mx-2">{{w.date}}</span></label>
                                    </div>
                                </div>
                            </div>
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
                            this.getWorkoutsPast();
						}
                        else{
                            router.push("/");
                        }
					}).catch(error => {
                        
                    })	
			},
			editProfile() {
				if (user.username.length === 0) {
					return;
				}
				router.push('edit-profile');
				window.location.reload();
			},
			
            getWorkoutsPast  : function() {
                axios.get('/rest/workoutHistory/getPast', {params: {id: this.user.username, type: this.user.type}})
                    .then(response => {
                        this.getWorkoutsFuture();
                        if(response.status == 204){
                            return;
                        }
                        this.pastWorkouts = response.data;
                    })
                    .catch(error => {
                        alert("Unknown error has uccured, please check later");
                        router.push("/");
                    })
            },
            getWorkoutsFuture : function() {
                axios.get('/rest/workoutHistory/getFuture', {params: {id: this.user.username, type: this.user.type}})
                    .then(response => {
                        if(response.status == 204){
                            return;
                        }
                        this.futureWorkouts = response.data;
                    })
                    .catch(error => {
                        alert("Unknown error has uccured, please check later");
                        router.push("/");
                    })
            },

    	},
    	mounted () {
			this.getUser();
        }
});