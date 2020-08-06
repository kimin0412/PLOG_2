<template>
  <v-app
  >
  <div v-if="!isfirstpage">
    <!-- 큰화면일때 -->
    <div class="d-none d-sm-block">

    <v-app-bar
      color="blue"
      dense
      dark
      elevation="6"
      elevate-on-scroll
      class="newnav"
      id="header"
    >
    
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-spacer></v-spacer>

      <v-toolbar-title class="logoname px-0 pt-5" @click="headeroff">P L O G</v-toolbar-title>

      <v-spacer></v-spacer>

      <v-btn icon>
        <router-link to="/special"  class="white--text" style="text-decoration: none;"><v-icon>mdi-star</v-icon></router-link>
      </v-btn>

      <template v-slot:extension>
        <v-tabs class="d-flex justify-center">
          <v-tab class="tabinfo navbarlistitemlink" @click="gotoschedule">Schedule</v-tab>
          <v-tab class="tabinfo navbarlistitemlink" @click="gotonote">Note</v-tab>
          <v-tab class="tabinfo navbarlistitemlink" @click="gotogroup">Group</v-tab>
          <v-tab v-if="lognow" class="tabinfo navbarlistitemlink" @click="gotomypage">MyPage</v-tab>
          <v-tab v-else class="tabinfo navbarlistitemlink" @click="gotologin">Login</v-tab>
          <v-tab class="tabinfo navbarlistitemlink" @click="gotosearch">Search</v-tab>
        </v-tabs>
      </template>
    </v-app-bar>
    
    <v-navigation-drawer
      v-model="drawer"
      app
      temporary
      class="mynav-drawer"
    >

    <v-list-item v-if="lognow" class="drawer-profile">
      <router-link to="/mypage" style="text-decoration:none;">
      <v-row>
        <v-col cols="12">
          <v-icon class="white--text">mdi-account</v-icon>
        </v-col>
        <v-col cols="12" class="text-h6 py-0 white--text">Nickname</v-col>
        <v-col cols="12" class="text-subtitle-2 py-0 grey--text text--lighten-1">email@email.com</v-col>
      </v-row>
      </router-link>
    </v-list-item>
    <v-list-item v-else class="drawer-profile">
      <router-link to="/mypage" style="text-decoration:none;">
      <v-row>
        <v-col cols="12">
          <v-icon class="white--text">mdi-account</v-icon>
        </v-col>
        <v-col cols="12" class="text-h6 py-0 white--text">WELCOME :)</v-col>
        <v-col cols="12" class="text-subtitle-2 py-0 grey--text text--lighten-1">좋은 하루 되세요</v-col>
      </v-row>
      </router-link>
    </v-list-item>

      <v-list
        nav
        dense
        class="drawer-group"
      >
        <v-list-item-group
          active-class="blue--text"
        >
        <div v-if="lognow">
          <router-link to="/schedule" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-calendar-month</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">Schedule</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/note" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-post</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">Note</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/note/create" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-pencil-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">New note</v-list-item-title>
          </v-list-item></router-link>

          <v-divider></v-divider>

          <router-link to="/search" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-magnify</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">Search</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/group" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account-group</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">Group</v-list-item-title>
          </v-list-item></router-link>

          <v-divider></v-divider>
        </div>


          <router-link to="/aboutus" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-information-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">About us</v-list-item-title>
          </v-list-item></router-link>

          <v-divider></v-divider>

          <div v-if="lognow">
            <router-link to="/mypage" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-account-circle</v-icon>
              </v-list-item-icon>
              <v-list-item-title class="grey--text">My Page</v-list-item-title>
            </v-list-item></router-link>
          </div>
          <div v-else>
            <router-link to="/signup" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-account-plus</v-icon>
              </v-list-item-icon>
              <v-list-item-title class="grey--text">Sign up</v-list-item-title>
            </v-list-item></router-link>
            <router-link to="/login" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-account-circle</v-icon>
              </v-list-item-icon>
              <v-list-item-title class="grey--text">Log in</v-list-item-title>
            </v-list-item></router-link>          
          </div>

          <!-- <router-link to="/signup/error" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)가입오류</v-list-item-title>
          </v-list-item></router-link> -->

          <!-- <router-link to="/signup/success" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)가입성공</v-list-item-title>
          </v-list-item></router-link> -->

          <!-- <router-link to="/auth" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">로그인 필요</v-list-item-title>
          </v-list-item></router-link> -->

          <!-- <router-link to="/note/update" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)노트수정</v-list-item-title>
          </v-list-item></router-link> -->

          <!-- <router-link to="error" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)전반적 에러</v-list-item-title>
          </v-list-item></router-link> -->

          <!-- <router-link to="/v1" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">v1</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/v2" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">v2</v-list-item-title>
          </v-list-item></router-link> -->

          <v-list-item @click="headeroff">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">PLOG</v-list-item-title>
          </v-list-item>    

        </v-list-item-group>       
      </v-list>
    </v-navigation-drawer>
  </div>

  <div class="d-none d-sm-block" style="min-height: 100%;">
      <v-container style="padding-bottom: 60px; padding-top:120px; min-height: 100vh;">
      <router-view/>
    </v-container>
    <v-footer
    
    padless
  >
    <v-card
      flat
      tile
      class="grey lighten-4 black--text text-center"
      width="100vw"
    >

      <v-card-text class="grey--text d-flex">
        <div class="footer-logo ml-15 grey--text">PLOG</div>
        <v-spacer></v-spacer>
        <router-link to="/aboutus" class="footer-router">About us</router-link>
        <router-link to="/aboutus/developers" class="footer-router">Developers</router-link>
        <router-link to="/aboutus" class="footer-router mr-15">License</router-link>
      </v-card-text>
    </v-card>
  </v-footer>
  <v-speed-dial
      fixed
      right
      bottom
      small
      v-model="fab"
      v-show="btnShow"
      :direction="direction"
      :open-on-hover="hover"
      :transition="transition"
      class="mb-13"
    >
      <template v-slot:activator>
        <v-btn
          v-model="fab"
          color="light-green"
          dark
          small
          fab
        >
          <v-icon v-if="fab">mdi-close</v-icon>
          <v-icon v-else>mdi-menu</v-icon>
        </v-btn>
      </template>
      <v-btn
        fab
        dark
        small
        color="brown"
        @click="gotoaboutus"
      >
        <v-icon>mdi-home</v-icon>
      </v-btn>
      <v-btn
        fab
        dark
        small
        color="teal"
        @click="gotonotecreate"
      >
        <v-icon>mdi-pencil-plus</v-icon>
      </v-btn>
    </v-speed-dial>

    <v-btn
      bottom
      right
      fixed
      dark
      fab
      color="orange"
      small
      v-show="btnShow"
      @click="$vuetify.goTo('#header')">
        <v-icon class="white--text">mdi-chevron-double-up</v-icon>
    </v-btn>

  </div>

  <!-- 작은화면 -->
  <div class="d-block d-sm-none">
    <div class="small-navtop">
      <v-app-bar
        color="white"
        dense
        dark
        elevation="0"
      >
        <v-app-bar-nav-icon class="blue--text" @click="drawer = true" style="margin-right:16px;"></v-app-bar-nav-icon>

        <v-spacer></v-spacer>

        <router-link to="/special" class="drawer-menu">
          <v-btn icon class="d-felx justify-end" @click="noalarm">
          <v-badge
            v-if="todo"
            color="red"
            dot
            offset-x="5"
            offset-y="5"
          >
            <v-icon class="blue--text">mdi-bell-outline</v-icon>
          </v-badge>

          <v-icon v-else class="blue--text">mdi-bell-outline</v-icon>
          </v-btn>        
        </router-link>

      </v-app-bar>
    </div>
    <v-navigation-drawer
        v-model="drawer"
        app
        temporary
        class="mynav-drawer"
      >

        <v-list-item v-if="lognow" class="drawer-profile">
          <router-link to="/mypage" style="text-decoration:none;">
          <v-row>
            <v-col cols="12">
              <v-icon class="white--text">mdi-account</v-icon>
            </v-col>
            <v-col cols="12" class="text-h6 py-0 white--text">Nickname</v-col>
            <v-col cols="12" class="text-subtitle-2 py-0 grey--text text--lighten-1">email@email.com</v-col>
          </v-row>
          </router-link>
        </v-list-item>
        <v-list-item v-else class="drawer-profile">
          <router-link to="/login" style="text-decoration:none;">
          <v-row>
            <v-col cols="12">
              <v-icon class="white--text">mdi-account</v-icon>
            </v-col>
            <v-col cols="12" class="text-h6 py-0 white--text">Welcome</v-col>
            <v-col cols="12" class="text-subtitle-2 py-0 grey--text text--lighten-1">Have a good day :)</v-col>
          </v-row>
          </router-link>
        </v-list-item>

        <v-list
          nav
          dense
          class="drawer-group"
        >
          <v-list-item-group
            active-class="blue--text"
          >

          <div v-if="lognow">
            <router-link to="/schedule" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-calendar-month</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Schedule</v-list-item-title>
            </v-list-item></router-link>

            <router-link to="/note" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-post</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Note</v-list-item-title>
            </v-list-item></router-link>

            <router-link to="/note/create" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-pen</v-icon>
              </v-list-item-icon>
              <v-list-item-title>New note</v-list-item-title>
            </v-list-item></router-link>

            <v-divider></v-divider>

            <router-link to="/search" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-magnify</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Search</v-list-item-title>
            </v-list-item></router-link>

            <v-divider></v-divider>

            <router-link to="/mypage" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-account-circle</v-icon>
              </v-list-item-icon>
              <v-list-item-title>My Page</v-list-item-title>
            </v-list-item></router-link>

            <router-link to="/logout" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title >로그아웃</v-list-item-title>
            </v-list-item></router-link>

            <router-link to="/aboutus" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-information-outline</v-icon>
              </v-list-item-icon>
              <v-list-item-title>About us</v-list-item-title>
            </v-list-item></router-link>

            <v-divider></v-divider>

            <v-list-item @click="headeroff">
              <v-list-item-icon>
                <v-icon>mdi-home</v-icon>
              </v-list-item-icon>
              <v-list-item-title >PLOG</v-list-item-title>
            </v-list-item>    


          </div>
          <div v-else>

            <router-link to="/login" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-account</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Log in</v-list-item-title>
            </v-list-item></router-link>

            <router-link to="/signup" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-account-plus</v-icon>
              </v-list-item-icon>
              <v-list-item-title>Sign up</v-list-item-title>
            </v-list-item></router-link>

            <v-divider></v-divider>

            <router-link to="/aboutus" class="drawer-menu"><v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-information-outline</v-icon>
              </v-list-item-icon>
              <v-list-item-title>About us</v-list-item-title>
            </v-list-item></router-link>

            <v-divider></v-divider>
            <v-list-item @click="headeroff">
              <v-list-item-icon>
                <v-icon>mdi-home</v-icon>
              </v-list-item-icon>
              <v-list-item-title >PLOG</v-list-item-title>
            </v-list-item>    

          </div> 
          </v-list-item-group>

        </v-list>
      </v-navigation-drawer>
    <v-container style="padding-bottom: 60px; padding-top:60px;">
      <router-view/>
    </v-container>
    <v-bottom-navigation
      color="blue"
      class="bottomnav d-flex justify-space-around px-4"
      elevation="0"
    >

      <v-btn class="px-0 bottomnavbtn">
        <router-link to="/schedule" class="drawer-menu">
          <div class="py-0 px-0"><v-icon>mdi-calendar-month</v-icon></div>
        </router-link>
      </v-btn>

      <v-btn class="px-0 bottomnavbtn">
        <router-link to="/note" class="drawer-menu">
          <div class="py-0 px-0"><v-icon>mdi-post</v-icon></div>
        </router-link>
      </v-btn>

      <v-btn class="px-0 bottomnavbtn">
        <router-link to="/note/create" class="drawer-menu">
          <div class="py-0 px-0"><v-icon>mdi-pencil-plus</v-icon></div>
        </router-link>
      </v-btn>

      <v-btn class="px-0 bottomnavbtn">
        <router-link to="/search" class="drawer-menu">
          <div class="py-0 px-0"><v-icon>mdi-magnify</v-icon></div>
        </router-link>
      </v-btn>
      
      <v-btn class="px-0 bottomnavbtn">
        <router-link to="/mypage" class="drawer-menu">
          <div class="py-0 px-0"><v-icon>mdi-account</v-icon></div>
        </router-link>
      </v-btn>

    </v-bottom-navigation>
        <v-btn
      bottom
      right
      fixed
      dark
      fab
      color="brown darken-1"
      small
      class="mb-12"
      v-show="btnShow"
      @click="$vuetify.goTo('#header')">
        <v-icon class="white--text">mdi-chevron-double-up</v-icon>
    </v-btn>
    </div>
  </div>


  <!-- 첫 화면(로고만) -->
  <div v-else>
    <div class="firstmain d-flex justify-center align-center">
      <div>
        <v-container>
          <v-row class="text-center">
            <v-col cols="12" class="py-0 firstmain-logo animate__animated animate__tada animate__slow animate__repeat-2">
              PLOG
            </v-col>
            <v-col cola="12">
              <v-btn @click="headeron" small class="py-0 firstmain-sub1 animate__animated animate__flash animate__delay-2s">
                Let's start
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
  </div>

  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {
    
  },
  created() {
    // 첫페이지(PLOG 들어가기 버튼 1번만 뜨게)
    if (sessionStorage.visit) {
      this.isfirstpage = false
    } else {
      sessionStorage.setItem('visit', 1)
    }
    // 로그인 여부 확인 후 데이터 수정해주기
    if (this.$store.state.auth.status.loggedIn) {
      this.isLoggedin = true
    }
  },
  data: () => ({
    // 로그인 여부
    isLoggedin : false,
    btnShow : window.scrollY > 1,
    drawer: false,
    activeBtn: 1,
    isfirstpage: true,
    todo: true,
    icons: [
      'mdi-facebook',
      'mdi-twitter',
      'mdi-linkedin',
      'mdi-instagram',
    ],
    // 오른쪽 아래 버튼
    direction: 'top',
    fab: false,
    fling: false,
    hover: false,
    tabs: null,
    top: false,
    right: true,
    bottom: true,
    left: false,
    transition: 'slide-y-reverse-transition',
  }),
  methods: {
    headeron() {
      this.isfirstpage = false
    },
    headeroff() {
      this.isfirstpage = true
    },
    noalarm() {
      this.todo = !this.todo
    },
    // 상단 탭 : 클릭 이용성 개선
    gotoschedule() {
      if (this.$route.name !== 'Schedule'){
        this.$router.push('/schedule')
      }
    },
    gotonote() {
      if (this.$route.name !== 'Note'){
        this.$router.push('/note')
      }
    },
    gotogroup() {
      if (this.$route.name !== 'GroupMain'){
        this.$router.push('/group')
      }
    },
    gotoaboutus() {
      if (this.$route.name !== 'Aboutus'){
        this.$router.push('/aboutus')
      }
    },
    gotomypage() {
      if (this.$route.name !== 'Mypage'){
        this.$router.push('/mypage')
      }
    },
    gotosearch() {
      if (this.$route.name !== 'Search'){
        this.$router.push('/search')
      }
    },
    gotologin() {
      if (this.$route.name !== 'Login'){
        this.$router.push('/login')
      }
    },
    // 맨위로 버튼 + 노트 생성 버튼
    handleScroll() {
      this.btnShow = window.scrollY > 0;
    },
    gotonotecreate() {
      if (this.$route.name !== 'Createnote'){
        this.$router.push('/note/create')
      }
    }
  },
  // 맨위로 버튼
  beforeMount() {
    window.addEventListener("scroll", this.handleScroll)
  },
  beforeDestory() {
    window.removeEventListener("scroll", this.handleScroll)
  },
  computed: {
    activeFab () {
      switch (this.tabs) {
        case 'one': return { class: 'purple', icon: 'account_circle' }
        case 'two': return { class: 'red', icon: 'edit' }
        case 'three': return { class: 'green', icon: 'keyboard_arrow_up' }
        default: return {}
      }
    },
    currentUser(){
      return this.$store.state.auth.user;
    },
    lognow() {
      return this.$store.state.auth.status.loggedIn
    }
  },
  watch: {
    top (val) {
      this.bottom = !val
    },
    right (val) {
      this.left = !val
    },
    bottom (val) {
      this.top = !val
    },
    left (val) {
      this.right = !val
    },
    

  }
};
</script>
<style>

