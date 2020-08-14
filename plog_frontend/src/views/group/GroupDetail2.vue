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
                                <img :src="teamimg" alt="" width="100">
                            </div>
                        </v-row>
                        <v-card-subtitle class="pt-5 pb-0 text-center text-h5">{{ myClub.clName }}</v-card-subtitle>
                        <v-card-text class="grey--text text-center text-caption pb-0">
                            Host : <strong>{{host.username}}</strong> 님
                        </v-card-text>
                        <v-card-text class="grey--text text-center">
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="7" class="text-left py-1 pl-5">Post</v-col>
                                <v-col v-if="Notes.length > 0" cols="5" class="text-right py-1 pr-5">num</v-col>
                                <v-col v-else cols="5" class="text-right py-1 pr-5">-</v-col>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="7" class="text-left py-1 pl-5">Color</v-col>
                                <v-col cols="5" class="text-right py-1 pl-5 pr-5 font-weight-light">
                                    <v-card :color="myClub.clColor" width="30" height="20" class="ml-auto transparent--text">c</v-card>
                                </v-col>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="8" class="text-left py-1 pl-5">Members</v-col>
                                <v-col cols="4" class="text-right py-1 pl-5 pr-5 font-weight-light">{{Members.length}}</v-col>
                            </v-row>
                            <v-divider></v-divider>
                        </v-card-text>
                    </v-card>
                    <v-card class="mx-auto mt-5">
                        <div>
                            <v-expansion-panels accordion>
                            <v-expansion-panel>
                                <v-expansion-panel-header>Group-log</v-expansion-panel-header>
                                <v-expansion-panel-content>

                                </v-expansion-panel-content>
                            </v-expansion-panel>
                            </v-expansion-panels>
                        </div>
                    </v-card>
                    <v-card class="mx-auto mt-2">
                        <v-expansion-panels accordion>
                        <v-expansion-panel>
                            <v-expansion-panel-header>My Logs</v-expansion-panel-header>
                            <v-expansion-panel-content class="px-0">
                                <v-row class="text-center">
                                    <v-col cols="12" class="text-caption grey--text py-0">내가 쓴 노트</v-col>
                                    <v-col cols="12" class="py-1"><v-divider></v-divider></v-col>
                                    <v-col cols="12" class="text-caption font-weight-bold black--text py-0">50 / 100</v-col>
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
                            <v-tab>Group Post</v-tab>
                            <v-tab>Members</v-tab>
                            <v-tab>Group Statistics</v-tab>
                            <v-tab-item>
                                <v-container>
                                <v-row>
                                    <v-col cols="12" class="mt-5 text-center">
                                      <router-link :to="{ path: '/group/noteCreate', query:{groupId:groupId}}" class="smallicon text-decoration-none text-rignt">
                                        <v-btn small color="transparent" elevation="0" class="ml-auto indigo--text"><v-icon class="mr-2 indigo--text" small>mdi-pencil-plus</v-icon>New note</v-btn>          
                                      </router-link>
                                        <v-btn small color="transparent" elevation="0" class="ml-auto grey--text text-decoration-none" @click="categoryDialog = true"><v-icon class="grey--text mr-2" small>mdi-folder-multiple-plus</v-icon>New folder</v-btn>
                                            <v-menu
                                            v-model="categoryDialog"
                                            :close-on-content-click="false"
                                            offset-x>
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
                                    </v-col>
                                    <v-col cols="12">
                                      <v-row class=" mb-4">
                                          <v-sheet
                                              class="mx-auto mysheet"
                                              @drop='onDrop($event, 1)' 
                                              @dragover.prevent
                                              @dragenter.prevent
                                              max-width="100%"
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
                                          

                                            <v-expansion-panels class="mx-5" focusable>
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
                                                          <v-icon color="red" small class="mr-2" @click="deleteCategory( category.cId )">mdi-delete</v-icon>
                                                          <v-icon dark color="grey" small class="mr-2" @click="openUpdateDialog( category.cId, category.cName )" >mdi-wrench</v-icon>
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
                                    </v-col>
                                </v-row>
                                </v-container>
                            </v-tab-item>
                            <v-tab-item>
                                <v-container>
                                    <v-row class="pt-5">
                                      <v-col cols="12">
                                        <v-virtual-scroll
                                          :items="Members"
                                          :item-height="60"
                                          max-height="60vh"
                                        >
                                          <template v-slot="{ item }">
                                            <v-list-item>

                                              <v-list-item-content>
                                                <v-col cols="12" >
                                                  <v-divider class="mx-5"></v-divider>
                                                    <v-row class="py-0">
                                                        <v-col cols="1"></v-col>
                                                        <v-col cols="3" class="text-center">
                                                            <div class="text-center"><img  class="text-center" :src="require(`@/assets/users/u`+item.id%13+'.png')" alt="" width="40"></div>
                                                        </v-col>
                                                        <v-col cols="7" class="text-center text-caption">
                                                            <div>이름: <strong class="text-caption font-weight-bold">{{item.username}}</strong></div>
                                                            <div class=" text-caption">{{item.email}}</div>
                                                        </v-col>
                                                        <v-col cols="1"></v-col>
                                                        <v-divider></v-divider>
                                                    </v-row>
                                                </v-col>
                                              </v-list-item-content>
                                            </v-list-item>
                                          </template>
                                        </v-virtual-scroll>                                        
                                      </v-col>
                                    </v-row>
                                    <!-- <v-row>
                                        <v-col cols="12">
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
                                    </v-row> -->
                                </v-container>
                            </v-tab-item>
                            <v-tab-item>

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
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">Team. {{groupName}}</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="4" class="text-right">
                <img :src="teamimg" alt="user-image" class="profile-image" width="50">
            </v-col>
            <v-col cols="8" class="text-center">
              <div><strong>환영합니다 :)</strong>, <br> <span class="text-caption grey--text">{{groupName}} 팀을 위한 공간입니다</span></div>
            </v-col>
            <v-col cols="12" class="py-0 mt-5">
              <v-divider></v-divider>
              <v-row class="py-0">
                <v-col cols="4" class="text-caption grey--text text-center py-1">POST</v-col>
                <v-col cols="4" class="text-caption grey--text text-center py-1">COLOR</v-col>
                <v-col cols="4" class="text-caption grey--text text-center py-1">MEMBERS</v-col>
              </v-row>
              <v-divider></v-divider>
              <v-row class="py-0">
                <v-col cols="4" class="text-caption text-center py-2">{{Notes.length}}</v-col>
                <v-col cols="4" class="text-caption text-center py-2"><v-card :color="myClub.clColor" width="30" height="15" class="my-1 mx-auto transparent--text d-inline-block">c</v-card></v-col>
                <v-col cols="4" class="text-caption text-center py-2">{{Members.length}}</v-col>
              </v-row>
              <v-divider></v-divider>
            </v-col>
            
            <v-col cols="12" class="text-right py-0">
              <v-btn @click="sheet = !sheet" class="grey--text text-caption" color="transparent" elevation="0" small><v-icon small class="mr-1">mdi-plus</v-icon> More...</v-btn>    
              <v-btn @click="clubDialog = true" class="grey--text text-caption" color="transparent" elevation="0" small><v-icon small class="mr-1">mdi-pen</v-icon> Edit</v-btn>    
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
                    <v-btn color="orange" text @click="updateClub"
                      >DONE</v-btn
                    >
                    <v-btn
                      color="grey"
                      text
                      @click="clubDialog = false"
                      >Close</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </v-dialog>    
              <div class="text-center">
                  <v-bottom-sheet v-model="sheet">
                    <v-sheet class="text-center" height="80vh">
                      <v-btn
                        class="mt-6"
                        text
                        small
                        color="red"
                        @click="sheet = !sheet"
                      >close  X</v-btn>
                      <v-row class="mt-5">
                        <v-col cols="12" class="py-0 font-weight-bold text-caption">
                          Group Introduction
                        </v-col>
                        <v-col cols="12" class="py-0 text-caption grey--text">
                          {{ groupIntro }}
                        </v-col>
                        <v-col cols="12" class="pt-10 pb-0 font-weight-bold text-caption">
                          Group Members
                        </v-col>
                        
                        <v-col cols="12">
                          <v-virtual-scroll
                                :items="Members"
                                :item-height="60"
                                max-height="300"
                              >
                                <template v-slot="{ item }">
                                  <v-list-item>

                                    <v-list-item-content>
                                      <v-col cols="12" >
                                        <v-divider class="mx-5"></v-divider>
                                          <v-row class="py-0">
                                              <v-col cols="1"></v-col>
                                              <v-col cols="3" class="text-center">
                                                  <div class="text-center"><img  class="text-center" :src="require(`@/assets/users/u`+item.id%13+'.png')" alt="" width="40"></div>
                                              </v-col>
                                              <v-col cols="7" class="text-center text-caption">
                                                  <div>이름: <strong class="text-caption font-weight-bold">{{item.username}}</strong></div>
                                                  <div class=" text-caption">{{item.email}}</div>
                                              </v-col>
                                              <v-col cols="1"></v-col>
                                              <v-divider></v-divider>
                                          </v-row>
                                      </v-col>
                                    </v-list-item-content>
                                  </v-list-item>
                                </template>
                              </v-virtual-scroll>
                        </v-col>
                        <v-col cols="12" class="mt-5 py-0 font-weight-bold text-caption">
                          Group Settings
                        </v-col>
                        <v-col  cols="12" class="py-0 text-caption grey--text">
                          <v-btn v-if="currentUser.id == host.id"  small color="orange" class="py-0 white--text text-center atag mr-3" @click="deleteGroup">그룹 삭제</v-btn>
                        </v-col>
                        <v-col  cols="12" class="py-0 text-caption grey--text">
                          <v-btn small color="red" text class="py-0 white--text text-center atag" @click="withDraw">탈퇴하기</v-btn>
                        </v-col>
                      </v-row>
                    </v-sheet>
                  </v-bottom-sheet>
                </div>     
            </v-col>
          </v-row>

          <v-row class="mt-10">
              <v-col cols="12" class="text-center text-subtitle-2 font-weight-bold">My P-logs</v-col>
              <v-col cols="12" class="text-center grey--text text-caption">
                나의 Plog 이용 통계와 자료는 <br> pc환경에서 확인할 수 있습니다.
              </v-col>
              <v-col cols="12">
                
              </v-col>
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
        sheet: false,
        teamimg:'',
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
      }
    },

    created() {
      
      this.teamimg = require('@/assets/gusers/f'+this.groupId%10+'.png')

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
            this.$router.push("clienterror")
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
            this.$router.push("clienterror")
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
            this.$router.push("clienterror")
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
            this.$router.push("clienterror")
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
            this.$router.push("clienterror")
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
          alert("이름 쓰세요.")
        } else{
          http.post('/category/insert', {
            cName : this.cName,
            cUser : 1,
            cClub : this.groupId,
          })
          .then(({data}) => {
            if(data.data == 'success'){
              alert("새 폴더가 생성되었습니다.")
              this.categoryDialog = false;
              this.$router.go();
            }
          })
          .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("clienterror")
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
            this.$router.push("clienterror")
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
            this.$router.push("clienterror")
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
                this.$router.push("clienterror")
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
              this.$router.push("clienterror")
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
              this.$router.push("clienterror")
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
              this.$router.push("clienterror")
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
                alert("그룹명은 필수입니다.")
            } else {
                if (this.entercode.trim() === ''){
                    alert("입장 확인용 비밀번호를 설정해주세요")
                } else {
                    if (this.groupIntro.trim() === ''){
                        alert("짧은 소개 부탁드립니다 :)")
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
                            alert(msg)
                            this.$router.push("/group");
                          }
                        })
                        .catch((error) => {
                          if(error.response) {
                            this.$router.push("servererror")
                          } else if(error.request) {
                            this.$router.push("clienterror")
                          } else{
                            this.$router.push("/404");
                          }                          
                        });                        
                    }
                }
            }
        },

        deleteMember(id, email) {
          alert(email+"님이 작성한 노트와 일정은 "+this.host.email+"님의 작성으로 수정됩니다.")
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
                alert(msg)
                this.$router.push("/group");
              }
            })
            .catch((error) => {
              if(error.response) {
                this.$router.push("servererror")
              } else if(error.request) {
                this.$router.push("clienterror")
              } else{
                this.$router.push("/404");
              }                          
            });
          this.$router.push("/group");
        },

        deleteGroup(){
          http.delete('club/delete', {
            params : {
              groupId : this.groupId
            }
          }).then(({ data }) => {
            let msg = '삭제 처리시 문제가 발생했습니다.';
              if (data.data == 'success') {
                msg = '삭제 완료되었습니다.';
                alert(msg)
                this.$router.push("/group");
              }
            })
            .catch((error) => {
              if(error.response) {
                this.$router.push("servererror")
              } else if(error.request) {
                this.$router.push("clienterror")
              } else{
                this.$router.push("/404");
              }                          
            });
          this.$router.push("/group");
        },

        withDraw() {
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
                alert(msg)
                this.$router.push("/group");
              }
            })
            .catch((error) => {
              if(error.response) {
                this.$router.push("servererror")
              } else if(error.request) {
                this.$router.push("clienterror")
              } else{
                this.$router.push("/404");
              }                          
            });
        }
    },
    computed: {
      currentUser(){
        return this.$store.state.auth.user;
      },
      animalimg(e,mid) {
          return require('@/assets/users/u'+mid%12+'.png')
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