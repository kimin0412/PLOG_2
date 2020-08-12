<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-row class="mt-5">
          </v-row>
          <v-row class="mt-15 pt-15">
          </v-row>       
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">PREVIEW</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">PLOGGERS</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">
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
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Preview',
    data() {
      return {

        }
    },
    methods: {

    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
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