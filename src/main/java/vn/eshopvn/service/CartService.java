package vn.eshopvn.service;

import java.util.List;

import vn.eshopvn.entity.Cart;
import vn.eshopvn.entity.CartItem;

public interface CartService {

	boolean isProductInCart(String cartId, String productId);

	boolean clearCart(String cartId);

	boolean removeCartItem(String pid, String cartid);

	boolean updateCartItem(CartItem cartItem);

	boolean addCartItem(CartItem cartItem);

	List<CartItem> getCartItemsByCartId(String cartId);

	Cart getCartByUserId(String userId);

	void createCart(Cart cart);
	
	CartItem getItemInCart(String cartId, String productId);
}
