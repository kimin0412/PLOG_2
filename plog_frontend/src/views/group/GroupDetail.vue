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
                    <v-col cols="12" class="text-center">
                      아직 서비스 준비중입니다.
                    </v-col>
                    <!-- <div>
                      <div class="d-none d-sm-block">
                        <div class="content-center mx-auto">
                          <v-row class="mb-2 justify-end">
                            <router-link :to="{ path: 'note/create', query:{groupId:groupId}}" class="smallicon mr-3">
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
                                      <v-col cols="12" class="py-0 text-center">Note Info.</v-col> 
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
                                    <div @click="getNoteInCategory(note)">
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
                                        <v-col cols="12" class="py-0 text-center">Note no. {{ selected2.pId }}</v-col>
                                        <v-flex class="py-0 text-center">
                                          <v-btn text icon @click="bookmark()">
                                            <v-icon large color="#FDD835" v-if="bmToggle == 1">mdi-star</v-icon>
                                            <v-icon large color="gray" v-else>mdi-star</v-icon>
                                        </v-btn>
                                        </v-flex>
                                        <v-col cols="12" class="py-0 text-center text-h6">
                                        <router-link :to="{ path: 'note/detail', query:{pId:selected2.pId}}" class="py-0 text-center text-h6"> 
                                          <v-col cols="12" class="py-0 text-center text-h6">{{ selected2.pTitle }}</v-col>
                                        </router-link>
                                        </v-col>
                                        <v-col cols="12" class="py-0 text-center text-subtitle-2">created at {{ selected2.pDate }}</v-col>
                                        <v-col cols="12" class="py-0 text-center text-subtitle-2">KEY WORDS
                                          <v-card-text class="d-flex justify-center flex-wrap py-0">
                                            <div v-for="(item,i) in hashtags2"  v-bind:key="i" >
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
                                      <v-col cols="12" class="py-0 text-center">Note Info</v-col>
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
                            </v-col>
                          </v-row>
                        </v-container>
                      </div>
                    </div> -->
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