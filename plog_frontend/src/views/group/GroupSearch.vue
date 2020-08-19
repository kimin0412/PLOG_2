<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row :class="{beforesearched:!searched}">
              <v-col v-if="!searched" cols="12">
                <div class="text-center display-1 font-weight-light">Search Group</div>
              </v-col>
              <v-col  v-if="!searched" cols="12" class="text-center grey--text mb-4 text-subtitle-1">
                찾으려는 그룹의 이름을 검색해주세요 :)
              </v-col>
              <v-col cols="12" class="py-1 text-h6">
                <v-text-field
                  prepend-icon="mdi-magnify"
                  placeholder="그룹명을 입력해주세요"
                  single-line
                  solo
                  rounded
                  clearable
                  autofocus
                  v-model="searchgroup"
                  @keyup.enter="complete"
                >
                </v-text-field>
              </v-col>
            </v-row>
            <!-- 검색 결과 -->
            <v-col v-if="searched" cols="12" class="text-center">
              <div v-if="groups.length >= 1" class="text-center grey--text mt-n10 font-weight-thin text-subtitle-2">"{{searchgroup}}"에 대한 검색결과입니다.</div>
              <div v-else class="text-center grey--text pt-15 font-weight-thin text-subtitle-2">
                {{searchgroup}}에 대한 검색결과가 존재하지 않습니다.<br> 내가 속한 그룹의 정보는 검색되지 않습니다.
              </div>
            </v-col>
            <v-row v-if="searched" class="text-center mt-15 px-15">              
              <v-col cols="12" class="text-center">
              <div v-for="(group, index) in groups" :key="index">
                <v-card class="my-1">
                <v-card-text class="d-flex justify-space-between py-0">
                  <div class="pt-2"><v-icon small class="mr-3">mdi-check</v-icon><v-card class="d-inline-block mr-2" :color="group.clColor" style="width: 20px; height: 15px;"></v-card> <strong class="mr-2">{{group.clName}}</strong> |  <span class="ml-2">{{group.clRegdate}}</span></div>
                  <v-btn @click="openJoinDialog(group.clId, group.clName)" text color="blue lighten-2  ml-auto">가입하기</v-btn>
                </v-card-text>
                </v-card>
              </div>
              </v-col>
            </v-row>
            <v-row class=" mb-4">
              <v-menu
                v-model="joinDialog"
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
                        <v-text-field label="Group Name*" v-model="openName" readonly></v-text-field>
                      </v-col>
                       <v-col cols="12">
                        <v-text-field label="Group Password*" v-model="openPassword" required></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="joinGroup">가입하기</v-btn>
                  <v-btn color="blue darken-1" text @click="joinDialog = false">Close</v-btn>
                </v-card-actions>
              </v-card>
              </v-menu>
            </v-row>
          </v-container>
        </div>
      </div>


      <div class="d-block d-sm-none">
        <v-container>
          <v-row :class="{beforesearched2:!searched2}">
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">SEARCH</v-col>
          </v-row>
          <v-row class="pt-5">
            <v-col cols="11" class="py-1 text-h6">
              <v-text-field
                single-line
                solo
                rounded
                autofocus
                v-model="searchgroup2"
                @keyup.enter="complete2"
                placeholder="그룹명을 입력해주세요"
              >
              </v-text-field>
            </v-col>
            <v-col cols="1" class="px-0">
              <v-icon @click="complete2">mdi-magnify</v-icon>
            </v-col>
          </v-row>

            <v-col v-if="searched2"  cols="12" class="text-center mt-n10 grey--text text-caption">
              <div v-if="groups2.length >= 1" class="text-center grey--text text-caption">"{{searchgroup2}}"에 대한 검색결과입니다.</div>
              <div v-else class="text-center grey--text text-caption">검색결과가 존재하지 않습니다.<br>이미 가입한 그룹은 검색 결과에서 제외됩니다.</div>              
            </v-col>
            <v-row v-if="searched2" class="text-center">              
              <v-col cols="12" class="text-center pt-10">
              <div v-for="(group, index) in groups2" :key="index">
                <v-card-text class="d-flex justify-space-between py-0 px-0 ">
                  <div class="py-2 text-left"><v-icon small class="mr-1">mdi-check</v-icon><v-card class="d-inline-block mx-2" :color="group.clColor" style="width: 20px; height: 15px;"></v-card>
                  <strong>{{group.clName}}</strong></div>
                  <v-btn @click="openJoinDialog2(group.clId, group.clName)" text color="blue lighten-2" class="ml-auto px-0 text-button">join</v-btn>
                </v-card-text>
              </div>
              </v-col>
            </v-row>
            <v-row class=" mb-4">
              <v-menu
                v-model="joinDialog2"
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
                        <v-text-field label="Group Name*" v-model="openName2" readonly></v-text-field>
                      </v-col>
                       <v-col cols="12">
                        <v-text-field label="Group Password*" v-model="openPassword2" required></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="joinGroup2">가입하기</v-btn>
                  <v-btn color="blue darken-1" text @click="joinDialog2 = false">Close</v-btn>
                </v-card-actions>
              </v-card>
              </v-menu>
            </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'
