import { defineStore } from 'pinia'
import axios from 'axios'

const API_URL = "http://localhost:8080/api";

export const useUserStore  = defineStore({
  id: 'user',
  state: () => ({
    token: "",
    loggedIn: false,

  }),
  actions: {
    async login(input) {
      try {
        let result = await axios.post(`${API_URL}/auth/login`, input);
        if (result.data.token) {
          localStorage.setItem('user', JSON.stringify(result.data));
          this.username = input.username;
          this.loggedIn = true;
        }
        return result;
      }
      catch(e) {
        return e;
      }
      
    },
    async validateToken(token) {
      try {
        let result = await axios.get(`${API_URL}/auth/validateToken`);
        return result;
      }
      catch(e) {
        return e;
      }
    },
    logout() {
      localStorage.setItem('user', "");
      this.username = "";
      this.loggedIn = false;
    },
    async register(input) {
      try {
        let result = await axios.post(`${API_URL}/auth/register`, input);
        return result;
      }
      catch(e) {
        return e;
      }
      
    },
  }
})
