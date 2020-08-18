<template>
  <div>
    <div class="text-center">
      <v-snackbar v-model="snackbar" :timeout="timeout" color="success" text>
        {{ text }}
      </v-snackbar>
    </div>
    <div class="d-none d-sm-block">
      <div class="content-center mx-auto">
        <v-container class="pt-0">
          <v-row>
            <v-col cols="2" class="px-0 d-flex align-center justify-center">
              Title
            </v-col>
            <v-col cols="10" class="d-flex align-center pb-0">
              <v-text-field
                color="brown lighten-3"
                dense
                solo
                label="제목을 입력해 주세요"
                v-model="title"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col
              cols="2"
              class="pt-0 px-0 d-flex align-center justify-center pb-10"
            >
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
              <div id="emoDiv">
                <input type="hidden" id="hidden-area" :value="hiddenArea" />
                <v-btn class="emoji" @click="addEmoji">⏰</v-btn>
                <v-btn class="emoji" @click="addEmoji">🌞</v-btn>
                <v-btn class="emoji" @click="addEmoji">👀</v-btn>
                <v-btn class="emoji" @click="addEmoji">💩</v-btn>
                <v-btn class="emoji" @click="addEmoji">💬</v-btn>
                <v-btn class="emoji" @click="addEmoji">💭</v-btn>
                <v-btn class="emoji" @click="addEmoji">💯</v-btn>
                <v-btn class="emoji" @click="addEmoji">📝</v-btn>
                <v-btn class="emoji" @click="addEmoji">📞</v-btn>
                <v-btn class="emoji" @click="addEmoji">📢</v-btn>
                <v-btn class="emoji" @click="addEmoji">📷</v-btn>
                <v-btn class="emoji" @click="addEmoji">🔞</v-btn>
                <v-btn class="emoji" @click="addEmoji">🔥</v-btn>
              </div>
            </v-col>
            <v-col cols="12">
              <Editor ref="toastuiEditor" :initialValue="editorText" />
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
                    <v-radio-group v-model="dialogm1" column>
                      <div v-if="todaySchedule.length > 0">
                        <v-radio-group v-model="dialogm1" column>
                          <div v-for="(item, i) in todaySchedule" v-bind:key="i">
                            <v-radio
                              v-bind:label="item.name"
                              v-bind:value="item.id"
                            ></v-radio>
                          </div>
                        </v-radio-group>
                      </div>
                      <div v-else>
                        <div>
                          오늘의 일정이 없습니다.
                        </div>
                      </div>
                    </v-radio-group>
                  </v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions class="d-flex justify-end">
                    <v-btn color="blue darken-1" text @click="dialog = false"
                      >Save</v-btn
                    >
                    <v-btn color="blue darken-1" text @click="dialog = false"
                      >Close</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-col>
            <!-- 표지 색상 고르는 dialog -->
            <v-col cols="12" class="d-flex justify-end">
              <v-dialog v-model="dialogColor" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="pink lighten-2"
                    dark
                    small
                    v-bind="attrs"
                    v-on="on"
                  >
                    <v-icon left>mdi-heart</v-icon>
                    Pick Color !
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>Select Color</v-card-title>
                  <v-divider></v-divider>
                  <v-card-text style="height: 300px;">
                    <v-radio-group v-model="pickColor" column>
                      <v-radio label="red" color="red" value="red"></v-radio>
                      <v-radio
                        label="orange"
                        color="orange"
                        value="orange"
                      ></v-radio>
                      <v-radio
                        label="amber"
                        color="amber"
                        value="amber"
                      ></v-radio>
                      <v-radio
                        label="yellow"
                        color="yellow"
                        value="yellow"
                      ></v-radio>
                      <v-radio label="lime" color="lime" value="lime"></v-radio>
                      <v-radio
                        label="green"
                        color="green"
                        value="green"
                      ></v-radio>
                      <v-radio label="blue" color="blue" value="blue"></v-radio>
                      <v-radio
                        label="purple"
                        color="purple"
                        value="purple"
                      ></v-radio>
                      <v-radio label="pink" color="pink" value="pink"></v-radio>
                      <v-radio
                        label="brown"
                        color="brown"
                        value="brown"
                      ></v-radio>
                      <v-radio label="grey" color="grey" value="grey"></v-radio>
                    </v-radio-group>
                  </v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions>
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="dialogColor = false"
                      >Save</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="dialogColor = false"
                      >Close</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-col>

            <v-col cols="12" class="d-flex justify-end py-0">
              <v-dialog v-model="dialogCategory" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="primary darken-1"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    class="px-5 d-none d-sm-block"
                    small
                  >
                    Category
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title>Categories</v-card-title>
                  <v-divider></v-divider>
                  <v-card-text style="height: 300px;">
                    <div v-if="categories.length > 0">
                      <v-radio-group v-model="category" column>
                        <div v-for="(item, i) in categories" v-bind:key="i">
                          <div v-if="item.cId != 1">
                            <v-radio
                              v-bind:label="item.cName"
                              v-bind:value="item.cId"
                            ></v-radio>
                          </div>
                        </div>
                      </v-radio-group>
                    </div>
                    <div v-else>
                      <div>
                        생성된 폴더가 없습니다.
                      </div>
                    </div>
                  </v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions class="d-flex justify-end">
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="dialogCategory = false"
                      >Save</v-btn
                    >
                    <v-btn
                      color="blue darken-1"
                      text
                      @click="
                        category = 1;
                        dialogCategory = false;
                      "
                      >Close</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-col>

            <v-col cols="12" class="text-end">
              <v-btn
                @click="updateAction"
                small
                color="light-green"
                class="white--text mr-3"
                >SAVE</v-btn
              >
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>

    <!-- 모바일 -->
    <div class="d-block d-sm-none">
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h5">UPDATE</v-col>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold">POST</v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 text-h6">Title</v-col>
          <v-col cols="12"> </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6">Keyword</v-col>
          <v-col cols="12"> </v-col>
        </v-row>
        <v-row class="mt-3">
          <v-col cols="12" class="py-1 text-h6">Content</v-col>
          <v-col cols="12"> </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
