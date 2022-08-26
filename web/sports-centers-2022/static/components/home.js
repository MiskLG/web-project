Vue.component("home", {
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
			<button class="btn btn-primary mx-4 mt-1" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
				Show search options
			</button>
			<form>
			<div class="bg-secondary rounded text-light row mx-4 mt-2 collapse border border-primary" id="collapseExample">
				<div class="mx-4 mt-1 col-md-4 pt-1 ">
					<div class="row m-2">
						<label> Search parameters:</label>
					</div>	
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label col-md-6">Name:</label>
							</div>
							<div class="col-md-8 align-center justify-content-center">
								<input type="text" class="form-control" placeholder="name of facility"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">Type:</label>
							</div>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder="type of facility"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">City:</label>
							</div>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder="name of the city"/>
							</div>
						</div>
					</div>
					<div class="row m-2">
						<div class="row">
							<div class="col-md-2 pt-1">
								<label class="form-label">Rating:</label>
							</div>
							<div class="col-md-8">
								<input type="text" class="form-control" placeholder="number from 1 to 5" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 mx-4 mt-1 pt-1 bg-secondary">
					<div class="row m-2">
						<label>Sort by: </label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
						<label class="form-check-label" for="flexRadioDefault1">
						Name
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
						<label class="form-check-label" for="flexRadioDefault2">
						Location
						</label>
					</div>
					<div class="form-check row-md-4 m-2">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked>
						<label class="form-check-label" for="flexRadioDefault2">
						Rating
						</label>
					</div>
				</div>
				<div class="col-md-3 mx-4 mt-1 pt-1 bg-secondary">
					<div class="row-md-3 m-2">
						<label>Show only (filter): </label>
					</div>
					<div class="row-md-3 m-2">
						<label>Type:<label>
						<select class="form-select form-select-sm mx-3">
							<option>Show all</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
					  	</select> 
					</div>
					<div class="row-md-3 m-2">
						<label>Open status:<label>
						<select class="form-select form-select-sm mx-3">
							<option>Show all</option>
							<option>Opened</option>
							<option>Closed</option>
					  	</select> 
					</div>
				</div>
				<div class="d-flex justify-content-center align-center row m-1">
					<button type="submit" class="btn btn-dark">
						Search
					</button>
				</div>
			</div>
			
			</form>
			<div class="m-4 bg-primary row" style="height: 400px" >
				<div class="col-md-3 m-3 bg-secondary">
				test
				</div>
				<div class="col-md-8 m-3 bg-info">
				test
				</div>
			</div>
		</div>
    	`,
    methods : {
    		editProduct : function () {
    			event.preventDefault();
    			if (this.id != -1){
    				axios.put('rest/products/edit/' + this.product.id, this.product).
    				then(response => (router.push(`/`)));
    			}
    			else{
    				axios.post('rest/products/add', this.product).
    				then(response => (router.push(`/`)));
    			}
    		}
    	},
    	mounted () {
			axios.get('rest/centers/getall')
			then(response => centers);
        }
});