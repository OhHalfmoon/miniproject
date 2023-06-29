# miniproject
미니프로잭트 : 파일영속성 및 싱글톤을 활용한 자바콘솔 쇼핑몰 구현


자바 콘솔에서 구현하는 미니프로잭트 입니다.  
중고거래라는 컨셉을 활용하기 위해 회원가입시 주소명을 받았습니다.  
화면ui가 아닌 콘솔에서 작동이기때문에 정확하게 주소명을 기입해야 카카오api가 작동한다는 단점이 있습니다.  
카카오 api key를 지운상태입니다. 사용하신다면 MemberServiceImpl 에서 getJSONData 메서드의 String auth에 키를 입력해야 합니다.  
더미데이터 유저는  
ID : police PW : 112  
ID : center PW : 93  
ID : tj PW :306  
관리자 계정은  
ID admin PW :team5  
사용하실 수 있습니다.  
DB가 아닌, 파일로 물건을 관리됩니다.  
폴더 내 ser 파일을 삭제하면 초기 더미데이터 값을 불러오며,  
ser파일이 있을경우 물품수량, 구매내역, 회원등이 더미데이터값과 다를 수 있습니다.  
  
제가 맡은 역할은 장바구니 및 구매 입니다. (Cart, Order)  
물품을 장바구니에 담고, 장바구니의 물품을 구매할 수 있습니다.  
장바구니에 같은 물건을 여러번 담을 수 있는데, 이럴경우 총 담은 물건의 수가 재고량보다 많을경우, 보유량보다 초과로 담을 수 없다는 메세지가 출력됩니다.  
  
장바구니에 물건을 담는것은 실제로 구매하는것은 아니기에 실제 상품리스트를 product와 ware(실제 재고)로 구분하고 있습니다.  
물건을 담으면 product의 수량이 차감되지만 아직 구매는 이루어지지 않았으므로 ware의 수량은 차감되지 않습니다.  
이렇게 분류한 이유는 파일영속화와 관련이 있습니다.  
예를 들어 바지가 총 10벌일때,  
A 회원으로 로그인하여 바지를 5벌 장바구니에만 담았습니다.  
로그아웃 하고 B회원으로 로그인하면 구매가 이루어지지 않았으나 화면에 바지 재고량이 10개가 아닌 5개로 나오는 문제가 있었습니다. 이를 해결하기위해 구분하였습니다.  
  
반대로 장바구니 물품을 삭제할경우 차감된 product 수량은 증가하지만 이또한 실제 구매가 아니었기에 실제 재고에는 영향을 끼치지 않습니다.  
  
장바구니의 물건을 실제로 구매할 경우 ware의 수량을 set을 통해 구매된 물량만큼 줄여줍니다.  
이후 장바구니리스트를 초기화 합니다.  
  
기존의 장바구니 리스트의 물건들은 그대로 구매내역 리스트로 add 됩니다.  
add리스트의 index번호가 결재번호가 됩니다. findByReceipt에서 userId를 파라미터로 받아와 해당 회원의 구매내역과 총 구매액을 보여줍니다.  
