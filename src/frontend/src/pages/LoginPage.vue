<template>
  <div>
        <v-row>
          <v-col cols="4"/>
          <v-col align="center">
            <v-text-field v-model="username" label="Username" solo/>
            <v-text-field v-model="password" label="Password" solo
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'"
            @click:append="showPassword = !showPassword"
            />
            <v-btn rounded color="primary" @click="login">login</v-btn>
          </v-col>
          <v-col cols="4"/>
        </v-row>
        <v-row>
          <v-col cols="4"/>
          <v-col align="center">
            <v-alert type="error" :value="error.visible">
              {{ error.message }}  
            </v-alert>
          </v-col>
          <v-col cols="4"/>
        </v-row>
        <v-row>
          <v-col align="center">
            <v-btn @click="$router.push('/register')" rounded color="success">Register</v-btn>
          </v-col>
        </v-row>
      </div>
</template>
<script>
import router from '../router';
import {useUserStore} from '../stores/UserStore';

export default {
  data() {
    return {
      showPassword: false,
      username: "",
      password: "",
      userStore: useUserStore(),
      error: {
        visible: false,
        message: ""}
    }
  },
  methods: {
    login: async function() {
        let result = await this.userStore.login({username: this.username, password: this.password});
        if(result.response && result.response.status === 403) {
          this.error.visible = true;
          this.error.message = "Wrong username and/or password."
        }
        if(this.userStore.$state.loggedIn) {
          router.push('/')
        }
    }
  }

}
</script>
<style lang="scss">
  .v-alert .v-alert__content{
    font-size: 12px;
  }
</style>