애플리케이션 배포 방법
1) script.sql 파일의 내용을 실행 (db생성, 테이블 생성)
2) 스프링 부트로 만들어 놓은 gradlew 파일 경로 확인 
3) cmd로 해당 경로로 이동
4) 'gradlew build' 명령어 입력하면, 빌드가 진행 
5) \build\libs 폴더 내에 jar 파일이 생성 확인 
6) java -jar 명령어 실행 

사이트 성능 향상 방안
1) Scale out 적용 (서버 갯수 증가)
2) Replication 적용