package team5.service;
import static team5.util.Utils.nextInt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.List;

import team5.util.Utils;
import team5.vo.Order;
import team5.vo.Product;


public class OrderServiceImpl implements OrderService{
		
	// 싱글톤
	private static OrderService orderService = new OrderServiceImpl();
	
	private OrderServiceImpl() {
		
	}
	
	public static OrderService getInstance() {
		return orderService;
	}

	private List<Order> receipts = new ArrayList<Order>();		
	
	public List<Order> getReceipts() {
		return receipts;
	}

	public void setReceipts(List<Order> receipts) {
		this.receipts = receipts;
	}

	CartService cartService = CartServiceImpl.getInstance();
	
	ProductService productService = ProductServiceImpl.getInstance();
	
	MemberService memberService = MemberServiceImpl.getInstance();
//	private Product product;
//	private Order order;
	
	{
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Order.ser"))) {
			receipts = (List<Order>)ois.readObject();
		}
		catch (FileNotFoundException e) {
			
		}
		catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	

	
	@Override
	//물건구매
	public void buy() {
		 
		productService = ProductServiceImpl.getInstance();
		cartService = CartServiceImpl.getInstance();

		
		System.out.println("============장바구니=============");
		cartService.displayCart();
		System.out.println("=================================");
		System.out.println("주문하신 상품을 구매하시겠습니까?");
		int question = Utils.nextInt("1. 예                   2. 아니요");			
		if (question == 1) {
			if (cartService.getCarts().size()!=0) {					
				System.out.println("주문이 완료되었습니다.");
				System.out.println("============주문내역=============");	
				for(int i = 0; i < cartService.getCarts().size(); i++) {
					
					String mem = memberService.getLoginUser().getUserId();
					Product p = cartService.getCarts().get(i);
					
					if (mem.equals(cartService.getCarts().get(i).getWriter())) {						
						receipts.add(new Order(receipts.size()+1, mem, p.getProName(), p.getProId(), p.getProCnt(), p.getProPrice()));
					}
				}
//				for (Product p : cartService.getCarts()) {
//					receipts.add(new Order(receipts.size()+1, memberService.getLoginUser().getUserId(), 
//						p.getProName(), p.getProId(), p.getProCnt(), p.getProPrice()));
//				}
			//여기서 장바구니 초기화 진행
//				for (int i =0; i<productService.getWare().size(); i++) {
//					Product waremin = productService.getWare().get(i);
//					Product proddmin = productService.getProducts().get(i);
//					waremin.setProCnt(proddmin.getProCnt());
//					productService.save();
//					productService.saveWare();
//				}
				for (int i =0; i<productService.getWare().size(); i++) {
					Product waremin = productService.getWare().get(i);
					Product proddmin = productService.getProducts().get(i);
					
					waremin.setProCnt(proddmin.getProCnt());
					productService.save();
					productService.saveWare();
				}
				String Id = memberService.getLoginUser().getUserId();
				
				for(int i = 0; i < cartService.getCarts().size(); i++) {					
					Product p = cartService.getCarts().get(i);					
					if (Id.equals(cartService.getCarts().get(i).getWriter())) {						
						cartService.getCarts().remove(i);
					}
				}
//				cartService.getCarts().clear();
				
				for (Order o : receipts) {				
					if(o.getUserId().equals(Id)) {
						System.out.println(o);
					}
				} int sum = 0;
				for (Order o : receipts) {
					if(o.getUserId().equals(Id)) {
						sum = sum + o.getProPrice();
					} 
				}
				System.out.println("총 합계 : "+ sum+"원");
				save();		
			}else System.err.println("장바구니에 상품이 없습니다");
			} 		
//		}
		else if (question == 2) {
			System.out.println("주문이 취소되었습니다");
		}
		else 
			System.err.println("잘못 입력하셨습니다.");
		
	}

	@Override
//	public voId removeOrder() {
//		// TODO Auto-generated method stub
//		product = cartService.findByCart(nextInt("상품코드를 입력하세요"));
//		cartService.getCarts().remove(product);		
//		System.out.println("항목이 삭제되었습니다.");	
//	}
	
	public Order findByOrder(String userId, int proId) {	
		Order order = null;
		for (Order o : receipts) {
			if ((o.getUserId().equals(userId)) && (o.getProId() == proId)) {
				order = o;
				break;
			}
		}
		return order;
	}
	
	public Order findByReceipt(String userId) {
		Order order = null;
		for (Order o : receipts) {
			if (o.getUserId().equals(userId)) {
				order = o;
				break;
			}
		}
		return order;
	}
	
	public void displayOrder() {
//		String Id = Utils.nextLine("ID를 입력하세요");
		String Id = memberService.getLoginUser().getUserId();
		for (Order o : receipts) {
			if(o.getUserId().equals(Id)) {
				System.out.println(o);
			}
		} int sum = 0;
		
		for (Order o : receipts) {
			if(o.getUserId().equals(Id)) {
				sum = sum + o.getProPrice();
			}
		}
		System.out.println("총 합계 : "+ sum+"원");
	}
	
	private void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Order.ser"))){
			oos.writeObject(receipts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
//	public static void main(String[] args) {
//		OrderService ord = OrderServiceImpl.getInstance();
//		CartService ca = CartServiceImpl.getInstance();
//		ca.addCart(3003);
//		ca.removeCart();
//		ord.buy();
//		ca.display();
//		ord.displayOrder();	
//	}
}


