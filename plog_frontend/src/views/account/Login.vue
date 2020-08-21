<template>
  <div>
    <!-- 큰 화면 -->
    <div class="d-none d-sm-block">
      <div class="centercontent mx-auto">
      <v-container class="big-loginform">
        <v-row>
          <v-col cols="12" class="py-1 display-2 font-weight-light text-center">Log in</v-col>
        </v-row>
        <v-row justify="center" class="mt-7">
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text">ID</v-col>
          <v-col cols="12" class="py-0 px-0">
            <v-text-field
              placeholder="ID를 입력해주세요"
              filled
              rounded
              dense
              v-model="user.username"
              clearable
              tabindex = "1"
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
              tabindex = "2"
              v-model="user.password"
            ></v-text-field> 
          </v-col>      
        </v-row>
        <v-row>
          <v-btn @click="handleLogin" rounded color="blue" dark block>Log in</v-btn>
        </v-row>
        <v-row class="mt-2">
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">ID를 잊었습니까?</v-col>
          <v-col cols="4" class="clicktarget py-0 mt-1 text-center blue-darken-2--text text-caption linkto" @click.stop="dialogtofind = true">찾기</v-col>
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">비밀번호를 잊었습니까?</v-col>
          <v-col cols="4" class="clicktarget py-0 mt-1 text-center blue-darken-2--text text-caption linkto" @click.stop="dialogtofind2 = true">찾기</v-col>
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">아직 회원이 아니십니까?</v-col>
          <v-col cols="4" class="py-0 text-center"><router-link to="/signup" class="text-caption linkto">회원 가입</router-link></v-col>
        </v-row>
      </v-container>
      </div>
    </div>
    <v-dialog
          v-model="dialogtofind2"
          max-width="500"
        >
          <v-card>
            <v-card-title class="headline">비밀번호 찾기</v-card-title>

            <v-card-text class="pb-0">
              <v-col cols="12" class="text-caption pt-5 text-center">ID를 입력해주세요</v-col>
                <v-col cols="12">
                  <v-text-field
                    label="ID"
                    hint="ID를 입력해주세요"
                    v-model="findmyid"
                    dense
                    outlined
                  ></v-text-field>
                </v-col>  
              <v-col cols="12" class="text-caption text-center">회원 가입 시 입력한 <br> 이메일을 입력해주세요</v-col>
                <v-col cols="12">
                  <v-text-field
                    label="Email"
                    hint="email@email.com 형식으로 입력해주세요"
                    v-model="findemail"
                    dense
                    outlined
                  ></v-text-field>
                </v-col>              
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn
                color="blue"
                text
                @click="findpw"
              >
                Send
              </v-btn>

              <v-btn
                color="grey"
                text
                @click="dialogtofind2 = false"
              >
                Close
              </v-btn>
            </v-card-actions>
          </v-card>
    </v-dialog>
    <v-dialog
          v-model="dialogtofind"
          max-width="500"
        >
          <v-card v-if="!sent">
            <v-card-title class="headline">ID 찾기</v-card-title>

            <v-card-text class="pb-0">
              <v-col cols="12" class="text-caption pt-5 text-center">회원 가입 시 입력한 <br> 이메일을 입력해주세요</v-col>
                <v-col cols="12">
                  <v-text-field
                    label="Email"
                    hint="email@email.com 형식으로 입력해주세요"
                    v-model="findemail2"
                    outlined
                  ></v-text-field>
                </v-col>                
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn
                color="blue"
                text
                @click="findid"
              >
                Send
              </v-btn>

              <v-btn
                color="grey"
                text
                @click="dialogtofind = false"
              >
                Close
              </v-btn>
            </v-card-actions>
          </v-card>
    </v-dialog>
    <!-- 작은화면 -->
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold blue--text">Log in</v-col>
        </v-row>
        <v-row justify="center" class="mt-7">
          <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5">ID</v-col>
          <v-col cols="12" class="py-0">
            <v-text-field
              placeholder="ID를 입력해주세요"
              filled
              rounded
              dense
              v-model="user.username"
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
              v-model="user.password"
            ></v-text-field> 
          </v-col>      
        </v-row>
        <v-row class="px-3">
          <v-btn  @click="handleLogin" rounded color="blue" dark block>Log in</v-btn>
        </v-row>
        <v-row class="px-5 mt-2">
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">ID를 잊었습니까?</v-col>
          <v-col cols="4" class="clicktarget mt-1 py-0 text-center blue-darken-2--text text-caption linkto" @click.stop="dialogtofind = true">찾기</v-col>
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">비밀번호를 잊었습니까?</v-col>
          <v-col cols="4" class="clicktarget mt-1 py-0 text-center blue-darken-2--text text-caption linkto" @click.stop="dialogtofind2 = true">찾기</v-col>
          <v-col cols="8" class="py-0 grey--text text-caption pt-1">아직 회원이 아니십니까?</v-col>
          <v-col cols="4" class="py-0 text-center"><router-link to="/signup" class="text-caption linkto">회원 가입</router-link></v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import User from '../../models/user';
