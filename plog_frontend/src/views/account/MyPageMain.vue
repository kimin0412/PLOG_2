<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">PROFILE</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
              <v-col cols="4" class="px-0">
                <img src="@/assets/profile/profile.jpg" alt="user-image" class="profile-image">
              </v-col>
              <v-col cols="8">
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
              <v-col col="12">
                <v-row class="d-flex justify-end mt-5">
                  <v-btn color="error" dark small @click="logOut">Log Out</v-btn>
                </v-row>                              
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">

              </v-col>
            </v-row>

            <v-row class="mt-10">
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">Bookmark</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
              <v-expansion-panels popout focusable>
              <v-expansion-panel>
                <div>
                  <v-expansion-panel-header>
                    My Bookmark
                  </v-expansion-panel-header>
                  <v-expansion-panel-content >
                    <v-sheet
                      class="mx-auto mysheet drop-zone"
                      
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
            </v-row>
            <v-row>
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
import http from '@/util/http-common.js'
export default {
    name: 'Mypage',
    data() {
      return {
        Notes : [],
        selected : {},
        bmToggle : 0,
        hashtags : [],
        panel : true,
        model: null,
      }
    },
    created() {
      // if (!this.$store.state.auth.status.loggedIn) {
      //   this.$router.push('/login');
      // }
       http.get('/post/list/bookmark',{
         params : {
           uid : this.$store.state.auth.user.id,
         }
       }).then(({data}) => {
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
     methods: {
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
                this.$router.push("clienterror")
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

.profile-image {
  width: 100%;
}
</style>