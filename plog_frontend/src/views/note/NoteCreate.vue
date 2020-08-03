<template>
  <div>
    <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
        <v-container class="pt-0">
        <v-row>
            <v-col cols="2" class="px-0 d-flex align-center justify-center">
                Title
            </v-col>
            <v-col cols="10" class="d-flex align-center pb-0">
                <v-text-field color="brown lighten-3" dense solo label="제목을 입력해 주세요" v-model="title"></v-text-field>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="2" class="pt-0 px-0 d-flex align-center justify-center pb-10">
                Keyword
            </v-col>
            <v-col cols="10" class="pt-0 px-0 d-flex align-center">
                <v-container fluid>
                    <v-combobox
                    v-model="model"
                    :search-input.sync="search"
                    hide-selected
                    hint="추가(enter) | 삭제(backspace) | 최대 10개까지 지정 가능"
                    multiple
                    persistent-hint
                    small-chips
                    color="brown lighten-3"
                    @keyup.space="nospace"
                    >
                    <template v-slot:no-data>
                        <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>
                            추가 <kbd>enter</kbd> | 삭제 <kbd>Backspace</kbd> :)
                            </v-list-item-title>
                        </v-list-item-content>
                        </v-list-item>
                    </template>
                    </v-combobox>
                </v-container>
            </v-col> 
        </v-row>
        <v-row>
            <v-col cols="12">
                <Editor ref="toastuiEditor1"  style="height: 600px;"/> 
            </v-col> 
        </v-row>
        <v-row>
            <v-col cols="12" class="d-flex justify-end py-0">
                <v-dialog v-model="dialog" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                    color="amber darken-2"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    class="px-5 d-none d-sm-block"
                    small
                    >
                    connect with schedule
                    </v-btn>
                </template>
                <v-card>
                    <v-card-title>Schedules</v-card-title>
                    <v-divider></v-divider>
                    <v-card-text style="height: 300px;">
                    <v-radio-group v-model="dialogm1" column >
                        <div v-for="item in todaySchedule"  v-bind:key = "item">
                            <v-radio v-bind:label="item.name" v-bind:value="item.id"></v-radio>
                        </div>
                    </v-radio-group>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions class="d-flex justify-end">
                        <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
                        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
            </v-col>
            <v-col cols="12" class="text-end">
                <v-btn @click="createAction" small color="light-green" class="white--text mr-3">SAVE</v-btn>
                <v-btn @click="tmpcreateAction" small color="grey" class="white--text">TEMP SAVE</v-btn>
            </v-col>
        </v-row> 
        </v-container>
        </div>       
    </div>
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h5">NEW</v-col>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold">POST</v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 text-h6">Title</v-col>
          <v-col cols="12">
            <v-text-field color="brown lighten-3" dense solo label="제목을 입력해 주세요" v-model="title"></v-text-field>
          </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6">Keyword</v-col>
          <v-col cols="12">
            <v-container fluid>
                <v-combobox
                v-model="model"
                :search-input.sync="search"
                hide-selected
                hint="추가(enter) | 삭제(backspace) | 최대 10개까지 지정 가능"
                multiple
                persistent-hint
                small-chips
                color="brown lighten-3"
                @keyup.space="nospace"
                >
                <template v-slot:no-data>
                    <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title>
                        추가 <kbd>enter</kbd> | 삭제 <kbd>Backspace</kbd> :)
                        </v-list-item-title>
                    </v-list-item-content>
                    </v-list-item>
                </template>
                </v-combobox>
            </v-container>
          </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6">Content</v-col>
          <v-col cols="12">
            <Editor ref="toastuiEditor2" /> 
          </v-col>
        </v-row>
        <v-row>
            <v-col cols="12" class="d-flex justify-end py-0">
                <v-dialog v-model="dialog" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                    color="amber darken-2"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    class="px-5 d-block d-sm-none"
                    small
                    >
                    + schedule
                    </v-btn>
                </template>
                <v-card>
                   <v-card-title>Schedules</v-card-title>
                    <v-divider></v-divider>
                    <v-card-text style="height: 300px;">
                    <div v-if="todaySchedule.length > 0">
                        <v-radio-group v-model="dialogm1" column >
                            <div v-for="item in todaySchedule"  v-bind:key = "item">
                                <v-radio v-bind:label="item.name" v-bind:value="item.id"></v-radio>
                            </div>
                        </v-radio-group>
                    </div>
                    <div v-else>
                        <div>
                             오늘의 일정이 없습니다.
                        </div>
                       
                    </div>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions class="d-flex justify-end">
                        <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
                        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
            </v-col>
            <v-col cols="12" class="text-end">
                <v-btn @click="createAction" small color="light-green" class="white--text mr-3">SAVE</v-btn>
                <v-btn @click="tmpcreateAction" small color="grey" class="white--text">TEMP SAVE</v-btn>
            </v-col>
        </v-row> 
      </v-container>
    </div>
  </div>
