<template>
  <v-container>
    <v-row class="mx-2">
      <v-btn rounded color="primary" @click="createTeam">create team</v-btn>
      <v-spacer/>
      <v-btn rounded color="error" @click="logout">logout</v-btn>
    </v-row>
    <v-row>
      <v-col>
        <v-data-table :headers="headers" :items="teams">
          <template v-slot:item.paidEntryFee="{ item}">
            <v-icon v-if="item.paidEntryFee" color="success">
              mdi-check-circle-outline
            </v-icon>
            <v-icon v-else color="error">
              mdi-close-circle-outline
            </v-icon>
          </template>
          <template v-slot:item.actions="{ item }">
            <v-icon
              small
              class="mr-2"
              color="primary"
              @click="openEditDialog(item)"
            >
              mdi-pencil
            </v-icon>
            <v-icon
              small
              color="error"
              @click="openDeleteDialog(item)"
            >
              mdi-delete
            </v-icon>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
    <v-row>
      <v-alert type="error" :value="error.visible">
        {{ error.message }}  
      </v-alert>
    </v-row>
    <delete-dialog 
      ref="deleteDialog"
      @cancel="loadTeams"
      @delete-successful="onSuccess('Delete')"
      @delete-unsuccessful="displayError($event)"
    />
    <create-dialog 
      ref="createDialog"
      @cancel="loadTeams"
      @create-successful="onSuccess('Creation')"
      @create-unsuccessful="displayError($event)"
      />
    <edit-dialog 
      ref="editDialog"
      @cancel="loadTeams"
      @edit-successful="onSuccess('Edit')"
      @edit-unsuccessful="displayError($event)" 
      />
    <v-snackbar v-model="snackbar.visible">
      {{ snackbar.message }}
    <template v-slot:action="{ attrs }">
      <v-btn
        color="blue"
        text
        v-bind="attrs"
        @click="snackbar.visible = false"
      >
      Close
      </v-btn>
    </template>
  </v-snackbar>
</v-container>
</template>
<script>
import router from '../router';
import {useTeamStore} from '../stores/TeamStore';
import {useUserStore} from '../stores/UserStore';
import DeleteDialog from '../components/DeleteDialog.vue' 
import CreateDialog from '../components/CreateDialog.vue' 
import EditDialog from '../components/EditDialog.vue' 

export default {
  components: {
    DeleteDialog,
    CreateDialog,
    EditDialog
  },
    async mounted() {
        this.loadTeams();
    },
  data() {
    return {
        teams: [],
        selectedTeam: {},
      teamStore: useTeamStore(),
      userStore: useUserStore(),
      editDialogOpen: false,
      error: {
        message: "",
        visible: false
      },
      snackbar: {
        message: "",
        visible: false
      },
      headers: [
        {text: 'Name', value: 'name'},
        {text: 'Founding year', value: 'foundingYear'},
        {text: 'Won world championships', value: 'wonWorldChampionships'},
        {text: 'Paid entry fee?', value: 'paidEntryFee'},
        {text: 'Actions', value: 'actions'}
    ]
    }
  },
  methods: {
    logout: function() {
        this.userStore.logout();
        router.push('/login')
    },
    loadTeams: async function() {
      await this.teamStore.listTeams();
      this.teams = this.teamStore.$state.teams;
    },
    createTeam: async function() {
      this.$refs.createDialog.open();
    },
    editTeam(team) {
      this.selectedTeam = team;
      this.editDialogOpen = true;
    },
    openDeleteDialog(item) {
      this.$refs.deleteDialog.open(item)
    },
    openEditDialog(item) {
      this.$refs.editDialog.open(item)
    },
    displayError(error) {
      this.error.visible = true;
      this.error.message = error.message;
    },
    onSuccess(actionType) {
      this.loadTeams();
      this.snackbar.visible = true;
      this.snackbar.message = `${actionType} successful`;
    }
  }
}
</script>