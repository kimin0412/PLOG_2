<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-row>
            <v-col cols="12" class="text-center">
              <img src="@/assets/icon/copyright.png" alt="license" width="100">
            </v-col>
            <v-col cols="12" class="text-center py-0 mb-5 grey--text font-weight-light display-1">
              LICENSE
            </v-col>
          </v-row>
          <v-row justify="center" class="px-10">
            <v-expansion-panels>
              <v-expansion-panel>
                <v-expansion-panel-header class="font-weight-bold">
                  MIT License
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row v-for="(item, i) in MITitems" :key="i">
                        <v-col cols="4" class="py-0">- {{item.Name}}</v-col>
                        <v-col cols="8" class="text-caption grey--text py-0 mb-3">{{item.github}}<br>{{item.copyright}}</v-col>
                    </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header class="font-weight-bold">
                  Apache-2.0 License
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row v-for="(item, i) in Apacheitems" :key="i">
                        <v-col cols="4" class="py-0">- {{item.Name}}</v-col>
                        <v-col cols="8" class="text-caption grey--text py-0 mb-3">{{item.github}}<br>{{item.copyright}}</v-col>
                    </v-row>                    
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header class="font-weight-bold">
                  Icons
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/itim2101" title="itim2101">itim2101</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/zlatko-najdenovski" title="Zlatko Najdenovski">Zlatko Najdenovski</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/catkuro" title="catkuro">catkuro</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/prosymbols" title="Prosymbols">Prosymbols</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                    </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header class="font-weight-bold">
                  Image & Illust
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row>
                        <v-col cols="12" class="grey--text text-caption py-1"><a href='https://www.freepik.com/vectors/cartoon'>Cartoon vector created by macrovector - www.freepik.com</a></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><a href='https://www.freepik.com/vectors/business'>Business vector created by pikisuperstar - www.freepik.com</a></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><a href='https://www.freepik.com/vectors/business'>Business vector created by pikisuperstar - www.freepik.com</a></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"></v-col>
                    </v-row>          
                    <v-row>
                      <v-col cols="12" class="text-caption py-1">기타 참고사이트</v-col>
                      <v-col cols="12" class="grey--text text-caption py-1">https://blush.design/</v-col> 
                      <v-col cols="12" class="grey--text text-caption py-1">https://www.freepik.com/</v-col>  
                    </v-row>       
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header class="font-weight-bold">
                  else
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row v-for="(item, i) in Else" :key="i">
                        <v-col cols="4" class="py-0">- {{item.Name}}</v-col>
                        <v-col cols="8" class="text-caption grey--text py-0 mb-3">{{item.github}}<br>{{item.copyright}}</v-col>
                    </v-row>               
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-row>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5"><img src="@/assets/icon/copyright.png" alt="license" width="40" class="my-n4"></v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">LICENSE</v-col>
          </v-row>
          <v-row justify="center" class="pt-10">
            <v-expansion-panels>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  MIT License
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row v-for="(item, i) in MITitems" :key="i">
                        <v-col cols="12" class="py-0">{{item.Name}}</v-col>
                        <v-col cols="12" class="text-caption grey--text py-0 text-wrap">{{item.github}}</v-col>
                        <v-col cols="12" class="text-caption grey--text py-0 mb-3 text-wrap">{{item.copyright}}</v-col>
                    </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  Apache-2.0 License
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row v-for="(item, i) in Apacheitems" :key="i">
                        <v-col cols="12" class="py-0">{{item.Name}}</v-col>
                        <v-col cols="12" class="text-caption grey--text py-0 text-wrap">{{item.github}}</v-col>
                        <v-col cols="12" class="text-caption grey--text py-0 mb-3 text-wrap">{{item.copyright}}</v-col>
                    </v-row>                    
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  Icons
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/itim2101" title="itim2101">itim2101</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/zlatko-najdenovski" title="Zlatko Najdenovski">Zlatko Najdenovski</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/catkuro" title="catkuro">catkuro</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/prosymbols" title="Prosymbols">Prosymbols</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><div>- Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div></v-col>
                    </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header class="font-weight-bold">
                  Image & Illust
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row>
                        <v-col cols="12" class="grey--text text-caption py-1"><a href='https://www.freepik.com/vectors/cartoon'>Cartoon vector created by macrovector - www.freepik.com</a></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><a href='https://www.freepik.com/vectors/business'>Business vector created by pikisuperstar - www.freepik.com</a></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"><a href='https://www.freepik.com/vectors/business'>Business vector created by pikisuperstar - www.freepik.com</a></v-col>
                        <v-col cols="12" class="grey--text text-caption py-1"></v-col>
                    </v-row>          
                    <v-row>
                      <v-col cols="12" class="text-caption py-1">기타 참고사이트</v-col>
                      <v-col cols="12" class="grey--text text-caption py-1">https://blush.design/</v-col>
                      <v-col cols="12" class="grey--text text-caption py-1">https://www.freepik.com/</v-col>    
                    </v-row>       
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  else
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row v-for="(item, i) in Else" :key="i">
                        <v-col cols="12" class="py-0">{{item.Name}}</v-col>
                        <v-col cols="12" class="text-caption grey--text py-0 text-wrap">{{item.github}}</v-col>
                        <v-col cols="12" class="text-caption grey--text py-0 mb-3 text-wrap">{{item.copyright}}</v-col>
                    </v-row>                    
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'License',
    data() {
      return {
        MITitems: [
          {
            Name:'Vue',
            github: 'https://github.com/vuejs/vue',
            copyright: 'Copyright (c) 2013-present, Yuxi (Evan) You',
          },
          {
            Name:'Vuetify',
            github: 'https://github.com/vuetifyjs/vuetify',
            copyright: 'Copyright (c) 2016-present Vuetify LLC',
          },
          {
            Name:'TOAST UI',
            github: 'https://github.com/nhn/tui.editor',
            copyright: 'This software is licensed under the MIT © NHN.',
          },
          {
            Name:'vue-agile',
            github: 'https://github.com/lukaszflorczak/vue-agile',
            copyright: 'Copyright (c) 2017 Łukasz Florczak',
          },
        ],
        Apacheitems: [
          {
            Name:'spring-boot',
            github: 'https://github.com/spring-projects/spring-boot',
            copyright: 'Spring Boot is Open Source software released under the Apache 2.0 license.',
          },
        ],
        Else: [
          {
            Name:'vue-d3-charts',
            github: 'https://github.com/Saigesp/vue-d3-charts',
            copyright: 'Copyright (C) 2007 Free Software Foundation, Inc.',
          },          
        ],
      }
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
  margin-top: 15vh;
}

</style>