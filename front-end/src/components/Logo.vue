<template>
  <div class="logo-wrapper">
    <div class="logo-wrapper">
      <img class="logo" src="/static/images/logo.png">
      <div class="tagline">Simple and Modern TaskAgile Application.</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Logo'
}
</script>

<style lang="scss" scoped>
  .logo-wrapper {
    margin-bottom: 40px;
    text-align: center;
    .logo {
        max-width: 250px;
        margin: 0 auto;
    }

    .tagline {
        line-height: 180%;
        color: #666;
    }
  }
</style>
