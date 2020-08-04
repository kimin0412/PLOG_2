<template>
  <div>
    <div class="d-none d-sm-block">
      <div class="content-center mx-auto">
        <v-row>
            <v-col cols="2" class="d-flex align-center justify-center pt-5">
                Title
            </v-col>
            <v-col cols="2" class="d-flex align-center pt-5">
                {{this.Note.pTitle}}
                <!-- <v-text-field color="brown lighten-3" solo label="제목을 입력해 주세요" v-model="title"></v-text-field> -->
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2" class="d-flex align-center justify-center pb-10">
                Keyword
            </v-col>
            <v-col cols="2" class="d-flex align-center pb-10">
                <v-card-text class="d-flex py-0">
                        <div v-for="item in hashtags"  v-bind:key = "item" >
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
            <v-col cols="10" class="">
                <div height="500px" class="tui-editor-contents" v-html="content">
                </div>
            </v-col> 
        </v-row>
        <v-row>
            <v-col cols="12" class="d-flex justify-end py-0 atag">
                <router-link :to="{ path: 'update', query:{pId:this.pId}}" class="py-0 text-center" style="text-decoration: none;"> 
                      <v-btn small color="orange" class="py-0 white--text text-center atag mr-3">수정</v-btn>
                </router-link>
                <v-btn @click="deleteNote" small color="red" class="white--text">삭제</v-btn>
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

          </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6  grey--text">Content</v-col>
          <v-col cols="12">
            <div height="500px" class="" v-html="content">
            </div>
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
        }
    },

    created() {
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