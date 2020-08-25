<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container class="mt-3">
            <v-row>
                <v-col cols="3">
                    <v-card class="mx-auto" min-height="71vh">
                        <v-card-text class="grey--text text-left">
                            <v-row>
                                <v-col cols="12" class="py-0">
                                  <v-subheader class="black--text font-weight-bold">일반</v-subheader>
                                </v-col>
                                <v-tabs
                                  v-model="tab2"
                                  background-color="transparent"
                                  vertical
                                  class="font-weight-bold"
                                >
                                  <v-tab class="text-left"
                                  @drop="onDrop($event, 1)"
                                  @dragover.prevent
                                  @dragenter.prevent
                                  >전체 노트</v-tab>
                                  <v-tab class="text-left">임시 저장</v-tab>
                                <v-col cols="12" class="py-0 mt-5">
                                  <v-subheader class="black--text font-weight-bold">카테고리</v-subheader>
                                </v-col>
                                  <div v-if="categories.length >= 1">
                                  <v-tab
                                    v-for="(category, index) in this.categories"
                                    :key="index"
                                    class="text-left"
                                    @click="getNotesInCategory(category.cId)"
                                    @drop="onDrop($event, category.cId)"
                                    @dragover.prevent
                                    @dragenter.prevent
                                  >
                                    {{ category.cName }}
                                  </v-tab>
                                  <v-col cols="12" @click="categoryDialog = true" class="px-0 text-center newfoldericon blue--text text--darken-2">+</v-col>
                                  </div>
                                  <div v-else class="text-center grey--text font-weight-thin mt-2 text-caption">카테고리 없음<br>
                                    <v-col cols="12" @click="categoryDialog = true" class="px-0 text-center newfoldericon blue--text text-caption"><v-icon small class="blue--text mr-2">mdi-folder-multiple-plus</v-icon>추가</v-col>
                                  </div>
                                </v-tabs>                              
                            </v-row>
                        </v-card-text>
                    </v-card>
                </v-col>
                <v-col cols="9">
                    <v-card class="mx-auto" min-height="71vh">
                        <v-tabs-items v-model="tab2">
                        <v-tab-item>
                          <v-row class="mx-5 pt-5">
                          <v-col cols="12" v-if="Notes.length == 0" class="text-center" style="margin-top: 30vh;">
                              아직 노트가 없습니다.
                          </v-col>
                          <v-col cols="12" v-else class="px-0 pt-0">
                            <v-row class="px-0" style="overflow: auto; height: 65vh;">
                            <v-col cols="12" class="grey--text text-center pt-0 text-caption">즐겨찾기에 추가한 노트가 상단에 배치됩니다.</v-col>
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

                                    <!-- <img src="@/assets/icon/file.png" width="100%" alt=""> -->
                                    <v-card :color="note.pColor" style="width: 100%; height: 70%;" class="transparent--text">c</v-card>
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
                              <v-card >
                              <v-sheet
                                class="mx-auto"
                                max-width="700"
                                height="6vh"
                                color="grey lighten-5"
                              >
                                <v-slide-group show-arrows>
                                  <v-slide-item
                                    v-for="(item, i) in hashtags" v-bind:key="i"
                                    v-slot:default="{ active, toggle }"
                                    class = "mt-2 mb-2"
                                  >
                                  <router-link
                                    :to="{ path: 'search', query: { name: item.hName } }"
                                    class="py-0 text-center text-h6 text-decoration-none"
                                  >
                                    <v-btn
                                      class="mx-1"
                                      color="grey lighten-2"
                                      :input-value="active"
                                      active-class="purple white--text"
                                      depressed
                                      rounded
                                      small
                                      @click="toggle"
                                      v-if="item.hId == note.pId"
                                    >
                                      # {{ item.hName }}
                                    </v-btn>
                                  </router-link>
                                  </v-slide-item>
                                </v-slide-group>
                              </v-sheet>
                              </v-card>
                            </v-col>
                          </v-row>
                          </v-col>
                          </v-row>
                        </v-tab-item>
                        <v-tab-item>
                          <v-row class="mx-5 pt-5" >
                            <v-col cols="12" v-if="tmpNotes.length == 0" class="text-center pt-5" style="margin-top: 30vh;">
                              임시 저장한 노트가 없습니다.
                            </v-col>
                            <v-col cols="12" v-else class="pt-5">
                              <v-row justify="center" align="center" height="60vh">
                                <v-col cols="12">
                                  <v-simple-table fixed-header>
                                    <template v-slot:default>
                                      <thead>
                                        <tr>
                                          <th class="text-center font-weight-bold">No.</th>
                                          <th class="text-center font-weight-bold">Title</th>
                                          <th class="text-center font-weight-bold">Date</th>
                                          <th class="text-center font-weight-bold"></th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                        <tr v-for="(tpnote, index) in tmpNotes"
                                            :key="index">
                                          <td class="text-center">{{ index+1 }}</td>
                                          <td class="text-center">{{ tpnote.tpTitle }}</td>
                                          <td class="text-center">{{ tpnote.tpDate.substr(0,10) }}</td>
                                          <td class="text-center"><router-link
                                                :to="{
                                                  path: 'note/tmpupdate',
                                                  query: { tpId: tpnote.tpId },
                                                }"
                                                class="py-0 text-center text-decoration-none text-center"
                                              >
                                              <v-icon class="blue--text mr-2">mdi-playlist-edit</v-icon>수정하기
                                              </router-link>
                                          </td>
                                        </tr>
                                      </tbody>
                                    </template>
                                  </v-simple-table>
                              </v-col>
                              </v-row>
                            </v-col>
                          </v-row>
                        </v-tab-item>
                        <div v-if="categories.length >= 1">
                        <v-tab-item
                          v-for="(category, i) in categories"
                          :key="i"
                        >
                          <v-row class="mx-5 pt-5">
                            <v-col cols="12" class="d-flex justify-space-between">
                              <div class="ml-5 font-weight-bold text-h6">
                                {{category.cName}}
                              </div>
                              <div>
                              <div @click="deleteCategory(category.cId)" class="red--text thingstohover d-inline-block">
                              <v-icon color="red" class="mr-1"
                              >mdi-delete</v-icon>
                              </div>
                              <div @click="openUpdateDialog(category.cId, category.cName)" class="grey--text thingstohover d-inline-block ml-3">                              
                               <v-icon color="grey" class="mr-1"
                              >mdi-wrench</v-icon>
                              </div>
                              </div>
                            </v-col>
                            <v-col cols="12" v-if="NotesInFolder.length == 0" class="text-center" style="margin-top: 25vh;">
                              {{category.cName}} 카테고리에 저장한 노트가 없습니다.
                            </v-col>
                            <v-col cols="12" v-else class="px-0">
                              <v-row class="pt-5"  style="overflow: auto; height: 57vh; margin-top: 3vh;">
                                
                                <v-col cols="6" v-for="(note, index) in NotesInFolder" :key="index">
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
                                        <v-card :color="note.pColor" style="width: 100%; height: 70%;" class="transparent--text">c</v-card>
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
                                  <v-card >
                                  <v-sheet
                                    class="mx-auto"
                                    max-width="700"
                                    height="6vh"
                                    color="grey lighten-5"
                                  >
                                    <v-slide-group show-arrows>
                                      <v-slide-item
                                        v-for="(item, i) in hashtags" v-bind:key="i"
                                        v-slot:default="{ active, toggle }"
                                        class = "mt-2 mb-2"
                                      >
                                      <router-link
                                        :to="{ path: 'search', query: { name: item.hName } }"
                                        class="py-0 text-center text-h6 text-decoration-none"
                                      >
                                        <v-btn
                                          class="mx-1"
                                          color="grey lighten-2"
                                          :input-value="active"
                                          active-class="purple white--text"
                                          depressed
                                          rounded
                                          small
                                          @click="toggle"
                                          v-if="item.hId == note.pId"
                                        >
                                          # {{ item.hName }}
                                        </v-btn>
                                      </router-link>
                                      </v-slide-item>
                                    </v-slide-group>
                                  </v-sheet>
                                  </v-card>
                                </v-col>
                                
                              </v-row>
                            </v-col>
                          </v-row>


                        </v-tab-item>
                        </div>
                      </v-tabs-items>
                    </v-card>
                </v-col>
            </v-row>
            <!--카테고리 생성 모달-->
            <v-dialog
              class="categorymodal"
              v-model="categoryDialog"
              :close-on-content-click="false"
              offset-x
              max-width="500">
              <v-card>
                  <v-card-title>
                      <span class="headline">New Category</span>
                  </v-card-title>
                  <v-card-text>
                      <v-container>
                      <v-row>
                          <v-col cols="12">
                          <v-text-field placeholder="새로운 카테고리 이름을 입력해주세요" v-model="cName" required></v-text-field>
                          </v-col>
                      </v-row>
                      </v-container>
                  </v-card-text>
                  <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="createCategory">Create</v-btn>
                      <v-btn color="grey" text @click="categoryDialog = false">Close</v-btn>
                  </v-card-actions>
              </v-card>
          </v-dialog>
          <!-- 카테고리 수정 모달 -->
            <v-dialog
            class="categorymodal"
            v-model="updateCategoryDialog"
            :close-on-content-click="false"
            offset-x
            max-width="500"
          >
            <v-card>
              <v-card-title>
                <span class="headline">Update Category</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="Category Name*"
                        v-model="cUpdateName"
                        required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="updateCategory"
                  >Done</v-btn
                >
                <v-btn
                  color="grey"
                  text
                  @click="updateCategoryDialog = false"
                  >Close</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>

          </v-container>
          <v-card class="menubar d-flex align-center">
            <v-row class="px-0 py-0" justify="center" align="center">
              <v-col cols="12" @click="categoryDialog = true" class="px-0 text-center newfoldericon"><v-icon class="white--text">mdi-folder-multiple-plus</v-icon></v-col>
              <v-col cols="12" class="px-0 text-center"><router-link to="/note/create" class="text-decoration-none"><v-icon class="white--text">mdi-pen-plus</v-icon></router-link></v-col>
              <v-col cols="12" class="px-0 text-center"><router-link to="/search" class="text-decoration-none"><v-icon class="white--text">mdi-magnify</v-icon></router-link></v-col>
            </v-row>
          </v-card>
        </div>
      </div>


      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">NOTE</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">My Notes</v-col>
          </v-row>
            <v-row>
              <v-col cols="5"></v-col>
              <v-col cols="7" class="mt-5 text-right text-caption py-0 ">
                <v-btn @click="categoryDialog = true" class="mb-2 mr-2" color="grey lighten-1">
                  <v-icon class="white--text">mdi-folder-multiple-plus</v-icon>
                </v-btn>                 
                <v-btn class="mb-2 mr-2" color="grey lighten-1">
                  <router-link to="/note/create" class="text-decoration-none"><v-icon class="white--text">mdi-pen-plus</v-icon></router-link>                  
                </v-btn>
                <v-btn class="mb-2 mr-2" color="grey lighten-1">
                  <router-link to="/search" class="text-decoration-none"><v-icon class="white--text">mdi-magnify</v-icon></router-link>
                </v-btn>
              </v-col>
                <v-col cols="12">
                    <v-tabs
                      v-model="tab"
                      background-color="transparent"
                      class="font-weight-bold"
                      show-arrows
                      >
                      <v-tab class="text-left"
                      >전체 노트</v-tab>
                      <v-tab
                        v-for="(category, index) in this.categories"
                        :key="index"
                        class="text-left"
                        @click="getNotesInCategory(category.cId)"
                      >
                        {{ category.cName }}
                      </v-tab>
                    </v-tabs>                              
                </v-col>
                <v-col cols="12" class="pt-0">
                    <v-card class="mx-auto" min-height="71vh">
                        <v-tabs-items v-model="tab">
                        <v-tab-item>
                          <v-row class="mx-5 pt-5">
                          <v-col cols="12" v-if="Notes.length == 0" class="text-center pt-5 grey--text text-caption" style="margin-top: 30vh;">
                              아직 노트가 없습니다.
                          </v-col>
                          <v-col cols="12" v-else class="px-0 pt-0">
                            <v-row class="px-0" style="overflow: auto; min-height: 65vh;">
                            <v-col cols="12" class="grey--text text-center pt-0 text-caption">즐겨찾기에 추가한 노트가 상단에 배치됩니다.</v-col>
                            <v-col cols="12"                            
                                v-for="(note, index) in Notes"
                                :key="index"
                                class="py-0">
                              <router-link
                                    :to="{
                                      path: 'note/detail',
                                      query: { pId: note.pId },
                                    }"
                                    class="py-0 text-center text-h6 text-decoration-none"
                                  >
                              <v-card>
                                <v-row class="mx-3" >
                                  <v-col cols="12" class="py-0 text-right mb-n10 pr-0 mt-2">
                                    <v-btn text icon>
                                      <v-icon
                                        color="#FDD835"
                                        v-if="note.pBookmark ==1">mdi-star</v-icon>
                                      <v-icon color="grey" v-else>mdi-star</v-icon>
                                    </v-btn>
                                  </v-col>
                                  <v-col cols="2" class="pr-0 pl-2 mt-4">
                                    <v-card :color="note.pColor" style="width: 100%; height: 70%;" class="transparent--text">c</v-card>
                                  </v-col>
                                  <v-col cols="10" class="pl-0">
                                    <v-card-title class="text-truncate d-block">{{note.pTitle}}</v-card-title>
                                    <v-card-subtitle>
                                      <div>{{note.pDate.substr(0,10)}}</div>
                                    </v-card-subtitle>
                                  </v-col>
                                </v-row>
                              </v-card>
                              </router-link>
                              <v-card class="mb-5">
                              <v-sheet
                                class="mx-auto"
                                max-width="700"
                                height="6vh"
                                color="grey lighten-5"
                              >
                                <v-slide-group show-arrows>
                                  <v-slide-item
                                    v-for="(item, i) in hashtags" v-bind:key="i"
                                    v-slot:default="{ active, toggle }"
                                    class = "mt-2 mb-2"
                                  >
                                  <router-link
                                    :to="{ path: 'search', query: { name: item.hName } }"
                                    class="py-0 text-center text-h6 text-decoration-none"
                                  >
                                    <v-btn
                                      class="mx-1"
                                      color="grey lighten-2"
                                      :input-value="active"
                                      active-class="purple white--text"
                                      depressed
                                      rounded
                                      small
                                      @click="toggle"
                                      v-if="item.hId == note.pId"
                                    >
                                      # {{ item.hName }}
                                    </v-btn>
                                  </router-link>
                                  </v-slide-item>
                                </v-slide-group>
                              </v-sheet>
                              </v-card>
                            </v-col>
                          </v-row>
                          </v-col>
                          </v-row>
                        </v-tab-item>
                        <div v-if="categories.length >= 1">
                        <v-tab-item
                          v-for="(category, i) in categories"
                          :key="i"
                        >
                          <v-row class="mx-5 pt-5">
                            <v-col cols="12" class="d-flex justify-space-between">
                              <div class="ml-2 font-weight-bold text-h6">
                                {{category.cName}}
                              </div>
                              <div>
                              <div @click="deleteCategory(category.cId)" class="red--text thingstohover d-inline-block">
                              <v-icon color="red" class="mr-1" small
                              >mdi-delete</v-icon>
                              </div>
                              <div @click="openUpdateDialog(category.cId, category.cName)" class="grey--text thingstohover d-inline-block ml-3">                              
                               <v-icon color="grey" class="mr-1" small
                              >mdi-wrench</v-icon>
                              </div>
                              </div>
                            </v-col>
                            <v-col cols="12" v-if="NotesInFolder.length == 0" class="text-center grey--text text-caption" style="margin-top: 25vh;">
                              "{{category.cName}}" 카테고리에 <br> 저장한 노트가 없습니다.
                            </v-col>
                            <v-col cols="12" v-else class="px-0">
                              <v-row style="overflow: auto; height: 57vh; margin-top: 3vh;">
                                <div>
                                <v-col cols="12" v-for="(note, index) in NotesInFolder" :key="index">
                                  <router-link
                                        :to="{
                                          path: 'note/detail',
                                          query: { pId: note.pId },
                                        }"
                                        class="py-0 text-center text-h6 text-decoration-none"
                                      ><v-card
                                  >
                                    <v-row class="mx-3" >
                                      <v-col cols="12" class="py-0 text-right mb-n10 pr-0 mt-2">
                                            <v-btn text icon>
                                              <v-icon
                                                color="#FDD835"
                                                v-if="note.pBookmark ==1"
                                                >mdi-star</v-icon>
                                              <v-icon color="grey" v-else
                                                >mdi-star</v-icon>
                                            </v-btn>
                                      </v-col>
                                      <v-col cols="2" class="pr-0 pl-2 mt-4">
                                        <v-card :color="note.pColor" style="width: 100%; height: 70%;" class="transparent--text">c</v-card>
                                        <!-- <v-card :color="note.pColor" style="width: 100%; height: 100%;" class="transparent--text">c</v-card> -->
                                      </v-col>
                                      <v-col cols="10" class="pl-0">
                                        <v-card-title class="text-truncate d-block">{{note.pTitle}}</v-card-title>
                                        <v-card-subtitle>
                                          <div>{{note.pDate.substr(0,10)}}</div>
                                        </v-card-subtitle>
                                      </v-col>
                                    </v-row>
                                  </v-card>
                                  </router-link>
                                  <v-card class="mb-3">
                              <v-sheet
                                class="mx-auto"
                                max-width="700"
                                height="6vh"
                                color="grey lighten-5"
                              >
                                <v-slide-group show-arrows>
                                  <v-slide-item
                                    v-for="(item, i) in hashtags" v-bind:key="i"
                                    v-slot:default="{ active, toggle }"
                                    class = "mt-2 mb-2"
                                  >
                                  <router-link
                                    :to="{ path: 'search', query: { name: item.hName } }"
                                    class="py-0 text-center text-h6 text-decoration-none"
                                  >
                                    <v-btn
                                      class="mx-1"
                                      color="grey lighten-2"
                                      :input-value="active"
                                      active-class="purple white--text"
                                      depressed
                                      rounded
                                      small
                                      @click="toggle"
                                      v-if="item.hId == note.pId"
                                    >
                                      # {{ item.hName }}
                                    </v-btn>
                                  </router-link>
                                  </v-slide-item>
                                </v-slide-group>
                              </v-sheet>
                              </v-card>
                                </v-col>
                                </div>
                              </v-row>
                            </v-col>
                          </v-row>
                        </v-tab-item>
                        </div>
                      </v-tabs-items>
                    </v-card>
                </v-col>
            </v-row>
            <!--카테고리 생성 모달-->
            <!-- <v-dialog
              class="categorymodal"
              v-model="categoryDialog"
              :close-on-content-click="false"
              offset-x
              max-width="500">
              <v-card>
                  <v-card-title>
                      <span class="headline">New Category</span>
                  </v-card-title>
                  <v-card-text>
                      <v-container>
                      <v-row>
                          <v-col cols="12">
                          <v-text-field placeholder="새로운 카테고리 이름을 입력해주세요" v-model="cName" required></v-text-field>
                          </v-col>
                      </v-row>
                      </v-container>
                  </v-card-text>
                  <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn color="blue darken-1" text @click="createCategory">Create</v-btn>
                      <v-btn color="grey" text @click="categoryDialog = false">Close</v-btn>
                  </v-card-actions>
              </v-card>
          </v-dialog> -->
          <!-- 카테고리 수정 모달 -->
            <!-- <v-dialog
            class="categorymodal"
            v-model="updateCategoryDialog"
            :close-on-content-click="false"
            offset-x
            max-width="500"
          >
            <v-card>
              <v-card-title>
                <span class="headline">Update Category</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="Category Name*"
                        v-model="cUpdateName"
                        required
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="updateCategory"
                  >Done</v-btn
                >
                <v-btn
                  color="grey"
                  text
                  @click="updateCategoryDialog = false"
                  >Close</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog> -->
        </v-container>
      </div>
  </div>
