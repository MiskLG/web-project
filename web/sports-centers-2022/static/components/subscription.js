Vue.component("subscription", {
	data: function () {
	    return {
			user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            discount: "",
            discountCode: "",
            discountCode2: "",
            code: "",
            originalPrice: "",
            sub: "",
            subscriptionId: "",
            wrapper: {sub:"", username:"", discountCode:""},
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
                    <label class="fs-2 justify-content-center d-flex">Subscription</label>   
                </div>
                <div class="row fs-4 mx-4 my-1 bg-secondary rounded">
                    <div class="row">
                        <div class="col-md-4">
                            <label class="fs-5 text-light">Type:</label>
                        </div>
                        <div class="col-md-8">
                            <span class="mx-1 text-info">{{sub.type}}</span> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="fs-5 text-light">Number of appointments:</label>
                        </div>
                        <div class="col-md-8">
                            <span class="mx-1 text-info">{{sub.numberOfAppointments}}</span> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="fs-4 text-light">Duration from:</label>
                        </div>
                        <div class="col-md-8">
                            <span class="mx-1 text-info">{{sub.date1}}</span> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="fs-5 text-light">Duration to:</label>
                        </div>
                        <div class="col-md-8">
                            <span class="mx-1 text-info">{{sub.date2}}</span> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="fs-5 text-light">Price:</label>
                        </div>
                        <div class="col-md-8">
                        <span class="mx-1 text-info">{{sub.price}}</span> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="fs-5 text-light">Promo code:</label>
                        </div>
                        <div class="col-md-8 d-flex">
                            <div class="col-md-5">
                                <input v-model="code" type="text" class="form-control" placeholder="promo code"/>
                            </div>
                            <div class="col-md-5">
                                <button class="ms-2 btn btn-primary col-md-6" type="button" @click="getCodeDiscount">
                                    Enter Code
                                </button>
                            </div>
                           
                        </div>
                    </div>
    
                </div>
                <div class="row">
                    <button class="ms-2 btn btn-primary" type="button" @click="subscribe()">
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
                            getUserSub();
						}
                        else{
                            router.push("/");
                        }
					}).catch(error => {
                        
                    })	
			},
			

            getSubscription : function() {
                axios.get("/rest/subscriptions/getTemplate", {params: {id: this.subscriptionId}})
                    .then(response => {
                        if(response.status == 204) {

                        }
                        else {
                            this.sub = response.data;
                            this.originalPrice = this.sub.price;
                            this.getUserDiscount();
                        }
                    })
                    .catch(error => {

                    })
            },
            getUserDiscount : function() {
                axios.get("/rest/buyer/getDiscount", {params: {id: this.user.username}})
                    .then(response => {
                        this.discount = response.data;
                        if(this.discount != 0) {
                            this.sub.price = this.originalPrice - this.originalPrice * this.discount / 100.;
                        }
                        
                        this.originalPrice = this.sub.price;
                    })
                    .catch( error => {
                    });
            },
            getCodeDiscount : function() {
                axios.get("/rest/promocodes/getById", {params: {id: this.code.trim()}})
                    .then(response => {
                        if (response.status == 204) {
                            alert("Code is not valid");
                            return;
                        }
                        this.discountCode = response.data;
                        this.discountCode2 = this.code;
                        
                        if(this.discountCode.discount != 0) {
                            this.sub.price = this.originalPrice - this.originalPrice * this.discountCode.discount /100.;
                        }
                    })
                    .catch( error => {
                    });
            },
            subscribe : function() {
                this.wrapper = {sub: this.sub, username: this.user.username, discountCode: this.discountCode2};
                axios.post("/rest/buyer/subscribe", this.wrapper)
                    .then(response => {
                        alert("Successfully subscribed");
                        router.push("/");
                    })
            }


    	},
    	mounted () {
			this.getUser();
			this.subscriptionId = this.$route.params.id;
            this.getSubscription();

        }
});