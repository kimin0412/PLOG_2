<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">visual5</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
                <v-col cols="12">해시태그 top10개</v-col>
                <D3PieChart :config="chart_config" :datum="chart_data" style="width: 100%; height: 100%;"></D3PieChart>

            </v-row>
            <v-row class="mt-5">            

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
            <v-col cols="12" class="py-1 text-h6">visual5</v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import { D3PieChart } from 'vue-d3-charts'

export default {
    name: 'Visual3',
    components: {
        D3PieChart
    },
    data() {
        return {
            chart_data: [
                    {count: 50, name: 'Vue'},
                    {count: 40, name: 'JS'},
                    {count: 35, name: 'JAVA'},
                    {count: 25, name: 'DB'},
                    {count: 10, name: 'Spring'},
                    {count: 8, name: '필기'},
                    {count: 6, name: 'Django'},
                    {count: 5, name: 'Python'},
                    {count: 4, name: 'React'},
                    {count: 2, name: 'SQL'},
                ],
                chart_config: {
                    key: 'name',
                    value: 'count',
                    color: {scheme: 'schemeTableau10'},
                    radius: {inner: 50}
                },
                count: 1
                }
    }

}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>