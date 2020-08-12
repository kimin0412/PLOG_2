<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container class="mt-3">
            <v-row>
                <v-col cols="3">
                    <v-card class="mx-auto">
                        <v-row>
                            <div class="mx-auto mt-10">
                                <img src="@/assets/profile/user.png" alt="" width="100">
                            </div>
                        </v-row>
                        <v-card-subtitle class="pt-5 pb-0 text-center text-h5">{{currentUser.username}}</v-card-subtitle>
                        <v-card-text class="grey--text text-center pb-0">
                        <div>{{currentUser.email}}</div>
                        </v-card-text>
                        <v-card-text class="grey--text text-center">
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="7" class="text-left py-1 pl-5">Birth</v-col>
                                <v-col cols="5" class="text-right py-1 pr-5" v-if="currentUser.birthday">{{currentUser.birthday}}</v-col>
                                <v-col cols="5" class="text-right py-1 pr-5 font-weight-light" v-else>-</v-col>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="7" class="text-left py-1 pl-5">Phone</v-col>
                                <v-col cols="5" class="text-right py-1 pr-5" v-if="currentUser.phone">{{currentUser.phone}}</v-col>
                                <v-col cols="5" class="text-right py-1 pr-5 font-weight-light" v-else>-</v-col>
                            </v-row>
                            <v-divider></v-divider>
                        </v-card-text>
                        <v-card-actions class="d-flex justify-end pt-0">
                            <v-btn color="blue" text small class="font-weight-bold" @click.stop="dialog = true">update</v-btn>
                            <!-- 수정모달 -->
                            <v-dialog v-model="dialog" max-width="400">
                                  <v-card>
                                    <v-card-title class="headline">Edit Profile</v-card-title>
                                    <v-card-text class="pb-0">
                                      <v-col cols="12" class="py-0 mt-10">
                                        <v-text-field
                                          label="Username" :placeholder="U.username" v-model="U.username"
                                          filled rounded dense></v-text-field>
                                      </v-col>
                                      <v-col cols="12" class="py-0">
                                        <v-text-field
                                          label="E-mail" :placeholder="U.useremail" v-model="U.useremail"
                                          filled rounded dense></v-text-field>
                                      </v-col>
                                      <v-col cols="12" class="py-0">
                                        <v-text-field
                                          label="Birthday" :placeholder="U.userbirthday" v-model="U.userbirthday"
                                          hint="YYYY-MM-DD 의 형식으로 입력해주세요"
                                          persistent-hint
                                          filled rounded dense></v-text-field>
                                      </v-col>
                                      <v-col cols="12" class="py-0">
                                        <v-text-field
                                          label="Phone" :placeholder="U.userphone" v-model="U.userphone"
                                          hint="000-0000-0000 의 형식으로 입력해주세요"
                                          persistent-hint                                          
                                          filled rounded dense></v-text-field>
                                      </v-col>
                                    </v-card-text>

                                    <v-card-actions class="pt-5">
                                      <v-spacer></v-spacer>
                                      <v-btn color="orange" text @click="sendData" >Edit</v-btn>
                                      <v-btn color="grey" text @click="dialog = false" >Close</v-btn>
                                    </v-card-actions>
                                  </v-card>
                                </v-dialog>
                        </v-card-actions>
                    </v-card>
                    <v-card class="mx-auto mt-5">
                        <div v-if="mygroups.length >= 1">
                            <v-expansion-panels accordion>
                            <v-expansion-panel>
                                <v-expansion-panel-header>Groups</v-expansion-panel-header>
                                <v-expansion-panel-content>
                                    <v-row  v-for="(item,i) in mygroups" :key="i">
                                    <v-col cols="5" class="d-flex justify-center py-1 pl-5">
                                        <v-card :color="item.clColor" class="transparent--text text-right" height="20" style="width:20px;">색</v-card>
                                    </v-col>
                                    <v-col cols="7" class="text-left text-caption py-1 pr-5 text-truncate pl-0">{{ item.clName }}</v-col>
                                </v-row>
                                </v-expansion-panel-content>
                            </v-expansion-panel>
                            </v-expansion-panels>
                        </div>
                        <div v-else>
                            <v-expansion-panels accordion>
                            <v-expansion-panel>
                                <v-expansion-panel-header>Groups</v-expansion-panel-header>
                                <v-expansion-panel-content>
                                    <v-row>
                                        <v-col cols="12" class="grey--text text-caption text-center">가입한 그룹이 없습니다 :)</v-col>
                                    </v-row>
                                </v-expansion-panel-content>
                            </v-expansion-panel>
                            </v-expansion-panels>
                        </div>
                    </v-card>
                    <v-card class="mx-auto mt-2">
                        <v-expansion-panels accordion>
                        <v-expansion-panel>
                            <v-expansion-panel-header>Logs</v-expansion-panel-header>
                            <v-expansion-panel-content class="px-0">
                                <v-row class="text-center">
                                    <v-col cols="6" class="text-caption grey--text py-0">Notes</v-col>
                                    <v-col cols="6" class="text-caption grey--text py-0">Groups</v-col>
                                    <v-col cols="12" class="py-1"><v-divider></v-divider></v-col>
                                    <v-col cols="6" class="text-caption font-weight-bold black--text py-0">50</v-col>
                                    <v-col cols="6" class="text-caption font-weight-bold black--text py-0">3</v-col>
                                </v-row>
                            </v-expansion-panel-content>
                        </v-expansion-panel>
                        </v-expansion-panels>
                    </v-card>
                    <v-card class="mx-auto mt-2">
                        <v-expansion-panels accordion>
                        <v-expansion-panel>
                            <v-expansion-panel-header>more...</v-expansion-panel-header>
                            <v-expansion-panel-content class="px-0">
                                <v-row class="text-center">
                                    <v-col cols="12" class="py-0">
                                        <v-btn small color="orange" @click="logOut" block dark>Log out</v-btn>                                   
                                    </v-col>
                                    <v-col cols="12">
                                        <v-btn small color="red" block dark>withdraw</v-btn>                                   
                                    </v-col>
                                </v-row>
                            </v-expansion-panel-content>
                        </v-expansion-panel>
                        </v-expansion-panels>
                    </v-card>
                </v-col>
                <v-col cols="9">
                    <v-card class="mx-auto" height="71vh">
                        <v-tabs background-color="white" color="grey" centered show-arrows>
                            <v-tab>Monthly Logs</v-tab>
                            <v-tab>Ranking</v-tab>
                            <v-tab>TOP10</v-tab>
                            <v-tab>WordCloud</v-tab>
                            <v-tab>Bookmark</v-tab>
                            <v-tab-item>
                                <v-container>
                                <v-row>
                                    <v-col cols="12" class="d-flex justify-center mt-5">
                                        <D3LineChart :config="chart_config_formonth" :datum="chart_data_formonth" height="300" style="width: 90%;"></D3LineChart>
                                    </v-col>
                                    <v-col cols="6" class="text-right brown--text">-- Schedule</v-col>
                                    <v-col cols="6" class="text-left blue-grey--text">-- Note</v-col>
                                </v-row>
                                </v-container>
                            </v-tab-item>
                            <v-tab-item>
                                <v-container fluid>
                                <v-row class="pt-10">
                                    <v-col cols="9" class="text-center d-flex justify-center pr-0">
                                        <D3BarChart :config="chart_config_bar" :datum="chart_data_bar" height="300" style="width: 100%;"></D3BarChart>
                                    </v-col>
                                    <v-col cols="3" class="d-flex justify-center align-center pl-0">
                                        <div class="text-center">
                                          <v-card
                                            class="mx-auto"
                                            max-width="300"
                                            tile
                                          >
                                            <v-list disabled>
                                              <v-subheader class="text-h6 front-weight-bold">RANKING</v-subheader>
                                              <v-list-item-group color="primary">
                                                <div v-if="sorted.length > 5">
                                                  <v-list-item
                                                    v-for="i in 5"  :key="i"
                                                  >
                                                    <v-list-item-icon>
                                                      <v-icon v-text="i"></v-icon>
                                                    </v-list-item-icon>
                                                    <v-list-item-content>
                                                      <v-list-item-title v-text="sorted[i-1].keyword"></v-list-item-title>
                                                    </v-list-item-content>
                                                  </v-list-item>
                                                </div>
                                                <div v-else>
                                                  <v-list-item
                                                    v-for="(item, i) in sorted" :key="i"
                                                  >
                                                    <v-list-item-icon>
                                                      <v-icon v-text="i+1"></v-icon>
                                                    </v-list-item-icon>
                                                    <v-list-item-content>
                                                      <v-list-item-title v-text="item.keyword"></v-list-item-title>
                                                    </v-list-item-content>
                                                  </v-list-item>
                                                </div>
                                              </v-list-item-group>
                                            </v-list>
                                          </v-card>




                                            <!-- <div class="text-h6 front-weight-bold py-5">RANKING</div>
                                            <div v-for="(item,i) in sorted"  :key="i">
                                              <div>#{{i}} {{item.name}}</div>
                                            </div> -->
                                        </div>
                                    </v-col>
                                </v-row>
                                </v-container>
                            </v-tab-item>
                            <v-tab-item>
                                <v-container fluid>
                                <v-row>
                                    <v-col>
                                    <v-col cols="12" class="d-flex justify-center mt-5">
                                        <D3PieChart :config="chart_config" :datum="chart_data" height="300" style="width: 90%;"></D3PieChart>
                                    </v-col>
                                    </v-col>
                                </v-row>
                                </v-container>
                            </v-tab-item>
                            <v-tab-item>
                                <v-container fluid>
                                <v-row>
                                    <v-col cols="12" class="d-flex justify-center align-center">
                                        <wordcloud
                                        :data="defaultWords"
                                        nameKey="name"
                                        valueKey="value"
                                        :color="myColors"
                                        :showTooltip="true"
                                        :wordClick="wordClickHandler"
                                         class="mt-5"
                                        >
                                        </wordcloud> 
                                    </v-col>
                                </v-row>
                                </v-container>
                            </v-tab-item>   
                            <v-tab-item>
                                <v-container fluid>
                                <v-row>
                                    <v-col cols="12">
                                        <v-expansion-panels popout focusable>
                                        <v-expansion-panel>
                                            <div>
                                            <v-expansion-panel-header>My Bookmark</v-expansion-panel-header>
                                            <v-expansion-panel-content >
                                                <v-sheet class="mx-auto mysheet drop-zone">
                                                <v-slide-group
                                                v-model="model"
                                                class="pa-4 px-0"
                                                show-arrows
                                                center-active
                                                >
                                                <v-slide-item
                                                    v-for="(note, index) in Notes" :key="index"
                                                    v-slot:default="{ active, toggle }"
                                                >
                                                <div @click="getNote(note)">
                                                    <v-card
                                                    :color="active ? 'grey' : note.pColor"
                                                    class="ma-4"
                                                    height="150"
                                                    width="100"
                                                    v-bind:id="note.pId"
                                                    @click="toggle"
                                                    >
                                                    <div class="text-center">
                                                        {{ note.pTitle }}
                                                    </div>
                                                    <v-row
                                                        class="fill-height"
                                                        align="center"
                                                        justify="center"
                                                    >
                                                        <v-scale-transition>
                                                        <v-icon
                                                            v-if="active"
                                                            color="white"
                                                            size="48"
                                                            v-text="'mdi-close-circle-outline'"
                                                        ></v-icon>
                                                        </v-scale-transition>
                                                    </v-row>
                                                    </v-card>
                                                    </div> 
                                                </v-slide-item>
                                                </v-slide-group>
                                                <v-expand-transition>
                                                <v-sheet
                                                    v-if="model != null"
                                                    color="grey lighten-4"
                                                    height="200"
                                                    tile
                                                >
                                                    <v-row
                                                    class="fill-height"
                                                    >
                                                    <v-col cols="12" class="py-0 text-center">Note no. {{ selected.pId }}</v-col>
                                                    <v-flex class="py-0 text-center">
                                                        <v-btn text icon @click="bookmark()">
                                                        <v-icon large color="#FDD835" v-if="bmToggle == 1">mdi-star</v-icon>
                                                        <v-icon large color="gray" v-else>mdi-star</v-icon>
                                                        </v-btn>
                                                    </v-flex>
                                                    <v-col cols="12" class="py-0 text-center text-h6">
                                                    <router-link :to="{ path: 'note/detail', query:{pId:selected.pId}}" class="py-0 text-center text-h6"> 
                                                        <v-col cols="12" class="py-0 text-center text-h6">{{ selected.pTitle }}</v-col>
                                                    </router-link>
                                                    </v-col>
                                                    <v-col cols="12" class="py-0 text-center text-subtitle-2">created at {{ selected.pDate }}</v-col>
                                                    <v-col cols="12" class="py-0 text-center text-subtitle-2">KEY WORDS
                                                        <v-card-text class="d-flex justify-center py-0">
                                                        <div v-for="item in hashtags"  v-bind:key = "item" >
                                                            <v-chip
                                                            class="ma-2"
                                                            color="teal"
                                                            text-color="white"
                                                            >
                                                            <v-avatar left>
                                                                <v-icon>mdi-checkbox-marked-circle</v-icon>
                                                            </v-avatar>
                                                            {{item.name}}
                                                            </v-chip>
                                                        </div>
                                                        </v-card-text>
                                                    </v-col>
                                                    </v-row>
                                                </v-sheet>
                                                </v-expand-transition>
                                                </v-sheet>
                                                </v-expansion-panel-content>
                                            </div>
                                            </v-expansion-panel>
                                        </v-expansion-panels>
                                    </v-col>
                                </v-row>
                                </v-container>
                            </v-tab-item>                            
                        </v-tabs>
                    </v-card>
                </v-col>
            </v-row>
          </v-container>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">PROFILE</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">MY PAGE</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">Profile</v-col>
            <v-col cols="4" class="ml-1 mt-3">
                <img src="@/assets/profile/user.png" alt="user-image" class="profile-image">
            </v-col>
            <v-col cols="12">
                <v-simple-table dense>
                  <template v-slot:default>
                    <tbody>
                     <tr>
                        <td>ID</td>
                        <td>{{currentUser.username}}</td>
                      </tr>
                      <tr>
                        <td>Email</td>
                        <td>{{currentUser.email}}</td>
                      </tr>
                      <tr>
                        <td>Birthday</td>
                        <td v-if="currentUser.birthday">{{currentUser.birthday}}</td>
                        <td v-else>정보가 없습니다.</td>
                      </tr>
                      <tr>
                        <td>Phone</td>
                        <td v-if="currentUser.phone">{{currentUser.phone}}</td>
                        <td v-else>정보가 없습니다.</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>              
            </v-col>
            <v-col cols="12">
              <v-row class="d-flex justify-end mt-5">
                <v-btn color="error" dark small @click="logOut">Log Out</v-btn>
              </v-row>              
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import { D3LineChart } from 'vue-d3-charts'
import { D3PieChart } from 'vue-d3-charts'
import wordcloud from 'vue-wordcloud'
import { D3BarChart } from 'vue-d3-charts'

