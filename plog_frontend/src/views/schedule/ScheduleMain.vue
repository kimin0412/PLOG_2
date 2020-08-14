<template>
  <div>
    <div class="d-none d-sm-block">
      <!-- 화면클때만 보이는 부분 - 캘린더 -->
      <div class="content-center mx-auto">
        <v-row class="fill-height">
          <v-col cols="12">
            <v-sheet height="64">
              <v-toolbar flat color="white">
                <v-btn
                  outlined
                  small
                  class="mr-4"
                  color="grey darken-2"
                  @click="setToday"
                >
                  Today
                </v-btn>
                <v-btn fab text small color="grey darken-2" @click="prev">
                  <v-icon small>mdi-chevron-left</v-icon>
                </v-btn>
                <v-btn fab text small color="grey darken-2" @click="next">
                  <v-icon small>mdi-chevron-right</v-icon>
                </v-btn>
                <v-toolbar-title v-if="$refs.calendar">
                  {{ $refs.calendar.title }}
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-row>
                  <!-- 스케줄 생성 모달 & 옵션선택지(v-menu) -->
                  <v-dialog v-model="dialog2" persistent max-width="600px">
                    <template v-slot:activator="{ on, attrs }">
                      <v-col
                        cols="12"
                        class="d-none d-sm-block py-1 text-right"
                      >
                        <v-menu bottom right>
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn
                              outlined
                              color="grey darken-2"
                              v-bind="attrs"
                              v-on="on"
                              small
                              class="mr-4"
                            >
                              <span>{{ groupName }}</span>
                              <v-icon right>mdi-menu-down</v-icon>
                            </v-btn>
                          </template>
                          <v-list v-for="(item, i) in myClub" :key="i">
                            <v-list-item
                              @click="
                                type = item.id;
                                (groupName = item.name),
                                  (groupColor = item.color),
                                  updateRange();
                              "
                            >
                              <v-list-item-title>{{
                                item.name
                              }}</v-list-item-title>
                            </v-list-item>
                          </v-list>
                        </v-menu>
                        <v-btn
                          color="blue darken-3"
                          small
                          dark
                          v-bind="attrs"
                          v-on="on"
                        >
                          <v-icon class="mr-3" small>mdi-calendar</v-icon> 일정
                          등록
                        </v-btn>
                      </v-col>
                      <v-col cols="12" class="d-sm-none py-1 text-right">
                        <v-btn color="info" small dark v-bind="attrs" v-on="on">
                          <v-icon>mdi-pencil</v-icon>
                        </v-btn>
                      </v-col>
                    </template>
                    <v-card>
                      <v-card-title>
                        <span v-if="type < 2" class="headline"
                          >New Schedule</span
                        >
                        <span v-else class="headline"
                          >New {{ groupName }} Schedule</span
                        >
                      </v-card-title>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="6" class="text-center">
                              <v-date-picker
                                v-model="dates"
                                range
                                width="200"
                              ></v-date-picker>
                            </v-col>
                            <v-col cols="12" sm="6">
                              <v-col cols="12" class="text-center">
                                <v-text-field
                                  label="Schedule Name*"
                                  v-model="sName"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  v-model="dateRangeText"
                                  label="Date range"
                                  readonly
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field
                                  label="Schedule Description*"
                                  v-model="sContent"
                                  required
                                ></v-text-field>
                              </v-col>

                              <v-col class="d-flex" cols="12" v-if="type < 2">
                                <v-select
                                  v-model="pickColor"
                                  :items="colors"
                                  filled
                                  label="Pick Color"
                                  full-width
                                ></v-select>
                              </v-col>
                              <v-col v-else>
                                <v-text-field
                                  label="color"
                                  v-model="groupColor"
                                  required
                                ></v-text-field>
                              </v-col>
                              <v-col cols="12" class="text-right">
                                <small>*indicates required field</small>
                              </v-col>
                            </v-col>
                          </v-row>
                        </v-container>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <small>*indicates required field</small>
                        <v-btn
                          color="blue darken-1"
                          text
                          @click="dialog2 = false"
                          >Close</v-btn
                        >
                        <v-btn
                          color="blue darken-1"
                          text
                          @click="createSchedule"
                          >등록하기</v-btn
                        >
                      </v-card-actions>
                    </v-card>
                  </v-dialog>

                  <!-- 상세스케줄 모달 -->
                  <v-dialog
                    v-model="scheduleDetailOpen"
                    :close-on-content-click="false"
                    :activator="selectedElement"
                    offset-x
                    class="detailmodal"
                    persistent
                    max-width="600px"
                  >
                    <v-card>
                      <v-card-title>
                        <span class="headline">{{ detailName }}</span>
                      </v-card-title>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12">
                              <v-text-field
                                label="Schedule Description*"
                                v-model="detailContent"
                                readonly
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6">
                              <v-text-field
                                v-model="detailDates"
                                label="Date range"
                                readonly
                              ></v-text-field>
                            </v-col>

                            <v-card class="mx-auto">
                              <div v-for="(item, i) in postOfSchedule" :key="i">
                                <v-card-text class="d-flex justify-center py-0">
                                  <div>
                                    {{ item.name }}
                                    <router-link
                                      :to="{
                                        path: 'note/detail',
                                        query: { pId: item.id },
                                      }"
                                      class="py-0 text-center text-h6"
                                    >
                                      <v-btn
                                        text
                                        color="blue lighten-2  ml-auto"
                                        >click</v-btn
                                      >
                                    </router-link>
                                  </div>
                                </v-card-text>
                              </div>
                            </v-card>
                          </v-row>
                        </v-container>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                          color="blue darken-1"
                          text
                          @click="updateDialogOpen"
                          >수정하기</v-btn
                        >
                        <v-btn
                          color="blue darken-1"
                          text
                          @click="deleteSchedule"
                          >삭제하기</v-btn
                        >
                        <v-btn
                          color="blue darken-1"
                          text
                          @click="scheduleDetailOpen = false"
                          >Close</v-btn
                        >
                      </v-card-actions>
                    </v-card>
                  </v-dialog>

                  <!-- 스케줄 수정 모달 -->
                  <v-dialog v-model="dialogUpdate" persistent max-width="600px">
                    <v-card>
                      <v-card-title>
                        <span class="headline">Update Schedule</span>
                      </v-card-title>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="6" md="4">
                              <v-text-field
                                label="Schedule Name*"
                                v-model="uName"
                                required
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="8">
                              <v-date-picker
                                v-model="uDates"
                                range
                              ></v-date-picker>
                            </v-col>

                            <v-col cols="12" sm="6">
                              <v-text-field
                                v-model="uDateRangeText"
                                label="Date range"
                                readonly
                              ></v-text-field>
                            </v-col>

                            <v-col cols="12">
                              <v-text-field
                                label="Schedule Description*"
                                v-model="uContent"
                                required
                              ></v-text-field>
                            </v-col>
                          </v-row>
                        </v-container>
                        <small>*indicates required field</small>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="closeUpdate"
                          >Close</v-btn
                        >
                        <v-btn
                          color="blue darken-1"
                          text
                          @click="updateSchedule"
                          >수정하기</v-btn
                        >
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-row>
              </v-toolbar>
            </v-sheet>
            <v-sheet height="530">
              <v-calendar
                ref="calendar"
                v-model="focus"
                color="primary"
                :events="events"
                :event-color="getEventColor"
                :type="month"
                @click:more="viewDay"
                @click:date="viewDay"
                @change="updateRange"
                :event-more="true"
              ></v-calendar>
            </v-sheet>
            <v-col cols="12" class="text-center grey--text">
              <small
                >날짜(숫자)를 클릭하시면, 해당 날짜의 일정과 기록이 하단에
                나타납니다 :)</small
              >
            </v-col>
          </v-col>
          <v-col cols="12">
            <v-row class="mt-10">
              <v-col cols="12" class="text-h6 py-1">Today's schedule</v-col>
              <v-col cols="12">
                <div v-for="(item, i) in dailySchedule" :key="i">
                  <div v-if="dailySchedule.length != 0">
                    <v-card class="mx-auto">
                      <v-card-text class="d-flex justify-space-between  py-0">
                        <div class="pt-2">
                          <v-icon class="mr-3 " small>mdi-check</v-icon
                          ><span class="text-subtitle-2 font-weight-bold">{{
                            item.name
                          }}</span>
                          : {{ item.content }}
                        </div>
                        <v-btn
                          @click="scheduleDetail(item.id)"
                          text
                          color="blue lighten-2  ml-auto"
                          >DETAIL</v-btn
                        > </v-card-text
                      ><v-divider></v-divider>
                    </v-card>
                  </div>
                  <div v-else>
                    <div>
                      작성한 일정이 없습니다.
                    </div>
                  </div>
                </div>
              </v-col>
            </v-row>
            <v-row class="mt-10">
              <v-col cols="12" class="text-h6 py-1">Daily Logs</v-col>
              <v-col cols="12">
                <div v-for="(item, i) in dailyPost" :key="i">
                  <div v-if="dailyPost.length != 0">
                    <router-link
                      :to="{ path: 'note/detail', query: { pId: item.id } }"
                      class="py-0 text-center text-h6 text-decoration-none"
                      ><v-card class="mx-auto">
                        <v-card-text class="d-flex justify-space-between py-0">
                          <div class="pt-2">
                            <v-icon class="mr-3" small>mdi-pencil</v-icon
                            >{{ item.name }}
                          </div>
                          <v-btn text color="blue lighten-2  ml-auto"
                            >click</v-btn
                          > </v-card-text
                        ><v-divider></v-divider> </v-card
                    ></router-link>
                  </div>
                  <div v-else><div>작성한 노트가 없습니다.</div></div>
                </div>
              </v-col>
            </v-row>
          </v-col>
        </v-row>
      </div>
    </div>
    <div class="d-block d-sm-none">
      <!-- 피커있는부분 -->
      <v-container>
        <v-row>
          <v-col cols="12" class="py-1 text-h5">DAILY</v-col>
          <v-col cols="12" class="py-1 text-h4 font-weight-bold"
            >SCHEDULE</v-col
          >
        </v-row>
        <v-row class="mt-10 px-0">
          <!-- 스케줄 생성 모달 -->
          <v-dialog v-model="dialog" persistent max-width="600px">
            <template v-slot:activator="{ on, attrs }">
              <v-col cols="12" class="d-none d-sm-block py-1 text-right px-0">
                <v-btn color="info" small dark v-bind="attrs" v-on="on">
                  일정 등록
                </v-btn>
              </v-col>
              <v-col cols="12" class="d-sm-none py-1 text-right px-0">
                <v-btn color="light-green" small dark v-bind="attrs" v-on="on">
                  <v-icon class="mr-3" small>mdi-calendar</v-icon> 일정 생성
                </v-btn>
              </v-col>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">New Schedule</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" class="text-center">
                      <v-date-picker
                        v-model="dates"
                        range
                        width="200"
                      ></v-date-picker>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-col cols="12" class="text-center">
                        <v-text-field
                          label="Schedule Name*"
                          v-model="sName"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="dateRangeText"
                          label="Date range"
                          readonly
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          label="Schedule Description*"
                          v-model="sContent"
                          required
                        ></v-text-field>
                      </v-col>
                      <v-col class="d-flex" cols="12">
                        <v-select
                          v-model="pickColor"
                          :items="colors"
                          filled
                          label="Pick Color"
                          full-width
                        >
                        </v-select>
                      </v-col>
                      <v-col cols="12" class="text-right">
                        <small>*indicates required field</small>
                      </v-col>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false"
                  >Close</v-btn
                >
                <v-btn color="blue darken-1" text @click="createSchedule"
                  >등록하기</v-btn
                >
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
        <v-row justify="center">
          <v-date-picker
            v-model="date"
            :events="functionEvents"
            :picker-date.sync="pickerDate"
            @click:date="viewDayPicker"
            elevation="5"
            full-width
          ></v-date-picker>
        </v-row>
        <v-col cols="12" class="text-center grey--text text-caption">
          <small>날짜(숫자) 클릭 > 일정과 기록이 하단에 나타납니다 :)</small>
        </v-col>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 text-h6">Daily Task</v-col>
          <v-col cols="12">
            <v-card class="mx-auto"
              ><div v-for="(item, i) in dailySchedule" :key="i">
                <v-card-text class="d-flex justify-space-between py-0">
                  <div class="pt-2">
                    <v-icon small class="mr-1">mdi-check</v-icon>
                    {{ item.name }}
                  </div>
                  <v-btn
                    @click="scheduleDetail(item.id)"
                    text
                    color="blue lighten-2  ml-auto"
                    >go</v-btn
                  >
                </v-card-text>
              </div></v-card
            >
          </v-col>
        </v-row>
        <v-row class="mt-10">
          <v-col cols="12" class="py-1 text-h6">Daily Log</v-col>
          <v-col cols="12">
            <div v-for="(item, i) in dailyPost" :key="i">
              <router-link
                :to="{ path: 'note/detail', query: { pId: item.id } }"
                class="py-0 text-center text-h6 text-decoration-none"
                ><v-card class="mx-auto">
                  <v-card-text class="d-flex justify-space-between py-0">
                    <div class="pt-2">
                      <v-icon class="mr-3" small>mdi-pencil</v-icon
                      >{{ item.name }}
                    </div>
                    <v-btn text color="blue lighten-2  ml-auto">click</v-btn>
                  </v-card-text>
                </v-card></router-link
              ><v-divider></v-divider>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common.js";