.mytest {
  color: rgb(202, 202, 186);
}
.navbarlistitemlink {
  text-decoration: none;
  /* color: rgb(255, 255, 255) !important; */
  color: rgb(163, 206, 245) !important;
  font-size: small !important;
}

.logoname {
  font-size: x-large !important; 
  font-weight: 800 !important;
  text-decoration: none;
  color: rgb(255, 255, 255) !important;
}

.logoname:hover {
  cursor: pointer;
}

.smalllogoname {
  font-weight: 800 !important;
  text-decoration: none;
  color: rgb(5, 149, 233);
}

.smalllogoname:hover {
  cursor: pointer;
}

.drawer-menu {
    text-decoration: none;
    font-size: small;
    color: rgb(172, 172, 172) !important;
}

.drawer-menu:active {
  color:  rgb(5, 149, 233) !important;
}
.drawer-menu:hover {
  color:  rgb(5, 149, 233) !important;
}

.mynav {
  z-index: 2 !important;
}

.mynav-drawer {
  /* z-index: 3 !important; */
  z-index: 10 !important;
}

.drawer-group {
  height: 75vh;
}

.bottomnav {
  position: fixed !important;
  bottom:0 !important;
  box-shadow: none !important;
  z-index: 3 !important;
}

.smallnavitem {
  font-size: x-small !important;
}