import http from '@/util/http-common.js'

export default {
    name: 'Profile',
    components: {
        D3LineChart, D3PieChart,wordcloud,D3BarChart,
    },
    data() {
      return {
        dialog: false,
        benched: 0,
        mygroups: [],
        Notes : [],
        selected : {},
        bmToggle : 0,
        hashtags : [],
        panel : true,
        model: null,
        // 프로필 수정
        U: {
          username: '',
          userbirthday: '',
          useremail: '',
          userpassword: '',
          userphone: '',
        },
        // 먼슬리 통계
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
                scheme: ['#795548', '#607D8B'],
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
        // top10
        chart_data: [
                {count: 50, name: 'Vue'},
                {count: 40, name: 'JS'},
                {count: 35, name: 'JAVA'},
                {count: 25, name: 'DB'},
                {count: 22, name: 'Spring'},
                {count: 17, name: '필기'},
                {count: 11, name: 'Django'},
                {count: 9, name: 'Python'},
                {count: 10, name: 'React'},
                {count: 100, name: 'else'},
            ],
        chart_config: {
                key: 'name',
                value: 'count',
                color: {scheme: 'schemeTableau10'},
                radius: {inner: 0}
            },
        count: 1,
        // wordcloud2
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
        ],
        // 막대
      chart_data_bar: [
        {c: 20, keyword: 'lorem'},
        {c: 28, keyword: 'ipsum'},
        {c: 35, keyword: 'dolor'},
        {c: 60, keyword: 'sit'},
        {c: 65, keyword: 'amet'},
      ],
      chart_config_bar: {
        key: 'keyword',
        values: ['c'],
        color: {
          keys: {
            vue: '#3F51B5',
          },
        },

      },
      sorted : [
        {c: 20, keyword: 'lorem'},
        {c: 28, keyword: 'ipsum'},
        {c: 35, keyword: 'dolor'},
        {c: 60, keyword: 'sit'},
        {c: 65, keyword: 'amet'},
      ],

      }
    },
    created() {
      // 수정페이지 관련
      this.U.username = this.$store.state.auth.user.username
      this.U.userbirthday = this.$store.state.auth.user.birthday
      this.U.useremail= this.$store.state.auth.user.email
      this.U.userpassword= this.$store.state.auth.user.password
      this.U.userphone= this.$store.state.auth.user.phone
      // 북마크
      http.get('/post/list/bookmark',{
        params : {
          uid : this.$store.state.auth.user.id,
        }
      }).then(({data}) => {
         this.Notes = data;
      });
      http.get('/club/list', {
        params : {
          uId : this.$store.state.auth.user.id,
        }
      }).then(({ data }) => {
        // this.mygroups = data
        this.mygroups = data
      });

      this.chart_data_bar = []
      this.defaultWords = []
      this.chart_data = []
      http.get('/hashtag/all', {
        params : {
          uid : this.$store.state.auth.user.id,
        }
      })
      .then(({data}) => {
        data.forEach(element => {
          this.chart_data_bar.push({"keyword" : element.hName, "c":element.hId})
          this.defaultWords.push({"name" : element.hName, "value":element.hId})
          this.chart_data.push({name: element.hName, count:element.hId})
        });

        this.sorted = this.chart_data_bar
        var sortingField = "c";

        this.sorted.sort((a, b) => {
          return b[sortingField] - a[sortingField];
        });
      });

      //먼슬리
      this.chart_data_formonth = []
      http.get('/post/count', {
        params : {
          uid : this.$store.state.auth.user.id,
        }
      }).then(({data}) => {
        data.forEach(element => {
          this.chart_data_formonth.push({"note" : element.pBookmark, "schedule":element.pCategory, "date":element.pClub})
        });

      });

     },


     methods: {
      wordClickHandler(name, value, vm) {
        console.log('wordClickHandler', name, value, vm);
      },
      logOut() {
        this.$store.dispatch('auth/logout');
        this.$router.push('/logout');
      },
       getNote(note) {
            console.log(note)
            this.selected = note
            this.hashtags = []
            this.bmToggle = note.pBookmark
            http.get('/hashtag/select', {
              params : {
                uid : this.$store.state.auth.user.id,
                pid : this.selected.pId,
              }
            })
            .then(({data}) => {
              data.forEach(element => {
                this.hashtags.push({"name" : element})
              });
            });
        },
        bookmark(){
          http.get('/post/bookmark', {
              params : {
                uid : this.$store.state.auth.user.id,
                pid : this.selected.pId,
              }
            })
            .then((response) => {
              if(response === 'success'){
                console.log("success");
              }              
            });
            if(this.bmToggle == 1){
                this.bmToggle = 0;
                this.selected.pBookmark = 0;
            } else {
                this.bmToggle = 1;
                this.selected.pBookmark = 1;
            }
        },
        sendData() {
          // 여기서 검증한 뒤에 axios 쏘기
        },
     },
    computed: {
      currentUser(){
        return this.$store.state.auth.user;
      }
    },
    mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>