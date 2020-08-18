<template>
  <div>
      <div class="d-none d-sm-block">
        <div class="content-center mx-auto">
          <v-row class="pt-10">
            <v-col cols="12"  class="py-0">
              <div class="text-center display-1 font-weight-light py-0">GALLERY</div>
            </v-col>
            <v-col cols="2" class="py-0"></v-col>
            <v-col cols="8" class="text-center py-0 grey--text">
              BEST PLOGGERS
            </v-col>
            <v-col cols="2" class="py-0"></v-col>
          </v-row>
          <v-row class="">
            <v-col cols="2"></v-col>
            <v-col cols="8">
            <agile class="main" ref="main" :options="options1" :as-nav-for="asNavFor1">
              <div class="slide bigimg"><img src="@/assets/gallery/p1.jpg" class="bigimg"/></div>
              <div class="slide bigimg"><img src="@/assets/gallery/p2.jpg" class="bigimg"/></div>
              <div class="slide bigimg"><img src="@/assets/gallery/p3.jpg" class="bigimg"/></div>
              <div class="slide bigimg"><img src="@/assets/gallery/p4.jpg" class="bigimg"/></div>
              <div class="slide bigimg"><img src="@/assets/gallery/p5.jpg" class="bigimg"/></div>
              <div class="slide bigimg"><img src="@/assets/gallery/p6.jpg" class="bigimg"/></div>
              <div class="slide bigimg"><img src="@/assets/gallery/p7.jpg" class="bigimg"/></div>
              <div class="slide bigimg"><img src="@/assets/gallery/p8.jpg" class="bigimg"/></div>
                <!-- <div class="slide" v-for="(slide, index) in slides" :key="index" :class="`slide--${index}`"><img :src="slide"/></div> -->
              </agile>
              <agile class="thumbnails mt-n5" ref="thumbnails" :options="options2" :as-nav-for="asNavFor2">
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(0)"><img src="@/assets/gallery/p1.jpg"/></div>
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(1)"><img src="@/assets/gallery/p2.jpg"/></div>
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(2)"><img src="@/assets/gallery/p3.jpg"/></div>
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(3)"><img src="@/assets/gallery/p4.jpg"/></div>
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(4)"><img src="@/assets/gallery/p5.jpg"/></div>
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(5)"><img src="@/assets/gallery/p6.jpg"/></div>
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(6)"><img src="@/assets/gallery/p7.jpg"/></div>
                <div class="slide slide--thumbniail smallslide" @click="$refs.thumbnails.goTo(7)"><img src="@/assets/gallery/p8.jpg"/></div>
                <!-- <div class="slide slide--thumbniail" v-for="(slide, index) in slides" :key="index" :class="`slide--${index}`" @click="$refs.thumbnails.goTo(index)"><img :src="slide"/></div> -->
                <template slot="prevButton"><v-icon>mdi-chevron-double-left</v-icon></template>
                <template slot="nextButton"><v-icon>mdi-chevron-double-right</v-icon></template>
              </agile>
              </v-col>
              <v-col cols="2"></v-col>
          </v-row>       
        </div>
      </div>
      <div class="d-block d-sm-none">
        <v-container>
          <v-row>
            <v-col cols="12" class="py-1 text-h5">Gallery</v-col>
            <v-col cols="12" class="py-1 text-h4 font-weight-bold">PLOGGERS</v-col>
          </v-row>
          <v-row class="mt-10 text-center">
            <v-col cols="12">
              <v-carousel
                cycle
                :show-arrows="true"
                hide-delimiters
                height="100%"
              >
                <v-carousel-item>
                  <div class="text-center">
                    <img src="@/assets/gallery/pp1.jpg" alt="plog_image" width="100%" class="ml-3">
                  </div>
                </v-carousel-item>
                <v-carousel-item>
                  <div class="text-center">
                    <img src="@/assets/gallery/pp2.jpg" alt="plog_image" width="100%" class="ml-3">
                  </div>
                </v-carousel-item>
                <v-carousel-item>
                  <div class="text-center">
                    <img src="@/assets/gallery/pp3.jpg" alt="plog_image" width="100%" class="ml-3">
                  </div>
                </v-carousel-item>
                <v-carousel-item>
                  <div class="text-center">
                    <img src="@/assets/gallery/pp4.jpg" alt="plog_image" width="100%" class="ml-3">
                  </div>
                </v-carousel-item>
                <v-carousel-item>
                  <div class="text-center">
                    <img src="@/assets/gallery/pp5.jpg" alt="plog_image" width="100%" class="ml-3">
                  </div>
                </v-carousel-item>
              </v-carousel>
            </v-col>
          </v-row>
        </v-container>
      </div>
  </div>
