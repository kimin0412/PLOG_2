<template>
  <div>
    <div class="d-none d-sm-block">
      <div class="content-center mx-auto">
        <v-row class="mb-2 justify-end">
          <router-link to="/note/create" class="smallicon mr-3">
            <v-btn small color="light-green" dark class="ml-auto d-none d-sm-block"><v-icon class="mr-3" small>mdi-pencil</v-icon>Write</v-btn>          
          </router-link>
          <router-link to="/search" class="smallicon mr-10">
            <v-btn small color="grey" dark class="ml-auto d-none d-sm-block"><v-icon class="mr-3" small>mdi-magnify</v-icon>Search</v-btn>          
          </router-link>
        </v-row>
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
                      <v-btn flat icon color="yellow" @click="bookmark()">
                        <v-icon>star</v-icon>
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
      </div>
    </div>
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h5">MY</v-col>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold">POSTS</v-col>
        </v-row>
        <v-row class="mb-2 justify-end mt-10">
          <router-link to="/note/create" class="smallicon mr-3">
            <v-btn small color="light-green" dark class="ml-auto"><v-icon small>mdi-pencil</v-icon></v-btn>          
          </router-link>
          <router-link to="/search" class="smallicon">
            <v-btn small color="grey" dark class="ml-auto"><v-icon small>mdi-magnify</v-icon></v-btn>          
          </router-link>
        </v-row>
        <v-row class="mt-5">
          <v-col cols="12" class="py-1 text-h6">My notes</v-col>
          <v-col cols="12">
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
                    <v-flex xs12 sm3>
                      <v-btn flat icon color="yellow" @click="bookmark()">
                        <v-icon>star</v-icon>
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
          </v-col>
        </v-row>
        <v-row class="mt-5">
          <v-col cols="12" class="py-1 text-h6">Temporary</v-col>
          <v-col cols="12">
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
                      {{ tpnote.tpTilte }}
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
                    <v-col cols="12" class="py-0 text-center">Note . {{ tpselected.tpId }}</v-col>
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
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
//import axios from 'axios';
import moment from 'moment';
import http from '@/util/http-common.js'
export default {
    name: 'Note',
    data() {
      return {
        Notes : [],
        tmpNotes : [],
        model: null,
        tpmodel: null,
        selected: {},
        tpselected: {},
        hashtags: [],
      }
    },
    created() {
      http.get('/post/list/all', {
        params : {
          uid : 1,
        }
      })
      .then(({data}) => {
        // data.forEach(element => {
        //   console.log(element);
        // });
        // console.log(data)
        this.Notes = data;
      });
      http.get('/tp/list/all', {
        params : {
          uId : 1,
        }
      })
      .then(({data}) => {
        this.tmpNotes = data;
      });
    },
    methods: {
        getFormatDate(regtime) {
            return moment(new Date(regtime)).format('YYYY.MM.DD');
        },
        getNote(note) {
            console.log(note)
            this.selected = note
            this.hashtags = []

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
        }
    },
}
</script>

<style scoped>
.note-content {
  background-color: rgba(207, 207, 207, 0.2);
}

.note-link {
  text-decoration: none;
}

.content-center {
  width: 85%;
}

.smallicon {
  text-decoration: none;
}

.mysheet {
  max-width: 100%;
}
</style>