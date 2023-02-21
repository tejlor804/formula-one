<template>
  <v-container>
    <v-row>
      <v-col cols="4"/>
      <v-col align="center">
        <v-form ref="registerForm">
          <v-text-field 
            v-model="username" 
            label="Username" 
            :rules="nameRules"
            solo
            />
          <v-text-field 
            v-model="password" 
            label="Password" 
            solo
            :rules="passwordRules"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'"
            @click:append="showPassword = !showPassword"
            @click-append-outer=""
          >
          <v-tooltip slot="append-outer" right>
            <template v-slot:activator="{ on }">
                <v-icon v-on="on" color="primary" dark>
                  mdi-information-outline
                </v-icon>
              </template>
            <span>Password rules: <br/>
                  At least 1 lowercase character<br/>
                  At least 1 uppercase character<br/>
                  At least 1 special character<br/>
                  At least 1 digit<br/>
                  At least 8 characters long
            </span>
          </v-tooltip>
          </v-text-field>
        </v-form>
        <v-btn 
          rounded 
          color="success" 
          :loading="loading"
          @click="register">
            Register
        </v-btn>
      </v-col>
      <v-col cols="4" />
    </v-row>
    <v-row>
      <v-col align="center">
        <v-btn 
          rounded 
          color="primary" 
          @click="$router.push('/login')">
            Back to login
        </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="4" />
      <v-col>
        <v-alert type="error" :value="error.visible">
          {{ error.message }}  
          </v-alert>
      </v-col>
      <v-col cols="4" />
    </v-row>
    <v-snackbar v-model="success">Registration successful!</v-snackbar>
  </v-container>
</template>
<script>
import router from '../router';
import {useUserStore} from '../stores/UserStore';

export default {
  data() {
    return {
      username: "",
      password: "",
      userStore: useUserStore(),
      showPassword: false,
      success: false,
      loading: false,
      passwordRegex: "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
      error: {
        visible: false,
        message: ""
      },
      nameRules: [
        v => !!v || 'Username must not be empty!'
      ],
      passwordRules: [
        v => !!v || 'Password must not be empty!',
        v => v.match(this.passwordRegex) || "Password doesn't match the rules!"
      ],
    }
  },
  methods: {
    register: async function() {
      if (this.$refs.registerForm.validate()) {
        this.loading = true;
        let result = await this.userStore.register({username: this.username, password: this.password});
        if(result.status === 200) {
          this.success = true;
          router.push("/");
        }
        else {
          this.error.visible = true;
          this.error.message = result.message;
        }
        this.loading = false;
      }  
    }
  }
}
</script>