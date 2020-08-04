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
          <v-tab class="tabinfo navbarlistitemlink" @click="gotoaboutus">About us</v-tab>
          <v-tab class="tabinfo navbarlistitemlink" @click="gotomypage">MyPage</v-tab>
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

    <v-list-item class="drawer-profile">
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

      <v-list
        nav
        dense
        class="drawer-group"
      >
        <v-list-item-group
          active-class="blue--text"
        >
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

          <router-link to="/special" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-star</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">Special</v-list-item-title>
          </v-list-item></router-link>

          <v-divider></v-divider>

          <router-link to="/mypage" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account-circle</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">My Page</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/aboutus" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-information-outline</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">About us</v-list-item-title>
          </v-list-item></router-link>

          <v-divider></v-divider>

          <router-link to="/login" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)로그인</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/signup" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)회원가입</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/signup/error" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)가입오류</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/signup/success" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)가입성공</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/auth" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">로그인 필요</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/note/update" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)노트수정</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="error" class="drawer-menu"><v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-box</v-icon>
            </v-list-item-icon>
            <v-list-item-title class="grey--text">(임시)전반적 에러</v-list-item-title>
          </v-list-item></router-link>

          <router-link to="/v1" class="drawer-menu"><v-list-item>
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
          </v-list-item></router-link>

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
    dark
    padless
  >
    <v-card
      flat
      tile
      class="grey darken-1 black--text text-center"
    >
      <v-card-text>
        <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4 white--text"
          icon
        >
          <v-icon size="24px">{{ icon }}</v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
        Phasellus feugiat arcu sapien, et iaculis ipsum elementum sit amet. Mauris cursus commodo interdum. Praesent ut risus eget metus luctus accumsan id ultrices nunc. Sed at orci sed massa consectetur dignissim a sit amet dui. Duis commodo vitae velit et faucibus. Morbi vehicula lacinia malesuada. Nulla placerat augue vel ipsum ultrices, cursus iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum tempor vel ut orci. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text d-flex">
        <div class="footer-logo">PLOG</div>
        <v-spacer></v-spacer>
        <router-link to="/aboutus" class="footer-router">About us</router-link>
        <router-link to="/aboutus/developers" class="footer-router">Developers</router-link>
        <router-link to="/aboutus" class="footer-router">License</router-link>
      </v-card-text>
    </v-card>
  </v-footer>
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

        <v-list-item class="drawer-profile">
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

        <v-list
          nav
          dense
          class="drawer-group"
        >
          <v-list-item-group
            active-class="blue--text"
          >

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-calendar-month</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/schedule" class="drawer-menu">Schedule</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-post</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/note" class="drawer-menu">Note</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-pen</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/note/create" class="drawer-menu">New note</router-link></v-list-item-title>
            </v-list-item>

            <v-divider></v-divider>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-magnify</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/search" class="drawer-menu">Search</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-star</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/special" class="drawer-menu">Special</router-link></v-list-item-title>
            </v-list-item>

            <v-divider></v-divider>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-account-circle</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/mypage" class="drawer-menu">My Page</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-information-outline</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/aboutus" class="drawer-menu">About us</router-link></v-list-item-title>
            </v-list-item>

            <v-divider></v-divider>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/login" class="drawer-menu">(임시)로그인</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/signup" class="drawer-menu">(임시)회원가입</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/signup/error" class="drawer-menu">(임시)회원가입 오류</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title><router-link to="/signup/success" class="drawer-menu">(임시)회원가입 성공</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title  class="drawer-menu"><router-link to="/logout" class="drawer-menu">(임시)로그아웃</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title  class="drawer-menu"><router-link to="/auth" class="drawer-menu">로그인필요</router-link></v-list-item-title>
            </v-list-item>

            <v-divider></v-divider>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title class="grey--text"><router-link to="/note/update" class="drawer-menu">(임시)노트수정</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-box</v-icon>
              </v-list-item-icon>
              <v-list-item-title class="grey--text"><router-link to="/error" class="drawer-menu">일반적인 오류</router-link></v-list-item-title>
            </v-list-item>

            <v-list-item>
              <v-list-item-icon>
                <v-icon>mdi-home</v-icon>
              </v-list-item-icon>
              <v-list-item-title class="grey--text" @click="headeroff">PLOG</v-list-item-title>
            </v-list-item>    
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
computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }//,
    // showAdminBoard() {
    //   if (this.currentUser && this.currentUser.roles) {
    //     return this.currentUser.roles.includes('ROLE_ADMIN');
    //   }

    //   return false;
    // },
    // showModeratorBoard() {
    //   if (this.currentUser && this.currentUser.roles) {
    //     return this.currentUser.roles.includes('ROLE_MODERATOR');
    //   }

    //   return false;
    // }
  },
  components: {
    
  },
  created() {
    // 첫페이지(PLOG 들어가기 버튼 1번만 뜨게)
    if (sessionStorage.visit) {
      this.isfirstpage = false
    } else {
      sessionStorage.setItem('visit', 1)
    }
  },
  data: () => ({
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
  color: rgb(212, 212, 212) !important;
  margin-left: 20px;
}
</style>