import "codemirror/lib/codemirror.css";
import "@toast-ui/editor/dist/toastui-editor.css";
import { Editor } from "@toast-ui/vue-editor";
import moment from "moment";
import AWS from 'aws-sdk';

export default {
  name: "NoteUpdate",
  components: {
    Editor,
  },
  data() {
    return {
      title: "",
      content: "",
      chip2: true,
      dialogm1: "",
      dialog: false,
      keywordinput: "",
      keywords: ["디폴트"],
      editorText: "",
      editorOptions: [
        {
          hideModeSwitch: true,
        },
      ],
      model: [],
      search: null,
      pId: this.$route.query.pId,
      todaySchedule: [],
      hashtags: [],
      nextPId: "",

      snackbar: false,
      text: "My timeout is set to 1500.",
      timeout: 1500,

      dialogColor: false,
      pickColor: "",
      category : '',
      dialogCategory : false,
      categories : [],
      hiddenArea : '',
      groupId: this.$route.query.clId,

      albumBucketName: 'plog-image',
      bucketRegion: 'ap-northeast-2',
      IdentityPoolId: 'ap-northeast-2:4985e7e4-3205-4085-8e76-368daf8dc9b7',

      cnt: null,
    };
  },
  // created 한 뒤 axios로
  created() {
    window.scrollTo(0, 0);
    http
      .get("/schedule/dayList", {
        params: {
          sDate: moment(new Date()).format("YYYY-MM-DD"),
          sId: this.$store.state.auth.user.id,
        },
      })
      .then(({ data }) => {
        data.forEach((element) => {
          this.todaySchedule.push({
            name: element.sName,
            startdate: element.sStartdate,
            enddate: element.sEnddate,
            id: element.sId,
          });
        });
      })
      .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("clienterror")
          } else{
            this.$router.push("/404");
          }                          
        });

    http
      .get("/post/", {
        params: {
          pId: this.pId,
        },
      })
      .then(({ data }) => {
        this.title = data.pTitle;
        const Entities = require("html-entities").XmlEntities;
        const entities = new Entities();
        var v_content = data.pContent;
        this.cnt = v_content.match(/&lt;img src=/g); 
        this.content = entities.decode(v_content);
        this.editorText = this.content;
        this.$refs.toastuiEditor.invoke("setHtml", this.editorText);
      })
      .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("clienterror")
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
          this.model.push(element)
        });
      })
      .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("clienterror")
          } else{
            this.$router.push("/404");
          }                          
        });
      http
      .get("/category/listAll", {
        params: {
          uid: this.$store.state.auth.user.id,
        },
      })
      .then(({ data }) => {
        this.categories = data;
      })
      .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("clienterror")
          } else{
            this.$router.push("/404");
          }                          
        });   
  },

  methods: {
    dataURLtoFile(dataurl, fileName) {
      var arr = dataurl.split(','),
      mime = arr[0].match(/:(.*?);/)[1],
      bstr = atob(arr[1]), 
      n = bstr.length, 
      u8arr = new Uint8Array(n);
      while(n--){
        u8arr[n] = bstr.charCodeAt(n);
      }        
      return new File([u8arr], fileName, {type:mime});
    },

    wordcomplete() {
      if (this.keywordinput.length < 2) {
        this.$dialog.notify.warning("두 글자 이상 입력해주세요 😯", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else {
        this.keywords.push(this.keywordinput);
        this.keywordinput = "";
      }
    },
    deletekeyword() {
      console.log(event.target);
    },
    updateAction() {
      var content = this.$refs.toastuiEditor.invoke("getHtml"); // content를 저장하는 액션 처리
      const Entities = require("html-entities").XmlEntities;
      const entities = new Entities();
      content = entities.encode(content);
      var resContent = '';

      if(this.category == ''){
        this.category = 1
      }

      var numOfHashTag = this.model.length;
      this.hashtags = "";
      for (let i = 0; i < numOfHashTag; i++) {
        this.hashtags += this.model[i] + " ";
      }

      var images = [];
      var i = 0;
      
      if(this.cnt != null) {
        i = this.cnt.length;
      }

      while(content.includes(";base64,")) {
        var start = content.indexOf("data:image");
        var end = content.indexOf("&quot;",start);

        var estart = content.indexOf("data:image");
        estart = content.indexOf("/", estart) + 1;
        var eend = content.indexOf(";",estart);
        var extend = content.substring(estart, eend);

        var image = content.substring(start, end);
        var fileName = this.pId + "_" + i + "." + extend;
        var file = this.dataURLtoFile(image, fileName);
        console.log(file);
        resContent = content.substring(0, start);
        resContent = resContent + "https://plog-image.s3.ap-northeast-2.amazonaws.com/" + fileName + "&quot; width=&quot;400";
        resContent = resContent + content.substring(end);
        console.log(resContent);
        images[i] = file;
        i++;
        content = resContent;
      }

      images.forEach(element => {
        AWS.config.update({
          region : this.bucketRegion,
          credentials: new AWS.CognitoIdentityCredentials({
            IdentityPoolId: this.IdentityPoolId
          })
        }); //s3 configuration

        var s3 = new AWS.S3({
          apiVersion: '2006-03-01',
          params: {
            Bucket: this.albumBucketName
          }
        }); //s3 configuration

        let photoKey = element.name;
        s3.upload({
          Key: photoKey,
          Body: element,
          ACL: 'public-read'
        } , (err) => {
          if(err){
            // console.log(err);
            this.$dialog.notify.error("이미지 업로드 중 에러가 발생하였습니다. 😥", {
              position: "bottom-right",
              timeout: 3000,
            });
            return;
          }
          // alert("성공!");
          // console.log(data);
        });
      });

      http
        .put("/post/", {
          pId: this.pId,
          pTitle: this.title,
          pContent: content,
          pUser: this.$store.state.auth.user.id,
          pSchedule: this.dialogm1,
          pCategory: this.category,
          pColor: this.pickColor,
          pClub:this.groupId,
          pHashtag : this.hashtags,
        })
        .then((Response) => {
          if (Response.data === "success") {
              this.$dialog.notify.success("노트 수정 완료 😄", {
              position: "bottom-right",
              timeout: 3000,
            });
            this.$router.push({path:'/group/detail', query:{clId : this.groupId}}); 
          }
        })
        .catch((error) => {
          if(error.response) {
            this.$router.push("servererror")
          } else if(error.request) {
            this.$router.push("clienterror")
          } else{
            this.$router.push("/404");
          }                          
        });
        
    },

 

    nospace() {
      this.$dialog.notify.warning("공백 없이 단어로 입력해주세요 😥", {
        position: "bottom-right",
        timeout: 3000,
      });
    },

    addEmoji() {
      let emoji = event.target.innerText;
      let toCopy = document.querySelector("#hidden-area");
      toCopy.setAttribute("type", "text");
      toCopy.setAttribute("value", emoji);
      toCopy.select();

      try {
        document.execCommand("copy");
        this.text = emoji + " 가 복사되었습니다!\nCtrl+V 로 사용하세요!";
        this.snackbar = true;
      } catch (err) {
        this.text = "복사에 실패하였습니다 😰";
        this.snackbar = true;
      }

      toCopy.setAttribute("type", "hidden");
      window.getSelection().removeAllRanges();
    },
  },
  watch: {
    model(val) {
      if (val.length > 11) {
        this.$nextTick(() => this.model.pop());
      }
    },
  },
};
</script>

<style scoped>
.content-center {
  width: 85%;
}
</style>
