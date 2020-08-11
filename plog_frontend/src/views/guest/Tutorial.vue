<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-row class="">
            <v-col cols="12"><div class="text-center display-1 font-weight-light">Tutorial</div></v-col>
            <v-col cols="12" class="py-0"><div class="text-center text-caption font-weight-light grey--text">추후 PLOG 소개 영상으로 대체될 예정입니다.</div></v-col>
          </v-row>
          <v-row class="mt-5" style="height: 80%;">
          <iframe width="100%" src="https://www.youtube.com/embed/dUbp9wAy178" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
          </v-row>       
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container class="center-v">
          <v-row>
            <v-col cols="12" class="text-center py-1 text-h4"><v-icon >mdi-television-play</v-icon></v-col>
            <v-col cols="12" class="text-center py-1 text-h4 font-weight-bold">Tutorial</v-col>
          </v-row>
          <v-row class="mt-5 content-center2 mx-auto">
                <v-col cols="12" class="py-0"><div class="text-center text-caption font-weight-light grey--text">추후 PLOG 영상으로 대체될 예정입니다.</div></v-col>
              <v-col cols="12" class="px-0" style="height: 40vh;">
                <iframe width="100%" height="100%" src="https://www.youtube.com/embed/dUbp9wAy178" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
              </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Tutorial',
    data() {
      return {

        }
    },
    methods: {

    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
  height: 80vh;
}
.content-center2 {
  width: 100%;
  min-height: 100%;
}
.link-icon {
  width: 30%;

}
.link-tag {
  text-decoration: none;
  color: #c7c7c7;
  text-align: center;
}
.center-v {
    margin-top: 5vh;
}
</style>