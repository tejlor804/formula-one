import { defineStore } from 'pinia'
import axios from 'axios'

const API_URL = "http://localhost:8080/api/";


export const useTeamStore  = defineStore({
  id: 'team',
  state: () => ({
    teams: []
  }),
  actions: {
    async listTeams() {
      try {
        let result = await axios.get(API_URL + "teams", {headers: { Authorization: `Bearer ${
            JSON.parse(localStorage.getItem('user')).token
        }` }});
        console.log(result);
        this.teams = result.data;
        return result;
      }
      catch(e) {
        return e;
      }
      
    },
    async createTeam(input) {
        try {
          let result = await axios.post(API_URL + "teams", input, {headers: { Authorization: `Bearer ${
              JSON.parse(localStorage.getItem('user')).token
          }` }});
          console.log("teams:", result);
          return result;
        }
        catch(e) {
          return e;
        }
        
      },
      async editTeam(teamId, teamInput) {
        try {
          console.log(teamInput);
          let result = await axios.patch(API_URL + `teams/${teamId}`, teamInput, {headers: { Authorization: `Bearer ${
              JSON.parse(localStorage.getItem('user')).token
          }` }});
          return result;
        }
        catch(e) {
          return e;
        }
        
      },
      async deleteTeam(teamId) {
        try {
          let result = await axios.delete(API_URL + `teams/${teamId}`, {headers: { Authorization: `Bearer ${
              JSON.parse(localStorage.getItem('user')).token
          }` }});
          return result;
        }
        catch(e) {
          return e;
        }
        
      },
  }
})
