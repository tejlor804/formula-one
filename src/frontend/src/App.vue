<template>
  <v-app :style="{ backgroundImage: 'url(' + require('@/assets/background.jpg') + ')' , backgroundSize: '100%'}">
    <v-container>
      <v-row justify="space-around">
        <v-col class="d-flex justify-center text-h1">
          <span class="red--text">Formula </span><span class="white--text"> One</span>
        </v-col>
      </v-row>
      <router-view/>
    </v-container>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
    //
  }),
};
</script>
<style lang="scss">
#app {
  margin: 0 !important;
}
</style>