import http from '@/util/http-common.js'

export default {
  name: 'Login',
  data() {
    return {
      sent: false,
      dialogtofind: false,
      dialogtofind2: false,
      user: new User('', ''),
      loading: false,
      message: '',
      findemail: '',
      findemail2: '',
      findmyid: '',
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  created() {
    if (this.$store.state.auth.status.loggedIn) {
      this.$router.push('/mypage');
    }
  },
  methods: {
    handleLogin() {
      if (!this.user.username.trim()) {
        this.$dialog.notify.warning("ID를 입력해주세요 😤", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else if (!this.user.password.trim()) {
        this.$dialog.notify.warning("비밀번호를 입력해주세요 😤", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else {
        this.loading = true;
        this.$validator.validateAll().then(isValid => {
          if (!isValid) {
            this.loading = false;
            return;
          }
          if (this.user.username && this.user.password) {
            this.$store.dispatch('auth/login', this.user).then(
              () => {
                this.$store.state.auth.status.loggedIn = true
                this.$router.push('/schedule');
              },
              error => {
                this.loading = false;
                this.message =
                  (error.response && error.response.data) ||
                  error.message ||
                  error.toString();
                this.$dialog.notify.error("없는 유저거나 비밀번호가 틀렸습니다.", {
                  position: "bottom-right",
                  timeout: 3000,
                });

               this.$router.push("/login")

              }
            )
            .catch(() => {
              this.$router.push("/error")
            })
          }
        });
      }
    },
     findpw() {
      if (!this.findemail || !this.findmyid) {
        this.$dialog.notify.warning("빈칸을 채워주세요 😚", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else if (!/.+@.+\..+/.test(this.findemail)) {
          this.$dialog.notify.warning("이메일 형식으로 입력해주시기 바랍니다! 😤", {
          position: "bottom-right",
          timeout: 3000,
          });
      } else {
        console.log("비밀번호 찾기")
        http.get('/findPW', {
              params : {
                findmyid : this.findmyid,
                findemail : this.findemail
              }
        }).then(()=>{
          //alert("Email로 Password가 전송되었습니다.")
          this.$router.push('/login'); 
         // this.$router.go();

        });
        this.findmyid ='';
        this.findemail ='';
        this.dialogtofind2 = false;
        this.$dialog.notify.success("Email로 Password가 전송되었습니다.", {
              position: "bottom-right",
              timeout: 3000,
            });
             this.$router.push('/login');
            //this.$router.go();
      }
    },
    findid() {
      if (!this.findemail2) {
           this.$dialog.notify.warning("이메일을 입력해주세요 😤", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else if (!/.+@.+\..+/.test(this.findemail2)) {
          this.$dialog.notify.warning("이메일 형식으로 입력해주시기 바랍니다! 😤", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else {
        console.log("ID찾기")
        http.get('/findID', {
              params : {
                findemail2 : this.findemail2,
              }
        }).then(()=>{
           // this.$router.go();

        }).catch(()=>{
        });
        this.findemail2=''
        this.dialogtofind = false
        this.$dialog.notify.success("Email로 ID가 전송되었습니다.", {
              position: "bottom-right",
              timeout: 3000,
            });
             this.$router.push('/login');
            //this.$router.go();
      }
    },
  }
};
// export default {
//   name: 'login',
//   data() {
//     return {
//       useremail:'',
//       userpassword:'',
//       errmessage1: '',
//       errmessage2: '',
//     }
//   },
//   methods: {
//     sendLoginData() {
//       if (!this.useremail || !this.userpassword) {
//         alert('모든 항목을 입력해야 합니다')
//       } else {
//         if (!/.+@.+\..+/.test(this.useremail)) {
//           alert('이메일 형식으로 입력해주세요. 예시) example@example.com')
//           } else {
//             alert('성공') //요청
//             // 요청보내면 됨
//             // 성공하면 => 쿠키 세팅하고 스케쥴 페이지로 렌더링
//             // 실패하면 => 오류 페이지로 렌더링
//           }
//       }
//     }
//   }
// }
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
  width: 35%;
}


.big-loginform {
  margin-top: 17vh; 
}

.clicktarget:hover {
  cursor: pointer;
}
</style>