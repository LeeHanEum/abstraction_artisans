package Main.mall;

import Main.mall.Item.Product;
import Main.mall.dto.CartDto;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private final User user;

    private final List<Product> productList;

    private final LocalDateTime createdAt;

    public Cart(User user) {
        this.user = user;
        this.productList = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }


    // 장바구니에 상품 담기
    public void addItemToCart(Product product) {
        productList.add(product);
        System.out.println("장바구니에 상품을 담았습니다.");
    }

    // 장바구니에서 상품 삭제
    public void deleteItemFromCart(Long productId) {
        for (Product p : productList){
            if (Objects.equals(p.getProductId(), productId)){
                productList.remove(p);
            }
        }
        System.out.println("장바구니에서 상품을 삭제했습니다.");
    }

    // 장바구니에서 상품 수량 조절
    public void EditQuantityFromCart(Long productId, int quantity) {
        Product product = null;
        for (Product p : productList){
            if (Objects.equals(p.getProductId(), productId)){
                product = p;
                return;
            }
        }
        int difference = quantity - getQuantityInCart(product);
        if (difference > 0){
            for (int i = 0; i < difference; i++){
                productList.add(product);
            }
        } else if (difference < 0){
            for (int i = 0; i < -difference; i++){
                productList.remove(product);
            }
        }
        System.out.println("장바구니에서 상품 수량을 조절했습니다.");
    }

    // 장바구니에서 상품 리스팅 조회
    public void getItemListFromCart() {
        System.out.println("장바구니에서 상품을 조회했습니다.");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    // 장바구니 상품 선택
    public List<Product> selectItemFromCart(Long[] productIds) {
        List<Product> selectedProductList = new ArrayList<>();
        for (Product p : productList){
            for (Long id : productIds){
                if (Objects.equals(p.getProductId(), id)){
                    selectedProductList.add(p);
                }
            }
        }
        return selectedProductList;
    }

    public int getQuantityInCart(Product product){
        int quantityInCart = 0;
        for (Product p : productList){
            if (Objects.equals(p.getProductId(), product.getProductId())){
                quantityInCart++;
            }
        }
        return quantityInCart;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for (Product p : productList){
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

//    public static Cart builder(CartDto cartDto){
//        Cart cart = new Cart();
//        cart.user = cartDto.getUser();
//        cart.productList = cartDto.getProductList();
//        cart.createdAt = LocalDateTime.now();
//        return cart;
//    }

}
