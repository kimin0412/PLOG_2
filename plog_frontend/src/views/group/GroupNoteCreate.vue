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
            <v-col cols="12" class="px-0">
              <div id="emoDiv">
                <v-sheet
                  class="mx-0"
                >
                  <v-slide-group show-arrows mandatory>
                    <input type="hidden" id="hidden-area" :value="hiddenArea" />
                    <v-slide-item
                      v-for="(emo,i) in emojiall"
                      :key="i"
                      v-slot:default="{ active, toggle }"
                    >
                      <v-btn
                        class="mx-1 px-1"
                        :input-value="active"
                        active-class="yellow darken-2 white--text"
                        depressed
                        rounded
                        @click="toggle"
                      >
                        <v-btn class="emoji transparent" elevation="0" rounded @click="addEmoji">{{emo}}</v-btn>
                      </v-btn>
                    </v-slide-item>
                  </v-slide-group>
                </v-sheet>
              </div>
            </v-col>
            <v-col cols="12">
              <Editor ref="toastuiEditor1" height="500px" />
            </v-col>
          </v-row>
          <v-row class="my-2">
                        <v-col cols="2" class="px-0 pb-0 mx-0 my-0">
                            <v-card :color="pickColor" class="py-2 transparent--text">색</v-card>
                        </v-col>
                        <v-col cols="10">
                            <v-select v-model="pickColor"
                                        :items="colors"
                                        filled
                                        dense
                                        label="노트의 색깔을 골라주세요"
                                        full-width>
                            </v-select>
                        </v-col>
          </v-row>
          <v-row>
            <!-- 일정과 연결 -->
            <v-col cols="12" class="d-flex justify-end py-0">
              <v-dialog v-model="dialog" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="amber darken-2"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    class="mb-2 px-5 d-none d-sm-block"
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
                      <div v-for="(item, i) in todaySchedule" :key="i">
                        <v-radio
                          v-bind:label="item.name"
                          v-bind:value="item.id"
                        ></v-radio>
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
            
            <!-- 폴더안에 넣기 -->
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
                          <v-radio
                            v-bind:label="item.cName"
                            v-bind:value="item.cId"
                          ></v-radio>
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
                @click="createAction"
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
          <v-col cols="12" class="py-1 text-h5">NEW</v-col>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold">POST</v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 text-h6">Title</v-col>
          <v-col cols="12">
            <v-text-field
              color="brown lighten-3"
              dense
              solo
              label="제목을 입력해 주세요"
              v-model="title"
            ></v-text-field>
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
            <Editor ref="toastuiEditor2" height="500px"/>
          </v-col>
        </v-row>

        <v-row class="my-2">
                        <v-col cols="2" class="px-0 pb-0 mx-0 my-0">
                            <v-card :color="pickColor" class="py-2 transparent--text">색</v-card>
                        </v-col>
                        <v-col cols="10">
                            <v-select v-model="pickColor"
                                        :items="colors"
                                        filled
                                        dense
                                        label="노트의 색깔을 골라주세요"
                                        full-width>
                            </v-select>
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
          
            <!-- 폴더안에 넣기 -->
            <v-col cols="12" class="d-flex justify-end py-0">
              <v-dialog v-model="dialogCategory" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    color="primary darken-1"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    class="px-5"
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
          <v-col cols="12" class="text-end pb-10">
            <v-btn
              @click="createAction"
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
</template>

<script>
import http from "@/util/http-common.js";
import "codemirror/lib/codemirror.css";
import "@toast-ui/editor/dist/toastui-editor.css";
import { Editor } from "@toast-ui/vue-editor";
import moment from "moment";
import AWS from 'aws-sdk';

