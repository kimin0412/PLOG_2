<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-row>
            <v-col cols="12" class="text-center">
              <img src="@/assets/us/id.png" alt="introduce developers" width="100">
            </v-col>
            <v-col cols="12" class="text-center py-0 mb-5 grey--text font-weight-light display-1">
              DEVELOPERS
            </v-col>
          </v-row>
          <v-row justify="center" class="px-10">
            <v-expansion-panels>
              <v-expansion-panel
                v-for="(mem,i) in members"
                :key="i"
              >
                <v-expansion-panel-header>
                  <span>{{mem.ename}}</span>  {{ mem.position }}</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <div class="pb-3 font-weight-bold">{{mem.name}}</div>
                  <div class="text-center grey--text display-1 font-weight-thin">{{mem.intro}}</div>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-row>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5"><img src="@/assets/us/id.png" alt="introduce developers" width="40" class="my-n4"></v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">DEVELOPERS</v-col>
          </v-row>
          <v-row class="mt-10" justify="center">
              <v-expansion-panels>
                <v-expansion-panel
                  v-for="mem in members"
                  :key="mem.index"
                >
                  <v-expansion-panel-header class="py-0">
                        <v-list-item three-line class="pl-0">
                          <v-list-item-content class="px-0 py-0">
                            <div class="overline mb-2 grey--text font-weight-light">{{mem.position}}</div>
                            <v-list-item-title class="text-h6 mb-1">{{mem.name}}</v-list-item-title>
                            <v-list-item-subtitle class="text-caption ml-1 mb-3">{{mem.ename}}</v-list-item-subtitle>
                          </v-list-item-content>
                        </v-list-item>
                  </v-expansion-panel-header>
                  <v-expansion-panel-content class="grey--text font-ewight-thin text-center my-5">
                    {{mem.intro}}
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </v-expansion-panels>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Developers',
    data() {
      return {
        members: [
          {
            name:'김민지',
            role: 'Leader',
            ename: 'M.J. Kim',
            position: 'FRONTEND & BACKEND',
            intro: '멤버 소개 문구',
          },
          {
            name:'김민섭',
            role: 'Member',
            ename: 'M.S. Kim', 
            position: 'FRONTEND & BACKEND',
            intro: '멤버 소개 문구',
          },
          {
            name:'이민재',
            role: 'Member',
            ename: 'M.J. Lee',
            position: 'FRONTEND & BACKEND',
            intro: '멤버 소개 문구',
          },
          {
            name:'신지식',
            role: 'Member',
            ename: 'J.S. Shin',
            position: 'BACKEND',
            intro: '멤버 소개 문구',
          },
          {
            name:'임혜민',
            role: 'Member',
            ename: 'H.M. Rheem',
            position: 'FRONTEND',
            intro: '멤버 소개 문구',
          },
        ]
      }
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
  margin-top: 15vh;
}

</style>