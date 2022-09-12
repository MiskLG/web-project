Vue.component("subscriptions", {
	data: function () {
	    return {
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

			subscriptions: "",
            currentSubscription: ""
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
			<div class="row m-4 bg-secondary rounded">
                <div class="row m-2 justify-content-center text-light">
                    <label class="fs-2 justify-content-center d-flex"> Current subscription</label>   
                </div>
                <div class="row m-2 justify-content-center text-light" v-if="currentSubscription.length === 0">
                    <label class="fs-2 justify-content-center d-flex"> No current subscription</label>
                </div>
                <div class="row m-2 justify-content-center text-light" v-if="currentSubscription.length !== 0">
                    <div class="row fs-4 mx-4 my-1 bg-secondary rounded">
                        <div class="row">
                            <div class="col-md-4">
                                <label class="fs-5 text-light">Status:</label>
                            </div>
                            <div class="col-md-8">
                            <span class="mx-1 text-info">{{currentSubscription.status}}</span> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label class="fs-5 text-light">Type:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="mx-1 text-info">{{currentSubscription.type}}</span> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label class="fs-5 text-light">Number of appointments:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="mx-1 text-info">{{currentSubscription.numberOfAppointments}}</span> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label class="fs-4 text-light">Duration from:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="mx-1 text-info">{{currentSubscription.date1}}</span> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label class="fs-5 text-light">Duration to:</label>
                            </div>
                            <div class="col-md-8">
                                <span class="mx-1 text-info">{{currentSubscription.date2}}</span> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row m-4 bg-secondary rounded mt-6">
                <div class="row m-2 justify-content-center text-light">
                    <label class="fs-2 justify-content-center d-flex"> Subscription offers</label>   
                </div>
            </div>
            <div class="row mx-4 my-1 bg-secondary rounded" v-for="s in subscriptions">
                <label class="fs-5 text-light col-md-3">Type: <span class="mx-1 text-info">{{s.type}}</span> </label>
                <label class="fs-5 text-light col-md-3">Price: <span class="mx-1 text-info">{{s.price}}</span> </label>
                <label class="fs-5 text-light col-md-3">Number of Appointments: <span class="mx-1 text-info">{{s.numberOfAppointments}}</span> </label>
                <div class="col-md-3">
                    <button class="btn btn-primary" type="button" @click="subscribe(s.id)">
                        Subscribe
                    </button>
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
                            this.getUserSub();
						}
                        else{
                            router.push("/");
                        }
					}).catch(error => {
                        
                    })	
			},

			getSubs : function() {
                axios.get("/rest/subscriptions/getAll")
                    .then(response => {
                        this.subscriptions = response.data;
                    })
                    .catch(error => {

                    })
            },
            getUserSub : function() {
                axios.get("rest/buyer/getSubscription", {params: {id: this.user.username}})
                    .then(response => {
                        if(response.status == 204) {

                        }
                        else {
                            this.currentSubscription = response.data;
                        }
                    })
                    .catch(error => {

                    })
            },
            subscribe : function(id) {
                router.push(`/subscriptions/${id}`);
            }

    	},
    	mounted () {
			this.getUser();
			this.getSubs();
        }
});