<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row :class="{beforesearched:!searched}">
              <v-col cols="12">
                <div class="text-center display-1 font-weight-light">Search Group</div>
              </v-col>
              <v-col cols="12" class="text-center grey--text mb-4">
                찾으려는 그룹의 이름을 검색해주세요 :)
              </v-col>
              <v-col cols="12" class="py-1 text-h6">
                <v-text-field
                  prepend-icon="mdi-magnify"
                  placeholder="group name"
                  single-line
                  solo
                  rounded
                  clearable
                  autofocus
                  v-model="searchgroup"
                  @keyup.enter="complete"
                  hint="그룹 이름으로 검색해주세요"
                >
                </v-text-field>
              </v-col>
            </v-row>
            <!-- 검색 결과 -->
            <v-col cols="12" class="text-center">
              {{searchgroup}}에 대한 검색결과입니다.
            </v-col>
            <v-row v-if="searched" class="text-center">              
              <v-col cols="12" class="text-center">
              <div v-for="(group, index) in groups" :key="index">
                <v-card-text class="d-flex justify-space-between py-0">
                  <div class="pt-2"><v-icon small class="mr-1">mdi-check</v-icon> <strong>{{group.clName}}</strong> |  {{group.clRegdate}}</div>
                  <v-btn @click="openJoinDialog(group.clId, group.clName)" text color="blue lighten-2  ml-auto">가입하기</v-btn>
                </v-card-text>
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
          <v-row>
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">SEARCH</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">
              <v-text-field
                prepend-icon="mdi-magnify"
                placeholder="group name"
                single-line
                solo
                rounded
                clearable
                autofocus
                v-model="searchgroup"
                @keyup.enter="complete2"
                hint="그룹 이름으로 검색해주세요"
              >
              </v-text-field>
            </v-col>
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
      }
    },
    watch: {
      searchgroup: function() {
        if (this.searchgroup === '') {
          this.searched = false
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
        });
      },
      complete2() {
        this.searched = true
        http.get('/club/list/search', {
          params : {
            searchword : this.searchgroup,
            uId : this.$store.state.auth.user.id,
          }
        })
        .then(({data}) => {
          this.groups = data;
        });
      },
      openJoinDialog(id, name) {
        this.openId = id;
        this.openName = name;
        this.joinDialog = true;
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
            alert("그룹에 가입 되었습니다.");
            this.joinDialog = false;
            this.$router.push("/group");
          } else{
            alert("비밀번호를 확인해 주세요.");
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
</style>