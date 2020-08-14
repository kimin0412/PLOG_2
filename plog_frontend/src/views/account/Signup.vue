<template>
    <div>
        <div class="d-none d-sm-block">
            <div class="centercontent mx-auto">
            <v-container class="big-signupform">
                <v-row>
                    <v-col cols="12" class="py-1 display-2 font-weight-light text-center">Sign up</v-col>
                </v-row>
                <v-row justify="center" class="mt-7">
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5">UserID</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="아이디를 입력해주세요"
                        filled
                        rounded
                        dense
                        v-model="user.username"
                        autofocus
                        ></v-text-field>            
                    </v-col>
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5 mt-n3">E-mail</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="이메일을 입력해주세요"
                        filled
                        rounded
                        dense
                        v-model="user.email"
                        ></v-text-field>            
                    </v-col>
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5 mt-n3">Password</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="6자리 이상으로 입력해주세요."
                        filled
                        rounded
                        dense
                        type="password"
                        v-model="user.password"
                        ></v-text-field> 
                    </v-col>    
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5 mt-n3">Password 확인</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="비밀번호를 확인해주세요"
                        filled
                        rounded
                        dense
                        type="password"
                        v-model="user.password2"
                        ></v-text-field> 
                    </v-col>     
                </v-row>
                <v-row class="px-3">
                    <v-btn @click="handleRegister" rounded color="blue" dark block>Sign up</v-btn>
                </v-row>
                <v-row class="px-5 mt-2">
                    <v-col cols="8" class="py-0 grey--text text-caption pt-1">이미 계정이 있으신가요?</v-col>
                    <v-col cols="4" class="py-0 text-right"><router-link to="/login" class="text-caption linkto">Log in</router-link></v-col>
                </v-row>
            </v-container>
            </div>
        </div>
        <div class="d-block d-sm-none">
            <v-container>
                <v-row>
                    <v-col cols="12" class="py-1 text-h4 font-weight-bold blue--text">Sign up</v-col>
                </v-row>
                <v-row justify="center" class="mt-7">
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5">Username</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="이름을 입력해주세요"
                        filled
                        rounded
                        dense
                        v-model="user.username"
                        autofocus
                        ></v-text-field>            
                    </v-col>
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5 mt-n3">E-mail</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="이메일을 입력해주세요"
                        filled
                        rounded
                        dense
                        v-model="user.email"
                        ></v-text-field>            
                    </v-col>
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5 mt-n3">Password</v-col>
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
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5 mt-n3">Password 확인</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="비밀번호를 확인해주세요"
                        filled
                        rounded
                        dense
                        type="password"
                        v-model="user.password2"
                        ></v-text-field> 
                    </v-col>     
                </v-row>
                <v-row class="px-3">
                    <v-btn @click="handleRegister" rounded color="blue" dark block>Sign up</v-btn>
                </v-row>
                <v-row class="px-5 mt-2">
                    <v-col cols="8" class="py-0 grey--text text-caption pt-1">이미 계정이 있으신가요?</v-col>
                    <v-col cols="4" class="py-0 text-center"><router-link to="/login" class="text-caption linkto">로그인 하기</router-link></v-col>
                </v-row>
            </v-container>
        </div>
    </div>
</template>
<!--
<script>
export default {
    name: 'signup',
    data() {
        return{
            username: '',
            useremail: '',
            userpassword: '',
            userpassword2: '',
        }
    },
    methods: {
        sendSignupData() {
            if (!this.username||!this.useremail||!this.userpassword||!this.userpassword2) {
                alert('모든 항목은 필수로 입력해야 합니다.')
            } else {
                if (this.username.length > 15) {
                    alert('이름은 최소 3글자 이상 15글자 미만이어야 합니다')
                } else if (this.username.length < 3) {
                    alert('이름은 최소 3글자 이상 15글자 미만이어야 합니다')
                } else {
                    if (!/.+@.+\..+/.test(this.useremail)) {
                        alert('이메일 형식으로 입력해주세요. 예시) example@example.com')
                    } else {
                        if (this.userpassword.length < 8) {
                            alert('비밀번호는 최소 8글자 이상이어야합니다.')
                        } else {
                            if (this.userpassword !== this.userpassword2) {
                                alert('비밀번호가 일치하지 않습니다.')
                            } else {
                                alert('성공') //나중에 코드 지워야 함
                                //요청 보내기
                            }
                        }
                    }
                }
            }
        }
    }
}
</script>
-->

<script>
import User from '../../models/user';

export default {
  name: 'Register',
  data() {
    return {
      user: new User('', '', ''),
      submitted: false,
      successful: false,
      password2:'',
      message: '',
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleRegister() {
      if (this.user.password.length < 6) {
        this.$dialog.notify.warning("비밀번호는 6자리 이상이어야 합니다 😤", {
        position: "bottom-right",
        timeout: 3000,
        });
      } else if (this.user.password !== this.user.password2) {
        //   console.log(this.user.password)
        //   console.log(this.user.password2)
            this.$dialog.notify.error("비밀번호가 일치하지 않습니다 😥", {
            position: "bottom-right",
            timeout: 3000,
            });
      } else {
        this.message = '';
        this.submitted = true;
        this.$validator.validate().then(isValid => {
            if (isValid) {
            this.$store.dispatch('auth/register', this.user).then(
                data => {
                this.message = data.message;
                this.successful = true;
                this.$router.push('signup/success');
                },
                error => {
                this.message =
                    (error.response && error.response.data) ||
                    error.message ||
                    error.toString();
                this.successful = false;
                }
            );
            }
        });
      }
    }
  }
};
</script>
<style scoped>
.linkto {
  text-decoration: none;
  color: rgb(67, 119, 196);
}
.centercontent {
  width: 35%;
}
.big-signupform {
  margin-top: 15vh; 
}
</style>