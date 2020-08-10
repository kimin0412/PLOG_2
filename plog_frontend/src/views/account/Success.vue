<template>
  <div>
    <!-- 큰 화면 -->
    <div class="d-none d-sm-block pt-12">
      <div class="centercontent mx-auto">
      <v-container class="signup-successbox">
        <v-row>
          <v-col cols="12" class="py-1 display-1 font-weight-light text-center">Welcome :)</v-col>
          <v-col cols="12" class="pt-4 pb-0 text-body-1 text-center">성공적으로 PLOG에</v-col>
          <v-col cols="12" class="py-0 text-body-1 text-center">회원가입 되었습니다.</v-col>
        </v-row>
        <v-row class="d-flex justify-center mt-5">
          <router-link to="/login" class="movebtn"><v-btn rounded color="blue" dark block small><v-icon small class="mr-2">mdi-account</v-icon>LOG IN</v-btn></router-link>
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
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text">회원가입이</v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">완료되었습니다</v-col>
        </v-row>
        <v-row class="mt-5 ml-1">
          <router-link to="/schedule" class="movebtn"><v-btn rounded color="blue" dark small><v-icon small class="mr-3">mdi-home</v-icon>go home</v-btn></router-link>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SignupSuccess',
  data() {
    return {
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
.signup-successbox {
  margin-top: 20vh;
}
</style>