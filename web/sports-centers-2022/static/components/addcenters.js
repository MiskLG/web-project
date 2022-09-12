Vue.component("add-centers", {
	data: function () {
	    return {
	    	user: {username:"", type:""},
	    	credentials: {username: "", password: ""},

            mapClicked : false,
            managers: "",
            map: "",
            center: {name: "",type: "",latitude: "",longitude: "",city: "",
                    street: "", stNumber: "", poNumber: "", image: "",
                     startTime: "", endTime: "", managerUsername: ""} 
	    }
	},
	    template: `
		<div>
        <div v-if="user.type == 'ADMIN'">
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
                    <div class="row m-2 d-flex justify-content-center">
                        <div id="map-create" style="height:500px;width:1200px"></div>   
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Name:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.name" type="text" class="form-control" placeholder="name"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Type:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.type" type="text" class="form-control" placeholder="type"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Latitude:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.latitude" type="text" class="form-control" placeholder="latitude (number)"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Longitude:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.longitude" type="text" class="form-control" placeholder="longitude (number)"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">City:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.city" type="text" class="form-control" placeholder="city"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Street:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.street" class="form-control" type="text" placeholder="street" />
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Street number:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.stNumber" class="form-control" type="text" placeholder="street number"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Post Office number:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.poNumber" class="form-control" type="number" placeholder="po. number"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">Start time:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.startTime" class="form-control" type="text" placeholder="start time in format hh:mm"/>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                            <label class="form-label col-md-6">End time:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <input v-model="center.endTime" class="form-control" type="text" placeholder="end time in format hh:mm"/>
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
                    <div class="row m-2">
                        <div class="col-md-2 pt-1">
                             <label class="form-label col-md-6">Manager:</label>
                        </div>
                        <div class="col-md-8 align-center justify-content-center">
                            <select v-model="center.managerUsername" class="form-select form-select-sm">
                                <option v-for="m in managers" :value="m.username">
                                    {{m.username}} - {{m.name}} {{m.lastname}}
                                </option>
                            </select>
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
                            if (this.user.type != "ADMIN" ) {
                                router.push("/");
                                window.location.reload();
                            }
                            this.displayMap();
						}
                        else{
                            router.push("/");
                            window.location.reload();
                        }
                    }).catch(error => {
                        
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
                if(isNaN(this.center.latitude) || isNaN(this.center.longitude)) {
                    alert("Latitude and Logitude must be a number");
                    return;
                }
                if(!/[0-9]?[0-9]:[0-9][0-9]/.test(this.center.endTime) || !(/[0-9]?[0-9]:[0-9][0-9]/.test(this.center.startTime))) {
                    alert("Start or End time are not in good format, use hh:mm format");
                    return;
                }
                if(parseInt(this.center.startTime.split(":")[0]) > parseInt(this.center.endTime.split(":")[0])) {
                    alert("End time must be bigger than Start time");
                    return;
                }
                else if (parseInt(this.center.startTime.split(":")[0]) == parseInt(this.center.endTime.split(":")[0]) && parseInt(this.center.startTime.split(":")[1]) > parseInt(this.center.endTime.split(":")[1])) {
                    alert("End time must be bigger than Start time");
                    return;
                }

                axios.post('rest/centers/add',this.center)
                    .then(response => {
                        window.location.reload();
                    })
                    .catch(error => {

                    });
            },
            trimData : function() {
                this.center.name.trim();
                this.center.type.trim();
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
            },
            getManagers : function() {
                axios.get('/rest/managers/getFree').
                    then(response => {
                        if(response.status == 204) {
                            alert("There are no free managers, please add some first");
                            router.push('/add-managers');
                        }
                        else{
                            this.managers = response.data;
                        }
                    })
                    .catch(error => {
                    })
            },
            displayMap: function () {

                let lat = 45.2396;
                let lon = 19.8227;
                let map = new ol.Map({
                    layers: [
                        new ol.layer.Tile({
                            source: new ol.source.OSM()
                        })
                    ],
                    view: new ol.View({
                        center: ol.proj.fromLonLat([lon, lat]),
                        zoom: 10
                    })
                });
    
                setTimeout(() => {
                    if (map) {
                        map.setTarget("map-create");
                        let c = document.getElementById("map-create").childNodes;
                        c[0].style.borderRadius = '15px';
                    }
                }, 50);
    
                map.on('click', evt => {
                    let coord = ol.proj.toLonLat(evt.coordinate);
                    this.reverseGeocode(coord);
                    this.mapClicked = true;
                })
            },
            reverseGeocode: function (coords) {
                fetch('http://nominatim.openstreetmap.org/reverse?format=json&lon=' + coords[0] + '&lat=' + coords[1])
                    .then(function (response) {
                        return response.json();
                    }).then(json => {
                    this.center.longitude = coords[0];
                    this.center.latitude = coords[1];
                    if (json.address.city_district) {
                        this.center.city = json.address.city_district;
                    }
                    else if (json.address.city) {
                        this.center.city = json.address.city;
                    } 
    
                    if (json.address.road) {
                        this.center.street = json.address.road;
                    }
    
                    if (json.address.house_number) {
                        this.center.stNumber = json.address.house_number;
                    }
    
                    if (json.address.postcode) {
                        this.center.poNumber = json.address.postcode;
                    }
    
                });
            },
            geocode: async function (street, city) {
                await fetch('http://nominatim.openstreetmap.org/search?format=json&street=' + street + '&city=' + city + '&country=srbija')
                    .then(function (response) {
                        return response.json();
                    }).then(json => {
                        this.center.longitude = json[0].lon;
                        this.center.latitude = json[0].lat;
                    });
            }
    	},
    	mounted () {
            this.getUser();
            this.getManagers();
            
        }
});