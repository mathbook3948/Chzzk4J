# Chzzk4J

---

**Chzzk4J**는 `socket.io`와 `Jetty` 기반으로 동작하며, **치지직 Open API**와의 연결을 쉽게 도와주는 Java 라이브러리입니다.

---
```java
public class Example {

  public static void main(String[] args) {
    String clientId = "...";
    String clientSecret = "...";

    ChzzkClient client = ChzzkClient.build(clientId, clientSecret);
    Response<CreateClientSessionResponse> res = client.createClientSession();
    //...
  }
}
```
## 주요 기능
[치지직 공식 API 문서](https://chzzk.gitbook.io/chzzk)에 명시되어 있는 대부분의 API 연동 단순화

## 설치
- Releases에서 다운로드 후 수동 설치 필요(~~추후 Maven Central에 등록 예정~~)

## 요구사항
- Java 17이상