export default {
    name: 'GroupSearch',
    data() {
      return {
        searchgroup: '',
        searched: false,
        groups : [],
        openId : '',
        openName : '',
        openPassword : '',
        joinDialog : false,

        searchgroup2: '',
        searched2: false,
        groups2:[],
        openId2: '',
        openName2: '',
        openPassword2: '',
        joinDialog2: false,
      }
    },
    watch: {
      searchgroup: function() {
        if (this.searchgroup === '') {
          this.searched = false
        }
      },
      searchgroup2: function() {
        if (this.searchgroup2 === '') {
          this.searched2 = false
        }
      }
    },
    methods: {
      complete() {
        this.searched = true
        http.get('/club/list/search', {
          params : {
            searchword : this.searchgroup,
            uId : this.$store.state.auth.user.id,
          }
        })
        .then(({data}) => {
          this.groups = data;
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
      complete2() {
        this.searched2 = true
        http.get('/club/list/search', {
          params : {
            searchword : this.searchgroup2,
            uId : this.$store.state.auth.user.id,
          }
        })
        .then(({data}) => {
          this.groups2 = data;
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
      openJoinDialog(id, name) {
        this.openId = id;
        this.openName = name;
        this.joinDialog = true;
      },
      openJoinDialog2(id, name) {
        this.openId2 = id;
        this.openName2 = name;
        this.joinDialog2 = true;
      },
      joinGroup() {
        http.get('/club/join', {
          params : {
            clId : this.openId,
            clPassword : this.openPassword,
            uId : this.$store.state.auth.user.id,
          }
        })
        .then(({data}) => {
          if(data === 'success'){
            this.$dialog.notify.success("그룹에 가입 되었습니다. 😃", {
              position: "bottom-right",
              timeout: 3000,
            });
            this.joinDialog = false;
            this.$router.push("/group");
          } else{
            this.$dialog.notify.error("비밀번호를 확인해 주세요. 😥", {
              position: "bottom-right",
              timeout: 3000,
            });
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
      joinGroup2() {
        http.get('/club/join', {
          params : {
            clId : this.openId2,
            clPassword : this.openPassword2,
            uId : this.$store.state.auth.user.id,
          }
        })
        .then(({data}) => {
          if(data === 'success'){
            this.$dialog.notify.success("그룹에 가입 되었습니다. 😃", {
              position: "bottom-right",
              timeout: 3000,
            });
            this.joinDialog2 = false;
            this.$router.push("/group");
          } else{
            this.$dialog.notify.error("비밀번호를 확인해 주세요. 😥", {
              position: "bottom-right",
              timeout: 3000,
            });
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
</script>

<style scoped>
.content-center {
  width: 85%;
}
.beforesearched {
  margin-top: 23vh;
}
.beforesearched2 {
  margin-top: 25vh;
  text-align: center;
}
</style>