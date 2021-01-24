import axios from 'axios'

const SERVER_URL="http://localhost:8080";

function requestJoinMember(credentials) {
    credentials.email = credentials.email.toLowerCase()
    return axios.post(`${SERVER_URL}/account/signup`, credentials)
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
    requestLoginMember
}