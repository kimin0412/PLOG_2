<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
      
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">ABOUT</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">What is PLOG?</v-col>
          </v-row>
          <v-row class="mt-10">
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'GroupMain',
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
.about-intro {
  width: 100%;
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