.bottomnavbtn {
  min-width: 60px !important;
  max-width: 60px !important;
}

.firstmain {
  width: 100vw !important;
  height: 100vh !important;
  background-color: rgb(15, 128, 204);
}

.firstmain-logo {
  font-size: 45px !important;
  color: white;
  font-weight: 700;
  letter-spacing: 5px;
}

.firstmain-sub1 {
  color: rgb(173, 212, 238);
  text-decoration: none;
}

.firstmain-sub1:hover {
  color:rgb(120, 178, 216);
}

.small-navtop {
  position: fixed;
  top:0;
  width: 100%;
  z-index: 3;
}

.drawer-profile {
  height: 20vh;
  background-color: rgb(0, 103, 172);
  margin-bottom: 10px !important;
}

.newnav {
  z-index: 5 !important;
  position: fixed !important;
  top: 0 !important;
}

.tabinfo {
  width: 110px;
}
.footer-logo {
  color: white;
  font-size: x-large;
  font-weight: 400;
  letter-spacing: 3px;
}
.footer-router {
  text-decoration: none;
  color: rgb(110, 110, 110) !important;
  margin-left: 20px;
}
.tooltip {
  display: none !important;
}

#create .v-speed-dial {
  position: absolute;
}

#create .v-btn--floating {
  position: relative;
}
</style>