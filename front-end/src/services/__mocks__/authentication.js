export default {
  authenticate (detail) {
    return new Promise((resolve, reject) => {
      (detail.username === 'onair1127' || detail.username === 'onair1127@naver.com') &&
      detail.password === 'snrnsk12@' ? resolve({ result: 'success' }) : reject(new Error('Invalid credentials'))
    })
  }
}
