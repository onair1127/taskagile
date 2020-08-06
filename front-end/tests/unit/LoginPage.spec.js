import Vue from 'vue';
import LoginPage from '@/views/LoginPage.vue';

// Jest의 describe(name, fn) API로 하나의 테스트 스위트를 만듬
describe('LoginPage.vue', () => {

  // Jest의 it(name, fn, timeout)
  // name: 테스트명, fn: 테스트의 예상값 함수, timeout: 타임아웃(ms, 생략하면 5초)
  //
  it('should render correct contents', () => {
    const Constructor = Vue.extend(LoginPage) // Vue의 하위 클래스인 LoginPage를 생성
    const vm = new Constructor().$mount()     // 인스턴스 생성후 부착, $mount는 페이지에 렌더링한것처럼 해줌
    expect(vm.$el.querySelector('h1').textContent)
            .toEqual('TaskAgile')
  })
})
