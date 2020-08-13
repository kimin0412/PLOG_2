<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-row class="mt-5 text-center">
            <v-col cols="12" class="text-center">서비스 준비중입니다.</v-col> 
          </v-row>
          <v-row class="mt-15 pt-15">
          </v-row>       
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">Gallery</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">PLOGGERS</v-col>
          </v-row>
          <v-row class="mt-10 text-center">
            <v-col cols="12">서비스 준비중입니다.</v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Preview',
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
}
.link-icon {
  width: 30%;

}
.link-tag {
  text-decoration: none;
  color: #c7c7c7;
  text-align: center;
}
</style>