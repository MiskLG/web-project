Vue.component("edit-workout", {
	data: function () {
	    return {
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},
            
            workoutId: "",
            coaches: "",
            workoutHelp: {name: ""},
            workout: {id:"", type: "",duration:"", description:"", coach:""},
            workoutData: ""
	    }
	},
	    template: `
		<div>
            <div v-if="user.type == 'MANAGER'">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark border border-secondary">
				<div class="container-fluid">
					<a class="navbar-brand" href="#/">SportsCenters</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
						Menu
					</button>
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
						<div v-if="user.username != ''"  class="dropdown">
							<button class="btn btn-secondary dropdown-toggle" style="min-width:120px" type="button" data-bs-toggle="dropdown" aria-expanded="false" >
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
                <div class="d-flex justify-content-center row-6 m-4 ">
                    <div class="row bg-secondary rounded">
                        <div class="col-md-4">
                            <img :src="workoutData.photo" class="m-1 img-thumbnail"/>
                        </div>
                        <div class="col-md-8 fs-5">
                            <div class="row my-2">
                                <label class="text-light">Name: <span class="text-info mx-2">{{workoutData.name}}</span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Type: <span class="text-info mx-2">{{workoutData.type}}</span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Duration: <span class="text-info mx-2">{{workoutData.duration}}min </span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Coach: <span class="text-info mx-2">{{workoutData.coachUsername}} - {{workoutData.coachName}} {{workoutData.coachLastname}}</span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Description: <span class="text-info mx-2">{{workoutData.description}}</span></label>
                            </div>
                        </div>
                    </div>
                </div>
			<form>
                <div class="bg-secondary rounded text-light row mx-4 mt-2 border border-primary">
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Name:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="workoutHelp.name" type="text" class="form-control" Disabled placeholder="name of workout (unique)"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Type:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="workout.type" type="text" class="form-control" placeholder="type of a workout"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Duration:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="workout.duration" type="number" class="form-control" placeholder="in minutes (optional)"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Description:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <textarea v-model="workout.description" class="form-control" id="exampleFormControlTextarea1" placeholder="(optional)" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Image:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input class="form-control date input-group" Disabled type="file"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Coach:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <select v-model="workout.coach" class="form-select form-select-sm">
                                <option v-for="c in coaches" :value="c.username">
                                    {{c.username}} - {{c.name}} {{c.lastname}}
                                </option>
                            </select>
                        </div>
                     </div>   
                    
                    <button @click="editWorkout" type="button" class="btn btn-primary">
						Edit Workout
					</button>
                </div>
            </form>
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
                            if (this.user.type != "MANAGER" ) {
                                router.push("/");
                                window.location.reload();
                            }
							this.checkFacility();
						}
                        else{
                            router.push("/");
                            window.location.reload();
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
            
			
            editWorkout : function () {
                this.workout.type.trim();
                this.workout.description.trim();
                this.workout.duration.trim();
                if(this.workout.type.length === 0 || this.workout.coach.length === 0) {
                    alert("Only optional fields can be left empty");
                    return;
                }

                axios.put('rest/workouts/update',this.workout)
                    .then(response => {
                        router.push('/facility-overview');
                    })
                    .catch(error => {

                    });
                
                
            },
            getCoaches : function() {
                axios.get('/rest/coaches/getAll').
                    then(response => {
                        if(response.status == 204) {
                            alert("There are no coaches, please add some first or ask someone that can");
                            router.push('/');
                        }
                        else{
                            this.coaches = response.data;
                        }
                    })
                    .catch(error => {
                    })
            },
			checkFacility : function() {
				axios.get('/rest/managers/checkFacility', {params : {username: this.user.username}}).
					then(response => {
						if(response.status == 204) {
							alert("You are currently not manager of any facility, please ask admin to add you to your facility");
                            router.push('/');
						}
					})
					.catch(error => {
						router.push('/');
					})
			},
            getData : function() {
                axios.get('/rest/workouts/getById', {params : {id: this.workoutId}})
                    .then(response => {
                        this.workoutData = response.data;
                        this.workout.id = this.workoutData.id;
                        this.workoutHelp.name = this.workoutData.name;
                        this.workout.type = this.workoutData.type;
                        this.workout.duration = this.workoutData.duration;
                        this.workout.description = this.workoutData.description;
                        this.workout.coach = this.workoutData.coachUsername;
                    })
                    .catch(error => {

                    })
            }

    	},
    	mounted () {
            this.workoutId = this.$route.params.id;
			this.getUser();
            this.getCoaches();
            this.getData();
        }
});