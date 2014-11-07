package com.includesys.sm.service.product;

import java.util.List;

import com.includesys.sm.dto.product.Product;
 


public interface ProductService {

	public List<Product> getProductList();
	public List<Product> getProductCodeList(String search_code);
	public Product getProductCodeListView(String search_code);
	public Product getProductCodeSearch(String search_code);	
	public void registerProductCode(Product product);
	public int updateProductCode(Product product);
}
