import http from 'k6/http';

export default function () {
    console.log('--- 8080 포트로 기본 GET 요청 전송 ---');
    try {
        const res1 = http.get('http://localhost:8080/');
        console.log(`8080 서버 응답: status=${res1.status}, body=${res1.body}`);
    } catch (error) {
        console.error('8080 서버 요청 중 에러 발생:', error);
    }

    console.log('--- 8081 포트로 기본 GET 요청 전송 ---');
    try {
        const res2 = http.get('http://localhost:8081/');
        console.log(`8081 서버 응답: status=${res2.status}, body=${res2.body}`);
    } catch (error) {
        console.error('8081 서버 요청 중 에러 발생:', error);
    }
}
