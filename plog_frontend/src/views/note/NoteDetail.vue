<template>
  <div>
    <div class="d-none d-sm-block">
      <div class="content-center mx-auto">
        <v-row>
            <v-col cols="2" class="d-flex align-center justify-center pt-5">
                Title
            </v-col>
            <v-col cols="10" class="d-flex align-center pt-5">
                {{this.Note.pTitle}}
                <!-- <v-text-field color="brown lighten-3" solo label="제목을 입력해 주세요" v-model="title"></v-text-field> -->
            </v-col>
            <v-flex class="py-0 text-center">
                <v-btn text icon @click="bookmark()">
                        <v-icon large color="#FDD835" v-if="bmToggle == 1">mdi-star</v-icon>
                        <v-icon large color="gray" v-else>mdi-star</v-icon>
                      </v-btn>
            </v-flex>
        </v-row>
        <v-row>
            <v-col cols="2" class="d-flex align-center justify-center pb-10">
                Keyword
            </v-col>
            <v-col cols="10" class="d-flex align-center pb-10">
                <v-card-text class="d-flex py-0">
                        <div v-for="(item,i) in hashtags"  v-bind:key="i" >
                          <v-chip
                            class="ma-2"
                            color="teal"
                            text-color="white"
                          >
                            <v-avatar left>
                              <v-icon>mdi-checkbox-marked-circle</v-icon>
                            </v-avatar>
                            {{item.name}}
                          </v-chip>
                        </div>
                  </v-card-text>
            </v-col> 
        </v-row>
        <v-row>
            <v-col cols="2" class="d-flex align-top justify-center ">
                Content
            </v-col>
            <v-col cols="10" class="" id="viewer" ref="viewer">
                <div height="500px" class="tui-editor-contents" v-html="content">
                </div>
            </v-col> 
        </v-row>
        <v-row>
            <v-col cols="12" class="d-flex justify-end py-0 atag">
                <v-btn @click="makePDF" small color="green" class="py-0 white--text text-center atag mr-3">PDF</v-btn>
                <router-link :to="{ path: 'update', query:{pId:this.pId}}" class="py-0 text-center" style="text-decoration: none;"> 
                      <v-btn small color="orange" class="py-0 white--text text-center atag mr-3">수정</v-btn>
                </router-link>
                <v-btn @click="deleteNote" small color="red" class="py-0 white--text text-center atag mr-3">삭제</v-btn>
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
                <router-link :to="{ path: 'update', query:{pId:this.pId}}" class="py-0 text-center" style="text-decoration: none;"> 
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
        console.log(data);
        this.Note = data;
        const Entities = require('html-entities').XmlEntities;
        const entities = new Entities();
        var v_content = this.Note.pContent;
        console.log(this.Note.pContent);
        this.content = entities.decode(v_content);
        this.bmToggle = data.pBookmark;
      });

      http.get('/hashtag/select', {
        params : {
          uid : 1,
          pid : this.pId,
        }
      })
      .then(({data}) => {
        data.forEach(element => {
          this.hashtags.push({"name" : element})
        });
      });
    },

    methods: {
        getFormatDate(regtime) {
            return moment(new Date(regtime)).format('YYYY.MM.DD');
        },
        deleteNote() {
            http.delete('/post/', {
                params : {
                  pId : this.pId,
                }
              })
              .then((response) => {
                if(response.data === 'success'){
                    alert('삭제 완료');
                    this.$router.push('/note')
                }
              });

              http.delete('/hashtag/delete', {
                params : {
                  pId : this.pId,
                }
              })
              .then(({data}) => {
                if(data.data == 'success'){
                    this.$router.push('/note')
                }
              });
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
                uid : 1,
                pid : this.pId,
              }
            })
            .then((response) => {
              if(response === 'success'){
                console.log("success");
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