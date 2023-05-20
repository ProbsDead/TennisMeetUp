<template>
  <div id="app">
    
    <router-view />

  </div>
</template>

<script>
export default {
  components: { 
    
   },
};
</script>

<style>


#app {
  display: grid;
  grid-template-columns: 1fr;
  grid-template-areas: "nav"
                        "view"
                        "footer";
  
  gap: 10px;
}
#content {
  padding-bottom: 2.5rem;
}
/* nav-bar {
  grid-area: nav;
} */
router-view {
  grid-area: view;
}
.footer {
  grid-area: footer;
  /* position: absolute; */
  bottom: 0;

  /* height: 2.5rem; */
  /* margin: 10%; */
}
/* @media print{
.noprint{
  display: none;
}
} */
</style>