</template>

<script>
import { VueAgile } from 'vue-agile'


export default {
    name: 'Gallery',
    components: {
      agile: VueAgile,
    },
    data() {
      return {
      asNavFor1: [],
			asNavFor2: [],
			options1: {
				dots: false,
				fade: true,
				navButtons: false
			},
			
			options2: {
				autoplay: true,
				centerMode: true,
				dots: false,
				navButtons: false,
				slidesToShow: 3,
				responsive: [
                {
                    breakpoint: 600,
                    settings: {
                        slidesToShow: 5
                    }
                },
                
                {
                    breakpoint: 1000,
                    settings: {
                        navButtons: true
                    }
                }
            ]
				
			},

		}
    },
    methods: {

    },
    mounted () {
      this.asNavFor1.push(this.$refs.thumbnails)
      this.asNavFor2.push(this.$refs.main)
    },
    created() {
      window.scrollTo({top:0, left:0, behavior:'smooth'});
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

.main {
  margin-bottom: 10px;
}

.thumbnails {
  margin: 0 -5px;
  width: calc(100% + 10px);

}

.agile__nav-button {
  background: transparent;
  border: none;
  color: #ccc;
  cursor: pointer;
  font-size: 24px;
  -webkit-transition-duration: 0.3s;
          transition-duration: 0.3s;
}
.thumbnails .agile__nav-button {
  position: absolute;
  top: 50%;
  -webkit-transform: translateY(-50%);
          transform: translateY(-50%);
}
.thumbnails .agile__nav-button--prev {
  left: -45px;
}
.thumbnails .agile__nav-button--next {
  right: -45px;
}
.agile__nav-button:hover {
  color: #888;
}
.agile__dot {
  margin: 0 10px;
}
.agile__dot button {
  background-color: #eee;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  display: block;
  height: 10px;
  font-size: 0;
  line-height: 0;
  margin: 0;
  padding: 0;
  -webkit-transition-duration: 0.3s;
          transition-duration: 0.3s;
  width: 10px;
}
.agile__dot--current button, .agile__dot:hover button {
  background-color: #888;
}

.slide {
  -webkit-box-align: center;
          align-items: center;
  box-sizing: border-box;
  color: #fff;
  display: -webkit-box;
  display: flex;
  height: 450px;
  -webkit-box-pack: center;
          justify-content: center;
}
.slide--thumbniail {
  cursor: pointer;
  height: 100px;
  margin-right: 20px;
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 0 5px;
  -webkit-transition: opacity 0.3s;
  transition: opacity 0.3s;
}
.slide--thumbniail:hover {
  opacity: 0.75;
}
.slide img {
  height: 100%;
  -o-object-fit: cover;
     object-fit: cover;
  -o-object-position: center;
     object-position: center;
  width: 100%;
  
}

.smallslide {
    box-shadow: 2px 2px 2px 2px rgb(163, 163, 163);
}
.bigimg {
  height: 60vh;
}
.carousel-3d-slide {
  padding: 20px;
}
.title {
  font-size: 2px;
}
</style>
