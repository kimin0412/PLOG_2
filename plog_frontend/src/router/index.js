import Vue from 'vue'
import VueRouter from 'vue-router'

// import Mainpage from '../views/Main.vue'
import Aboutus from '../views/AboutUsMain.vue'
import Schedule from '../views/schedule/ScheduleMain.vue'
import Mypage from '../views/account/MyPageMain.vue'
import Search from '../views/SearchMain.vue'
import Note from '../views/note/NoteMain.vue'

// account
import Login from '../views/account/Login.vue'
import Signup from '../views/account/Signup.vue'
import Editprofile from '../views/account/Editprofile.vue'
import SignupSuccess from '../views/account/Success.vue'
import SignupError from '../views/account/Error.vue'
import Logout from '../views/account/Logout.vue'

// special
import Special from '../views/Special.vue'

// settings
import Settings from '../views/Settings.vue'

// note
import Createnote from '../views/note/NoteCreate.vue'
import Detailnote from '../views/note/NoteDetail.vue'
import Updatenote from '../views/note/NoteUpdate.vue'
import TmpUpdatenote from '../views/note/TmpNoteUpdate.vue'

// auth
import NeedAuth from '../views/auth/NeedAuth.vue'

// Error
import GeneralError from '../views/GeneralError.vue'
import PageNotFound from '../views/PageNotFound.vue'

// developers
import Developers from '../views/Developers.vue'

// visualize
import Visual1 from '../views/analysis/Visual1.vue'
import Visual2 from '../views/analysis/Visual2.vue'

// group
import GroupMain from '../views/group/GroupMain.vue'
import GroupDetail from '../views/group/GroupDetail.vue'
import GroupSearch from '../views/group/GroupSearch.vue'
import GroupCreate from '../views/group/GroupCreate.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Aboutus',
    component: Aboutus
  },
  {
    path: '/aboutus',
    name: 'Aboutus',
    component: Aboutus
  },
  // 개발자들
  {
    path: '/aboutus/developers',
    name: 'Developers',
    component: Developers
  },
  {
    path: '/schedule',
    name: 'Schedule',
    component: Schedule
  },
  {
    path: '/note',
    name: 'Note',
    component: Note
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  // account
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/mypage/edit',
    name: 'Editprofile',
    component: Editprofile
  },
  {
    path: '/signup/success',
    name: 'SignupSuccess',
    component: SignupSuccess
  },
  {
    path: '/signup/error',
    name: 'SignupError',
    component: SignupError
  },
  {
    path: '/logout',
    name: 'Logout',
    component: Logout
  },
  // special
  {
    path: '/special',
    name: 'Special',
    component: Special
  },
  // settings
  {
    path: '/settings',
    name: 'Settings',
    component: Settings
  },

  // note - crud
  {
    path: '/note/create',
    name: 'Createnote',
    component: Createnote
  },

  {
    path: '/note/detail',
    name: 'Detailnote',
    component: Detailnote
  },
  {
    path: '/note/update',
    name: 'Updatenote',
    component: Updatenote
  },
  {
    path: '/note/tmpupdate',
    name: 'TmpUpdatenote',
    component: TmpUpdatenote
  },
  // auth
  {
    path: '/auth',
    name: 'NeedAuth',
    component: NeedAuth
  },
  // error
  {
    path: '/error',
    name: 'GeneralError',
    component: GeneralError
  },
  // club
  {
    path: '/group',
    name: 'GroupMain',
    component: GroupMain
  },
  {
    path: '/group/detail',
    name: 'GroupDetail',
    component: GroupDetail
  },
  {
    path: '/group/search',
    name: 'GroupSearch',
    component: GroupSearch
  },
  {
    path: '/group/create',
    name: 'GroupCreate',
    component: GroupCreate
  },
  // visual
  {
    path: '/v1',
    name: 'Visual1',
    component: Visual1
  },
  {
    path: '/v2',
    name: 'Visual2',
    component: Visual2
  },
  // 404 page not found
  {
    path: '/404',
    name: 'PageNotFound',
    component: PageNotFound
  },
  { 
    path: '*',
    redirect: '/404',
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
