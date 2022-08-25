<template>
  <div>
    <Loader v-if="!isLoaded" />
    <div id="app">
      <nav>
        <router-link to="/">Add Players</router-link> |
        <router-link to="/about">Check Combo</router-link>
      </nav>
      <router-view />
    </div>
  </div>
</template>
<script>
import Loader from "./components/Loader.vue";

export default {
  data() {
    return {
      isLoaded: false,
    };
  },
  components: {
    Loader,
  },
  mounted() {
    document.onreadystatechange = () => {
      if (document.readyState == "complete") {
        this.isLoaded = true;
      }
    };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
