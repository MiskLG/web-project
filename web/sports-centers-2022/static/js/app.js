const Home = {template: '<home></home>' }
const Login = {template: '<login></login>'}
const Register = {template: '<register></register>'}
const AddCenters = {template: '<add-centers></add-centers>'}
const Center = {template: '<show-center></show-center>'}
const Edit = {template: '<edit-profile></edit-profile>'}
const AllUsers = {template: '<all-users></all-users>'}
const AddManagers = {template: '<add-managers></add-managers>'}
const AddCoaches = {template: '<add-coaches></add-coaches>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', component: Home},
		{ path: '/login', component: Login},
		{ path: '/register', component: Register},
		{ path: '/add-centers', component: AddCenters},
		{ path: '/center/:id', component: Center},
		{ path: '/profile/:username', component: Edit},
		{ path: '/all-users', component: AllUsers},
		{ path: '/add-managers', component: AddManagers},
		{ path: '/add-coaches', component: AddCoaches},
	  ]
});

var app = new Vue({
	router,
	el: '#app',
	data:{
	}
});