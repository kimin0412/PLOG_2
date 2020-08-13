<template>
  <v-dialog v-model="modal" scrollable max-width="300px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn color="primary darken-1" dark v-bind="attrs" v-on="on" class="px-5" small>Category</v-btn>
    </template>
    <v-card>
      <v-card-title>Categories</v-card-title>
      <v-divider></v-divider>
      <v-card-text style="height: 300px;">
        <div v-if="categories.length > 0">
          <v-radio-group v-model="category" column>
            <div v-for="(item, i) in categories" v-bind:key="i">
              <div v-if="item.cId != 1">
                <v-radio v-bind:label="item.cName" v-bind:value="item.cId"></v-radio>
              </div>
            </div>
          </v-radio-group>
        </div>
        <div v-else>
          <div>생성된 폴더가 없습니다.</div>
        </div>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions class="d-flex justify-end">
        <v-btn color="blue darken-1" text @click="dialogCategory = false">Save</v-btn>
        <v-btn
          color="blue darken-1"
          text
          @click="
                        category = 1;
                        dialogCategory = false;
                      "
        >Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data: () => {
    return {};
  },
};
</script>