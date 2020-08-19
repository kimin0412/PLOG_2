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
                  <v-tab>Posts</v-tab>
                  <v-tab>Team info.</v-tab>
                  <v-tab>Members</v-tab>
                  <v-tab-item>
                    <div>
                      <div class="d-none d-sm-block">
                        <div class="content-center mx-auto">
                          <v-row class="mb-2 justify-end">
                            <router-link :to="{ path: '/group/noteCreate', query:{groupId:groupId}}" class="smallicon mr-3 text-decoration-none">
                              <v-btn small color="light-green" dark class="ml-auto d-none d-sm-block "><v-icon class="mr-3" small>mdi-pencil</v-icon>Write</v-btn>          
                            </router-link>
                            <router-link to="/search" class="smallicon mr-10 text-decoration-none">
                              <v-btn small color="grey" dark class="ml-auto d-none d-sm-block"><v-icon class="mr-3" small>mdi-magnify</v-icon>Search</v-btn>          
                            </router-link>
                            <v-btn small color="blue" dark class="ml-auto d-none d-sm-block text-decoration-none" @click="categoryDialog = true"><v-icon class="mr-3" small>mdi-message-text</v-icon>new folder</v-btn>
                            
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
                          </v-row>
                          <v-row class="mt-10">
                            <v-col cols="12" class="py-1 px-0">
                              <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                              <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">My notes</div>
                            </v-col>
                          </v-row>
                          
                          <!-- 폴더에 들어가지 않은 노트들 -->
                          <v-row class=" mb-4">
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
                                      :color="active ? 'grey lighten-1' : 'grey lighten-2'"
                                      class="ma-1"
                                      width="200"
                                      height="120"
                                      @click="toggle"
                                      draggable
                                      @dragstart='startDrag($event, note)'
                                    >
                                      <v-list-item three-line>
                                        <v-list-item-content>
                                          <v-list-item-title class="headline mb-1"> {{ note.pTitle }}</v-list-item-title>
                                          <v-list-item-subtitle>created at</v-list-item-subtitle>
                                          <v-list-item-subtitle>{{ note.pDate | removeTime }}</v-list-item-subtitle>
                                        </v-list-item-content>

                                        <v-list-item-avatar
                                          tile
                                          size="30"
                                          v-bind:color=note.pColor
                                        ></v-list-item-avatar>
                                      </v-list-item>
                                      <v-row align="center" justify="center">
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

                                    <!-- <v-card
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
                                    </v-card> -->
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
                                      <router-link :to="{ path: '/group/noteDetail', query:{pId:selected.pId, clId:this.groupId}}" class="py-0 text-center text-h6"> 
                                        <v-col cols="12" class="py-0 text-center text-h6">{{ selected.pTitle }}</v-col>
                                      </router-link>
                                      </v-col>
                                      <v-col cols="12" class="py-0 text-center text-subtitle-2">created at {{ selected.pDate | removeTime }}</v-col>
                                      <v-col cols="12" class="py-0 text-center text-subtitle-2">writed at <strong>{{ selectedName }}</strong></v-col>
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
                                        <router-link :to="{ path: '/group/noteDetail', query:{pId:selected2.pId, clId:this.groupId}}" class="py-0 text-center text-h6"> 
                                          <v-col cols="12" class="py-0 text-center text-h6">{{ selected2.pTitle }}</v-col>
                                        </router-link>
                                        </v-col>
                                        <v-col cols="12" class="py-0 text-center text-subtitle-2">created at {{ selected2.pDate }}</v-col>
                                        <v-col cols="12" class="py-0 text-center text-subtitle-2">writed at <strong>{{ selectedName2 }}</strong></v-col>
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
                         
                        </div>
                      </div>
                      <div class="d-block d-sm-none">
                        <v-container>
                          <v-row>
                            <v-col cols="12" class="py-1 text-h5">MY</v-col>
                            <v-col cols="12" class="py-1 text-h4 font-weight-bold">POSTS</v-col>
                          </v-row>
                          <v-row class="mb-2 justify-end mt-10">
                            <router-link to="/group/noteCreate" class="smallicon mr-3">
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
                                      <router-link :to="{ path: '/group/noteDetail', query:{pId:selected.pId, clId:this.groupId}}" class="py-0 text-center text-h6"> 
                                        <v-col cols="12" class="py-0 text-center text-h6">{{ selected.pTitle }}</v-col>
                                      </router-link>
                                      </v-col>
                                      <v-col cols="12" class="py-0 text-center text-subtitle-2">created at {{ selected.pDate }}</v-col>
                                      <v-col cols="12" class="py-0 text-center text-subtitle-2">writed at <strong>{{ selectedName }}</strong></v-col>
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
                        </v-container>
                      </div>
                    </div>
                  </v-tab-item>
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
                                  <td>{{ host.email }}</td>
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
                        <div class="d-flex justify-end py-0 atag">
                          <div v-if="host.id == this.$store.state.auth.user.id">
                            <v-btn small color="orange" class="py-0 white--text text-center atag mr-3" @click="clubDialog = true">그룹 수정</v-btn>
                            <v-dialog
                              v-model="clubDialog"
                              :close-on-content-click="false"
                              offset-x
                              persistent
                              max-width="400px"
                            >
                              <v-card>
                                <v-card-title>
                                  <span class="headline">Update Group</span>
                                </v-card-title>
                                <v-card-text>
                                  <v-container>
                                    <v-row>
                                      <v-col cols="12">
                                        <v-text-field
                                          label="Group Name*"
                                          v-model="groupName"
                                          required
                                        ></v-text-field>
                                      </v-col>
                                      <v-col cols="12" class="py-0 px-0">
                                        <v-text-field
                                        placeholder="입장 코드를 입력해주세요"
                                        filled
                                        rounded
                                        dense
                                        clearable
                                        v-model="entercode"
                                        ></v-text-field> 
                                    </v-col>      
                                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">Introduction</v-col>
                                    <v-col cols="12" class="py-0 px-0">
                                        <v-textarea
                                        filled
                                        auto-grow
                                        rows="4"
                                        row-height="30"
                                        v-model="groupIntro"
                                        :rules="[rules.counter]"
                                        rounded
                                        maxlength="150"
                                        ></v-textarea>
                                    </v-col>
                                    </v-row>
                                    
                                    <v-row class="my-2">
                                      <v-col cols="1" class="px-0 pb-0 mx-0 my-0">
                                          <v-card :color="groupColor" class="py-2 transparent--text">색</v-card>
                                      </v-col>
                                      <v-col cols="11">
                                          <v-select v-model="groupColor"
                                                      :items="colors"
                                                      filled
                                                      dense
                                                      label=""
                                                      full-width>
                                          </v-select>
                                      </v-col>
                                  </v-row>
                                  <v-card class="mx-auto">
                                    <div v-for="(member,index) in Members" :key="index">
                                      <div v-if="member.id != host.id">
                                      <v-card-text class="d-flex justify-center py-0">
                                        <div>
                                          {{ member.email }}
                                            <v-btn
                                              text
                                              color="blue lighten-2  ml-auto"
                                              @click="deleteMember(member.id, member.email)"
                                              >회원 삭제하기</v-btn
                                            >
                                        </div>
                                      </v-card-text>
                                    </div></div>
                                  </v-card>
                                  </v-container>
                                </v-card-text>
                                <v-card-actions>
                                  <v-spacer></v-spacer>
                                  <v-btn color="blue darken-1" text @click="updateClub"
                                    >수정하기</v-btn
                                  >
                                  <v-btn
                                    color="blue darken-1"
                                    text
                                    @click="clubDialog = false"
                                    >Close</v-btn
                                  >
                                </v-card-actions>
                              </v-card>
                            </v-dialog>
                            
                            <v-btn small color="orange" class="py-0 white--text text-center atag mr-3" @click="deleteGroup">그룹 삭제</v-btn>
                          </div>
                          <div v-else>
                            <v-btn small color="orange" class="py-0 white--text text-center atag mr-3" @click="withDraw">탈퇴하기</v-btn>
                          </div>
                        </div>
                        <v-col cols="2"></v-col>          
                      </v-row>
                    </v-container>
                  </v-tab-item>
                  <v-tab-item>
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
                                  <td>{{ host.email }}</td>
                                </tr>
                              </tbody>
                            </template>
                          </v-simple-table>                          
                        </v-col>  
                        <v-col cols="3"></v-col>
                        <v-col cols="3" class="text-center mt-5">
                          <v-simple-table dense class="text-left">
                            <template v-slot:default>
                              <th>Group Members</th>
                              <tbody>
                                <tr><td></td></tr>
                                <tr v-for="(member,index) in Members" :key="index"><td>{{member.email}}</td></tr>
                              </tbody>
                            </template>
                          </v-simple-table>
                        </v-col>  
                      </v-row>
                    </v-container>
                  </v-tab-item>
                  
                </v-tabs>
              </v-card>
            </v-col>
          </v-row>
          
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
        host : {},
        regdate : '',
        Members : [],
        categoryDialog : false,
        updateCategoryDialog : false,
        categories : [],
        Notes : [],

        selected: {},
        selected2 : {},
        hashtags : [],
        hashtags2 : [],
        bmToggle : 0,
        bmToggle2 : 0,
        model: null,
        modelInCategory : null,
        cName : '',
        cUpdateName : '',
        selectedName : '',
        selectedName2 : '',

        clubDialog : false,
        colors: ['red', 'pink', 'purple', 'indigo', 'light-blue', 'green', 'lime', 'yellow', 'orange', 'brown', 'grey'],
        entercode : '',
        rules: {
          counter: value => value.length <= 150 || '150자 이내',
        },
        groupName : '',
        groupIntro : '',
        groupColor : '',

        pageIndex: 0,
      }
    },

    created() {
      if(this.$route.query.q == null){
        // console.log("empty");
        this.pageIndex = 0;
      }
      else{
        this.pageIndex = this.$route.query.q;
      }

      http.get('/club', {
          params : {
            clId : this.groupId,
          }
        }).then(({ data }) => {
          this.myClub = data
          this.regdate = this.myClub.clRegdate.substr(0, 10)
          this.groupName = data.clName
          this.groupIntro = data.clInfo
          this.groupColor = data.clColor
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

        http.get('/club/host', {
          params : {
            clId : this.groupId,
          }
        }).then(({ data }) => {
          this.host = data
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

         http.get('/club/members', {
          params : {
            clId : this.groupId,
          }
        }).then(({ data }) => {
          this.Members = data
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

      window.scrollTo(0, 0);
      http.get('/category/club/listAll', {
        params : {
          cClub : this.groupId,
        }
      })
      .then(({data}) => {
        this.categories = data;
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

      http.get('post/list/all/club', {
        params : {
          clid : this.groupId,
        }
      })
      .then(({data}) => {
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
    },

    filters: {
      removeTime(val) {
        return val.substr(0, 10)
      },
    },

    methods: {
      createCategory() {
        if(this.cName == ""){
          this.$dialog.notify.warning("카테고리 이름을 쓰세요 😤", {
            position: "bottom-right",
            timeout: 3000,
          });
        } else{
          http.post('/category/insert', {
            cName : this.cName,
            cUser : 1,
            cClub : this.groupId,
          })
          .then(({data}) => {
            if(data.data == 'success'){
              this.$dialog.notify.success("새 폴더가 생성되었습니다 😚", {
                position: "bottom-right",
                timeout: 3000,
              });
              this.categoryDialog = false;
              this.$router.push({path:"/group/detail", query: { q: ++this.pageIndex }});
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
      updateCategory () {
        http.put('/category/update', {
          cId : this.toUpdate,
          cName : this.cUpdateName
        })
        .then(({data}) => {
          if(data.data == 'success'){
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

      getNote(note) {
            this.selected = note
            this.hashtags = []
            this.bmToggle = note.pBookmark
            this.hashtags = []
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

          http.get('/post/user', {
            params : {
              pid : this.selected.pId,
            }
          })
          .then(({data}) => {
            this.selectedName = data
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
        getNoteInCategory(note){
          this.selected2 = note
          this.hashtags2 = []
          this.bmToggle2 = note.pBookmark
          http.get('/hashtag/select', {
            params : {
              uid : this.$store.state.auth.user.id,
              pid : this.selected2.pId,
            }
          })
          .then(({data}) => {
            data.forEach(element => {
              this.hashtags2.push({"name" : element})
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

          http.get('/post/user', {
            params : {
              pid : this.selected2.pId,
            }
          })
          .then(({data}) => {
            this.selectedName2 = data
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
        openUpdateDialog( cId, cName ) {
          this.updateCategoryDialog = true
          this.cUpdateName = cName
          this.toUpdate = cId
        },

        updateClub() {
          if (this.groupName.trim() === ''){
              this.$dialog.notify.warning("그룹명은 필수입니다. 😤", {
                position: "bottom-right",
                timeout: 3000,
              });
            } else {
                if (this.entercode.trim() === ''){
                  this.$dialog.notify.warning("입장 확인용 비밀번호를 설정해주세요! 😥", {
                    position: "bottom-right",
                    timeout: 3000,
                  });
                } else {
                    if (this.groupIntro.trim() === ''){
                      this.$dialog.notify.warning("짧은 소개 부탁드립니다! 😚", {
                        position: "bottom-right",
                        timeout: 3000,
                      });
                    } else {
                        http.post('/club/update', {
                          clId : this.$store.state.auth.user.id,
                          clName : this.groupName,
                          clInfo : this.groupIntro,
                          clColor : this.groupColor,
                          clPassword : this.entercode
                        })
                        .then(({ data }) => {
                          let msg = '수정 처리시 문제가 발생했습니다.';
                          if (data.data == 'success') {
                            msg = '수정이 완료되었습니다.';
                            this.$dialog.notify.success(msg + " 😚", {
                              position: "bottom-right",
                              timeout: 3000,
                            });
                            this.$router.push("/group");
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
                }
            }
        },

        deleteMember: async function (id, email) {
          const res = await this.$dialog.warning({
            text: email+"님이 작성한 노트와 일정은 "+this.host.email+"님의 작성으로 수정됩니다./n괜찮으신가요?",
            title: 'Warning'
          })
          if(res){
            http.delete('club/delete/member', {
              params : {
                uId : id,
                hostId : this.host.id,
                groupId : this.groupId
              }
            }).then(({ data }) => {
              let msg = '삭제 처리시 문제가 발생했습니다.';
                if (data.data == 'success') {
                  msg = '회원 삭제 완료되었습니다.';
                  this.$dialog.notify.success(msg + " 😚", {
                    position: "bottom-right",
                    timeout: 3000,
                  });
                  this.$router.push("/group");
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
            this.$router.push("/group");
          }
        },

        deleteGroup: async function (){
          const res = await this.$dialog.warning({
            text: "그룹을 삭제 하시겠습니까?",
            title: 'Delete Group'
          });
          if(res){
          http.delete('club/delete', {
            params : {
              groupId : this.groupId
            }
          }).then(({ data }) => {
            let msg = '삭제 처리시 문제가 발생했습니다.';
              if (data.data == 'success') {
                msg = '삭제 완료되었습니다.';
                this.$dialog.notify.success(msg + " 😚", {
                  position: "bottom-right",
                  timeout: 3000,
                });
                this.$router.push("/group");
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
          this.$router.push("/group");
          }
        },

        withDraw: async function () {
          const res = await this.$dialog.warning({
            text: "그룹에서 탈퇴 하시겠습니까?",
            title: 'Withdraw Group'
          });
          if(res){
            http.delete('club/delete/member', {
              params : {
                groupId : this.groupId,
                uId : this.$store.state.auth.user.id,
                hostId : this.host.id,
              }
            }).then(({ data }) => {
              let msg = '탈퇴 처리시 문제가 발생했습니다.';
                if (data.data == 'success') {
                  msg = '탈퇴 완료되었습니다.';
                  this.$dialog.notify.success(msg + " 😚", {
                    position: "bottom-right",
                    timeout: 3000,
                  });
                  this.$router.push("/group");
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
            this.$router.push("/group");
          }
        }
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}

</style>