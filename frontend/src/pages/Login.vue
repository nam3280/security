<script>
  import {reactive} from "vue";
  import axios from "axios";
  import store from "@/scripts/store";
  import router from "@/scripts/router";

  export default {
    setup(){
      const state = reactive({
        from:{
          email: "",
          password: ""
        }
      })

      const submit = () => {
        axios.post("/api/account/login", state.from).then((res)=>{
          store.commit('setAccount', res.data);
          sessionStorage.setItem("id", res.data);
          router.push({path: "/"});
          window.alert("로그인");
        }).catch(()=>{
          window.alert("로그인 정보x");
        });
      }
      return {state, submit};
    }
  }
</script>

<template>
  <main class="form-signin w-100 m-auto">
    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" @keyup.enter="submit()" v-model="state.from.email">
      <label for="floatingInput">Email address</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" @keyup.enter="submit()" v-model="state.from.password">
      <label for="floatingPassword">Password</label>
    </div>
    <div class="form-check text-start my-3">
      <input class="form-check-input" type="checkbox" value="remember-me" id="checkDefault">
      <label class="form-check-label" for="checkDefault" @keyup.enter="submit()">
        Remember me
      </label>
    </div>
    <button class="btn btn-primary w-100 py-2" @click="submit()">Sign in</button>
    <p class="mt-5 mb-3 text-body-secondary">© 2017–2025</p>
  </main>
</template>

<style scoped>
  .form-signin {
    max-width: 330px;
    padding: 1rem;
  }

  .form-signin .form-floating:focus-within {
    z-index: 2;
  }

  .form-signin input[type="email"] {
    margin-bottom: -1px;
    border-bottom-right-radius: 0;
    border-bottom-left-radius: 0;
  }

  .form-signin input[type="password"] {
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }
</style>