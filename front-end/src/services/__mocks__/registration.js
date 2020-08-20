export default {
  register (detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'onair1127@naver.com'
        ? resolve({ result: 'success' })
        : reject(new Error('User already exist'))
    })
  }
}
