<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto mt-10">
          <v-row>
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light">GROUP-LOG</div>
            </v-col>
            <v-col cols="12" class="text-center grey--text mb-4">
              Team 과 함께 PLOG를 활용해보세요 :)
            </v-col>
            <v-col cols="2"></v-col>
            <v-col cols="4" class="text-center">
              <router-link to="/group/search" class="text-decoration-none">
                <v-hover v-slot:default="{ hover }">
                <v-card
                  class="mx-auto"
                  color="grey lighten-4"
                  max-width="600"
                >
                  <v-img
                    :aspect-ratio="16/9"
                    src="@/assets/us/team.jpg"
                  >
                    <v-expand-transition>
                      
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out blue darken-4 v-card--reveal display-2 white--text"
                        style="height: 100%;"
                      >
                        <v-icon class="display-2 white--text mr-2">mdi-magnify</v-icon> Search
                      </div>
                      
                    </v-expand-transition>
                  </v-img>
                  <v-card-text
                    class="py-3"
                    style="position: relative;"
                  >
                    <div class="font-weight-light grey--text title">Find your group</div>
                  </v-card-text>
                </v-card>
              </v-hover>
            </router-link>
            </v-col>
            <v-col cols="4" class="text-center">
              <router-link to="/group/create" class="text-decoration-none">
                  <v-hover v-slot:default="{ hover }">
                  <v-card
                    class="mx-auto"
                    color="grey lighten-4"
                    max-width="600"
                  >
                    <v-img
                      :aspect-ratio="16/9"
                      src="@/assets/us/team.jpg"
                    >
                      <v-expand-transition>
                        
                        <div
                          v-if="hover"
                          class="d-flex transition-fast-in-fast-out blue darken-4  v-card--reveal display-2 white--text"
                          style="height: 100%;"
                        >
                          <v-icon class="display-2 white--text mr-2">mdi-account-multiple-plus</v-icon> Create
                        </div>
                        
                      </v-expand-transition>
                    </v-img>
                    <v-card-text
                      class="py-3"
                      style="position: relative;"
                    >
                      <div class="font-weight-light grey--text title">Make your group</div>
                    </v-card-text>
                  </v-card>
                </v-hover>
              </router-link>
            </v-col>  
            <v-col cols="2"></v-col>          
          </v-row>
          <v-row class="mt-10 pt-10">
            <v-col cols="12 mb-4">
              <div class="text-center display-1 font-weight-light">MY GROUP LIST</div>
            </v-col>
            <v-col cols="11" class="offset-1 mx-10 mt-2" v-for="(item,i) in myClub" :key="i">
              <v-hover v-slot:default="{ hover }">
              <router-link :to="{ path: 'group/detail', query:{clId:item.clId}}" class="text-decoration-none">
              <v-card
                class="mx-auto"
                :elevation="hover ? 12 : 2"
                :class="{ 'on-hover': hover }"
              >
                <v-card-text>
                  <div>group #{{i+1}}</div>
                  <p class="text-h4 text--primary mb-0">
                    {{ item.clName }}
                  </p>
                  <p>Group host</p>
                  <div class="text--primary text-truncate">
                    {{ item.clInfo }}
                  </div>
                </v-card-text>
              </v-card>
              </router-link>
              </v-hover>
            </v-col>
          </v-row>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">My Group :)</v-col>
          </v-row>
          <v-row class="mt-10">
            
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'
export default {
    name: 'GroupMain',
    data() {
      return {
        myClub : [],
      }
    },
    created() {
      this.myClub = []
      http.get('/club/list', {
        params : {
          uId : this.$store.state.auth.user.id,
        }
      }).then(({ data }) => {
        this.myClub = data
      });
    },
    methods: {

    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
.v-card--reveal {
  align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: .5;
  position: absolute;
  width: 100%;
}
</style>