<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-parallax
            dark
            src="@/assets/us/us2.jpg"
            height="300"
          >
            <v-row
              align="center"
              justify="center"
            >
              <v-col class="text-center" cols="12">
                <h1 class="display-1 black--text font-weight-thin mb-4">P L O G</h1>
                <h4 class="black--text font-weight-thin">P L A N N E R  &nbsp;&nbsp; +  &nbsp;&nbsp; B L O G</h4>
              </v-col>
            </v-row>
          </v-parallax>
          <v-row class="mt-5">
            <v-col cols="4">
              <img src="@/assets/us/calendar.jpg" alt="user-image" class="about-intro">
            </v-col>
            <v-col cols="8" class="text-right mb-2" align-self="end">
              <div class="display-2 font-weight-thin">SCHEDULE</div>
              <div class="text-subtitle-2 grey--text">대학생, 취준생, 구직자 모두에게 필수적인 일정 관리</div>
              <div class="text-subtitle-2 grey--text">자소서 일정, 글을 기록한 날짜 등 주요 일정을 한 눈에 :)</div>
            </v-col>    
          </v-row>
          <v-row class="mt-5">
            <v-col cols="8" class="text-left mb-2" align-self="end">
              <div class="display-2 font-weight-thin">DAILY LOG</div>
              <div class="text-subtitle-2 grey--text">마크다운 형식을 지원하는 에디터로</div>
              <div class="text-subtitle-2 grey--text">필기, 자소서, 블로그 포스트 등을 자유롭게</div>
            </v-col> 
            <v-col cols="4">
              <img src="@/assets/us/note.jpg" alt="user-image" class="about-intro">
            </v-col> 
          </v-row>
          <v-row class="my-15 py-15">
            <v-col cols="3">
              <router-link to="/login" class="link-tag">
                <div><img src="@/assets/icon/monitor.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">Log in</div>
              </router-link>
            </v-col>
            <v-col cols="3">
              <router-link to="/schedule" class="link-tag">
                <div><img src="@/assets/icon/calen.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">Schedule</div>
              </router-link>
            </v-col>
            <v-col cols="3">
              <router-link to="/note" class="link-tag">
                <div><img src="@/assets/icon/blogging.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">Note</div>
              </router-link>              
            </v-col>
            <v-col cols="3">
              <router-link to="/note/create" class="link-tag">
                <div><img src="@/assets/icon/writing.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">Write</div>
              </router-link>              
            </v-col>
          </v-row> 
          <v-row class="mt-5">
            <v-col cols="4">
              <img src="@/assets/us/people.jpg" alt="people-image" class="about-intro">
            </v-col>
            <v-col cols="8" class="text-right mb-2" align-self="end">
              <div class="display-2 font-weight-thin">GROUP-Log</div>
              <div class="text-subtitle-2 grey--text">스터디, 팀 프로젝트 등 그룹원들과 함께</div>
              <div class="text-subtitle-2 grey--text">일정과 기록을 공유할 수 있는 Group-log 서비스</div>
            </v-col>    
          </v-row>
          <v-row class="mt-5">
            <v-col cols="8" class="text-left mb-2" align-self="end">
              <div class="display-2 font-weight-thin">ANALYSIS</div>
              <div class="text-subtitle-2 grey--text">PLOG 사용과 해시태그 현황을</div>
              <div class="text-subtitle-2 grey--text">마이페이지에서 시각화로 볼 수 있는 서비스</div>
            </v-col> 
            <v-col cols="4">
              <img src="@/assets/us/analysis.jpg" alt="user-image" class="about-intro">
            </v-col> 
          </v-row> 
          <v-row class="my-15 pt-15">
            <v-col cols="3">
              <router-link to="/group" class="link-tag">
                <div><img src="@/assets/icon/network.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">Group</div>
              </router-link>
            </v-col>
            <v-col cols="3">
              <router-link to="/profile" class="link-tag">
                <div><img src="@/assets/icon/analytics.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">Statistics</div>
              </router-link>
            </v-col>
            <v-col cols="3">
              <router-link to="/howto" class="link-tag">
                <div><img src="@/assets/icon/question.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">How to</div>
              </router-link>              
            </v-col>
            <v-col cols="3">
              <router-link to="/gallery" class="link-tag">
                <div><img src="@/assets/icon/openbook.png" alt="user-image" class="link-icon "></div>
                <div class="mt-5">Gallery</div>
              </router-link>              
            </v-col>
          </v-row>      
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">ABOUT</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">What is PLOG?</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">
                <img
                src="@/assets/us/us2.jpg"
                height="200"
                style="width:100%;"
              >
                <v-row
                  align="center"
                  justify="center"
                >
                  <v-col class="text-center" cols="12">
                    <h4 class="black--text font-weight-thin">P L A N N E R  &nbsp;&nbsp; +  &nbsp;&nbsp; B L O G</h4>
                  </v-col>
                </v-row>
              <v-row class="mt-8 pt-5">
                <v-col cols="12" class="text-center mb-2" align-self="end">
                  <div class="display-1 font-weight-thin">SCHEDULE</div>
                  <div class="text-caption grey--text">대학생, 취준생등 모두를 위한 일정 관리</div>
                  <div class="text-caption grey--text">자소서 제출 일정 등 주요 일정을 한 눈에 :)</div>
                </v-col>    
              </v-row>
              <v-row class="mt-5">
                <v-col cols="12" class="text-center mb-2" align-self="end">
                  <div class="display-1 font-weight-thin">DAILY LOG</div>
                  <div class="text-caption grey--text">마크다운 형식을 지원하는 에디터</div>
                  <div class="text-caption grey--text">필기, 자소서, 블로그 포스트 등을 자유롭게</div>
                </v-col> 
              </v-row>
              <v-row class="mt-15 px-10">
                <v-col cols="6" class="mb-5">
                  <router-link to="/login" class="link-tag">
                    <div><img src="@/assets/icon/monitor.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">Log in</div>
                  </router-link>
                </v-col>
                <v-col cols="6" class="mb-5">
                  <router-link to="/schedule" class="link-tag">
                    <div><img src="@/assets/icon/calen.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">Schedule</div>
                  </router-link>
                </v-col>
                <v-col cols="6">
                  <router-link to="/note" class="link-tag">
                    <div><img src="@/assets/icon/blogging.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">Note</div>
                  </router-link>              
                </v-col>
                <v-col cols="6">
                  <router-link to="/note/create" class="link-tag">
                    <div><img src="@/assets/icon/writing.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">Write</div>
                  </router-link>              
                </v-col>
              </v-row>       
              <v-row class="mt-8 pt-5">
                <v-col cols="12" class="text-center mb-2" align-self="end">
                  <div class="display-1 font-weight-thin">GROUP</div>
                  <div class="text-caption grey--text">스터디, 팀 프로젝트 등 그룹원들과 함께</div>
                  <div class="text-caption grey--text">일정과 노트를 공유할 수 있는 Group-log 서비스</div>
                </v-col>    
              </v-row>
              <v-row class="mt-5">
                <v-col cols="12" class="text-center mb-2" align-self="end">
                  <div class="display-1 font-weight-thin">ANALYSIS</div>
                  <div class="text-caption grey--text">PLOG 사용 내역과 해시태그 현황을</div>
                  <div class="text-caption grey--text">마이페이지에서 시각화로 볼 수 있는 서비스</div>
                </v-col> 
              </v-row>
              <v-row class="mt-15 px-10">
                <v-col cols="6" class="mb-5">
                  <router-link to="/group" class="link-tag">
                    <div><img src="@/assets/icon/network.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">Group</div>
                  </router-link>
                </v-col>
                <v-col cols="6" class="mb-5">
                  <router-link to="/profile" class="link-tag">
                    <div><img src="@/assets/icon/analytics.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">Statistics</div>
                  </router-link>
                </v-col>
                <v-col cols="6">
                  <router-link to="/howto" class="link-tag">
                    <div><img src="@/assets/icon/question.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">How To</div>
                  </router-link>              
                </v-col>
                <v-col cols="6">
                  <router-link to="/gallery" class="link-tag">
                    <div><img src="@/assets/icon/openbook.png" alt="user-image" class="link-icon "></div>
                    <div class="mt-4 font-weight-light text-button">Gallery</div>
                  </router-link>              
                </v-col>
              </v-row>       
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Aboutus',
    data() {
      return {
      showMenu: false,
      items: [
        { title: 'Developers >' },
      ],
      }
    },
    methods: {
      gotoDevelopers() {
        if (this.$route.name !== 'Developers'){
        this.$router.push('/aboutus/developers')
      }
      }
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
.about-intro {
  width: 100%;
}

.link-icon {
  width: 30%;

}
.link-tag {
  text-decoration: none;
  color: #c7c7c7;
  text-align: center;
}
</style>