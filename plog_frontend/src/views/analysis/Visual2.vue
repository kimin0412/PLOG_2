<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">visual2</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
                <D3WordsCloud :config="chart_config" :datum="chart_data" style="min-width: 75vw; min-height:300px;"></D3WordsCloud>
            </v-row>
            <v-row>
              <v-col cols="12">

              </v-col>
            </v-row>

          </v-container>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">ANALYSIS</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">MY DATA</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">visual2</v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
// https://saigesp.github.io/vue-d3-charts/#/wordscloud
import { D3WordsCloud } from 'vue-d3-charts';

export default {
    name: 'Visual2',
    components: {
        D3WordsCloud,
    },
    data() {
        return {
            chart_data : [{
                word: "Vue",
                size: 70
                },{
                word: "JavaScript",
                size: 50
                },{
                word: "Spring",
                size: 60
                },{
                word: "Vuetify",
                size: 80
                },{
                word: "MYSQL",
                size: 10
                },],
            chart_config : {
            // angle: { start: 0, end: 90, steps: 2 },
            color: {
                key: false,
                keys: false,
                scheme: false,
                current: "#1f77b4",
                default: "#AAA",
                axis: "#000",
            },
            fontFamily: "monospace",
            margin: {
                top: 30,
                right: 30,
                bottom: 30,
                left: 30,
            },
            transition: {
                duration: 350,
                ease: "easeLinear",
            },
            }
        }
    }

}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>