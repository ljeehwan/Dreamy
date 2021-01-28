import axios from 'axios'

const SERVER_URL="http://localhost:8080";
// 회원가입
function requestJoinMember(credentials) {
    credentials.email = credentials.email.toLowerCase()
    return axios.post(`${SERVER_URL}/account/signup`, credentials)
}
// 회원 정보 변경
function requestUpdateMember(credentials, userId) {
    return axios.put(`${SERVER_URL}/account/update/${userId}`, credentials)
}
// 회원 정보 삭제
function requestDeleteMember(userId) {
    return axios.delete(`${SERVER_URL}/account/delete/${userId}`)
}


//로그인
function requestLoginMember(user) {
    return axios.post(`${SERVER_URL}/account/login`, user)
}


function setSnackBarInfo(text, color, location) {
    return {
        text: text,
        color: color,
        location: location,
    }
}

export {
    setSnackBarInfo,
    requestJoinMember,
    requestLoginMember,
    requestUpdateMember,
    // requestMemberInfo,
    requestDeleteMember,
}