<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row>
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">PROFILE</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">
              <v-col cols="4" class="px-0">
                <img src="@/assets/profile/profile.jpg" alt="user-image" class="profile-image">
              </v-col>
              <v-col cols="8">
                <v-simple-table dense>
                  <template v-slot:default>
                    <tbody>
                      <tr>
                        <td>ID</td>
                        <td>{{currentUser.username}}</td>
                      </tr>
                      <tr>
                        <td>Email</td>
                        <td>{{currentUser.email}}</td>
                      </tr>
                      <tr>
                        <td>Birthday</td>
                        <td v-if="currentUser.birthday">{{currentUser.birthday}}</td>
                        <td v-else>정보가 없습니다.</td>
                      </tr>
                      <tr>
                        <td>Phone</td>
                        <td v-if="currentUser.phone">{{currentUser.phone}}</td>
                        <td v-else>정보가 없습니다.</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="12">

              </v-col>
            </v-row>

            <v-row class="mt-10">
              <v-col cols="12" class="py-1 px-0">
                <div class="mr-3" style="width: 70px; border-top: 3px solid #bdbdbd; border-left: 2px solid #bdbdbd;"></div>
                <div class="ml-1 grey--text text--lighten-1 py-1 text-subtitle-1 font-weight-bold">TAB2</div>
              </v-col>
            </v-row>
            <v-row class="mt-5">

            </v-row>
            <v-row>
            </v-row>

          </v-container>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">PROFILE</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">MY PAGE</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">Profile</v-col>
            <v-col cols="4" class="ml-1 mt-3">
                <img src="@/assets/profile/user.png" alt="user-image" class="profile-image">
            </v-col>
            <v-col cols="12">
                <v-simple-table dense>
                  <template v-slot:default>
                    <tbody>
                     <tr>
                        <td>ID</td>
                        <td>{{currentUser.username}}</td>
                      </tr>
                      <tr>
                        <td>Email</td>
                        <td>{{currentUser.email}}</td>
                      </tr>
                      <tr>
                        <td>Birthday</td>
                        <td v-if="currentUser.birthday">{{currentUser.birthday}}</td>
                        <td v-else>정보가 없습니다.</td>
                      </tr>
                      <tr>
                        <td>Phone</td>
                        <td v-if="currentUser.phone">{{currentUser.phone}}</td>
                        <td v-else>정보가 없습니다.</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>              
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Mypage',
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

<style scoped>
.content-center {
  width: 85%;
}

.profile-image {
  width: 100%;
}
</style>