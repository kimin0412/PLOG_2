<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-row class="mt-5 pt-10">
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light">PLOG, how to</div>
            </v-col>
            <v-col cols="2"></v-col>
            <v-col cols="8" class="text-center grey--text">
              PLOG는 마크다운 양식을 지원하는 Toast 에디터를 사용합니다. 
            </v-col>
            <v-col cols="2"></v-col>
          </v-row>
          <v-row class="mt-15 pt-5">
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light">What is Markdown?</div>
            </v-col>
            <v-col cols="12" class="text-center grey--text">
                <a href="https://www.markdownguide.org/" target="_blank"><img src="@/assets/howto/markdown.jpg" alt="" width="100%"></a>
            </v-col>
            <v-col cols="1"></v-col>
            <v-col cols="10" class="text-center grey--text">
              마크다운이란, 텍스트 기반의 마크업 언어로 글을 보다 읽고 작성할 수 있게 돕습니다. <br>문법이 간단하며 쉽게 HTML변환이 가능해 최근들어 크게 각광받고 있습니다.
            </v-col>
            <v-col cols="1"></v-col>
            <v-col cols="12" class="py-0 text-center"><a href="https://guides.github.com/features/mastering-markdown/" target="_blank" class="text-decoration-none blue--text text-caption">마크다운에 대해 더 알아보기</a></v-col>
          </v-row>
          <v-row class="mt-15 pt-15">
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light py-0">Quick Tips!</div>
            </v-col>
            <v-col cols="12">
                <v-row align="center">
                    <v-item-group v-model="window" class="shrink mr-6" mandatory tag="v-flex">
                    <v-item v-for="n in length" :key="n" v-slot:default="{ active, toggle }">
                        <div>
                        <v-btn :input-value="active" icon @click="toggle" >
                            <v-icon>mdi-record</v-icon>
                        </v-btn>
                        </div>
                    </v-item>
                    </v-item-group>
                    <v-col>
                    <v-window v-model="window" class="elevation-2" vertical >
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Text</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 * 과 ~ 로 굵기, 기울기, 취소선을 적용할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center mt-5">
                                    <ul>
                                        <li>
                                            <div>굵기</div>
                                            <ul>
                                                <li>**강조하고싶은 문구**</li>
                                                <li><strong>강조하고싶은 문구</strong> 처럼 표시됩니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>기울기</div>
                                            <ul>
                                                <li>*기울이고 싶은 문구*</li>
                                                <li><span class="font-italic">기울이고 싶은 문구</span> 처럼 표시됩니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>가로선</div>
                                            <ul>
                                                <li>~~취소선을 긋고자 하는 문구~~</li>
                                                <li><span class="text-decoration-line-through">취소선을 긋고자 하는 문구</span> 처럼 표시됩니다.</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Header</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 #(샵) 기호로 헤더를 조절할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center mt-5">
                                    <ul>
                                        <li>
                                            <div> # + spacebar</div>
                                            <ul>
                                                <li># Sample title</li>
                                                <li class="py-2"><h1 class="d-inline-block">Sample title</h1> 으로 표시됩니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div> examples</div>
                                            <ul>
                                                <li>## 제목 : <h2 class="d-inline-block">제목</h2> 으로 표시됩니다.</li>
                                                <li>### 제목 : <h3 class="d-inline-block">제목</h3> 으로 표시됩니다.</li>
                                                <li>#### 제목 : <h4 class="d-inline-block">제목</h4> 으로 표시됩니다.</li>
                                                <li>##### 제목 : <h5 class="d-inline-block">제목</h5> 으로 표시됩니다.</li>
                                                <li>###### 제목 : <h6 class="d-inline-block">제목</h6> 으로 표시됩니다.</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Code Block</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 code 태그나 `백틱`을 통해 코드를 작성할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center mt-5">
                                    <ul>
                                        <li>
                                            <div>코드 태그 활용하기</div>
                                            <ul>
                                                <li>{{code}}</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>백틱 활용하기</div>
                                            <ul>
                                                <li>{{b}}</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                                <v-col>
                                    <img src="@/assets/howto/block.jpg" alt="" width="100%">
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">List</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 -(대시) 또는 숫자(1, 2, 3 ...)를 통해 리스트를 표현할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center align-center mt-10">
                                    <ul>
                                        <li>
                                            <div>순서가 있는 리스트</div>
                                            <ul>
                                                <li>숫자를 활용합니다.</li>
                                                <li><code>1. + 공백(spacebar)</code>를 입력하면 인식해 순서 있는 리스트로 출력합니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>순서가 없는 리스트</div>
                                            <ul>
                                                <li>-(대시) 를 활용합니다.</li>
                                                <li><code>-(대시) + 공백(spacebar)</code>를 입력하면 인식해 순서 없는 리스트로 출력합니다.</li>
                                                <li>엔터(줄바꿈) + 탭(tab) 클릭 시 리스트의 수준에 변화를 줄 수 있습니다.</li>                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Else...</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row class="pt-5">
                                <v-col cols="6" class="pr-1">
                                    <v-card class="mx-auto" max-width="400" >
                                        <v-img
                                        class="white--text align-end"
                                        height="150px"
                                        src="@/assets/us/note.jpg"
                                        >
                                        <v-card-title>Cheat-sheet</v-card-title>
                                        </v-img>

                                        <v-card-subtitle class="pb-0">Markdown</v-card-subtitle>

                                        <v-card-text class="text--primary">
                                        <div>Quick and easy tip</div>
                                        </v-card-text>
                                        <v-card-actions class="d-flex justify-end">
                                        <a href="https://www.markdownguide.org/cheat-sheet/" class="text-decoration-none" target="_blank"><v-btn color="blue" text>VISIT</v-btn></a>
                                        </v-card-actions>
                                    </v-card>
                                </v-col>
                                <v-col cols="6" class="pl-1">
                                    <v-card class="mx-auto" max-width="400" >
                                        <v-img
                                        class="white--text align-end"
                                        height="150px"
                                        src="@/assets/howto/toast.jpg"
                                        >
                                        <v-card-title>Editor</v-card-title>
                                        </v-img>

                                        <v-card-subtitle class="pb-0">TOAST UI</v-card-subtitle>

                                        <v-card-text class="text--primary">
                                        <div>TOAST UI Editor</div>
                                        </v-card-text>
                                        <v-card-actions class="d-flex justify-end">
                                        <a href="https://ui.toast.com/tui-editor/" class="text-decoration-none" target="_blank"><v-btn color="blue" text>VISIT</v-btn></a>
                                        </v-card-actions>
                                    </v-card>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                    </v-window>
                    </v-col>
                </v-row>
            </v-col>
          </v-row>       
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">HOW TO</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">PLOG Note</v-col>
          </v-row>
          <v-row class="mt-5 pt-10">
            <v-col cols="12" class="pb-5">
              <div class="text-center display-1 font-weight-light">How to use?</div>
            </v-col>
            <v-col cols="12" class="text-center grey--text py-0">
              PLOG는 마크다운 양식을 지원하는
            </v-col>
            <v-col cols="12" class="text-center grey--text py-0">
              Toast UI 에디터를 사용합니다. 
            </v-col>
          </v-row>
        <v-row class="mt-15 pt-5">
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light">Markdown</div>
            </v-col>
            <v-col cols="12" class="text-center grey--text pb-0">
                <a href="https://www.markdownguide.org/" target="_blank"><img src="@/assets/howto/markdown.jpg" alt="" width="80%"></a>
            </v-col>
            <v-col cols="12" class="pt-0 pb-2 text-center"><a href="https://guides.github.com/features/mastering-markdown/" target="_blank" class="text-decoration-none blue--text text-caption">마크다운에 대해 더 알아보기</a></v-col>
            <v-col cols="1"></v-col>
            <v-col cols="10" class="text-center grey--text text-justify">
              마크다운이란, 텍스트 기반의 마크업 언어로 글을 보다 읽고 작성할 수 있게 돕습니다. 문법이 비교적 간단하며 쉽게 HTML 변환이 가능해 최근들어 크게 각광받고 있습니다.
            </v-col>
            <v-col cols="1"></v-col>
          </v-row>
            <v-row class="mt-15 pt-5">
            <v-col cols="12">
              <div class="text-center display-1 font-weight-light py-0">Quick Tips!</div>
            </v-col>
            <v-col cols="12">
            <v-item-group v-model="window" class="shrink d-flex justify-center" mandatory tag="v-flex">
            <v-item v-for="n in length" :key="n" v-slot:default="{ active, toggle }">
                <div>
                <v-btn :input-value="active" icon @click="toggle" >
                    <v-icon small>mdi-record</v-icon>
                </v-btn>
                </div>
            </v-item>
            </v-item-group>
            </v-col>
            <v-col cols="12">
                <v-row align="center">
                    <v-col>
                    <v-window v-model="window" class="elevation-2" >
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Text</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 * 과 ~ 로 굵기, 기울기, 취소선을 적용할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center mt-5">
                                    <ul>
                                        <li>
                                            <div>굵기</div>
                                            <ul>
                                                <li>**강조하고싶은 문구**</li>
                                                <li><strong>강조하고싶은 문구</strong> 처럼 표시됩니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>기울기</div>
                                            <ul>
                                                <li>*기울이고 싶은 문구*</li>
                                                <li><span class="font-italic">기울이고 싶은 문구</span> 처럼 표시됩니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>가로선</div>
                                            <ul>
                                                <li>~~취소선을 긋고자 하는 문구~~</li>
                                                <li><span class="text-decoration-line-through">취소선을 긋고자 하는 문구</span> 처럼 표시됩니다.</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Header</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 #(샵) 기호로 헤더를 조절할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center mt-5">
                                    <ul>
                                        <li>
                                            <div> # + spacebar</div>
                                            <ul>
                                                <li># Sample title</li>
                                                <li class="py-2"><h1 class="d-inline-block">Sample title</h1> 으로 표시됩니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div> examples</div>
                                            <ul>
                                                <li>## 제목 : <h2 class="d-inline-block">제목</h2> 으로 표시됩니다.</li>
                                                <li>### 제목 : <h3 class="d-inline-block">제목</h3> 으로 표시됩니다.</li>
                                                <li>#### 제목 : <h4 class="d-inline-block">제목</h4> 으로 표시됩니다.</li>
                                                <li>##### 제목 : <h5 class="d-inline-block">제목</h5> 으로 표시됩니다.</li>
                                                <li>###### 제목 : <h6 class="d-inline-block">제목</h6> 으로 표시됩니다.</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Code Block</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 code 태그나 `백틱`을 통해 코드를 작성할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center mt-5">
                                    <ul>
                                        <li>
                                            <div>코드 태그 활용하기</div>
                                            <ul>
                                                <li>{{code}}</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>백틱 활용하기</div>
                                            <ul>
                                                <li>{{b}}</li>
                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                                <v-col class="text-caption grey--text text-center mt-5">
                                    PC 버전에서 예시 이미지를 확인하실 수 있습니다.
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">List</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row>
                                <v-col cols="12">
                                    마크다운은 -(대시) 또는 숫자(1, 2, 3 ...)를 통해 리스트를 표현할 수 있습니다.
                                </v-col>
                                <v-col cols="12" class="d-flex justify-center align-center mt-10">
                                    <ul>
                                        <li>
                                            <div>순서가 있는 리스트</div>
                                            <ul>
                                                <li>숫자를 활용합니다.</li>
                                                <li><code>1. + 공백(spacebar)</code>를 입력하면 인식해 순서 있는 리스트로 출력합니다.</li>
                                            </ul>
                                        </li>
                                        <li>
                                            <div>순서가 없는 리스트</div>
                                            <ul>
                                                <li>-(대시) 를 활용합니다.</li>
                                                <li><code>-(대시) + 공백(spacebar)</code>를 입력하면 인식해 순서 없는 리스트로 출력합니다.</li>
                                                <li>엔터(줄바꿈) + 탭(tab) 클릭 시 리스트의 수준에 변화를 줄 수 있습니다.</li>                                            </ul>
                                        </li>
                                    </ul>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                        <v-window-item>
                        <v-card flat min-height="400">
                            <v-card-text>
                            <v-row class="mb-4" align="center">
                                <strong class="text-h5 ml-5">Else...</strong>
                            </v-row>
                            <v-divider></v-divider>
                            <v-row class="pt-5">
                                <v-col cols="12" class="mt-5">
                                    <v-card class="mx-auto" max-width="400" >
                                        <v-card-subtitle class="pb-0">Markdown</v-card-subtitle>
                                        <v-card-text class="text--primary">
                                        <div>Quick and easy tip</div>
                                        </v-card-text>
                                        <v-card-actions class="d-flex justify-end py-0">
                                        <a href="https://www.markdownguide.org/cheat-sheet/" class="text-decoration-none" target="_blank"><v-btn color="blue" small text>VISIT</v-btn></a>
                                        </v-card-actions>
                                    </v-card>
                                </v-col>
                                <v-col cols="12" class="">
                                    <v-card class="mx-auto" max-width="400" >
                                        <v-card-subtitle class="pb-0">TOAST UI</v-card-subtitle>
                                        <v-card-text class="text--primary">
                                        <div>TOAST UI Editor</div>
                                        </v-card-text>
                                        <v-card-actions class="d-flex justify-end py-0">
                                        <a href="https://ui.toast.com/tui-editor/" class="text-decoration-none" target="_blank"><v-btn color="blue" small text>VISIT</v-btn></a>
                                        </v-card-actions>
                                    </v-card>
                                </v-col>
                            </v-row>
                            </v-card-text>
                        </v-card>
                        </v-window-item>
                    </v-window>
                    </v-col>
                </v-row>
            </v-col>
          </v-row>       
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'Howto',
    data() {
      return {
      length: 5,
      window: 0,
      code: '<code>print("이렇게 활용해보세요")</code>',
      b: '```(백틱 세 개)+사용하려는언어(생략가능) + 작성하려는 코드 + ```(백틱 세 개)'
        }
    },
    created() {
      window.scrollTo({top:0, left:0, behavior:'smooth'});
    },
    methods: {

    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
.link-icon {
  width: 30%;

}
.link-tag {
  text-decoration: none;
  color: #c7c7c7;
  text-align: center;
}
</style>