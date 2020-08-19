<template>
  <div>
      <div class="d-none d-sm-block">
        <div :class="{temploca:myClub.length<1}" class="content-center mx-auto mt-10">
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
                        class="d-flex transition-fast-in-fast-out blue darken-4 v-card--reveal display-1 white--text"
                        style="height: 100%;"
                      >
                        <v-icon class="display-1 white--text mr-2">mdi-magnify</v-icon> Search
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
                          class="d-flex transition-fast-in-fast-out blue darken-4  v-card--reveal display-1 white--text"
                          style="height: 100%;"
                        >
                          <v-icon class="display-1 white--text mr-2">mdi-account-multiple-plus</v-icon> Create
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
          <v-row class="mt-10 pt-10" v-if="myClub.length >= 1">
            <v-col cols="12 mb-n10">
              <div class="text-center display-1 font-weight-light">MY GROUP LIST</div>
            </v-col>
            <v-col cols="12">
              <carousel-3d :controls-visible="true" :controls-prev-html="'&#10092;'" :controls-next-html="'&#10093;'" 
                          :controls-width="30" :controls-height="60" :clickable="false">
                <slide v-for="(item, i) in myClub" :index="i" :key="i">
                  <router-link :to="{ path: 'group/detail2', query:{clId:item.clId}}" class="text-decoration-none">
                  <v-hover
                    v-slot:default="{ hover }"
                  >
                  <v-card
                    class="mx-auto mt-15"
                    max-width="100%"
                    min-height="15vh"
                    outlined
                    :elevation="hover ? 10:5"
                  >
                    <v-list-item three-line>
                      <v-list-item-content>
                        <div class="overline mb-4">{{item.clRegdate.substr(0,10)}}</div>
                        <v-list-item-title class="headline mb-1"><strong>{{item.clName}}</strong></v-list-item-title>
                        <v-list-item-subtitle class="text-truncate">Group intro : {{item.clInfo}}</v-list-item-subtitle>
                      </v-list-item-content>

                      <v-list-item-avatar
                        tile
                        size="30"
                        color="transparent"
                      ><v-card :color="item.clColor" style="width: 100%; height: 100%;"></v-card></v-list-item-avatar>
                    </v-list-item>

                    <v-card-actions class="d-flex justify-end">
                      <v-btn text class="indigo--text">ENTER</v-btn>
                    </v-card-actions>
                  </v-card>   
                  </v-hover>       
                  </router-link>          
                </slide>
              </carousel-3d>              
            </v-col>
            <!-- <v-col cols="11" class="offset-1 mx-10 mt-2" v-for="(item,i) in myClub" :key="i">
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
            </v-col> -->
          </v-row>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">My Group</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-row>
              <v-col cols="6" class="pr-1 pl-10">
                <router-link to="/group/search" class="link-tag">
                <v-card elevation="0 pt-4 pb-3">
                  <div><img src="@/assets/icon/gsearch.png" alt="user-image" class="link-icon"></div>
                  <div class="mt-2 grey--text">Search</div>
                </v-card>
                </router-link>
              </v-col>
              <v-col cols="6" class="pl-1 pr-10">
                <router-link to="/group/create" class="link-tag">
                <v-card elevation="0 pt-4 pb-3">
                  <div><img src="@/assets/icon/gcreate.png" alt="user-image" class="link-icon "></div>
                  <div class="mt-2 grey--text">Create</div>
                </v-card>
                </router-link>
              </v-col>
            </v-row>
          </v-row>
          <v-row>
            <v-col cols="12" class="mt-7 text-center text-h6 font-weight-bold">My Groups</v-col>
            <v-col cols="12" class="mt-n5">
              <v-carousel
                  cycle
                  hide-delimiter-background
                  height="100%"
                >
                  <v-carousel-item
                     v-for="(item, i) in myClub" :index="i" :key="i"
                     class="pb-5"
                  >
                    <v-sheet
                    light
                    color="transparent"
                    >
                  <router-link :to="{ path: 'group/detail2', query:{clId:item.clId}}" class="text-decoration-none">
                  <v-card
                    class="mx-auto mt-6 mb-10"
                    max-width="70%"
                  >
                    <v-card-text class="pb-0">
                      <v-row class="mr-2" justify="end">
                        <div class="text-caption grey--text text-right">{{i+1}}/{{myClub.length}}</div>
                      </v-row>
                      <v-row>
                        <v-col cols="4">
                          <div class="d-inline-block transparent--text mt-3 ml-1" :class="item.clColor" style="width:25px; height:25px;">cl</div>
                        </v-col>
                        <v-col cols="8">
                          <div class="black--text text-left font-weight-bold text-truncate pb-0">{{item.clName}}</div>
                          <p class="text-caption text-left">{{item.clRegdate.substr(0,10)}}</p>
                        </v-col>
                      </v-row>
                    </v-card-text>
                    <v-card-actions class="pt-0 d-flex justify-center">
                      <v-btn
                        text
                        small
                        color="grey"
                        class="text-caption"
                      >
                        Enter
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                  </router-link>
                    </v-sheet>
                  </v-carousel-item>
                </v-carousel>
            </v-col>
            <!-- <v-col cols="12" class="mb-n10">
              <carousel-3d :controls-visible="true" :controls-prev-html="'&#10092;'" :controls-next-html="'&#10093;'" 
                          :controls-width="30" :controls-height="40" :clickable="false">
                <slide v-for="(item, i) in myClub" :index="i" :key="i">
                  <router-link :to="{ path: 'group/detail', query:{clId:item.clId}}" class="text-decoration-none">
                  <v-card
                    class="mx-auto mt-6"
                    max-width="70%"
                  >
                    <v-card-text class="pb-0">
                      <v-row>
                        <v-col cols="4">
                          <div class="d-inline-block transparent--text mt-3 ml-1" :class="item.clColor" style="width:25px; height:25px;">cl</div>
                        </v-col>
                        <v-col cols="8">
                          <div class="black--text text-left font-weight-bold text-truncate pb-0">{{item.clName}}</div>
                          <p class="text-caption text-left">{{item.clRegdate.substr(0,10)}}</p>
                        </v-col>
                      </v-row>
                    </v-card-text>
                    <v-card-actions class="pt-0 d-flex justify-center">
                      <v-btn
                        text
                        small
                        color="black"
                        class="text-caption"
                      >
                        Enter
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                  </router-link>
                </slide>
              </carousel-3d>
            </v-col> -->
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js'
import { Carousel3d } from 'vue-carousel-3d'

export default {
    name: 'GroupMain',
    componenet: {
      'carousel-3d': Carousel3d.Carousel3d,
    'slide': Carousel3d.Slide
    },
    data() {
      return {
        myClub : [],
        slides: 7
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
      })
      .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("error")
          } else{
            this.$router.push("/404");
          }                          
        });
    },
    methods: {

    },

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
.temploca {
  padding-top: 10vh;
}

.link-icon {
  width: 30%;

}
.link-tag {
  text-decoration: none;
  color: #c7c7c7;
  text-align: center;
}
.carousel-3d-container figcaption {
  position: absolute;
  color: #fff;
  bottom: 0;
  position: absolute;
  bottom: 0;
  padding: 15px;
  font-size: 12px;
  min-width: 100%;
  box-sizing: border-box;
}
.carousel-3d-slide {
  background-color: transparent !important;
  border: 1px solid transparent;
}


</style>
<style lang="scss" scoped>
.carousel-3d-container {
  .carousel-3d-slide {
    padding: 20px;
    
    .title { font-size: 22px; }
  }
}

</style>