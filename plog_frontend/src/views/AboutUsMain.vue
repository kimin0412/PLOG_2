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
          <v-row class="mt-15 pt-15">
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