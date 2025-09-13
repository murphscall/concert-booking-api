import http from 'k6/http';
import {check, sleep} from 'k6';
import exec from 'k6/execution';

export function setup() {
    const loginRes = http.post('http://localhost:8080/api/auth/login', JSON.stringify({
        email: 'test@naver.com', // 실제 로그인 가능한 계정
        password: 'test1111^^',      // 실제 비밀번호
    }), {
        headers: {'Content-Type': 'application/json'},
    });

    if (loginRes.status !== 200) {
        exec.test.abort('Login failed, aborting test.');
    }

    const accessToken = loginRes.cookies.accessToken[0].value;
    return accessToken;
}

export const options = {
    scenarios: {
        burst_booking: {
            executor: 'ramping-vus',
            startVUs: 0,
            stages: [
                {duration: '5s', target: 50},
                {duration: '10s', target: 50},
                {duration: '10s', target: 0},
            ],
        },
    },
};

export default function (accessToken) {
    if (!accessToken) {
        return;
    }

    const port = 8080 + (exec.vu.idInTest % 2);
    const url = `http://localhost:${port}/api/bookings`;

    const payload = JSON.stringify({
        ticketId: 1, // DB에 실제로 존재하는 티켓 ID
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
            'Cookie': `accessToken=${accessToken}`,
        },
    };

    const bookingRes = http.post(url, payload, params);

    check(bookingRes, {
        '예매 성공 (201 Created)': (r) => r.status === 201,
        '예매 실패/경합 (400)': (r) => r.status === 400,
    });

    sleep(1);
}