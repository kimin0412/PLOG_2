<template>
  <div>
    <!-- 큰 화면 -->
    <div class="d-none d-sm-block">
      <div class="centercontent mx-auto">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold text-center">Log in</v-col>
        </v-row>
        <v-row justify="center" class="mt-7">
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text">E-mail</v-col>
          <v-col cols="12" class="py-0 px-0">
            <v-text-field
              placeholder="이메일을 입력해주세요"
              filled
              rounded
              dense
              v-model="useremail"
              clearable
              autofocus
            ></v-text-field>            
          </v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">Password</v-col>
          <v-col cols="12" class="py-0 px-0">
            <v-text-field
              placeholder="비밀번호를 입력해주세요"
              filled
              rounded
              dense
              type="password"
              clearable
              v-model="userpassword"
            ></v-text-field> 
          </v-col>      
        </v-row>
        <v-row>
          <v-btn @click="sendLoginData" rounded color="blue" dark block>Log in</v-btn>
        </v-row>
        <v-row class="mt-2">
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">비밀번호를 잊었습니까?</v-col>
          <v-col cols="4" class="py-0 text-center"><a href="#" class="blue-darken-2--text text-caption linkto">찾기</a></v-col>
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">아직 회원이 아니십니까?</v-col>
          <v-col cols="4" class="py-0 text-center"><router-link to="/signup" class="text-caption linkto">회원 가입</router-link></v-col>
        </v-row>
      </v-container>
      </div>
    </div>

    <!-- 작은화면 -->
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold blue--text">Log in</v-col>
        </v-row>
        <v-row justify="center" class="mt-7">
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5">E-mail</v-col>
          <v-col cols="12" class="py-0">
            <v-text-field
              placeholder="이메일을 입력해주세요"
              filled
              rounded
              dense
              v-model="useremail"
              autofocus
            ></v-text-field>            
          </v-col>
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5  mt-n3">Password</v-col>
          <v-col cols="12" class="py-0">
            <v-text-field
              placeholder="비밀번호를 입력해주세요"
              filled
              rounded
              dense
              type="password"
              v-model="userpassword"
            ></v-text-field> 
          </v-col>      
        </v-row>
        <v-row class="px-3">
          <v-btn  @click="sendLoginData" rounded color="blue" dark block>Log in</v-btn>
        </v-row>
        <v-row class="px-5 mt-2">
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">비밀번호를 잊었습니까?</v-col>
          <v-col cols="4" class="py-0 text-center"><a href="#" class="blue-darken-2--text text-caption linkto">찾기</a></v-col>
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">아직 회원이 아니십니까?</v-col>
          <v-col cols="4" class="py-0 text-center"><router-link to="/signup" class="text-caption linkto">회원 가입</router-link></v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
export default {
  name: 'login',
  data() {
    return {
      useremail:'',
      userpassword:'',
      errmessage1: '',
      errmessage2: '',
    }
  },
  methods: {
    sendLoginData() {
      if (!this.useremail || !this.userpassword) {
        alert('모든 항목을 입력해야 합니다')
      } else {
        if (!/.+@.+\..+/.test(this.useremail)) {
          alert('이메일 형식으로 입력해주세요. 예시) example@example.com')
          } else {
            alert('성공') //요청
            // 요청보내면 됨
            // 성공하면 => 쿠키 세팅하고 스케쥴 페이지로 렌더링
            // 실패하면 => 오류 페이지로 렌더링
          }
      }
    }
  }
}
</script>

<style scoped>
.submitbtn {
  min-width: 100% !important;
}

.linkto {
  text-decoration: none;
  color: rgb(67, 119, 196);
}

.centercontent {
  width: 50%;
}
</style>