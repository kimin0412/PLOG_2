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
             <v-col cols="12" class="">
              <Editor
                ref="toastuiEditor"
                height="500px"
                :initialValue="editorText"
              />
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

          <!-- 일정과 연결 -->
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
                    <div v-if="todaySchedule.length > 0">
                      <v-radio-group v-model="dialogm1" column>
                        <div v-for="item in todaySchedule" v-bind:key="item">
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
              <v-btn
                @click="tmpUpdateAction"
                small
                color="grey"
                class="white--text"
                >TEMP SAVE</v-btn
              >
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
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

export default {
  name: "NoteUpdate",
  components: {
    Editor,
  },
  data() {
    return {
      emojiall: [
        '⏰','🌞','👀','💩','💬','💭','💯','📝','📞','📢','📷','🔞','🔥',
      ],
      title: "",
      content: "",
      chip2: true,
      keywordinput: "",
      keywords: ["디폴트"],
      dialogm1: "",
      dialog: false,
      editorText: "",
      editorOptions: [
        {
          hideModeSwitch: true,
        },
      ],
      model: [],
      search: null,
      tpId: this.$route.query.tpId,
      todaySchedule: [],
      hashtags: "",
      nextPId: "",

      snackbar: false,
      text: "My timeout is set to 1500.",
      timeout: 1500,

      pickColor: 'indigo',
      colors: ['red', 'pink', 'purple', 'indigo', 'light-blue', 'green', 'lime', 'yellow', 'orange', 'brown', 'grey'],
      hiddenArea : '',

      dialogCategory: false,
      category: "",
      categories: [],
    };
  },
  // created 한 뒤 axios로
  created() {
    window.scrollTo(0, 0);
    http
      .get("/tp/", {
        params: {
          tpId: this.tpId,
        },
      })
      .then(({ data }) => {
        this.title = data.tpTitle;
        //this.model = data.model;
        const Entities = require("html-entities").XmlEntities;
        const entities = new Entities();
        var v_content = data.tpContent;
        this.content = entities.decode(v_content);
        console.log(this.content);
        this.editorText = this.content;
        this.$refs.toastuiEditor.invoke("setHtml", this.editorText);
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

    http
      .get("/schedule/dayList", {
        params: {
          sId: this.$store.state.auth.user.id,
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

      http.get("/category/listAll", {
        params: {
          uid: this.$store.state.auth.user.id,
        },
      })
      .then(({ data }) => {
        this.categories = data;
      });
  },

  methods: {
    wordcomplete() {
      if (this.keywordinput.length < 2) {
        alert("두 글자 이상 입력해주세요");
      } else {
        this.keywords.push(this.keywordinput);
        this.keywordinput = "";
      }
    },
    deletekeyword() {
      console.log(event.target);
    },
    createAction() {
      var content = this.$refs.toastuiEditor.invoke("getHtml"); // content를 저장하는 액션 처리
      const Entities = require("html-entities").XmlEntities;
      const entities = new Entities();
      content = entities.encode(content);

      var numOfHashTag = this.model.length;
      this.hashtags = "";
      for (let i = 0; i < numOfHashTag; i++) {
        this.hashtags += this.model[i] + " ";
      }

      http
        .post("/post/", {
          pTitle: this.title,
          pContent: content,
          pUser: this.$store.state.auth.user.id,
          pSchedule: this.dialogm1,
          pCategory: this.category,
          pColor: this.pickColor,
          pClub:1,
          pHashtag : this.hashtags
        })
        .then((response) => {
          if (response.data.data === "success") {
            this.$dialog.notify.success("등록 완료 😤", {
                    position: "bottom-right",
                    timeout: 3000,
                  });
            this.$router.push("/note");
            http
              .delete("/tp/", {
                params: {
                  tpId: this.tpId,
                },
              })
              .then((response) => {
                if (response.data === "success") {
                  this.$dialog.notify.success("임시노트 삭제 완료 😤", {
                    position: "bottom-right",
                    timeout: 3000,
                  });
                  this.$router.go();
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

    tmpUpdateAction() {
      var content = this.$refs.toastuiEditor.invoke("getHtml"); // content를 저장하는 액션 처리
      const Entities = require("html-entities").XmlEntities;
      const entities = new Entities();
      content = entities.encode(content);
      console.log(content);

      http
        .put("/tp/", {
          tpId: this.tpId,
          tpTitle: this.title,
          tpContent: content,
          tpUser: this.$store.state.auth.user.id,
        })
        .then((Response) => {
          if (Response.data === "success") {
            this.$dialog.notify.warning("임시 수정 완료 😤", {
              position: "bottom-right",
              timeout: 3000,
            });
            this.$router.push("/note");
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
    nospace() {
      alert("공백 없이 단어로 입력해주세요");
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
