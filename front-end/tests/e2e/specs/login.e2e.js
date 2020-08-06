// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

module.exports = {  // node.js에서의 모듈 정의, 나이트왓치 테스트를 여기에 작성
  'login test': function(browser) {
    browser
      .url(process.env.VUE_DEV_SERVER_URL + 'login')
      .waitForElementVisible('#app', 5000)
      .assert.containsText('h1', 'TaskAgile')
      .end()
  }
}
