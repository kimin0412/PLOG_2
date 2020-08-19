<template>
  <div>
    <div class="d-none d-sm-block">
      <div class="content-center mx-auto">
        <v-row>
            <v-col cols="11" class="d-flex pt-5">
                <strong> {{ categoryName }}</strong>
            </v-col>
            <v-col class="d-flex  align-left justify-left pt-5">
              <v-btn text icon @click="bookmark()">
                  <v-icon large color="#FDD835" v-if="bmToggle == 1">mdi-star</v-icon>
                  <v-icon large color="gray" v-else>mdi-star</v-icon>
                </v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" class="d-flex py-1" style="font-size:30px; color:#455A64">
              {{ Note.pTitle }}
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" class="d-flex py-2" style="font-size:15px; color:#78909C">
              {{ writer }} |  {{ Note.pDate | removeTime }} | 
              <span v-if="scheduleName!=''" class="px-1">{{ scheduleName }} </span>
              |
              <span class="chgBtn px-2" @click="makePDF"> PDF </span>
              |
              <router-link :to="{ path: 'noteUpdate', query:{pId:this.pId, clId:this.groupId}}" class="py-0 text-center " style="text-decoration: none; color:#78909C"> 
                <span class="chgBtn px-2"> 수정 </span>
              </router-link>
              |
              <span class="chgBtn px-2" style="color:#F44336" @click="deleteNote"> 삭제 </span>
            </v-col>
        </v-row>
        <v-row class="d-flex align-right mb-10">
          <v-card-text class="d-flex align-right py-2">
            <div v-for="(item,i) in hashtags"  v-bind:key="i" >
              <v-chip
                class="ma-2"
                color="teal"
                text-color="white"
                small
              >
                <v-avatar left>
                  <v-icon>mdi-checkbox-marked-circle</v-icon>
                </v-avatar>
                {{item.name}}
              </v-chip>
            </div>
          </v-card-text>
        </v-row>
        <hr style="color:#78909C">
        <v-row>
            <v-col cols="10" class="" id="viewer" ref="viewer">
                <div height="500px" class="tui-editor-contents" v-html="content">
                </div>
            </v-col> 
        </v-row>       
      </div>
    </div>
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h5">DETAIL</v-col>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold">MY NOTE</v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 text-h6 grey--text">Title</v-col>
          <v-col cols="12" class="" >
              {{this.Note.pTitle}}
          </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1  grey--text text-h6">Keyword</v-col>
          <v-col cols="12">
            <v-card-text class="d-flex py-0 px-0">
                    <div v-for="(item,i) in hashtags"  v-bind:key="i" >
                      <v-chip
                        small
                        class="ma-2"
                        color="teal"
                        text-color="white"
                      >
                        <v-avatar left>
                          <v-icon small>mdi-checkbox-marked-circle</v-icon>
                        </v-avatar>
                        {{item.name}}
                      </v-chip>
                    </div>
              </v-card-text>
          </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6  grey--text">Content</v-col>
          <v-col cols="12">
            <div class="" v-html="content" style="max-width: 100vw;">
            </div>
          </v-col>
        </v-row>
        <v-row>
            <v-col cols="12" class="d-flex justify-end py-0 atag">
                <v-btn @click="makePDF" small color="green" class="py-0 white--text text-center atag mr-3">PDF</v-btn>
                <router-link :to="{ path: 'noteUpdate', query:{pId:this.pId, clId:this.groupId}}" class="py-0 text-center" style="text-decoration: none;"> 
                      <v-btn small color="orange" class="py-0 white--text text-center atag mr-3">수정</v-btn>
                </router-link>
                <v-btn @click="deleteNote" small color="red" class="py-0 white--text text-center atag mr-3">삭제</v-btn>
            </v-col>
        </v-row>   
      </v-container>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
import http from '@/util/http-common.js';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import html2canvas from 'html2canvas'
import jsPDF from 'jspdf'

