<template>
    <div>
        <div class="d-none d-sm-block pt-12">
            <div class="centercontent mx-auto">
            <v-container>
                <v-row>
                    <v-col cols="12" class="py-1 text-h4 text-center font-weight-bold">Sign up</v-col>
                </v-row>
                <v-row justify="center" class="mt-7">
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text pl-5">Username</v-col>
                    <v-col cols="12" class="py-0">
                        <v-text-field
                        placeholder="닉네임을 입력해주세요"
                        filled
                        rounded
                        dense
                        v-model="username"
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
                        v-model="useremail"
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
                        v-model="userpassword"
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
                        v-model="userpassword2"
                        ></v-text-field> 
                    </v-col>     
                </v-row>
                <v-row class="px-3">
                    <v-btn @click="sendSignupData" rounded color="blue" dark block>Sign up</v-btn>
                </v-row>
                <v-row class="px-5 mt-2">
                    <v-col cols="8" class="py-0 grey--text text-caption pt-1">이미 계정이 있으신가요?</v-col>
                    <v-col cols="4" class="py-0 text-right"><router-link to="/login" class="text-caption linkto">로그인 하기</router-link></v-col>
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
                        v-model="username"
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
                        v-model="useremail"
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
                        v-model="userpassword"
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
                        v-model="userpassword2"
                        ></v-text-field> 
                    </v-col>     
                </v-row>
                <v-row class="px-3">
                    <v-btn @click="sendSignupData" rounded color="blue" dark block>Sign up</v-btn>
                </v-row>
                <v-row class="px-5 mt-2">
                    <v-col cols="8" class="py-0 grey--text text-caption pt-1">이미 계정이 있으신가요?</v-col>
                    <v-col cols="4" class="py-0 text-center"><router-link to="/login" class="text-caption linkto">로그인 하기</router-link></v-col>
                </v-row>
            </v-container>
        </div>
    </div>
</template>

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

<style scoped>
.linkto {
  text-decoration: none;
  color: rgb(67, 119, 196);
}
.centercontent {
  width: 50%;
}
</style>