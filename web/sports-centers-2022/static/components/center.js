Vue.component("center", {
	data: function () {
	    return {
			
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            passedId: "",
			content: "",
            workouts: "",
            comments: ""
	    }
	},
	    template: `
		<div>
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
                                    <li><a v-if="user.type=='BUYER'" @click="changePage('subscription')" class="dropdown-item" href="#subscription" >Subscription</a></li>
                                    <li><a class="dropdown-item" @click="logout">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
                <div class="d-flex justify-content-center m-4 bg-secondary row rounded" >
                    <div class="row mb-3">
                        <label class="col-6 fs-1 d-flex justify-content-end text-light"> {{content.name}} </label>
                        <label class="col-6 fs-4 d-flex justify-content-end text-light"> Rating:  <span class="mx-2 text-info">{{content.rating}}/5</span> </label>
                    </div>
                    <div class="row m-3">
                        <div class="col-md-5 ">
                            <img :src="content.image" class="img-thumbnail"/>
                        </div>
                        <div class="col-md-6 bg-secondary fs-3">       
                            <div class="row my-2">
                                <label class="text-light">Type: <span class="text-info mx-2">{{content.type}}</span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Status: <span class="text-info mx-2">{{content.status}}</span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Work hours: <span class="text-info mx-2">{{content.startTime}}h - {{content.endTime}}h </span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Location: <span class="text-info mx-2">{{content.city}}, {{content.poNumber}}, {{content.street}} {{content.stNumber}} </span></label>
                            </div>
                            <div class="row my-2">
                                <label class="text-light">Longitude: <span class="text-info mx-2">{{content.longitude}}</span> Latitude: <span class="text-info mx-2">{{content.latitude}}</span></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mx-4">
                    <div class="col-6 justify-content-center bg-secondary rounded ">
                        <div class="d-flex justify-content-center row fs-2 text-light">
                            Workouts
                        </div>
                        <div v-for="w in workouts" class="row">
                            <div class="col-md-4 ">
                                <img :src="w.photo" class="img-thumbnail"/>
                            </div>
                            <div class="col-md-8 fs-5">
                                <div class="row my-2">
                                    <label class="text-light">Name: <span class="text-info mx-2">{{w.name}}</span></label>
                                </div>
                                <div class="row my-2">
                                    <label class="text-light">Type: <span class="text-info mx-2">{{w.type}}</span></label>
                                </div>
                                <div class="row my-2">
                                    <label class="text-light">Duration: <span class="text-info mx-2">{{w.duration}}min </span></label>
                                </div>
                                <div class="row my-2">
                                    <label class="text-light">Coach: <span class="text-info mx-2">{{w.coachUsername}} - {{w.coachName}} {{w.coachLastname}}</span></label>
                                </div>
                                <div class="row my-2">
                                    <label class="text-light">Description: <span class="text-info mx-2">{{w.description}}</span></label>
                                </div>
                                <div class="row m-2" v-if="user.type=='BUYER'">
                                    <button class="btn btn-primary" type="button" @click="schedule(w.id)">
                                        Schedule workout
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6 justify-content-center">
                        <div class="d-flex fs-2 bg-secondary rounded justify-content-center text-light">
                            Comments
                        </div>
                        <div v-for="c in comments" class="row fs-4">
                            
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
                            this.getContent();
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
			

			getContent : function() {
                axios.get('/rest/centers/getById', {params : {id: this.passedId}}).
                    then(response => {
                        this.content = response.data;
                        this.getWorkouts();
                        if(this.user.type == 'ADMIN') {
                            this.getAllComments();
                        }
                        else{
                            this.getComments();
                        }
                    })
                    .catch(error => {
                        router.push('/');
                    })
            },
            getWorkouts : function() {
                axios.get('/rest/workouts/getByFacility', {params: {id: this.content.id}})
                    .then(response => {
                        this.workouts = response.data;
                    })
                    .catch(error => {
                
                    })
            },
            getComments : function() {
                axios.get('/rest/comments/getByFacilityApproved', {params: {id: this.content.id}})
                    .then(response => {
                        this.comments = response.data;
                    })
                    .catch(error => {
                      
                    })
            },
            getComments : function() {
                axios.get('/rest/comments/getByFacilityAll', {params: {id: this.content.id}})
                    .then(response => {
                        this.comments = response.data;
                    })
                    .catch(error => {
                      
                    })
            },
            schedule : function(id) {
                router.push(`/schedule/${id}`);
            }
    	},
    	mounted () {
            this.passedId = this.$route.params.id;
			this.getUser();

        }
});