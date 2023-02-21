<template>
    <v-form ref="form">
        <label>
            Team name
        </label>
        <v-text-field v-model = "value.name" :rules="nameRules">
        </v-text-field>
        <label>
            Founding year
        </label>
        <v-text-field type="number" v-model = "value.foundingYear" :rules="foundingYearRules">
        </v-text-field>
        <label>
            Won world championships
        </label>
        <v-text-field type="number" v-model = "value.wonWorldChampionships" :rules="championshipRules">
        </v-text-field>
        <label>
            Paid entry fee?
        </label>
        <v-checkbox v-model = "value.paidEntryFee" :checked="value.paidEntryFee" />
    </v-form>
</template>
<script>
export default {
    data() {
        return {
            currentYear: new Date().getFullYear(),
            valid: false,
            nameRules: [
            v => !!v || 'Name is required',
            ],
            foundingYearRules: [
            v => !!v || 'Founding year is required',
            v => (v >= 1950 && v <= this.currentYear) || `Founding year must be between 1950 and ${
                this.currentYear}!`,
            ],
            championshipRules: [
            v => v >= 0 || 'Won world championships should be more than or equal to 0!',
            ],
        }
    },
    props: {
        value: {}
    },
    methods: {
        validate: function() {
            return this.$refs.form.validate();
        }
    }
}
</script>