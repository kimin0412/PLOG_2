<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto mt-10">
          <v-row>
            <v-col cols="12">
              <v-card elevation="0">
                <v-tabs
                  background-color="white"
                  color="indigo"
                  centered
                >
                  <v-tab>Team info.</v-tab>
                  <v-tab>Members</v-tab>
                  <v-tab>Posts</v-tab>
                  
                  <v-tab-item
                  >
                    <v-container fluid>
                      <v-row>
                        <v-col cols="2"></v-col>
                        <v-col cols="3" class="text-center mt-5">
                          <img src="@/assets/group/team.png" alt="" width="120">              
                        </v-col>
                        <v-col cols="5" class="text-center mt-5">
                          <v-simple-table dense class="text-left">
                            <template v-slot:default>
                              <tbody>
                                <tr>
                                  <td>Group Name</td>
                                  <td>{{ myClub.clName }}</td>
                                </tr>
                                <tr>
                                  <td>Group Host</td>
                                  <td>{{ host }}</td>
                                </tr>
                                <tr>
                                  <td>Group Color</td>
                                  <td>{{ myClub.clColor }}</td>
                                </tr>
                                <tr>
                                  <td>Created</td>
                                  <td>{{ regdate }}</td>
                                </tr>
                              </tbody>
                            </template>
                          </v-simple-table>
                        </v-col>  
                        <v-col cols="2"></v-col>          
                      </v-row>
                    </v-container>
                  </v-tab-item>
                  <v-tab-item>
                    <v-container fluid>
                      <v-row>
                        <v-col cols="12" class="text-center">
                          아직 서비스 준비중입니다.
                        </v-col>
                        <!-- <v-col cols="2"></v-col>
                        <v-col cols="3" class="text-center mt-5">
                          <img src="@/assets/group/team.png" alt="" width="120">              
                        </v-col>
                        <v-col cols="5" class="text-center mt-5">
                          <v-simple-table dense class="text-left">
                            <template v-slot:default>
                              <tbody>
                                <tr>
                                  <td>Group Name</td>
                                  <td>싸라밸</td>
                                </tr>
                                <tr>
                                  <td>Group Host</td>
                                  <td>M.J. Kim</td>
                                </tr>
                                <tr>
                                  <td>Group Color</td>
                                  <td>color</td>
                                </tr>
                                <tr>
                                  <td>Created</td>
                                  <td>2020.00.00</td>
                                </tr>
                              </tbody>
                            </template>
                          </v-simple-table>
                        </v-col>  
                        <v-col cols="2"></v-col>           -->
                      </v-row>
                    </v-container>
                  </v-tab-item>
                  <v-tab-item>
                    <v-row class="mt-10 pt-10">
                      <v-col cols="12" class="text-center">
                        아직 서비스 준비중입니다.
                      </v-col>
                    </v-row>
                  </v-tab-item>
                </v-tabs>
              </v-card>
            </v-col>
          </v-row>
          <!-- <v-row>
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light">Group Info.</div>
            </v-col>
            <v-col cols="2"></v-col>
            <v-col cols="3" class="text-center mt-5">
              <img src="@/assets/group/team.png" alt="" width="120">              
            </v-col>
            <v-col cols="5" class="text-center mt-5">
              <v-simple-table dense class="text-left">
                <template v-slot:default>
                  <tbody>
                    <tr>
                      <td>Group Name</td>
                      <td>싸라밸</td>
                    </tr>
                    <tr>
                      <td>Group Host</td>
                      <td>M.J. Kim</td>
                    </tr>
                    <tr>
                      <td>Group Color</td>
                      <td>color</td>
                    </tr>
                    <tr>
                      <td>Created</td>
                      <td>2020.00.00</td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-col>  
            <v-col cols="2"></v-col>          
          </v-row>
          <v-row class="mt-10">
            <v-col cols="1"></v-col>
            <v-col cols="10" class="text-center text-h6 grey--text mb-4 px-15">
              SSAFY 3기 5반 2팀 싸라밸(민지/민섭/지식/민재/혜민)
            </v-col>
            <v-col cols="1"></v-col>
          </v-row>
          <v-row class="mt-10 pt-10">
            <v-col cols="12 mb-4">
              <div class="text-center display-1 font-weight-light">Group Posts</div>
            </v-col>
            <v-col cols="12">
              <v-item-group>
              <v-container>
                <v-row>
                  <v-col
                    v-for="n in 4"
                    :key="n"
                    cols="3"
                  >
                    <v-item v-slot:default="{ active, toggle }">
                      <v-card
                        :color="active ? 'primary' : 'transparent'"
                        class="d-flex align-center justify-center"
                        height="200"
                        @click="toggle"
                        elevation="0"
                      >
                        <v-scroll-y-transition>
                          <div
                            v-if="active"
                            class="text-h4 flex-grow-1 text-center"
                          >
                            <div class="display-1">title</div>
                            <div class="grey--text text--lighten-3 text-subtitle-2">writer</div>
                            <div class="grey--text text--lighten-3 text-subtitle-2">2020.00.00</div>
                            <router-link to="/note/detail" class="text-decoration-none"> <v-btn>보러가기</v-btn></router-link>
                          </div>
                          <div v-else class="text-center">
                            <div><img src="@/assets/group/file.png" alt="" width="50"></div>
                            <div class="text-h6">post title</div>
                            <div>20.00.00</div>
                          </div>
                        </v-scroll-y-transition>
                      </v-card>
                    </v-item>
                  </v-col>
                </v-row>
              </v-container>
            </v-item-group>
            </v-col>
          </v-row> -->
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">Info.</v-col>
          </v-row>
          <v-row class="mt-10">
            
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js';
export default {
    name: 'GroupDetail',
    data() {
      return {
        groupId: this.$route.query.clId,
        myClub : [],
        host : '',
        regdate : '',
      }
    },

    created() {
      http.get('/club', {
          params : {
            clId : this.groupId,
          }
        }).then(({ data }) => {
          this.myClub = data
          this.regdate = this.myClub.clRegdate.substr(0, 10)
        });

        http.get('/club/host', {
          params : {
            clId : this.groupId,
          }
        }).then(({ data }) => {
          this.host = data
        });
    },
    methods: {
      
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>