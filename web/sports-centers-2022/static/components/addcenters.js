Vue.component("add-centers", {
	data: function () {
	    return {
	    	user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            center: {name: "",type: "",latitude: "",longitude: "",city: "",
                    street: "", stNumber: "", poNumber: "", image: "",
                     startTime: "", endTime: ""} 
	    }
	},
	    template: `
		<div>
        <div v-if="user.type == 'ADMIN'">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark border border-secondary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#/">SportsCenters</a>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#/" >Home</a>
                        </li>
                        <li v-if="user.type == 'BUYER'" class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#/workouts">Workouts</a>
                        </li>
                        <li v-if="user.type == 'ADMIN' "class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#/add-centers">Add Centers</a>
                        </li>
                        <li v-if="user.type == 'ADMIN' "class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#/add-managers">Add Managers</a>
                        </li>
                        <li v-if="user.type == 'ADMIN' "class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#/add-coaches">Add Coaches</a>
                        </li>
                        <li v-if="user.type == 'ADMIN' "class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#/all-users">All Users</a>
                        </li>
                        <li v-if="user.type == 'MANAGER' "class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#/add-workouts">Add workouts</a>
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
                            <label class="form-label col-md-6">Name:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.name" type="text" class="form-control" placeholder="username"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Type:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.type" type="text" class="form-control" placeholder="name"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Latitude:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.latitude" type="text" class="form-control" placeholder="lastname"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Longitude:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.longitude" type="text" class="form-control" placeholder="password"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">City:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.city" type="text" class="form-control" placeholder="repeat password"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Street:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.street" class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Street number:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.stNumber" class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Post Office number:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.poNumber" class="form-control" type="number"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Start time:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.startTime" class="form-control" type="text" />
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">End time:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.endTime" class="form-control" type="text" />
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Image:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input class="form-control date input-group" type="file" @change="onFileInput($event)"/>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center align-center row m-1">
					<button @click="addFacility" type="button" class="btn btn-primary">
						Add Facility
					</button>
				</div>
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
                        })	
                }
                console.log(this.user);
                setTimeout(function(){
                    window.location.reload();
                }, 500);
                
            },
            logout : function () {
                this.user.username = "";
                this.user.type = "";
                axios.post('rest/user/logout');
                console.log(this.user);
                setTimeout(function(){
                    window.location.reload();
                }, 500);
            },
            registerMove : function () {
                console.log(this.searchParameters.name);
                console.log(this.sortParameter)
                router.push('/register');
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
            getUser : function () {
                axios.get('rest/user/current').
                    then(response => {
                        if(response.data != 'NOUSER') {
                            this.user = response.data;
                        }
                    })	
            },


            addFacility: function(){
                this.trimData();
                if(this.center.name.length === 0 || this.center.type.length === 0 || this.center.latitude.length === 0 ||
                    this.center.longitude.length === 0 || this.center.city.length === 0 || this.center.street.length === 0 ||
                    this.center.stNumber.length === 0 || this.center.stNumber.length === 0 || this.center.poNumber.length === 0 ||
                    this.center.startTime.length === 0 || this.center.endTime.length === 0) {
                        alert("Every field must be filled");
                        return;
                    }
                axios.post('rest/centers/add',this.center);
                setTimeout(function(){
					window.location.reload();
				}, 500);
            },
            trimData : function() {
                this.center.name.trim();
                this.center.type.trim();
                this.center.latitude.trim();
                this.center.longitude.trim();
                this.center.city.trim();
                this.center.street.trim();
                this.center.stNumber.trim();
                this.center.stNumber.trim();
                this.center.poNumber.trim();
                this.center.startTime.trim();
                this.center.endTime.trim();
            },
            onFileInput: function(e){
                var patternFileExtension = /\.([0-9a-z]+)(?:[\?#]|$)/i;
                var files = e.target.files;
                if(!files.length){
                    return;
                }
                var fileExtension = (files[0].name).match(patternFileExtension)[1];
                if(fileExtension=="png" || fileExtension=="jpg" || fileExtension=="jpeg" || fileExtension=="gif"){
                    this.createImage(files[0]);
                }
                else{
                    alert("Chosen file must be an image");
                    this.removeImage();
                }
            },
            createImage: function(file){
                var reader = new FileReader();
    
                reader.onload = (e) =>{
                    this.center.image = e.target.result;
                };
                reader.readAsDataURL(file);
            },
            removeImage: function(){
                this.center.image="";
                this.$refs.imgUpload.value = null;
            }
    	},
    	mounted () {
            this.getUser();
        }
});