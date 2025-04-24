//cors 설정
//백엔드는 8080포트, 프론트는 3000포트를 사용하는데
//백엔드에서 요청을 하면 응답이 프론트로 가지 않음.(포트가 다르기 때문)
//이를 해결하기 위한 설정을 하는 부분
module.exports = {
    devServer: {
        proxy: {
            '/api' : {
                target: 'http://localhost:8080',
            }
        }
    }
}
