<template>
    <v-dialog v-model="visible" width="500">
        <v-card>
            <v-card-title>Creating new team</v-card-title>
            <v-card-text>
                <input-form v-model="teamToCreate" ref="inputForm" />
            </v-card-text>
            <v-card-actions>
                <v-btn rounded color="error" :loading="loading" dark @click="close">
                    Cancel
                </v-btn>
                <v-spacer/>
                <v-btn rounded color="primary" :loading="loading" @click="createTeam">
                    create
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
            teamStore: useTeamStore(),
            teamToCreate: {
                name: "",
                wonWorldChampionships: 0,
                paidEntryFee: false
            },
            visible: false,
            loading: false
        }
    },
    methods: {
        createTeam: async function () {
            if(this.$refs.inputForm.validate()) {
                this.loading = true;
                let result = await this.teamStore.createTeam(this.teamToCreate);
                if(result.status === 200) {
                this.$emit('create-successful');
            }
            else {
                this.$emit('create-unsuccessful', result);
            }
            this.teamToCreate = {};
            this.loading = false;
            this.visible = false;
            } 
        },
        open() {
        this.visible = true;
    },
    close() {
    this.$emit('cancel');
    this.visible = false;
    }
    },
    

}
</script>