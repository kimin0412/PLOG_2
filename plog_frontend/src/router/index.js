import Vue from 'vue'
import VueRouter from 'vue-router'

// import Mainpage from '../views/Main.vue'
import Aboutus from '../views/AboutUsMain.vue'
import Schedule from '../views/schedule/ScheduleMain.vue'
import Mypage from '../views/account/MyPageMain.vue'
import Search from '../views/SearchMain.vue'
import Note from '../views/note/NoteMain2.vue'
import Note2 from '../views/note/NoteMain.vue'

// account
import Login from '../views/account/Login.vue'
import Signup from '../views/account/Signup.vue'
import Editprofile from '../views/account/Editprofile.vue'
import SignupSuccess from '../views/account/Success.vue'
import SignupError from '../views/account/Error.vue'
import Logout from '../views/account/Logout.vue'
import Profile from '../views/account/Profile.vue'

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
import GeneralError from '../views/error/GeneralError.vue'
import PageNotFound from '../views/error/PageNotFound.vue'
import ServerError from '../views/error/ServerError.vue'
import ClientError from '../views/error/ClientError.vue'

// developers
import Developers from '../views/Developers.vue'

// visualize
import Visual1 from '../views/analysis/Visual1.vue'
import Visual2 from '../views/analysis/Visual2.vue'
import Visual3 from '../views/analysis/Visual3.vue'
import Visual4 from '../views/analysis/Visual4.vue'
import Visual5 from '../views/analysis/Visual5.vue'

// group
import GroupMain from '../views/group/GroupMain.vue'
import GroupDetail from '../views/group/GroupDetail.vue'
import GroupSearch from '../views/group/GroupSearch.vue'
import GroupCreate from '../views/group/GroupCreate.vue'
import GroupNoteCreate from '../views/group/GroupNoteCreate.vue'
import GroupNoteDetail from '../views/group/GroupNoteDetail.vue'
import GroupNoteUpdate from '../views/group/GroupNoteUpdate.vue'

import GroupDetail2 from '../views/group/GroupDetail2.vue'


//guest
import Howto from '../views/guest/Howto.vue'
import Gallery from '../views/guest/Gallery.vue'
import Tutorial from '../views/guest/Tutorial.vue'

import { auth } from '@/store/auth.module';

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Main',
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
    component: Schedule,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/note',
    name: 'Note',
    component: Note,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/note2',
    name: 'Note2',
    component: Note2,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/mypage',
    name: 'Mypage',
    component: Mypage,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/search',
    name: 'Search',
    component: Search,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  // account
  {
    path: '/login',
    name: 'Login',
    component: Login,
    beforeEnter(from, to, next) {
      if (auth.state.status.loggedIn) {
        alert('이미 로그인 상태입니다.')
        next('/mypage')
      } else {
        next()
      }
    }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
    beforeEnter(from, to, next) {
      if (auth.state.status.loggedIn) {
        alert('이미 PLOG의 유저이시군요 :)')
        next('/profile')
      } else {
        next()
      }
    }
  },
  {
    path: '/mypage/edit',
    name: 'Editprofile',
    component: Editprofile,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
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
    component: Logout,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
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
    component: Createnote,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },

  {
    path: '/note/detail',
    name: 'Detailnote',
    component: Detailnote,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/note/update',
    name: 'Updatenote',
    component: Updatenote,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/note/tmpupdate',
    name: 'TmpUpdatenote',
    component: TmpUpdatenote,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  // auth
  {
    path: '/auth',
    name: 'NeedAuth',
    component: NeedAuth
  },
  // club
  {
    path: '/group',
    name: 'GroupMain',
    component: GroupMain,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/group/detail',
    name: 'GroupDetail',
    component: GroupDetail,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/group/search',
    name: 'GroupSearch',
    component: GroupSearch,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/group/create',
    name: 'GroupCreate',
    component: GroupCreate,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/group/noteCreate',
    name: 'GroupNoteCreate',
    component: GroupNoteCreate,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/group/noteDetail',
    name: 'GroupNoteDetail',
    component: GroupNoteDetail,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/group/noteUpdate',
    name: 'GroupNoteUpdate',
    component: GroupNoteUpdate,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
  },
  {
    path: '/group/detail2',
    name: 'GroupDetail2',
    component: GroupDetail2,
    beforeEnter(from, to, next) {
      if (!auth.state.status.loggedIn) {
        next('/auth')
      } else {
        next()
      }
    }
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
  {
    path: '/v3',
    name: 'Visual3',
    component: Visual3
  },
  {
    path: '/v4',
    name: 'Visual4',
    component: Visual4
  },
  {
    path: '/v5',
    name: 'Visual5',
    component: Visual5
  },
  // guest
  {
    path: '/gallery',
    name: 'Gallery',
    component: Gallery
  },
  {
    path: '/howto',
    name: 'Howto',
    component: Howto
  },
  {
    path: '/tutorial',
    name: 'Tutorial',
    component: Tutorial
  },
  // error
  {
    path: '/error',
    name: 'GeneralError',
    component: GeneralError
  },
  {
    path: '/servererror',
    name: 'ServerError',
    component: ServerError
  },
  {
    path: '/clienterror',
    name: 'ClientError',
    component: ClientError
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
