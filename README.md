# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# Git commit 메세지

접두어로 `docs`, `test`, `feat`, `fix`, `refactor`, `chore` 사용
example feat: 사용자 입력 후 도메인 사용

## 기능 목록

### 덱

- [x] 생성 시점에서 카드를 섞는다.
- [x] 카드를 뽑을 수 있다.
- [ ] 카드가 존재하지 않을 경우, 예외가 발생한다.

### 플레이어들

- [x] 승패를 계산해서 반환한다.
    - [x] 딜러는 모든 플레이어와 결과를 계산한다.
    - [x] 플레이어는 딜러와의 결과만을 계산한다.
    - [x] 딜러가 burst되었을 경우
        - [x] 21이하 참가자는 모든 1승
        - [x] burst된 참가자는 패로 기록한다.
    - [x] 딜러가 21 이하인데, 플레이어랑 다른 겨우
        - [x] 참가자는 숫자를 비교하여 21에 가까운 사람이 승리한다.
        - [x] 딜러와 참가자의 수가 같은 경우, 패로 기록한다.
        - [x] burst된 참가자는 패로 기록한다.
    - [x] 딜러와 플레이어의 점수가 같은 경우
        - [x] 블랙잭인 사람이 승리한다.
        - [x] 그래도 무승부일 경우는 무승부로 판정한다.

### 딜러

- [x] 참가자가 카드를 뽑는다.
- [x] 딜러의 손패는 한장만 반환한다.
- [x] 딜러는 16점 이하면 카드를 추가 지급한다.
  - [x] 16점 비교 로직을 딜러 내에서 관리한다.

### 플레이어

- [x] 참가자가 카드를 뽑는다.
- [x] 참가자가 뽑을 수 있는 상태인지 확인한다.

### 카드 손패

- [x] 들고 있는 카드의 전체 점수를 반환한다.
- [x] 카드는 점수를 반환한다.
    - [x] Ace는 1 또는 11점으로 계산한다.
    - [x] J,Q,K는 10으로 계산한다.
    - [x] Ace는 21보다 클 경우에는 1로, 그렇지 않으면 11로 계산한다.

### 카드

- [x] 카드의 기호와 숫자를 반환한다.

### 입출력

- [x] 참가자 이름을 입력 받는다.
    - [x] 각 참가자는 ,로 구분한다.
    - [ ] 참가자 이름의 중복을 검증한다.
    - [ ] 위의 형식에 맞지 않은 이름 형식은 예외가 발생한다.
- [x] 딜러의 카드는 한 장만 공개한다.
- [x] 게임 완료 후 승패를 출력한다.
- [x] 카드 분배 결과를 출력한다.
- [x] 카드를 추가 지급 받을지 물어본다.
    - [x] 응답은 y/n로만 받을 수 있다.
    - [x] 응답이 n이거나 카드의 합이 21일때까지 물어본다.
    - [x] y/n 여부와 상관없이 현플레이어의 손패를 출력한다.
    - [ ] 위의 형식에 맞지 않은 응답은 예외가 발생한다.

### 리팩터링

- [x] 플레이어가 딜러로부터 카드를 받도록 리팩터링한다.
    - 딜러는 덱을 필드로 갖는다.
- [x] 딜러의 이름 필드를 제거한다.
- [ ] 공통된 코드 추가 메서드로 묶는다.
- [x] `protected` 필드 직접 접근 문제 해결한다.
- [ ] `Participant`를 `Gamer`로 변경한다. 