export default {
    name: 'Note',
    data() {
        return {
            Note : {},
            title : "",
            content : "",
            chip2: true,
            keywordinput:'',
            keywords: ['디폴트',],
            dialogm1: '',
            dialog: false,
            editorText: [{
                text: 'This is initialValue.'
            }],
            editorOptions: [{
                hideModeSwitch: true
            }],
            model: [],
            search: null,
            pId: this.$route.query.pId,
            hashtags: [],
            bmToggle : 0,
            groupId: this.$route.query.clId,
            scheduleName : '',
        }
    },

    created() {
      window.scrollTo(0, 0);
      http.get('/post/', {
        params : {
          pId : this.pId,
        }
      })
      .then(({data}) => {
        this.Note = data;
        const Entities = require('html-entities').XmlEntities;
        const entities = new Entities();
        var v_content = this.Note.pContent;
        this.content = entities.decode(v_content);
        this.bmToggle = data.pBookmark;
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

      http.get('/hashtag/select', {
        params : {
          uid : this.$store.state.auth.user.id,
          pid : this.pId,
        }
      })
      .then(({data}) => {
        data.forEach(element => {
          this.hashtags.push({"name" : element})
        });
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

      http.get('/schedule/', {
        params : {
          pId : this.pId,
        }
      })
      .then(({data}) => {
        if(data.sId != 1){
          this.scheduleName = data.sName
        }
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

    mounted() { 
      
    },

    filters: {
      removeTime(val) {
        return val.substr(0, 10)
      },
    },

    methods: {
        getFormatDate(regtime) {
            return moment(new Date(regtime)).format('YYYY.MM.DD');
        },
        deleteNote: async function () {
          const res = await this.$dialog.warning({
            text:"노트를 삭제 하시겠습니까?",
            title: 'Delete Note'
          });
          if(res){
            http.delete('/post/', {
                params : {
                  pId : this.pId,
                }
              })
              .then((response) => {
                if(response.data === 'success'){
                  this.$dialog.notify.error("노트 삭제 완료 🤗", {
                    position: "bottom-right",
                    timeout: 3000,
                  });
                }
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

              http.delete('/hashtag/delete', {
                params : {
                  pId : this.pId,
                }
              })
              .then(({data}) => {
                if(data.data == 'success'){
                    this.$router.push({path:'/group/detail2', query:{clId : this.groupId}}); 
                }
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
          }
        },
        makePDF () {
            var fileName = this.Note.pTitle;
            window.scrollTo(0, 0);
            var canvas = document.createElement('canvas');
            canvas.setAttribute('id', "myCanvas");
            canvas.setAttribute('width', 1920);
            canvas.setAttribute('height', 1080);
            canvas.setAttribute('style', "width : 300px; height : 300px");
            // var context = canvas.getContext("2d");
            // context.scale(3,3);
            canvas = this.$refs.viewer;
            html2canvas(canvas).then(function(canvas){
                var doc = new jsPDF("p", "mm", "a4");
                var imgData = canvas.toDataURL('image/png');
                var imgWidth = 210; 
                var pageHeight = 295;
                var imgHeight = canvas.height * imgWidth / canvas.width;
                var heightLeft = imgHeight; 
                var position = 0; 
                doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);

                heightLeft -= pageHeight; 
                while (heightLeft >= 0) { 
                    position = heightLeft - imgHeight; 
                    doc.addPage(); 
                    doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight); 
                    heightLeft -= pageHeight; 
                }
                doc.save(fileName + ".pdf");
            })
        },
        bookmark(){
          http.get('/post/bookmark', {
              params : {
                uid : this.$store.state.auth.user.id,
                pid : this.pId,
              }
            })
            .then((response) => {
              if(response === 'success'){
                console.log("success");
              }              
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
            if(this.bmToggle == 1){
                this.bmToggle = 0;
                this.Note.pBookmark = 0;
            } else {
                this.bmToggle = 1;
                this.Note.pBookmark = 1;
            }
        },
    },
    watch: {
      model (val) {
        if (val.length > 11) {
          this.$nextTick(() => this.model.pop())
        }
      },
    },
          
}
</script>

<style scoped>
.atag {
  text-decoration: none;
}
.content-center {
  width: 85%;
}
</style>