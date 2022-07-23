Vue.component("login", {
	data: function () {
	    return {
	      username: null,
	      password: null
	    }
	},
	    template: `
    	<div>
    		<h3>Prikaz proizvoda</h3>
    		<table border="1">
	    		<tr bgcolor="lightgrey">
	    			<th>Naziv</th>
	    			<th>Cena</th>
	    		</tr>
	    	</table>
    	</div>
    	`,
    mounted () {
    },
});