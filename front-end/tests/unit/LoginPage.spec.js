import Vue from 'vue'
import LoginPage from '@/views/LoginPage'

describe('LoginPage.vue', () => {
  it('should render corrent contents', () => {
    const Constructor = Vue.extend(LoginPage)
    const vm = new Constructor().$mount()
    expect(vm.$el.querySelector('h1').textContent)
      .toEqual('TaskAgile')
  })
})
