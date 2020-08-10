<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container style="width:100%; height: 100%;">
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">visual4</div>
              </v-col>
            </v-row>
            <v-row style="width:40vw; height: 60vh;">
              <v-col cols="12">
                  <v-col cols="12">해시태그</v-col>
                  <v-col cols="12">
                    <D3BarChart
                    :config="chart_config"
                    :datum="chart_data"
                    :title="chart_title"
                    ></D3BarChart>
                  </v-col>
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
            <v-col cols="12" class="py-1 text-h6">visual4</v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import { D3BarChart } from 'vue-d3-charts';


export default {
    name: 'Visual4',
    components: {
        D3BarChart,
    },
    data() {
        return {
            chart_title: 'Hashtag Ranking',
            chart_data: [
                {count: 10, tags: 'DB'},
                {count: 8, tags: 'Vue'},
                {count: 6, tags: 'JS'},
                {count: 5, tags: 'JAVA'},
                {count: 1, tags: 'Python'}
            ],
            chart_config: {
                key: 'tags',
                values: ['count'],
                axis: {
                yTicks: 10
                },
                color: {
                default: '#222f3e',
                current: '#41B882'
                }
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