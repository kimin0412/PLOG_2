<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row :class="{beforesearched : !manuallysearched}">
              <v-col v-if="!manuallysearched" cols="12">
                <div class="text-center display-1 font-weight-light">Search Note</div>
              </v-col>
              <v-col  v-if="!manuallysearched" cols="12" class="text-center grey--text mb-4">
                찾으려는 노트의 제목, 내용, 해시태그를 검색해주세요.
              </v-col>
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
              <v-col cols="3" class="py-0"><v-switch v-model="op1" :label="`제목`"></v-switch></v-col>
              <v-col cols="3" class="py-0"><v-switch v-model="op2" :label="`내용`"></v-switch></v-col>
              <v-col cols="3" class="py-0"><v-switch v-model="op3" :label="`해시태그`"></v-switch></v-col>
            </v-row>
            <v-row v-if="searched" class="mt-7 mb-5">
              <v-col  v-if="Notes.length < 1" cols="12" class="py-1 text-subtitle-2 text-center grey--text">검색 결과가 존재하지 않습니다.</v-col>
              <v-col  v-if="Notes.length >= 1" cols="12" class="py-1 text-subtitle-2 text-center grey--text">'{{ searchword }}' 에 대한 검색결과입니다.</v-col>
              <v-col v-if="Notes.length >= 1" cols="12" class="py-5 text-subtitle-2 text-center grey--text">
                <v-row class="mt-10">
                  <v-col cols="12" class="py-1 px-0">
                    <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">My notes</div>
                  </v-col>
                </v-row>
                <v-row class=" mb-4">
                  
                    <v-col cols="6"                            
                                v-for="(note, index) in Notes"
                                :key="index">
                    <v-hover v-slot:default="{ hover }">
                      <v-card
                                draggable
                                @dragstart="startDrag($event, note)"
                              >
                                <v-row class="mx-3" >
                                  <v-col cols="12" class="py-0 text-right mb-n10 pr-0 mt-2">
                                        <v-btn text icon>
                                          <v-icon
                                            color="#FDD835"
                                            v-if="note.pBookmark ==1"
                                            >mdi-star</v-icon
                                          >
                                          <v-icon color="grey" v-else
                                            >mdi-star</v-icon
                                          >
                                        </v-btn>
                                  </v-col>
                                  <v-col cols="2" class="pr-0 pl-2 mt-4">

                                    <img src="@/assets/icon/file.png" width="100%" alt="">
                                    <!-- <v-card :color="note.pColor" style="width: 100%; height: 100%;" class="transparent--text">c</v-card> -->
                                  </v-col>
                                  <v-col cols="10" class="pl-0">
                                    <v-card-title class="text-truncate d-block">{{note.pTitle}}</v-card-title>
                                    <v-card-subtitle>
                                      <div>{{note.pDate.substr(0,10)}}</div>
                                    </v-card-subtitle>
                                  </v-col>
                                </v-row>

                              <v-expand-transition>
                                <router-link
                                  :to="{
                                    path: 'note/detail',
                                    query: { pId: note.pId },
                                  }"
                                  class="py-0 text-center text-h6"
                                >
                                <div
                                  v-if="hover"
                                  class="d-flex transition-fast-in-fast-out grey v-card--reveal display-3 white--text"
                                  style="height: 100%;"
                                > <v-icon class="white--text display-3">mdi-book-open-page-variant</v-icon>
                                </div>
                                </router-link>
                              </v-expand-transition>
                              </v-card>
                              </v-hover>
                    </v-col>
                </v-row>
                <v-row class="mt-10 pt-15">
                  <v-col cols="12" class="py-1 px-0">
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
                              {{ tpnote.tpTitle }}
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
                            <v-col cols="12" class="py-0 text-center">Temp. Note Info.</v-col>
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
          <v-row :class="{beforesearched2 : !manuallysearched}">
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
                v-model="searchword2"
                @keyup.enter="complete2"
              >
              </v-text-field>
            </v-col>
          </v-row>
          <v-row class="px-4 mt-n10">
              <v-col cols="4" class="py-0 px-0"><v-switch class="text-center" v-model="op4" :label="`제목`"></v-switch></v-col>
              <v-col cols="4" class="py-0 px-0"><v-switch class="text-center" v-model="op5" :label="`내용`"></v-switch></v-col>
              <v-col cols="4" class="py-0 px-0"><v-switch class="text-center" v-model="op6" :label="`#`"></v-switch></v-col>
          </v-row>
          <v-row v-if="searched" class="mt-3">
            <v-col cols="12" class="py-1 text-caption text-center grey--text">'{{ searchword2 }}' 에 대한 검색결과입니다.</v-col>
          </v-row>
          <v-row v-if="searched" class="mt-3">
            <v-col cols="12" class="py-1 text-subtitle-2 text-center grey--text">
              <v-row class="mt-10">
                  <v-col cols="12" class="py-1 px-0">
                    <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">My notes</div>
                  </v-col>
                </v-row>
                <v-row class=" mb-4">
                  <v-col v-if="Notes.length < 1" cols="12">검색 결과가 없습니다.</v-col>
                  <v-sheet v-else
                      class="mx-auto mysheet"
                    >
                      <v-col cols="12"                            
                                v-for="(note, index) in Notes"
                                :key="index">
                    <v-hover v-slot:default="{ hover }">
                      <v-card
                                draggable
                                @dragstart="startDrag($event, note)"
                              >
                                <v-row class="mx-3" >
                                  <v-col cols="12" class="py-0 text-right mb-n10 pr-0 mt-2">
                                        <v-btn text icon>
                                          <v-icon
                                            color="#FDD835"
                                            v-if="note.pBookmark ==1"
                                            >mdi-star</v-icon
                                          >
                                          <v-icon color="grey" v-else
                                            >mdi-star</v-icon
                                          >
                                        </v-btn>
                                  </v-col>
                                  <v-col cols="2" class="pr-0 pl-2 mt-4">

                                    <img src="@/assets/icon/file.png" width="100%" alt="">
                                    <!-- <v-card :color="note.pColor" style="width: 100%; height: 100%;" class="transparent--text">c</v-card> -->
                                  </v-col>
                                  <v-col cols="10" class="pl-0">
                                    <v-card-title class="text-truncate d-block">{{note.pTitle}}</v-card-title>
                                    <v-card-subtitle>
                                      <div>{{note.pDate.substr(0,10)}}</div>
                                    </v-card-subtitle>
                                  </v-col>
                                </v-row>

                              <v-expand-transition>
                                <router-link
                                  :to="{
                                    path: 'note/detail',
                                    query: { pId: note.pId },
                                  }"
                                  class="py-0 text-center text-h6"
                                >
                                <div
                                  v-if="hover"
                                  class="d-flex transition-fast-in-fast-out grey v-card--reveal display-3 white--text"
                                  style="height: 100%;"
                                > <v-icon class="white--text display-3">mdi-book-open-page-variant</v-icon>
                                </div>
                                </router-link>
                              </v-expand-transition>
                              </v-card>
                              </v-hover>
                    </v-col>
                    </v-sheet>          
                </v-row>
                <v-row class="mt-10 pt-15">
                  <v-col cols="12" class="py-1 px-0">
                    <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">Temp. notes</div>
                  </v-col>
                </v-row>
                <v-row class=" mb-4">
                  <v-col v-if="tmpNotes.length < 1" cols="12">검색 결과가 없습니다.</v-col>
                  <v-sheet v-else
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
                              {{ tpnote.tpTitle }}
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
                            <v-col cols="12" class="py-0 text-center">Temp. Note Info.</v-col>
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
        searchword2: '',
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
        manuallysearched: false,
      }
    },
    watch: {
      searchword: function() {
        if (this.searchword === '') {
          this.searched = false
          this.manuallysearched = false
        }
      }
    },
    created () {
      if(this.hashtagName != undefined) {
        this.searched = true
        this.manuallysearched = true
        this.pastword = this.searched
        this.searchword = this.hashtagName
        this.searchword2 = this.hashtagName
        this.op1 = false
        this.op2 = false
        this.op3 = true
        this.op4 = false
        this.op5 = false
        this.op6 = true
        http.get('/post/list/search/hashtag', {
          params : {
            uid : this.$store.state.auth.user.id,
            hName : this.hashtagName
          }
        })
        .then(({data}) => {
          //console.log(data);
          this.Notes = data;
          //this.selected = null;
        })
        .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("error")
          } else{
            this.$router.push("/404");
          }                          
        });
      }
      
    },
    methods: {
      complete() {
        this.manuallysearched = true
        this.searched = true
        this.pastword = this.searched
        //console.log(this.searchword)
        http.get('/post/list/search', {
          params : {
            uid : this.$store.state.auth.user.id,
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
        })
        .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("error")
          } else{
            this.$router.push("/404");
          }                          
        });

        http.get('/tp/list/search', {
          params : {
            uid : this.$store.state.auth.user.id,
            searchword : this.searchword,
            c1 : this.op1,
            c2 : this.op2,
          }
        })
        .then(({data}) => {
          this.manuallysearched = true
          this.tmpNotes = data;
          //this.tpselected = null;
        })
        .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("error")
          } else{
            this.$router.push("/404");
          }                          
        });

      },
      complete2() {
        this.searched = true
        this.manuallysearched = true
        this.pastword = this.searched
        console.log(this.searchword2)
        http.get('/post/list/search', {
          params : {
            uid : this.$store.state.auth.user.id,
            searchword : this.searchword2,
            c1 : this.op4,
            c2 : this.op5,
            c3 : this.op6,
          }
        })
        .then(({data}) => {
          //console.log(data);
          this.manuallysearched = true
          this.Notes = data;
          //this.selected = null;
        })
        .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("error")
          } else{
            this.$router.push("/404");
          }                          
        });
        http.get('/tp/list/search', {
          params : {
            uid : this.$store.state.auth.user.id,
            searchword : this.searchword,
            c1 : this.op4,
            c2 : this.op5,
          }
        })
        .then(({data}) => {
          this.manuallysearched = true
          this.tmpNotes = data;
          //this.tpselected = null;
        })
        .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("error")
          } else{
            this.$router.push("/404");
          }                          
        });
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
                uid : this.$store.state.auth.user.id,
                pid : this.selected.pId,
              }
            })
            .then(({data}) => {
              data.forEach(element => {
                this.hashtags.push({"name" : element})
              });
            })
            .catch((error) => {
              if(error.response) {
                this.$router.push("servererror")
              } else if(error.request) {
                this.$router.push("error")
              } else{
                this.$router.push("/404");
              }                          
            });
        },
        getTpNote(tpnote) {
            console.log(tpnote)
            this.tpselected = tpnote
            
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
            })
            .catch((error) => {
              if(error.response) {
                this.$router.push("servererror")
              } else if(error.request) {
                this.$router.push("error")
              } else{
                this.$router.push("/404");
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
.beforesearched {
  margin-top: 20vh;
}
.beforesearched2 {
  margin-top: 10vh;
}
.newfoldericon:hover {
  cursor: pointer;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .7;
  position: absolute;
  width: 100%;
}
.thingstohover:hover {
  cursor: pointer;
}
</style>