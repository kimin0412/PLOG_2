<template>
  <div>
    <!-- 큰 화면 -->
    <div class="d-none d-sm-block pt-12">
      <div class="centercontent mx-auto">
      <v-container class="need-authbox">
        <v-row>
          <v-col cols="12" class="py-1 display-1 font-weight-light text-center">로그인 후</v-col>
          <v-col cols="12" class="py-1 display-1 font-weight-light text-center">이용 가능한</v-col>
          <v-col cols="12" class="py-1 display-1 font-weight-light text-center">서비스 입니다</v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 font-weight-light text-center grey--text">Please login to use PLOG service :)</v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="text-center blue--text">
            <router-link to="/login" class="mr-4 text-decoration-none blue--text"><v-icon small class="mr-2 blue--text">mdi-account</v-icon>Log in</router-link>
            | <router-link to="/signup" class="ml-4 text-decoration-none blue--text"><v-icon small class="mr-2 blue--text">mdi-account-plus</v-icon>Sign up</router-link>
          </v-col>
        </v-row>
      </v-container>
      </div>
    </div>

    <!-- 작은화면 -->
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold blue--text">WELCOME :)</v-col>
        </v-row>
        <v-row class="mt-7">
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text">로그인이</v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">필요한</v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">서비스입니다</v-col>
        </v-row>
        <v-row class="mt-5 ml-1">
          <router-link to="/login" class="movebtn"><v-btn rounded color="blue" dark small><v-icon small class="mr-3">mdi-account</v-icon>Log in</v-btn></router-link>
        </v-row>
        <v-row class="mt-1 ml-1">
          <router-link to="/signup" class="movebtn"><v-btn rounded color="blue" dark small><v-icon small class="mr-3">mdi-account-plus</v-icon>Sign up</v-btn></router-link>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NeedAuth',
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


.need-authbox {
  margin-top: 20vh;
}
</style>