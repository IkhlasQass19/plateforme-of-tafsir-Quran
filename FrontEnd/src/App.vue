<script setup>
import {RouterView} from 'vue-router'
import Navbar from "./components/global/Navbar.vue";
</script>

<template>
  <header>
    <div class="wrapper">
      <navbar></navbar>
    </div>
  </header>
  <div class="container">
    <RouterView/>
  </div>
</template>

<style>
@import './assets/css/main.css';
</style>
<style scoped>
.container {
  padding-top: 6rem;
  padding-bottom: 2rem;
  min-height: 94vh;
}
</style>