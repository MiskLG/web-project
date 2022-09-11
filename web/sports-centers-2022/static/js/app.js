const Home = {template: '<home></home>' }
const Login = {template: '<login></login>'}
const Register = {template: '<register></register>'}
const AddCenters = {template: '<add-centers></add-centers>'}
const Center = {template: '<center></center>'}
const Edit = {template: '<edit-profile></edit-profile>'}
const AllUsers = {template: '<all-users></all-users>'}
const AddManagers = {template: '<add-managers></add-managers>'}
const AddCoaches = {template: '<add-coaches></add-coaches>'}
const AddWorkouts = {template: '<add-workouts></add-workouts>'}
const FacilityOverview = {template: '<facility-overview></facility-overview>'}
const EditWorkout = {template: '<edit-workout></edit-workout>'}
const Workouts = {template: '<workouts></workouts>'}
const Subscriptions = {template: '<subscriptions></subscriptions>'}
const PromoCodes = {template: '<addcodes></addcodes>'}
const Subscription = {template: '<subscription></subscription>'}
const Schedule = {template: '<schedule></schedule>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', component: Home},
		{ path: '/login', component: Login},
		{ path: '/register', component: Register},
		{ path: '/add-centers', component: AddCenters},
		{ path: '/center/:id', component: Center},
		{ path: '/edit-profile', component: Edit},
		{ path: '/all-users', component: AllUsers},
		{ path: '/add-managers', component: AddManagers},
		{ path: '/add-coaches', component: AddCoaches},
		{ path: '/add-workouts', component: AddWorkouts},
		{ path: '/facility-overview', component: FacilityOverview},
		{ path: '/edit-workout/:id', component: EditWorkout},
		{ path: '/workouts', component: Workouts},
		{ path: '/subscriptions', component: Subscriptions},
		{ path: '/promo-codes', component: PromoCodes},
		{ path: '/subscriptions/:id', component: Subscription},
		{ path: '/schedule/:id', component: Schedule},
	  ]
});

var app = new Vue({
	router,
	el: '#app',
	data:{
	}
});