</template>

<script>
import http from '@/util/http-common.js';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/vue-editor';
import moment from 'moment';

export default {
    components: {
      Editor
    },
    data() {
        return {
            title : "",
            content : "",
            chip2: true,
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
            todaySchedule : [], 
            hashtags : '',
            nextPId : ''  
        }
    },

    created() {
        http.get('/hashtag/getnextPostId', {
            params : {
                uId : 1,
            }
        }).then(({data}) => {
            this.nextPId = data.hId;
        })

      http.get('/schedule/dayList', {
        params : {
            sId : 1,
          sDate : moment(new Date()).format('YYYY-MM-DD')
        }
      })
      .then(({data}) => {
        data.forEach(element => {
            this.todaySchedule.push({"name":element.sName, "startdate" : element.sStartdate, "enddate" : element.sEnddate, "id": element.sId});  
        });
      });
    },

    methods: {

        createAction() { 
            var content1 = this.$refs.toastuiEditor1.invoke("getHtml");
            var content2 = this.$refs.toastuiEditor2.invoke("getHtml");
            var content = null;
            if(content1 == ""){
                content = content2;
            } else {
                content = content1;
            }
            const Entities = require('html-entities').XmlEntities;
            const entities = new Entities();
            content = entities.encode(content);
            console.log(content);

            http.post('/post/',{
                    pTitle : this.title,
                    pContent : content,
                    pUser : 1,
                    pSchedule : this.dialogm1,
                    pCategory : 1,
            })
            .then(({ data }) => {
                if(data.data == 'success'){
                    alert('등록 완료');
                }
            });

            this.createTags();
        },
        createTags(){
            ////hashtag 저장하는 곳
            var numOfHashTag = this.model.length
            this.hashtags = ''
            for (let i = 0; i < numOfHashTag; i++) {
                this.hashtags += this.model[i] + " "                
            }

            http.post('/hashtag/insert',{
                hId : this.nextPId,
                hName : this.hashtags
            })
            .then(({data}) => {
                if(data.data == 'success'){
                    this.$router.push('/note')
                }
            })
        },
        tmpcreateAction() { 
            var content1 = this.$refs.toastuiEditor1.invoke("getHtml");
            var content2 = this.$refs.toastuiEditor2.invoke("getHtml");
            var content = null;
            if(content1 == ""){
                content = content2;
            } else {
                content = content1;
            }
            const Entities = require('html-entities').XmlEntities;
            const entities = new Entities();
            content = entities.encode(content);
            console.log(content);

            http.post('/tp/',{
                tpTitle : this.title,
                tpContent : content,
                tpUser : 1,
            })
            .then((Response) => {
                if(Response.data === 'success'){
                    alert('임시 샘플 등록 완료');
                    this.$router.push('/note')
                }
            })
        },
        nospace() {
            alert('공백 없이 단어로 입력해주세요')
        }
    },
    watch: {
      model (val) {
        if (val.length > 11) {
          this.$nextTick(() => this.model.pop())
        }
      },        
}
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
</style>