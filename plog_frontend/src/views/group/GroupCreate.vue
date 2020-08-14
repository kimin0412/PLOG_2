<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto mt-10">
          <v-row>
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light">Make your own Group</div>
            </v-col>
            <v-col cols="12" class="text-center grey--text mb-4">
                <v-container class="centercontent">
                    <v-row justify="center">
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text">Group Name</v-col>
                    <v-col cols="12" class="py-0 px-0">
                        <v-text-field
                        placeholder="그룹명을 입력해주세요"
                        filled
                        rounded
                        dense
                        v-model="groupname"
                        clearable
                        autofocus
                        ></v-text-field>            
                    </v-col>
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">Enter Code</v-col>
                    <v-col cols="12" class="py-0 px-0">
                        <v-text-field
                        placeholder="입장 코드를 입력해주세요"
                        filled
                        rounded
                        dense
                        clearable
                        v-model="entercode"
                        ></v-text-field> 
                    </v-col>      
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">Introduction</v-col>
                    <v-col cols="12" class="py-0 px-0">
                        <v-textarea
                        filled
                        auto-grow
                        rows="4"
                        row-height="30"
                        v-model="groupintro"
                        placeholder="그룹에 대해 150자 이내로 간단히 소개해주세요"
                        :rules="[rules.counter]"
                        counter
                        rounded
                        maxlength="150"
                        ></v-textarea>
                    </v-col>   
                    </v-row>
                    <v-row class="my-2">
                        <v-col cols="1" class="px-0 pb-0 mx-0 my-0">
                            <v-card :color="groupcolor" class="py-2 transparent--text">색</v-card>
                        </v-col>
                        <v-col cols="11">
                            <v-select v-model="groupcolor"
                                        :items="colors"
                                        filled
                                        dense
                                        label="그룹을 대표하는 색깔을 골라주세요"
                                        full-width>
                            </v-select>
                        </v-col>
                    </v-row>
                    <v-row>
                    <v-btn @click="createGroup" rounded color="blue" dark block>Create Group</v-btn>
                    </v-row>
                    <v-row class="mt-2">
                    <v-col cols="8"></v-col>
                    <v-col cols="4" class="py-0 text-right"><router-link to="/group" class="text-decoration-none blue-darken-2--text text-caption linkto">◀ 돌아가기</router-link></v-col>
                    </v-row>
                </v-container>
            </v-col>
          </v-row>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">Make group</v-col>
          </v-row>
          <v-row class="mt-5">
            <v-col cols="12" class="text-center grey--text mb-4">
                <v-container>
                    <v-row justify="center">
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text">Group Name</v-col>
                    <v-col cols="12" class="py-0 px-0">
                        <v-text-field
                        placeholder="그룹명"
                        filled
                        rounded
                        dense
                        v-model="groupname"
                        clearable
                        autofocus
                        ></v-text-field>            
                    </v-col>
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">Enter Code</v-col>
                    <v-col cols="12" class="py-0 px-0">
                        <v-text-field
                        placeholder="입장 코드"
                        filled
                        rounded
                        dense
                        clearable
                        v-model="entercode"
                        ></v-text-field> 
                    </v-col>      
                    <v-col cols="12" class="py-1 text-subtitle-2 grey--text mt-n3">Introduction</v-col>
                    <v-col cols="12" class="py-0 px-0">
                        <v-textarea
                        filled
                        auto-grow
                        rows="4"
                        row-height="20"
                        v-model="groupintro"
                        placeholder="150자 이내"
                        :rules="[rules.counter]"
                        counter
                        rounded
                        maxlength="150"
                        ></v-textarea>
                    </v-col>   
                    </v-row>
                    <v-row class="my-2">
                        <v-col cols="2" class="px-0 pb-0 mx-0 my-0">
                            <v-card :color="groupcolor" class="py-2 transparent--text">색</v-card>
                        </v-col>
                        <v-col cols="10">
                            <v-select v-model="groupcolor"
                                        :items="colors"
                                        filled
                                        dense
                                        label="그룹을 대표하는 색깔을 골라주세요"
                                        full-width>
                            </v-select>
                        </v-col>
                    </v-row>
                    <v-row>
                    <v-btn @click="createGroup" small rounded color="blue" dark block>Create Group</v-btn>
                    </v-row>
                    <v-row class="mt-2">
                    <v-col cols="8"></v-col>
                    <v-col cols="4" class="py-0 text-right"><router-link to="/group" class="text-decoration-none blue-darken-2--text text-caption linkto">◀ 돌아가기</router-link></v-col>
                    </v-row>
                </v-container>
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'
export default {
    name: 'GroupCreate',
    data() {
      return {
          groupname: '',
          entercode: '',
          groupintro:'',
          groupcolor: 'indigo',
          rules: {
            counter: value => value.length <= 150 || '150자 이내',
          },
          colors: ['red', 'pink', 'purple', 'indigo', 'light-blue', 'green', 'lime', 'yellow', 'orange', 'brown', 'grey'],
      }
    },
    methods: {
        createGroup() {
            if (this.groupname.trim() === ''){
              this.$dialog.notify.warning("그룹명은 필수입니다. 😥", {
                  position: "bottom-right",
                  timeout: 3000,
              });
            } else {
                if (this.entercode.trim() === ''){
                  this.$dialog.notify.warning("입장 확인용 비밀번호를 설정해주세요! 😥", {
                      position: "bottom-right",
                      timeout: 3000,
                  });
                } else {
                    if (this.groupintro.trim() === ''){
                        this.$dialog.notify.warning("짧은 소개 부탁드립니다! 😃", {
                          position: "bottom-right",
                          timeout: 3000,
                      });
                    } else {
                        http.post('/club/insert', {
                          clId : this.$store.state.auth.user.id,
                          clName : this.groupname,
                          clInfo : this.groupintro,
                          clColor : this.groupcolor,
                          clPassword : this.entercode
                          // sColor : this.pickColor,
                        })
                        .then(({ data }) => {
                          let msg = "등록 처리시 문제가 발생했습니다.";
                            if (data.data == "success") {
                              msg = "등록이 완료되었습니다.";
                              this.$dialog.notify.success(msg + " 😃", {
                                position: "bottom-right",
                                timeout: 3000,
                              });
                            } else {
                              this.$dialog.notify.error(msg + " 😥", {
                                position: "bottom-right",
                                timeout: 3000,
                              });
                            }
                            this.$router.push("/group");
                        })
                        .catch((error) => {
                          if(error.response) {
                            this.$router.push("servererror")
                          } else if(error.request) {
                            this.$router.push("clienterror")
                          } else{
                            this.$router.push("/404");
                          }                          
                        })                
                    }
                }
            }
        }
  }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
.centercontent {
  width: 65%;
}
</style>