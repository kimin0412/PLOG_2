<template>
  <div>
    <div class="d-none d-sm-block">
      프로필 수정 페이지       
    </div>
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h5">EDIT</v-col>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold">profile</v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 text-h6">Nickname</v-col>
          <v-col cols="12">

          </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6">Email</v-col>
          <v-col cols="12">

          </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6">Birthday</v-col>
          <v-col cols="12">

          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import User from '../../models/user';
import http from '@/util/http-common.js'

export default {
    name: 'editprofile',
    data() {
        return {
            user: new User('', '', '', ''),
        }
    },
    methods: {
Edit(){
          if (this.user.password != '' && (this.user.password !== this.user.password2)) {
          //console.log(this.user.password)
          //console.log(this.user.password2)
          alert('비밀번호가 일치하지 않습니다')
          }
      },
      withDraw(){
        //const withdraw2 = confirm("진짜 탈퇴합니까?");
        console.log(this.$store.state.auth.user.id);
        http.get('auth/withdraw', {
              params : {
                id : this.$store.state.auth.user.id,
              }
        });
        this.logOut();
      },
      handleLogin() {

      if (!this.user.username.trim()) {
        alert("ID를 입력해주세요")
      } else if (!this.user.password.trim()) {
        alert("비밀번호를 입력해주세요")
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
                this.$store.state.auth.status.loggedIn = true;
                this.successful = true;
                this.$router.push('/schedule');
              },
              error => {
                this.loading = false;
                this.message =
                  (error.response && error.response.data) ||
                  error.message ||
                  error.toString();

                
                console.log("헤더 : " + Headers.message.status);
                console.log(this.message);
              //this.$router.push("/error")

              }
            )
            .catch(() => {
                console.log("헤더 : " + Headers.response);
                console.log(this.message);
                
              this.$router.push("/error")
            })
          }
        });
      }
    }
     },
    watch: {
    },
     computed: {
      currentUser(){
        return this.$store.state.auth.user;
      }
    },
    mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
    }
}
</script>

<style>

</style>