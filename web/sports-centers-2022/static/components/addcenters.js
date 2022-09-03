Vue.component("addcenters", {
	data: function () {
	    return {
			centers: null,
			user: null,
	    	username: null,
	    	password: null
	    }
	},
	    template: `
		<div>
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark border border-secondary">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">SportsCenters</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="#">Home</a>
							</li>
							<li class="nav-item">
							<a class="nav-link active" aria-current="page" href="#">Workouts</a>
							</li>
						</ul>
						<div class="dropdown dropstart">
							<button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
								Profile
							</button>
							<ul class="dropdown-menu dropdown-menu-dark">
								<li><a class="dropdown-item" href="#">Action</a></li>
								<li><a class="dropdown-item" href="#">Another action</a></li>
								<li><a class="dropdown-item" href="#">Something else here</a></li>
							</ul>
						</div>
					</div>
				</div>
			</nav>			
		</div>
    	`,
    methods : {
            addCenter: async function(){
                var canAdd = await this.canAddCenter();
                if(canAdd){
                    if(this.checkData()){
                        var SportsCenter = {centerId: 0, centerTitle: this.centerTitle, type: this.type, status:"CLOSED",
                        location:{ latitude: this.latitude, longitude: this.longitude, address: {street: this.street, streetNumber: this.streetNumber, city:this.city, zipCode:this.zipCode}},
                        logoPath: this.image.split(",")[1], grade: 0, workHours:[this.workHoursStart, this.workHoursEnd]
                        };
                        await this.axiosAddCenter(SportsCenter);
                        this.axiosEditManager();
                    
                    }
                    else{
                        this.error="Sva polja moraju biti popunjena";
                    }
                }
                else{
                    this.error="Sva polja moraju biti popunjena";
                }
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
                    alert("odabrani file mora biti slika");
                    this.removeImage();
                }
            },
            createImage: function(file){
                var reader = new FileReader();
    
                reader.onload = (e) =>{
                    this.image = e.target.result;
                };
                reader.readAsDataURL(file);
            },
            removeImage: function(){
                this.image="";
                this.$refs.imgUpload.value = null;
            }
    	},
    	mounted () {
        }
});