<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">visual3</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
                <v-col cols="12">월간 데이터</v-col>
                <D3LineChart :config="chart_config_formonth" :datum="chart_data_formonth" height="400" style="width: 100%;"></D3LineChart>
            </v-row>
            <v-row class="mt-5">            </v-row>
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
            <v-col cols="12" class="py-1 text-h6">visual3</v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import { D3LineChart } from 'vue-d3-charts'

export default {
    name: 'Visual3',
    components: {
        D3LineChart,
    },
    data() {
        return {
            chart_data_formonth: [
                    {schedule: 7, note: 2, date: 1},
                    {schedule: 8, note: 3, date: 2},
                    {schedule: 20, note: 13, date: 3},
                    {schedule: 10, note: 8, date: 4},
                    {schedule: 4, note: 10, date: 5},
                    {schedule: 5, note: 11, date: 6},
                    {schedule: 4, note: 4, date: 7},
                    {schedule: 7, note: 5, date: 8},
                    {schedule: 10, note: 12, date: 9},
                    {schedule: 17, note: 12, date: 10},
                    {schedule: 5, note: 7, date: 11},
                    {schedule: 2, note: 3, date: 12},
                ],
            chart_config_formonth: {
                    values: ['schedule', 'note'],
                    color : {
                        scheme: ['#41B882', '#222f3e'],
                    },
                    date: {
                        key: 'date',
                        inputFormat: '%m',
                        outputFormat: '%b',
                    },
                    points:{
                        visibleSize: 3,
                    },
                    axis: {
                        yTicks: 2,
                        xTicks: 12,
                        },
                    transition: { ease: "easeBounceOut" }
                    }, 
            // chart_data_forweek: [
            //         {schedulew: 2, notew: 1, date2: 1},
            //         {schedulew: 3, notew: 5, date2: 2},
            //         {schedulew: 3, notew: 1, date2: 3},
            //         {schedulew: 1, notew: 0, date2: 4},
            //         {schedulew: 0, notew: 0, date2: 5},
            //         {schedulew: 1, notew: 2, date2: 6},
            //         {schedulew: 2, notew: 3, date2: 7},
            //     ],
            // chart_config_forweek: {
            //         values: ['schedulew', 'notew'],
            //         color : {
            //             scheme: ['#41B882', '#222f3e'],
            //         },
            //         date: {
            //             key: 'date2',
            //             inputFormat: '%u',
            //             outputFormat: '%A',
            //         },
            //         points:{
            //             visibleSize: 3,
            //         },
            //         axis: {
            //             yTicks: 3,
            //             xTicks: 1,
            //             },
            //         transition: { ease: "easeBounceOut" }
            //         }, 
                }
        }

}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>