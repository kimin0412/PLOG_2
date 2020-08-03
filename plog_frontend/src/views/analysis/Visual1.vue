<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">visual1</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
                <wordcloud
                :data="defaultWords"
                nameKey="name"
                valueKey="value"
                :color="myColors"
                :showTooltip="true"
                :wordClick="wordClickHandler">
                </wordcloud>                
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
            <v-col cols="12" class="py-1 text-h6">visual1</v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
// https://github.com/feifang/vue-wordcloud
import wordcloud from 'vue-wordcloud'

export default {
    name: 'Visual1',
    components: {
        wordcloud
    },
      methods: {
    wordClickHandler(name, value, vm) {
      console.log('wordClickHandler', name, value, vm);
    }
  },
    data() {
        return {
        myColors: ['#1f77b4', '#629fc9', '#94bedb', '#c9e0ef'],
        defaultWords: [{
            "name": "Vue",
            "value": 80
            },
            {
            "name": "JavaScript",
            "value": 70
            },
            {
            "name": "Spring",
            "value": 60
            },
            {
            "name": "Django",
            "value": 20
            },
            {
            "name": "Python",
            "value": 15
            },
            {
            "name": "JAVA",
            "value": 70
            },
            {
            "name": "Vuetify",
            "value": 80
            },
            {
            "name": "Node.js",
            "value": 60
            },
            {
            "name": "MYsql",
            "value": 40
            }
        ]
        }
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>