</template>
<script>
import moment from "moment";
import http from '@/util/http-common.js';

export default {
    name: 'Note2',
    data() {
      return {
        tab: null,
        tab2 : null,
        items: ['Appetizers', 'Entrees', 'Deserts', 'Cocktails',],
        text: 'Lorem ipsum dolor sit amet',

        Notes: [],
        tmpNotes: [],
        model: null,
        tpmodel: null,
        selected: {},
        bmToggle: 0,
        tpselected: {},
        hashtags: [],
        categoryDialog: false,
        cName: "",
        categories: [],
        modelInCategory: null,
        panel: true,
        updateCategoryDialog: false,
        cUpdateName: "",
        toUpdate: "",
        selected2: {},
        hashtags2: [],
        bmToggle2: 0,
        NotesInFolder : [],
        hashtagsInCategory : [],


      }
    },
    created() {
      window.scrollTo({top:0, left:0, behavior:'smooth'});
      http.get("/category/listAll", {
              params: {
                uid: this.$store.state.auth.user.id,
              },
            })
            .then(({ data }) => {
              this.categories = data;
              this.length2 = data.length;
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

          http
            .get("/post/list/all", {
              params: {
                uid: this.$store.state.auth.user.id,
              },
            })
            .then(({ data }) => {
              this.Notes = data;
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

          http
            .get("/tp/list/all", {
              params: {
                uId: this.$store.state.auth.user.id,
              },
            })
            .then(({ data }) => {
              this.tmpNotes = data;
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
      http.get("/hashtag/notes", {
        params: {
          uid: this.$store.state.auth.user.id,
          clId : 1,
          cId : 1,
        },
      }).then(({ data }) => {
        this.hashtags = data;
      }).catch((error) => {
        if(error.response) {
          this.$router.push("servererror")
        } else if(error.request) {
          this.$router.push("error")
        } else{
          this.$router.push("/404");
        }                          
      });
    },
    methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format("YYYY.MM.DD");
    },
    getNote(note) {
      this.selected = note;
      this.hashtags = [];
      this.bmToggle = note.pBookmark;
      this.hashtags = [];
      http.get("/hashtag/select", {
          params: {
            uid: this.$store.state.auth.user.id,
            pid: this.selected.pId,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.hashtags.push({ name: element });
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
    getNotesInCategory(cid) {
      this.NotesInFolder = []
      http.get("/post/list/category", {
        params: {
          uid : this.$store.state.auth.user.id,
          cid : cid,
          clid : 1,
        },
      }).then(({ data }) => {
        this.NotesInFolder = data;
      }).catch((error) => {
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
      this.tpselected = tpnote;
    },
    bookmark() {
      http
        .get("/post/bookmark", {
          params: {
            uid: this.$store.state.auth.user.id,
            pid: this.selected.pId,
          },
        })
        .then((response) => {
          if (response === "success") {
            // console.log("success");
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
      if (this.bmToggle == 1) {
        this.bmToggle = 0;
        this.selected.pBookmark = 0;
      } else {
        this.bmToggle = 1;
        this.selected.pBookmark = 1;
      }
    },

    bookmark2() {
      http
        .get("/post/bookmark", {
          params: {
            uid: this.$store.state.auth.user.id,
            pid: this.selected2.pId,
          },
        })
        .then((response) => {
          if (response === "success") {
            // console.log("success");
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

      if (this.bmToggle2 == 1) {
        this.bmToggle2 = 0;
        this.selected2.pBookmark = 0;
      } else {
        this.bmToggle2 = 1;
        this.selected2.pBookmark = 1;
      }
    },
    createCategory() {
      if (this.cName == "") {
        this.$dialog.notify.warning("카테고리 이름을 쓰세요 😤", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else {
        http
          .post("/category/insert", {
            cName: this.cName,
            cUser: this.$store.state.auth.user.id,
            cClub:1
          })
          .then(({ data }) => {
            if (data.data == "success") {
              this.$dialog.notify.success("새 폴더가 생성되었습니다 😚", {
                position: "bottom-right",
                timeout: 3000,
              });
              this.categoryDialog = false;
              this.$router.go();
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
      }
    },

    startDrag(evt, item) {
      evt.dataTransfer.dropEffect = "move";
      evt.dataTransfer.effectAllowed = "move";
      evt.dataTransfer.setData("ID", item.pId);
    },
    onDrop(evt, cId) {
      const itemID = evt.dataTransfer.getData("ID");
      http
        .post("/category/update/post", {
          pId: itemID,
          pCategory: cId,
        })
        .then(({ data }) => {
          if (data.data == "success") {
            this.$router.go();
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
    },

    deleteCategory(cId) {
      var ok = confirm("안에있는 내용들 다 지울겁니까?");
      if (ok) {
        // 다 지우기
        http
          .delete("/category/delete/all", {
            params: {
              cid: cId,
            },
          })
          .then(({ data }) => {
            if (data.data == "success") {
              this.$router.go();
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

      } else {
        // 카테고리만 지우고 나머진 pCategory를 1로 변경하기
        http
          .delete("/category/delete/only", {
            params: {
              cid: cId,
            },
          })
          .then(({ data }) => {
            if (data.data == "success") {
              this.$router.go();
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
      }
    },

    openUpdateDialog(cId, cName) {
      this.updateCategoryDialog = true;
      this.cUpdateName = cName;
      this.toUpdate = cId;
    },

    updateCategory() {
      http
        .put("/category/update", {
          cId: this.toUpdate,
          cName: this.cUpdateName,
        })
        .then(({ data }) => {
          if (data.data == "success") {
            this.$router.go();
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
    },
    setNull(){
      this.selected2 = null;
      this.modelInCategory = null;
    }
    },
    computed: {
      currentUser(){
        return this.$store.state.auth.user;
      },
    },
    mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
    },
    watch: {
      length (val) {
        this.tab = val-1
        this.tab2 = val-1
      },
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
.v-tabs-slider-wrapper {
  visibility: hidden !important;
}
.v-tabs-slider {
  visibility: hidden !important;
}
.menubar {
  position: fixed;
  left: 5vw;
  top: 40vh;
  width: 45px;
  height: 180px;
  background-color: black;
  opacity: 0.3;
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