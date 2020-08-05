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
          <v-btn small color="blue" dark class="ml-auto d-none d-sm-block" @click="categoryDialog = true"><v-icon class="mr-3" small>mdi-message-text</v-icon>new folder</v-btn>
        </v-row>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 px-0">
            <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
            <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">My notes</div>
          </v-col>
        </v-row>
        <v-row class=" mb-4">
          
          <!-- 카테고리 생성 모달 -->
          <v-menu
            v-model="categoryDialog"
            :close-on-content-click="false"
            offset-x
          >
          <v-card>
            <v-card-title>
              <span class="headline">New Category</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field label="Category Name*" v-model="cName" required></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="createCategory">생성하기</v-btn>
              <v-btn color="blue darken-1" text @click="categoryDialog = false">Close</v-btn>
            </v-card-actions>
          </v-card>
          </v-menu>

          <!-- 카테고리 수정 모달 -->
          <v-menu
            v-model="updateCategoryDialog"
            :close-on-content-click="false"
            offset-x
          >
          <v-card>
            <v-card-title>
              <span class="headline">Update Category</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12">
                    <v-text-field label="Category Name*" v-model="cUpdateName" required></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="updateCategory">수정하기</v-btn>
              <v-btn color="blue darken-1" text @click="updateCategoryDialog = false">Close</v-btn>
            </v-card-actions>
          </v-card>
          </v-menu>


          <v-sheet
              class="mx-auto mysheet"
              @drop='onDrop($event, 1)' 
              @dragover.prevent
              @dragenter.prevent
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
                    v-if="note.pCategory == 1"
                    :color="active ? 'grey' : note.pColor"
                    class="ma-4"
                    height="150"
                    width="100"
                    @click="toggle"
                    draggable
                    @dragstart='startDrag($event, note)'
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
                    <v-col cols="12" class="py-0 text-center text-subtitle-2">KEY WORDS</v-col>
                      <v-card-text class="d-flex justify-center flex-wrap py-0">
                        <div v-for="(item,i) in hashtags"  v-bind:key="i" >
                          <router-link :to="{ path: 'search', query:{name:item.name}}" class="py-0 text-center text-h6"> 
                              <v-chip
                                class="ma-2"
                                color="teal"
                                text-color="white"
                                small
                              >
                                <v-avatar left>
                                  <v-icon>mdi-checkbox-marked-circle</v-icon>
                                </v-avatar>
                                {{item.name}}
                              </v-chip>
                            </router-link>
                        </div>
                      </v-card-text>
                  </v-row>
                </v-sheet>
              </v-expand-transition>
            </v-sheet>
          

             <!-- 폴더 부분 -->

            <v-expansion-panels popout focusable>
            <v-expansion-panel
              v-for="(category, index) in this.categories" :key="index"
            >
            <div v-if="category.cId != 1" 
              @drop='onDrop($event, category.cId)' 
              @dragover.prevent
              @dragenter.prevent
            >
              <v-expansion-panel-header>{{ category.cName }}
                <template v-slot:default="{ open }">
                  <v-row no-gutters>
                    <v-col cols="4">{{ category.cName }}</v-col>
                    <v-col
                      cols="8"
                      class="d-flex justify-end py-0"
                    >
                      <v-fade-transition leave-absolute>
                        <span
                          v-if="open"
                          key="0"
                        >
                          <v-icon color="red" class="mr-2" @click="deleteCategory( category.cId )">mdi-delete</v-icon>
                          <v-icon dark color="blue" class="mr-2" @click="openUpdateDialog( category.cId, category.cName )" >mdi-wrench</v-icon>
                        </span>
                      </v-fade-transition>
                    </v-col>
                  </v-row>
                </template>
              </v-expansion-panel-header>
              <v-expansion-panel-content >
                <v-sheet
                  class="mx-auto mysheet drop-zone"
                  
                >
                <v-slide-group
                  v-model="modelInCategory"
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
                      v-if="note.pCategory == category.cId"
                      :color="active ? 'grey' : note.pColor"
                      class="ma-4"
                      height="150"
                      width="100"
                      v-bind:id="note.pId"
                      @click="toggle"
                      draggable
                      @dragstart='startDrag($event, note)'
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
                    v-if="modelInCategory != null"
                    color="grey lighten-4"
                    height="250"
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
                        <v-card-text class="d-flex justify-center flex-wrap py-0">
                          <div v-for="(item,i) in hashtags"  v-bind:key="i" >
                            <router-link :to="{ path: 'search', query:{name:item.name}}" class="py-0 text-center text-h6"> 
                              <v-chip
                                class="ma-2"
                                color="teal"
                                text-color="white"
                                small
                              >
                                <v-avatar left>
                                  <v-icon>mdi-checkbox-marked-circle</v-icon>
                                </v-avatar>
                                {{item.name}}
                              </v-chip>
                            </router-link>
                            
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
                  v-for="(tpnote, index) in tmpNotes" :key="index"
                  v-slot:default="{ active, toggle }"
                >
                <div @click="getTpNote(tpnote)">
                  <v-card
                    :color="active ? 'grey' : tpnote.pColor"
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
                    <v-flex xs12 sm3 class="text-center">
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
                        <div v-for="(item,i) in hashtags"  v-bind:key="i" >
                          <router-link :to="{ path: 'search', query:{hId:item.name}}" class="py-0 text-center text-h6"> 
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
                          </router-link>
                          
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
                  v-for="(tpnote, index) in tmpNotes" :key="index"
                  v-slot:default="{ active, toggle }"
                >
                <div @click="getTpNote(tpnote)">
                  <v-card
                    :color="active ? 'grey' : tpnote.pColor"
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
        bmToggle : 0,
        tpselected: {},
        hashtags: [],
        categoryDialog : false,
        cName : '',
        categories : [],
        modelInCategory : null,
        panel : true,
        updateCategoryDialog : false,
        cUpdateName : '',
        toUpdate : '',
      }
    },
    created() {
      window.scrollTo(0, 0);
      http.get('/category/listAll', {
        params : {
          uid : 1,
        }
      })
      .then(({data}) => {
        this.categories = data;
      });

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
        createCategory() {
           http.post('/category/insert', {
              cName : this.cName,
              cUser : 1
            })
            .then(({data}) => {
              if(data.data == 'success'){
                alert("새 폴더가 생성되었습니다.")
                this.categoryDialog = false;
                this.$router.go();
              }
            });
        },

        startDrag (evt, item) { 
          evt.dataTransfer.dropEffect = 'move'
          evt.dataTransfer.effectAllowed = 'move'
          evt.dataTransfer.setData('ID', item.pId)
        },
        onDrop (evt, cId) {
          const itemID = evt.dataTransfer.getData('ID')
          http.post('/category/update/post', {
            pId : itemID,
            pCategory : cId
          })
          .then(({data}) => {
            if(data.data == 'success'){
              this.$router.go();
            }
          });
        },

        deleteCategory (cId) {
          var ok = confirm("안에있는 내용들 다 지울겁니까?")
          if(ok) { // 다 지우기
            http.delete('/category/delete/all', {
              params : {
                cid : cId
              }
            })
            .then(({data}) => {
              if(data.data == 'success'){
                this.$router.go();
              }
            });
          } else { // 카테고리만 지우고 나머진 pCategory를 1로 변경하기
            http.delete('/category/delete/only', {
              params : {
                cid : cId
              }
            })
            .then(({data}) => {
              if(data.data == 'success'){
                this.$router.go();
              }
            });
          }
        },

        openUpdateDialog( cId, cName ) {
          this.updateCategoryDialog = true
          this.cUpdateName = cName
          this.toUpdate = cId
        },

        updateCategory () {
          http.put('/category/update', {
              cId : this.toUpdate,
              cName : this.cUpdateName
          })
            .then(({data}) => {
              if(data.data == 'success'){
                this.$router.go();
              }
            });
        },
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