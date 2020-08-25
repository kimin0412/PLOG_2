<template>
  <div>
    <!-- 큰 화면 -->
    <div class="d-none d-sm-block pt-12">
      <div class="centercontent mx-auto">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold text-center">Error :(</v-col>
          <v-col cols="12" class="pt-4 pb-2 text-h6 text-center">알 수 없는 오류</v-col>
          <v-col cols="12" class="py-0 text-subtitle-2 text-center">잠시 후 다시 시도해 주세요 </v-col>
        </v-row>
        <v-row class="d-flex justify-center mt-5">
          <router-link to="/aboutus" class="movebtn"><v-btn rounded color="blue" dark block small><v-icon small>mdi-home</v-icon>HOME</v-btn></router-link>
        </v-row>
      </v-container>
      </div>
    </div>

    <!-- 작은화면 -->
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold blue--text">Error :(</v-col>
        </v-row>
        <v-row class="mt-7">
          <v-col cols="12" class="py-1 text-subtitle-1 font-weight-bold grey--text">알 수 없는 오류</v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">잠시 후 다시</v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">시도해주시기 바랍니다</v-col>
        </v-row>
        <v-row class="mt-5 ml-1">
          <router-link to="/aboutus" class="movebtn"><v-btn rounded color="blue" dark small><v-icon small class="mr-3">mdi-home</v-icon>go home</v-btn></router-link>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  name: 'GeneralError',
  data() {
    return {
    }
  },
  created() {
      window.scrollTo({top:0, left:0, behavior:'smooth'});
    },
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
</style>