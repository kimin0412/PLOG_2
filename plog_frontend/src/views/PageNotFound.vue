<template>
  <div>
    <!-- 큰 화면 -->
    <div class="d-none d-sm-block pt-12">
      <div class="centercontent mx-auto">
      <v-container class="pnf">
        <v-row>
          <v-col cols="12" class="py-1 display-1 font-weight-bold text-center">404</v-col>
          <v-col cols="12" class="py-1 display-1 font-weight-light text-center">Page Not Found</v-col>
          <v-col cols="12" class="pt-4 pb-2 text-subtitle-2 text-center grey--text">요청하신 페이지를 찾을 수 없습니다</v-col>
        </v-row>
        <v-row class="d-flex justify-center mt-5">
          <router-link v-if="lognow" to="/schedule" class="movebtn"><v-btn rounded color="blue" dark block small><v-icon class="mr-2" small>mdi-calendar</v-icon>Schedule</v-btn></router-link>        
          <router-link v-else to="/aboutus" class="movebtn"><v-btn rounded color="blue" dark block small><v-icon class="mr-2" small>mdi-home</v-icon>HOME</v-btn></router-link>
        </v-row>
      </v-container>
      </div>
    </div>

    <!-- 작은화면 -->
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold blue--text">404</v-col>
          <v-col cols="12" class="py-1 text-subtitle-1 font-weight-bold blue--text">Page not found</v-col>
        </v-row>
        <v-row class="mt-7">
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">요청하신 페이지를</v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">찾을 수 없습니다.</v-col>
        </v-row>
        <v-row class="mt-5 ml-1">
          <router-link v-if="lognow" to="/schedule" class="movebtn"><v-btn rounded color="blue" dark small><v-icon small class="mr-3">mdi-calendar</v-icon>go home</v-btn></router-link>
          <router-link v-else to="/aboutus" class="movebtn"><v-btn rounded color="blue" dark small><v-icon small class="mr-3">mdi-home</v-icon>go home</v-btn></router-link>

        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PageNotFound',
  data() {
    return {
    }
  },
  computed: {
    lognow() {
      return this.$store.state.auth.status.loggedIn
    }
  }
}
</script>

<style scoped>
.submitbtn {
  min-width: 100% !important;
}

.linkto {
  text-decoration: none;
  color: rgb(67, 119, 196);
}

.centercontent {
  width: 50%;
}

.movebtn {
    text-decoration: none;
    color: white;
}
.pnf {
  margin-top: 20vh;
}
</style>