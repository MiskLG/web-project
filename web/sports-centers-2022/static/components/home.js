Vue.component("home", {
	data: function () {
	    return {
	      username: null,
	      password: null
	    }
	},
	    template: `
		<div>
			<nav class="navbar navbar-expand-lg bg-light">
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
							<a class="nav-link" href="#">Link</a>
							</li>
							
							<li class="nav-item">
							<a class="nav-link disabled">Disabled</a>
							</li>
						</ul>
						<div class="dropdown">
							<button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
								Dropdown button
							</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">Action</a></li>
								<li><a class="dropdown-item" href="#">Another action</a></li>
								<li><a class="dropdown-item" href="#">Something else here</a></li>
							</ul>
						</div>
					</div>
				</div>
		
			</nav>
			<form>
				<div class="mb-3" >
					<label class="form-label">Username</label>
					<input class="form-control" type="text" placeholder="username" aria-label="username">
				</div>
				<div class="mb-3" >
					<label class="form-label">Password</label>
					<input class="form-control" type="password" placeholder="password" aria-label="password">
				</div>
				<button type="submit" class="btn btn-primary" >Primary</button>
			<form>
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
    		axios.get('rest/add')
        }
});