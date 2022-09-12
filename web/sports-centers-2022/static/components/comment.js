Vue.component("user-comments", {
	data: function () {
	    return {	
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            centers: "",
            rating: "5",
            text: "",
	    }
	},
	    template: `
		<div>
            <div v-if="user.type == 'BUYER'">
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
								<li><a class="dropdown-item" @click="changePage('edit-profile')" href="#edit-profile">Edit Profile</a></li>
								<li><a v-if="user.type=='BUYER'" @click="changePage('subscriptions')" class="dropdown-item" href="#subscriptions" >Subscription</a></li>
								<li><a class="dropdown-item" @click="logout">Logout</a></li>
							</ul>
						</div>
					</div>
				</div>
			</nav>
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
						<label class="col-10 text-light fs-5">Longitude: <span class="text-info mx-2">{{center.longitude}}</span> Latitude: <span class="text-info mx-2">{{center.latitude}}</span></label>
					</div>
                    <div class="row my-2">
                        <label class="text-light fs-5 col-md-6"> Choose rating:</label>
                        <div class="col-md-6">
                            <select v-model="rating" class="form-select form-select-sm mx-3">
                            <option value="1">One star</option>
                            <option value="2">Two stars</option>
                            <option value="3">Three stars</option>
                            <option value="4">Four stars</option>
                            <option value="5">Five stars</option>
                            </select>
                        </div>
                    </div>
                    <div class="row my-2">
                        <div class="col-md-12 align-center justify-content-center">
                            <textarea v-model="text" class="form-control" id="exampleFormControlTextarea1" placeholder="Write comment here (optional)" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="col-2 d-flex justify-content-end">
                        <button class="btn btn-primary" type="button" @click="comment(center.id)">
                            Comment
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
				axios.get('/rest/user/current').
					then(response => {
						if(response.data != 'NOUSER') {
							this.user = response.data;
                            this.getCenters();
						}
                        else{
                            router.push("/");
                        }
					}).catch(error => {
                        
                    })	
			},

            getCenters : function () {
				axios.get('/rest/comments/getUncommented', {params: {id: this.user.username}}).
					then(response => {
						this.centers = response.data;
					}).catch(error => {
                        
                    })	
			},
			comment : function(id) {
                wrapper = {user: this.user.username, userName: "not needed", facility: id, text: this.text, rating: this.rating}
                axios.post('/rest/comments/add', wrapper)
                    .then(response => {
                        alert("Comment successfully added");
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