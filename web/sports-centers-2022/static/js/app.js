const Home = {template: '<home></home>' }
const Login = {template: '<login></login>'}
const Register = {template: '<register></register>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', component: Home},
		{ path: '/login', component: Login},
		{ path: '/register', component: Register},
	  ]
});

var app = new Vue({
	router,
	el: '#app',
	data:{
		login:'',
		username:''
	}
});