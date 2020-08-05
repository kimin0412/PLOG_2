<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row class="mt-5">
              <v-col cols="12" class="py-1 text-h6">
                <v-text-field
                  prepend-icon="mdi-magnify"
                  placeholder="검색어를 입력해주세요"
                  single-line
                  solo
                  rounded
                  clearable
                  autofocus
                  v-model="searchword"
                  @keyup.enter="complete"
                  hint="제목, 내용, 해쉬태그 내용을 검색해보세요"
                >
                </v-text-field>
              </v-col>
              <v-col cols="3" class="py-0"></v-col>
              <v-col cols="3" class="py-0"><v-switch v-model="op1" :label="`제목${op1.toString()}`"></v-switch></v-col>
              <v-col cols="3" class="py-0"><v-switch v-model="op2" :label="`내용${op2.toString()}`"></v-switch></v-col>
              <v-col cols="3" class="py-0"><v-switch v-model="op3" :label="`해시태그${op3.toString()}`"></v-switch></v-col>
            </v-row>
            <v-row v-if="searched" class="mt-7 mb-5">
              <v-col cols="12" class="py-1 text-subtitle-2 text-center grey--text">'{{ searchword }}' 에 대한 검색결과입니다.</v-col>
              <v-col cols="12" class="py-5 text-subtitle-2 text-center grey--text">
                <v-row class="mt-10">
                  <v-col cols="12" class="py-1 px-0">
                    <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                    <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">My notes</div>
                  </v-col>
                </v-row>
                <v-row class=" mb-4">
                  <v-sheet
                      class="mx-auto mysheet"
                    >
                      <v-slide-group
                        v-model="model"
                        class="pa-4 px-0"
                        show-arrows
                        center-active
                      >
                        <v-slide-item
                          v-for="(note, index) in this.Notes" :key="index"
                          v-slot:default="{ active, toggle }"
                        >
                        <div @click="getNote(note)">
                          <v-card
                            :color="active ? 'primary' : 'grey lighten-2'"
                            class="ma-4"
                            height="150"
                            width="100"
                            @click="toggle"
                          >
                            <div class="text-center">
                              note {{ note.pId }}
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
                                  size="30"
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
                                  <v-btn small color="primary">{{item.name}}</v-btn>
                                </div>
                              </v-card-text>
                            </v-col>
                          </v-row>
                        </v-sheet>
                      </v-expand-transition>
                    </v-sheet>          
                </v-row>
                <v-row class="mt-10 pt-15">
                  <v-col cols="12" class="py-1 px-0">
                    <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                    <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">Temp. notes</div>
                  </v-col>
                </v-row>
                <v-row class=" mb-4">
                  <v-sheet
                      class="mx-auto mysheet"
                    >
                      <v-slide-group
                        v-model="tpmodel"
                        class="pa-4 px-0"
                        show-arrows
                        center-active
                      >
                        <v-slide-item
                          v-for="(tpnote, index) in this.tmpNotes" :key="index"
                          v-slot:default="{ active, toggle }"
                        >
                        <div @click="getTpNote(tpnote)">
                          <v-card
                            :color="active ? 'primary' : 'grey lighten-2'"
                            class="ma-4"
                            height="150"
                            width="100"
                            @click="toggle"
                          >
                            <div class="text-center">
                              note {{ tpnote.tpId }}
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
                                  size="30"
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
                          v-if="tpmodel != null"
                          color="grey lighten-4"
                          height="200"
                          tile
                        >
                          <v-row
                            class="fill-height"
                          >
                            <v-col cols="12" class="py-0 text-center">Note no. {{ tpselected.tpId }}</v-col>
                            <v-col cols="12" class="py-0 text-center text-h6">
                            <router-link :to="{ path: 'note/tmpupdate', query:{tpId:tpselected.tpId}}" class="py-0 text-center text-h6"> 
                              <v-col cols="12" class="py-0 text-center text-h6">{{ tpselected.tpTitle }}</v-col>
                            </router-link>
                            </v-col>
                            <v-col cols="12" class="py-0 text-center text-subtitle-2">created at {{ tpselected.tpDate }}</v-col>
                            <v-col cols="12" class="py-0 text-center text-subtitle-2">KEY WORDS</v-col>
                          </v-row>
                        </v-sheet>
                      </v-expand-transition>
                    </v-sheet>          
                </v-row>
              </v-col>          
            </v-row>
          </v-container>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">FILE</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">SEARCH</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">
              <v-text-field
                prepend-icon="mdi-magnify"
                placeholder="검색어를 입력해주세요"
                single-line
                solo
                rounded
                clearable
                autofocus
                v-model="searchword"
                @keyup.enter="complete2"
                hint="제목, 내용, 해쉬태그 내용을 검색해보세요"
              >
              </v-text-field>
            </v-col>
          </v-row>
          <v-row>
              <v-col cols="4" class="py-0"><v-switch v-model="op1" :label="`제목${op1.toString()}`"></v-switch></v-col>
              <v-col cols="4" class="py-0"><v-switch v-model="op2" :label="`내용${op2.toString()}`"></v-switch></v-col>
              <v-col cols="4" class="py-0"><v-switch v-model="op3" :label="`#${op3.toString()}`"></v-switch></v-col>
          </v-row>
          <v-row v-if="searched" class="mt-3">
            <v-col cols="12" class="py-1 text-subtitle-2 text-center grey--text">'{{ searchword }}' 에 대한 검색결과입니다.</v-col>
          </v-row>
          <v-row v-if="searched" class="mt-3">
            <v-col cols="12" class="py-1 text-subtitle-2 text-center grey--text">
              <v-row class="mt-10">
                  <v-col cols="12" class="py-1 px-0">
                    <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                    <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">My notes</div>
                  </v-col>
                </v-row>
                <v-row class=" mb-4">
                  <v-sheet
                      class="mx-auto mysheet"
                    >
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
                            :color="active ? 'primary' : 'grey lighten-2'"
                            class="ma-4"
                            height="150"
                            width="100"
                            @click="toggle"
                          >
                            <div class="text-center">
                              note {{ note.pId }}
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
                                  size="30"
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
                                  <v-btn small color="primary">{{item.name}}</v-btn>
                                </div>
                              </v-card-text>
                            </v-col>
                          </v-row>
                        </v-sheet>
                      </v-expand-transition>
                    </v-sheet>          
                </v-row>
                <v-row class="mt-10 pt-15">
                  <v-col cols="12" class="py-1 px-0">
                    <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                    <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">Temp. notes</div>
                  </v-col>
                </v-row>
                <v-row class=" mb-4">
                  <v-sheet
                      class="mx-auto mysheet"
                    >
                      <v-slide-group
                        v-model="tpmodel"
                        class="pa-4 px-0"
                        show-arrows
                        center-active
                      >
                        <v-slide-item
                          v-for="(tpnote, index) in this.tmpNotes" :key="index"
                          v-slot:default="{ active, toggle }"
                        >
                        <div @click="getTpNote(tpnote)">
                          <v-card
                            :color="active ? 'primary' : 'grey lighten-2'"
                            class="ma-4"
                            height="150"
                            width="100"
                            @click="toggle"
                          >
                            <div class="text-center">
                              note {{ tpnote.tpId }}
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
                                  size="30"
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
                          v-if="tpmodel != null"
                          color="grey lighten-4"
                          height="200"
                          tile
                        >
                          <v-row
                            class="fill-height"
                          >
                            <v-col cols="12" class="py-0 text-center">Note no. {{ tpselected.tpId }}</v-col>
                            <v-col cols="12" class="py-0 text-center text-h6">
                            <router-link :to="{ path: 'note/tmpupdate', query:{tpId:tpselected.tpId}}" class="py-0 text-center text-h6"> 
                              <v-col cols="12" class="py-0 text-center text-h6">{{ tpselected.tpTitle }}</v-col>
                            </router-link>
                            </v-col>
                            <v-col cols="12" class="py-0 text-center text-subtitle-2">created at {{ tpselected.tpDate }}</v-col>
                            <v-col cols="12" class="py-0 text-center text-subtitle-2">KEY WORDS</v-col>
                          </v-row>
                        </v-sheet>
                      </v-expand-transition>
                    </v-sheet>          
                </v-row>
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'
import moment from 'moment';
export default {
    name: 'Search',
    data() {
      return {
        op1: true,
        op2: false,
        op3: false,
        op4: true,
        op5: false,
        op6: false,
        searched: false,
        searchword: '',
        pastword: '',
        Notes : [],
        tmpNotes : [],
        model: null,
        tpmodel: null,
        selected: {},
        tpselected: {},
        hashtags: [],
        bmToggle : 0,
        hashtagName : this.$route.query.name,
      }
    },
    // watch() {
    //   searchagain
    // },

    created () {
      if(this.hashtagName != null || this.hashtagName != ''){
        this.searched = true
        this.pastword = this.searched
        this.searchword = this.hashtagName
        this.op1 = false
        this.op2 = false
        this.op3 = true
        http.get('/post/list/search/hashtag', {
          params : {
            uid : 1,
            hName : this.hashtagName
          }
        })
        .then(({data}) => {
          //console.log(data);
          this.Notes = data;
          //this.selected = null;
        });
      }
      
    },
    methods: {
      complete() {
        this.searched = true
        this.pastword = this.searched
        //console.log(this.searchword)
        http.get('/post/list/search', {
          params : {
            uid : 1,
            searchword : this.searchword,
            c1 : this.op1,
            c2 : this.op2,
            c3 : this.op3,
          }
        })
        .then(({data}) => {
          //console.log(data);
          this.Notes = data;
          //this.selected = null;
        });
        http.get('/tp/list/search', {
          params : {
            uid : 1,
            searchword : this.searchword,
            c1 : this.op1,
            c2 : this.op2,
          }
        })
        .then(({data}) => {
          this.tmpNotes = data;
          //this.tpselected = null;
        });
      },
      complete2() {
        this.searched = true
        this.pastword = this.searched
        //console.log(this.searchword)
      },
      getFormatDate(regtime) {
            return moment(new Date(regtime)).format('YYYY.MM.DD');
        },
        getNote(note) {
            console.log(note)
            this.selected = note
            this.hashtags = []
            this.bmToggle = note.pBookmark
            http.get('/hashtag/select', {
              params : {
                uid : 1,
                pid : this.selected.pId,
              }
            })
            .then(({data}) => {
              data.forEach(element => {
                this.hashtags.push({"name" : element})
              });
            });
        },
        getTpNote(tpnote) {
            console.log(tpnote)
            this.tpselected = tpnote
            
        },
        bookmark(){
          http.get('/post/bookmark', {
              params : {
                uid : 1,
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
      },  
      
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
</style>