export default {
  components: {
    Editor,
  },
  data() {
    return {
      emojiall: [
        '⏰','🌞','👀','💩','💬','💭','💯','📝','📞','📢','📷','🔞','🔥',
      ],
      hiddenArea: "",
      title: "",
      content: "",
      chip2: true,
      dialogm1: "",
      dialog: false,
      editorText: [
        {
          text: "This is initialValue.",
        },
      ],
      editorOptions: [
        {
          hideModeSwitch: true,
        },
      ],
      model: [],
      search: null,
      todaySchedule: [],
      hashtags: "",
      nextPId: "",
      dialogCategory: false,
      category: "",
      categories: [],

      snackbar: false,
      text: "My timeout is set to 1500.",
      timeout: 1500,

      pickColor: 'indigo',
      colors: ['red', 'pink', 'purple', 'indigo', 'light-blue', 'green', 'lime', 'yellow', 'orange', 'brown', 'grey'],

      groupId : this.$route.query.groupId,

      albumBucketName: 'plog-image',
      bucketRegion: 'ap-northeast-2',
      IdentityPoolId: 'ap-northeast-2:4985e7e4-3205-4085-8e76-368daf8dc9b7',

    };
  },

  created() {
    if(this.groupId != undefined) {
      http
      .get("/hashtag/getnextPostId", {
        params: {
          uId: this.$store.state.auth.user.id,
        },
      })
      .then(({ data }) => {
        this.nextPId = data.hId;
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

      http.get("/schedule/club/dayList", {
        params: {
          sClub: this.groupId,
          sDate: moment(new Date()).format("YYYY-MM-DD"),
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
      });

      http.get("/category/club/listAll", {
        params: {
          cClub: this.groupId,
        },
      })
      .then(({ data }) => {
        this.categories = data;
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

    createAction() {
      if (!this.title.trim()) {
        this.$dialog.notify.warning("제목을 입력 해주세요 😥", {
          position: "bottom-right",
          timeout: 3000,
        });
      } else {
        var content1 = this.$refs.toastuiEditor1.invoke("getHtml");
        var content2 = this.$refs.toastuiEditor2.invoke("getHtml");
        var content = null;
        if (content1 == "") {
          content = content2;
        } else {
          content = content1;
        }
        const Entities = require("html-entities").XmlEntities;
        const entities = new Entities();
        content = entities.encode(content);
        var resContent = content;

        var numOfHashTag = this.model.length;
        this.hashtags = "";
        for (let i = 0; i < numOfHashTag; i++) {
          this.hashtags += this.model[i] + " ";
        }

        http
          .post("/post/", {
            pId: this.nextPId,
            pTitle: this.title,
            pContent: '',
            pUser: this.$store.state.auth.user.id,
            pSchedule: this.dialogm1,
            pCategory: this.category,
            pColor: this.pickColor,
            pClub : this.groupId,
            pHashtag : this.hashtags,
          })
          .then(({ data }) => {
            if (data.data == "success") {
              var images = [];
              var i = 0;
              while(content.includes(";base64,")) {
                var start = content.indexOf("data:image");
                var end = content.indexOf("&quot;",start);

                var estart = content.indexOf("data:image");
                estart = content.indexOf("/", estart) + 1;
                var eend = content.indexOf(";",estart);
                var extend = content.substring(estart, eend);

                var image = content.substring(start, end);
                var fileName = data.temp + "_" + i + "." + extend;
                var file = this.dataURLtoFile(image, fileName);
                //console.log(file);

                resContent = content.substring(0, start);
                resContent = resContent + "https://plog-image.s3.ap-northeast-2.amazonaws.com/" + fileName + "&quot; width=&quot;400";
                resContent = resContent + content.substring(end);
                //console.log(resContent);
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
                pId: data.temp,
                pTitle: this.title,
                pContent: resContent,
                pUser: this.$store.state.auth.user.id,
                pSchedule: this.dialogm1,
                pCategory: this.category,
                pColor: this.pickColor,
                pClub : this.groupId,
                pHashtag : this.hashtags
              })
              .then((Response) => {
                if (Response.data === "success") {
                  this.$dialog.notify.success("노트 등록 완료 😃", {
                    position: "bottom-right",
                    timeout: 3000,
                  });
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
          });
          //     this.$dialog.notify.success("노트 등록 완료 😃", {
          //       position: "bottom-right",
          //       timeout: 3000,
          //     });
          //     this.$router.push({path:'/group/detail2', query:{clId : this.groupId}}); 
          //   }
          // })
          // .catch((error) => {
          //   if(error.response) {
          //     this.$router.push("servererror")
          //   } else if(error.request) {
          //     this.$router.push("error")
          //   } else{
          //     this.$router.push("/404");
          //   }                          
          // });
      }

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
.emoji {
  float: left;
  font-size: 20px;
}
</style>