export default {
  name: "schedule",
  data: () => ({
    dialog: false,
    dialog2: false,
    dialogUpdate: false,
    arrayEvents: null,
    date: new Date().toISOString().substr(0, 10),
    focus: "",
    type: 0,
    typeToLabel: {
      "My Schedule": "My Schedule",
    },
    month: "month",
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    events: [],
    colors: [
      "red",
      "pink",
      "purple",
      "indigo",
      "light-blue",
      "green",
      "lime",
      "yellow",
      "orange",
      "brown",
      "grey",
    ],
    names: [
      "Meeting",
      "Holiday",
      "PTO",
      "Travel",
      "Event",
      "Birthday",
      "Conference",
      "Party",
    ],
    pickerDate: null,

    start_selected: false,
    end_selected: false,
    startDate: "",
    endDate: "",
    textOpen: false,
    dates: [],

    scheduleDays: [],
    postDays: [],
    scheduleDetailOpen: false,
    dailySchedule: [],
    dailyPost: [],
    detailScheduleName: "",

    detailName: "",
    detailContent: "",
    detailDates: "",
    detailEDate: "",
    detailSDate: "",

    sName: "",
    sContent: "",

    uName: "",
    uContent: "",
    uDates: [],

    postOfSchedule: [],
    scheduleDetailId: "",
    pickColor: "red",

    myClub: [],
    groupName: "All Schedule",
    groupColor: "",
  }),

  created() {
    //오늘의 일정, 포스트 가져오는 부분
    this.dailySchedule = [];
    http
      .get("/schedule/dayList", {
        params: {
          sId: this.$store.state.auth.user.id,
          sDate: this.date,
        },
      })
      .then(({ data }) => {
        data.forEach((element) => {
          this.dailySchedule.push({
            name: element.sName,
            content: element.sContent,
            id: element.sId,
          });
        });
      });

    http
      .get("/schedule/dayPost", {
        params: {
          sId: this.$store.state.auth.user.id,
          sDate: this.date,
        },
      })
      .then(({ data }) => {
        data.forEach((element) => {
          this.dailyPost.push({ name: element.pTitle, id: element.pId });
        });
      });

    this.myClub = [];
    http
      .get("/club/list", {
        params: {
          uId: this.$store.state.auth.user.id,
        },
      })
      .then(({ data }) => {
        this.myClub.push({ name: "All Schedule", id: 0 });
        this.myClub.push({ name: "My Schedule", id: 1 });
        data.forEach((element) => {
          this.myClub.push({
            name: element.clName,
            id: element.clId,
            color: element.clColor,
          });
        });
      });
  },

  computed: {
    dateRangeText() {
      if (this.dates[0] > this.dates[1]) {
        this.$dialog.notify.warning(
          "종료날짜를 시작날짜 이후로 정해주세요. 😥",
          {
            position: "bottom-right",
            timeout: 3000,
          }
        );
        return "";
      } else return this.dates.join(" ~ ");
    },

    uDateRangeText() {
      return this.uDates.join(" ~ ");
    },
  },

  watch: {
    pickerDate(val) {
      this.scheduleDays = [];
      http
        .get("/schedule/monthSchedulePicker", {
          params: {
            sId: this.$store.state.auth.user.id,
            sDate: val,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.scheduleDays.push(element);
          });
        });

      this.postDays = [];
      http
        .get("/schedule/monthPostPicker", {
          params: {
            sId: this.$store.state.auth.user.id,
            sDate: val,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.postDays.push(element);
          });
        });
    },
  },
  mounted() {
    this.$refs.calendar.checkChange();
  },

  methods: {
    functionEvents(date) {
      const [, , day] = date.split("-");
      if (this.scheduleDays.includes(parseInt(day, 10))) {
        if (this.postDays.includes(parseInt(day, 10))) return ["red", "cyan"];
        else return ["cyan"];
      } else if (this.postDays.includes(parseInt(day, 10))) return ["red"];
      return false;
    },

    viewDayPicker() {
      this.dailySchedule = [];
      http
        .get("/schedule/dayList", {
          params: {
            sId: this.$store.state.auth.user.id,
            sDate: this.date,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.dailySchedule.push({
              name: element.sName,
              content: element.sContent,
              id: element.sId,
            });
          });
        });

      this.dailyPost = [];
      http
        .get("/schedule/dayPost", {
          params: {
            sId: this.$store.state.auth.user.id,
            sDate: this.date,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.dailyPost.push({ name: element.pTitle, id: element.pId });
          });
        });
    },

    viewDay({ date }) {
      this.dailySchedule = [];
      http
        .get("/schedule/dayList", {
          params: {
            sId: this.$store.state.auth.user.id,
            sDate: date,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.dailySchedule.push({
              name: element.sName,
              content: element.sContent,
              id: element.sId,
            });
          });
        });

      this.dailyPost = [];
      http
        .get("/schedule/dayPost", {
          params: {
            sId: this.$store.state.auth.user.id,
            sDate: date,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.dailyPost.push({ name: element.pTitle, id: element.pId });
          });
        });
    },

    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },

    updateRange() {
      const events = [];
      const allDay = this.rnd(0, 3) === 0;

      if (this.type == 0) {
        http.get("/schedule/monthList/All", {
            params: {
              sId: this.$store.state.auth.user.id,
              sDate: this.$refs.calendar.title,
            },
          })
          .then(({ data }) => {
            data.forEach((element) => {
              //alert(element.s_startdate)
              if (this.type == 0) {
                events.push({
                  id: element.sId,
                  name: element.sName,
                  start: element.sStartdate.substr(0, 10),
                  end: element.sEnddate,
                  color: element.sColor + " lighten-2",
                });
              } else {
                if (element.sClub < 2) {
                  events.push({
                    id: element.sId,
                    name: element.sName,
                    start: element.sStartdate.substr(0, 10),
                    end: element.sEnddate,
                    color: element.sColor + " lighten-2",
                    timed: !allDay,
                  });
                }
              }
            });
          });
      } else {
        //클럽인경우
        http
          .get("/club/monthList", {
            params: {
              clId: this.type,
              sDate: this.$refs.calendar.title,
            },
          })
          .then(({ data }) => {
            data.forEach((element) => {
              //alert(element.s_startdate)
              if (element.sClub == this.type) {
                events.push({
                  id: element.sId,
                  name: element.sName,
                  start: element.sStartdate.substr(0, 10),
                  end: element.sEnddate,
                  color: element.sColor + " lighten-2",

                  // timed: !allDay,
                });
              }
            });
          });
      }

      this.events = events;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },

    createSchedule() {
      this.dialog = false;
      this.dialog2 = false;

      if (this.type < 2) {
        http
          .post("/schedule/insert", {
            sName: this.sName,
            sContent: this.sContent,
            sStartdate: this.dates[0],
            sEnddate: this.dates[1],
            sColor: this.pickColor,
            sUser: this.$store.state.auth.user.id,
            sClub: 1,
            // sColor : this.pickColor,
          })
          .then(({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data.data == "success") {
              msg = "등록이 완료되었습니다.";
              this.$router.go();
              alert(msg);
              this.$dialog.notify.success(msg + " 😥", {
                position: "bottom-right",
                timeout: 3000,
              });
              // this.$router.go();
            } else {
              this.$dialog.notify.error(msg + " 😥", {
                position: "bottom-right",
                timeout: 3000,
              });
            }
            // alert(msg);
          });
      } else {
        //클럽인경우
        http
          .post("/schedule/insert", {
            sName: this.sName,
            sContent: this.sContent,
            sStartdate: this.dates[0],
            sEnddate: this.dates[1],
            sColor: this.groupColor,
            sUser: this.$store.state.auth.user.id,
            sClub: this.type,
          })
          .then(({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data.data == "success") {
              msg = "등록이 완료되었습니다.";
              this.$dialog.notify.success(msg + " 😥", {
                position: "bottom-right",
                timeout: 3000,
              });
              this.$router.go();
            } else {
              this.$dialog.notify.error(msg + " 😥", {
                position: "bottom-right",
                timeout: 3000,
              });
            }
            // alert(msg);
          });
      }
    },

    scheduleDetail(val) {
      this.scheduleDetailOpen = true;

      http
        .get("/schedule/select", {
          params: {
            sId: val,
            sUser: this.$store.state.auth.user.id,
          },
        })
        .then(({ data }) => {
          this.detailName = data.sName;
          this.detailContent = data.sContent;
          this.detailSDate = data.sStartdate.substr(0, 10);
          this.detailEDate = data.sEnddate.substr(0, 10);
          this.detailDates = this.detailSDate + " ~ " + this.detailEDate;
        });

      this.postOfSchedule = [];
      http
        .get("/schedule/selectPost", {
          params: {
            sId: val,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.postOfSchedule.push({ name: element.pTitle, id: element.pId });
          });
        });

      this.scheduleDetailId = val;
    },

    updateDialogOpen() {
      this.uName = this.detailName;
      this.uContent = this.detailContent;
      this.uDates[0] = this.detailSDate;
      this.uDates[1] = this.detailEDate;
      this.dialogUpdate = true;
    },

    updateSchedule() {
      http
        .post("/schedule/update", {
          sId: this.scheduleDetailId,
          sName: this.uName,
          sContent: this.uContent,
          sStartdate: this.uDates[0],
          sEnddate: this.uDates[1],
          sUser: this.$store.state.auth.user.id,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data.data == "success") {
            msg = "수정이 완료되었습니다.";
            this.$router.go();
          }
          alert(msg);
        });
      this.dialogUpdate = false;
    },

    closeUpdate() {
      this.dialogUpdate = false;
    },

    deleteSchedule() {
      var ok = confirm("삭제할거에요?");
      if (ok) {
        http
          .post("/schedule/delete", {
            sId: this.scheduleDetailId,
            sName: this.uName,
            sContent: this.uContent,
            sStartdate: this.uDates[0],
            sEnddate: this.uDates[1],
          })
          .then(({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data.data == "success") {
              msg = "삭제가 완료되었습니다.";
              this.$router.go();
            }
            alert(msg);
          })
          .catch(() => {
            alert("일정이 있으면 삭제가 안됩니다.");
          });
        this.dialogUpdate = false;
      }
    },

    viewSchedule({ nativeEvent, event }) {
      this.scheduleDetailOpen = true;

      http
        .get("/schedule/select", {
          params: {
            sId: event.id,
            sUser: this.$store.state.auth.user.id,
          },
        })
        .then(({ data }) => {
          this.detailName = data.sName;
          this.detailContent = data.sContent;
          this.detailSDate = data.sStartdate.substr(0, 10);
          this.detailEDate = data.sEnddate.substr(0, 10);
          this.detailDates = this.detailSDate + " ~ " + this.detailEDate;
        });

      this.postOfSchedule = [];
      http
        .get("/schedule/selectPost", {
          params: {
            sId: event.id,
          },
        })
        .then(({ data }) => {
          data.forEach((element) => {
            this.postOfSchedule.push({ name: element.pTitle, id: element.pId });
          });
        });

      this.scheduleDetailId = event.id;

      nativeEvent.stopPropagation();
    },
  },
};
</script>

<style scoped>
.content-center {
  width: 85%;
}
.detailmodal {
  position: absolute;
  top: 50vh;
  left: 50vw;
}
/* .pl-1 > strong {
  visibility: hidden !important;
} */
.v-event-more .pl-1 {
  background-color: grey !important;
}
</style>
