<template>
    <v-dialog v-model="visible" width="500">
    <v-card>
        <v-card-title>Editing team {{ name }}</v-card-title>
        <v-card-text>
    <input-form v-model="teamToEdit" ref="editInputForm">
    </input-form>
        </v-card-text>
        <v-card-actions>
                <v-btn rounded color="error" dark @click="visible = false">
                    Cancel
                </v-btn>
                <v-spacer/>
                <v-btn rounded color="primary" @click="editTeam">
                    Edit
                </v-btn>
                
        </v-card-actions>
    </v-card>
</v-dialog> 
</template>
<script>
import InputForm from './InputForm.vue';
import { useTeamStore } from '../stores/TeamStore';

export default {
    components: {
        InputForm
    },
    data() {
        return {
            name: "",
            teamStore: useTeamStore(),
            visible: false,
            teamToEdit: {},
            loading: false
        }
    },
    methods: {
        editTeam: async function () {
            if(this.$refs.editInputForm.validate()) {
                this.loading = true;
                const teamInput = {
                    name: this.teamToEdit.name,
                    wonWorldChampionships: this.teamToEdit.wonWorldChampionships,
                    paidEntryFee: this.teamToEdit.paidEntryFee,
                    foundingYear: this.teamToEdit.foundingYear
                };
                let result = await this.teamStore.editTeam(this.teamToEdit.id, teamInput);
                if(result.status === 200) {
                this.$emit('edit-successful');
            }
            else {
                this.$emit('edit-unsuccessful', result);
            }
            this.loading = false;
            this.visible = false;
            } 
        },
        open(teamToEdit) {
            this.teamToEdit = teamToEdit;
            this.name = teamToEdit.name;
            this.visible = true;
        }
    }
}
</script>