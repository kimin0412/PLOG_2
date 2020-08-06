<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-container>
            <v-row :class="{beforesearched:!searched}">
              <v-col cols="12">
                <div class="text-center display-1 font-weight-light">Search Group</div>
              </v-col>
              <v-col cols="12" class="text-center grey--text mb-4">
                찾으려는 그룹의 이름을 검색해주세요 :)
              </v-col>
              <v-col cols="12" class="py-1 text-h6">
                <v-text-field
                  prepend-icon="mdi-magnify"
                  placeholder="group name"
                  single-line
                  solo
                  rounded
                  clearable
                  autofocus
                  v-model="searchgroup"
                  @keyup.enter="complete"
                  hint="그룹 이름으로 검색해주세요"
                >
                </v-text-field>
              </v-col>
            </v-row>
            <!-- 검색 결과 -->
            <v-row v-if="searched">
              {{searchgroup}}에 대한 검색결과입니다.
            </v-row>
          </v-container>
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">GROUP</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">SEARCH</v-col>
          </v-row>
          <v-row class="mt-10">
            <v-col cols="12" class="py-1 text-h6">
              <v-text-field
                prepend-icon="mdi-magnify"
                placeholder="group name"
                single-line
                solo
                rounded
                clearable
                autofocus
                v-model="searchword"
                @keyup.enter="complete2"
                hint="그룹 이름으로 검색해주세요"
              >
              </v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
export default {
    name: 'GroupSearch',
    data() {
      return {
        searchgroup: '',
        searched: false,
      }
    },
    watch: {
      searchgroup: function() {
        if (this.searchgroup === '') {
          this.searched = false
        }
      }
    },
    methods: {
      complete() {
        this.searched = true
      },
      complete2() {
        this.searched = true
      }
    }
}
</script>

<style scoped>
.content-center {
  width: 85%;
}
.beforesearched {
  margin-top: 23vh;
}
</style>