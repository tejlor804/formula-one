<template>
    <v-dialog v-model="visible" width="500">
        <v-card>
            <v-card-title>Deleting team {{ teamToDelete.name }}</v-card-title>
            <v-card-text>
                Are you sure you want to delete team {{ teamToDelete.name }}?
            </v-card-text>
            <v-card-actions>
                <v-spacer/>
                <v-btn rounded color="primary" :loading="loading" dark @click="close">
                    Cancel
                </v-btn>
                <v-btn rounded color="error" :loading="loading" @click="deleteTeam">
                    Delete
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>
<script>
import {useTeamStore} from '../stores/TeamStore';

export default {
    data() {
        return {
            visible: false,
            teamToDelete: {},
            teamStore: useTeamStore()
        }
    },
    methods: {
        open: function (team) {
            this.teamToDelete = team;
            this.visible = true;
        },
        deleteTeam: async function() {
            this.loading = true;
            const result = await this.teamStore.deleteTeam(this.teamToDelete.id)
            if(result.status === 200) {
                this.$emit('delete-successful');
            }
            else {
                this.$emit('delete-unsuccessful', result);
            }
            this.loading = false;
            this.visible = false;
        },
             close() {
             this.$emit('cancel');
             this.visible = false;
             }
    }
}
</script>