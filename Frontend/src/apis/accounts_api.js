import axios from 'axios'

const SERVER_URL="http://localhost:8080";

function requestJoinMember(credentials) {
    credentials.email = credentials.email.toLowerCase()
    return axios.post(`${SERVER_URL}/account/signup`, credentials)
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
    requestJoinMember
}