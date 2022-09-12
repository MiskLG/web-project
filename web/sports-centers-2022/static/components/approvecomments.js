Vue.component("comments-overview", {
	data: function () {
	    return {
			
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            comments : ""
	    }
	},
	    template: `
		<div>
            <div v-if="user.type =='ADMIN'">
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
                <div class="col justify-content-center">
                    <div class="d-flex fs-2 bg-secondary rounded justify-content-center text-light row m-2">
                        Comments
                    </div>
                    <div v-for="c in comments" class="row fs-4 m-2">
                        <div class="bg-secondary rounded">
                            <div class="row my-2">
                                <div class="col-md-6">
                                <label class="text-light">User:</label>
                                </div>
                                <div class="col-md-6">
                                <span class="text-info mx-2">{{c.user}} </span>
                                </div>
                            </div>
                            <div class="row my-2">
                                <div class="col-md-6">
                                <label class="text-light">Comment text:</label>
                                </div>
                                <div class="col-md-6">
                                <span class="text-info mx-2">{{c.text}} </span>
                                </div>
                            </div>
                            <div class="row my-2">
                                <div class="col-md-6">
                                <label class="text-light">Rating:</label>
                                </div>
                                <div class="col-md-6">
                                <span class="text-info mx-2">{{c.rating}} </span>
                                </div>
                            </div>
                            <div class="row my-2">
                                <div class="col-md-6 d-flex justify-content-end">
                                    <button class="btn btn-primary m-1" type="button" @click="disapprove(c.user, c.facility)">
                                        Disapprove
                                    </button>
                                </div>
                                <div class="col-md-6 d-flex justify-content-start">
                                    <button class="btn btn-primary m-1" type="button" @click="approve(c.user, c.facility)">
                                        Approve
                                    </button>
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
                            this.getUnapproved();
						}
                        else{
                            router.push("/")
                        }
					}).catch(error => {
                        
                    })	
			},
			
            getUnapproved : function() {
                axios.get("/rest/comments/getUnapproved")
                    .then(response => {
                        this.comments = response.data;
                    })
            },
            approve : function(userid, facilityid) {
                wrapper = {userId: userid, facilityId:facilityid}
                axios.put("/rest/comments/approve", wrapper)
                    .then(response => {
                        alert("Comment has been approved");
                        window.location.reload();
                    })
                    .catch(error => {

                    })
            },
            disapprove : function(userid, facilityid) {
                wrapper = {userId: userid, facilityId:facilityid}
                axios.put("/rest/comments/disapprove", wrapper)
                    .then(response => {
                        alert("Comment has been disapproved");
                        window.location.reload();
                    })
                    .catch(error => {

                    })
            }

	
    	},
    	mounted () {
			this.getUser();
        }
});