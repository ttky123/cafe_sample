# 카페 사장님의 제품 관리 시스템 만들기
------
# 요구사항
- 사장님은 시스템에 휴대폰번호와 비밀번호 입력을 통해서 회원 가입을 할 수 있습니다. 
  - 사장님의 휴대폰 번호를 올바르게 입력했는지 확인해주세요
  - 비밀번호를 안전하게 보관할 수 있는 장치를 만들어주세요
- 사장님은 회원 가입이후, 로그인과 로그아웃을 할 수 있습니다. 
- 사장님은 로그인 이후 상품관련 아래의 행동을 할 수 있습니다. 
  - 상품의 필수속성(required)은 다음과 같습니다. 
    - 카테고리
    - 가격
    - 원가 
    - 이름 
    - 설명
    - 바코드
    - 유통기한 
    - 사이즈 : small or large
- 사장님은 상품을 등록할 수 있습니다. 
- 사장님은 상품의 속성을 부분 수정할 수 있습니다. 
- 사장님은 상품을 삭제 할 수 있습니다
- 사장님은 등록한 상품의 리스트를 볼 수 있습니다. 
    - cursor based pagination 기반으로, 1page 당 10개의 상품이 보이도록 구현
- 사장님은 등록한 상품의 상세 내역을 볼 수 있습니다. 
- 사장님은 상품 이름을 기반으로 검색할 수 있습니다. 
    - 이름에 대해서 like 검색과 초성검색을 지원해야 합니다.
    - 예) 슈크림 라떼 → 검색가능한 키워드 : 슈크림, 크림, 라떼, ㅅㅋㄹ, ㄹㄸ
- 로그인하지 않은 사장님의 상품 관련 API에 대한 접근 제한 처리가 되어야 합니다.
------------------------
# 구현 내용
![image](https://github.com/ttky123/cafe_sample/assets/43338000/f912311b-50be-4a8a-8b43-b4ebdf766975)

Spring Security + JWT Token을 이용한 api 접근제어

![image](https://github.com/ttky123/cafe_sample/assets/43338000/d2be8713-f5d9-4cc0-9406-08daa04b390b)

![image](https://github.com/ttky123/cafe_sample/assets/43338000/c4500708-b34a-45cf-9986-b47aa0bd033a)

초성 검색을 위한 컬럼 추가, 등록 상품 조회를 위한 다대일 관계 매핑

![image](https://github.com/ttky123/cafe_sample/assets/43338000/13264230-6329-4fc1-bdd7-f58a0f5dc47f)

커서 기반 페이지네이션을 위해 QueryDsl 사용

![image](https://github.com/ttky123/cafe_sample/assets/43338000/10ff31fb-19b1-4e0c-91d7-0c96f98caacf)

login / logout을 위한 AuthConntroller

![image](https://github.com/ttky123/cafe_sample/assets/43338000/50df3e93-16e2-4f6f-9422-ca4ac1cca7e4)

JWT Token 관리를 위한 Redis사용

![image](https://github.com/ttky123/cafe_sample/assets/43338000/28f90da2-a582-4c5f-937d-670fe71138a7)

초성 기반 검색을 위한 추출 메소드 추가
