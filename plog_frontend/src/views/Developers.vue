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
              <v-expansion-panel>
                <v-expansion-panel-header>
                  <span>M.J. Kim</span>  Frontend & Backend</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-row>
                    <v-col cols="4"> 
                      <div><img src="@/assets/mem/mj2.png" alt="" width="100%"></div>
                    </v-col>
                    <v-col cols="8">
                      <div class="font-weight-bold">Name: 김민지</div>
                      <div class="pb-3 font-weight-bold">Role : Leader</div>
                      <div class="grey--text display-1 font-weight-thin">"배포 너무 재밌다"</div>
                      <div class="mt-10 grey--text">#배포의여신<br> #불좀꺼줄래?<br> #울팀UCC감독님 <br>#야진짜대박이지않냐?<br> #울팀막내♥</div>
                    </v-col>
                  </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  <span>M.S. Kim</span>  Frontend & Backend</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-row>
                    <v-col cols="4"> 
                      <div><img src="@/assets/mem/ms.png" alt="" width="100%"></div>
                    </v-col>
                    <v-col cols="8">
                      <div class="font-weight-bold">Name: 김민섭</div>
                      <div class="pb-3 font-weight-bold">Role : Member</div>
                      <div class="grey--text display-1 font-weight-thin">"행복하시고~ 건강하시고~"</div>
                      <div class="mt-10 grey--text">#분위기메이커<br> #풀스택(든든^^) <br>#넌좋은개발자가될거야... <br>#제가고칠게요(고마워 민섭아)<br> #사랑과전쟁</div>
                    </v-col>
                  </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  <span>M.J. Lee</span>  Frontend & Backend</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-row>
                    <v-col cols="4"> 
                      <div><img src="@/assets/mem/mj.png" alt="" width="100%"></div>
                    </v-col>
                    <v-col cols="8">
                      <div class="font-weight-bold">Name: 이민재</div>
                      <div class="pb-3 font-weight-bold">Role : Member</div>
                      <div class="grey--text display-1 font-weight-thin">"빠쓰 좋다~"</div>
                      <div class="mt-10 grey--text">#어려운건민재한테 <br>#고양이 <br>#좋습니다<br> #정신나갈거같애<br> #오늘의 기분: 그냥 그래요</div>
                    </v-col>
                  </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  <span>J.S. Shin</span>  Backend</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-row>
                    <v-col cols="4"> 
                      <div><img src="@/assets/mem/js.png" alt="" width="100%"></div>
                    </v-col>
                    <v-col cols="8">
                      <div class="font-weight-bold">Name: 신지식</div>
                      <div class="pb-3 font-weight-bold">Role : Member</div>
                      <div class="grey--text display-1 font-weight-thin">"자라나라 수염수염"</div>
                      <div class="mt-10 grey--text">#CORS^^<br> #jwt♡ <br>#으른으른 <br>#저는막차가 일찍끊겨서... <br>#졸려요~</div>
                    </v-col>
                  </v-row>
                </v-expansion-panel-content>
              </v-expansion-panel>
              <v-expansion-panel>
                <v-expansion-panel-header>
                  <span>H.M. Rheem</span>  Frontend</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <v-row>
                    <v-col cols="4"> 
                      <div><img src="@/assets/mem/hm.png" alt="" width="100%"></div>
                    </v-col>
                    <v-col cols="8">
                      <div class="font-weight-bold">Name: 임혜민</div>
                      <div class="pb-3 font-weight-bold">Role : Member</div>
                      <div class="grey--text display-1 font-weight-thin">"달리자~ (찡긋)"</div>
                      <div class="mt-10 grey--text">#소문의 프론트장인 나야나<br> #어깨빵^^<br> #야..우냐?<br> #머지했어요~(1:30 a.m.)<br> #간식셔틀(뺏는거아님)</div>
                    </v-col>
                  </v-row>
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
            intro: '배포 너무 재밌다',
          },
          {
            name:'김민섭',
            role: 'Member',
            ename: 'M.S. Kim', 
            position: 'FRONTEND & BACKEND',
            intro: '행복하시고~ 건강하시고~',
          },
          {
            name:'이민재',
            role: 'Member',
            ename: 'M.J. Lee',
            position: 'FRONTEND & BACKEND',
            intro: '빠쓰 좋다~',
          },
          {
            name:'신지식',
            role: 'Member',
            ename: 'J.S. Shin',
            position: 'BACKEND',
            intro: '자라나라 수염수염',
          },
          {
            name:'임혜민',
            role: 'Member',
            ename: 'H.M. Rheem',
            position: 'FRONTEND',
            intro: '달리자(찡긋)',
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