<template>
  <div>
      <v-row>
          <v-col
            cols="12"
            >
              <v-carousel>
                <v-carousel-item
                v-for="(item,i) in items"
                :key="i"
                :src="item.src"
                reverse-transition="fade-transition"
                transition="fade-transition"
                ></v-carousel-item>
              </v-carousel>
          </v-col>
      </v-row>
  </div>
</template>

<script>
export default {
    name: 'mainpage',
    data() {
        return {
          headerOn: false,
          items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],
        }
    },
    created() {
      window.scrollTo({top:0, left:0, behavior:'smooth'});
    },
}
</script>

<style>

</style>