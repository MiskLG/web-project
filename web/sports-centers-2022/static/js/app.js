const Home = {template: '<home></home>' }
const Login = {template: '<login></login>'}
const Register = {template: '<register></register>'}
const AddCenters = {template: '<add-centers></add-centers>'}
const Center = {template: '<show-center></show-center>'}
const Edit = {template: '<edit-profile></edit-profile>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', component: Home},
		{ path: '/login', component: Login},
		{ path: '/register', component: Register},
		{ path: '/add-centers', component: AddCenters},
		{ path: '/center/:id', component: Center},
		{ path: '/profile/:username', component: Edit},
	  ]
});

var app = new Vue({
	router,
	el: '#app',
	data:{
	}
});