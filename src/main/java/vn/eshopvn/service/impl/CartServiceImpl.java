package vn.eshopvn.service.impl;

import java.util.List;

import vn.eshopvn.dao.CartDAO;
import vn.eshopvn.dao.impl.CartDAOImpl;
import vn.eshopvn.entity.Cart;
import vn.eshopvn.entity.CartItem;
import vn.eshopvn.service.CartService;

public class CartServiceImpl implements CartService{

	CartDAO cDAO = new CartDAOImpl();
	
	@Override
	public boolean isProductInCart(String cartId, String productId) {
		return cDAO.isProductInCart(cartId, productId);
	}

	@Override
	public boolean clearCart(String cartId) {
		try {
			cDAO.clearCart(cartId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeCartItem(String pid, String cartid) {
		try {
			cDAO.removeCartItem(pid, cartid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCartItem(CartItem cartItem) {
		try {
			cDAO.updateCartItem(cartItem);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addCartItem(CartItem cartItem) {
		try {
			cDAO.addCartItem(cartItem);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartItem> getCartItemsByCartId(String cartId) {
		return cDAO.getCartItemsByCartId(cartId);
	}

	@Override
	public Cart getCartByUserId(String userId) {
		return cDAO.getCartByUserId(userId);
	}

	@Override
	public void createCart(Cart cart) {
		try {
			cDAO.createCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CartItem getItemInCart(String cartId, String productId) {
		return cDAO.getItemInCart(cartId, productId);
	}
}
