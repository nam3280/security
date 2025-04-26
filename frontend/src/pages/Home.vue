<template>
  <div class="home">
    <div class="album py-5 bg-body-tertiary">
      <div class="container">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <!--v-for="(배열[idx],인덱스) in state.items 응답으로 받은 배열" :key="인덱스"-->
          <!--key순으로 배열이 돈다.-->
          <div class="col" v-for="(item, idx) in state.items" :key="idx">
            <!--자식 컴포넌트인 Card에 item 값을 전달한다. 그럼 Card는 props로 받는다.-->
            <Card :item="item"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import {reactive} from "vue";
import Card from "@/components/Card.vue";

export default {
  name: 'Home',
  components:{Card},
  setup(){
    const state = reactive({
      items: []
    })
    axios.get("/api/items").then(({data}) =>{
      state.items = data;
    })
    return {state};
  }
}
</script>

<style scoped>
</style>