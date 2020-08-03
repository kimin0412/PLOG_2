<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">SPECIAL</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
            </v-row>

            <v-row class="mt-10">
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">TAB2</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">

            </v-row>
            <v-row>
            </v-row>

          </v-container>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">FOR YOU</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">ANALYSIS</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">Your logs</v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Special'
}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>