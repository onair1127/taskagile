/**
 * RegisterPage.vue TDD
 */

import { mount, createLocalVue } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage'
import registerationService from '@/services/registration'
import VueRouter from 'vue-router'
import Vuelidate from 'vuelidate'

/**
 * vm.$router에 접근할 수 있도록
 * 테스트에 Vue Router 추가
 */
const localVue = createLocalVue()
localVue.use(VueRouter)
localVue.use(Vuelidate)
const router = new VueRouter()

// registrationService의 Mock
jest.mock('@/services/registration')

describe('RegisterPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldEmailAddress
  let fieldPassword
  let buttonSubmit
  let registerSpy

  beforeEach(() => {
    wrapper = mount(RegisterPage, {
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find("form button[type='submit']")

    registerSpy = jest.spyOn(registerationService, 'register')
  })

  // Spy 객체가 쓰인 테스트는 끝난 후 리셋
  afterEach(() => {
    registerSpy.mockReset()
    registerSpy.mockRestore()
  })

  // 다 끝나면 registrationService 복구
  afterAll(() => {
    jest.restoreAllMocks()
  })

  /**
   * vue-tests-utils API 테스트
   * UI 레이아웃의 값들이 정상 출력됬는지, 폼의 필드값의 초기값이 ''인지
   * HTML 요소, HTMLElement, DOM 노드 속성 가져오기
   */
  it('should render correct contents', () => {
    // const Constructor = Vue.extend(RegisterPage)
    // const vm = new Constructor().$mount()
    expect(wrapper.find('.logo').attributes().src)
      .toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text())
      .toEqual('Simple and Modern TaskAgile Application.')

    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create Account')
  })

  /**
   * Vue 인스턴스 접근
   * 각 form-group에 해당하는 프로퍼티의 초기값이 빈 문자열임을 테스트
   */
  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  /**
   * 제출 핸들러의 존재 여부 확인 테스트
   * jest 와 stub을 활용
   */
  it('should have form submit event handler `submitForm`', () => {
    const stub = jest.fn()
    wrapper.setMethods({ submitForm: stub })
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })

  /**
   * 회원가입 검증 성공 테스트
   * 새로운 유저일 경우 회원가입 성공
   * register() 메소드는 Promise 기반이기 때문에 vm.$nextTick에 expect 구문을 넣어야 한다.
   */
  it('should register when it is a new user', async () => {
    // __mocks__에서는 오직 onair1127@naver.com만 새로운 사용자다
    expect.assertions(2)

    const stub = jest.fn()
    wrapper.vm.$router.push = stub // vm.$router의 push 메소드 스텁을 만들어서 리다이렉트가 발생했는지 확인
    wrapper.vm.form.username = 'onair1127'
    wrapper.vm.form.emailAddress = 'onair1127@naver.com'
    wrapper.vm.form.password = 'snrnsk12@'

    wrapper.vm.submitForm() // 폼 제출
    expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(stub).toHaveBeenCalledWith({ name: 'LoginPage' })
  })

  /**
   * 회원가입 검증 실패 테스트
   * 유저가 이미 존재할 경우 회원가입 실패
   */
  it('should fail it is not a new user', async () => {
    // __mocks__에서는 오직 onair1127@naver.com만 새로운 사용자다
    expect.assertions(2)

    wrapper.vm.form.username = 'okjung'
    wrapper.vm.form.emailAddress = 'okj@naver.org'
    wrapper.vm.form.password = 'snrnsk12@'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    // eslint-disable-next-line no-unused-expressions
    expect(wrapper.find('.failed').isVisible()).toBeTrue
  })

  /**
   * 폼 Data Validation 검증 테스트
   * 백엔드로 데이터를 보내기 전에 1차적으로 검증
   */
  it('should fail when the email address is invalid', () => {
    wrapper.vm.form.emailAddress = 'bad-email-address'
    wrapper.vm.submitForm()
    expect(registerSpy).not.toHaveBeenCalled()
  })
})
